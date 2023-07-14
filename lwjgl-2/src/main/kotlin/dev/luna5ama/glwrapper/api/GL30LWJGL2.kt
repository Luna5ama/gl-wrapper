package dev.luna5ama.glwrapper.api

import dev.luna5ama.kmogus.Arr
import dev.luna5ama.kmogus.Ptr
import dev.luna5ama.kmogus.asIntBuffer
import dev.luna5ama.kmogus.nullIntBuffer
import org.lwjgl.opengl.GL30

open class GL30LWJGL2(override val tempArr: Arr) : IGL30 {
    override fun glGetStringi(name: Int, index: Int): String? {
        return GL30.glGetStringi(name, index)
    }

    override fun glIsRenderbuffer(renderbuffer: Int): Boolean {
        return GL30.glIsRenderbuffer(renderbuffer)
    }

    override fun glBindRenderbuffer(target: Int, renderbuffer: Int) {
        GL30.glBindRenderbuffer(target, renderbuffer)
    }

    private val glDeleteRenderbuffers = nullIntBuffer()

    override fun glDeleteRenderbuffers(n: Int, renderbuffers: Long) {
        glDeleteRenderbuffers(n, Ptr(renderbuffers))
    }

    override fun glDeleteRenderbuffers(n: Int, renderbuffers: Ptr) {
        GL30.glDeleteRenderbuffers(renderbuffers.asIntBuffer(n, glDeleteRenderbuffers))
    }

    override fun glIsFramebuffer(framebuffer: Int): Boolean {
        return GL30.glIsFramebuffer(framebuffer)
    }

    override fun glBindFramebuffer(target: Int, framebuffer: Int) {
        GL30.glBindFramebuffer(target, framebuffer)
    }

    private val glDeleteFramebuffers = nullIntBuffer()

    override fun glDeleteFramebuffers(n: Int, framebuffers: Long) {
        glDeleteFramebuffers(n, Ptr(framebuffers))
    }

    override fun glDeleteFramebuffers(n: Int, framebuffers: Ptr) {
        GL30.glDeleteFramebuffers(framebuffers.asIntBuffer(n, glDeleteFramebuffers))
    }

    override fun glColorMaski(buf: Int, r: Boolean, g: Boolean, b: Boolean, a: Boolean) {
        GL30.glColorMaski(buf, r, g, b, a)
    }

    override fun glEnablei(cap: Int, index: Int) {
        GL30.glEnablei(cap, index)
    }

    override fun glDisablei(cap: Int, index: Int) {
        GL30.glDisablei(cap, index)
    }

    override fun glIsEnabledi(cap: Int, index: Int): Boolean {
        return GL30.glIsEnabledi(cap, index)
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

    private val glDeleteVertexArrays = nullIntBuffer()

    override fun glDeleteVertexArrays(n: Int, arrays: Long) {
        glDeleteVertexArrays(n, Ptr(arrays))
    }

    override fun glDeleteVertexArrays(n: Int, arrays: Ptr) {
        GL30.glDeleteVertexArrays(arrays.asIntBuffer(n, glDeleteVertexArrays))
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