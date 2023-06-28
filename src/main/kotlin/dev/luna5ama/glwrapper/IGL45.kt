@file:JvmName("GL45")

package dev.luna5ama.glwrapper

import dev.luna5ama.kmogus.Ptr
import dev.luna5ama.kmogus.ensureCapacity
import dev.luna5ama.kmogus.memcpy

const val GL_NEGATIVE_ONE_TO_ONE = 0x935E
const val GL_ZERO_TO_ONE = 0x935F

const val GL_CLIP_ORIGIN = 0x935C
const val GL_CLIP_DEPTH_MODE = 0x935D

const val GL_QUERY_WAIT_INVERTED = 0x8E17
const val GL_QUERY_NO_WAIT_INVERTED = 0x8E18
const val GL_QUERY_BY_REGION_WAIT_INVERTED = 0x8E19
const val GL_QUERY_BY_REGION_NO_WAIT_INVERTED = 0x8E1A

const val GL_MAX_CULL_DISTANCES = 0x82F9
const val GL_MAX_COMBINED_CLIP_AND_CULL_DISTANCES = 0x82FA

const val GL_TEXTURE_TARGET = 0x1006

const val GL_QUERY_TARGET = 0x82EA

const val GL_CONTEXT_RELEASE_BEHAVIOR = 0x82FB

const val GL_CONTEXT_RELEASE_BEHAVIOR_FLUSH = 0x82FC

const val GL_GUILTY_CONTEXT_RESET = 0x8253
const val GL_INNOCENT_CONTEXT_RESET = 0x8254
const val GL_UNKNOWN_CONTEXT_RESET = 0x8255

const val GL_RESET_NOTIFICATION_STRATEGY = 0x8256

const val GL_LOSE_CONTEXT_ON_RESET = 0x8252
const val GL_NO_RESET_NOTIFICATION = 0x8261

const val GL_CONTEXT_FLAG_ROBUST_ACCESS_BIT = 0x4

const val GL_CONTEXT_LOST = 0x507


interface IGL45 : GLBase {
    fun glClipControl(origin: Int, depth: Int)

    @Unsafe
    fun glCreateBuffers(n: Int, buffers: Long)

    @Unsafe
    fun glNamedBufferStorage(buffer: Int, size: Long, data: Long, flags: Int)

    @Unsafe
    fun glNamedBufferData(buffer: Int, size: Long, data: Long, usage: Int)

    @Unsafe
    fun glNamedBufferSubData(buffer: Int, offset: Long, size: Long, data: Long)

    fun glCopyNamedBufferSubData(readBuffer: Int, writeBuffer: Int, readOffset: Long, writeOffset: Long, size: Long)

    @Unsafe
    fun glClearNamedBufferData(buffer: Int, internalFormat: Int, format: Int, type: Int, data: Long)

    @Unsafe
    fun glClearNamedBufferSubData(
        buffer: Int,
        internalFormat: Int,
        offset: Long,
        size: Long,
        format: Int,
        type: Int,
        data: Long
    )

    @Unsafe
    fun glMapNamedBufferUnsafe(buffer: Int, access: Int): Long

    @Unsafe
    fun glMapNamedBufferRangeUnsafe(buffer: Int, offset: Long, length: Long, access: Int): Long

    fun glUnmapNamedBuffer(buffer: Int): Boolean

    fun glFlushMappedNamedBufferRange(buffer: Int, offset: Long, length: Long)

    @Unsafe
    fun glGetNamedBufferParameteriv(buffer: Int, pname: Int, params: Long)

    @Unsafe
    fun glGetNamedBufferParameteri64v(buffer: Int, pname: Int, params: Long)

    @Unsafe
    fun glGetNamedBufferSubData(buffer: Int, offset: Long, size: Long, data: Long)

    @Unsafe
    fun glCreateFramebuffers(n: Int, framebuffers: Long)
    fun glNamedFramebufferRenderbuffer(framebuffer: Int, attachment: Int, renderbuffertarget: Int, renderbuffer: Int)
    fun glNamedFramebufferParameteri(framebuffer: Int, pname: Int, param: Int)
    fun glNamedFramebufferTexture(framebuffer: Int, attachment: Int, texture: Int, level: Int)
    fun glNamedFramebufferTextureLayer(framebuffer: Int, attachment: Int, texture: Int, level: Int, layer: Int)
    fun glNamedFramebufferDrawBuffer(framebuffer: Int, buf: Int)

