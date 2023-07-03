@file:JvmName("GL44")

package dev.luna5ama.glwrapper.api

import dev.luna5ama.kmogus.Ptr
import dev.luna5ama.kmogus.ensureCapacity
import dev.luna5ama.kmogus.memcpy

interface IGL44 : GLBase {
    companion object {
        const val GL_MAX_VERTEX_ATTRIB_STRIDE = 0x82E5

        const val GL_PRIMITIVE_RESTART_FOR_PATCHES_SUPPORTED = 0x8221

        const val GL_TEXTURE_BUFFER_BINDING = 0x8C2A

        const val GL_MAP_PERSISTENT_BIT = 0x40
        const val GL_MAP_COHERENT_BIT = 0x80
        const val GL_DYNAMIC_STORAGE_BIT = 0x100
        const val GL_CLIENT_STORAGE_BIT = 0x200

        const val GL_BUFFER_IMMUTABLE_STORAGE = 0x821F
        const val GL_BUFFER_STORAGE_FLAGS = 0x8220

        const val GL_CLIENT_MAPPED_BUFFER_BARRIER_BIT = 0x4000

        const val GL_CLEAR_TEXTURE = 0x9365

        const val GL_LOCATION_COMPONENT = 0x934A
        const val GL_TRANSFORM_FEEDBACK_BUFFER_INDEX = 0x934B
        const val GL_TRANSFORM_FEEDBACK_BUFFER_STRIDE = 0x934C

        const val GL_QUERY_RESULT_NO_WAIT = 0x9194

        const val GL_QUERY_BUFFER = 0x9192

        const val GL_QUERY_BUFFER_BINDING = 0x9193

        const val GL_QUERY_BUFFER_BARRIER_BIT = 0x8000

        const val GL_MIRROR_CLAMP_TO_EDGE = 0x8743
    }

    @Unsafe
    fun glClearTexSubImage(
        texture: Int,
        level: Int,
        xOffset: Int,
        yOffset: Int,
        zOffset: Int,
        width: Int,
        height: Int,
        depth: Int,
        format: Int,
        type: Int,
        data: Long
    )

    @Unsafe
    fun glClearTexImage(texture: Int, level: Int, format: Int, type: Int, data: Long)

    @Unsafe
    fun glBindBuffersBase(target: Int, first: Int, count: Int, buffers: Long)

    @Unsafe
    fun glBindBuffersRange(target: Int, first: Int, count: Int, buffers: Long, offsets: Long, sizes: Long)

    @Unsafe
    fun glBindTextures(first: Int, count: Int, textures: Long)

    @Unsafe
    fun glBindSamplers(first: Int, count: Int, samplers: Long)

    @Unsafe
    fun glBindImageTextures(first: Int, count: Int, textures: Long)


    fun glClearTexSubImage(
        texture: Int,
        level: Int,
        xOffset: Int,
        yOffset: Int,
        zOffset: Int,
        width: Int,
        height: Int,
        depth: Int,
        format: Int,
        type: Int,
        data: Ptr
    ) {
        glClearTexSubImage(texture, level, xOffset, yOffset, zOffset, width, height, depth, format, type, data.address)
    }

    fun glClearTexImage(texture: Int, level: Int, format: Int, type: Int, data: Ptr) {
        glClearTexImage(texture, level, format, type, data.address)
    }

    fun glBindBuffersBase(target: Int, first: Int, count: Int, buffers: Ptr) {
        glBindBuffersBase(target, first, count, buffers.address)
    }

    fun glBindBuffersBase(target: Int, first: Int, vararg buffers: Int) {
        val length = buffers.size * 4L
        tempArr.ensureCapacity(length, true)
        val ptr = tempArr.ptr
        memcpy(buffers, ptr, length)
        glBindBuffersBase(target, first, buffers.size, ptr)
    }

    fun glBindBuffersBase(target: Int, first: Int, buffers: IntArray, offset: Int, length: Int) {
        val byteLength = length * 4L
        tempArr.ensureCapacity(byteLength, true)
        val ptr = tempArr.ptr
        memcpy(buffers, offset * 4L, ptr, 0L, byteLength)
        glBindBuffersBase(target, first, length, ptr)
    }

