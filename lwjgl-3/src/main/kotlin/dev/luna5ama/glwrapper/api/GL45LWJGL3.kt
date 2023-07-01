package dev.luna5ama.glwrapper.api

import dev.luna5ama.kmogus.Arr
import org.lwjgl.opengl.GL45C

open class GL45LWJGL3(override val tempArr: Arr) : IGL45 {
    override fun glClipControl(origin: Int, depth: Int) {
        GL45C.glClipControl(origin, depth)
    }

    override fun glCreateBuffers(n: Int, buffers: Long) {
        GL45C.nglCreateBuffers(n, buffers)
    }

    override fun glNamedBufferStorage(buffer: Int, size: Long, data: Long, flags: Int) {
        GL45C.nglNamedBufferStorage(buffer, size, data, flags)
    }

    override fun glNamedBufferData(buffer: Int, size: Long, data: Long, usage: Int) {
        GL45C.nglNamedBufferData(buffer, size, data, usage)
    }

    override fun glNamedBufferSubData(buffer: Int, offset: Long, size: Long, data: Long) {
        GL45C.nglNamedBufferSubData(buffer, offset, size, data)
    }

    override fun glCopyNamedBufferSubData(
        readBuffer: Int,
        writeBuffer: Int,
        readOffset: Long,
        writeOffset: Long,
        size: Long
    ) {
        GL45C.glCopyNamedBufferSubData(readBuffer, writeBuffer, readOffset, writeOffset, size)
    }

    override fun glClearNamedBufferData(buffer: Int, internalFormat: Int, format: Int, type: Int, data: Long) {
        GL45C.nglClearNamedBufferData(buffer, internalFormat, format, type, data)
    }

    override fun glClearNamedBufferSubData(
        buffer: Int,
        internalFormat: Int,
        offset: Long,
        size: Long,
        format: Int,
        type: Int,
        data: Long
    ) {
        GL45C.nglClearNamedBufferSubData(buffer, internalFormat, offset, size, format, type, data)
    }

    override fun glMapNamedBufferRangeUnsafe(buffer: Int, offset: Long, length: Long, access: Int): Long {
        return GL45C.nglMapNamedBufferRange(buffer, offset, length, access)
    }

    override fun glUnmapNamedBuffer(buffer: Int): Boolean {
        return GL45C.glUnmapNamedBuffer(buffer)
    }

    override fun glFlushMappedNamedBufferRange(buffer: Int, offset: Long, length: Long) {
        GL45C.glFlushMappedNamedBufferRange(buffer, offset, length)
    }

    override fun glGetNamedBufferParameteri(buffer: Int, pname: Int): Int {
        return GL45C.glGetNamedBufferParameteri(buffer, pname)
    }

    override fun glGetNamedBufferParameteriv(buffer: Int, pname: Int, params: Long) {
        GL45C.nglGetNamedBufferParameteriv(buffer, pname, params)
    }

    override fun glGetNamedBufferParameteri64(buffer: Int, pname: Int): Long {
        return GL45C.glGetNamedBufferParameteri64(buffer, pname)
    }

    override fun glGetNamedBufferParameteri64v(buffer: Int, pname: Int, params: Long) {
        GL45C.nglGetNamedBufferParameteri64v(buffer, pname, params)
    }

    override fun glGetNamedBufferSubData(buffer: Int, offset: Long, size: Long, data: Long) {
        GL45C.nglGetNamedBufferSubData(buffer, offset, size, data)
    }

    override fun glCreateFramebuffers(n: Int, framebuffers: Long) {
        GL45C.nglCreateFramebuffers(n, framebuffers)
    }

    override fun glNamedFramebufferRenderbuffer(
        framebuffer: Int,
        attachment: Int,
        renderbuffertarget: Int,
        renderbuffer: Int
    ) {
        GL45C.glNamedFramebufferRenderbuffer(framebuffer, attachment, renderbuffertarget, renderbuffer)
    }

