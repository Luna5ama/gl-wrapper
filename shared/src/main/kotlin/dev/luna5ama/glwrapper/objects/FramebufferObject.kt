package dev.luna5ama.glwrapper.objects

import dev.luna5ama.glwrapper.Config
import dev.luna5ama.glwrapper.base.*
import dev.luna5ama.glwrapper.enums.GLObjectType
import dev.luna5ama.kmogus.MemoryStack

abstract class AbstractFramebufferObject private constructor(protected val delegate: IGLObject.Impl) : IGLObject by delegate, IGLBinding,
    IGLTargetBinding, IGLSized2D {
    constructor() : this(IGLObject.Impl(GLObjectType.Framebuffer))

    final override var sizeX = -1; protected set
    final override var sizeY = -1; protected set

    abstract fun destroyFbo()

    abstract fun check()

    fun parameteri(pname: Int, param: Int) {
        glNamedFramebufferParameteri(id, pname, param)
    }

    override fun bind() {
        bind(GL_FRAMEBUFFER)
    }

    override fun unbind() {
        unbind(GL_FRAMEBUFFER)
    }

    override fun bind(target: Int) {
        check()
        glBindFramebuffer(target, id)
    }

    override fun unbind(target: Int) {
        check()
        glBindFramebuffer(target, 0)
    }
}

class FramebufferObject : AbstractFramebufferObject() {
    override fun destroy() {
        if (delegate.id0 == 0) return

        for (i in colorAttachments.indices) {
            colorAttachments[i]?.destroy()
        }

        val depth = depthAttachment
        val stencil = stencilAttachment

        depth?.destroy()

        if (stencil != null && stencil !== depth) {
            stencil.destroy()
        }

        destroyFbo()
    }

    override fun destroyFbo() {
        if (delegate.id0 == 0) return

        colorAttachments.fill(null)
        depthAttachment = null
        stencilAttachment = null

        sizeX = -1
        sizeY = -1

        delegate.destroy()
    }
    
    val colorAttachments = arrayOfNulls<Attachment>(32)
    var depthAttachment: Attachment? = null; private set
    var stencilAttachment: Attachment? = null; private set
    
    fun attach(texture: LayeredAttachment, attachment: Int, level: Int = 0) {
        updateAttachment(attachment, texture)
        glNamedFramebufferTexture(id, attachment, texture.id, level)
    }

    fun attach(texture: LayeredAttachment, attachment: Int, layer: Int, level: Int = 0) {
        updateAttachment(attachment, texture)
        glNamedFramebufferTextureLayer(id, attachment, texture.id, level, layer)
    }

    fun attach(texture: NonLayeredAttachment, attachment: Int, level: Int = 0) {
        updateAttachment(attachment, texture)
        glNamedFramebufferTexture(id, attachment, texture.id, level)
    }

    fun setReadBuffer(attachment: Int) {
        require(attachment in GL_COLOR_ATTACHMENT0..GL_COLOR_ATTACHMENT31) { "Invalid attachment: $attachment" }
        val index = attachment - GL_COLOR_ATTACHMENT0
        require(colorAttachments[index] != null) { "Attachment not set: $index" }
        glNamedFramebufferReadBuffer(id, attachment)
    }

    fun setReadBufferNone() {
        glNamedFramebufferReadBuffer(id, GL_NONE)
    }

    fun setDrawBuffer(attachment: Int) {
        require(attachment in GL_COLOR_ATTACHMENT0..GL_COLOR_ATTACHMENT31) { "Invalid attachment: $attachment" }
        val index = attachment - GL_COLOR_ATTACHMENT0
        require(colorAttachments[index] != null) { "Attachment not set: $index" }
        glNamedFramebufferDrawBuffer(id, attachment)
    }

    fun setDrawBuffer(vararg attachments: Int) {
        attachments.forEach {
            require(it in GL_COLOR_ATTACHMENT0..GL_COLOR_ATTACHMENT31) { "Invalid attachment: $it" }
            val index = it - GL_COLOR_ATTACHMENT0
            require(colorAttachments[index] != null) { "Attachment not set: $index" }
        }
        MemoryStack {
            val arr = malloc(attachments.size * 4L)
            val ptr = arr.ptr
            for (i in attachments.indices) {
                ptr.setInt(i * 4L, attachments[i])
            }
            glNamedFramebufferDrawBuffers(id, attachments.size, ptr)
        }
    }

    fun setDrawBufferAll() {
        MemoryStack {
            val arr = malloc(colorAttachments.size * 4L)
            var ptr = arr.ptr
            var n = 0
            for (i in colorAttachments.indices) {
                if (colorAttachments[i] == null) continue
                ptr = ptr.setIntInc(GL_COLOR_ATTACHMENT0 + i)
                n++
            }
            glNamedFramebufferDrawBuffers(id, n, arr.ptr)
        }
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
        val aSizeX = attachment.sizeX
        val aSizeY = (attachment as? IGLSized2D)?.sizeY ?: 1
        if (sizeX != -1 && sizeY != -1) {
            require(sizeX == aSizeX && sizeY == aSizeY) {
                "Framebuffer attachments size mismatch: $sizeX x $sizeY != $aSizeX x $aSizeY"
            }
            return
        }

        sizeX = aSizeX
        sizeY = aSizeY
    }

