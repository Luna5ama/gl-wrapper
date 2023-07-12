package dev.luna5ama.glwrapper.impl

import dev.luna5ama.glwrapper.api.*
import dev.luna5ama.kmogus.Ptr

sealed class TextureObject(val target: Int) : IGLObject, IGLBinding, IGLTargetBinding {
    override val id = glCreateTextures(target)

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
        glDeleteTextures(id)
    }

    class Texture1D(target: Int = GL_TEXTURE_1D) : TextureObject(target), IGLSized1D {
        override var sizeX = 0; private set

        fun allocate(levels: Int, internalformat: Int, width: Int): Texture1D {
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
            glCompressedTextureSubImage1D(id, level, xoffset, width, format, imageSize, pixels)
            return this
        }
    }

    class Texture2D(target: Int = GL_TEXTURE_2D) : TextureObject(target), IGLSized2D, FramebufferObject.Attachment {
        override var sizeX = 0; private set
        override var sizeY = 0; private set

        fun allocate(levels: Int, internalformat: Int, width: Int, height: Int): Texture2D {
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
            glCompressedTextureSubImage2D(id, level, xoffset, yoffset, width, height, format, imageSize, pixels)
            return this
        }
    }

    class Texture3D(target: Int = GL_TEXTURE_3D) : TextureObject(target), IGLSized3D, FramebufferObject.Attachment {
        override var sizeX = 0; private set
        override var sizeY = 0; private set
        override var sizeZ = 0; private set

        fun allocate(levels: Int, internalformat: Int, width: Int, height: Int, depth: Int): Texture3D {
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