    @Unsafe
    fun glNamedFramebufferDrawBuffers(framebuffer: Int, n: Int, bufs: Long)

    fun glNamedFramebufferReadBuffer(framebuffer: Int, src: Int)

    @Unsafe
    fun glInvalidateNamedFramebufferData(framebuffer: Int, numAttachments: Int, attachments: Long)

    @Unsafe
    fun glInvalidateNamedFramebufferSubData(
        framebuffer: Int,
        numAttachments: Int,
        attachments: Long,
        x: Int,
        y: Int,
        width: Int,
        height: Int
    )

    @Unsafe
    fun glClearNamedFramebufferiv(framebuffer: Int, buffer: Int, drawbuffer: Int, value: Long)

    @Unsafe
    fun glClearNamedFramebufferuiv(framebuffer: Int, buffer: Int, drawbuffer: Int, value: Long)

    @Unsafe
    fun glClearNamedFramebufferfv(framebuffer: Int, buffer: Int, drawbuffer: Int, value: Long)

    @Unsafe
    fun glClearNamedFramebufferfi(framebuffer: Int, buffer: Int, depth: Float, stencil: Int)
    fun glBlitNamedFramebuffer(
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
    )

    fun glCheckNamedFramebufferStatus(framebuffer: Int, target: Int): Int

    @Unsafe
    fun glGetNamedFramebufferParameteriv(framebuffer: Int, pname: Int, params: Long)

    @Unsafe
    fun glGetNamedFramebufferAttachmentParameteriv(framebuffer: Int, attachment: Int, pname: Int, params: Long)

    @Unsafe
    fun glCreateRenderbuffers(n: Int, renderbuffers: Long)
    fun glNamedRenderbufferStorage(renderbuffer: Int, internalformat: Int, width: Int, height: Int)
    fun glNamedRenderbufferStorageMultisample(
        renderbuffer: Int,
        samples: Int,
        internalformat: Int,
        width: Int,
        height: Int
    )

    @Unsafe
    fun glGetNamedRenderbufferParameteriv(renderbuffer: Int, pname: Int, params: Long)

    @Unsafe
    fun glCreateTextures(target: Int, n: Int, textures: Long)

    fun glTextureBuffer(texture: Int, internalformat: Int, buffer: Int)
    fun glTextureBufferRange(texture: Int, internalformat: Int, buffer: Int, offset: Long, size: Long)

    fun glTextureStorage1D(texture: Int, levels: Int, internalformat: Int, width: Int)
    fun glTextureStorage2D(texture: Int, levels: Int, internalformat: Int, width: Int, height: Int)
    fun glTextureStorage3D(texture: Int, levels: Int, internalformat: Int, width: Int, height: Int, depth: Int)
    fun glTextureStorage2DMultisample(
        texture: Int,
        samples: Int,
        internalformat: Int,
        width: Int,
        height: Int,
        fixedsamplelocations: Boolean
    )

    fun glTextureStorage3DMultisample(
        texture: Int,
        samples: Int,
        internalformat: Int,
        width: Int,
        height: Int,
        depth: Int,
        fixedsamplelocations: Boolean
    )

    @Unsafe
    fun glTextureSubImage1D(texture: Int, level: Int, xoffset: Int, width: Int, format: Int, type: Int, pixels: Long)

    @Unsafe
    fun glTextureSubImage2D(
        texture: Int,
        level: Int,
        xoffset: Int,
        yoffset: Int,
        width: Int,
        height: Int,
        format: Int,
        type: Int,
        pixels: Long
    )

    @Unsafe
    fun glTextureSubImage3D(
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
    )

    @Unsafe
    fun glCompressedTextureSubImage1D(
        texture: Int,
        level: Int,
        xoffset: Int,
        width: Int,
        format: Int,
        imageSize: Int,
        data: Long
    )

    @Unsafe
    fun glCompressedTextureSubImage2D(
        texture: Int,
        level: Int,
        xoffset: Int,
        yoffset: Int,
        width: Int,
        height: Int,
        format: Int,
        imageSize: Int,
        data: Long
    )

    @Unsafe
    fun glCompressedTextureSubImage3D(
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
    )

    fun glCopyTextureSubImage1D(texture: Int, level: Int, xoffset: Int, x: Int, y: Int, width: Int)
    fun glCopyTextureSubImage2D(
        texture: Int,
        level: Int,
        xoffset: Int,
        yoffset: Int,
        x: Int,
        y: Int,
        width: Int,
        height: Int
    )