    override fun check() {
        if (!Config.checks) return
        checkCreated()
        if (colorAttachments.all { it == null } && depthAttachment == null && stencilAttachment == null) {
            throw IllegalStateException("Framebuffer has no attachments")
        }
        if (sizeX == -1 || sizeY == -1) {
            throw IllegalStateException("Framebuffer size not set")
        }
        val status = glCheckNamedFramebufferStatus(id, GL_FRAMEBUFFER)
        check(status == GL_FRAMEBUFFER_COMPLETE) {
            val statusStr = when (status) {
                GL_FRAMEBUFFER_COMPLETE -> "GL_FRAMEBUFFER_COMPLETE"
                GL_FRAMEBUFFER_UNDEFINED -> "GL_FRAMEBUFFER_UNDEFINED"
                GL_FRAMEBUFFER_INCOMPLETE_ATTACHMENT -> "GL_FRAMEBUFFER_INCOMPLETE_ATTACHMENT"
                GL_FRAMEBUFFER_INCOMPLETE_MISSING_ATTACHMENT -> "GL_FRAMEBUFFER_INCOMPLETE_MISSING_ATTACHMENT"
                GL_FRAMEBUFFER_INCOMPLETE_DRAW_BUFFER -> "GL_FRAMEBUFFER_INCOMPLETE_DRAW_BUFFER"
                GL_FRAMEBUFFER_INCOMPLETE_READ_BUFFER -> "GL_FRAMEBUFFER_INCOMPLETE_READ_BUFFER"
                GL_FRAMEBUFFER_UNSUPPORTED -> "GL_FRAMEBUFFER_UNSUPPORTED"
                GL_FRAMEBUFFER_INCOMPLETE_MULTISAMPLE -> "GL_FRAMEBUFFER_INCOMPLETE_MULTISAMPLE"
                GL_FRAMEBUFFER_INCOMPLETE_LAYER_TARGETS -> "GL_FRAMEBUFFER_INCOMPLETE_LAYER_TARGETS"
                else -> "Unknown"
            }
            "Framebuffer is not complete: $statusStr"
        }
    }

    fun clearAttachments() {
        colorAttachments.fill(null)
        depthAttachment = null
        stencilAttachment = null
        sizeX = -1
        sizeY = -1
    }

    fun clearColor(attachment: Int, red: Float, green: Float, blue: Float, alpha: Float) {
        glClearNamedFramebufferf(id, GL_COLOR, attachment, red, green, blue, alpha)
    }

    fun clearDepth(depth: Float) {
        glClearNamedFramebufferf(id, GL_DEPTH, 0, depth)
    }

    fun clearStencil(stencil: Int) {
        glClearNamedFramebufferi(id, GL_STENCIL, 0, stencil)
    }

    fun clearDepthStencil(depth: Float, stencil: Int) {
        glClearNamedFramebufferfi(id, GL_DEPTH_STENCIL, 0, depth, stencil)
    }

    fun blitTo(
        target: FramebufferObject,
        mask: Int, filter: Int
    ) {
        glBlitNamedFramebuffer(
            id, target.id,
            0, 0, sizeX, sizeY,
            0, 0, target.sizeX, target.sizeY,
            mask, filter
        )
    }

    fun blitTo(
        target: FramebufferObject,
        mask: Int, filter: Int,
        srcX0: Int, srcY0: Int, srcX1: Int, srcY1: Int,
        dstX0: Int, dstY0: Int, dstX1: Int, dstY1: Int
    ) {
        glBlitNamedFramebuffer(
            id, target.id,
            srcX0, srcY0, srcX1, srcY1,
            dstX0, dstY0, dstX1, dstY1,
            mask, filter
        )
    }

    class Empty : AbstractFramebufferObject() {
        override fun destroy() {
            destroyFbo()
        }

        override fun destroyFbo() {
            sizeX = -1
            sizeY = -1

            delegate.destroy()
        }

        override fun check() {
            if (!Config.checks) return
            checkCreated()
            if (sizeX == -1 || sizeY == -1) {
                throw IllegalStateException("Framebuffer size not set")
            }
        }

        fun resize(sizeX: Int, sizeY: Int, layers: Int = 0, samples: Int = 0) {
            require(sizeX > 0 && sizeY > 0) { "Invalid size: $sizeX x $sizeY" }
            this.sizeX = sizeX
            this.sizeY = sizeY

            parameteri(GL_FRAMEBUFFER_DEFAULT_WIDTH, sizeX)
            parameteri(GL_FRAMEBUFFER_DEFAULT_HEIGHT, sizeY)
            parameteri(GL_FRAMEBUFFER_DEFAULT_LAYERS, layers)
            parameteri(GL_FRAMEBUFFER_DEFAULT_SAMPLES, samples)
        }
    }

    sealed interface Attachment : IGLObject, IGLSized1D
    sealed interface LayeredAttachment : Attachment {
        val layers: Int
    }

    sealed interface NonLayeredAttachment : Attachment
}