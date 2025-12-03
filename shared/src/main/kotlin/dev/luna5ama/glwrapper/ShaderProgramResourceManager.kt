package dev.luna5ama.glwrapper

import dev.luna5ama.glwrapper.base.GL_ACTIVE_RESOURCES
import dev.luna5ama.glwrapper.base.GL_ARRAY_SIZE
import dev.luna5ama.glwrapper.base.GL_ARRAY_STRIDE
import dev.luna5ama.glwrapper.base.GL_BLOCK_INDEX
import dev.luna5ama.glwrapper.base.GL_BUFFER_DATA_SIZE
import dev.luna5ama.glwrapper.base.GL_LOCATION
import dev.luna5ama.glwrapper.base.GL_MATRIX_STRIDE
import dev.luna5ama.glwrapper.base.GL_MAX_NAME_LENGTH
import dev.luna5ama.glwrapper.base.GL_MAX_NUM_ACTIVE_VARIABLES
import dev.luna5ama.glwrapper.base.GL_NAME_LENGTH
import dev.luna5ama.glwrapper.base.GL_NUM_ACTIVE_VARIABLES
import dev.luna5ama.glwrapper.base.GL_SHADER_STORAGE_BLOCK
import dev.luna5ama.glwrapper.base.GL_TYPE
import dev.luna5ama.glwrapper.base.GL_UNIFORM
import dev.luna5ama.glwrapper.base.GL_UNIFORM_BLOCK
import dev.luna5ama.glwrapper.base.glBindBuffersRange
import dev.luna5ama.glwrapper.base.glBindImageTextures
import dev.luna5ama.glwrapper.base.glBindSamplers
import dev.luna5ama.glwrapper.base.glBindTextures
import dev.luna5ama.glwrapper.base.glGetProgramInterfaceiv
import dev.luna5ama.glwrapper.base.glGetProgramResourceName
import dev.luna5ama.glwrapper.base.glGetProgramResourceiv
import dev.luna5ama.glwrapper.base.glProgramUniform1i
import dev.luna5ama.glwrapper.base.glShaderStorageBlockBinding
import dev.luna5ama.glwrapper.base.glUniformBlockBinding
import dev.luna5ama.glwrapper.enums.BufferTarget
import dev.luna5ama.glwrapper.enums.GLSLDataType
import dev.luna5ama.glwrapper.enums.ShaderStage
import dev.luna5ama.kmogus.MemoryStack
import dev.luna5ama.kmogus.Ptr
import dev.luna5ama.kmogus.memcpy
import it.unimi.dsi.fastutil.ints.Int2ObjectMap
import it.unimi.dsi.fastutil.ints.Int2ObjectMaps
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap
import it.unimi.dsi.fastutil.objects.Object2ObjectMap
import it.unimi.dsi.fastutil.objects.Object2ObjectMaps
import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap
import kotlin.collections.iterator

class ShaderProgramResourceManager(val programID: Int) {
    private var uniformLookUpCacheName: String? = null
    private var uniformLookUpCache: ResourceInterface.Uniform.Entry? = null

    fun locateUniform(name: String): ResourceInterface.Uniform.Entry? {
        if (uniformLookUpCacheName == name) {
            return uniformLookUpCache
        }

        val entry = uniformResource.nameToEntryMap[name]
        require(!Config.checks || entry != null) { "Uniform not found: $name" }

        uniformLookUpCacheName = name
        uniformLookUpCache = entry

        return entry
    }

    val uniformResource = ResourceInterface.Uniform(this)
    val uniformBlockResource = ResourceInterface.UniformBlock(this)
    val shaderStorageBlockResource = ResourceInterface.ShaderStorageBlock(this)

    val samplerBindings = BindingManager.Samplers(this)
    val imageBindings = BindingManager.Images(this)
    val bufferBindings = BindingManager.Buffers(this)

    sealed class ResourceInterface<T : ResourceInterface.Entry> {
        abstract val entries: Int2ObjectMap<T>

        operator fun get(index: Int): T = entries[index]

        sealed interface Entry {
            val index: Int
        }

        class Uniform internal constructor(manager: ShaderProgramResourceManager) : ResourceInterface<Uniform.Entry>() {
            data class Entry(
                override val index: Int,
                val name: String,
                val location: Int,
                val blockIndex: Int,
                val type: GLSLDataType,
                val arraySize: Int,
                val arrayStride: Int,
                val matrixStride: Int,
            ) : ResourceInterface.Entry

            override val entries: Int2ObjectMap<Entry>
            val nameToEntryMap: Object2ObjectMap<String, Entry>

