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

    class Texture1D : TextureObject(GL_TEXTURE_1D), IGLSized1D {
        override var sizeX = 0; private set

        fun allocate(levels: Int, internalformat: Int, width: Int) {
            sizeX = width
            glTextureStorage1D(id, levels, internalformat, width)
        }

        fun upload(
            level: Int,
            xoffset: Int,
            width: Int,
            format: Int,
            type: Int,
            pixels: Ptr
        ) {
            glTextureSubImage1D(id, level, xoffset, width, format, type, pixels)
        }

        fun uploadCompressed(
            level: Int,
            xoffset: Int,
            width: Int,
            format: Int,
            imageSize: Int,
            pixels: Ptr
        ) {
            glCompressedTextureSubImage1D(id, level, xoffset, width, format, imageSize, pixels)
        }
    }

    class Texture2D : TextureObject(GL_TEXTURE_2D), IGLSized2D, FramebufferObject.Attachment {
        override var sizeX = 0; private set
        override var sizeY = 0; private set

        fun allocate(levels: Int, internalformat: Int, width: Int, height: Int) {
            sizeX = width
            sizeY = height
            glTextureStorage2D(id, levels, internalformat, width, height)
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
        ) {
            glTextureSubImage2D(id, level, xoffset, yoffset, width, height, format, type, pixels)
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
        ) {
            glCompressedTextureSubImage2D(id, level, xoffset, yoffset, width, height, format, imageSize, pixels)
        }
    }

    class Texture3D : TextureObject(GL_TEXTURE_3D), IGLSized3D {
        override var sizeX = 0; private set
        override var sizeY = 0; private set
        override var sizeZ = 0; private set

        fun allocate(levels: Int, internalformat: Int, width: Int, height: Int, depth: Int) {
            sizeX = width
            sizeY = height
            sizeZ = depth
            glTextureStorage3D(id, levels, internalformat, width, height, depth)
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
        ) {
            glTextureSubImage3D(id, level, xoffset, yoffset, zoffset, width, height, depth, format, type, pixels)
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
        ) {
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
        }
    }
}