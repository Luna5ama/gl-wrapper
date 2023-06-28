package dev.luna5ama.glwrapper.api

import dev.luna5ama.kmogus.Arr
import org.lwjgl.opengl.GL30C

open class GL30LWJGL3(override val tempArr: Arr) : IGL30 {
    override fun glGetStringi(name: Int, index: Int): String? {
        return GL30C.glGetStringi(name, index)
    }

    override fun glIsRenderbuffer(renderbuffer: Int): Boolean {
        return GL30C.glIsRenderbuffer(renderbuffer)
    }

    override fun glBindRenderbuffer(target: Int, renderbuffer: Int) {
        GL30C.glBindRenderbuffer(target, renderbuffer)
    }

    override fun glDeleteRenderbuffers(n: Int, renderbuffers: Long) {
        GL30C.nglDeleteRenderbuffers(n, renderbuffers)
    }

    override fun glIsFramebuffer(framebuffer: Int): Boolean {
        return GL30C.glIsFramebuffer(framebuffer)
    }

    override fun glBindFramebuffer(target: Int, framebuffer: Int) {
        GL30C.glBindFramebuffer(target, framebuffer)
    }

    override fun glDeleteFramebuffers(n: Int, framebuffers: Long) {
        GL30C.nglDeleteFramebuffers(n, framebuffers)
    }

    override fun glColorMaski(buf: Int, r: Boolean, g: Boolean, b: Boolean, a: Boolean) {
        GL30C.glColorMaski(buf, r, g, b, a)
    }

    override fun glBindBufferRange(target: Int, index: Int, buffer: Int, offset: Long, size: Long) {
        GL30C.glBindBufferRange(target, index, buffer, offset, size)
    }

    override fun glBindBufferBase(target: Int, index: Int, buffer: Int) {
        GL30C.glBindBufferBase(target, index, buffer)
    }

    override fun glBindVertexArray(array: Int) {
        GL30C.glBindVertexArray(array)
    }

    override fun glDeleteVertexArrays(n: Int, arrays: Long) {
        GL30C.nglDeleteVertexArrays(n, arrays)
    }

    override fun glIsVertexArray(array: Int): Boolean {
        return GL30C.glIsVertexArray(array)
    }

    override fun glDeleteRenderbuffers(renderbuffer: Int) {
        GL30C.glDeleteRenderbuffers(renderbuffer)
    }

    override fun glDeleteRenderbuffers(vararg renderbuffers: Int) {
        GL30C.glDeleteRenderbuffers(renderbuffers)
    }

    override fun glDeleteFramebuffers(framebuffer: Int) {
        GL30C.glDeleteFramebuffers(framebuffer)
    }

    override fun glDeleteFramebuffers(vararg framebuffers: Int) {
        GL30C.glDeleteFramebuffers(framebuffers)
    }

    override fun glDeleteVertexArrays(array: Int) {
        GL30C.glDeleteVertexArrays(array)
    }

    override fun glDeleteVertexArrays(vararg arrays: Int) {
        GL30C.glDeleteVertexArrays(arrays)
    }
}