package dev.luna5ama.glwrapper.api

import dev.luna5ama.kmogus.Arr
import org.lwjgl.PointerBuffer
import org.lwjgl.opengl.GL45
import java.lang.invoke.MethodType

open class GL45LWJGL2(override val tempArr: Arr) : IGL45 {
    override fun glClipControl(origin: Int, depth: Int) {
        GL45.glClipControl(origin, depth)
    }

    private val glCreateBuffers = createBuffer().asIntBuffer()

    override fun glCreateBuffers(n: Int, buffers: Long) {
        GL45.glCreateBuffers(wrapBuffer(glCreateBuffers, buffers, n))
    }

    private val glNamedBufferStorage = createBuffer()

    override fun glNamedBufferStorage(buffer: Int, size: Long, data: Long, flags: Int) {
        GL45.glNamedBufferStorage(buffer, wrapBuffer(glNamedBufferStorage, data, size.toInt()), flags)
    }

    private val glNamedBufferData = createBuffer()

    override fun glNamedBufferData(buffer: Int, size: Long, data: Long, usage: Int) {
        GL45.glNamedBufferData(buffer, wrapBuffer(glNamedBufferData, data, size.toInt()), usage)
    }

    private val glNamedBufferSubData = createBuffer()

    override fun glNamedBufferSubData(buffer: Int, offset: Long, size: Long, data: Long) {
        GL45.glNamedBufferSubData(buffer, offset, wrapBuffer(glNamedBufferSubData, data, size.toInt()))
    }

    override fun glCopyNamedBufferSubData(
        readBuffer: Int,
        writeBuffer: Int,
        readOffset: Long,
        writeOffset: Long,
        size: Long
    ) {
        GL45.glCopyNamedBufferSubData(readBuffer, writeBuffer, readOffset, writeOffset, size)
    }

    private val glClearNamedBufferData = wrapBuffer(createBuffer(), 0L, 8)

    override fun glClearNamedBufferData(buffer: Int, internalFormat: Int, format: Int, type: Int, data: Long) {
        GL45.glClearNamedBufferData(
            buffer,
            internalFormat,
            format,
            type,
            wrapBuffer(glClearNamedBufferData, data, internalFormat)
        )
    }

    private val glClearNamedBufferSubData = wrapBuffer(createBuffer(), 0L, 8)

    override fun glClearNamedBufferSubData(
        buffer: Int,
        internalFormat: Int,
        offset: Long,
        size: Long,
        format: Int,
        type: Int,
        data: Long
    ) {
        GL45.glClearNamedBufferSubData(
            buffer,
            internalFormat,
            offset,
            size,
            format,
            type,
            wrapBuffer(glClearNamedBufferSubData, data, internalFormat)
        )
    }

    private val glMapNamedBufferRangeUnsafe = createBuffer()

    override fun glMapNamedBufferRangeUnsafe(buffer: Int, offset: Long, length: Long, access: Int): Long {
        return GL45.glMapNamedBufferRange(
            buffer,
            offset,
            length,
            access,
            wrapBuffer(glMapNamedBufferRangeUnsafe, 0L, length.toInt())
        ).address
    }

    override fun glUnmapNamedBuffer(buffer: Int): Boolean {
        return GL45.glUnmapNamedBuffer(buffer)
    }

    override fun glFlushMappedNamedBufferRange(buffer: Int, offset: Long, length: Long) {
        GL45.glFlushMappedNamedBufferRange(buffer, offset, length)
    }

    override fun glGetNamedBufferParameteri(buffer: Int, pname: Int): Int {
        return GL45.glGetNamedBufferParameteri(buffer, pname)
    }

    private val glGetNamedBufferParameteriv = wrapBuffer(createBuffer().asIntBuffer(), 0L, 4)

    override fun glGetNamedBufferParameteriv(buffer: Int, pname: Int, params: Long) {
        GL45.glGetNamedBufferParameter(buffer, pname, wrapBuffer(glGetNamedBufferParameteriv, params))
    }

