package dev.luna5ama.glwrapper

import dev.luna5ama.glwrapper.base.*
import dev.luna5ama.glwrapper.enums.BufferTarget
import dev.luna5ama.glwrapper.enums.GLSLDataType
import dev.luna5ama.kmogus.MemoryStack
import java.util.*

internal class ShaderProgramResourceBindingManager(resourceManager: ShaderProgramResourceManager) {
    val samplerBindings = BindingManager.Samplers(resourceManager)
    val imageBindings = BindingManager.Images(resourceManager)
    val bufferBindings = BindingManager.Buffers(resourceManager)

    sealed class BindingManager {
        internal abstract fun apply(specs: ShaderBindingSpecs)

        data class BindingPoint(val name: String, val index: Int)

        class Samplers internal constructor(manager: ShaderProgramResourceManager) : BindingManager() {
            private val bindingPoints = mutableListOf<BindingPoint>()

            init {
                val samplerUniforms = manager.uniformResource.entries.values.asSequence()
                    .filter { it.type is GLSLDataType.Opaque.Sampler }
                    .filter { it.blockIndex == -1 }
                    .toList()

                val usedTextureUnits = BitSet()
                MemoryStack {
                    val temp = malloc(4L)
                    val tempPtr = temp.ptr
                    samplerUniforms.filter {
                        glGetUniformiv(manager.programID, it.location, tempPtr)
                        val bindingIndex = tempPtr.getInt()
                        if (bindingIndex >= 0 && !usedTextureUnits.get(bindingIndex)) {
                            usedTextureUnits.set(bindingIndex, true)
                            bindingPoints.add(BindingPoint(it.name, bindingIndex))
                            false
                        } else {
                            true
                        }
                    }.forEach {
                        val bindingIndex = usedTextureUnits.nextClearBit(0)
                        usedTextureUnits.set(bindingIndex, true)
                        glProgramUniform1i(manager.programID, it.location, bindingIndex)
                        bindingPoints.add(BindingPoint(it.name, bindingIndex))
                    }
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

        class Images internal constructor(manager: ShaderProgramResourceManager) : BindingManager() {
            private val bindingPoints = mutableListOf<BindingPoint>()

            init {
                val imageUniforms = manager.uniformResource.entries.values.asSequence()
                    .filter { it.type is GLSLDataType.Opaque.Image }
                    .toList()

                val usedImageUnits = BitSet()
                MemoryStack {
                    val temp = malloc(4L)
                    val tempPtr = temp.ptr
                    imageUniforms.filter {
                        glGetUniformiv(manager.programID, it.location, tempPtr)
                        val bindingIndex = tempPtr.getInt()
                        if (bindingIndex >= 0 && !usedImageUnits.get(bindingIndex)) {
                            usedImageUnits.set(bindingIndex, true)
                            bindingPoints.add(BindingPoint(it.name, bindingIndex))
                            false
                        } else {
                            true
                        }
                    }.forEach {
                        val bindingIndex = usedImageUnits.nextClearBit(0)
                        usedImageUnits.set(bindingIndex, true)
                        glProgramUniform1i(manager.programID, it.location, bindingIndex)
                        bindingPoints.add(BindingPoint(it.name, bindingIndex))
                    }
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