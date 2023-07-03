package dev.luna5ama.glwrapper.api

import dev.luna5ama.kmogus.Arr
import net.minecraft.client.render.BufferRenderer

open class GL15Impl(tempArr: Arr) : GL15LWJGL3(tempArr) {
    override fun glBindBuffer(target: Int, buffer: Int) {
        BufferRenderer.resetCurrentVertexBuffer()
        super.glBindBuffer(target, buffer)
    }
}