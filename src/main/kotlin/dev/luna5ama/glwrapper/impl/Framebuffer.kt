package dev.luna5ama.glwrapper.impl

import dev.luna5ama.glwrapper.api.*
import it.unimi.dsi.fastutil.objects.ObjectArrayList

class Framebuffer : IGLObject, IGLBinding, IGLTargetBinding {
    override val id = glCreateFramebuffers()
    val colorAttachments = ObjectArrayList<IGLObject>()
    var depthAttachment: IGLObject? = null; private set
    var stencilAttachment: IGLObject? = null; private set

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
            else -> {
                colorAttachments.add(texture)
            }
        }
        glNamedFramebufferTexture(id, attachment, texture.id, level)
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
            else -> {
                colorAttachments.add(renderbuffer)
            }
        }
        glNamedFramebufferRenderbuffer(id, attachment, GL_RENDERBUFFER, renderbuffer.id)
    }

    fun check() {
        require(glCheckNamedFramebufferStatus(id, GL_FRAMEBUFFER) == GL_FRAMEBUFFER_COMPLETE)
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
            colorAttachments[i].destroy()
        }
        colorAttachments.clear()
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
        colorAttachments.clear()
        depthAttachment = null
        stencilAttachment = null
    }
}