    override fun glGetNamedBufferParameteri64(buffer: Int, pname: Int): Long {
        return GL45.glGetNamedBufferParameteri64(buffer, pname)
    }

    private val glGetNamedBufferParameteri64v = wrapBuffer(createBuffer().asLongBuffer(), 0L, 8)

    override fun glGetNamedBufferParameteri64v(buffer: Int, pname: Int, params: Long) {
        GL45.glGetNamedBufferParameter(buffer, pname, wrapBuffer(glGetNamedBufferParameteri64v, params))
    }

    private val glGetNamedBufferSubData = createBuffer()

    override fun glGetNamedBufferSubData(buffer: Int, offset: Long, size: Long, data: Long) {
        GL45.glGetNamedBufferSubData(buffer, offset, wrapBuffer(glGetNamedBufferSubData, data, size.toInt()))
    }

    private val glCreateFramebuffers = createBuffer().asIntBuffer()

    override fun glCreateFramebuffers(n: Int, framebuffers: Long) {
        GL45.glCreateFramebuffers(wrapBuffer(glCreateFramebuffers, framebuffers, n))
    }

    override fun glNamedFramebufferRenderbuffer(
        framebuffer: Int,
        attachment: Int,
        renderbuffertarget: Int,
        renderbuffer: Int
    ) {
        GL45.glNamedFramebufferRenderbuffer(framebuffer, attachment, renderbuffertarget, renderbuffer)
    }

    override fun glNamedFramebufferParameteri(framebuffer: Int, pname: Int, param: Int) {
        GL45.glNamedFramebufferParameteri(framebuffer, pname, param)
    }

    override fun glNamedFramebufferTexture(framebuffer: Int, attachment: Int, texture: Int, level: Int) {
        GL45.glNamedFramebufferTexture(framebuffer, attachment, texture, level)
    }

    override fun glNamedFramebufferTextureLayer(
        framebuffer: Int,
        attachment: Int,
        texture: Int,
        level: Int,
        layer: Int
    ) {
        GL45.glNamedFramebufferTextureLayer(framebuffer, attachment, texture, level, layer)
    }

    override fun glNamedFramebufferDrawBuffer(framebuffer: Int, buf: Int) {
        GL45.glNamedFramebufferDrawBuffer(framebuffer, buf)
    }

    private val glNamedFramebufferDrawBuffers = createBuffer().asIntBuffer()

    override fun glNamedFramebufferDrawBuffers(framebuffer: Int, n: Int, bufs: Long) {
        GL45.glNamedFramebufferDrawBuffers(framebuffer, wrapBuffer(glNamedFramebufferDrawBuffers, bufs, n))
    }

    override fun glNamedFramebufferReadBuffer(framebuffer: Int, src: Int) {
        GL45.glNamedFramebufferReadBuffer(framebuffer, src)
    }

    private val glInvalidateNamedFramebufferData = createBuffer().asIntBuffer()

    override fun glInvalidateNamedFramebufferData(framebuffer: Int, numAttachments: Int, attachments: Long) {
        GL45.glInvalidateNamedFramebufferData(
            framebuffer,
            wrapBuffer(glInvalidateNamedFramebufferData, attachments, numAttachments)
        )
    }

    private val glInvalidateNamedFramebufferSubData = createBuffer().asIntBuffer()

    override fun glInvalidateNamedFramebufferSubData(
        framebuffer: Int,
        numAttachments: Int,
        attachments: Long,
        x: Int,
        y: Int,
        width: Int,
        height: Int
    ) {
        GL45.glInvalidateNamedFramebufferSubData(
            framebuffer,
            wrapBuffer(glInvalidateNamedFramebufferSubData, attachments, numAttachments),
            x,
            y,
            width,
            height
        )
    }

    private val glClearNamedFramebufferiv = wrapBuffer(createBuffer().asIntBuffer(), 0L, 4)

