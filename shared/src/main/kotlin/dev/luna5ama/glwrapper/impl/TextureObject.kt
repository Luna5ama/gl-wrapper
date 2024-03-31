package dev.luna5ama.glwrapper.impl

import dev.luna5ama.glwrapper.api.*
import dev.luna5ama.kmogus.MemoryStack
import dev.luna5ama.kmogus.Ptr

@Suppress("FunctionName")
sealed class TextureObject private constructor(val target: Int, private val delegate: IGLObject.Impl) :
    IGLObject by delegate, IGLSampler, FramebufferObject.Attachment {
    constructor(type: GLObjectType.Texture = GLObjectType.Texture.Storage, target: Int) : this(
        target,
        IGLObject.Impl(type, target)
    )

    var levels = -1; protected set
    var internalformat = -1; protected set
    
    protected open fun reset() {
        levels = 0
        internalformat = 0
    }

    final override fun destroy() {
        delegate.destroy()
        reset()
    }

    fun textureView(
        origtexture: TextureObject,
        internalformat: Int,
        minlevel: Int,
        numlevels: Int,
        minlayer: Int,
        numlayers: Int
    ) {
        tryCreate()
        require(origtexture.levels != -1) { "Original texture must be allocated" }
        glTextureView(id, target, origtexture.id, internalformat, minlevel, numlevels, minlayer, numlayers)
        levels = numlevels
        this.internalformat = internalformat
    }

    override fun parameterf(pname: Int, param: Float) {
        tryCreate()
        glTextureParameterf(id, pname, param)
    }

    override fun parameteri(pname: Int, param: Int) {
        tryCreate()
        glTextureParameteri(id, pname, param)
    }

    override fun parameterfv(pname: Int, v1: Float, v2: Float, v3: Float, v4: Float) {
        tryCreate()
        glTextureParameterfv(id, pname, v1, v2, v3, v4)
    }

    override fun parameteriv(pname: Int, v1: Int, v2: Int, v3: Int, v4: Int) {
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

    sealed interface ArrayTexture : FramebufferObject.LayeredAttachment {
        val layers: Int
    }

    sealed interface CubemapTexture : FramebufferObject.LayeredAttachment

    sealed interface RegularTexture : FramebufferObject.Attachment

    sealed class Tex1D private constructor(type: GLObjectType.Texture, target: Int) : TextureObject(type, target),
        IGLSized1D {
        final override var sizeX = 0; private set

        override fun reset() {
            super.reset()
            sizeX = 0
        }

        fun allocate(levels: Int, internalformat: Int, width: Int) {
            check(sizeX == 0) { "Texture already allocated" }
            tryCreate()
            this.levels = levels
            this.internalformat = internalformat
            sizeX = width
            glTextureStorage1D(id, levels, internalformat, width)
            clearImage()
        }

        fun upload(
            level: Int,
            xoffset: Int,
            width: Int,
            format: Int,
            type: Int,
            pixels: Ptr
        ) {
            checkCreated()
            glTextureSubImage1D(id, level, xoffset, width, format, type, pixels)
        }

        fun upload(
            level: Int,
            xoffset: Int,
            width: Int,
            format: Int,
            type: Int,
            buffer: BufferObject,
            offset: Long
        ) {
            checkCreated()
            buffer.bind(GL_PIXEL_UNPACK_BUFFER)
            glTextureSubImage1D(id, level, xoffset, width, format, type, offset)
            buffer.unbind(GL_PIXEL_UNPACK_BUFFER)
        }

        fun uploadCompressed(
            level: Int,
            xoffset: Int,
            width: Int,
            format: Int,
            imageSize: Int,
            pixels: Ptr
        ) {
            checkCreated()
            glCompressedTextureSubImage1D(id, level, xoffset, width, format, imageSize, pixels)
        }

        fun uploadCompressed(
            level: Int,
            xoffset: Int,
            width: Int,
            format: Int,
            imageSize: Int,
            buffer: BufferObject,
            offset: Long
        ) {
            checkCreated()
            buffer.bind(GL_PIXEL_UNPACK_BUFFER)
            glCompressedTextureSubImage1D(id, level, xoffset, width, format, imageSize, offset)
            buffer.unbind(GL_PIXEL_UNPACK_BUFFER)
        }

        fun copyTo(
            dst: Tex1D,
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

        fun copyTo(dst: Tex1D, srcLevel: Int, dstLevel: Int) {
            require(dst.sizeBit == sizeBit) { "Size must be match" }
            copyTo(
                dst,
                srcLevel, 0,
                dstLevel, 0,
                sizeX
            )
        }

        fun copyTo(dst: Tex1D) {
            copyTo(dst, 0, 0)
        }

        fun invalidate(level: Int, xoffset: Int, width: Int) {
            checkCreated()
            glInvalidateTexSubImage(id, level, xoffset, 0, 0, width, 1, 1)
        }

        class Texture1D(type: GLObjectType.Texture = GLObjectType.Texture.Storage) : Tex1D(type, GL_TEXTURE_1D),
            RegularTexture, FramebufferObject.NonLayeredAttachment
    }

    sealed class Tex2D private constructor(type: GLObjectType.Texture, target: Int) : TextureObject(type, target),
        IGLSized2D {
        final override var sizeX = 0; private set
        final override var sizeY = 0; private set

        override fun reset() {
            super.reset()
            sizeX = 0
            sizeY = 0
        }

        fun allocate(levels: Int, internalformat: Int, width: Int, height: Int) {
            check(sizeX == 0 && sizeY == 0) { "Texture already allocated" }
            tryCreate()
            this.levels = levels
            this.internalformat = internalformat
            sizeX = width
            sizeY = height
            glTextureStorage2D(id, levels, internalformat, width, height)
            clearImage()
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
            checkCreated()
            glTextureSubImage2D(id, level, xoffset, yoffset, width, height, format, type, pixels)
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
        ) {
            checkCreated()
            buffer.bind(GL_PIXEL_UNPACK_BUFFER)
            glTextureSubImage2D(id, level, xoffset, yoffset, width, height, format, type, offset)
            buffer.unbind(GL_PIXEL_UNPACK_BUFFER)
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
            checkCreated()
            glCompressedTextureSubImage2D(id, level, xoffset, yoffset, width, height, format, imageSize, pixels)
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
        ) {
            checkCreated()
            buffer.bind(GL_PIXEL_UNPACK_BUFFER)
            glCompressedTextureSubImage2D(id, level, xoffset, yoffset, width, height, format, imageSize, offset)
            buffer.unbind(GL_PIXEL_UNPACK_BUFFER)
        }

        fun copyTo(
            dst: Tex2D,
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

        fun copyTo(dst: Tex2D, srcLevel: Int, dstLevel: Int) {
            require(dst.sizeBit == sizeBit) { "Size must be match" }
            copyTo(
                dst,
                srcLevel, 0, 0,
                dstLevel, 0, 0,
                sizeX, sizeY,
            )
        }

        fun copyTo(dst: Tex2D) {
            copyTo(dst, 0, 0)
        }

        fun invalidate(level: Int, xoffset: Int, yoffset: Int, width: Int, height: Int) {
            checkCreated()
            glInvalidateTexSubImage(id, level, xoffset, yoffset, 0, width, height, 1)
        }

        class Texture2D(type: GLObjectType.Texture = GLObjectType.Texture.Storage) : Tex2D(type, GL_TEXTURE_2D),
            RegularTexture, FramebufferObject.NonLayeredAttachment

        class Texture1DArray(type: GLObjectType.Texture = GLObjectType.Texture.Storage) :
            Tex2D(type, GL_TEXTURE_1D_ARRAY), ArrayTexture {
            override val layers: Int
                get() = sizeY
        }

        class TextureCubemap(type: GLObjectType.Texture = GLObjectType.Texture.Storage) :
            Tex2D(type, GL_TEXTURE_CUBE_MAP), CubemapTexture
    }

    sealed class Tex3D private constructor(type: GLObjectType.Texture, target: Int) : TextureObject(type, target),
        IGLSized3D {
        final override var sizeX = 0; private set
        final override var sizeY = 0; private set
        final override var sizeZ = 0; private set

        override fun reset() {
            super.reset()
            sizeX = 0
            sizeY = 0
            sizeZ = 0
        }

        fun allocate(levels: Int, internalformat: Int, width: Int, height: Int, depth: Int) {
            check(sizeX == 0 && sizeY == 0 && sizeZ == 0) { "Texture already allocated" }
            tryCreate()
            this.levels = levels
            this.internalformat = internalformat
            sizeX = width
            sizeY = height
            sizeZ = depth
            glTextureStorage3D(id, levels, internalformat, width, height, depth)
            clearImage()
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
            checkCreated()
            glTextureSubImage3D(id, level, xoffset, yoffset, zoffset, width, height, depth, format, type, pixels)
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
        ) {
            checkCreated()
            buffer.bind(GL_PIXEL_UNPACK_BUFFER)
            glTextureSubImage3D(id, level, xoffset, yoffset, zoffset, width, height, depth, format, type, offset)
            buffer.unbind(GL_PIXEL_UNPACK_BUFFER)
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
        ) {
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
        }

        fun copyTo(
            dst: Tex3D,
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

        fun copyTo(dst: Tex3D, srcLevel: Int, dstLevel: Int) {
            require(dst.sizeBit == sizeBit) { "Size must be match" }
            copyTo(
                dst,
                srcLevel, 0, 0, 0,
                dstLevel, 0, 0, 0,
                sizeX, sizeY, sizeZ
            )
        }

        fun copyTo(dst: Tex3D) {
            copyTo(dst, 0, 0)
        }

        fun invalidate(level: Int, xoffset: Int, yoffset: Int, zoffset: Int, width: Int, height: Int, depth: Int) {
            checkCreated()
            glInvalidateTexSubImage(id, level, xoffset, yoffset, zoffset, width, height, depth)
        }

        class Texture3D(type: GLObjectType.Texture = GLObjectType.Texture.Storage) : Tex3D(type, GL_TEXTURE_3D),
            RegularTexture, FramebufferObject.LayeredAttachment

        class Texture2DArray(type: GLObjectType.Texture = GLObjectType.Texture.Storage) :
            Tex3D(type, GL_TEXTURE_2D_ARRAY), ArrayTexture {
            override val layers: Int
                get() = sizeZ
        }

        class TextureCubemapArray(type: GLObjectType.Texture = GLObjectType.Texture.Storage) :
            Tex3D(type, GL_TEXTURE_CUBE_MAP_ARRAY), CubemapTexture, ArrayTexture {
            override val layers: Int
                get() = sizeZ
        }
    }
}


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