    fun glCopyTextureSubImage3D(
        texture: Int,
        level: Int,
        xoffset: Int,
        yoffset: Int,
        zoffset: Int,
        x: Int,
        y: Int,
        width: Int,
        height: Int
    )

    fun glTextureParameterf(texture: Int, pname: Int, param: Float)

    fun glTextureParameteri(texture: Int, pname: Int, param: Int)

    @Unsafe
    fun glTextureParameterfv(texture: Int, pname: Int, params: Long)

    @Unsafe
    fun glTextureParameteriv(texture: Int, pname: Int, params: Long)

    @Unsafe
    fun glTextureParameterIiv(texture: Int, pname: Int, params: Long)

    @Unsafe
    fun glTextureParameterIuiv(texture: Int, pname: Int, params: Long)

    fun glGenerateTextureMipmap(texture: Int)
    fun glBindTextureUnit(unit: Int, texture: Int)

    @Unsafe
    fun glGetTextureImage(texture: Int, level: Int, format: Int, type: Int, bufSize: Int, pixels: Long)

    @Unsafe
    fun glGetCompressedTextureImage(texture: Int, level: Int, bufSize: Int, pixels: Long)

    fun glGetTextureLevelParameterf(texture: Int, level: Int, pname: Int): Float
    fun glGetTextureLevelParameteri(texture: Int, level: Int, pname: Int): Int

    @Unsafe
    fun glGetTextureLevelParameterfv(texture: Int, level: Int, pname: Int, params: Long)

    @Unsafe
    fun glGetTextureLevelParameteriv(texture: Int, level: Int, pname: Int, params: Long)

    fun glGetTextureParameterf(texture: Int, pname: Int): Float
    fun glGetTextureParameteri(texture: Int, pname: Int): Int

    @Unsafe
    fun glGetTextureParameterfv(texture: Int, pname: Int, params: Long)

    @Unsafe
    fun glGetTextureParameteriv(texture: Int, pname: Int, params: Long)

    @Unsafe
    fun glGetTextureParameterIiv(texture: Int, pname: Int, params: Long)

    @Unsafe
    fun glGetTextureParameterIuiv(texture: Int, pname: Int, params: Long)

    @Unsafe
    fun glCreateVertexArrays(n: Int, arrays: Long)
    fun glDisableVertexArrayAttrib(vaobj: Int, index: Int)
    fun glEnableVertexArrayAttrib(vaobj: Int, index: Int)
    fun glVertexArrayElementBuffer(vaobj: Int, buffer: Int)
    fun glVertexArrayVertexBuffer(vaobj: Int, bindingindex: Int, buffer: Int, offset: Long, stride: Int)

    @Unsafe
    fun glVertexArrayVertexBuffers(vaobj: Int, first: Int, count: Int, buffers: Long, offsets: Long, strides: Long)
    fun glVertexArrayAttribFormat(
        vaobj: Int,
        attribindex: Int,
        size: Int,
        type: Int,
        normalized: Boolean,
        relativeoffset: Int
    )

    fun glVertexArrayAttribIFormat(vaobj: Int, attribindex: Int, size: Int, type: Int, relativeoffset: Int)
    fun glVertexArrayAttribLFormat(vaobj: Int, attribindex: Int, size: Int, type: Int, relativeoffset: Int)
    fun glVertexArrayAttribBinding(vaobj: Int, attribindex: Int, bindingindex: Int)
    fun glVertexArrayBindingDivisor(vaobj: Int, bindingindex: Int, divisor: Int)

    @Unsafe
    fun glCreateSamplers(n: Int, samplers: Long)

    fun glMemoryBarrierByRegion(barriers: Int)

    fun glTextureBarrier()

    @Unsafe
    fun glReadnPixels(x: Int, y: Int, width: Int, height: Int, format: Int, type: Int, bufSize: Int, data: Long)


    fun glCreateBuffers(n: Int, buffers: Ptr) {
        glCreateBuffers(n, buffers.address)
    }

    fun glCreateBuffers(): Int {
        val ptr = tempArr.ptr
        glCreateBuffers(1, ptr)
        return ptr.getInt()
    }

    fun glNamedBufferStorage(buffer: Int, size: Long, data: Ptr, flags: Int) {
        glNamedBufferStorage(buffer, size, data.address, flags)
    }

