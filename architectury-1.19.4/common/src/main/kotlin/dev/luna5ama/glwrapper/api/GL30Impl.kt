package dev.luna5ama.glwrapper.api

import dev.luna5ama.kmogus.Arr
import net.minecraft.client.render.BufferRenderer

open class GL30Impl(tempArr: Arr) : GL30LWJGL3(tempArr) {
    override fun glBindVertexArray(array: Int) {
        BufferRenderer.resetCurrentVertexBuffer()
        super.glBindVertexArray(array)
    }
}