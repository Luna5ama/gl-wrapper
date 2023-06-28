package dev.luna5ama.glwrapper.api

import dev.luna5ama.kmogus.Arr
import org.lwjgl.opengl.GL15C

open class GL15LWJGL3(override val tempArr: Arr) : IGL15 {
    override fun glBindBuffer(target: Int, buffer: Int) {
        GL15C.glBindBuffer(target, buffer)
    }

    override fun glDeleteBuffers(n: Int, buffers: Long) {
        GL15C.nglDeleteBuffers(n, buffers)
    }

    override fun glIsBuffer(buffer: Int): Boolean {
        return GL15C.glIsBuffer(buffer)
    }


    override fun glDeleteBuffers(buffer: Int) {
        GL15C.glDeleteBuffers(buffer)
    }

    override fun glDeleteBuffers(vararg buffers: Int) {
        GL15C.glDeleteBuffers(buffers)
    }
}