package dev.luna5ama.glwrapper.impl

import dev.luna5ama.glwrapper.api.*
import dev.luna5ama.kmogus.MemoryStack
import dev.luna5ama.kmogus.Ptr

sealed class TextureObject private constructor(private val delegate: IGLObject.Impl, val target: Int) :
    IGLObject by delegate, IGLBinding, IGLSampler {
    constructor(target: Int) : this(IGLObject.Impl(GLObjectType.TEXTURE, target), target)

    var levels = 0; protected set
    var internalformat = 0; protected set

    override fun parameterf0(pname: Int, param: Float) {
        checkCreated()
        glTextureParameterf(id, pname, param)
    }

    override fun parameteri0(pname: Int, param: Int) {
        checkCreated()
        glTextureParameteri(id, pname, param)
    }

    override fun parameterfv0(pname: Int, v1: Float, v2: Float, v3: Float, v4: Float) {
        checkCreated()
        glTextureParameterfv(id, pname, v1, v2, v3, v4)
    }

    override fun parameteriv0(pname: Int, v1: Int, v2: Int, v3: Int, v4: Int) {
        checkCreated()
        glTextureParameteriv(id, pname, v1, v2, v3, v4)
    }

    fun generateMipmap() {
        checkCreated()
        glGenerateTextureMipmap(id)
    }

    override fun bind(unit: Int) {
        glBindTextureUnit(unit, id)
    }

    override fun unbind(unit: Int) {
        glBindTextureUnit(unit, 0)
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

    override fun destroy() {
        delegate.destroy()
        levels = 0
        internalformat = 0
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

        override fun destroy() {
            super.destroy()
            sizeX = 0
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

        override fun destroy() {
            super.destroy()
            sizeX = 0
            sizeY = 0
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