    override fun glClearNamedFramebufferiv(framebuffer: Int, buffer: Int, drawbuffer: Int, value: Long) {
        GL45.glClearNamedFramebuffer(
            framebuffer,
            buffer,
            drawbuffer,
            wrapBuffer(glClearNamedFramebufferiv, value, 4)
        )
    }

    private val glClearNamedFramebufferuiv = wrapBuffer(createBuffer().asIntBuffer(), 0L, 4)

    override fun glClearNamedFramebufferuiv(framebuffer: Int, buffer: Int, drawbuffer: Int, value: Long) {
        GL45.glClearNamedFramebuffer(
            framebuffer,
            buffer,
            drawbuffer,
            wrapBuffer(glClearNamedFramebufferuiv, value, 4)
        )
    }

    private val glClearNamedFramebufferfv = wrapBuffer(createBuffer().asIntBuffer(), 0L, 4)

    override fun glClearNamedFramebufferfv(framebuffer: Int, buffer: Int, drawbuffer: Int, value: Long) {
        GL45.glClearNamedFramebuffer(
            framebuffer,
            buffer,
            drawbuffer,
            wrapBuffer(glClearNamedFramebufferfv, value, 4)
        )
    }

    override fun glClearNamedFramebufferfi(framebuffer: Int, buffer: Int, drawbuffer: Int, depth: Float, stencil: Int) {
        GL45.glClearNamedFramebufferfi(framebuffer, buffer, depth, stencil)
    }

    override fun glBlitNamedFramebuffer(
        readFramebuffer: Int,
        drawFramebuffer: Int,
        srcX0: Int,
        srcY0: Int,
        srcX1: Int,
        srcY1: Int,
        dstX0: Int,
        dstY0: Int,
        dstX1: Int,
        dstY1: Int,
        mask: Int,
        filter: Int
    ) {
        GL45.glBlitNamedFramebuffer(
            readFramebuffer,
            drawFramebuffer,
            srcX0,
            srcY0,
            srcX1,
            srcY1,
            dstX0,
            dstY0,
            dstX1,
            dstY1,
            mask,
            filter
        )
    }

    override fun glCheckNamedFramebufferStatus(framebuffer: Int, target: Int): Int {
        return GL45.glCheckNamedFramebufferStatus(framebuffer, target)
    }

    override fun glGetNamedFramebufferParameteri(framebuffer: Int, pname: Int): Int {
        return GL45.glGetNamedFramebufferParameter(framebuffer, pname)
    }

    private val glGetNamedFramebufferParameteriv = wrapBuffer(createBuffer().asIntBuffer(), 0L, 4)

    override fun glGetNamedFramebufferParameteriv(framebuffer: Int, pname: Int, params: Long) {
        GL45.glGetNamedFramebufferParameter(
            framebuffer,
            pname,
            wrapBuffer(glGetNamedFramebufferParameteriv, params)
        )
    }

    override fun glGetNamedFramebufferAttachmentParameteri(framebuffer: Int, attachment: Int, pname: Int): Int {
        return GL45.glGetNamedFramebufferAttachmentParameter(framebuffer, attachment, pname)
    }

    private val glGetNamedFramebufferAttachmentParameteriv = wrapBuffer(createBuffer().asIntBuffer(), 0L, 4)

    override fun glGetNamedFramebufferAttachmentParameteriv(
        framebuffer: Int,
        attachment: Int,
        pname: Int,
        params: Long
    ) {
        GL45.glGetNamedFramebufferAttachmentParameter(
            framebuffer,
            attachment,
            pname,
            wrapBuffer(glGetNamedFramebufferAttachmentParameteriv, params)
        )
    }

    private val glCreateRenderbuffers = createBuffer().asIntBuffer()

    override fun glCreateRenderbuffers(n: Int, renderbuffers: Long) {
        GL45.glCreateRenderbuffers(wrapBuffer(glCreateRenderbuffers, renderbuffers, n))
    }

