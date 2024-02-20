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
    override val type: GLObjectType
        get() = GLObjectType.PROGRAM

    private var currentBindingIndex = 0
    private val bufferBindings = EnumMap<BindingTarget, Object2ByteMap<String>>(BindingTarget::class.java)

    private var uniformLookUpCacheStr: String? = null
    private var uniformLookUpCache = -1
    private val uniformLocations = Object2IntOpenHashMap<String>().apply {
        defaultReturnValue(-1)
    }

    private var labelName: String? = null

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

    override fun label0(label: String) {
        if (label != labelName) {
            glObjectLabel(type.identifier, id, label)
        }
        labelName = label
    }

    override fun create() {
        throw UnsupportedOperationException("Shader program cannot be created manually")
    }

    override fun tryCreate() {
        throw UnsupportedOperationException("Shader program cannot be created manually")
    }

    override fun checkCreated() {
        // do nothing
    }

    override fun resetID() {
        throw UnsupportedOperationException("Shader program cannot be reset manually")
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
        if (uniformLookUpCacheStr == name) {
            return uniformLookUpCache
        }

        var loc = uniformLocations.getInt(name)
        if (loc == -1) {
            loc = glGetUniformLocation(id, name)
            uniformLocations.put(name, loc)
        }

        uniformLookUpCacheStr = name
        uniformLookUpCache = loc

        return loc
    }

    fun uniform1i(name: String, value: Int) {
        glProgramUniform1i(id, locateUniform(name), value)
    }

    fun uniform2i(name: String, value1: Int, value2: Int) {
        glProgramUniform2i(id, locateUniform(name), value1, value2)
    }

    fun uniform3i(name: String, value1: Int, value2: Int, value3: Int) {
        glProgramUniform3i(id, locateUniform(name), value1, value2, value3)
    }

    fun uniform4i(name: String, value1: Int, value2: Int, value3: Int, value4: Int) {
        glProgramUniform4i(id, locateUniform(name), value1, value2, value3, value4)
    }

    fun uniform1f(name: String, value: Float) {
        glProgramUniform1f(id, locateUniform(name), value)
    }

    fun uniform2f(name: String, value1: Float, value2: Float) {
        glProgramUniform2f(id, locateUniform(name), value1, value2)
    }

    fun uniform3f(name: String, value1: Float, value2: Float, value3: Float) {
        glProgramUniform3f(id, locateUniform(name), value1, value2, value3)
    }

    fun uniform4f(name: String, value1: Float, value2: Float, value3: Float, value4: Float) {
        glProgramUniform4f(id, locateUniform(name), value1, value2, value3, value4)
    }

    fun uniform1ui(name: String, value: Int) {
        glProgramUniform1ui(id, locateUniform(name), value)
    }

    fun uniform2ui(name: String, value1: Int, value2: Int) {
        glProgramUniform2ui(id, locateUniform(name), value1, value2)
    }

    fun uniform3ui(name: String, value1: Int, value2: Int, value3: Int) {
        glProgramUniform3ui(id, locateUniform(name), value1, value2, value3)
    }

    fun uniform4ui(name: String, value1: Int, value2: Int, value3: Int, value4: Int) {
        glProgramUniform4ui(id, locateUniform(name), value1, value2, value3, value4)
    }

    fun bindBuffer(target: Int, buffer: BufferObject, blockName: String): Boolean {
        return bindBuffer(target, buffer, blockName, -1, -1)
    }

    fun bindBuffer(target: Int, buffer: BufferObject, blockName: String, offset: Long, size: Long): Boolean {
        var bindingIndex: Int

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

        if (offset == -1L || size == -1L) {
            glBindBufferBase(target, bindingIndex, buffer.id)
        } else {
            glBindBufferRange(target, bindingIndex, buffer.id, offset, size)
        }

        return true
    }

    fun bindAtomicCounterBuffer(buffer: BufferObject, bindingIndex: Int) {
        glBindBufferBase(GL_ATOMIC_COUNTER_BUFFER, bindingIndex, buffer.id)
    }

    fun bindAtomicCounterBuffer(buffer: BufferObject, bindingIndex: Int, offset: Long, size: Long) {
        glBindBufferRange(GL_ATOMIC_COUNTER_BUFFER, bindingIndex, buffer.id, offset, size)
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