            init {
                val entries = Int2ObjectOpenHashMap<Entry>()
                val nameToEntryMap = Object2ObjectOpenHashMap<String, Entry>()
                MemoryStack.Companion {
                    val propCount = 6
                    val properties = malloc(propCount * 4L).ptr
                    properties.setIntInc(GL_BLOCK_INDEX)
                        .setIntInc(GL_LOCATION)
                        .setIntInc(GL_TYPE)
                        .setIntInc(GL_ARRAY_SIZE)
                        .setIntInc(GL_ARRAY_STRIDE)
                        .setIntInc(GL_MATRIX_STRIDE)

                    val values = malloc(propCount * 4L).ptr

                    iterateResourceNamedIndex(manager.programID, GL_UNIFORM) { (i, name) ->
                        glGetProgramResourceiv(
                            manager.programID,
                            GL_UNIFORM,
                            i,
                            propCount,
                            properties,
                            propCount,
                            Ptr.Companion.NULL,
                            values
                        )
                        val blockIndex = values.getInt(0)
                        val location = values.getInt(4)
                        val type = GLSLDataType.Companion[values.getInt(8)]
                        val arraySize = values.getInt(12)
                        val arrayStride = values.getInt(16)
                        val matrixStride = values.getInt(20)

                        val entry = Entry(
                            index = i,
                            name = name,
                            location = location,
                            blockIndex = blockIndex,
                            type = type,
                            arraySize = arraySize,
                            arrayStride = arrayStride,
                            matrixStride = matrixStride
                        )
                        nameToEntryMap.put(name, entry)
                        entries.put(i, entry)
                    }
                }
                this.entries = Int2ObjectMaps.unmodifiable(entries)
                this.nameToEntryMap = Object2ObjectMaps.unmodifiable(nameToEntryMap)
            }
        }

