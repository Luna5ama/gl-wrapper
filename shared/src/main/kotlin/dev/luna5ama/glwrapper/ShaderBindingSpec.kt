package dev.luna5ama.glwrapper

import dev.luna5ama.glwrapper.enums.BufferTarget
import dev.luna5ama.glwrapper.objects.BufferObject
import dev.luna5ama.glwrapper.objects.SamplerObject
import dev.luna5ama.glwrapper.objects.TextureObject
import kotlin.contracts.contract

sealed interface ShaderBindingSpec {
    class Samplers private constructor(val bindings: Map<String, Binding>): ShaderBindingSpec  {
        constructor(bindings: List<Binding>) : this(bindings.associateBy { it.name })
        constructor(vararg bindings: Binding) : this(bindings.associateBy { it.name })

        class Binding(val name: String, val texture: TextureObject, val sampler: SamplerObject)

        class Builder private constructor(private val bindings: MutableMap<String, Binding>) {
            constructor() : this(mutableMapOf())
            constructor(samplers: Samplers) : this(samplers.bindings.toMutableMap())

            fun add(name: String, texture: TextureObject, sampler: SamplerObject) {
                bindings[name] = Binding(name, texture, sampler)
            }

            fun build() = Samplers(bindings)
        }

        inline fun copy(block: Builder.() -> Unit): Samplers {
            contract {
                callsInPlace(block, kotlin.contracts.InvocationKind.EXACTLY_ONCE)
            }
            return Builder(this).apply(block).build()
        }

        companion object {
            inline operator fun invoke(block: Builder.() -> Unit): Samplers {
                return Builder().apply(block).build()
            }
        }
    }

    class Images private constructor(val bindings: Map<String, Binding>): ShaderBindingSpec  {
        constructor(bindings: List<Binding>) : this(bindings.associateBy { it.name })
        constructor(vararg bindings: Binding) : this(bindings.associateBy { it.name })

        class Binding(
            val name: String,
            val texture: TextureObject,
            val access: Int,
            val format: Int,
            val level: Int,
            val layered: Int,
            val layer: Int,
        ) {
            constructor(name: String, texture: TextureObject) :
                    this(name, texture, 0, 0, 0, -1, 0)
        }

        inline fun copy(block: Builder.() -> Unit) {
            contract {
                callsInPlace(block, kotlin.contracts.InvocationKind.EXACTLY_ONCE)
            }
            Builder(this).apply(block).build()
        }

        class Builder private constructor(private val bindings: MutableMap<String, Binding>) {
            constructor() : this(mutableMapOf())
            constructor(images: Images) : this(images.bindings.toMutableMap())

            fun add(
                name: String,
                texture: TextureObject
            ) {
                bindings[name] = Binding(name, texture)
            }

            fun add(
                name: String,
                texture: TextureObject,
                access: Int,
                format: Int,
                level: Int,
                layered: Int,
                layer: Int,
            ) {
                bindings[name] = Binding(name, texture, access, format, level, layered, layer)
            }

            fun build() = Images(bindings)
        }

        companion object {
           inline operator fun invoke(block: Builder.() -> Unit) = Builder().apply(block).build()
        }
    }

    class Buffers private constructor(val bindings: Map<BufferTarget.Shader, Map<String, Binding>>) : ShaderBindingSpec {
        constructor(bindings: List<Binding>) : this(bindings.groupBy { it.target }.mapValues { entry -> entry.value.associateBy { it.name } })
        constructor(vararg bindings: Binding) : this(bindings.groupBy { it.target }.mapValues { entry -> entry.value.associateBy { it.name } })

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

        class Builder private constructor(private val bindings: MutableMap<BufferTarget.Shader, MutableMap<String, Binding>>) {
            constructor() : this(mutableMapOf())
            constructor(buffers: Buffers) : this(buffers.bindings.mapValuesTo(mutableMapOf()) { it.value.toMutableMap() })

            fun add(name: String, buffer: BufferObject, target: BufferTarget.Shader, offset: Int = 0, size: Int = -1) {
                bindings.computeIfAbsent(target) { mutableMapOf() }[name] = Binding(name, buffer, target, offset, size)
            }

            fun build() = Buffers(bindings)
        }

        companion object {
            inline operator fun invoke(block: Builder.() -> Unit) = Builder().apply(block).build()
        }
    }
}