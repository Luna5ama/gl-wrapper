package dev.luna5ama.glwrapper.api

import dev.luna5ama.kmogus.Arr
import net.minecraft.client.render.BufferRenderer

open class GL30Impl(tempArr: Arr) : GL30LWJGL3(tempArr) {
    private val currentVertexArray = getStaticSetter(
        BufferRenderer::class.java,
        "field_29331",
        "f_166830_",
        "currentVertexArray"
    )

    override fun glBindVertexArray(array: Int) {
        currentVertexArray(array)
        super.glBindVertexArray(array)
    }
}