package dev.luna5ama.glwrapper

import dev.luna5ama.glwrapper.base.*
import dev.luna5ama.glwrapper.enums.GLObjectType
import dev.luna5ama.glwrapper.enums.ShaderStage
import dev.luna5ama.glwrapper.objects.IGLBinding
import dev.luna5ama.glwrapper.objects.IGLObject
import dev.luna5ama.kmogus.Ptr
import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap
import java.util.*

open class ShaderProgram private constructor(
    val shaderSources: List<ShaderSource>,
) : IGLObject, IGLBinding {
    constructor(vararg shaderSources: ShaderSource) : this(shaderSources.toList())

    final override var id = 0; private set
    override val type: GLObjectType
        get() = GLObjectType.Program

    final override var label: String = ""
        set(value) {
            if (field != value) {
                if (value.isNotEmpty()) {
                    glObjectLabel(type.identifier, id, value)
                } else {
                    glObjectLabel(type.identifier, id, 0, Ptr.NULL)
                }
            }
            field = value
        }

    private val shaderStages: Set<ShaderStage> = shaderSources.mapTo(EnumSet.noneOf(ShaderStage::class.java)) {
        it.shaderStage ?: throw IllegalArgumentException("Shader type is not specified $it")
    }

    private lateinit var resourceManager: ShaderProgramResourceManager
    private lateinit var bindingManager: ShaderProgramResourceBindingManager

    init {
        initialize(false)
    }

    fun reload() {
        initialize(true)
    }

    private fun initialize(destroy: Boolean) {
        fun createShader(source: ShaderSource): Int {
            val id = glCreateShader(source.shaderStage!!.value)

            val codeSrc = source.resolveCodeSrc()
            glShaderSource(id, codeSrc)
            glCompileShader(id)

            val compiled = glGetShaderi(id, GL_COMPILE_STATUS)
            if (compiled == 0) {
                System.err.print(buildString {
                    append(glGetShaderInfoLog(id, glGetShaderi(id, GL_INFO_LOG_LENGTH)))
                    appendLine("Shader source:")
                    codeSrc.lineSequence().forEachIndexed { i, it ->
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

        val programID = glCreateProgram()
        if (label != null) {
            glObjectLabel(type.identifier, programID, label!!)
        }
        val shaderIDs = IntArray(shaderSources.size) { i ->
            createShader(shaderSources[i]).also {
                glAttachShader(programID, it)
            }
        }

        glLinkProgram(programID)
        val linked = glGetProgrami(programID, GL_LINK_STATUS)
        if (linked == 0) {
            System.err.print(glGetProgramInfoLog(programID, glGetProgrami(programID, GL_INFO_LOG_LENGTH)))
            glDeleteProgram(programID)
            shaderIDs.forEach {
                glDetachShader(programID, it)
                glDeleteShader(it)
            }
            throw IllegalStateException("Shader program failed to link")
        }

        destroy()
        this.id = programID

        shaderIDs.forEach {
            glDetachShader(programID, it)
            glDeleteShader(it)
        }

        resourceManager = ShaderProgramResourceManager(programID)
        bindingManager = ShaderProgramResourceBindingManager(resourceManager)
    }

    fun applyBinding(spec: ShaderBindingSpecs) {
        runCatching {
            bindingManager.samplerBindings.apply(spec)
            bindingManager.imageBindings.apply(spec)
            bindingManager.bufferBindings.apply(spec)
        }.onFailure {
            it.printStackTrace()
        }
    }

    inline fun applyBinding(block: ShaderBindingSpecs.Builder.() -> Unit) {
        applyBinding(ShaderBindingSpecs.Builder().apply(block).build())
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

    fun uniform1i(name: String, value: Int) {
        val entry = resourceManager.locateUniform(name)?.location ?: return
        glProgramUniform1i(id, entry, value)
    }

    fun uniform1iv(name: String, count: Int, value: Ptr) {
        val entry = resourceManager.locateUniform(name)?.location ?: return
        glProgramUniform1iv(id, entry, count, value)
    }

    fun uniform2i(name: String, value1: Int, value2: Int) {
        val entry = resourceManager.locateUniform(name)?.location ?: return
        glProgramUniform2i(id, entry, value1, value2)
    }

    fun uniform2iv(name: String, count: Int, value: Ptr) {
        val entry = resourceManager.locateUniform(name)?.location ?: return
        glProgramUniform2iv(id, entry, count, value)
    }

    fun uniform3i(name: String, value1: Int, value2: Int, value3: Int) {
        val entry = resourceManager.locateUniform(name)?.location ?: return
        glProgramUniform3i(id, entry, value1, value2, value3)
    }

    fun uniform3iv(name: String, count: Int, value: Ptr) {
        val entry = resourceManager.locateUniform(name)?.location ?: return
        glProgramUniform3iv(id, entry, count, value)
    }

    fun uniform4i(name: String, value1: Int, value2: Int, value3: Int, value4: Int) {
        val entry = resourceManager.locateUniform(name)?.location ?: return
        glProgramUniform4i(id, entry, value1, value2, value3, value4)
    }

    fun uniform4iv(name: String, count: Int, value: Ptr) {
        val entry = resourceManager.locateUniform(name)?.location ?: return
        glProgramUniform4iv(id, entry, count, value)
    }

    fun uniform1ui(name: String, value: Int) {
        val entry = resourceManager.locateUniform(name)?.location ?: return
        glProgramUniform1ui(id, entry, value)
    }

    fun uniform1uiv(name: String, count: Int, value: Ptr) {
        val entry = resourceManager.locateUniform(name)?.location ?: return
        glProgramUniform1uiv(id, entry, count, value)
    }

    fun uniform2ui(name: String, value1: Int, value2: Int) {
        val entry = resourceManager.locateUniform(name)?.location ?: return
        glProgramUniform2ui(id, entry, value1, value2)
    }

    fun uniform2uiv(name: String, count: Int, value: Ptr) {
        val entry = resourceManager.locateUniform(name)?.location ?: return
        glProgramUniform2uiv(id, entry, count, value)
    }

    fun uniform3ui(name: String, value1: Int, value2: Int, value3: Int) {
        val entry = resourceManager.locateUniform(name)?.location ?: return
        glProgramUniform3ui(id, entry, value1, value2, value3)
    }

    fun uniform3uiv(name: String, count: Int, value: Ptr) {
        val entry = resourceManager.locateUniform(name)?.location ?: return
        glProgramUniform3uiv(id, entry, count, value)
    }

    fun uniform4ui(name: String, value1: Int, value2: Int, value3: Int, value4: Int) {
        val entry = resourceManager.locateUniform(name)?.location ?: return
        glProgramUniform4ui(id, entry, value1, value2, value3, value4)
    }

    fun uniform4uiv(name: String, count: Int, value: Ptr) {
        val entry = resourceManager.locateUniform(name)?.location ?: return
        glProgramUniform4uiv(id, entry, count, value)
    }

    fun uniformHandle(name: String, value: Long) {
        val entry = resourceManager.locateUniform(name)?.location ?: return
        glProgramUniformHandleui64ARB(id, entry, value)
    }

    fun uniformHandle(name: String, count: Int, value: Ptr) {
        val entry = resourceManager.locateUniform(name)?.location ?: return
        glProgramUniformHandleui64vARB(id, entry, count, value)
    }

    fun uniform1f(name: String, value: Float) {
        val entry = resourceManager.locateUniform(name)?.location ?: return
        glProgramUniform1f(id, entry, value)
    }

    fun uniform1fv(name: String, count: Int, value: Ptr) {
        val entry = resourceManager.locateUniform(name)?.location ?: return
        glProgramUniform1fv(id, entry, count, value)
    }

    fun uniform2f(name: String, value1: Float, value2: Float) {
        val entry = resourceManager.locateUniform(name)?.location ?: return
        glProgramUniform2f(id, entry, value1, value2)
    }

    fun uniform2fv(name: String, count: Int, value: Ptr) {
        val entry = resourceManager.locateUniform(name)?.location ?: return
        glProgramUniform2fv(id, entry, count, value)
    }

    fun uniform3f(name: String, value1: Float, value2: Float, value3: Float) {
        val entry = resourceManager.locateUniform(name)?.location ?: return
        glProgramUniform3f(id, entry, value1, value2, value3)
    }

    fun uniform3fv(name: String, count: Int, value: Ptr) {
        val entry = resourceManager.locateUniform(name)?.location ?: return
        glProgramUniform3fv(id, entry, count, value)
    }

    fun uniform4f(name: String, value1: Float, value2: Float, value3: Float, value4: Float) {
        val entry = resourceManager.locateUniform(name)?.location ?: return
        glProgramUniform4f(id, entry, value1, value2, value3, value4)
    }

    fun uniform4fv(name: String, count: Int, value: Ptr) {
        val entry = resourceManager.locateUniform(name)?.location ?: return
        glProgramUniform4fv(id, entry, count, value)
    }

    fun uniformMatrix2fv(name: String, count: Int, transpose: Boolean, value: Ptr) {
        val entry = resourceManager.locateUniform(name)?.location ?: return
        glProgramUniformMatrix2fv(id, entry, count, transpose, value)
    }

    fun uniformMatrix3fv(name: String, count: Int, transpose: Boolean, value: Ptr) {
        val entry = resourceManager.locateUniform(name)?.location ?: return
        glProgramUniformMatrix3fv(id, entry, count, transpose, value)
    }

    fun uniformMatrix4fv(name: String, count: Int, transpose: Boolean, value: Ptr) {
        val entry = resourceManager.locateUniform(name)?.location ?: return
        glProgramUniformMatrix4fv(id, entry, count, transpose, value)
    }

    fun uniformMatrix2x3fv(name: String, count: Int, transpose: Boolean, value: Ptr) {
        val entry = resourceManager.locateUniform(name)?.location ?: return
        glProgramUniformMatrix2x3fv(id, entry, count, transpose, value)
    }

    fun uniformMatrix3x2fv(name: String, count: Int, transpose: Boolean, value: Ptr) {
        val entry = resourceManager.locateUniform(name)?.location ?: return
        glProgramUniformMatrix3x2fv(id, entry, count, transpose, value)
    }

    fun uniformMatrix2x4fv(name: String, count: Int, transpose: Boolean, value: Ptr) {
        val entry = resourceManager.locateUniform(name)?.location ?: return
        glProgramUniformMatrix2x4fv(id, entry, count, transpose, value)
    }

    fun uniformMatrix4x2fv(name: String, count: Int, transpose: Boolean, value: Ptr) {
        val entry = resourceManager.locateUniform(name)?.location ?: return
        glProgramUniformMatrix4x2fv(id, entry, count, transpose, value)
    }

    fun uniformMatrix3x4fv(name: String, count: Int, transpose: Boolean, value: Ptr) {
        val entry = resourceManager.locateUniform(name)?.location ?: return
        glProgramUniformMatrix3x4fv(id, entry, count, transpose, value)
    }

    fun uniformMatrix4x3fv(name: String, count: Int, transpose: Boolean, value: Ptr) {
        val entry = resourceManager.locateUniform(name)?.location ?: return
        glProgramUniformMatrix4x3fv(id, entry, count, transpose, value)
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

    abstract class Variants<K> {
        private val map = Object2ObjectOpenHashMap<K, ShaderProgram>()

        abstract fun create(key: K): ShaderProgram

        operator fun get(key: K): ShaderProgram {
            return map.computeIfAbsent(key) { create(key) }
        }
    }
}