package dev.luna5ama.glwrapper

import dev.luna5ama.glwrapper.api.*
import dev.luna5ama.glwrapper.enums.BufferTarget
import dev.luna5ama.glwrapper.enums.GLObjectType
import dev.luna5ama.glwrapper.enums.GLSLDataType
import dev.luna5ama.glwrapper.enums.ShaderStage
import dev.luna5ama.glwrapper.objects.IGLBinding
import dev.luna5ama.glwrapper.objects.IGLObject
import dev.luna5ama.kmogus.MemoryStack
import dev.luna5ama.kmogus.Ptr
import dev.luna5ama.kmogus.memcpy
import it.unimi.dsi.fastutil.ints.Int2ObjectMap
import it.unimi.dsi.fastutil.ints.Int2ObjectMaps
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap
import it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap
import java.util.*

@Suppress("LeakingThis")
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

    private var labelName: String? = null

    private val shaderStages: Set<ShaderStage> = shaders.mapTo(EnumSet.noneOf(ShaderStage::class.java)) {
        it.shaderStage ?: throw IllegalArgumentException("Shader type is not specified $it")
    }

    init {
        fun createShader(source: ShaderSource): Int {
            val id = glCreateShader(source.shaderStage!!.value)

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

    val uniformResource = ResourceInterface.Uniform(this)
    val uniformBlockResource = ResourceInterface.UniformBlock(this)
    val shaderStorageBlockResource = ResourceInterface.ShaderStorageBlock(this)
    val atomicCounterResource = ResourceInterface.AtomicCounter(this)
    val subroutineResource: Map<ShaderStage, ResourceInterface.Subroutine>
    val subroutineUniformResource: Map<ShaderStage, ResourceInterface.SubroutineUniform>

    init {
        subroutineResource = shaderStages.associateWithTo(EnumMap(ShaderStage::class.java)) {
            ResourceInterface.Subroutine(this, it)
        }
        subroutineUniformResource = shaderStages.associateWithTo(EnumMap(ShaderStage::class.java)) {
            ResourceInterface.SubroutineUniform(this, it)
        }
    }

    private val samplerBindings = BindingManager.Samplers(this)
    private val imageBindings = BindingManager.Images(this)
    private val bufferBindings = BindingManager.Buffers(this)
    private val subroutineUniformBindings = BindingManager.SubroutineUniforms(this)

    fun applyBinding(spec: ShaderBindingSpecs) {
        runCatching {
            samplerBindings.apply(spec)
            imageBindings.apply(spec)
            bufferBindings.apply(spec)
            subroutineUniformBindings.apply(spec)
        }.onFailure {
            it.printStackTrace()
        }
    }

    inline fun applyBinding(block: ShaderBindingSpecs.Builder.() -> Unit) {
        applyBinding(ShaderBindingSpecs.Builder().apply(block).build())
    }

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

        operator fun get(index: Int): T = entries[index]

        sealed interface Entry {
            val index: Int
        }

        class Uniform(program: ShaderProgram) : ResourceInterface<Uniform.Entry>() {
            class Entry(
                override val index: Int,
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
                    val propCount = 7
                    val properties = malloc(propCount * 4L).ptr
                    properties.setIntInc(GL_BLOCK_INDEX)
                        .setIntInc(GL_LOCATION)
                        .setIntInc(GL_TYPE)
                        .setIntInc(GL_ARRAY_SIZE)
                        .setIntInc(GL_ARRAY_STRIDE)
                        .setIntInc(GL_MATRIX_STRIDE)
                        .setIntInc(GL_ATOMIC_COUNTER_BUFFER_INDEX)

                    val values = malloc(propCount * 4L).ptr

                    iterateResourceNamedIndex(program.id, GL_UNIFORM) { (i, name) ->
                        glGetProgramResourceiv(
                            program.id,
                            GL_UNIFORM,
                            i,
                            propCount,
                            properties,
                            propCount,
                            Ptr.NULL,
                            values
                        )
                        val blockIndex = values.getInt(0)
                        val location = values.getInt(4)
                        val type = GLSLDataType[values.getInt(8)]
                        val arraySize = values.getInt(12)
                        val arrayStride = values.getInt(16)
                        val matrixStride = values.getInt(20)
                        val atomicCounterBufferIndex = values.getInt(24)

                        entries.put(
                            i, Entry(
                                index = i,
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

        class UniformBlock(program: ShaderProgram) : ResourceInterface<UniformBlock.Entry>() {
            class Entry(
                override val index: Int,
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
                    val propCount = 2
                    val properties = malloc(propCount * 4L).ptr
                    properties.setIntInc(GL_BUFFER_DATA_SIZE)
                        .setIntInc(GL_NUM_ACTIVE_VARIABLES)
                    val values = malloc(propCount * 4L).ptr

                    val temp = malloc(1 * 4L).ptr
                    glGetProgramInterfaceiv(program.id, GL_UNIFORM_BLOCK, GL_MAX_NUM_ACTIVE_VARIABLES, temp)
                    val maxNumActiveVariables = temp.getInt()

                    val activeVariableIndicesPtr = malloc(maxNumActiveVariables * 4L).ptr
                    var bindingIndex = 0

                    iterateResourceNamedIndex(program.id, GL_UNIFORM_BLOCK) { (i, name) ->
                        glGetProgramResourceiv(
                            program.id, GL_UNIFORM_BLOCK, i,
                            propCount, properties,
                            propCount, Ptr.NULL, values
                        )
                        val dataSize = values.getInt(0)
                        val numActiveVariables = values.getInt(4)
                        glGetProgramResourceiv(
                            program.id,
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

                        glUniformBlockBinding(program.id, i, bindingIndex)
                        entries.put(
                            i, Entry(
                                index = i,
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

        class ShaderStorageBlock(program: ShaderProgram) : ResourceInterface<ShaderStorageBlock.Entry>() {
            class Entry(
                override val index: Int,
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
                    val propN = 2
                    val properties = malloc(propN * 4L).ptr
                    properties.setIntInc(GL_BUFFER_DATA_SIZE)
                        .setIntInc(GL_NUM_ACTIVE_VARIABLES)
                    val values = malloc(propN * 4L).ptr

                    val temp = malloc(1 * 4L).ptr
                    glGetProgramInterfaceiv(program.id, GL_SHADER_STORAGE_BLOCK, GL_MAX_NUM_ACTIVE_VARIABLES, temp)
                    val maxNumActiveVariables = temp.getInt()

                    val activeVariableIndicesPtr = calloc(maxNumActiveVariables * 4L).ptr
                    var bindingIndex = 0

                    iterateResourceNamedIndex(program.id, GL_SHADER_STORAGE_BLOCK) { (i, name) ->
                        glGetProgramResourceiv(
                            program.id,
                            GL_SHADER_STORAGE_BLOCK,
                            i,
                            propN,
                            properties,
                            propN,
                            Ptr.NULL,
                            values
                        )
                        val dataSize = values.getInt(0)
                        val numActiveVariables = values.getInt(4)
                        glGetProgramResourceiv(
                            program.id,
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


                        glShaderStorageBlockBinding(program.id, i, bindingIndex)
                        entries.put(
                            i, Entry(
                                index = i,
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

        class AtomicCounter(program: ShaderProgram) : ResourceInterface<AtomicCounter.Entry>() {
            class Entry(
                override val index: Int,
                val bufferBinding: Int,
                val dataSize: Int,
                val numActiveVariables: Int,
                val activeVariableIndices: List<Int>,
            ) : ResourceInterface.Entry

            override val entries: Int2ObjectMap<Entry>

            init {
                val entries = Int2ObjectOpenHashMap<Entry>()
                val propCount = 3
                MemoryStack {
                    val properties = malloc(propCount * 4L).ptr
                    properties.setIntInc(GL_BUFFER_BINDING)
                        .setIntInc(GL_BUFFER_DATA_SIZE)
                        .setIntInc(GL_NUM_ACTIVE_VARIABLES)

                    val values = malloc(propCount * 4L).ptr

                    val temp = malloc(4L).ptr
                    glGetProgramInterfaceiv(program.id, GL_ATOMIC_COUNTER_BUFFER, GL_MAX_NUM_ACTIVE_VARIABLES, temp)
                    val maxNumActiveVariables = temp.getInt() * 4
                    val activeVariableIndicesPtr = calloc(maxNumActiveVariables.toLong()).ptr

                    iterateResourceIndex(program.id, GL_ATOMIC_COUNTER_BUFFER) { i ->
                        glGetProgramResourceiv(
                            program.id, GL_ATOMIC_COUNTER_BUFFER, i,
                            propCount, properties,
                            propCount, Ptr.NULL, values
                        )
                        val bufferBinding = values.getInt(0)
                        val dataSize = values.getInt(4)
                        val numActiveVariables = values.getInt(8)
                        glGetProgramResourceiv(
                            program.id,
                            GL_ATOMIC_COUNTER_BUFFER,
                            i,
                            1,
                            properties,
                            maxNumActiveVariables,
                            Ptr.NULL,
                            activeVariableIndicesPtr
                        )
                        val activeVariableIndices = List(numActiveVariables) { j ->
                            activeVariableIndicesPtr.getInt(j * 4L)
                        }
                        entries.put(
                            i, Entry(
                                index = i,
                                bufferBinding = bufferBinding,
                                dataSize = dataSize,
                                numActiveVariables = numActiveVariables,
                                activeVariableIndices = activeVariableIndices
                            )
                        )
                    }
                }
                this.entries = Int2ObjectMaps.unmodifiable(entries)
            }
        }

        class Subroutine(program: ShaderProgram, stage: ShaderStage) : ResourceInterface<Subroutine.Entry>() {
            class Entry(
                override val index: Int,
                val name: String
            ) : ResourceInterface.Entry

            override val entries: Int2ObjectMap<Entry>

            init {
                val entries = Int2ObjectOpenHashMap<Entry>()
                MemoryStack {
                    iterateResourceNamedIndex(program.id, stage.subroutine.value) { (i, name) ->
                        entries.put(i, Entry(i, name))
                    }
                }
                this.entries = Int2ObjectMaps.unmodifiable(entries)
            }
        }

        class SubroutineUniform(program: ShaderProgram, stage: ShaderStage) :
            ResourceInterface<SubroutineUniform.Entry>() {
            class Entry(
                override val index: Int,
                val name: String,
                val location: Int,
                val compatibleSubroutines: Set<Subroutine.Entry>
            ) : ResourceInterface.Entry

            override val entries: Int2ObjectMap<Entry>

            init {
                val entries = Int2ObjectOpenHashMap<Entry>()
                MemoryStack {
                    val subroutines = program.subroutineResource[stage]
                        ?: error("Subroutine resource not found for $stage")

                    val propCount = 2
                    val properties = malloc(propCount * 4L).ptr
                    properties.setIntInc(GL_LOCATION)
                        .setIntInc(GL_NUM_COMPATIBLE_SUBROUTINES)
                    val propertiesCompatibleSubroutines = malloc(1 * 4L).ptr
                    propertiesCompatibleSubroutines.setInt(GL_COMPATIBLE_SUBROUTINES)
                    val values = malloc(propCount * 4L).ptr

                    val temp = malloc(1 * 4L).ptr
                    glGetProgramInterfaceiv(
                        program.id,
                        stage.subroutineUniform.value,
                        GL_MAX_NUM_COMPATIBLE_SUBROUTINES,
                        temp
                    )
                    val maxNumCompatibleSubroutines = temp.getInt()
                    val compatibleSubroutinesPtr = malloc(maxNumCompatibleSubroutines * 4L).ptr

                    iterateResourceNamedIndex(program.id, stage.subroutineUniform.value) { (i, name) ->
                        glGetProgramResourceiv(
                            program.id,
                            stage.subroutineUniform.value,
                            i,
                            propCount,
                            properties,
                            propCount,
                            Ptr.NULL,
                            values
                        )
                        val location = values.getInt(0)
                        val numCompatibleSubroutines = values.getInt(4)
                        glGetProgramResourceiv(
                            program.id,
                            stage.subroutineUniform.value,
                            i,
                            1,
                            propertiesCompatibleSubroutines,
                            maxNumCompatibleSubroutines,
                            Ptr.NULL,
                            compatibleSubroutinesPtr
                        )
                        val compatibleSubroutines = mutableSetOf<Subroutine.Entry>()
                        for (j in 0 until numCompatibleSubroutines) {
                            val compatibleSubroutineIndex = compatibleSubroutinesPtr.getInt(j * 4L)
                            compatibleSubroutines.add(subroutines[compatibleSubroutineIndex])
                        }
                        entries.put(
                            i, Entry(
                                index = i,
                                name = name,
                                location = location,
                                compatibleSubroutines = compatibleSubroutines
                            )
                        )
                    }
                }
                this.entries = Int2ObjectMaps.unmodifiable(entries)
            }
        }

        private companion object {
            fun iterateResourceNamedIndex(
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

            fun iterateResourceIndex(
                program: Int,
                resourceType: Int,
                block: (Int) -> Unit,
            ) {
                MemoryStack {
                    val singleBuffer = malloc(4L).ptr
                    glGetProgramInterfaceiv(program, resourceType, GL_ACTIVE_RESOURCES, singleBuffer)
                    val numUniforms = singleBuffer.getInt()
                    for (i in 0 until numUniforms) {
                        block(i)
                    }
                }
            }
        }
    }

    sealed class BindingManager {
        internal abstract fun apply(specs: ShaderBindingSpecs)

        data class BindingPoint(val name: String, val index: Int)

        class Samplers(program: ShaderProgram) : BindingManager() {
            private val bindingPoints = mutableListOf<BindingPoint>()

            init {
                var bindingIndex = 0
                for (entry in program.uniformResource.entries.values) {
                    if (entry.type !is GLSLDataType.Opaque.Sampler) continue
                    if (entry.blockIndex != -1) continue
                    glProgramUniform1i(program.id, entry.location, bindingIndex)
                    bindingPoints.add(BindingPoint(entry.name, bindingIndex))
                    bindingIndex++
                }
            }

            override fun apply(specs: ShaderBindingSpecs) {
                MemoryStack {
                    val bindings = specs.samplers
                    val count = bindingPoints.size
                    val textures = malloc(count * 4L).ptr
                    val samplers = malloc(count * 4L).ptr
                    for ((name, index) in bindingPoints) {
                        val binding = bindings[name]
                        require(binding != null) { "Missing binding for sampler unit: $name" }
                        textures.setInt(index * 4L, binding.texture.id)
                        samplers.setInt(index * 4L, binding.sampler?.id ?: 0)
                    }
                    glBindTextures(0, count, textures)
                    glBindSamplers(0, count, samplers)
                }
            }
        }

        class Images(program: ShaderProgram) : BindingManager() {
            private val bindingPoints = mutableListOf<BindingPoint>()

            init {
                var bindingIndex = 0
                for (entry in program.uniformResource.entries.values) {
                    if (entry.type !is GLSLDataType.Opaque.Image) continue
                    glProgramUniform1i(program.id, entry.location, bindingIndex)
                    bindingPoints.add(BindingPoint(entry.name, bindingIndex))
                    bindingIndex++
                }
            }

            override fun apply(specs: ShaderBindingSpecs) {
                MemoryStack {
                    val bindings = specs.images
                    val count = bindingPoints.size
                    val textures = malloc(count * 4L).ptr
                    for ((name, index) in bindingPoints) {
                        val binding = bindings[name]
                        require(binding != null) { "Missing binding for image unit: $name" }
                        textures.setInt(index * 4L, binding.texture.id)
                    }
                    glBindImageTextures(0, count, textures)
                }
            }
        }

        class Buffers(program: ShaderProgram) : BindingManager() {
            private val bindingPointMap = mutableMapOf<BufferTarget.Shader, MutableList<BindingPoint>>()

            init {
                for (entry in program.uniformResource.entries.values) {
                    if (entry.type !is GLSLDataType.Opaque.AtomicCounter) continue
                    bindingPointMap.getOrPut(BufferTarget.AtomicCounter, ::mutableListOf)
                        .add(BindingPoint(entry.name, entry.atomicCounterBufferIndex))
                }
                for (entry in program.shaderStorageBlockResource.entries.values) {
                    bindingPointMap.getOrPut(BufferTarget.ShaderStorage, ::mutableListOf)
                        .add(BindingPoint(entry.name, entry.bindingIndex))
                }
                for (entry in program.uniformBlockResource.entries.values) {
                    bindingPointMap.getOrPut(BufferTarget.Uniform, ::mutableListOf)
                        .add(BindingPoint(entry.name, entry.bindingIndex))
                }
            }

            override fun apply(specs: ShaderBindingSpecs) {
                val bindingMap = specs.buffers
                for ((target, bindingPoints) in bindingPointMap) {
                    val bindings = bindingMap[target]
                    require(bindings != null) { "Missing bindings for targer: $target" }
                    MemoryStack {
                        val targetBindingCount = bindingPoints.size
                        val buffers = malloc(targetBindingCount * 4L).ptr
                        val offsets = malloc(targetBindingCount * 8L).ptr
                        val sizes = malloc(targetBindingCount * 8L).ptr
                        for ((name, index) in bindingPoints) {
                            val binding = bindings[name]
                            require(binding != null) { "Missing binding for $target buffer block: $name" }
                            check(index < targetBindingCount)
                            check(index >= 0)
                            check(binding.buffer.id != 0)
                            check(binding.offset < binding.buffer.size)
                            check(binding.offset + binding.size <= binding.buffer.size)
                            buffers.setInt(index * 4L, binding.buffer.id)
                            offsets.setLong(index * 8L, if (binding.offset == -1L) 0 else binding.offset)
                            sizes.setLong(index * 8L, if (binding.size == -1L) binding.buffer.size else binding.size)
                        }
                        glBindBuffersRange(target.value, 0, targetBindingCount, buffers, offsets, sizes)
                    }
                }
            }
        }

        class SubroutineUniforms(program: ShaderProgram) : BindingManager() {
            private data class BindingPoint(
                val name: String,
                val index: Int,
                val compatibleSubroutines: Map<String, ResourceInterface.Subroutine.Entry>
            )

            private val bindingPoints =
                program.subroutineUniformResource.entries.associateTo(EnumMap(ShaderStage::class.java)) { (stage, resource) ->
                    stage to resource.entries.values.map { entry ->
                        BindingPoint(entry.name, entry.index, entry.compatibleSubroutines.associateBy { it.name })
                    }
                }

            override fun apply(specs: ShaderBindingSpecs) {
                MemoryStack {
                    val bindingMap = specs.subroutines
                    for ((stage, bindingPoints) in bindingPoints) {
                        if (bindingPoints.isEmpty()) return
                        val bindings = bindingMap[stage]
                        require(bindings != null) { "Missing bindings for $stage subroutine" }
                        val count = bindingPoints.size
                        val values = malloc(count * 4L).ptr
                        for ((name, index, compatibleSubroutines) in bindingPoints) {
                            val binding = bindings[name]
                            require(binding != null) { "Missing binding for $stage subroutine uniform: $name" }
                            val compatibleSubroutine = compatibleSubroutines[binding.funcName]
                            require(compatibleSubroutine != null) {
                                "Invalid subroutine function name: ${binding.funcName} for $stage subroutine uniform: $name"
                            }
                            values.setInt(index * 4L, compatibleSubroutine.index)
                        }
                    }
                }
            }
        }
    }
}