    override fun glNamedRenderbufferStorage(renderbuffer: Int, internalformat: Int, width: Int, height: Int) {
        GL45.glNamedRenderbufferStorage(renderbuffer, internalformat, width, height)
    }

    override fun glNamedRenderbufferStorageMultisample(
        renderbuffer: Int,
        samples: Int,
        internalformat: Int,
        width: Int,
        height: Int
    ) {
        GL45.glNamedRenderbufferStorageMultisample(renderbuffer, samples, internalformat, width, height)
    }

    override fun glGetNamedRenderbufferParameteri(renderbuffer: Int, pname: Int): Int {
        return GL45.glGetNamedRenderbufferParameter(renderbuffer, pname)
    }

    private val glGetNamedRenderbufferParameteriv = wrapBuffer(createBuffer().asIntBuffer(), 0L, 4)

    override fun glGetNamedRenderbufferParameteriv(renderbuffer: Int, pname: Int, params: Long) {
        GL45.glGetNamedRenderbufferParameter(renderbuffer, pname, wrapBuffer(glGetNamedRenderbufferParameteriv, params))
    }

    private val glCreateTextures = createBuffer().asIntBuffer()

    override fun glCreateTextures(target: Int, n: Int, textures: Long) {
        GL45.glCreateTextures(target, wrapBuffer(glCreateTextures, textures, n))
    }

    override fun glTextureBuffer(texture: Int, internalformat: Int, buffer: Int) {
        GL45.glTextureBuffer(texture, internalformat, buffer)
    }

    override fun glTextureBufferRange(texture: Int, internalformat: Int, buffer: Int, offset: Long, size: Long) {
        GL45.glTextureBufferRange(texture, internalformat, buffer, offset, size)
    }

    override fun glTextureStorage1D(texture: Int, levels: Int, internalformat: Int, width: Int) {
        GL45.glTextureStorage1D(texture, levels, internalformat, width)
    }

    override fun glTextureStorage2D(texture: Int, levels: Int, internalformat: Int, width: Int, height: Int) {
        GL45.glTextureStorage2D(texture, levels, internalformat, width, height)
    }

    override fun glTextureStorage3D(
        texture: Int,
        levels: Int,
        internalformat: Int,
        width: Int,
        height: Int,
        depth: Int
    ) {
        GL45.glTextureStorage3D(texture, levels, internalformat, width, height, depth)
    }

    override fun glTextureStorage2DMultisample(
        texture: Int,
        samples: Int,
        internalformat: Int,
        width: Int,
        height: Int,
        fixedsamplelocations: Boolean
    ) {
        GL45.glTextureStorage2DMultisample(texture, samples, internalformat, width, height, fixedsamplelocations)
    }

    override fun glTextureStorage3DMultisample(
        texture: Int,
        samples: Int,
        internalformat: Int,
        width: Int,
        height: Int,
        depth: Int,
        fixedsamplelocations: Boolean
    ) {
        GL45.glTextureStorage3DMultisample(texture, samples, internalformat, width, height, depth, fixedsamplelocations)
    }

    private val glTextureSubImage1D = getFunctionAddress("glTextureSubImage1D")
    private val nglTextureSubImage1D = trustedLookUp.findStatic(
        GL45::class.java,
        "nglTextureSubImage1D",
        MethodType.methodType(
            Void.TYPE,
            Int::class.java,
            Int::class.java,
            Int::class.java,
            Int::class.java,
            Int::class.java,
            Int::class.java,
            Long::class.java,
            Long::class.java
        )
    )

    override fun glTextureSubImage1D(
        texture: Int,
        level: Int,
        xoffset: Int,
        width: Int,
        format: Int,
        type: Int,
        pixels: Long
    ) {
        nglTextureSubImage1D.invokeExact(
            texture,
            level,
            xoffset,
            width,
            format,
            type,
            pixels,
            glTextureSubImage1D
        )
    }

