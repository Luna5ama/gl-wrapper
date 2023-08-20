package dev.luna5ama.glwrapper.impl

import dev.luna5ama.glwrapper.api.*
import dev.luna5ama.kmogus.Ptr

sealed class TextureObject(val target: Int) : IGLObject, IGLBinding, IGLTargetBinding {
    final override var id = glCreateTextures(target); private set
    var levels = 0; protected set
    var internalformat = 0; protected set

    override fun create() {
        super.create()
        id = glCreateTextures(target)
    }

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

    override fun destroy() {
        if (id != 0) {
            glDeleteTextures(id)
            id = 0
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