    fun glNamedBufferData(buffer: Int, size: Long, data: Ptr, usage: Int) {
        glNamedBufferData(buffer, size, data.address, usage)
    }

    fun glNamedBufferSubData(buffer: Int, offset: Long, size: Long, data: Ptr) {
        glNamedBufferSubData(buffer, offset, size, data.address)
    }

    fun glClearNamedBufferData(buffer: Int, internalFormat: Int, format: Int, type: Int, data: Ptr) {
        glClearNamedBufferData(buffer, internalFormat, format, type, data.address)
    }

    fun glClearNamedBufferSubData(
        buffer: Int,
        internalFormat: Int,
        offset: Long,
        size: Long,
        format: Int,
        type: Int,
        data: Ptr
    ) {
        glClearNamedBufferSubData(buffer, internalFormat, offset, size, format, type, data.address)
    }

    fun glMapNamedBuffer(buffer: Int, access: Int): Ptr {
        return Ptr(glMapNamedBufferUnsafe(buffer, access))
    }

    fun glMapNamedBufferRange(buffer: Int, offset: Long, length: Long, access: Int): Ptr {
        return Ptr(glMapNamedBufferRangeUnsafe(buffer, offset, length, access))
    }

    fun glGetNamedBufferParameteriv(buffer: Int, pname: Int, params: Ptr) {
        glGetNamedBufferParameteriv(buffer, pname, params.address)
    }

    fun glGetNamedBufferParameteri(buffer: Int, pname: Int): Int {
        val ptr = tempArr.ptr
        glGetNamedBufferParameteriv(buffer, pname, ptr)
        return ptr.getInt()
    }

    fun glGetNamedBufferParameteri64v(buffer: Int, pname: Int, params: Ptr) {
        glGetNamedBufferParameteri64v(buffer, pname, params.address)
    }

    fun glGetNamedBufferParameteri64(buffer: Int, pname: Int): Long {
        val ptr = tempArr.ptr
        glGetNamedBufferParameteri64v(buffer, pname, ptr)
        return ptr.getLong()
    }

    fun glGetNamedBufferSubData(buffer: Int, offset: Long, size: Long, data: Ptr) {
        glGetNamedBufferSubData(buffer, offset, size, data.address)
    }

    fun glCreateFramebuffers(n: Int, framebuffers: Ptr) {
        glCreateFramebuffers(n, framebuffers.address)
    }

    fun glCreateFramebuffers(): Int {
        val ptr = tempArr.ptr
        glCreateFramebuffers(1, ptr)
        return ptr.getInt()
    }

    fun glNamedFramebufferDrawBuffers(framebuffer: Int, n: Int, bufs: Ptr) {
        glNamedFramebufferDrawBuffers(framebuffer, n, bufs.address)
    }

    fun glNamedFramebufferDrawBuffers(framebuffer: Int, buf: Int) {
        val ptr = tempArr.ptr
        ptr.setInt(buf)
        glNamedFramebufferDrawBuffers(framebuffer, 1, ptr)
    }

    fun glNamedFramebufferDrawBuffers(framebuffer: Int, buf1: Int, buf2: Int) {
        val ptr = tempArr.ptr
        ptr.setInt(buf1)
        ptr.setInt(4, buf2)
        glNamedFramebufferDrawBuffers(framebuffer, 2, ptr)
    }

    fun glNamedFramebufferDrawBuffers(framebuffer: Int, buf1: Int, buf2: Int, buf3: Int) {
        val ptr = tempArr.ptr
        ptr.setInt(buf1)
        ptr.setInt(4, buf2)
        ptr.setInt(8, buf3)
        glNamedFramebufferDrawBuffers(framebuffer, 3, ptr)
    }

    fun glNamedFramebufferDrawBuffers(framebuffer: Int, buf1: Int, buf2: Int, buf3: Int, buf4: Int) {
        val ptr = tempArr.ptr
        ptr.setInt(buf1)
        ptr.setInt(4, buf2)
        ptr.setInt(8, buf3)
        ptr.setInt(12, buf4)
        glNamedFramebufferDrawBuffers(framebuffer, 4, ptr)
    }

    fun glNamedFramebufferDrawBuffers(framebuffer: Int, vararg bufs: Int) {
        val l = bufs.size * 4L
        tempArr.ensureCapacity(l, true)
        val ptr = tempArr.ptr
        memcpy(bufs, ptr, l)
        glNamedFramebufferDrawBuffers(framebuffer, bufs.size, ptr)
    }

