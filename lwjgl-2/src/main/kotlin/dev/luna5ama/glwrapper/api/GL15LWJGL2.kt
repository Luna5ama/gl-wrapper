package dev.luna5ama.glwrapper.base

import dev.luna5ama.kmogus.Arr
import dev.luna5ama.kmogus.Ptr
import dev.luna5ama.kmogus.asIntBuffer
import dev.luna5ama.kmogus.nullIntBuffer
import org.lwjgl.opengl.GL15

open class GL15LWJGL2(override val tempArr: Arr) : IGL15 {
    override fun glBindBuffer(target: Int, buffer: Int) {
        GL15.glBindBuffer(target, buffer)
    }

    private val glDeleteBuffers = nullIntBuffer()

    override fun glDeleteBuffers(n: Int, buffers: Long) {
        glDeleteBuffers(n, Ptr(buffers))
    }

    override fun glDeleteBuffers(n: Int, buffers: Ptr) {
        GL15.glDeleteBuffers(buffers.asIntBuffer(n, glDeleteBuffers))
    }

    override fun glIsBuffer(buffer: Int): Boolean {
        return GL15.glIsBuffer(buffer)
    }


    override fun glDeleteBuffers(buffer: Int) {
        GL15.glDeleteBuffers(buffer)
    }
}