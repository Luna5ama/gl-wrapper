package dev.luna5ama.glwrapper.objects

import dev.luna5ama.glwrapper.VertexAttribute
import dev.luna5ama.glwrapper.api.glBindVertexArray
import dev.luna5ama.glwrapper.api.glVertexArrayElementBuffer
import dev.luna5ama.glwrapper.api.glVertexArrayVertexBuffer
import dev.luna5ama.glwrapper.api.glVertexArrayVertexBuffers
import dev.luna5ama.glwrapper.enums.GLObjectType
import dev.luna5ama.kmogus.Ptr
import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap

class VertexArrayObject private constructor(private val delegate: IGLObject.Impl) : IGLObject by delegate, IGLBinding {
    constructor() : this(IGLObject.Impl(GLObjectType.VertexArray))

    private var ebo: BufferObject? = null
    private val vboBindings = Object2ObjectOpenHashMap<BufferObject, VBOBinding>()

    fun attachEbo(ebo: BufferObject) {
        tryCreate()
        glVertexArrayElementBuffer(id, ebo.id)
        this.ebo = ebo
    }

    fun attachVbo(vbo: BufferObject, vertexAttribute: VertexAttribute, offset: Long = 0) {
        tryCreate()
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
        if (delegate.id0 == 0) return

        ebo?.destroy()
        vboBindings.keys.forEach {
            it.destroy()
        }
        destroyVao()
    }

    fun destroyVao() {
        if (delegate.id0 == 0) return

        ebo = null
        vboBindings.clear()
        delegate.destroy()
    }

    fun clear() {
        if (delegate.id0 == 0) return

        if (ebo != null) {
            glVertexArrayElementBuffer(id, 0)
            ebo = null
        }
        glVertexArrayVertexBuffers(id, 0, vboBindings.size, Ptr.NULL, Ptr.NULL, Ptr.NULL)
        vboBindings.clear()
    }

    private data class VBOBinding(
        val index: Int,
        val offset: Long,
        val vbo: BufferObject,
        val vertexAttribute: VertexAttribute
    )
}