    fun glNamedFramebufferDrawBuffers(framebuffer: Int, bufs: IntArray, offset: Int, length: Int) {
        val l = length * 4L
        tempArr.ensureCapacity(l, true)
        val ptr = tempArr.ptr
        memcpy(bufs, offset * 4L, ptr, 0L, l)
        glNamedFramebufferDrawBuffers(framebuffer, bufs.size, ptr)
    }

    fun glInvalidateNamedFramebufferData(framebuffer: Int, numAttachments: Int, attachments: Ptr) {
        glInvalidateNamedFramebufferData(framebuffer, numAttachments, attachments.address)
    }

    fun glInvalidateNamedFramebufferData(framebuffer: Int, attachment: Int) {
        val ptr = tempArr.ptr
        ptr.setInt(attachment)
        glInvalidateNamedFramebufferData(framebuffer, 1, ptr)
    }

    fun glInvalidateNamedFramebufferData(framebuffer: Int, attachment1: Int, attachment2: Int) {
        val ptr = tempArr.ptr
        ptr.setInt(attachment1)
        ptr.setInt(4, attachment2)
        glInvalidateNamedFramebufferData(framebuffer, 2, ptr)
    }

    fun glInvalidateNamedFramebufferData(framebuffer: Int, attachment1: Int, attachment2: Int, attachment3: Int) {
        val ptr = tempArr.ptr
        ptr.setInt(attachment1)
        ptr.setInt(4, attachment2)
        ptr.setInt(8, attachment3)
        glInvalidateNamedFramebufferData(framebuffer, 3, ptr)
    }

    fun glInvalidateNamedFramebufferData(
        framebuffer: Int,
        attachment1: Int,
        attachment2: Int,
        attachment3: Int,
        attachment4: Int
    ) {
        val ptr = tempArr.ptr
        ptr.setInt(attachment1)
        ptr.setInt(4, attachment2)
        ptr.setInt(8, attachment3)
        ptr.setInt(12, attachment4)
        glInvalidateNamedFramebufferData(framebuffer, 4, ptr)
    }

    fun glInvalidateNamedFramebufferData(framebuffer: Int, vararg attachments: Int) {
        val l = attachments.size * 4L
        tempArr.ensureCapacity(l, true)
        val ptr = tempArr.ptr
        memcpy(attachments, ptr, l)
        glInvalidateNamedFramebufferData(framebuffer, attachments.size, ptr)
    }

    fun glInvalidateNamedFramebufferData(framebuffer: Int, attachments: IntArray, offset: Int, length: Int) {
        val l = length * 4L
        tempArr.ensureCapacity(l, true)
        val ptr = tempArr.ptr
        memcpy(attachments, offset * 4L, ptr, 0L, l)
        glInvalidateNamedFramebufferData(framebuffer, attachments.size, ptr)
    }

    fun glInvalidateNamedFramebufferSubData(
        framebuffer: Int,
        numAttachments: Int,
        attachments: Ptr,
        x: Int,
        y: Int,
        width: Int,
        height: Int
    ) {
        glInvalidateNamedFramebufferSubData(framebuffer, numAttachments, attachments.address, x, y, width, height)
    }

    fun glInvalidateNamedFramebufferSubData(
        framebuffer: Int,
        attachment: Int,
        x: Int,
        y: Int,
        width: Int,
        height: Int
    ) {
        val ptr = tempArr.ptr
        ptr.setInt(attachment)
        glInvalidateNamedFramebufferSubData(framebuffer, 1, ptr, x, y, width, height)
    }

    fun glInvalidateNamedFramebufferSubData(
        framebuffer: Int,
        attachment1: Int,
        attachment2: Int,
        x: Int,
        y: Int,
        width: Int,
        height: Int
    ) {
        val ptr = tempArr.ptr
        ptr.setInt(attachment1)
        ptr.setInt(4, attachment2)
        glInvalidateNamedFramebufferSubData(framebuffer, 2, ptr, x, y, width, height)
    }

    fun glInvalidateNamedFramebufferSubData(
        framebuffer: Int,
        attachment1: Int,
        attachment2: Int,
        attachment3: Int,
        x: Int,
        y: Int,
        width: Int,
        height: Int
    ) {
        val ptr = tempArr.ptr
        ptr.setInt(attachment1)
        ptr.setInt(4, attachment2)
        ptr.setInt(8, attachment3)
        glInvalidateNamedFramebufferSubData(framebuffer, 3, ptr, x, y, width, height)
    }

