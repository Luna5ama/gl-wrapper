package dev.luna5ama.glwrapper.api

import dev.luna5ama.kmogus.Arr
import org.lwjgl.opengl.GL15

open class GL15LWJGL2(override val tempArr: Arr) : IGL15 {
    override fun glBindBuffer(target: Int, buffer: Int) {
        GL15.glBindBuffer(target, buffer)
    }

    private val glDeleteBuffers = createBuffer().asIntBuffer()

    override fun glDeleteBuffers(n: Int, buffers: Long) {
        GL15.glDeleteBuffers(wrapBuffer(glDeleteBuffers, buffers, n))
    }

    override fun glIsBuffer(buffer: Int): Boolean {
        return GL15.glIsBuffer(buffer)
    }


    override fun glDeleteBuffers(buffer: Int) {
        GL15.glDeleteBuffers(buffer)
    }
}