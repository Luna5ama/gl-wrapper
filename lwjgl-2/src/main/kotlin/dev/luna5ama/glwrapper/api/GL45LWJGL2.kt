package dev.luna5ama.glwrapper.api

import dev.luna5ama.kmogus.*
import org.lwjgl.PointerBuffer
import org.lwjgl.opengl.GL45
import java.lang.invoke.MethodType

open class GL45LWJGL2(override val tempArr: Arr) : IGL45 {
    override fun glClipControl(origin: Int, depth: Int) {
        GL45.glClipControl(origin, depth)
    }

//    private val glPointParameteriv = nullIntBuffer()
//
//    override fun glPointParameteriv(pname: Int, params: Long) {
//        glPointParameteriv(pname, Ptr(params))
//    }
//
//    override fun glPointParameteriv(pname: Int, params: Ptr) {
//        GL14.glPointParameter(pname, params.asIntBuffer(4, glPointParameteriv))
//    }

    private val glCreateBuffers = nullIntBuffer()

    override fun glCreateBuffers(n: Int, buffers: Long) {
        glCreateBuffers(n, Ptr(buffers))
    }

    override fun glCreateBuffers(n: Int, buffers: Ptr) {
        GL45.glCreateBuffers(buffers.asIntBuffer(n, glCreateBuffers))
    }

    private val glNamedBufferStorage = nullByteBuffer()

    override fun glNamedBufferStorage(buffer: Int, size: Long, data: Long, flags: Int) {
        glNamedBufferStorage(buffer, size, Ptr(data), flags)
    }

    override fun glNamedBufferStorage(buffer: Int, size: Long, data: Ptr, flags: Int) {
        GL45.glNamedBufferStorage(buffer, data.asByteBuffer(size.toInt(), glNamedBufferStorage), flags)
    }

    private val glNamedBufferData = nullByteBuffer()

    override fun glNamedBufferData(buffer: Int, size: Long, data: Long, usage: Int) {
        glNamedBufferData(buffer, size, Ptr(data), usage)
    }

    override fun glNamedBufferData(buffer: Int, size: Long, data: Ptr, usage: Int) {
        GL45.glNamedBufferData(buffer, data.asByteBuffer(size.toInt(), glNamedBufferData), usage)
    }

    private val glNamedBufferSubData = nullByteBuffer()

    override fun glNamedBufferSubData(buffer: Int, offset: Long, size: Long, data: Long) {
        glNamedBufferSubData(buffer, offset, size, Ptr(data))
    }

