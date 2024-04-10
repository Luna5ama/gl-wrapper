package dev.luna5ama.glwrapper

import dev.luna5ama.glwrapper.enums.BufferTarget
import dev.luna5ama.glwrapper.objects.BufferObject
import dev.luna5ama.glwrapper.objects.SamplerObject
import dev.luna5ama.glwrapper.objects.TextureObject
import kotlin.contracts.contract

sealed interface ShaderBindingSpec {
    class Sampler private constructor(val bindings: Map<String, Binding>) : ShaderBindingSpec {
        constructor(bindings: List<Binding>) : this(bindings.associateBy { it.name })
        constructor(vararg bindings: Binding) : this(bindings.associateBy { it.name })

        class Binding(val name: String, val texture: TextureObject, val sampler: SamplerObject)

        inline fun copy(block: Builder.() -> Unit): Sampler {
            contract {
                callsInPlace(block, kotlin.contracts.InvocationKind.EXACTLY_ONCE)
            }
            return Builder(this).apply(block).build()
        }

        operator fun plus(other: Sampler): Sampler {
            return Sampler(bindings + other.bindings)
        }

        class Builder private constructor(private val bindings: MutableMap<String, Binding>) {
            constructor() : this(mutableMapOf())
            constructor(samplers: Sampler) : this(samplers.bindings.toMutableMap())

            fun add(name: String, texture: TextureObject, sampler: SamplerObject) {
                bindings[name] = Binding(name, texture, sampler)
            }

            fun build() = Sampler(bindings)
        }

        companion object {
            inline operator fun invoke(block: Builder.() -> Unit): Sampler {
                return Builder().apply(block).build()
            }
        }
    }

    class Image private constructor(val bindings: Map<String, Binding>) : ShaderBindingSpec {
        constructor(bindings: List<Binding>) : this(bindings.associateBy { it.name })
        constructor(vararg bindings: Binding) : this(bindings.associateBy { it.name })

        class Binding(
            val name: String,
            val texture: TextureObject
        )

        inline fun copy(block: Builder.() -> Unit) {
            contract {
                callsInPlace(block, kotlin.contracts.InvocationKind.EXACTLY_ONCE)
            }
            Builder(this).apply(block).build()
        }

        operator fun plus(other: Image): Image {
            return Image(bindings + other.bindings)
        }

        class Builder private constructor(private val bindings: MutableMap<String, Binding>) {
            constructor() : this(mutableMapOf())
            constructor(images: Image) : this(images.bindings.toMutableMap())

            fun add(name: String, texture: TextureObject) {
                bindings[name] = Binding(name, texture)
            }

            fun build() = Image(bindings)
        }

        companion object {
            inline operator fun invoke(block: Builder.() -> Unit) = Builder().apply(block).build()
        }
    }

    class Buffer private constructor(val bindings: Map<BufferTarget.Shader, Map<String, Binding>>) : ShaderBindingSpec {
        constructor(bindings: List<Binding>) : this(bindings.groupBy { it.target }
            .mapValues { entry -> entry.value.associateBy { it.name } })

        constructor(vararg bindings: Binding) : this(bindings.groupBy { it.target }
            .mapValues { entry -> entry.value.associateBy { it.name } })

        data class Binding(
            val name: String,
            val buffer: BufferObject,
            val target: BufferTarget.Shader,
            val offset: Int = 0,
            val size: Int = -1
        )

        inline fun copy(block: Builder.() -> Unit) {
            contract {
                callsInPlace(block, kotlin.contracts.InvocationKind.EXACTLY_ONCE)
            }
            Builder(this).apply(block).build()
        }

        operator fun plus(other: Buffer): Buffer {
            return Buffer(bindings + other.bindings)
        }

        class Builder private constructor(private val bindings: MutableMap<BufferTarget.Shader, MutableMap<String, Binding>>) {
            constructor() : this(mutableMapOf())
            constructor(buffers: Buffer) : this(buffers.bindings.mapValuesTo(mutableMapOf()) { it.value.toMutableMap() })

            fun add(name: String, buffer: BufferObject, target: BufferTarget.Shader, offset: Int = 0, size: Int = -1) {
                bindings.computeIfAbsent(target) { mutableMapOf() }[name] = Binding(name, buffer, target, offset, size)
            }

            fun build() = Buffer(bindings)
        }

        companion object {
            inline operator fun invoke(block: Builder.() -> Unit) = Builder().apply(block).build()
        }
    }
}