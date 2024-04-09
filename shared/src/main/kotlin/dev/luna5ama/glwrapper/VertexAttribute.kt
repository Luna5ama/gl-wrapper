package dev.luna5ama.glwrapper

import dev.luna5ama.glwrapper.api.*
import dev.luna5ama.glwrapper.enums.GLDataType
import dev.luna5ama.glwrapper.objects.VertexArrayObject

class VertexAttribute private constructor(val stride: Int, private val divisor: Int, private val entries: List<Entry>) {
    fun apply(vao: VertexArrayObject, binding: Int) {
        if (GpuVendor.isIntel) {
            val prevVao = glGetInteger(GL_VERTEX_ARRAY_BINDING)
            vao.bind()
            entries.forEach {
                it.apply(vao.id, binding)
            }

            if (divisor != 0) {
                glVertexArrayBindingDivisor(vao.id, binding, divisor)
            }
            glBindVertexArray(prevVao)
        } else {
            entries.forEach {
                it.apply(vao.id, binding)
            }

            if (divisor != 0) {
                glVertexArrayBindingDivisor(vao.id, binding, divisor)
            }
        }
    }

    class Builder(private val stride: Int, private val divisor: Int) {
        private var offset = 0
        private val entries = ArrayList<Entry>()

        fun int(index: Int, size: Int, type: GLDataType) {
            entries.add(IntEntry(index, size, type.glEnum, offset))
            offset += size * type.size
        }

        fun float(index: Int, size: Int, type: GLDataType, normalized: Boolean) {
            entries.add(FloatEntry(index, size, type.glEnum, offset, normalized))
            offset += size * type.size
        }

        fun padding(bytes: Int) {
            offset += bytes
        }

        fun build(): VertexAttribute {
            return VertexAttribute(stride, divisor, entries)
        }
    }

    private sealed interface Entry {
        val index: Int
        val size: Int
        val type: Int
        val offset: Int

        fun apply(vaoID: Int, binding: Int)
    }

    private class FloatEntry(
        override val index: Int,
        override val size: Int,
        override val type: Int,
        override val offset: Int,
        val normalized: Boolean
    ) : Entry {
        override fun apply(vaoID: Int, binding: Int) {
            glEnableVertexArrayAttrib(vaoID, index)
            glVertexArrayAttribBinding(vaoID, index, binding)
            glVertexArrayAttribFormat(vaoID, index, size, type, normalized, offset)
        }
    }

    private class IntEntry(
        override val index: Int,
        override val size: Int,
        override val type: Int,
        override val offset: Int
    ) : Entry {
        override fun apply(vaoID: Int, binding: Int) {
            glEnableVertexArrayAttrib(vaoID, index)
            glVertexArrayAttribBinding(vaoID, index, binding)
            glVertexArrayAttribIFormat(vaoID, index, size, type, offset)
        }
    }

    companion object {
        @JvmField
        val EMPTY = VertexAttribute(1, 0, emptyList())
    }
}


inline fun buildAttribute(stride: Int, divisor: Int = 0, block: VertexAttribute.Builder.() -> Unit): VertexAttribute {
    return VertexAttribute.Builder(stride, divisor).apply(block).build()
}