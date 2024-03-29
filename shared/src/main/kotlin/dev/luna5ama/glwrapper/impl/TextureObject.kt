package dev.luna5ama.glwrapper.impl

import dev.luna5ama.glwrapper.api.*
import dev.luna5ama.kmogus.MemoryStack
import dev.luna5ama.kmogus.Ptr

sealed class TextureObject private constructor(val target: Int, private val delegate: IGLObject.Impl) :
    IGLObject by delegate, IGLSampler {
    constructor(target: Int, type: GLObjectType.Texture) : this(target, IGLObject.Impl(type, target))

    var levels = 0; protected set
    var internalformat = 0; protected set

    override fun parameterf0(pname: Int, param: Float) {
        tryCreate()
        glTextureParameterf(id, pname, param)
    }

    override fun parameteri0(pname: Int, param: Int) {
        tryCreate()
        glTextureParameteri(id, pname, param)
    }

    override fun parameterfv0(pname: Int, v1: Float, v2: Float, v3: Float, v4: Float) {
        tryCreate()
        glTextureParameterfv(id, pname, v1, v2, v3, v4)
    }

    override fun parameteriv0(pname: Int, v1: Int, v2: Int, v3: Int, v4: Int) {
        tryCreate()
        glTextureParameteriv(id, pname, v1, v2, v3, v4)
    }

    fun generateMipmap() {
        checkCreated()
        glGenerateTextureMipmap(id)
    }

    fun bindTextureUnit(unit: Int) {
        checkCreated()
        glBindTextureUnit(unit, id)
    }

    fun unbindTextureUnit(unit: Int) {
        checkCreated()
        glBindTextureUnit(unit, 0)
    }

    fun bindImageUnit(unit: Int, level: Int, layered: Boolean, layer: Int, access: Int) {
        checkCreated()
        glBindImageTexture(unit, id, level, layered, layer, access, this.internalformat)
    }

    fun unbindImageUnit(unit: Int) {
        checkCreated()
        glBindImageTexture(unit, 0, 0, false, 0, GL_READ_WRITE, this.internalformat)
    }

    fun clearImage() {
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
        clearImage(clearFormat, clearType, Ptr.NULL)
    }

    fun clearImage(level: Int, format: Int, type: Int, data: Ptr) {
        checkCreated()
        glClearTexImage(id, level, format, type, data)
    }

    fun clearImage(format: Int, type: Int, data: Ptr) {
        checkCreated()
        repeat(levels) {
            clearImage(it, format, type, data)
        }
    }

    fun invalidate(level: Int) {
        checkCreated()
        glInvalidateTexImage(id, level)
    }

    fun invalidate() {
        checkCreated()
        repeat(levels) {
            glInvalidateTexImage(id, it)
        }
    }

    override fun destroy() {
        delegate.destroy()
        levels = 0
        internalformat = 0
    }

    class Texture1D(target: Int = GL_TEXTURE_1D, type: GLObjectType.Texture = GLObjectType.Texture.Storage) : TextureObject(target, type), IGLSized1D {
        override var sizeX = 0; private set

        fun allocate(levels: Int, internalformat: Int, width: Int): Texture1D {
            check(sizeX == 0) { "Texture already allocated" }
            tryCreate()
            this.levels = levels
            this.internalformat = internalformat
            sizeX = width
            glTextureStorage1D(id, levels, internalformat, width)
            clearImage()
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

        fun upload(
            level: Int,
            xoffset: Int,
            width: Int,
            format: Int,
            type: Int,
            buffer: BufferObject,
            offset: Long
        ): Texture1D {
            checkCreated()
            buffer.bind(GL_PIXEL_UNPACK_BUFFER)
            glTextureSubImage1D(id, level, xoffset, width, format, type, offset)
            buffer.unbind(GL_PIXEL_UNPACK_BUFFER)
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

        fun uploadCompressed(
            level: Int,
            xoffset: Int,
            width: Int,
            format: Int,
            imageSize: Int,
            buffer: BufferObject,
            offset: Long
        ): Texture1D {
            checkCreated()
            buffer.bind(GL_PIXEL_UNPACK_BUFFER)
            glCompressedTextureSubImage1D(id, level, xoffset, width, format, imageSize, offset)
            buffer.unbind(GL_PIXEL_UNPACK_BUFFER)
            return this
        }

        fun copyTo(
            dst: Texture1D,
            srcLevel: Int,
            srcX: Int,
            dstLevel: Int,
            dstX: Int,
            srcWidth: Int
        ) {
            require(dst.internalformat == internalformat) { "Internal format must be match" }
            checkCreated()
            glCopyImageSubData(
                id, target, srcLevel, srcX, 0, 0,
                dst.id, dst.target, dstLevel, dstX, 0, 0,
                srcWidth, 1, 1
            )
        }

        fun copyTo(dst: Texture1D, srcLevel: Int, dstLevel: Int) {
            require(dst.sizeBit == sizeBit) { "Size must be match" }
            copyTo(
                dst,
                srcLevel, 0,
                dstLevel, 0,
                sizeX
            )
        }

        fun copyTo(dst: Texture1D) {
            copyTo(dst, 0, 0)
        }

        fun invalidate(level: Int, xoffset: Int, width: Int) {
            checkCreated()
            glInvalidateTexSubImage(id, level, xoffset, 0, 0, width, 1, 1)
        }

        override fun destroy() {
            super.destroy()
            sizeX = 0
        }
    }

    class Texture2D(target: Int = GL_TEXTURE_2D, type: GLObjectType.Texture = GLObjectType.Texture.Storage) : TextureObject(target, type), IGLSized2D, FramebufferObject.Attachment {
        override var sizeX = 0; private set
        override var sizeY = 0; private set

        fun allocate(levels: Int, internalformat: Int, width: Int, height: Int): Texture2D {
            check(sizeX == 0 && sizeY == 0) { "Texture already allocated" }
            tryCreate()
            this.levels = levels
            this.internalformat = internalformat
            sizeX = width
            sizeY = height
            glTextureStorage2D(id, levels, internalformat, width, height)
            clearImage()
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

        fun upload(
            level: Int,
            xoffset: Int,
            yoffset: Int,
            width: Int,
            height: Int,
            format: Int,
            type: Int,
            buffer: BufferObject,
            offset: Long
        ): Texture2D {
            checkCreated()
            buffer.bind(GL_PIXEL_UNPACK_BUFFER)
            glTextureSubImage2D(id, level, xoffset, yoffset, width, height, format, type, offset)
            buffer.unbind(GL_PIXEL_UNPACK_BUFFER)
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

        fun uploadCompressed(
            level: Int,
            xoffset: Int,
            yoffset: Int,
            width: Int,
            height: Int,
            format: Int,
            imageSize: Int,
            buffer: BufferObject,
            offset: Long
        ): Texture2D {
            checkCreated()
            buffer.bind(GL_PIXEL_UNPACK_BUFFER)
            glCompressedTextureSubImage2D(id, level, xoffset, yoffset, width, height, format, imageSize, offset)
            buffer.unbind(GL_PIXEL_UNPACK_BUFFER)
            return this
        }

        fun copyTo(
            dst: Texture2D,
            srcLevel: Int,
            srcX: Int,
            srcY: Int,
            dstLevel: Int,
            dstX: Int,
            dstY: Int,
            srcWidth: Int,
            srcHeight: Int
        ) {
            require(dst.internalformat == internalformat) { "Internal format must be match" }
            checkCreated()
            glCopyImageSubData(
                id, target, srcLevel, srcX, srcY, 0,
                dst.id, dst.target, dstLevel, dstX, dstY, 0,
                srcWidth, srcHeight, 1
            )
        }

        fun copyTo(dst: Texture2D, srcLevel: Int, dstLevel: Int) {
            require(dst.sizeBit == sizeBit) { "Size must be match" }
            copyTo(
                dst,
                srcLevel, 0, 0,
                dstLevel, 0, 0,
                sizeX, sizeY,
            )
        }

        fun copyTo(dst: Texture2D) {
            copyTo(dst, 0, 0)
        }

        fun invalidate(level: Int, xoffset: Int, yoffset: Int, width: Int, height: Int) {
            checkCreated()
            glInvalidateTexSubImage(id, level, xoffset, yoffset, 0, width, height, 1)
        }

        override fun destroy() {
            super.destroy()
            sizeX = 0
            sizeY = 0
        }
    }

    class Texture3D(target: Int = GL_TEXTURE_3D, type: GLObjectType.Texture = GLObjectType.Texture.Storage) : TextureObject(target, type), IGLSized3D, FramebufferObject.Attachment {
        override var sizeX = 0; private set
        override var sizeY = 0; private set
        override var sizeZ = 0; private set

        fun allocate(levels: Int, internalformat: Int, width: Int, height: Int, depth: Int): Texture3D {
            check(sizeX == 0 && sizeY == 0 && sizeZ == 0) { "Texture already allocated" }
            tryCreate()
            this.levels = levels
            this.internalformat = internalformat
            sizeX = width
            sizeY = height
            sizeZ = depth
            glTextureStorage3D(id, levels, internalformat, width, height, depth)
            clearImage()
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
            buffer: BufferObject,
            offset: Long
        ): Texture3D {
            checkCreated()
            buffer.bind(GL_PIXEL_UNPACK_BUFFER)
            glTextureSubImage3D(id, level, xoffset, yoffset, zoffset, width, height, depth, format, type, offset)
            buffer.unbind(GL_PIXEL_UNPACK_BUFFER)
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
            buffer: BufferObject,
            offset: Long
        ): Texture3D {
            checkCreated()
            buffer.bind(GL_PIXEL_UNPACK_BUFFER)
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
                offset
            )
            buffer.unbind(GL_PIXEL_UNPACK_BUFFER)
            return this
        }

        fun copyTo(
            dst: Texture3D,
            srcLevel: Int,
            srcX: Int,
            srcY: Int,
            srcZ: Int,
            dstLevel: Int,
            dstX: Int,
            dstY: Int,
            dstZ: Int,
            srcWidth: Int,
            srcHeight: Int,
            srcDepth: Int
        ) {
            require(dst.internalformat == internalformat) { "Internal format must be match" }
            checkCreated()
            glCopyImageSubData(
                id, target, srcLevel, srcX, srcY, srcZ,
                dst.id, dst.target, dstLevel, dstX, dstY, dstZ,
                srcWidth, srcHeight, srcDepth
            )
        }

        fun copyTo(dst: Texture3D, srcLevel: Int, dstLevel: Int) {
            require(dst.sizeBit == sizeBit) { "Size must be match" }
            copyTo(
                dst,
                srcLevel, 0, 0, 0,
                dstLevel, 0, 0, 0,
                sizeX, sizeY, sizeZ
            )
        }

        fun copyTo(dst: Texture3D) {
            copyTo(dst, 0, 0)
        }

        fun invalidate(level: Int, xoffset: Int, yoffset: Int, zoffset: Int, width: Int, height: Int, depth: Int) {
            checkCreated()
            glInvalidateTexSubImage(id, level, xoffset, yoffset, zoffset, width, height, depth)
        }

        override fun destroy() {
            super.destroy()
            sizeX = 0
            sizeY = 0
            sizeZ = 0
        }
    }

    companion object {
        fun bindTextures(firstUnit: Int, t1: TextureObject, t2: TextureObject) {
            MemoryStack {
                val arr = malloc(2 * 4L)
                val ptr = arr.ptr
                ptr.setIntInc(t1.id)
                    .setIntInc(t2.id)
                glBindTextures(firstUnit, 2, ptr)
            }
        }

        fun bindTextures(firstUnit: Int, t1: TextureObject, t2: TextureObject, t3: TextureObject) {
            MemoryStack {
                val arr = malloc(3 * 4L)
                val ptr = arr.ptr
                ptr.setIntInc(t1.id)
                    .setIntInc(t2.id)
                    .setIntInc(t3.id)
                glBindTextures(firstUnit, 3, ptr)
            }
        }

        fun bindTextures(firstUnit: Int, t1: TextureObject, t2: TextureObject, t3: TextureObject, t4: TextureObject) {
            MemoryStack {
                val arr = malloc(4 * 4L)
                val ptr = arr.ptr
                ptr.setIntInc(t1.id)
                    .setIntInc(t2.id)
                    .setIntInc(t3.id)
                    .setIntInc(t4.id)
                glBindTextures(firstUnit, 4, ptr)
            }
        }

        fun bindTextures(
            firstUnit: Int,
            t1: TextureObject,
            t2: TextureObject,
            t3: TextureObject,
            t4: TextureObject,
            t5: TextureObject
        ) {
            MemoryStack {
                val arr = malloc(5 * 4L)
                val ptr = arr.ptr
                ptr.setIntInc(t1.id)
                    .setIntInc(t2.id)
                    .setIntInc(t3.id)
                    .setIntInc(t4.id)
                    .setIntInc(t5.id)
                glBindTextures(firstUnit, 5, ptr)
            }
        }

        fun bindTextures(
            firstUnit: Int,
            t1: TextureObject,
            t2: TextureObject,
            t3: TextureObject,
            t4: TextureObject,
            t5: TextureObject,
            t6: TextureObject
        ) {
            MemoryStack {
                val arr = malloc(6 * 4L)
                val ptr = arr.ptr
                ptr.setIntInc(t1.id)
                    .setIntInc(t2.id)
                    .setIntInc(t3.id)
                    .setIntInc(t4.id)
                    .setIntInc(t5.id)
                    .setIntInc(t6.id)
                glBindTextures(firstUnit, 6, ptr)
            }
        }

        fun bindTextures(
            firstUnit: Int,
            t1: TextureObject,
            t2: TextureObject,
            t3: TextureObject,
            t4: TextureObject,
            t5: TextureObject,
            t6: TextureObject,
            t7: TextureObject
        ) {
            MemoryStack {
                val arr = malloc(7 * 4L)
                val ptr = arr.ptr
                ptr.setIntInc(t1.id)
                    .setIntInc(t2.id)
                    .setIntInc(t3.id)
                    .setIntInc(t4.id)
                    .setIntInc(t5.id)
                    .setIntInc(t6.id)
                    .setIntInc(t7.id)
                glBindTextures(firstUnit, 7, ptr)
            }
        }

        fun bindTextures(
            firstUnit: Int,
            t1: TextureObject,
            t2: TextureObject,
            t3: TextureObject,
            t4: TextureObject,
            t5: TextureObject,
            t6: TextureObject,
            t7: TextureObject,
            t8: TextureObject
        ) {
            MemoryStack {
                val arr = malloc(8 * 4L)
                val ptr = arr.ptr
                ptr.setIntInc(t1.id)
                    .setIntInc(t2.id)
                    .setIntInc(t3.id)
                    .setIntInc(t4.id)
                    .setIntInc(t5.id)
                    .setIntInc(t6.id)
                    .setIntInc(t7.id)
                    .setIntInc(t8.id)
                glBindTextures(firstUnit, 8, ptr)
            }
        }

        fun bindTextures(firstUnit: Int, vararg textures: TextureObject) {
            MemoryStack {
                val arr = malloc(textures.size * 4L)
                var ptr = arr.ptr
                textures.forEach { ptr = ptr.setIntInc(it.id) }
                glBindTextures(firstUnit, textures.size, ptr)
            }
        }

        fun bindTextures(firstUnit: Int, textures: Collection<TextureObject>) {
            MemoryStack {
                val arr = malloc(textures.size * 4L)
                var ptr = arr.ptr
                textures.forEach { ptr = ptr.setIntInc(it.id) }
                glBindTextures(firstUnit, textures.size, ptr)
            }
        }
    }
}