        class UniformBlock internal constructor(manager: ShaderProgramResourceManager) : ResourceInterface<UniformBlock.Entry>() {
            data class Entry(
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
                MemoryStack.Companion {
                    val propCount = 2
                    val properties = malloc(propCount * 4L).ptr
                    properties.setIntInc(GL_BUFFER_DATA_SIZE)
                        .setIntInc(GL_NUM_ACTIVE_VARIABLES)
                    val values = malloc(propCount * 4L).ptr

                    val temp = malloc(1 * 4L).ptr
                    glGetProgramInterfaceiv(manager.programID, GL_UNIFORM_BLOCK, GL_MAX_NUM_ACTIVE_VARIABLES, temp)
                    val maxNumActiveVariables = temp.getInt()

                    val activeVariableIndicesPtr = malloc(maxNumActiveVariables * 4L).ptr
                    var bindingIndex = 0

                    iterateResourceNamedIndex(manager.programID, GL_UNIFORM_BLOCK) { (i, name) ->
                        glGetProgramResourceiv(
                            manager.programID, GL_UNIFORM_BLOCK, i,
                            propCount, properties,
                            propCount, Ptr.Companion.NULL, values
                        )
                        val dataSize = values.getInt(0)
                        val numActiveVariables = values.getInt(4)
                        glGetProgramResourceiv(
                            manager.programID,
                            GL_UNIFORM_BLOCK,
                            i,
                            1,
                            properties,
                            1,
                            Ptr.Companion.NULL,
                            activeVariableIndicesPtr
                        )

                        val activeVariableIndices = List(numActiveVariables) { j ->
                            activeVariableIndicesPtr.getInt(j * 4L)
                        }

                        glUniformBlockBinding(manager.programID, i, bindingIndex)
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

        class ShaderStorageBlock internal constructor(manager: ShaderProgramResourceManager) :
            ResourceInterface<ShaderStorageBlock.Entry>() {
            data class Entry(
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
                MemoryStack.Companion {
                    val propN = 2
                    val properties = malloc(propN * 4L).ptr
                    properties.setIntInc(GL_BUFFER_DATA_SIZE)
                        .setIntInc(GL_NUM_ACTIVE_VARIABLES)
                    val values = malloc(propN * 4L).ptr

                    val temp = malloc(1 * 4L).ptr
                    glGetProgramInterfaceiv(
                        manager.programID,
                        GL_SHADER_STORAGE_BLOCK,
                        GL_MAX_NUM_ACTIVE_VARIABLES,
                        temp
                    )
                    val maxNumActiveVariables = temp.getInt()

                    val activeVariableIndicesPtr = calloc(maxNumActiveVariables * 4L).ptr
                    var bindingIndex = 0

                    iterateResourceNamedIndex(manager.programID, GL_SHADER_STORAGE_BLOCK) { (i, name) ->
                        glGetProgramResourceiv(
                            manager.programID,
                            GL_SHADER_STORAGE_BLOCK,
                            i,
                            propN,
                            properties,
                            propN,
                            Ptr.Companion.NULL,
                            values
                        )
                        val dataSize = values.getInt(0)
                        val numActiveVariables = values.getInt(4)
                        glGetProgramResourceiv(
                            manager.programID,
                            GL_SHADER_STORAGE_BLOCK,
                            i,
                            1,
                            properties,
                            1,
                            Ptr.Companion.NULL,
                            activeVariableIndicesPtr
                        )

                        val activeVariableIndices = List(numActiveVariables) { j ->
                            activeVariableIndicesPtr.getInt(j * 4L)
                        }


                        glShaderStorageBlockBinding(manager.programID, i, bindingIndex)
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

        class Subroutine internal constructor(manager: ShaderProgramResourceManager, stage: ShaderStage) :
            ResourceInterface<Subroutine.Entry>() {
            data class Entry(
                override val index: Int,
                val name: String
            ) : ResourceInterface.Entry

            override val entries: Int2ObjectMap<Entry>

            init {
                val entries = Int2ObjectOpenHashMap<Entry>()
                MemoryStack.Companion {
                    iterateResourceNamedIndex(manager.programID, stage.subroutine.value) { (i, name) ->
                        entries.put(i, Entry(i, name))
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
                MemoryStack.Companion {
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
                        glGetProgramResourceiv(program, resourceType, i, 1, properties, 1, Ptr.Companion.NULL, values)
                        val nameLength = values.getInt(0)

                        glGetProgramResourceName(
                            program,
                            resourceType,
                            i,
                            maxNameLength,
                            Ptr.Companion.NULL,
                            nameBuffer
                        )
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
                MemoryStack.Companion {
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

        class Samplers internal constructor(manager: ShaderProgramResourceManager) : BindingManager() {
            private val bindingPoints = mutableListOf<BindingPoint>()

            init {
                var bindingIndex = 0
                for (entry in manager.uniformResource.entries.values) {
                    if (entry.type !is GLSLDataType.Opaque.Sampler) continue
                    if (entry.blockIndex != -1) continue
                    glProgramUniform1i(manager.programID, entry.location, bindingIndex)
                    bindingPoints.add(BindingPoint(entry.name, bindingIndex))
                    bindingIndex++
                }
            }

            override fun apply(specs: ShaderBindingSpecs) {
                MemoryStack.Companion {
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

        class Images internal constructor(manager: ShaderProgramResourceManager) : BindingManager() {
            private val bindingPoints = mutableListOf<BindingPoint>()

            init {
                var bindingIndex = 0
                for (entry in manager.uniformResource.entries.values) {
                    if (entry.type !is GLSLDataType.Opaque.Image) continue
                    glProgramUniform1i(manager.programID, entry.location, bindingIndex)
                    bindingPoints.add(BindingPoint(entry.name, bindingIndex))
                    bindingIndex++
                }
            }

            override fun apply(specs: ShaderBindingSpecs) {
                MemoryStack.Companion {
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

        class Buffers internal constructor(manager: ShaderProgramResourceManager) : BindingManager() {
            private val bindingPointMap = mutableMapOf<BufferTarget.Shader, MutableList<BindingPoint>>()

            init {
                for (entry in manager.shaderStorageBlockResource.entries.values) {
                    bindingPointMap.getOrPut(BufferTarget.ShaderStorage, ::mutableListOf)
                        .add(BindingPoint(entry.name, entry.bindingIndex))
                }
                for (entry in manager.uniformBlockResource.entries.values) {
                    bindingPointMap.getOrPut(BufferTarget.Uniform, ::mutableListOf)
                        .add(BindingPoint(entry.name, entry.bindingIndex))
                }
            }

            override fun apply(specs: ShaderBindingSpecs) {
                val bindingMap = specs.buffers
                for ((target, bindingPoints) in bindingPointMap) {
                    val bindings = bindingMap[target]
                    require(bindings != null) { "Missing bindings for targer: $target" }
                    MemoryStack.Companion {
                        val targetBindingCount = bindingPoints.size
                        val buffers = malloc(targetBindingCount * 4L).ptr
                        val offsets = malloc(targetBindingCount * 8L).ptr
                        val sizes = malloc(targetBindingCount * 8L).ptr
                        for ((name, index) in bindingPoints) {
                            val binding = bindings[name]
                            require(binding != null) { "Missing binding for $target buffer block: $name" }
                            check(index < targetBindingCount)
                            check(index >= 0)
                            val bufferView = binding.bufferView
                            val bindingSize = if (bufferView.viewSize == -1L) {
                                bufferView.viewBuffer.size - bufferView.viewOffset
                            } else {
                                bufferView.viewSize
                            }
                            check(bufferView.viewBuffer.id != 0)
                            check(bufferView.viewOffset < bufferView.viewBuffer.size)
                            check(bufferView.viewOffset + bindingSize <= bufferView.viewBuffer.size)
                            buffers.setInt(index * 4L, bufferView.viewBuffer.id)
                            offsets.setLong(index * 8L, bufferView.viewOffset)
                            sizes.setLong(index * 8L, bindingSize)
                        }
                        glBindBuffersRange(target.value, 0, targetBindingCount, buffers, offsets, sizes)
                    }
                }
            }
        }
    }
}