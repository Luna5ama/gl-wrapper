package dev.luna5ama.glwrapper.impl

import dev.luna5ama.glwrapper.api.*
import it.unimi.dsi.fastutil.objects.Object2ByteMap
import it.unimi.dsi.fastutil.objects.Object2ByteOpenHashMap
import it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap
import java.util.*

open class ShaderProgram(
    vararg shaders: ShaderSource,
) : IGLObject, IGLBinding {
    final override val id: Int
    private var currentBindingIndex = 0
    private val bufferBindings = EnumMap<BindingTarget, Object2ByteMap<String>>(BindingTarget::class.java)

    private val uniformLocations = Object2IntOpenHashMap<String>().apply {
        defaultReturnValue(-1)
    }

    init {
        val programID = glCreateProgram()
        val shaderIDs = IntArray(shaders.size) { i ->
            createShader(shaders[i]).also {
                glAttachShader(programID, it)
            }
        }

        glLinkProgram(programID)
        val linked = glGetProgrami(programID, GL_LINK_STATUS)
        if (linked == 0) {
            System.err.print(glGetProgramInfoLog(programID, glGetProgrami(programID, GL_INFO_LOG_LENGTH)))
            glDeleteProgram(programID)
            throw IllegalStateException("Shader program failed to link")
        }
        this.id = programID

        shaderIDs.forEach {
            glDetachShader(programID, it)
            glDeleteShader(it)
        }
    }

    private fun createShader(source: ShaderSource): Int {
        val id = glCreateShader(source.glTypeEnum)

        glShaderSource(id, source.codeSrc)
        glCompileShader(id)

        val compiled = glGetShaderi(id, GL_COMPILE_STATUS)
        if (compiled == 0) {
            System.err.print(buildString {
                append(glGetShaderInfoLog(id, glGetShaderi(id, GL_INFO_LOG_LENGTH)))
                appendLine("Shader source:")
                source.codeSrc.lines().forEachIndexed { i, it ->
                    append(i + 1)
                    append('\t')
                    appendLine(it)
                }
            })
            System.err.flush()
            glDeleteShader(id)
            throw IllegalStateException("Failed to compile shader: $source")
        }

        return id
    }

    fun locateUniform(name: String): Int {
        var loc = uniformLocations.getInt(name)
        if (loc == -1) {
            loc = glGetUniformLocation(id, name)
            uniformLocations.put(name, loc)
        }
        return loc
    }

    fun bindBuffer(target: Int, buffer: BufferObject, blockName: String): Boolean {
        return bindBuffer(target, buffer, blockName, -1, -1)
    }

    fun bindBuffer(target: Int, buffer: BufferObject, blockName: String, offset: Int, size: Int): Boolean {
        var bindingIndex: Int

        if (target == GL_ATOMIC_COUNTER_BUFFER || target == GL_TRANSFORM_FEEDBACK_BUFFER) {
            bindingIndex = locateUniform(blockName)
        } else {
            val bindingTarget: BindingTarget = BindingTarget[target]
            val map = bufferBindings.getOrPut(bindingTarget) {
                Object2ByteOpenHashMap<String>().apply {
                    defaultReturnValue(-1)
                }
            }

            bindingIndex = map.getByte(blockName).toInt()
            when (bindingIndex) {
                -1 -> {
                    if (!bindingTarget.addBinding(id, blockName, currentBindingIndex)) {
                        map.put(blockName, -2)
                        return false
                    }

                    bindingIndex = currentBindingIndex++
                    map.put(blockName, bindingIndex.toByte())
                }
                -2 -> {
                    return false
                }
            }
        }

        if (offset == -1 || size == -1) {
            glBindBufferBase(target, bindingIndex, buffer.id)
        } else {
            glBindBufferRange(target, bindingIndex, buffer.id, offset.toLong(), size.toLong())
        }

        return true
    }

    override fun bind() {
        glUseProgram(id)
    }

    override fun unbind() {
        glUseProgram(0)
    }

    override fun destroy() {
        glDeleteProgram(id)
    }

    private enum class BindingTarget {
        UNIFORM_BUFFER {
            override fun addBinding(id: Int, blockName: String, bindingIndex: Int): Boolean {
                val blockIndex = glGetProgramResourceIndex(id, GL_UNIFORM_BLOCK, blockName)
                if (blockIndex == GL_INVALID_INDEX) return false

                glUniformBlockBinding(id, blockIndex, bindingIndex)
                return true
            }
        },
        SHADER_STORAGE_BUFFER {
            override fun addBinding(id: Int, blockName: String, bindingIndex: Int): Boolean {
                val blockIndex = glGetProgramResourceIndex(id, GL_SHADER_STORAGE_BLOCK, blockName)
                if (blockIndex == GL_INVALID_INDEX) return false

                glShaderStorageBlockBinding(id, blockIndex, bindingIndex)
                return true
            }
        };

        abstract fun addBinding(id: Int, blockName: String, bindingIndex: Int): Boolean

        companion object {
            operator fun get(target: Int): BindingTarget {
                return when (target) {
                    GL_UNIFORM_BUFFER -> UNIFORM_BUFFER
                    GL_SHADER_STORAGE_BUFFER -> SHADER_STORAGE_BUFFER
                    else -> throw IllegalArgumentException("Unsupported buffer binding target: $target")
                }
            }
        }
    }
}