    fun glBindBuffersRange(target: Int, first: Int, count: Int, buffers: Ptr, offsets: Ptr, sizes: Ptr) {
        glBindBuffersRange(target, first, count, buffers.address, offsets.address, sizes.address)
    }

    fun glBindBuffersRange(target: Int, first: Int, buffers: IntArray, offsets: LongArray, sizes: LongArray) {
        val lengthInt = buffers.size * 4L
        val lengthLong = buffers.size * 8L

        tempArr.ensureCapacity(lengthInt + lengthLong + lengthLong, true)

        val ptrBuffers = tempArr.ptr
        val ptrOffsets = ptrBuffers + lengthInt
        val ptrSizes = ptrOffsets + lengthLong

        memcpy(buffers, ptrBuffers, lengthInt)
        memcpy(offsets, ptrOffsets, lengthLong)
        memcpy(sizes, ptrSizes, lengthLong)

        glBindBuffersRange(target, first, buffers.size, ptrBuffers, ptrSizes, ptrSizes)
    }

    fun glBindBuffersRange(
        target: Int,
        first: Int,
        buffers: IntArray,
        offsets: LongArray,
        sizes: LongArray,
        offset: Int,
        length: Int
    ) {
        val byteLengthInt = length * 4L
        val byteLengthLong = length * 8L

        tempArr.ensureCapacity(byteLengthInt + byteLengthLong + byteLengthLong, true)

        val ptrBuffers = tempArr.ptr
        val ptrOffsets = ptrBuffers + byteLengthInt
        val ptrSizes = ptrOffsets + byteLengthLong

        memcpy(buffers, offset * 4L, ptrBuffers, 0L, byteLengthInt)
        memcpy(offsets, offset * 4L, ptrOffsets, 0L, byteLengthLong)
        memcpy(sizes, offset * 4L, ptrSizes, 0L, byteLengthLong)

        glBindBuffersRange(target, first, length, ptrBuffers, ptrOffsets, ptrSizes)
    }

    fun glBindTextures(first: Int, count: Int, textures: Ptr) {
        glBindTextures(first, count, textures.address)
    }

    fun glBindTextures(first: Int, vararg textures: Int) {
        val length = textures.size * 4L
        tempArr.ensureCapacity(length, true)
        val ptr = tempArr.ptr
        memcpy(textures, ptr, length)
        glBindTextures(first, textures.size, ptr)
    }

    fun glBindTextures(first: Int, textures: IntArray, offset: Int, length: Int) {
        val byteLength = length * 4L
        tempArr.ensureCapacity(byteLength, true)
        val ptr = tempArr.ptr
        memcpy(textures, offset * 4L, ptr, 0L, byteLength)
        glBindTextures(first, length, ptr)
    }

    fun glBindSamplers(first: Int, count: Int, samplers: Ptr) {
        glBindSamplers(first, count, samplers.address)
    }

    fun glBindSamplers(first: Int, vararg samplers: Int) {
        val length = samplers.size * 4L
        tempArr.ensureCapacity(length, true)
        val ptr = tempArr.ptr
        memcpy(samplers, ptr, length)
        glBindSamplers(first, samplers.size, ptr)
    }

    fun glBindSamplers(first: Int, samplers: IntArray, offset: Int, length: Int) {
        val byteLength = length * 4L
        tempArr.ensureCapacity(byteLength, true)
        val ptr = tempArr.ptr
        memcpy(samplers, offset * 4L, ptr, 0L, byteLength)
        glBindSamplers(first, length, ptr)
    }

    fun glBindImageTextures(first: Int, count: Int, textures: Ptr) {
        glBindImageTextures(first, count, textures.address)
    }

    fun glBindImageTextures(first: Int, vararg textures: Int) {
        val length = textures.size * 4L
        tempArr.ensureCapacity(length, true)
        val ptr = tempArr.ptr
        memcpy(textures, ptr, length)
        glBindImageTextures(first, textures.size, ptr)
    }

    fun glBindImageTextures(first: Int, textures: IntArray, offset: Int, length: Int) {
        val byteLength = length * 4L
        tempArr.ensureCapacity(byteLength, true)
        val ptr = tempArr.ptr
        memcpy(textures, offset * 4L, ptr, 0L, byteLength)
        glBindImageTextures(first, length, ptr)
    }
}