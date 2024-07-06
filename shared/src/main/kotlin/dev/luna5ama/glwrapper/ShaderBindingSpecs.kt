package dev.luna5ama.glwrapper

import dev.luna5ama.glwrapper.enums.BufferTarget
import dev.luna5ama.glwrapper.enums.ShaderStage
import dev.luna5ama.glwrapper.objects.BufferObject
import dev.luna5ama.glwrapper.objects.SamplerObject
import dev.luna5ama.glwrapper.objects.TextureObject
import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.contract

data class ShaderBindingSpecs(
    val samplers: Map<String, Sampler>,
    val images: Map<String, Image>,
    val buffers: Map<BufferTarget.Shader, Map<String, Buffer>>,
    val subroutines: Map<ShaderStage, Map<String, Subroutine>>
) {
    data class Sampler(
        val name: String,
        val texture: TextureObject,
        val sampler: SamplerObject?
    ) {
        constructor(name: String, texture: TextureObject) : this(name, texture, null)
    }

    data class Image(
        val name: String,
        val texture: TextureObject
    )

    data class Buffer(
        val name: String,
        val bufferView: BufferView,
        val target: BufferTarget.Shader,
    )

    data class Subroutine(
        val uniformName: String,
        val stage: ShaderStage,
        val funcName: String
    )


    class Builder {
        private val samplers = mutableMapOf<String, Sampler>()
        private val images = mutableMapOf<String, Image>()
        private val buffers = mutableMapOf<BufferTarget.Shader, MutableMap<String, Buffer>>()
        private val subroutines = mutableMapOf<ShaderStage, MutableMap<String, Subroutine>>()

        fun sampler(binding: Sampler) {
            val v = samplers.put(binding.name, binding)
            require(v == null) {
                "Duplicated sampler unit name: ${binding.name}, existing: $v, new: $binding"
            }
        }

        fun sampler(name: String, texture: TextureObject, sampler: SamplerObject) {
            sampler(Sampler(name, texture, sampler))
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

        fun image(name: String, texture: TextureObject) {
            image(Image(name, texture))
        }

        fun image(bindings: Collection<Image>) {
            bindings.forEach { image(it) }
        }

        fun buffer(binding: Buffer) {
            val map = buffers.getOrPut(binding.target, ::mutableMapOf)
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

        fun subroutine(binding: Subroutine) {
            val map = subroutines.getOrPut(binding.stage, ::mutableMapOf)
            val v = map.put(binding.uniformName, binding)
            require(v == null) {
                "Duplicated ${binding.stage} subroutine name: ${binding.uniformName}, existing: $v, new: $binding"
            }
        }

        fun subroutine(name: String, stage: ShaderStage, funcName: String) {
            subroutine(Subroutine(name, stage, funcName))
        }

        fun subroutine(bindings: Collection<Subroutine>) {
            bindings.forEach { subroutine(it) }
        }

        fun build() = ShaderBindingSpecs(samplers, images, buffers, subroutines)
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
