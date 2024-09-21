package dev.luna5ama.glwrapper.base

import net.minecraft.client.render.BufferRenderer

class GL15Mc(private val delegate: GL15) : GL15 by delegate {
    override fun glBindBuffer(target: Int, buffer: Int) {
        BufferRenderer.resetCurrentVertexBuffer()
        delegate.glBindBuffer(target, buffer)
    }
}