package dev.luna5ama.glwrapper.base

import net.minecraft.client.render.BufferRenderer

class GL30Mc(private val delegate: GL30) : GL30 by delegate {
    private val currentVertexArray = getStaticSetter(
        BufferRenderer::class.java,
        "field_29331",
        "f_166830_",
        "currentVertexArray"
    )

    override fun glBindVertexArray(array: Int) {
        currentVertexArray(array)
        delegate.glBindVertexArray(array)
    }
}