    override fun glNamedBufferSubData(buffer: Int, offset: Long, size: Long, data: Ptr) {
        GL45.glNamedBufferSubData(buffer, offset, data.asByteBuffer(size.toInt(), glNamedBufferSubData))
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

    private val glClearNamedBufferData = nullByteBuffer()

    override fun glClearNamedBufferData(buffer: Int, internalFormat: Int, format: Int, type: Int, data: Long) {
        glClearNamedBufferData(
            buffer,
            internalFormat,
            format,
            type,
            Ptr(data)
        )
    }

    override fun glClearNamedBufferData(buffer: Int, internalFormat: Int, format: Int, type: Int, data: Ptr) {
        GL45.glClearNamedBufferData(
            buffer,
            internalFormat,
            format,
            type,
            data.asByteBuffer(0, glClearNamedBufferData)
        )
    }

    private val glClearNamedBufferSubData = nullByteBuffer()

    override fun glClearNamedBufferSubData(
        buffer: Int,
        internalFormat: Int,
        offset: Long,
        size: Long,
        format: Int,
        type: Int,
        data: Long
    ) {
        glClearNamedBufferSubData(
            buffer,
            internalFormat,
            offset,
            size,
            format,
            type,
            Ptr(data)
        )
    }

    override fun glClearNamedBufferSubData(
        buffer: Int,
        internalFormat: Int,
        offset: Long,
        size: Long,
        format: Int,
        type: Int,
        data: Ptr
    ) {
        GL45.glClearNamedBufferSubData(
            buffer,
            internalFormat,
            offset,
            size,
            format,
            type,
            data.asByteBuffer(0, glClearNamedBufferSubData)
        )
    }

    private val glMapNamedBufferRangeUnsafe = nullByteBuffer()

    override fun glMapNamedBufferRangeUnsafe(buffer: Int, offset: Long, length: Long, access: Int): Long {
        return GL45.glMapNamedBufferRange(
            buffer,
            offset,
            length,
            access,
            Ptr.NULL.asByteBuffer(length.toInt(), glMapNamedBufferRangeUnsafe)
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

    private val glGetNamedBufferParameteriv = nullIntBuffer()

    override fun glGetNamedBufferParameteriv(buffer: Int, pname: Int, params: Long) {
        glGetNamedBufferParameteriv(buffer, pname, Ptr(params))
    }

    override fun glGetNamedBufferParameteriv(buffer: Int, pname: Int, params: Ptr) {
        GL45.glGetNamedBufferParameter(buffer, pname, params.asIntBuffer(4, glGetNamedBufferParameteriv))
    }

    override fun glGetNamedBufferParameteri64(buffer: Int, pname: Int): Long {
        return GL45.glGetNamedBufferParameteri64(buffer, pname)
    }

    private val glGetNamedBufferParameteri64v = nullLongBuffer()

    override fun glGetNamedBufferParameteri64v(buffer: Int, pname: Int, params: Long) {
        glGetNamedBufferParameteri64v(buffer, pname, Ptr(params))
    }

    override fun glGetNamedBufferParameteri64v(buffer: Int, pname: Int, params: Ptr) {
        GL45.glGetNamedBufferParameter(buffer, pname, params.asLongBuffer(4, glGetNamedBufferParameteri64v))
    }

    private val glGetNamedBufferSubData = nullByteBuffer()

    override fun glGetNamedBufferSubData(buffer: Int, offset: Long, size: Long, data: Long) {
        glGetNamedBufferSubData(buffer, offset, size, Ptr(data))
    }

    override fun glGetNamedBufferSubData(buffer: Int, offset: Long, size: Long, data: Ptr) {
        GL45.glGetNamedBufferSubData(buffer, offset, data.asByteBuffer(size.toInt(), glGetNamedBufferSubData))
    }

    private val glCreateFramebuffers = nullIntBuffer()

    override fun glCreateFramebuffers(n: Int, framebuffers: Long) {
        glCreateFramebuffers(n, Ptr(framebuffers))
    }

    override fun glCreateFramebuffers(n: Int, framebuffers: Ptr) {
        GL45.glCreateFramebuffers(framebuffers.asIntBuffer(n, glCreateFramebuffers))
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

    private val glNamedFramebufferDrawBuffers = nullIntBuffer()

    override fun glNamedFramebufferDrawBuffers(framebuffer: Int, n: Int, bufs: Long) {
        glNamedFramebufferDrawBuffers(framebuffer, n, Ptr(bufs))
    }

    override fun glNamedFramebufferDrawBuffers(framebuffer: Int, n: Int, bufs: Ptr) {
        GL45.glNamedFramebufferDrawBuffers(framebuffer, bufs.asIntBuffer(n, glNamedFramebufferDrawBuffers))
    }

    override fun glNamedFramebufferReadBuffer(framebuffer: Int, src: Int) {
        GL45.glNamedFramebufferReadBuffer(framebuffer, src)
    }

    private val glInvalidateNamedFramebufferData = nullIntBuffer()

    override fun glInvalidateNamedFramebufferData(framebuffer: Int, numAttachments: Int, attachments: Long) {
        glInvalidateNamedFramebufferData(framebuffer, numAttachments, Ptr(attachments))
    }

    override fun glInvalidateNamedFramebufferData(framebuffer: Int, numAttachments: Int, attachments: Ptr) {
        GL45.glInvalidateNamedFramebufferData(
            framebuffer,
            attachments.asIntBuffer(numAttachments, glInvalidateNamedFramebufferData)
        )
    }

    private val glInvalidateNamedFramebufferSubData = nullIntBuffer()

    override fun glInvalidateNamedFramebufferSubData(
        framebuffer: Int,
        numAttachments: Int,
        attachments: Long,
        x: Int,
        y: Int,
        width: Int,
        height: Int
    ) {
        glInvalidateNamedFramebufferSubData(framebuffer, numAttachments, Ptr(attachments), x, y, width, height)
    }

    override fun glInvalidateNamedFramebufferSubData(
        framebuffer: Int,
        numAttachments: Int,
        attachments: Ptr,
        x: Int,
        y: Int,
        width: Int,
        height: Int
    ) {
        GL45.glInvalidateNamedFramebufferSubData(
            framebuffer,
            attachments.asIntBuffer(numAttachments, glInvalidateNamedFramebufferSubData),
            x,
            y,
            width,
            height
        )
    }

    private val glClearNamedFramebufferiv = nullIntBuffer()

    override fun glClearNamedFramebufferiv(framebuffer: Int, buffer: Int, drawbuffer: Int, value: Long) {
        glClearNamedFramebufferiv(framebuffer, buffer, drawbuffer, Ptr(value))
    }

    override fun glClearNamedFramebufferiv(framebuffer: Int, buffer: Int, drawbuffer: Int, value: Ptr) {
        GL45.glClearNamedFramebuffer(
            framebuffer,
            buffer,
            drawbuffer,
            value.asIntBuffer(4, glClearNamedFramebufferiv)
        )
    }

    private val glClearNamedFramebufferuiv = nullIntBuffer()

    override fun glClearNamedFramebufferuiv(framebuffer: Int, buffer: Int, drawbuffer: Int, value: Long) {
        glClearNamedFramebufferuiv(framebuffer, buffer, drawbuffer, Ptr(value))
    }

    override fun glClearNamedFramebufferuiv(framebuffer: Int, buffer: Int, drawbuffer: Int, value: Ptr) {
        GL45.glClearNamedFramebuffer(
            framebuffer,
            buffer,
            drawbuffer,
            value.asIntBuffer(4, glClearNamedFramebufferuiv)
        )
    }

    private val glClearNamedFramebufferfv = nullFloatBuffer()

    override fun glClearNamedFramebufferfv(framebuffer: Int, buffer: Int, drawbuffer: Int, value: Long) {
        glClearNamedFramebufferfv(framebuffer, buffer, drawbuffer, Ptr(value))
    }

    override fun glClearNamedFramebufferfv(framebuffer: Int, buffer: Int, drawbuffer: Int, value: Ptr) {
        GL45.glClearNamedFramebuffer(framebuffer, buffer, drawbuffer, value.asFloatBuffer(4, glClearNamedFramebufferfv))
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

    private val glGetNamedFramebufferParameteriv = nullIntBuffer()

    override fun glGetNamedFramebufferParameteriv(framebuffer: Int, pname: Int, params: Long) {
        glGetNamedFramebufferParameteriv(framebuffer, pname, Ptr(params))
    }

    override fun glGetNamedFramebufferParameteriv(framebuffer: Int, pname: Int, params: Ptr) {
        GL45.glGetNamedFramebufferParameter(framebuffer, pname, params.asIntBuffer(4, glGetNamedFramebufferParameteriv))
    }

    override fun glGetNamedFramebufferAttachmentParameteri(framebuffer: Int, attachment: Int, pname: Int): Int {
        return GL45.glGetNamedFramebufferAttachmentParameter(framebuffer, attachment, pname)
    }

    private val glGetNamedFramebufferAttachmentParameteriv = nullIntBuffer()

    override fun glGetNamedFramebufferAttachmentParameteriv(
        framebuffer: Int,
        attachment: Int,
        pname: Int,
        params: Long
    ) {
        glGetNamedFramebufferAttachmentParameteriv(framebuffer, attachment, pname, Ptr(params))
    }

    override fun glGetNamedFramebufferAttachmentParameteriv(
        framebuffer: Int,
        attachment: Int,
        pname: Int,
        params: Ptr
    ) {
        GL45.glGetNamedFramebufferAttachmentParameter(
            framebuffer,
            attachment,
            pname,
            params.asIntBuffer(4, glGetNamedFramebufferAttachmentParameteriv)
        )
    }

    private val glCreateRenderbuffers = nullIntBuffer()

    override fun glCreateRenderbuffers(n: Int, renderbuffers: Long) {
        glCreateRenderbuffers(n, Ptr(renderbuffers))
    }

    override fun glCreateRenderbuffers(n: Int, renderbuffers: Ptr) {
        GL45.glCreateRenderbuffers(renderbuffers.asIntBuffer(n, glCreateRenderbuffers))
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

    private val glGetNamedRenderbufferParameteriv = nullIntBuffer()

    override fun glGetNamedRenderbufferParameteriv(renderbuffer: Int, pname: Int, params: Long) {
        glGetNamedRenderbufferParameteriv(renderbuffer, pname, Ptr(params))
    }

    override fun glGetNamedRenderbufferParameteriv(renderbuffer: Int, pname: Int, params: Ptr) {
        GL45.glGetNamedRenderbufferParameter(
            renderbuffer,
            pname,
            params.asIntBuffer(4, glGetNamedRenderbufferParameteriv)
        )
    }

    private val glCreateTextures = nullIntBuffer()

    override fun glCreateTextures(target: Int, n: Int, textures: Long) {
        glCreateTextures(target, n, Ptr(textures))
    }

    override fun glCreateTextures(target: Int, n: Int, textures: Ptr) {
        GL45.glCreateTextures(target, textures.asIntBuffer(n, glCreateTextures))
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

    private val glTextureParameterfv = nullFloatBuffer()

    override fun glTextureParameterfv(texture: Int, pname: Int, params: Long) {
        glTextureParameterfv(texture, pname, Ptr(params))
    }

    override fun glTextureParameterfv(texture: Int, pname: Int, params: Ptr) {
        GL45.glTextureParameter(texture, pname, params.asFloatBuffer(4, glTextureParameterfv))
    }

    private val glTextureParameteriv = nullIntBuffer()

    override fun glTextureParameteriv(texture: Int, pname: Int, params: Long) {
        glTextureParameteriv(texture, pname, Ptr(params))
    }

    override fun glTextureParameteriv(texture: Int, pname: Int, params: Ptr) {
        GL45.glTextureParameter(texture, pname, params.asIntBuffer(4, glTextureParameteriv))
    }

    private val glTextureParameterIiv = nullIntBuffer()

    override fun glTextureParameterIiv(texture: Int, pname: Int, params: Long) {
        glTextureParameterIiv(texture, pname, Ptr(params))
    }

    override fun glTextureParameterIiv(texture: Int, pname: Int, params: Ptr) {
        GL45.glTextureParameterI(texture, pname, params.asIntBuffer(4, glTextureParameterIiv))
    }

    private val glTextureParameterIuiv = nullIntBuffer()

    override fun glTextureParameterIuiv(texture: Int, pname: Int, params: Long) {
        glTextureParameterIuiv(texture, pname, Ptr(params))
    }

    override fun glTextureParameterIuiv(texture: Int, pname: Int, params: Ptr) {
        GL45.glTextureParameterIu(texture, pname, params.asIntBuffer(4, glTextureParameterIuiv))
    }

    override fun glGenerateTextureMipmap(texture: Int) {
        GL45.glGenerateTextureMipmap(texture)
    }

    override fun glBindTextureUnit(unit: Int, texture: Int) {
        GL45.glBindTextureUnit(unit, texture)
    }

    private val glGetTextureImage = getFunctionAddress("glGetTextureImage")
    private val nglGetTextureImage = trustedLookUp.findStatic(
        GL45::class.java,
        "nglGetTextureImage",
        MethodType.methodType(
            Void.TYPE,
            Int::class.java,
            Int::class.java,
            Int::class.java,
            Int::class.java,
            Int::class.java,
            Long::class.java,
            Long::class.java
        )
    )

    override fun glGetTextureImage(texture: Int, level: Int, format: Int, type: Int, bufSize: Int, pixels: Long) {
        nglGetTextureImage.invokeExact(texture, level, format, type, bufSize, pixels, glGetTextureImage)
    }

    private val glGetCompressedTextureImage = getFunctionAddress("glGetCompressedTextureImage")
    private val nglGetCompressedTextureImage = trustedLookUp.findStatic(
        GL45::class.java,
        "nglGetCompressedTextureImage",
        MethodType.methodType(
            Void.TYPE,
            Int::class.java,
            Int::class.java,
            Int::class.java,
            Long::class.java,
            Long::class.java
        )
    )

    override fun glGetCompressedTextureImage(texture: Int, level: Int, bufSize: Int, pixels: Long) {
        nglGetCompressedTextureImage.invokeExact(texture, level, bufSize, pixels, glGetCompressedTextureImage)
    }

    override fun glGetTextureLevelParameterf(texture: Int, level: Int, pname: Int): Float {
        return GL45.glGetTextureLevelParameterf(texture, level, pname)
    }

    override fun glGetTextureLevelParameteri(texture: Int, level: Int, pname: Int): Int {
        return GL45.glGetTextureLevelParameteri(texture, level, pname)
    }

    private val glGetTextureLevelParameterfv = nullFloatBuffer()

    override fun glGetTextureLevelParameterfv(texture: Int, level: Int, pname: Int, params: Long) {
        glGetTextureLevelParameterfv(texture, level, pname, Ptr(params))
    }

    override fun glGetTextureLevelParameterfv(texture: Int, level: Int, pname: Int, params: Ptr) {
        GL45.glGetTextureLevelParameter(texture, level, pname, params.asFloatBuffer(4, glGetTextureLevelParameterfv))
    }

    private val glGetTextureLevelParameteriv = nullIntBuffer()

    override fun glGetTextureLevelParameteriv(texture: Int, level: Int, pname: Int, params: Long) {
        glGetTextureLevelParameteriv(texture, level, pname, Ptr(params))
    }

    override fun glGetTextureLevelParameteriv(texture: Int, level: Int, pname: Int, params: Ptr) {
        GL45.glGetTextureLevelParameter(texture, level, pname, params.asIntBuffer(4, glGetTextureLevelParameteriv))
    }

    override fun glGetTextureParameterf(texture: Int, pname: Int): Float {
        return GL45.glGetTextureParameterf(texture, pname)
    }

    override fun glGetTextureParameteri(texture: Int, pname: Int): Int {
        return GL45.glGetTextureParameteri(texture, pname)
    }

    private val glGetTextureParameterfv = nullFloatBuffer()

    override fun glGetTextureParameterfv(texture: Int, pname: Int, params: Long) {
        glGetTextureParameterfv(texture, pname, Ptr(params))
    }

    override fun glGetTextureParameterfv(texture: Int, pname: Int, params: Ptr) {
        GL45.glGetTextureParameter(texture, pname, params.asFloatBuffer(4, glGetTextureParameterfv))
    }

    private val glGetTextureParameteriv = nullIntBuffer()

    override fun glGetTextureParameteriv(texture: Int, pname: Int, params: Long) {
        glGetTextureParameteriv(texture, pname, Ptr(params))
    }

    override fun glGetTextureParameteriv(texture: Int, pname: Int, params: Ptr) {
        GL45.glGetTextureParameter(texture, pname, params.asIntBuffer(4, glGetTextureParameteriv))
    }

    private val glGetTextureParameterIiv = nullIntBuffer()

    override fun glGetTextureParameterIiv(texture: Int, pname: Int, params: Long) {
        glGetTextureParameterIiv(texture, pname, Ptr(params))
    }

    override fun glGetTextureParameterIiv(texture: Int, pname: Int, params: Ptr) {
        GL45.glGetTextureParameterI(texture, pname, params.asIntBuffer(4, glGetTextureParameterIiv))
    }

    private val glGetTextureParameterIuiv = nullIntBuffer()

    override fun glGetTextureParameterIuiv(texture: Int, pname: Int, params: Long) {
        glGetTextureParameterIuiv(texture, pname, Ptr(params))
    }

    override fun glGetTextureParameterIuiv(texture: Int, pname: Int, params: Ptr) {
        GL45.glGetTextureParameterIu(texture, pname, params.asIntBuffer(4, glGetTextureParameterIuiv))
    }

    private val glCreateVertexArrays = nullIntBuffer()

    override fun glCreateVertexArrays(n: Int, arrays: Long) {
        glCreateVertexArrays(n, Ptr(arrays))
    }

    override fun glCreateVertexArrays(n: Int, arrays: Ptr) {
        GL45.glCreateVertexArrays(arrays.asIntBuffer(n, glCreateVertexArrays))
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

    private val vertexarrayvertexbuffersBuffers = nullIntBuffer()
    private val vertexarrayvertexbuffersOffsets = PointerBuffer(nullByteBuffer())
    private val glvertexarrayvertexbuffersStrides = nullIntBuffer()

    override fun glVertexArrayVertexBuffers(
        vaobj: Int,
        first: Int,
        count: Int,
        buffers: Long,
        offsets: Long,
        strides: Long
    ) {
        glVertexArrayVertexBuffers(
            vaobj,
            first,
            count,
            Ptr(buffers),
            Ptr(offsets),
            Ptr(strides)
        )
    }

    override fun glVertexArrayVertexBuffers(
        vaobj: Int,
        first: Int,
        count: Int,
        buffers: Ptr,
        offsets: Ptr,
        strides: Ptr
    ) {
        GL45.glVertexArrayVertexBuffers(
            vaobj,
            first,
            count,
            buffers.asIntBuffer(count, vertexarrayvertexbuffersBuffers),
            offsets.asPointerBuffer(count, vertexarrayvertexbuffersOffsets),
            strides.asIntBuffer(count, glvertexarrayvertexbuffersStrides)
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

    private val glCreateSamplers = nullIntBuffer()

    override fun glCreateSamplers(n: Int, samplers: Long) {
        glCreateSamplers(n, Ptr(samplers))
    }

    override fun glCreateSamplers(n: Int, samplers: Ptr) {
        GL45.glCreateSamplers(samplers.asIntBuffer(n, glCreateSamplers))
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
