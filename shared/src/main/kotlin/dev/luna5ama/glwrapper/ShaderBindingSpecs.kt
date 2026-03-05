package dev.luna5ama.glwrapper

import dev.luna5ama.glwrapper.enums.BufferTarget
import dev.luna5ama.glwrapper.objects.SamplerObject
import dev.luna5ama.glwrapper.objects.TextureObject
import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap
import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.contract

data class ShaderBindingSpecs(
    val samplers: Map<String, Sampler>,
    val images: Map<String, Image>,
    val buffers: Map<BufferTarget.Shader, Map<String, Buffer>>
) {
    data class Sampler(
        val name: String,
        val texture: Int,
        val sampler: SamplerObject? = null
    ) {
        constructor(name: String, texture: TextureObject) : this(name, texture.id, null)
        constructor(name: String, texture: TextureObject, sampler: SamplerObject? = null) : this(name, texture.id, sampler)
    }

    data class Image(
        val name: String,
        val texture: Int
    ) {
        constructor(name: String, texture: TextureObject) : this(name, texture.id)
    }

    data class Buffer(
        val name: String,
        val bufferView: BufferView,
        val target: BufferTarget.Shader,
    )

    class Builder {
        private val samplers = Object2ObjectOpenHashMap<String, Sampler>()
        private val images = Object2ObjectOpenHashMap<String, Image>()
        private val buffers = Object2ObjectOpenHashMap<BufferTarget.Shader, MutableMap<String, Buffer>>()

        fun sampler(binding: Sampler) {
            val v = samplers.put(binding.name, binding)
            require(v == null) {
                "Duplicated sampler unit name: ${binding.name}, existing: $v, new: $binding"
            }
        }

        fun sampler(name: String, texture: TextureObject, sampler: SamplerObject) {
            sampler(Sampler(name, texture, sampler))
        }

        fun sampler(name: String, texture: Int) {
            sampler(Sampler(name, texture))
        }

        fun sampler(name: String, texture: TextureObject) {
            sampler(Sampler(name, texture))
        }

        fun sampler(bindings: Collection<Sampler>) {
            bindings.forEach { sampler(it) }
        }

        fun sampler(bindings: Collection<Sampler>, sampler: SamplerObject) {
            bindings.forEach { sampler(it.copy(sampler = sampler)) }
        }

        fun image(binding: Image) {
            val v = images.put(binding.name, binding)
            require(v == null) {
                "Duplicated image unit name: ${binding.name}, existing: $v, new: $binding"
            }
        }

        fun image(name: String, texture: Int) {
            image(Image(name, texture))
        }

        fun image(name: String, texture: TextureObject) {
            image(Image(name, texture))
        }

        fun image(bindings: Collection<Image>) {
            bindings.forEach { image(it) }
        }

        fun buffer(binding: Buffer) {
            val map = buffers.getOrPut(binding.target) { Object2ObjectOpenHashMap() }
            val v = map.put(binding.name, binding)
            require(v == null) {
                "Duplicated ${binding.target} buffer name: ${binding.name}, existing: $v, new: $binding"
            }
        }

        fun buffer(name: String, bufferView: BufferView, target: BufferTarget.Shader) {
            buffer(Buffer(name, bufferView, target))
        }

        fun buffer(bindings: Collection<Buffer>) {
            bindings.forEach { buffer(it) }
        }

        fun build() = ShaderBindingSpecs(samplers, images, buffers)
    }

    companion object {
        @OptIn(ExperimentalContracts::class)
        inline fun of(block: Builder.() -> Unit): ShaderBindingSpecs {
            contract {
                callsInPlace(block, kotlin.contracts.InvocationKind.EXACTLY_ONCE)
            }
            return Builder().apply(block).build()
        }
    }
}