    private val glTextureSubImage2D = getFunctionAddress("glTextureSubImage2D")
    private val nglTextureSubImage2D = trustedLookUp.findStatic(
        GL45::class.java,
        "nglTextureSubImage2D",
        MethodType.methodType(
            Void.TYPE,
            Int::class.java,
            Int::class.java,
            Int::class.java,
            Int::class.java,
            Int::class.java,
            Int::class.java,
            Int::class.java,
            Int::class.java,
            Long::class.java,
            Long::class.java
        )
    )

    override fun glTextureSubImage2D(
        texture: Int,
        level: Int,
        xoffset: Int,
        yoffset: Int,
        width: Int,
        height: Int,
        format: Int,
        type: Int,
        pixels: Long
    ) {
        nglTextureSubImage2D.invokeExact(
            texture,
            level,
            xoffset,
            yoffset,
            width,
            height,
            format,
            type,
            pixels,
            glTextureSubImage2D,
        )
    }

    private val glTextureSubImage3D = getFunctionAddress("glTextureSubImage3D")
    private val nglTextureSubImage3D = trustedLookUp.findStatic(
        GL45::class.java,
        "nglTextureSubImage3D",
        MethodType.methodType(
            Void.TYPE,
            Int::class.java,
            Int::class.java,
            Int::class.java,
            Int::class.java,
            Int::class.java,
            Int::class.java,
            Int::class.java,
            Int::class.java,
            Int::class.java,
            Int::class.java,
            Long::class.java,
            Long::class.java
        )
    )

    override fun glTextureSubImage3D(
        texture: Int,
        level: Int,
        xoffset: Int,
        yoffset: Int,
        zoffset: Int,
        width: Int,
        height: Int,
        depth: Int,
        format: Int,
        type: Int,
        pixels: Long
    ) {
        nglTextureSubImage3D.invokeExact(
            texture,
            level,
            xoffset,
            yoffset,
            zoffset,
            width,
            height,
            depth,
            format,
            type,
            pixels,
            glTextureSubImage3D
        )
    }

    private val glCompressedTextureSubImage1D = getFunctionAddress("glCompressedTextureSubImage1D")
    private val nglCompressedTextureSubImage1D = trustedLookUp.findStatic(
        GL45::class.java,
        "nglCompressedTextureSubImage1D",
        MethodType.methodType(
            Void.TYPE,
            Int::class.java,
            Int::class.java,
            Int::class.java,
            Int::class.java,
            Int::class.java,
            Int::class.java,
            Long::class.java,
            Long::class.java
        )
    )

    override fun glCompressedTextureSubImage1D(
        texture: Int,
        level: Int,
        xoffset: Int,
        width: Int,
        format: Int,
        imageSize: Int,
        data: Long
    ) {
        nglCompressedTextureSubImage1D.invokeExact(
            texture,
            level,
            xoffset,
            width,
            format,
            imageSize,
            data,
            glCompressedTextureSubImage1D
        )
    }

    private val glCompressedTextureSubImage2D = getFunctionAddress("glCompressedTextureSubImage2D")
    private val nglCompressedTextureSubImage2D = trustedLookUp.findStatic(
        GL45::class.java,
        "nglCompressedTextureSubImage2D",
        MethodType.methodType(
            Void.TYPE,
            Int::class.java,
            Int::class.java,
            Int::class.java,
            Int::class.java,
            Int::class.java,
            Int::class.java,
            Int::class.java,
            Int::class.java,
            Long::class.java,
            Long::class.java
        )
    )

    override fun glCompressedTextureSubImage2D(
        texture: Int,
        level: Int,
        xoffset: Int,
        yoffset: Int,
        width: Int,
        height: Int,
        format: Int,
        imageSize: Int,
        data: Long
    ) {
        nglCompressedTextureSubImage2D.invokeExact(
            texture,
            level,
            xoffset,
            yoffset,
            width,
            height,
            format,
            imageSize,
            data,
            glCompressedTextureSubImage2D
        )
    }

