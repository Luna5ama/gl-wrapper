package dev.luna5ama.glwrapper.impl

import dev.luna5ama.glwrapper.api.*
import dev.luna5ama.kmogus.Ptr

sealed class TextureObject(val target: Int) : IGLObject by IGLObject.Impl(GLObjectType.TEXTURE, target), IGLBinding, IGLTargetBinding {
    var levels = 0; protected set
    var internalformat = 0; protected set

    override fun bind(target: Int) {
        glBindTextureUnit(target, id)
    }

    override fun unbind(target: Int) {
        glBindTextureUnit(target, 0)
    }

    override fun bind() {
        bind(target)
    }

    override fun unbind() {
        unbind(target)
    }

    fun clear() {
        checkCreated()
        val clearFormat = when (internalformat) {
            GL_DEPTH_COMPONENT,
            GL_DEPTH_COMPONENT16,
            GL_DEPTH_COMPONENT24,
            GL_DEPTH_COMPONENT32,
            GL_DEPTH_COMPONENT32F -> GL_DEPTH_COMPONENT
            GL_DEPTH_STENCIL,
            GL_DEPTH24_STENCIL8,
            GL_DEPTH32F_STENCIL8 -> GL_DEPTH_STENCIL
            else -> GL_RGBA
        }
        val clearType = when (internalformat) {
            GL_DEPTH_COMPONENT,
            GL_DEPTH_COMPONENT16,
            GL_DEPTH_COMPONENT24,
            GL_DEPTH_COMPONENT32,
            GL_DEPTH_COMPONENT32F -> GL_FLOAT
            GL_DEPTH_STENCIL,
            GL_DEPTH24_STENCIL8 -> GL_UNSIGNED_INT_24_8
            GL_DEPTH32F_STENCIL8 -> GL_FLOAT_32_UNSIGNED_INT_24_8_REV
            GL_R16F,
            GL_RG16F,
            GL_RGB16F,
            GL_RGBA16F,
            GL_R32F,
            GL_RG32F,
            GL_RGB32F,
            GL_RGBA32F -> GL_FLOAT
            else -> GL_UNSIGNED_BYTE
        }
        repeat(levels) {
            glClearTexImage(id, it, clearFormat, clearType, Ptr.NULL)
        }
    }

    class Texture1D(target: Int = GL_TEXTURE_1D) : TextureObject(target), IGLSized1D {
        override var sizeX = 0; private set

        fun allocate(levels: Int, internalformat: Int, width: Int): Texture1D {
            tryCreate()
            this.levels = levels
            this.internalformat = internalformat
            sizeX = width
            glTextureStorage1D(id, levels, internalformat, width)
            clear()
            return this
        }

        fun upload(
            level: Int,
            xoffset: Int,
            width: Int,
            format: Int,
            type: Int,
            pixels: Ptr
        ): Texture1D {
            checkCreated()
            glTextureSubImage1D(id, level, xoffset, width, format, type, pixels)
            return this
        }

        fun uploadCompressed(
            level: Int,
            xoffset: Int,
            width: Int,
            format: Int,
            imageSize: Int,
            pixels: Ptr
        ): Texture1D {
            checkCreated()
            glCompressedTextureSubImage1D(id, level, xoffset, width, format, imageSize, pixels)
            return this
        }
    }

    class Texture2D(target: Int = GL_TEXTURE_2D) : TextureObject(target), IGLSized2D, FramebufferObject.Attachment {
        override var sizeX = 0; private set
        override var sizeY = 0; private set

        fun allocate(levels: Int, internalformat: Int, width: Int, height: Int): Texture2D {
            tryCreate()
            this.levels = levels
            this.internalformat = internalformat
            sizeX = width
            sizeY = height
            glTextureStorage2D(id, levels, internalformat, width, height)
            clear()
            return this
        }

        fun upload(
            level: Int,
            xoffset: Int,
            yoffset: Int,
            width: Int,
            height: Int,
            format: Int,
            type: Int,
            pixels: Ptr
        ): Texture2D {
            checkCreated()
            glTextureSubImage2D(id, level, xoffset, yoffset, width, height, format, type, pixels)
            return this
        }

        fun uploadCompressed(
            level: Int,
            xoffset: Int,
            yoffset: Int,
            width: Int,
            height: Int,
            format: Int,
            imageSize: Int,
            pixels: Ptr
        ): Texture2D {
            checkCreated()
            glCompressedTextureSubImage2D(id, level, xoffset, yoffset, width, height, format, imageSize, pixels)
            return this
        }
    }

    class Texture3D(target: Int = GL_TEXTURE_3D) : TextureObject(target), IGLSized3D, FramebufferObject.Attachment {
        override var sizeX = 0; private set
        override var sizeY = 0; private set
        override var sizeZ = 0; private set

        fun allocate(levels: Int, internalformat: Int, width: Int, height: Int, depth: Int): Texture3D {
            tryCreate()
            this.levels = levels
            this.internalformat = internalformat
            sizeX = width
            sizeY = height
            sizeZ = depth
            glTextureStorage3D(id, levels, internalformat, width, height, depth)
            clear()
            return this
        }

        fun upload(
            level: Int,
            xoffset: Int,
            yoffset: Int,
            zoffset: Int,
            width: Int,
            height: Int,
            depth: Int,
            format: Int,
            type: Int,
            pixels: Ptr
        ): Texture3D {
            checkCreated()
            glTextureSubImage3D(id, level, xoffset, yoffset, zoffset, width, height, depth, format, type, pixels)
            return this
        }

        fun uploadCompressed(
            level: Int,
            xoffset: Int,
            yoffset: Int,
            zoffset: Int,
            width: Int,
            height: Int,
            depth: Int,
            format: Int,
            imageSize: Int,
            pixels: Ptr
        ): Texture3D {
            checkCreated()
            glCompressedTextureSubImage3D(
                id,
                level,
                xoffset,
                yoffset,
                zoffset,
                width,
                height,
                depth,
                format,
                imageSize,
                pixels
            )
            return this
        }
    }
}