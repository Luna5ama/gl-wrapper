package dev.luna5ama.glwrapper.base

class IntelGL45(private val delegate: GL45) : GL45 by delegate {
    override fun glVertexArrayElementBuffer(vaobj: Int, buffer: Int) {
        if (buffer == 0) {
            val prevVao = glGetInteger(GL_VERTEX_ARRAY_BINDING)
            glBindVertexArray(vaobj)
            glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, 0)
            glBindVertexArray(prevVao)
        } else {
            delegate.glVertexArrayElementBuffer(vaobj, buffer)
        }
    }

    override fun glBlitNamedFramebuffer(
        readFramebuffer: Int,
        drawFramebuffer: Int,
        srcX0: Int,
        srcY0: Int,
        srcX1: Int,
        srcY1: Int,
        dstX0: Int,
        dstY0: Int,
        dstX1: Int,
        dstY1: Int,
        mask: Int,
        filter: Int
    ) {
        if (drawFramebuffer == 0) {
            val prevReadFbo = glGetInteger(GL_READ_FRAMEBUFFER_BINDING)
            val prevDrawFbo = glGetInteger(GL_DRAW_FRAMEBUFFER_BINDING)
            glBindFramebuffer(GL_READ_FRAMEBUFFER, readFramebuffer)
            glBindFramebuffer(GL_DRAW_FRAMEBUFFER, drawFramebuffer)
            glBlitFramebuffer(
                srcX0,
                srcY0,
                srcX1,
                srcY1,
                dstX0,
                dstY0,
                dstX1,
                dstY1,
                mask,
                filter
            )
            glBindFramebuffer(GL_READ_FRAMEBUFFER, prevReadFbo)
            glBindFramebuffer(GL_DRAW_FRAMEBUFFER, prevDrawFbo)
        } else delegate.glBlitNamedFramebuffer(
            readFramebuffer,
            drawFramebuffer,
            srcX0,
            srcY0,
            srcX1,
            srcY1,
            dstX0,
            dstY0,
            dstX1,
            dstY1,
            mask,
            filter
        )
    }
}