    override fun glNamedFramebufferParameteri(framebuffer: Int, pname: Int, param: Int) {
        GL45C.glNamedFramebufferParameteri(framebuffer, pname, param)
    }

    override fun glNamedFramebufferTexture(framebuffer: Int, attachment: Int, texture: Int, level: Int) {
        GL45C.glNamedFramebufferTexture(framebuffer, attachment, texture, level)
    }

    override fun glNamedFramebufferTextureLayer(
        framebuffer: Int,
        attachment: Int,
        texture: Int,
        level: Int,
        layer: Int
    ) {
        GL45C.glNamedFramebufferTextureLayer(framebuffer, attachment, texture, level, layer)
    }

    override fun glNamedFramebufferDrawBuffer(framebuffer: Int, buf: Int) {
        GL45C.glNamedFramebufferDrawBuffer(framebuffer, buf)
    }

    override fun glNamedFramebufferDrawBuffers(framebuffer: Int, n: Int, bufs: Long) {
        GL45C.nglNamedFramebufferDrawBuffers(framebuffer, n, bufs)
    }

    override fun glNamedFramebufferReadBuffer(framebuffer: Int, src: Int) {
        GL45C.glNamedFramebufferReadBuffer(framebuffer, src)
    }

    override fun glInvalidateNamedFramebufferData(framebuffer: Int, numAttachments: Int, attachments: Long) {
        GL45C.nglInvalidateNamedFramebufferData(framebuffer, numAttachments, attachments)
    }

    override fun glInvalidateNamedFramebufferData(framebuffer: Int, vararg attachments: Int) {
        GL45C.glInvalidateNamedFramebufferData(framebuffer, attachments)
    }

    override fun glInvalidateNamedFramebufferSubData(
        framebuffer: Int,
        numAttachments: Int,
        attachments: Long,
        x: Int,
        y: Int,
        width: Int,
        height: Int
    ) {
        GL45C.nglInvalidateNamedFramebufferSubData(
            framebuffer,
            numAttachments,
            attachments,
            x,
            y,
            width,
            height
        )
    }

    override fun glInvalidateNamedFramebufferSubData(
        framebuffer: Int,
        vararg attachments: Int,
        x: Int,
        y: Int,
        width: Int,
        height: Int
    ) {
        GL45C.glInvalidateNamedFramebufferSubData(
            framebuffer,
            attachments,
            x,
            y,
            width,
            height
        )
    }

    override fun glClearNamedFramebufferiv(framebuffer: Int, buffer: Int, drawbuffer: Int, value: Long) {
        GL45C.nglClearNamedFramebufferiv(framebuffer, buffer, drawbuffer, value)
    }

    override fun glClearNamedFramebufferuiv(framebuffer: Int, buffer: Int, drawbuffer: Int, value: Long) {
        GL45C.nglClearNamedFramebufferuiv(framebuffer, buffer, drawbuffer, value)
    }

    override fun glClearNamedFramebufferfv(framebuffer: Int, buffer: Int, drawbuffer: Int, value: Long) {
        GL45C.nglClearNamedFramebufferfv(framebuffer, buffer, drawbuffer, value)
    }