    fun glInvalidateNamedFramebufferSubData(
        framebuffer: Int,
        attachment1: Int,
        attachment2: Int,
        attachment3: Int,
        attachment4: Int,
        x: Int,
        y: Int,
        width: Int,
        height: Int
    ) {
        val ptr = tempArr.ptr
        ptr.setInt(attachment1)
        ptr.setInt(4, attachment2)
        ptr.setInt(8, attachment3)
        ptr.setInt(12, attachment4)
        glInvalidateNamedFramebufferSubData(framebuffer, 4, ptr, x, y, width, height)
    }

    fun glInvalidateNamedFramebufferSubData(
        framebuffer: Int,
        vararg attachments: Int,
        x: Int,
        y: Int,
        width: Int,
        height: Int
    ) {
        val l = attachments.size * 4L
        tempArr.ensureCapacity(l, true)
        val ptr = tempArr.ptr
        memcpy(attachments, ptr, l)
        glInvalidateNamedFramebufferSubData(framebuffer, attachments.size, ptr, x, y, width, height)
    }

    fun glInvalidateNamedFramebufferSubData(
        framebuffer: Int,
        attachments: IntArray,
        offset: Int,
        length: Int,
        x: Int,
        y: Int,
        width: Int,
        height: Int
    ) {
        val l = length * 4L
        tempArr.ensureCapacity(l, true)
        val ptr = tempArr.ptr
        memcpy(attachments, offset * 4L, ptr, 0L, l)
        glInvalidateNamedFramebufferSubData(framebuffer, attachments.size, ptr, x, y, width, height)
    }

    fun glClearNamedFramebufferiv(framebuffer: Int, buffer: Int, drawbuffer: Int, value: Ptr) {
        glClearNamedFramebufferiv(framebuffer, buffer, drawbuffer, value.address)
    }

    fun glClearNamedFramebufferi(framebuffer: Int, buffer: Int, drawbuffer: Int, stencil: Int) {
        val ptr = tempArr.ptr
        ptr.setInt(stencil)
        glClearNamedFramebufferiv(framebuffer, buffer, drawbuffer, ptr)
    }

    fun glClearNamedFramebufferiv(framebuffer: Int, buffer: Int, drawbuffer: Int, r: Int, g: Int, b: Int, a: Int) {
        val ptr = tempArr.ptr
        ptr.setInt(r)
        ptr.setInt(4, g)
        ptr.setInt(8, b)
        ptr.setInt(12, a)
        glClearNamedFramebufferiv(framebuffer, buffer, drawbuffer, ptr)
    }

    fun glClearNamedFramebufferuiv(framebuffer: Int, buffer: Int, drawbuffer: Int, value: Ptr) {
        glClearNamedFramebufferuiv(framebuffer, buffer, drawbuffer, value.address)
    }

    fun glClearNamedFramebufferui(framebuffer: Int, buffer: Int, drawbuffer: Int, r: Int, g: Int, b: Int, a: Int) {
        val ptr = tempArr.ptr
        ptr.setInt(r)
        ptr.setInt(4, g)
        ptr.setInt(8, b)
        ptr.setInt(12, a)
        glClearNamedFramebufferuiv(framebuffer, buffer, drawbuffer, ptr)
    }

    fun glClearNamedFramebufferfv(framebuffer: Int, buffer: Int, drawbuffer: Int, value: Ptr) {
        glClearNamedFramebufferfv(framebuffer, buffer, drawbuffer, value.address)
    }

    fun glClearNamedFramebufferf(framebuffer: Int, buffer: Int, drawbuffer: Int, stencil: Float) {
        val ptr = tempArr.ptr
        ptr.setFloat(stencil)
        glClearNamedFramebufferfv(framebuffer, buffer, drawbuffer, ptr)
    }

    fun glClearNamedFramebufferf(
        framebuffer: Int,
        buffer: Int,
        drawbuffer: Int,
        r: Float,
        g: Float,
        b: Float,
        a: Float
    ) {
        val ptr = tempArr.ptr
        ptr.setFloat(r)
        ptr.setFloat(4, g)
        ptr.setFloat(8, b)
        ptr.setFloat(12, a)
        glClearNamedFramebufferfv(framebuffer, buffer, drawbuffer, ptr)
    }

    fun glGetNamedFramebufferParameteriv(framebuffer: Int, pname: Int, params: Ptr) {
        glGetNamedFramebufferParameteriv(framebuffer, pname, params.address)
    }

