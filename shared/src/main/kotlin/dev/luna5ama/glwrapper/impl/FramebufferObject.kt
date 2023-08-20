package dev.luna5ama.glwrapper.impl

import dev.luna5ama.glwrapper.api.*

class FramebufferObject : IGLObject, IGLBinding, IGLTargetBinding, IGLSized2D {
    override var id = glCreateFramebuffers(); private set
    val colorAttachments = arrayOfNulls<Attachment>(32)
    var depthAttachment: Attachment? = null; private set
    var stencilAttachment: Attachment? = null; private set

    override var sizeX = -1; private set
    override var sizeY = -1; private set

    override fun create() {
        super<IGLObject>.create()
        id = glCreateFramebuffers()
    }

    fun attach(texture: TextureObject.Texture2D, attachment: Int, level: Int = 0) {
        updateAttachment(attachment, texture)
        glNamedFramebufferTexture(id, attachment, texture.id, level)
    }

    fun attachLayer(texture: TextureObject.Texture3D, attachment: Int, layer: Int, level: Int = 0) {
        updateAttachment(attachment, texture)
        glNamedFramebufferTextureLayer(id, attachment, texture.id, level, layer)
    }

    fun attach(renderbuffer: RenderbufferObject, attachment: Int) {
        updateAttachment(attachment, renderbuffer)
        glNamedFramebufferRenderbuffer(id, attachment, GL_RENDERBUFFER, renderbuffer.id)
    }

    private fun updateAttachment(attachment: Int, obj: Attachment) {
        tryCreate()
        when (attachment) {
            GL_DEPTH_ATTACHMENT -> {
                depthAttachment = obj
            }
            GL_STENCIL_ATTACHMENT -> {
                stencilAttachment = obj
            }
            GL_DEPTH_STENCIL_ATTACHMENT -> {
                depthAttachment = obj
                stencilAttachment = obj
            }
            in GL_COLOR_ATTACHMENT0..GL_COLOR_ATTACHMENT31 -> {
                colorAttachments[attachment - GL_COLOR_ATTACHMENT0] = obj
            }
            else -> {
                throw IllegalArgumentException("Invalid attachment: $attachment")
            }
        }
        checkAndUpdateSize(obj)
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
        checkCreated()
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
        if (id == 0) return

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

        sizeX = -1
        sizeY = -1
        id = 0
    }

    fun destroyFbo() {
        if (id == 0) return

        glDeleteFramebuffers(id)
        colorAttachments.fill(null)
        depthAttachment = null
        stencilAttachment = null

        sizeX = -1
        sizeY = -1
        id = 0
    }

    interface Attachment : IGLSized2D
}