    private val glCompressedTextureSubImage3D = getFunctionAddress("glCompressedTextureSubImage3D")
    private val nglCompressedTextureSubImage3D = trustedLookUp.findStatic(
        GL45::class.java,
        "nglCompressedTextureSubImage3D",
        MethodType.methodType(
            Void.TYPE,
            Int::class.java,
            Int::class.java,
            Int::class.java,
            Int::class.java,
            Int::class.java,
            Int::class.java,
            Int::class.java,
            Int::class.java,
            Int::class.java,
            Int::class.java,
            Long::class.java,
            Long::class.java
        )
    )

    override fun glCompressedTextureSubImage3D(
        texture: Int,
        level: Int,
        xoffset: Int,
        yoffset: Int,
        zoffset: Int,
        width: Int,
        height: Int,
        depth: Int,
        format: Int,
        imageSize: Int,
        data: Long
    ) {
        nglCompressedTextureSubImage3D.invokeExact(
            texture,
            level,
            xoffset,
            yoffset,
            zoffset,
            width,
            height,
            depth,
            format,
            imageSize,
            data,
            glCompressedTextureSubImage3D
        )
    }

    override fun glCopyTextureSubImage1D(texture: Int, level: Int, xoffset: Int, x: Int, y: Int, width: Int) {
        GL45.glCopyTextureSubImage1D(texture, level, xoffset, x, y, width)
    }

    override fun glCopyTextureSubImage2D(
        texture: Int,
        level: Int,
        xoffset: Int,
        yoffset: Int,
        x: Int,
        y: Int,
        width: Int,
        height: Int
    ) {
        GL45.glCopyTextureSubImage2D(texture, level, xoffset, yoffset, x, y, width, height)
    }

    override fun glCopyTextureSubImage3D(
        texture: Int,
        level: Int,
        xoffset: Int,
        yoffset: Int,
        zoffset: Int,
        x: Int,
        y: Int,
        width: Int,
        height: Int
    ) {
        GL45.glCopyTextureSubImage3D(texture, level, xoffset, yoffset, zoffset, x, y, width, height)
    }

    override fun glTextureParameterf(texture: Int, pname: Int, param: Float) {
        GL45.glTextureParameterf(texture, pname, param)
    }

    override fun glTextureParameteri(texture: Int, pname: Int, param: Int) {
        GL45.glTextureParameteri(texture, pname, param)
    }

    private val glTextureParameterfv = wrapBuffer(createBuffer().asFloatBuffer(), 0L, 4)

    override fun glTextureParameterfv(texture: Int, pname: Int, params: Long) {
        GL45.glTextureParameter(texture, pname, wrapBuffer(glTextureParameterfv, params))
    }

    private val glTextureParameteriv = wrapBuffer(createBuffer().asIntBuffer(), 0L, 4)

    override fun glTextureParameteriv(texture: Int, pname: Int, params: Long) {
        GL45.glTextureParameter(texture, pname, wrapBuffer(glTextureParameteriv, params))
    }

    private val glTextureParameterIiv = wrapBuffer(createBuffer().asIntBuffer(), 0L, 4)

    override fun glTextureParameterIiv(texture: Int, pname: Int, params: Long) {
        GL45.glTextureParameterI(texture, pname, wrapBuffer(glTextureParameterIiv, params))
    }

    private val glTextureParameterIuiv = wrapBuffer(createBuffer().asIntBuffer(), 0L, 4)

    override fun glTextureParameterIuiv(texture: Int, pname: Int, params: Long) {
        GL45.glTextureParameterIu(texture, pname, wrapBuffer(glTextureParameterIuiv, params))
    }

    override fun glGenerateTextureMipmap(texture: Int) {
        GL45.glGenerateTextureMipmap(texture)
    }

    override fun glBindTextureUnit(unit: Int, texture: Int) {
        GL45.glBindTextureUnit(unit, texture)
    }

