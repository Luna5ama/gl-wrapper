package dev.luna5ama.glwrapper.lwjgl2

import dev.luna5ama.glwrapper.IGL30
import dev.luna5ama.kmogus.Arr
import org.lwjgl.opengl.GL30

open class GL30LWJGL2(override val tempArr: Arr) : IGL30 {
    override fun glGetStringi(name: Int, index: Int): String {
        return GL30.glGetStringi(name, index)
    }

    override fun glIsRenderbuffer(renderbuffer: Int): Boolean {
        return GL30.glIsRenderbuffer(renderbuffer)
    }

    override fun glBindRenderbuffer(target: Int, renderbuffer: Int) {
        GL30.glBindRenderbuffer(target, renderbuffer)
    }

    private val glDeleteRenderbuffers = createBuffer().asIntBuffer()

    override fun glDeleteRenderbuffers(n: Int, renderbuffers: Long) {
        GL30.glDeleteFramebuffers(wrapBuffer(glDeleteRenderbuffers, renderbuffers, n))
    }

    override fun glIsFramebuffer(framebuffer: Int): Boolean {
        return GL30.glIsFramebuffer(framebuffer)
    }

    override fun glBindFramebuffer(target: Int, framebuffer: Int) {
        GL30.glBindFramebuffer(target, framebuffer)
    }

    private val glDeleteFramebuffers = createBuffer().asIntBuffer()

    override fun glDeleteFramebuffers(n: Int, framebuffers: Long) {
        GL30.glDeleteFramebuffers(wrapBuffer(glDeleteFramebuffers, framebuffers, n))
    }

    override fun glColorMaski(buf: Int, r: Boolean, g: Boolean, b: Boolean, a: Boolean) {
        GL30.glColorMaski(buf, r, g, b, a)
    }

    override fun glBindBufferRange(target: Int, index: Int, buffer: Int, offset: Long, size: Long) {
        GL30.glBindBufferRange(target, index, buffer, offset, size)
    }

    override fun glBindBufferBase(target: Int, index: Int, buffer: Int) {
        GL30.glBindBufferBase(target, index, buffer)
    }

    override fun glBindVertexArray(array: Int) {
        GL30.glBindVertexArray(array)
    }

    private val glDeleteVertexArrays = createBuffer().asIntBuffer()

    override fun glDeleteVertexArrays(n: Int, arrays: Long) {
        GL30.glDeleteVertexArrays(wrapBuffer(glDeleteVertexArrays, arrays, n))
    }

    override fun glIsVertexArray(array: Int): Boolean {
        return GL30.glIsVertexArray(array)
    }

    override fun glDeleteRenderbuffers(renderbuffer: Int) {
        GL30.glDeleteRenderbuffers(renderbuffer)
    }

    override fun glDeleteFramebuffers(framebuffer: Int) {
        GL30.glDeleteFramebuffers(framebuffer)
    }

    override fun glDeleteVertexArrays(array: Int) {
        GL30.glDeleteVertexArrays(array)
    }
}