    fun glGetNamedFramebufferParameteri(framebuffer: Int, pname: Int): Int {
        val ptr = tempArr.ptr
        glGetNamedFramebufferParameteriv(framebuffer, pname, ptr)
        return ptr.getInt()
    }

    fun glGetNamedFramebufferAttachmentParameteriv(framebuffer: Int, attachment: Int, pname: Int, params: Ptr) {
        glGetNamedFramebufferAttachmentParameteriv(framebuffer, attachment, pname, params.address)
    }

    fun glGetNamedFramebufferAttachmentParameteri(framebuffer: Int, attachment: Int, pname: Int): Int {
        val ptr = tempArr.ptr
        glGetNamedFramebufferAttachmentParameteriv(framebuffer, attachment, pname, ptr)
        return ptr.getInt()
    }

    fun glCreateRenderbuffers(n: Int, renderbuffers: Ptr) {
        glCreateRenderbuffers(n, renderbuffers.address)
    }

    fun glCreateRenderbuffers(): Int {
        val ptr = tempArr.ptr
        glCreateRenderbuffers(1, ptr)
        return ptr.getInt()
    }

    fun glGetNamedRenderbufferParameteriv(renderbuffer: Int, pname: Int, params: Ptr) {
        glGetNamedRenderbufferParameteriv(renderbuffer, pname, params.address)
    }

    fun glGetNamedRenderbufferParameteri(renderbuffer: Int, pname: Int): Int {
        val ptr = tempArr.ptr
        glGetNamedRenderbufferParameteriv(renderbuffer, pname, ptr)
        return ptr.getInt()
    }

    fun glCreateTextures(target: Int, n: Int, textures: Ptr) {
        glCreateTextures(target, n, textures.address)
    }

    fun glCreateTextures(target: Int): Int {
        val ptr = tempArr.ptr
        glCreateTextures(target, 1, ptr)
        return ptr.getInt()
    }

    fun glTextureSubImage1D(
        texture: Int,
        level: Int,
        xoffset: Int,
        width: Int,
        format: Int,
        type: Int,
        pixels: Ptr
    ) {
        glTextureSubImage1D(texture, level, xoffset, width, format, type, pixels.address)
    }

    fun glTextureSubImage2D(
        texture: Int,
        level: Int,
        xoffset: Int,
        yoffset: Int,
        width: Int,
        height: Int,
        format: Int,
        type: Int,
        pixels: Ptr
    ) {
        glTextureSubImage2D(texture, level, xoffset, yoffset, width, height, format, type, pixels.address)
    }

    fun glTextureSubImage3D(
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
        pixels: Ptr
    ) {
        glTextureSubImage3D(
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
            pixels.address
        )
    }

    fun glCompressedTextureSubImage1D(
        texture: Int,
        level: Int,
        xoffset: Int,
        width: Int,
        format: Int,
        imageSize: Int,
        data: Ptr
    ) {
        glCompressedTextureSubImage1D(texture, level, xoffset, width, format, imageSize, data.address)
    }

    fun glCompressedTextureSubImage2D(
        texture: Int,
        level: Int,
        xoffset: Int,
        yoffset: Int,
        width: Int,
        height: Int,
        format: Int,
        imageSize: Int,
        data: Ptr
    ) {
        glCompressedTextureSubImage2D(texture, level, xoffset, yoffset, width, height, format, imageSize, data.address)
    }

    fun glCompressedTextureSubImage3D(
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
        data: Ptr
    ) {
        glCompressedTextureSubImage3D(
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
            data.address
        )
    }

    fun glTextureParameterfv(texture: Int, pname: Int, param: Ptr) {
        glTextureParameterfv(texture, pname, param.address)
    }

    fun glTextureParameterfv(texture: Int, pname: Int, v0: Float, v1: Float, v2: Float, v3: Float) {
        val ptr = tempArr.ptr
        ptr.setFloat(v0)
        ptr.setFloat(4, v1)
        ptr.setFloat(8, v2)
        ptr.setFloat(12, v3)
        glTextureParameterfv(texture, pname, ptr)
    }

    fun glTextureParameteriv(texture: Int, pname: Int, param: Ptr) {
        glTextureParameteriv(texture, pname, param.address)
    }