    override fun glClearNamedFramebufferfi(framebuffer: Int, buffer: Int, drawbuffer: Int, depth: Float, stencil: Int) {
        GL45C.glClearNamedFramebufferfi(framebuffer, drawbuffer, buffer, depth, stencil)
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
        GL45C.glBlitNamedFramebuffer(
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
        return GL45C.glCheckNamedFramebufferStatus(framebuffer, target)
    }

    override fun glGetNamedFramebufferParameteri(framebuffer: Int, pname: Int): Int {
        return GL45C.glGetNamedFramebufferParameteri(framebuffer, pname)
    }

    override fun glGetNamedFramebufferParameteriv(framebuffer: Int, pname: Int, params: Long) {
        GL45C.nglGetNamedFramebufferParameteriv(framebuffer, pname, params)
    }

    override fun glGetNamedFramebufferAttachmentParameteri(framebuffer: Int, attachment: Int, pname: Int): Int {
        return GL45C.glGetNamedFramebufferAttachmentParameteri(framebuffer, attachment, pname)
    }

    override fun glGetNamedFramebufferAttachmentParameteriv(
        framebuffer: Int,
        attachment: Int,
        pname: Int,
        params: Long
    ) {
        GL45C.nglGetNamedFramebufferAttachmentParameteriv(framebuffer, attachment, pname, params)
    }

    override fun glCreateRenderbuffers(n: Int, renderbuffers: Long) {
        GL45C.nglCreateRenderbuffers(n, renderbuffers)
    }

    override fun glNamedRenderbufferStorage(renderbuffer: Int, internalformat: Int, width: Int, height: Int) {
        GL45C.glNamedRenderbufferStorage(renderbuffer, internalformat, width, height)
    }

    override fun glNamedRenderbufferStorageMultisample(
        renderbuffer: Int,
        samples: Int,
        internalformat: Int,
        width: Int,
        height: Int
    ) {
        GL45C.glNamedRenderbufferStorageMultisample(renderbuffer, samples, internalformat, width, height)
    }

    override fun glGetNamedRenderbufferParameteri(renderbuffer: Int, pname: Int): Int {
        return GL45C.glGetNamedRenderbufferParameteri(renderbuffer, pname)
    }

    override fun glGetNamedRenderbufferParameteriv(renderbuffer: Int, pname: Int, params: Long) {
        GL45C.nglGetNamedRenderbufferParameteriv(renderbuffer, pname, params)
    }

    override fun glCreateTextures(target: Int, n: Int, textures: Long) {
        GL45C.nglCreateTextures(target, n, textures)
    }

    override fun glTextureBuffer(texture: Int, internalformat: Int, buffer: Int) {
        GL45C.glTextureBuffer(texture, internalformat, buffer)
    }

    override fun glTextureBufferRange(texture: Int, internalformat: Int, buffer: Int, offset: Long, size: Long) {
        GL45C.glTextureBufferRange(texture, internalformat, buffer, offset, size)
    }

    override fun glTextureStorage1D(texture: Int, levels: Int, internalformat: Int, width: Int) {
        GL45C.glTextureStorage1D(texture, levels, internalformat, width)
    }

    override fun glTextureStorage2D(texture: Int, levels: Int, internalformat: Int, width: Int, height: Int) {
        GL45C.glTextureStorage2D(texture, levels, internalformat, width, height)
    }

    override fun glTextureStorage3D(
        texture: Int,
        levels: Int,
        internalformat: Int,
        width: Int,
        height: Int,
        depth: Int
    ) {
        GL45C.glTextureStorage3D(texture, levels, internalformat, width, height, depth)
    }

    override fun glTextureStorage2DMultisample(
        texture: Int,
        samples: Int,
        internalformat: Int,
        width: Int,
        height: Int,
        fixedsamplelocations: Boolean
    ) {
        GL45C.glTextureStorage2DMultisample(texture, samples, internalformat, width, height, fixedsamplelocations)
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
        GL45C.glTextureStorage3DMultisample(
            texture,
            samples,
            internalformat,
            width,
            height,
            depth,
            fixedsamplelocations
        )
    }

    override fun glTextureSubImage1D(
        texture: Int,
        level: Int,
        xoffset: Int,
        width: Int,
        format: Int,
        type: Int,
        pixels: Long
    ) {
        GL45C.glTextureSubImage1D(texture, level, xoffset, width, format, type, pixels)
    }

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
        GL45C.glTextureSubImage2D(texture, level, xoffset, yoffset, width, height, format, type, pixels)
    }

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
        GL45C.glTextureSubImage3D(texture, level, xoffset, yoffset, zoffset, width, height, depth, format, type, pixels)
    }

    override fun glCompressedTextureSubImage1D(
        texture: Int,
        level: Int,
        xoffset: Int,
        width: Int,
        format: Int,
        imageSize: Int,
        data: Long
    ) {
        GL45C.glCompressedTextureSubImage1D(texture, level, xoffset, width, format, imageSize, data)
    }

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
        GL45C.glCompressedTextureSubImage2D(texture, level, xoffset, yoffset, width, height, format, imageSize, data)
    }

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
        GL45C.glCompressedTextureSubImage3D(
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
            data
        )
    }

    override fun glCopyTextureSubImage1D(texture: Int, level: Int, xoffset: Int, x: Int, y: Int, width: Int) {
        GL45C.glCopyTextureSubImage1D(texture, level, xoffset, x, y, width)
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
        GL45C.glCopyTextureSubImage2D(texture, level, xoffset, yoffset, x, y, width, height)
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
        GL45C.glCopyTextureSubImage3D(texture, level, xoffset, yoffset, zoffset, x, y, width, height)
    }

    override fun glTextureParameterf(texture: Int, pname: Int, param: Float) {
        GL45C.glTextureParameterf(texture, pname, param)
    }

    override fun glTextureParameteri(texture: Int, pname: Int, param: Int) {
        GL45C.glTextureParameteri(texture, pname, param)
    }

    override fun glTextureParameterfv(texture: Int, pname: Int, params: Long) {
        GL45C.nglTextureParameterfv(texture, pname, params)
    }

    override fun glTextureParameteriv(texture: Int, pname: Int, params: Long) {
        GL45C.nglTextureParameteriv(texture, pname, params)
    }

    override fun glTextureParameterIiv(texture: Int, pname: Int, params: Long) {
        GL45C.nglTextureParameterIiv(texture, pname, params)
    }

    override fun glTextureParameterIuiv(texture: Int, pname: Int, params: Long) {
        GL45C.nglTextureParameterIuiv(texture, pname, params)
    }

    override fun glGenerateTextureMipmap(texture: Int) {
        GL45C.glGenerateTextureMipmap(texture)
    }

    override fun glBindTextureUnit(unit: Int, texture: Int) {
        GL45C.glBindTextureUnit(unit, texture)
    }

    override fun glGetTextureImage(texture: Int, level: Int, format: Int, type: Int, bufSize: Int, pixels: Long) {
        GL45C.nglGetTextureImage(texture, level, format, type, bufSize, pixels)
    }

    override fun glGetCompressedTextureImage(texture: Int, level: Int, bufSize: Int, pixels: Long) {
        GL45C.nglGetCompressedTextureImage(texture, level, bufSize, pixels)
    }

    override fun glGetTextureLevelParameterf(texture: Int, level: Int, pname: Int): Float {
        return GL45C.glGetTextureLevelParameterf(texture, level, pname)
    }

    override fun glGetTextureLevelParameteri(texture: Int, level: Int, pname: Int): Int {
        return GL45C.glGetTextureLevelParameteri(texture, level, pname)
    }

    override fun glGetTextureParameterf(texture: Int, pname: Int): Float {
        return GL45C.glGetTextureParameterf(texture, pname)
    }

    override fun glGetTextureParameteri(texture: Int, pname: Int): Int {
        return GL45C.glGetTextureParameteri(texture, pname)
    }

    override fun glGetTextureLevelParameterfv(texture: Int, level: Int, pname: Int, params: Long) {
        GL45C.nglGetTextureLevelParameterfv(texture, level, pname, params)
    }

    override fun glGetTextureLevelParameteriv(texture: Int, level: Int, pname: Int, params: Long) {
        GL45C.nglGetTextureLevelParameteriv(texture, level, pname, params)
    }

    override fun glGetTextureParameterfv(texture: Int, pname: Int, params: Long) {
        GL45C.nglGetTextureParameterfv(texture, pname, params)
    }

    override fun glGetTextureParameteriv(texture: Int, pname: Int, params: Long) {
        GL45C.nglGetTextureParameteriv(texture, pname, params)
    }

    override fun glGetTextureParameterIiv(texture: Int, pname: Int, params: Long) {
        GL45C.nglGetTextureParameterIiv(texture, pname, params)
    }

    override fun glGetTextureParameterIuiv(texture: Int, pname: Int, params: Long) {
        GL45C.nglGetTextureParameterIuiv(texture, pname, params)
    }

    override fun glCreateVertexArrays(n: Int, arrays: Long) {
        GL45C.nglCreateVertexArrays(n, arrays)
    }

    override fun glDisableVertexArrayAttrib(vaobj: Int, index: Int) {
        GL45C.glDisableVertexArrayAttrib(vaobj, index)
    }

    override fun glEnableVertexArrayAttrib(vaobj: Int, index: Int) {
        GL45C.glEnableVertexArrayAttrib(vaobj, index)
    }

    override fun glVertexArrayElementBuffer(vaobj: Int, buffer: Int) {
        GL45C.glVertexArrayElementBuffer(vaobj, buffer)
    }

    override fun glVertexArrayVertexBuffer(vaobj: Int, bindingindex: Int, buffer: Int, offset: Long, stride: Int) {
        GL45C.glVertexArrayVertexBuffer(vaobj, bindingindex, buffer, offset, stride)
    }

    override fun glVertexArrayVertexBuffers(
        vaobj: Int,
        first: Int,
        count: Int,
        buffers: Long,
        offsets: Long,
        strides: Long
    ) {
        GL45C.nglVertexArrayVertexBuffers(vaobj, first, count, buffers, offsets, strides)
    }

    override fun glVertexArrayAttribFormat(
        vaobj: Int,
        attribindex: Int,
        size: Int,
        type: Int,
        normalized: Boolean,
        relativeoffset: Int
    ) {
        GL45C.glVertexArrayAttribFormat(vaobj, attribindex, size, type, normalized, relativeoffset)
    }

    override fun glVertexArrayAttribIFormat(vaobj: Int, attribindex: Int, size: Int, type: Int, relativeoffset: Int) {
        GL45C.glVertexArrayAttribIFormat(vaobj, attribindex, size, type, relativeoffset)
    }

    override fun glVertexArrayAttribLFormat(vaobj: Int, attribindex: Int, size: Int, type: Int, relativeoffset: Int) {
        GL45C.glVertexArrayAttribLFormat(vaobj, attribindex, size, type, relativeoffset)
    }

    override fun glVertexArrayAttribBinding(vaobj: Int, attribindex: Int, bindingindex: Int) {
        GL45C.glVertexArrayAttribBinding(vaobj, attribindex, bindingindex)
    }

    override fun glVertexArrayBindingDivisor(vaobj: Int, bindingindex: Int, divisor: Int) {
        GL45C.glVertexArrayBindingDivisor(vaobj, bindingindex, divisor)
    }

    override fun glCreateSamplers(n: Int, samplers: Long) {
        GL45C.nglCreateSamplers(n, samplers)
    }

    override fun glMemoryBarrierByRegion(barriers: Int) {
        GL45C.glMemoryBarrierByRegion(barriers)
    }

    override fun glTextureBarrier() {
        GL45C.glTextureBarrier()
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
        GL45C.glReadnPixels(x, y, width, height, format, type, bufSize, data)
    }


    override fun glCreateBuffers(): Int {
        return GL45C.glCreateBuffers()
    }

    override fun glCreateFramebuffers(): Int {
        return GL45C.glCreateFramebuffers()
    }

    override fun glCreateRenderbuffers(): Int {
        return GL45C.glCreateRenderbuffers()
    }

    override fun glCreateTextures(target: Int): Int {
        return GL45C.glCreateTextures(target)
    }

    override fun glCreateVertexArrays(): Int {
        return GL45C.glCreateVertexArrays()
    }

    override fun glCreateSamplers(): Int {
        return GL45C.glCreateSamplers()
    }
}