    private val glGetTextureImage = createBuffer()

    override fun glGetTextureImage(texture: Int, level: Int, format: Int, type: Int, bufSize: Int, pixels: Long) {
        GL45.glGetTextureImage(texture, level, format, type, wrapBuffer(glGetTextureImage, pixels))
    }

    private val glGetCompressedTextureImage = createBuffer()

    override fun glGetCompressedTextureImage(texture: Int, level: Int, bufSize: Int, pixels: Long) {
        GL45.glGetCompressedTextureImage(texture, level, wrapBuffer(glGetCompressedTextureImage, pixels))
    }

    override fun glGetTextureLevelParameterf(texture: Int, level: Int, pname: Int): Float {
        return GL45.glGetTextureLevelParameterf(texture, level, pname)
    }

    override fun glGetTextureLevelParameteri(texture: Int, level: Int, pname: Int): Int {
        return GL45.glGetTextureLevelParameteri(texture, level, pname)
    }

    private val glGetTextureLevelParameterfv = wrapBuffer(createBuffer().asFloatBuffer(), 0L, 4)

    override fun glGetTextureLevelParameterfv(texture: Int, level: Int, pname: Int, params: Long) {
        GL45.glGetTextureLevelParameter(texture, level, pname, wrapBuffer(glGetTextureLevelParameterfv, params))
    }

    private val glGetTextureLevelParameteriv = wrapBuffer(createBuffer().asIntBuffer(), 0L, 4)

    override fun glGetTextureLevelParameteriv(texture: Int, level: Int, pname: Int, params: Long) {
        GL45.glGetTextureLevelParameter(texture, level, pname, wrapBuffer(glGetTextureLevelParameteriv, params))
    }

    override fun glGetTextureParameterf(texture: Int, pname: Int): Float {
        return GL45.glGetTextureParameterf(texture, pname)
    }

    override fun glGetTextureParameteri(texture: Int, pname: Int): Int {
        return GL45.glGetTextureParameteri(texture, pname)
    }

    private val glGetTextureParameterfv = wrapBuffer(createBuffer().asFloatBuffer(), 0L, 4)

    override fun glGetTextureParameterfv(texture: Int, pname: Int, params: Long) {
        GL45.glGetTextureParameter(texture, pname, wrapBuffer(glGetTextureParameterfv, params))
    }

    private val glGetTextureParameteriv = wrapBuffer(createBuffer().asIntBuffer(), 0L, 4)

    override fun glGetTextureParameteriv(texture: Int, pname: Int, params: Long) {
        GL45.glGetTextureParameter(texture, pname, wrapBuffer(glGetTextureParameteriv, params))
    }

    private val glGetTextureParameterIiv = wrapBuffer(createBuffer().asIntBuffer(), 0L, 4)

    override fun glGetTextureParameterIiv(texture: Int, pname: Int, params: Long) {
        GL45.glGetTextureParameterI(texture, pname, wrapBuffer(glGetTextureParameterIiv, params))
    }

    private val glGetTextureParameterIuiv = wrapBuffer(createBuffer().asIntBuffer(), 0L, 4)

    override fun glGetTextureParameterIuiv(texture: Int, pname: Int, params: Long) {
        GL45.glGetTextureParameterIu(texture, pname, wrapBuffer(glGetTextureParameterIuiv, params))
    }

    private val glCreateVertexArrays = createBuffer().asIntBuffer()

    override fun glCreateVertexArrays(n: Int, arrays: Long) {
        GL45.glCreateVertexArrays(wrapBuffer(glCreateVertexArrays, arrays, n))
    }

    override fun glDisableVertexArrayAttrib(vaobj: Int, index: Int) {
        GL45.glDisableVertexArrayAttrib(vaobj, index)
    }

    override fun glEnableVertexArrayAttrib(vaobj: Int, index: Int) {
        GL45.glEnableVertexArrayAttrib(vaobj, index)
    }