    fun glTextureParameteriv(texture: Int, pname: Int, v0: Int, v1: Int, v2: Int, v3: Int) {
        val ptr = tempArr.ptr
        ptr.setInt(v0)
        ptr.setInt(4, v1)
        ptr.setInt(8, v2)
        ptr.setInt(12, v3)
        glTextureParameteriv(texture, pname, ptr)
    }

    fun glTextureParameterIiv(texture: Int, pname: Int, param: Ptr) {
        glTextureParameterIiv(texture, pname, param.address)
    }

    fun glTextureParameterIuiv(texture: Int, pname: Int, param: Ptr) {
        glTextureParameterIuiv(texture, pname, param.address)
    }

    fun glGetTextureImage(texture: Int, level: Int, format: Int, type: Int, bufSize: Int, pixels: Ptr) {
        glGetTextureImage(texture, level, format, type, bufSize, pixels.address)
    }

    fun glGetCompressedTextureImage(texture: Int, level: Int, bufSize: Int, pixels: Ptr) {
        glGetCompressedTextureImage(texture, level, bufSize, pixels.address)
    }

    fun glGetTextureParameterfv(texture: Int, pname: Int, params: Ptr) {
        glGetTextureParameterfv(texture, pname, params.address)
    }

    fun glGetTextureParameteriv(texture: Int, pname: Int, params: Ptr) {
        glGetTextureParameteriv(texture, pname, params.address)
    }

    fun glGetTextureParameterIiv(texture: Int, pname: Int, params: Ptr) {
        glGetTextureParameterIiv(texture, pname, params.address)
    }

    fun glGetTextureParameterIuiv(texture: Int, pname: Int, params: Ptr) {
        glGetTextureParameterIuiv(texture, pname, params.address)
    }

    fun glCreateVertexArrays(n: Int, arrays: Ptr) {
        glCreateVertexArrays(n, arrays.address)
    }

    fun glCreateVertexArrays(): Int {
        val ptr = tempArr.ptr
        glCreateVertexArrays(1, ptr)
        return ptr.getInt()
    }

    fun glVertexArrayVertexBuffers(vaobj: Int, first: Int, count: Int, buffers: Ptr, offsets: Ptr, strides: Ptr) {
        glVertexArrayVertexBuffers(vaobj, first, count, buffers.address, offsets.address, strides.address)
    }

    fun glVertexArrayVertexBuffers(
        vaobj: Int,
        first: Int,
        count: Int,
        buffers: IntArray,
        offsets: LongArray,
        strides: LongArray
    ) {
        val lInt = count * 4L
        val lLong = count * 8L
        tempArr.ensureCapacity(lInt + lLong + lInt, true)
        val ptrBuffers = tempArr.ptr
        val ptrOffsets = ptrBuffers + lInt
        val ptrStrides = ptrOffsets + lLong
        memcpy(buffers, ptrBuffers, lInt)
        memcpy(offsets, ptrOffsets, lLong)
        memcpy(strides, ptrStrides, lInt)
        glVertexArrayVertexBuffers(vaobj, first, count, ptrBuffers, ptrOffsets, ptrStrides)
    }

    fun glVertexArrayVertexBuffers(
        vaobj: Int,
        first: Int,
        count: Int,
        buffers: IntArray,
        offsets: LongArray,
        strides: IntArray,
        offset: Int
    ) {
        val lInt = count * 4L
        val lLong = count * 8L
        tempArr.ensureCapacity(lInt + lLong + lInt, true)
        val ptrBuffers = tempArr.ptr
        val ptrOffsets = ptrBuffers + lInt
        val ptrStrides = ptrOffsets + lLong
        memcpy(buffers, offset * 4L, ptrBuffers, 0L, lInt)
        memcpy(offsets, offset * 8L, ptrOffsets, 0L, lLong)
        memcpy(strides, offset * 8L, ptrStrides, 0L, lInt)
        glVertexArrayVertexBuffers(vaobj, first, count, ptrBuffers, ptrOffsets, ptrStrides)
    }

    fun glCreateSamplers(n: Int, samplers: Ptr) {
        glCreateSamplers(n, samplers.address)
    }

    fun glCreateSamplers(): Int {
        val ptr = tempArr.ptr
        glCreateSamplers(1, ptr)
        return ptr.getInt()
    }

    fun glReadnPixels(x: Int, y: Int, width: Int, height: Int, format: Int, type: Int, bufSize: Int, data: Ptr) {
        glReadnPixels(x, y, width, height, format, type, bufSize, data.address)
    }
}