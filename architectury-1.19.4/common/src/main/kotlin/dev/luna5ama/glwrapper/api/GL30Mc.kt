package dev.luna5ama.glwrapper.api

import net.minecraft.client.render.BufferRenderer

class GL30Mc(private val delegate: GL30) : GL30 by delegate {
    override fun glBindVertexArray(array: Int) {
        BufferRenderer.resetCurrentVertexBuffer()
        delegate.glBindVertexArray(array)
    }
}