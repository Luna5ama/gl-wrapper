package dev.luna5ama.glwrapper

import dev.luna5ama.glwrapper.enums.BufferTarget
import dev.luna5ama.glwrapper.objects.BufferObject
import dev.luna5ama.glwrapper.objects.SamplerObject
import dev.luna5ama.glwrapper.objects.TextureObject
import kotlin.contracts.contract

data class ShaderBindingSpecs(
    val samplers: Map<String, SamplerBinding>,
    val images: Map<String, ImageBinding>,
    val buffers: Map<BufferTarget.Shader, Map<String, BufferBinding>>
) {
    data class SamplerBinding(
        val name: String,
        val texture: TextureObject,
        val sampler: SamplerObject?
    ) {
        constructor(name: String, texture: TextureObject) : this(name, texture, null)
    }

    data class ImageBinding(
        val name: String,
        val texture: TextureObject
    )

    data class BufferBinding(
        val name: String,
        val buffer: BufferObject,
        val target: BufferTarget.Shader,
        val offset: Long,
        val size: Long
    ) {
        constructor(name: String, buffer: BufferObject, target: BufferTarget.Shader) : this(name, buffer, target, -1, -1)
    }


    class Builder {
        private val samplers = mutableMapOf<String, SamplerBinding>()
        private val images = mutableMapOf<String, ImageBinding>()
        private val buffers = mutableMapOf<BufferTarget.Shader, MutableMap<String, BufferBinding>>()

        fun sampler(binding: SamplerBinding) {
            val v = samplers.put(binding.name, binding)
            require(v == null) {
                "Duplicated sampler unit name: ${binding.name}, existing: $v, new: $binding"
            }
        }

        fun sampler(name: String, texture: TextureObject, sampler: SamplerObject) {
            sampler(SamplerBinding(name, texture, sampler))
        }

        fun sampler(name: String, texture: TextureObject) {
            sampler(SamplerBinding(name, texture))
        }

        fun sampler(bindings: Collection<SamplerBinding>) {
            bindings.forEach { sampler(it) }
        }

        fun sampler(bindings: Collection<SamplerBinding>, sampler: SamplerObject) {
            bindings.forEach { sampler(it.copy(sampler = sampler)) }
        }

        fun image(binding: ImageBinding) {
            val v = images.put(binding.name, binding)
            require(v == null) {
                "Duplicated image unit name: ${binding.name}, existing: $v, new: $binding"
            }
        }

        fun image(name: String, texture: TextureObject) {
            image(ImageBinding(name, texture))
        }

        fun image(bindings: Collection<ImageBinding>) {
            bindings.forEach { image(it) }
        }

        fun buffer(binding: BufferBinding) {
            val map = buffers.getOrPut(binding.target, ::mutableMapOf)
            val v = map.put(binding.name, binding)
            require(v == null) {
                "Duplicated ${binding.target} buffer name: ${binding.name}, existing: $v, new: $binding"
            }
        }

        fun buffer(name: String, buffer: BufferObject, target: BufferTarget.Shader) {
            buffer(BufferBinding(name, buffer, target))
        }

        fun buffer(name: String, buffer: BufferObject, target: BufferTarget.Shader, offset: Long, size: Long) {
            buffer(BufferBinding(name, buffer, target, offset, size))
        }

        fun buffer(bindings: Collection<BufferBinding>) {
            bindings.forEach { buffer(it) }
        }

        fun build() = ShaderBindingSpecs(samplers, images, buffers)
    }

    companion object {
        inline fun of(block: Builder.() -> Unit): ShaderBindingSpecs {
            contract {
                callsInPlace(block, kotlin.contracts.InvocationKind.EXACTLY_ONCE)
            }
            return Builder().apply(block).build()
        }
    }
}
