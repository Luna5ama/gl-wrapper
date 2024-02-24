package dev.luna5ama.glwrapper.api

import net.minecraft.client.render.BufferRenderer

class GL15Mc(private val delegate: GL15) : GL15 by delegate {
    private val currentVertexBuffer = getStaticSetter(
        BufferRenderer::class.java,
        "field_29332",
        "f_166831_",
        "currentVertexBuffer"
    )
    private val currentElementBuffer = getStaticSetter(
        BufferRenderer::class.java,
        "field_29333",
        "f_166832_",
        "currentElementBuffer"
    )

    override fun glBindBuffer(target: Int, buffer: Int) {
        when (target) {
            GL_ARRAY_BUFFER -> currentVertexBuffer(buffer)
            GL_ELEMENT_ARRAY_BUFFER -> currentElementBuffer(buffer)
        }
        delegate.glBindBuffer(target, buffer)
    }
}