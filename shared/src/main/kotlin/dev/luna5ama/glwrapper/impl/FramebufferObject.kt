package dev.luna5ama.glwrapper.impl

import dev.luna5ama.glwrapper.api.*
import it.unimi.dsi.fastutil.objects.ObjectArrayList

class FramebufferObject : IGLObject, IGLBinding, IGLTargetBinding, IGLSized2D {
    override val id = glCreateFramebuffers()
    val colorAttachments = arrayOfNulls<Attachment>(32)
    var depthAttachment: Attachment? = null; private set
    var stencilAttachment: Attachment? = null; private set

    override var sizeX = -1; private set
    override var sizeY = -1; private set

    fun attach(texture: TextureObject.Texture2D, attachment: Int, level: Int = 0) {
        when (attachment) {
            GL_DEPTH_ATTACHMENT -> {
                depthAttachment = texture
            }
            GL_STENCIL_ATTACHMENT -> {
                stencilAttachment = texture
            }
            GL_DEPTH_STENCIL_ATTACHMENT -> {
                depthAttachment = texture
                stencilAttachment = texture
            }
            in GL_COLOR_ATTACHMENT0..GL_COLOR_ATTACHMENT31 -> {
                colorAttachments[attachment - GL_COLOR_ATTACHMENT0] = texture
            }
            else -> {
                throw IllegalArgumentException("Invalid attachment: $attachment")
            }
        }

        checkAndUpdateSize(texture)
        glNamedFramebufferTexture(id, attachment, texture.id, level)
    }

    fun attachLayer(texture: TextureObject.Texture3D, attachment: Int, layer: Int, level: Int = 0) {
        when (attachment) {
            GL_DEPTH_ATTACHMENT -> {
                depthAttachment = texture
            }
            GL_STENCIL_ATTACHMENT -> {
                stencilAttachment = texture
            }
            GL_DEPTH_STENCIL_ATTACHMENT -> {
                depthAttachment = texture
                stencilAttachment = texture
            }
            in GL_COLOR_ATTACHMENT0..GL_COLOR_ATTACHMENT31 -> {
                colorAttachments[attachment - GL_COLOR_ATTACHMENT0] = texture
            }
            else -> {
                throw IllegalArgumentException("Invalid attachment: $attachment")
            }
        }

        checkAndUpdateSize(texture)
        glNamedFramebufferTextureLayer(id, attachment, texture.id, level, layer)
    }

    fun attach(renderbuffer: RenderbufferObject, attachment: Int) {
        when (attachment) {
            GL_DEPTH_ATTACHMENT -> {
                depthAttachment = renderbuffer
            }
            GL_STENCIL_ATTACHMENT -> {
                stencilAttachment = renderbuffer
            }
            GL_DEPTH_STENCIL_ATTACHMENT -> {
                depthAttachment = renderbuffer
                stencilAttachment = renderbuffer
            }
            in GL_COLOR_ATTACHMENT0..GL_COLOR_ATTACHMENT31 -> {
                colorAttachments[attachment - GL_COLOR_ATTACHMENT0] = renderbuffer
            }
            else -> {
                throw IllegalArgumentException("Invalid attachment: $attachment")
            }
        }

        checkAndUpdateSize(renderbuffer)
        glNamedFramebufferRenderbuffer(id, attachment, GL_RENDERBUFFER, renderbuffer.id)
    }

    private fun checkAndUpdateSize(attachment: Attachment) {
        if (sizeX != -1 && sizeY != -1) {
            require(sizeX == attachment.sizeX && sizeY == attachment.sizeY) {
                "Framebuffer attachments size mismatch: $sizeX x $sizeY != ${attachment.sizeX} x ${attachment.sizeY}"
            }
            return
        }

        sizeX = attachment.sizeX
        sizeY = attachment.sizeY
    }

    fun check() {
        check(glCheckNamedFramebufferStatus(id, GL_FRAMEBUFFER) == GL_FRAMEBUFFER_COMPLETE)
    }

    override fun bind() {
        bind(GL_DRAW_FRAMEBUFFER)
    }

    override fun unbind() {
        unbind(GL_DRAW_FRAMEBUFFER)
    }

    override fun bind(target: Int) {
        glBindFramebuffer(target, id)
    }

    override fun unbind(target: Int) {
        glBindFramebuffer(target, 0)
    }

    override fun destroy() {
        glDeleteFramebuffers(id)
        for (i in colorAttachments.indices) {
            colorAttachments[i]?.destroy()
        }
        colorAttachments.fill(null)
        val depth = depthAttachment
        depthAttachment = null

        val stencil = stencilAttachment
        stencilAttachment = null

        if (depth != null) {
            depth.destroy()
        }

        if (stencil != null && stencil !== depth) {
            stencil.destroy()
        }
    }

    fun destroyFbo() {
        glDeleteFramebuffers(id)
        colorAttachments.fill(null)
        depthAttachment = null
        stencilAttachment = null
    }

    interface Attachment : IGLSized2D
}