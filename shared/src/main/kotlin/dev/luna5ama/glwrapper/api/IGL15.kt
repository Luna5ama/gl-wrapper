@file:JvmName("GL15")

package dev.luna5ama.glwrapper.api

import dev.luna5ama.kmogus.Ptr
import dev.luna5ama.kmogus.ensureCapacity
import dev.luna5ama.kmogus.memcpy

interface IGL15 : GLBase {
    companion object {
        const val GL_SRC1_ALPHA = 0x8589

        const val GL_ARRAY_BUFFER = 0x8892
        const val GL_ELEMENT_ARRAY_BUFFER = 0x8893

        const val GL_ARRAY_BUFFER_BINDING = 0x8894
        const val GL_ELEMENT_ARRAY_BUFFER_BINDING = 0x8895

        const val GL_VERTEX_ATTRIB_ARRAY_BUFFER_BINDING = 0x889F

        const val GL_STREAM_DRAW = 0x88E0
        const val GL_STREAM_READ = 0x88E1
        const val GL_STREAM_COPY = 0x88E2
        const val GL_STATIC_DRAW = 0x88E4
        const val GL_STATIC_READ = 0x88E5
        const val GL_STATIC_COPY = 0x88E6
        const val GL_DYNAMIC_DRAW = 0x88E8
        const val GL_DYNAMIC_READ = 0x88E9
        const val GL_DYNAMIC_COPY = 0x88EA

        const val GL_READ_ONLY = 0x88B8
        const val GL_WRITE_ONLY = 0x88B9
        const val GL_READ_WRITE = 0x88BA

        const val GL_BUFFER_SIZE = 0x8764
        const val GL_BUFFER_USAGE = 0x8765
        const val GL_BUFFER_ACCESS = 0x88BB
        const val GL_BUFFER_MAPPED = 0x88BC

        const val GL_BUFFER_MAP_POINTER = 0x88BD

        const val GL_SAMPLES_PASSED = 0x8914

        const val GL_QUERY_COUNTER_BITS = 0x8864
        const val GL_CURRENT_QUERY = 0x8865

        const val GL_QUERY_RESULT = 0x8866
        const val GL_QUERY_RESULT_AVAILABLE = 0x8867
    }

    fun glBindBuffer(target: Int, buffer: Int)

    @Unsafe
    fun glDeleteBuffers(n: Int, buffers: Long)

    fun glIsBuffer(buffer: Int): Boolean


    fun glDeleteBuffers(n: Int, buffers: Ptr) {
        glDeleteBuffers(n, buffers.address)
    }

    fun glDeleteBuffers(buffer: Int) {
        val ptr = tempArr.ptr
        ptr.setInt(buffer)
        glDeleteBuffers(1, ptr)
    }

    fun glDeleteBuffers(buffer1: Int, buffer2: Int) {
        val ptr = tempArr.ptr
        ptr.setInt(buffer1)
        ptr.setInt(4, buffer2)
        glDeleteBuffers(2, ptr)
    }

    fun glDeleteBuffers(buffer1: Int, buffer2: Int, buffer3: Int) {
        val ptr = tempArr.ptr
        ptr.setInt(buffer1)
        ptr.setInt(4, buffer2)
        ptr.setInt(8, buffer3)
        glDeleteBuffers(3, ptr)
    }

    fun glDeleteBuffers(buffer1: Int, buffer2: Int, buffer3: Int, buffer4: Int) {
        val ptr = tempArr.ptr
        ptr.setInt(buffer1)
        ptr.setInt(4, buffer2)
        ptr.setInt(8, buffer3)
        ptr.setInt(12, buffer4)
        glDeleteBuffers(4, ptr)
    }

    fun glDeleteBuffers(vararg buffers: Int) {
        tempArr.ensureCapacity(buffers.size * 4L, false)
        val ptr = tempArr.ptr
        memcpy(buffers, ptr, buffers.size * 4L)
        glDeleteBuffers(buffers.size, ptr)
    }

    fun glDeleteBuffers(buffers: IntArray, offset: Int, length: Int) {
        tempArr.ensureCapacity(length * 4L, false)
        val ptr = tempArr.ptr
        memcpy(buffers, offset * 4L, ptr, 0L, length * 4L)
        glDeleteBuffers(length, ptr)
    }
}