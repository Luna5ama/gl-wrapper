package dev.luna5ama.glwrapper

import dev.luna5ama.glwrapper.api.*
import dev.luna5ama.glwrapper.enums.BufferTarget
import dev.luna5ama.glwrapper.enums.GLObjectType
import dev.luna5ama.glwrapper.enums.GLSLDataType
import dev.luna5ama.glwrapper.enums.ShaderType
import dev.luna5ama.glwrapper.objects.IGLBinding
import dev.luna5ama.glwrapper.objects.IGLObject
import dev.luna5ama.kmogus.MemoryStack
import dev.luna5ama.kmogus.Ptr
import dev.luna5ama.kmogus.memcpy
import it.unimi.dsi.fastutil.ints.Int2ObjectMap
import it.unimi.dsi.fastutil.ints.Int2ObjectMaps
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap
import it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap
import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap
import java.util.*

open class ShaderProgram(
    vararg shaders: ShaderSource,
) : IGLObject, IGLBinding {
    final override val id: Int
    override val type: GLObjectType
        get() = GLObjectType.Program

    private var uniformLookUpCacheStr: String? = null
    private var uniformLookUpCache = -1
    private val uniformLocations = Object2IntOpenHashMap<String>().apply {
        defaultReturnValue(-1)
    }
    private val subroutineIndices = EnumMap<ShaderType, Object2IntOpenHashMap<String>>(ShaderType::class.java)

    private var labelName: String? = null

    init {
        fun createShader(source: ShaderSource): Int {
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

    val uniformResource = ResourceInterface.Uniform(id)
    val uniformBlockResource = ResourceInterface.UniformBlock(id)
    val shaderStorageBlockResource = ResourceInterface.ShaderStorageBlock(id)

    val samplerBindings = SamplerBindings()
    val imageBindings = ImageBindings()
    val bufferBindings = BufferBindings()

    override fun label(label: String) {
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

    private fun locateSubroutineIndex(shaderType: ShaderType, name: String): Int {
        val map = subroutineIndices.getOrPut(shaderType) {
            Object2IntOpenHashMap<String>().apply {
                defaultReturnValue(-1)
            }
        }
        var index = map.getInt(name)
        if (index == -1) {
            index = glGetSubroutineIndex(id, shaderType.value, name)
            map.put(name, index)
        }

        return index
    }

    fun uniformSubroutines(shaderType: ShaderType, vararg subroutineNames: String) {
        MemoryStack {
            val arr = malloc(subroutineNames.size * 4L)
            val ptr = arr.ptr
            for (i in subroutineNames.indices) {
                ptr.setInt(i * 4L, locateSubroutineIndex(shaderType, subroutineNames[i]))
            }
            glUniformSubroutinesuiv(shaderType.value, subroutineNames.size, ptr)
        }
    }

    fun uniform1i(name: String, value: Int) {
        glProgramUniform1i(id, locateUniform(name), value)
    }

    fun uniform1iv(name: String, count: Int, value: Ptr) {
        glProgramUniform1iv(id, locateUniform(name), count, value)
    }

    fun uniform2i(name: String, value1: Int, value2: Int) {
        glProgramUniform2i(id, locateUniform(name), value1, value2)
    }

    fun uniform2iv(name: String, count: Int, value: Ptr) {
        glProgramUniform2iv(id, locateUniform(name), count, value)
    }

    fun uniform3i(name: String, value1: Int, value2: Int, value3: Int) {
        glProgramUniform3i(id, locateUniform(name), value1, value2, value3)
    }

    fun uniform3iv(name: String, count: Int, value: Ptr) {
        glProgramUniform3iv(id, locateUniform(name), count, value)
    }

    fun uniform4i(name: String, value1: Int, value2: Int, value3: Int, value4: Int) {
        glProgramUniform4i(id, locateUniform(name), value1, value2, value3, value4)
    }

    fun uniform4iv(name: String, count: Int, value: Ptr) {
        glProgramUniform4iv(id, locateUniform(name), count, value)
    }

    fun uniform1ui(name: String, value: Int) {
        glProgramUniform1ui(id, locateUniform(name), value)
    }

    fun uniform1uiv(name: String, count: Int, value: Ptr) {
        glProgramUniform1uiv(id, locateUniform(name), count, value)
    }

    fun uniform2ui(name: String, value1: Int, value2: Int) {
        glProgramUniform2ui(id, locateUniform(name), value1, value2)
    }

    fun uniform2uiv(name: String, count: Int, value: Ptr) {
        glProgramUniform2uiv(id, locateUniform(name), count, value)
    }

    fun uniform3ui(name: String, value1: Int, value2: Int, value3: Int) {
        glProgramUniform3ui(id, locateUniform(name), value1, value2, value3)
    }

    fun uniform3uiv(name: String, count: Int, value: Ptr) {
        glProgramUniform3uiv(id, locateUniform(name), count, value)
    }

    fun uniform4ui(name: String, value1: Int, value2: Int, value3: Int, value4: Int) {
        glProgramUniform4ui(id, locateUniform(name), value1, value2, value3, value4)
    }

    fun uniform4uiv(name: String, count: Int, value: Ptr) {
        glProgramUniform4uiv(id, locateUniform(name), count, value)
    }

    fun uniformHandle(name: String, value: Long) {
        glProgramUniformHandleui64ARB(id, locateUniform(name), value)
    }

    fun uniformHandle(name: String, count: Int, value: Ptr) {
        glProgramUniformHandleui64vARB(id, locateUniform(name), count, value)
    }

    fun uniform1f(name: String, value: Float) {
        glProgramUniform1f(id, locateUniform(name), value)
    }

    fun uniform1fv(name: String, count: Int, value: Ptr) {
        glProgramUniform1fv(id, locateUniform(name), count, value)
    }

    fun uniform2f(name: String, value1: Float, value2: Float) {
        glProgramUniform2f(id, locateUniform(name), value1, value2)
    }

    fun uniform2fv(name: String, count: Int, value: Ptr) {
        glProgramUniform2fv(id, locateUniform(name), count, value)
    }

    fun uniform3f(name: String, value1: Float, value2: Float, value3: Float) {
        glProgramUniform3f(id, locateUniform(name), value1, value2, value3)
    }

    fun uniform3fv(name: String, count: Int, value: Ptr) {
        glProgramUniform3fv(id, locateUniform(name), count, value)
    }

    fun uniform4f(name: String, value1: Float, value2: Float, value3: Float, value4: Float) {
        glProgramUniform4f(id, locateUniform(name), value1, value2, value3, value4)
    }

    fun uniform4fv(name: String, count: Int, value: Ptr) {
        glProgramUniform4fv(id, locateUniform(name), count, value)
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

    sealed class ResourceInterface<T : ResourceInterface.Entry> {
        abstract val entries: Int2ObjectMap<T>

        sealed interface Entry

        class Uniform(program: Int) : ResourceInterface<Uniform.Entry>() {
            class Entry(
                val name: String,
                val location: Int,
                val blockIndex: Int,
                val type: GLSLDataType,
                val arraySize: Int,
                val arrayStride: Int,
                val matrixStride: Int,
                val atomicCounterBufferIndex: Int,
            ) : ResourceInterface.Entry

            override val entries: Int2ObjectMap<Entry>

            init {
                val entries = Int2ObjectOpenHashMap<Entry>()
                MemoryStack {
                    val properties = malloc(7 * 4L).ptr
                    properties.setIntInc(GL_BLOCK_INDEX)
                        .setIntInc(GL_LOCATION)
                        .setIntInc(GL_TYPE)
                        .setIntInc(GL_ARRAY_SIZE)
                        .setIntInc(GL_ARRAY_STRIDE)
                        .setIntInc(GL_MATRIX_STRIDE)
                        .setIntInc(GL_ATOMIC_COUNTER_BUFFER_INDEX)

                    val values = malloc(7 * 4L).ptr

                    iterateResourceIndex(program, GL_UNIFORM) { (i, name) ->
                        glGetProgramResourceiv(program, GL_UNIFORM, i, 7, properties, 7, Ptr.NULL, values)
                        val blockIndex = values.getInt(0)
                        if (blockIndex != -1) return@iterateResourceIndex
                        val location = values.getInt(4)
                        val type = GLSLDataType[values.getInt(8)]
                        val arraySize = values.getInt(12)
                        val arrayStride = values.getInt(16)
                        val matrixStride = values.getInt(20)
                        val atomicCounterBufferIndex = values.getInt(24)

                        entries.put(
                            i, Entry(
                                name = name,
                                location = location,
                                blockIndex = blockIndex,
                                type = type,
                                arraySize = arraySize,
                                arrayStride = arrayStride,
                                matrixStride = matrixStride,
                                atomicCounterBufferIndex = atomicCounterBufferIndex
                            )
                        )
                    }
                }
                this.entries = Int2ObjectMaps.unmodifiable(entries)
            }
        }

        class UniformBlock(program: Int) : ResourceInterface<UniformBlock.Entry>() {
            class Entry(
                val name: String,
                val bindingIndex: Int,
                val dataSize: Int,
                val numActiveVariables: Int,
                val activeVariableIndices: List<Int>,
            ) : ResourceInterface.Entry

            override val entries: Int2ObjectMap<Entry>

            init {
                val entries = Int2ObjectOpenHashMap<Entry>()
                MemoryStack {
                    val properties = malloc(2 * 4L).ptr
                    properties.setIntInc(GL_BUFFER_DATA_SIZE)
                        .setIntInc(GL_NUM_ACTIVE_VARIABLES)
                    val values = malloc(2 * 4L).ptr

                    val temp = malloc(1 * 4L).ptr
                    glGetProgramInterfaceiv(program, GL_UNIFORM_BLOCK, GL_MAX_NUM_ACTIVE_VARIABLES, temp)
                    val maxNumActiveVariables = temp.getInt()

                    val activeVariableIndicesPtr = malloc(maxNumActiveVariables * 4L).ptr
                    var bindingIndex = 0

                    iterateResourceIndex(program, GL_UNIFORM_BLOCK) { (i, name) ->
                        glGetProgramResourceiv(program, GL_UNIFORM_BLOCK, i, 4, properties, 4, Ptr.NULL, values)
                        val dataSize = values.getInt(0)
                        val numActiveVariables = values.getInt(4)
                        glGetProgramResourceiv(
                            program,
                            GL_UNIFORM_BLOCK,
                            i,
                            1,
                            properties,
                            1,
                            Ptr.NULL,
                            activeVariableIndicesPtr
                        )

                        val activeVariableIndices = List(numActiveVariables) { j ->
                            activeVariableIndicesPtr.getInt(j * 4L)
                        }

                        glUniformBlockBinding(program, i, bindingIndex)
                        entries.put(
                            i, Entry(
                                name = name,
                                bindingIndex = bindingIndex,
                                dataSize = dataSize,
                                numActiveVariables = numActiveVariables,
                                activeVariableIndices = activeVariableIndices
                            )
                        )
                        bindingIndex++
                    }
                }
                this.entries = Int2ObjectMaps.unmodifiable(entries)
            }
        }

        class ShaderStorageBlock(program: Int) : ResourceInterface<ShaderStorageBlock.Entry>() {
            class Entry(
                val name: String,
                val bindingIndex: Int,
                val dataSize: Int,
                val numActiveVariables: Int,
                val activeVariableIndices: List<Int>,
            ) : ResourceInterface.Entry

            override val entries: Int2ObjectMap<Entry>

            init {
                val entries = Int2ObjectOpenHashMap<Entry>()
                MemoryStack {
                    val properties = malloc(2 * 4L).ptr
                    properties.setIntInc(GL_BUFFER_DATA_SIZE)
                        .setIntInc(GL_NUM_ACTIVE_VARIABLES)
                    val values = malloc(2 * 4L).ptr

                    val temp = malloc(1 * 4L).ptr
                    glGetProgramInterfaceiv(program, GL_SHADER_STORAGE_BLOCK, GL_MAX_NUM_ACTIVE_VARIABLES, temp)
                    val maxNumActiveVariables = temp.getInt()

                    val activeVariableIndicesPtr = malloc(maxNumActiveVariables * 4L).ptr
                    var bindingIndex = 0

                    iterateResourceIndex(program, GL_SHADER_STORAGE_BLOCK) { (i, name) ->
                        glGetProgramResourceiv(program, GL_SHADER_STORAGE_BLOCK, i, 4, properties, 4, Ptr.NULL, values)
                        val dataSize = values.getInt(0)
                        val numActiveVariables = values.getInt(4)
                        glGetProgramResourceiv(
                            program,
                            GL_SHADER_STORAGE_BLOCK,
                            i,
                            1,
                            properties,
                            1,
                            Ptr.NULL,
                            activeVariableIndicesPtr
                        )

                        val activeVariableIndices = List(numActiveVariables) { j ->
                            activeVariableIndicesPtr.getInt(j * 4L)
                        }

                        glShaderStorageBlockBinding(program, i, bindingIndex)
                        entries.put(
                            i, Entry(
                                name = name,
                                bindingIndex = bindingIndex,
                                dataSize = dataSize,
                                numActiveVariables = numActiveVariables,
                                activeVariableIndices = activeVariableIndices
                            )
                        )
                        bindingIndex++
                    }
                }
                this.entries = Int2ObjectMaps.unmodifiable(entries)
            }
        }

        private companion object {
            fun iterateResourceIndex(
                program: Int,
                resourceType: Int,
                block: (IndexedValue<String>) -> Unit,
            ) {
                MemoryStack {
                    val properties = malloc(1 * 4L).ptr
                    val values = malloc(1 * 4L).ptr

                    properties.setIntInc(GL_NAME_LENGTH)
                    val singleBuffer = malloc(4L).ptr
                    glGetProgramInterfaceiv(program, resourceType, GL_ACTIVE_RESOURCES, singleBuffer)
                    val numUniforms = singleBuffer.getInt()
                    glGetProgramInterfaceiv(program, resourceType, GL_MAX_NAME_LENGTH, singleBuffer)
                    val maxNameLength = singleBuffer.getInt()
                    val nameBuffer = malloc(maxNameLength.toLong()).ptr
                    val byteArray = ByteArray(maxNameLength)

                    for (i in 0 until numUniforms) {
                        glGetProgramResourceiv(program, resourceType, i, 1, properties, 1, Ptr.NULL, values)
                        val nameLength = values.getInt(0)

                        glGetProgramResourceName(program, resourceType, i, maxNameLength, Ptr.NULL, nameBuffer)
                        memcpy(nameBuffer, 0L, byteArray, 0L, nameLength.toLong())
                        val name = String(byteArray, 0, nameLength - 1)

                        block(IndexedValue(i, name))
                    }
                }
            }
        }
    }

    sealed interface BindingManager<T : ShaderBindingSpec> {
        fun apply(spec: T)
    }

    inner class SamplerBindings : BindingManager<ShaderBindingSpec.Sampler> {
        private val bindingIndices = Object2IntOpenHashMap<String>().apply {
            defaultReturnValue(-1)
        }

        init {
            var bindingIndex = 0
            for (entry in uniformResource.entries.values) {
                if (entry.type !is GLSLDataType.Opaque.Sampler) continue
                if (entry.blockIndex != -1) continue
                glProgramUniform1i(id, entry.location, bindingIndex)
                bindingIndices.put(entry.name, bindingIndex)
                bindingIndex++
            }
        }

        override fun apply(spec: ShaderBindingSpec.Sampler) {
            require(spec.bindings.size == bindingIndices.size) { "Sampler binding size mismatch" }
            MemoryStack {
                val textures = malloc(spec.bindings.size * 4L).ptr
                val samplers = malloc(spec.bindings.size * 4L).ptr
                for (binding in spec.bindings.values) {
                    val index = bindingIndices.getInt(binding.name)
                    require(index != -1) { "Sampler binding not found: ${binding.name}" }
                    textures.setInt(index * 4L, binding.texture.id)
                    samplers.setInt(index * 4L, binding.sampler.id)
                }
                glBindTextures(0, spec.bindings.size, textures)
                glBindSamplers(0, spec.bindings.size, samplers)
            }
        }
    }

    inner class ImageBindings : BindingManager<ShaderBindingSpec.Image> {
        private val bindingIndices = Object2IntOpenHashMap<String>().apply {
            defaultReturnValue(-1)
        }

        init {
            var bindingIndex = 0
            for (entry in uniformResource.entries.values) {
                if (entry.type !is GLSLDataType.Opaque.Image) continue
                glProgramUniform1i(id, entry.location, bindingIndex)
                bindingIndices.put(entry.name, bindingIndex)
                bindingIndex++
            }
        }

        override fun apply(spec: ShaderBindingSpec.Image) {
            require(spec.bindings.size == bindingIndices.size) { "Image binding size mismatch" }
            MemoryStack {
                val textures = malloc(spec.bindings.size * 4L).ptr
                for (binding in spec.bindings.values) {
                    val index = bindingIndices.getInt(binding.name)
                    require(index != -1) { "Image binding not found: ${binding.name}" }
                    textures.setInt(index * 4L, binding.texture.id)
                }
                glBindImageTextures(0, spec.bindings.size, textures)
                for (binding in spec.bindings.values) {
                    if (binding.format == 0) continue
                    glBindImageTexture(
                        bindingIndices.getInt(binding.name),
                        binding.texture.id,
                        binding.level,
                        binding.layered == 1,
                        binding.layer,
                        binding.access,
                        binding.format
                    )
                }
            }
        }
    }

    inner class BufferBindings : BindingManager<ShaderBindingSpec.Buffer> {
        private val bindingIndicesMap = Object2ObjectOpenHashMap<BufferTarget.Shader, Object2IntOpenHashMap<String>>()

        init {
            for (entry in uniformResource.entries.values) {
                if (entry.type !is GLSLDataType.Opaque.AtomicCounter) continue
                val map = bindingIndicesMap.computeIfAbsent(BufferTarget.AtomicCounter) {
                    Object2IntOpenHashMap<String>().apply {
                        defaultReturnValue(-1)
                    }
                }
                map.put(entry.name, entry.atomicCounterBufferIndex)
            }
            for (entry in shaderStorageBlockResource.entries.values) {
                val map = bindingIndicesMap.computeIfAbsent(BufferTarget.ShaderStorage) {
                    Object2IntOpenHashMap<String>().apply {
                        defaultReturnValue(-1)
                    }
                }
                map.put(entry.name, entry.bindingIndex)
            }
            for (entry in uniformBlockResource.entries.values) {
                val map = bindingIndicesMap.computeIfAbsent(BufferTarget.Uniform) {
                    Object2IntOpenHashMap<String>().apply {
                        defaultReturnValue(-1)
                    }
                }
                map.put(entry.name, entry.bindingIndex)
            }
        }

        override fun apply(spec: ShaderBindingSpec.Buffer) {
            require(spec.bindings.size == bindingIndicesMap.size) { "Sampler binding size mismatch" }
            for ((target, bindings) in spec.bindings) {
                MemoryStack {
                    val bindingIndices = bindingIndicesMap[target] ?: throw IllegalArgumentException("Buffer binding target not found: $target")
                    val buffers = malloc(bindings.size * 4L).ptr
                    val offsets = malloc(bindings.size * 4L).ptr
                    val sizes = malloc(bindings.size * 4L).ptr
                    for (binding in bindings.values) {
                        val index = bindingIndices.getInt(binding.name)
                        require(index != -1) { "Buffer binding not found: ${binding.name}" }
                        buffers.setInt(index * 4L, binding.buffer.id)
                        offsets.setInt(index * 4L, binding.offset)
                        sizes.setInt(index * 4L, binding.size)
                    }
                    glBindBuffersRange(target.value, 0, bindings.size, buffers, offsets, sizes)
                }
            }
        }
    }
}