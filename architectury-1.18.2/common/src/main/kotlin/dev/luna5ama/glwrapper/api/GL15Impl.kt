package dev.luna5ama.glwrapper.api

import dev.luna5ama.kmogus.Arr
import net.minecraft.client.render.BufferRenderer

open class GL15Impl(tempArr: Arr) : GL15LWJGL3(tempArr) {
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
        super.glBindBuffer(target, buffer)
    }
}