    override fun glVertexArrayElementBuffer(vaobj: Int, buffer: Int) {
        GL45.glVertexArrayElementBuffer(vaobj, buffer)
    }

    override fun glVertexArrayVertexBuffer(vaobj: Int, bindingindex: Int, buffer: Int, offset: Long, stride: Int) {
        GL45.glVertexArrayVertexBuffer(vaobj, bindingindex, buffer, offset, stride)
    }

    private val glVertexArrayVertexBuffers_buffers = createBuffer().asIntBuffer()
    private val glVertexArrayVertexBuffers_offsets = PointerBuffer(createBuffer())
    private val glVertexArrayVertexBuffers_strides = createBuffer().asIntBuffer()

    override fun glVertexArrayVertexBuffers(
        vaobj: Int,
        first: Int,
        count: Int,
        buffers: Long,
        offsets: Long,
        strides: Long
    ) {
        GL45.glVertexArrayVertexBuffers(
            vaobj,
            first,
            count,
            wrapBuffer(glVertexArrayVertexBuffers_buffers, buffers, count),
            wrapBuffer(glVertexArrayVertexBuffers_offsets, offsets, count),
            wrapBuffer(glVertexArrayVertexBuffers_strides, offsets, count),
        )
    }

    override fun glVertexArrayAttribFormat(
        vaobj: Int,
        attribindex: Int,
        size: Int,
        type: Int,
        normalized: Boolean,
        relativeoffset: Int
    ) {
        GL45.glVertexArrayAttribFormat(vaobj, attribindex, size, type, normalized, relativeoffset)
    }

    override fun glVertexArrayAttribIFormat(vaobj: Int, attribindex: Int, size: Int, type: Int, relativeoffset: Int) {
        GL45.glVertexArrayAttribIFormat(vaobj, attribindex, size, type, relativeoffset)
    }

    override fun glVertexArrayAttribLFormat(vaobj: Int, attribindex: Int, size: Int, type: Int, relativeoffset: Int) {
        GL45.glVertexArrayAttribLFormat(vaobj, attribindex, size, type, relativeoffset)
    }

    override fun glVertexArrayAttribBinding(vaobj: Int, attribindex: Int, bindingindex: Int) {
        GL45.glVertexArrayAttribBinding(vaobj, attribindex, bindingindex)
    }

    override fun glVertexArrayBindingDivisor(vaobj: Int, bindingindex: Int, divisor: Int) {
        GL45.glVertexArrayBindingDivisor(vaobj, bindingindex, divisor)
    }

    private val glCreateSamplers = createBuffer().asIntBuffer()

    override fun glCreateSamplers(n: Int, samplers: Long) {
        GL45.glCreateSamplers(wrapBuffer(glCreateSamplers, samplers, n))
    }

    override fun glMemoryBarrierByRegion(barriers: Int) {
        GL45.glMemoryBarrierByRegion(barriers)
    }

    override fun glTextureBarrier() {
        GL45.glTextureBarrier()
    }

    override fun glReadnPixels(
        x: Int,
        y: Int,
        width: Int,
        height: Int,
        format: Int,
        type: Int,
        bufSize: Int,
        data: Long
    ) {
        GL45.glReadnPixels(x, y, width, height, format, type, bufSize, data)
    }


    override fun glCreateBuffers(): Int {
        return GL45.glCreateBuffers()
    }

    override fun glCreateFramebuffers(): Int {
        return GL45.glCreateFramebuffers()
    }

    override fun glCreateRenderbuffers(): Int {
        return GL45.glCreateRenderbuffers()
    }

    override fun glCreateTextures(target: Int): Int {
        return GL45.glCreateTextures(target)
    }

    override fun glCreateVertexArrays(): Int {
        return GL45.glCreateVertexArrays()
    }

    override fun glCreateSamplers(): Int {
        return GL45.glCreateSamplers()
    }
}
