package dev.luna5ama.glwrapper.impl

import dev.luna5ama.glwrapper.api.*
import dev.luna5ama.kmogus.Ptr
import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap

class VertexArrayObject : IGLObject, IGLBinding {
    override val id: Int = glCreateVertexArrays()

    private var ebo: BufferObject? = null
    private val vboBindings = Object2ObjectOpenHashMap<BufferObject, VBOBinding>()

    fun attachEbo(ebo: BufferObject) {
        glVertexArrayElementBuffer(id, ebo.id)
        this.ebo = ebo
    }

    fun attachVbo(vbo: BufferObject, vertexAttribute: VertexAttribute, offset: Long = 0) {
        val prevBinding = vboBindings[vbo]
        if (prevBinding?.vbo == vbo && prevBinding.offset == offset && prevBinding.vertexAttribute == vertexAttribute) return

        val binding = VBOBinding(prevBinding?.index ?: vboBindings.size, offset, vbo, vertexAttribute)
        vboBindings[vbo] = binding

        if (prevBinding?.index != binding.index
            || prevBinding.vbo != vbo
            || prevBinding.offset != offset
            || prevBinding.vertexAttribute.stride != vertexAttribute.stride
        ) {
            glVertexArrayVertexBuffer(id, binding.index, vbo.id, offset, vertexAttribute.stride)
        }

        if (prevBinding?.index != binding.index
            || prevBinding.vertexAttribute != vertexAttribute
        ) {
            vertexAttribute.apply(this, binding.index)
        }
    }

    override fun bind() {
        glBindVertexArray(id)
    }

    override fun unbind() {
        glBindVertexArray(0)
    }

    override fun destroy() {
        glDeleteVertexArrays(id)
        ebo?.destroy()
        ebo = null
        vboBindings.keys.forEach {
            it.destroy()
        }
        vboBindings.clear()
    }

    fun clear() {
        if (ebo != null) {
            glVertexArrayElementBuffer(id, 0)
            ebo = null
        }
        glVertexArrayVertexBuffers(id, 0, vboBindings.size, Ptr.NULL, Ptr.NULL, Ptr.NULL)
        vboBindings.clear()
    }

    fun destroyVao() {
        glDeleteVertexArrays(id)
        ebo = null
        vboBindings.clear()
    }

    private data class VBOBinding(
        val index: Int,
        val offset: Long,
        val vbo: BufferObject,
        val vertexAttribute: VertexAttribute
    )
}