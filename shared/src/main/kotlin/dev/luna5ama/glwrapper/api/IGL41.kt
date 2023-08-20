@file:JvmName("GL41")

package dev.luna5ama.glwrapper.api

import dev.luna5ama.kmogus.Ptr
import dev.luna5ama.kmogus.ensureCapacity
import dev.luna5ama.kmogus.memcpy

interface IGL41 : GLBase {
    companion object {
        internal const val GL_SHADER_COMPILER = 0x8DFA
        internal const val GL_SHADER_BINARY_FORMATS = 0x8DF8
        internal const val GL_NUM_SHADER_BINARY_FORMATS = 0x8DF9
        internal const val GL_MAX_VERTEX_UNIFORM_VECTORS = 0x8DFB
        internal const val GL_MAX_VARYING_VECTORS = 0x8DFC
        internal const val GL_MAX_FRAGMENT_UNIFORM_VECTORS = 0x8DFD
        internal const val GL_IMPLEMENTATION_COLOR_READ_TYPE = 0x8B9A
        internal const val GL_IMPLEMENTATION_COLOR_READ_FORMAT = 0x8B9B

        internal const val GL_FIXED = 0x140C

        internal const val GL_LOW_FLOAT = 0x8DF0
        internal const val GL_MEDIUM_FLOAT = 0x8DF1
        internal const val GL_HIGH_FLOAT = 0x8DF2
        internal const val GL_LOW_INT = 0x8DF3
        internal const val GL_MEDIUM_INT = 0x8DF4
        internal const val GL_HIGH_INT = 0x8DF5

        internal const val GL_RGB565 = 0x8D62

        internal const val GL_PROGRAM_BINARY_RETRIEVABLE_HINT = 0x8257

        internal const val GL_PROGRAM_BINARY_LENGTH = 0x8741

        internal const val GL_NUM_PROGRAM_BINARY_FORMATS = 0x87FE
        internal const val GL_PROGRAM_BINARY_FORMATS = 0x87FF

        internal const val GL_VERTEX_SHADER_BIT = 0x1
        internal const val GL_FRAGMENT_SHADER_BIT = 0x2
        internal const val GL_GEOMETRY_SHADER_BIT = 0x4
        internal const val GL_TESS_CONTROL_SHADER_BIT = 0x8
        internal const val GL_TESS_EVALUATION_SHADER_BIT = 0x10
        internal const val GL_ALL_SHADER_BITS = -0x1

        internal const val GL_PROGRAM_SEPARABLE = 0x8258

        internal const val GL_ACTIVE_PROGRAM = 0x8259

        internal const val GL_PROGRAM_PIPELINE_BINDING = 0x825A

        internal const val GL_MAX_VIEWPORTS = 0x825B
        internal const val GL_VIEWPORT_SUBPIXEL_BITS = 0x825C
        internal const val GL_VIEWPORT_BOUNDS_RANGE = 0x825D
        internal const val GL_LAYER_PROVOKING_VERTEX = 0x825E
        internal const val GL_VIEWPORT_INDEX_PROVOKING_VERTEX = 0x825F

        internal const val GL_UNDEFINED_VERTEX = 0x8260
    }

    fun glProgramUniform1f(program: Int, location: Int, v0: Float)
    fun glProgramUniform2f(program: Int, location: Int, v0: Float, v1: Float)
    fun glProgramUniform3f(program: Int, location: Int, v0: Float, v1: Float, v2: Float)
    fun glProgramUniform4f(program: Int, location: Int, v0: Float, v1: Float, v2: Float, v3: Float)

    fun glProgramUniform1i(program: Int, location: Int, v0: Int)
    fun glProgramUniform2i(program: Int, location: Int, v0: Int, v1: Int)
    fun glProgramUniform3i(program: Int, location: Int, v0: Int, v1: Int, v2: Int)
    fun glProgramUniform4i(program: Int, location: Int, v0: Int, v1: Int, v2: Int, v3: Int)

    fun glProgramUniform1ui(program: Int, location: Int, v0: Int)
    fun glProgramUniform2ui(program: Int, location: Int, v0: Int, v1: Int)
    fun glProgramUniform3ui(program: Int, location: Int, v0: Int, v1: Int, v2: Int)
    fun glProgramUniform4ui(program: Int, location: Int, v0: Int, v1: Int, v2: Int, v3: Int)

    @Unsafe
    fun glProgramUniform1fv(program: Int, location: Int, count: Int, value: Long)

    @Unsafe
    fun glProgramUniform2fv(program: Int, location: Int, count: Int, value: Long)

    @Unsafe
    fun glProgramUniform3fv(program: Int, location: Int, count: Int, value: Long)

    @Unsafe
    fun glProgramUniform4fv(program: Int, location: Int, count: Int, value: Long)

    @Unsafe
    fun glProgramUniform1iv(program: Int, location: Int, count: Int, value: Long)

    @Unsafe
    fun glProgramUniform2iv(program: Int, location: Int, count: Int, value: Long)

    @Unsafe
    fun glProgramUniform3iv(program: Int, location: Int, count: Int, value: Long)

    @Unsafe
    fun glProgramUniform4iv(program: Int, location: Int, count: Int, value: Long)

    @Unsafe
    fun glProgramUniform1uiv(program: Int, location: Int, count: Int, value: Long)

    @Unsafe
    fun glProgramUniform2uiv(program: Int, location: Int, count: Int, value: Long)

    @Unsafe
    fun glProgramUniform3uiv(program: Int, location: Int, count: Int, value: Long)

    @Unsafe
    fun glProgramUniform4uiv(program: Int, location: Int, count: Int, value: Long)

    @Unsafe
    fun glProgramUniformMatrix2fv(program: Int, location: Int, count: Int, transpose: Boolean, value: Long)

    @Unsafe
    fun glProgramUniformMatrix3fv(program: Int, location: Int, count: Int, transpose: Boolean, value: Long)

    @Unsafe
    fun glProgramUniformMatrix4fv(program: Int, location: Int, count: Int, transpose: Boolean, value: Long)

    @Unsafe
    fun glProgramUniformMatrix2x3fv(program: Int, location: Int, count: Int, transpose: Boolean, value: Long)

    @Unsafe
    fun glProgramUniformMatrix3x2fv(program: Int, location: Int, count: Int, transpose: Boolean, value: Long)

    @Unsafe
    fun glProgramUniformMatrix2x4fv(program: Int, location: Int, count: Int, transpose: Boolean, value: Long)

    @Unsafe
    fun glProgramUniformMatrix4x2fv(program: Int, location: Int, count: Int, transpose: Boolean, value: Long)

    @Unsafe
    fun glProgramUniformMatrix3x4fv(program: Int, location: Int, count: Int, transpose: Boolean, value: Long)

    @Unsafe
    fun glProgramUniformMatrix4x3fv(program: Int, location: Int, count: Int, transpose: Boolean, value: Long)


    fun glProgramUniform1fv(program: Int, location: Int, count: Int, value: Ptr) {
        glProgramUniform1fv(program, location, count, value.address)
    }

    fun glProgramUniform1fv(program: Int, location: Int, count: Int, vararg value: Float) {
        val length = count * 1L * 4L
        tempArr.ensureCapacity(length, false)
        val ptr = tempArr.ptr
        memcpy(value, ptr, length)
        glProgramUniform1fv(program, location, count, ptr)
    }

    fun glProgramUniform1fv(program: Int, location: Int, count: Int, value: FloatArray, offset: Int) {
        val length = count * 1L * 4L
        tempArr.ensureCapacity(length, false)
        val ptr = tempArr.ptr
        memcpy(value, offset * 4L, ptr, 0L, length)
        glProgramUniform1fv(program, location, count, ptr)
    }

    fun glProgramUniform2fv(program: Int, location: Int, count: Int, value: Ptr) {
        glProgramUniform2fv(program, location, count, value.address)
    }

    fun glProgramUniform2fv(program: Int, location: Int, count: Int, vararg value: Float) {
        val length = count * 2L * 4L
        tempArr.ensureCapacity(length, false)
        val ptr = tempArr.ptr
        memcpy(value, ptr, length)
        glProgramUniform2fv(program, location, count, ptr)
    }

    fun glProgramUniform2fv(program: Int, location: Int, count: Int, value: FloatArray, offset: Int) {
        val length = count * 2L * 4L
        tempArr.ensureCapacity(length, false)
        val ptr = tempArr.ptr
        memcpy(value, offset * 4L, ptr, 0L, length)
        glProgramUniform2fv(program, location, count, ptr)
    }

    fun glProgramUniform3fv(program: Int, location: Int, count: Int, value: Ptr) {
        glProgramUniform3fv(program, location, count, value.address)
    }

    fun glProgramUniform3fv(program: Int, location: Int, count: Int, vararg value: Float) {
        val length = count * 3L * 4L
        tempArr.ensureCapacity(length, false)
        val ptr = tempArr.ptr
        memcpy(value, ptr, length)
        glProgramUniform3fv(program, location, count, ptr)
    }

    fun glProgramUniform3fv(program: Int, location: Int, count: Int, value: FloatArray, offset: Int) {
        val length = count * 3L * 4L
        tempArr.ensureCapacity(length, false)
        val ptr = tempArr.ptr
        memcpy(value, offset * 4L, ptr, 0L, length)
        glProgramUniform3fv(program, location, count, ptr)
    }

    fun glProgramUniform4fv(program: Int, location: Int, count: Int, value: Ptr) {
        glProgramUniform4fv(program, location, count, value.address)
    }

    fun glProgramUniform4fv(program: Int, location: Int, count: Int, vararg value: Float) {
        val length = count * 4L * 4L
        tempArr.ensureCapacity(length, false)
        val ptr = tempArr.ptr
        memcpy(value, ptr, length)
        glProgramUniform4fv(program, location, count, ptr)
    }

    fun glProgramUniform4fv(program: Int, location: Int, count: Int, value: FloatArray, offset: Int) {
        val length = count * 4L * 4L
        tempArr.ensureCapacity(length, false)
        val ptr = tempArr.ptr
        memcpy(value, offset * 4L, ptr, 0L, length)
        glProgramUniform4fv(program, location, count, ptr)
    }

    fun glProgramUniform1iv(program: Int, location: Int, count: Int, value: Ptr) {
        glProgramUniform1iv(program, location, count, value.address)
    }

    fun glProgramUniform1iv(program: Int, location: Int, count: Int, vararg value: Int) {
        val length = count * 1L * 4L
        tempArr.ensureCapacity(length, false)
        val ptr = tempArr.ptr
        memcpy(value, ptr, length)
        glProgramUniform1iv(program, location, count, ptr)
    }

    fun glProgramUniform1iv(program: Int, location: Int, count: Int, value: IntArray, offset: Int) {
        val length = count * 1L * 4L
        tempArr.ensureCapacity(length, false)
        val ptr = tempArr.ptr
        memcpy(value, offset * 4L, ptr, 0L, length)
        glProgramUniform1iv(program, location, count, ptr)
    }

    fun glProgramUniform2iv(program: Int, location: Int, count: Int, value: Ptr) {
        glProgramUniform2iv(program, location, count, value.address)
    }

    fun glProgramUniform2iv(program: Int, location: Int, count: Int, vararg value: Int) {
        val length = count * 2L * 4L
        tempArr.ensureCapacity(length, false)
        val ptr = tempArr.ptr
        memcpy(value, ptr, length)
        glProgramUniform2iv(program, location, count, ptr)
    }

    fun glProgramUniform2iv(program: Int, location: Int, count: Int, value: IntArray, offset: Int) {
        val length = count * 2L * 4L
        tempArr.ensureCapacity(length, false)
        val ptr = tempArr.ptr
        memcpy(value, offset * 4L, ptr, 0L, length)
        glProgramUniform2iv(program, location, count, ptr)
    }

    fun glProgramUniform3iv(program: Int, location: Int, count: Int, value: Ptr) {
        glProgramUniform3iv(program, location, count, value.address)
    }

    fun glProgramUniform3iv(program: Int, location: Int, count: Int, vararg value: Int) {
        val length = count * 3L * 4L
        tempArr.ensureCapacity(length, false)
        val ptr = tempArr.ptr
        memcpy(value, ptr, length)
        glProgramUniform3iv(program, location, count, ptr)
    }

    fun glProgramUniform3iv(program: Int, location: Int, count: Int, value: IntArray, offset: Int) {
        val length = count * 3L * 4L
        tempArr.ensureCapacity(length, false)
        val ptr = tempArr.ptr
        memcpy(value, offset * 4L, ptr, 0L, length)
        glProgramUniform3iv(program, location, count, ptr)
    }

    fun glProgramUniform4iv(program: Int, location: Int, count: Int, value: Ptr) {
        glProgramUniform4iv(program, location, count, value.address)
    }

    fun glProgramUniform4iv(program: Int, location: Int, count: Int, vararg value: Int) {
        val length = count * 4L * 4L
        tempArr.ensureCapacity(length, false)
        val ptr = tempArr.ptr
        memcpy(value, ptr, length)
        glProgramUniform4iv(program, location, count, ptr)
    }

    fun glProgramUniform4iv(program: Int, location: Int, count: Int, value: IntArray, offset: Int) {
        val length = count * 4L * 4L
        tempArr.ensureCapacity(length, false)
        val ptr = tempArr.ptr
        memcpy(value, offset * 4L, ptr, 0L, length)
        glProgramUniform4iv(program, location, count, ptr)
    }

    fun glProgramUniform1uiv(program: Int, location: Int, count: Int, value: Ptr) {
        glProgramUniform1uiv(program, location, count, value.address)
    }

    fun glProgramUniform1uiv(program: Int, location: Int, count: Int, vararg value: Int) {
        val length = count * 1L * 4L
        tempArr.ensureCapacity(length, false)
        val ptr = tempArr.ptr
        memcpy(value, ptr, length)
        glProgramUniform1uiv(program, location, count, ptr)
    }

    fun glProgramUniform1uiv(program: Int, location: Int, count: Int, value: IntArray, offset: Int) {
        val length = count * 1L * 4L
        tempArr.ensureCapacity(length, false)
        val ptr = tempArr.ptr
        memcpy(value, offset * 4L, ptr, 0L, length)
        glProgramUniform1uiv(program, location, count, ptr)
    }

    fun glProgramUniform2uiv(program: Int, location: Int, count: Int, value: Ptr) {
        glProgramUniform2uiv(program, location, count, value.address)
    }

    fun glProgramUniform2uiv(program: Int, location: Int, count: Int, vararg value: Int) {
        val length = count * 2L * 4L
        tempArr.ensureCapacity(length, false)
        val ptr = tempArr.ptr
        memcpy(value, ptr, length)
        glProgramUniform2uiv(program, location, count, ptr)
    }

    fun glProgramUniform2uiv(program: Int, location: Int, count: Int, value: IntArray, offset: Int) {
        val length = count * 2L * 4L
        tempArr.ensureCapacity(length, false)
        val ptr = tempArr.ptr
        memcpy(value, offset * 4L, ptr, 0L, length)
        glProgramUniform2uiv(program, location, count, ptr)
    }

    fun glProgramUniform3uiv(program: Int, location: Int, count: Int, value: Ptr) {
        glProgramUniform3uiv(program, location, count, value.address)
    }

    fun glProgramUniform3uiv(program: Int, location: Int, count: Int, vararg value: Int) {
        val length = count * 3L * 4L
        tempArr.ensureCapacity(length, false)
        val ptr = tempArr.ptr
        memcpy(value, ptr, length)
        glProgramUniform3uiv(program, location, count, ptr)
    }

    fun glProgramUniform3uiv(program: Int, location: Int, count: Int, value: IntArray, offset: Int) {
        val length = count * 3L * 4L
        tempArr.ensureCapacity(length, false)
        val ptr = tempArr.ptr
        memcpy(value, offset * 4L, ptr, 0L, length)
        glProgramUniform3uiv(program, location, count, ptr)
    }

    fun glProgramUniform4uiv(program: Int, location: Int, count: Int, value: Ptr) {
        glProgramUniform4uiv(program, location, count, value.address)
    }

    fun glProgramUniform4uiv(program: Int, location: Int, count: Int, vararg value: Int) {
        val length = count * 4L * 4L
        tempArr.ensureCapacity(length, false)
        val ptr = tempArr.ptr
        memcpy(value, ptr, length)
        glProgramUniform4uiv(program, location, count, ptr)
    }

    fun glProgramUniform4uiv(program: Int, location: Int, count: Int, value: IntArray, offset: Int) {
        val length = count * 4L * 4L
        tempArr.ensureCapacity(length, false)
        val ptr = tempArr.ptr
        memcpy(value, offset * 4L, ptr, 0L, length)
        glProgramUniform4uiv(program, location, count, ptr)
    }

    fun glProgramUniformMatrix2fv(program: Int, location: Int, count: Int, transpose: Boolean, value: Ptr) {
        glProgramUniformMatrix2fv(program, location, count, transpose, value.address)
    }

    fun glProgramUniformMatrix2fv(program: Int, location: Int, count: Int, transpose: Boolean, vararg value: Float) {
        val length = count * 2L * 2L * 4L
        tempArr.ensureCapacity(length, false)
        val ptr = tempArr.ptr
        memcpy(value, ptr, length)
        glProgramUniformMatrix2fv(program, location, count, transpose, ptr)
    }

    fun glProgramUniformMatrix2fv(
        program: Int,
        location: Int,
        count: Int,
        transpose: Boolean,
        value: FloatArray,
        offset: Int
    ) {
        val length = count * 2L * 2L * 4L
        tempArr.ensureCapacity(length, false)
        val ptr = tempArr.ptr
        memcpy(value, offset * 4L, ptr, 0L, length)
        glProgramUniformMatrix2fv(program, location, count, transpose, ptr)
    }

    fun glProgramUniformMatrix3fv(program: Int, location: Int, count: Int, transpose: Boolean, value: Ptr) {
        glProgramUniformMatrix3fv(program, location, count, transpose, value.address)
    }

    fun glProgramUniformMatrix3fv(program: Int, location: Int, count: Int, transpose: Boolean, vararg value: Float) {
        val length = count * 3L * 3L * 4L
        tempArr.ensureCapacity(length, false)
        val ptr = tempArr.ptr
        memcpy(value, ptr, length)
        glProgramUniformMatrix3fv(program, location, count, transpose, ptr)
    }

    fun glProgramUniformMatrix3fv(
        program: Int,
        location: Int,
        count: Int,
        transpose: Boolean,
        value: FloatArray,
        offset: Int
    ) {
        val length = count * 3L * 3L * 4L
        tempArr.ensureCapacity(length, false)
        val ptr = tempArr.ptr
        memcpy(value, offset * 4L, ptr, 0L, length)
        glProgramUniformMatrix3fv(program, location, count, transpose, ptr)
    }

    fun glProgramUniformMatrix4fv(program: Int, location: Int, count: Int, transpose: Boolean, value: Ptr) {
        glProgramUniformMatrix4fv(program, location, count, transpose, value.address)
    }

    fun glProgramUniformMatrix4fv(program: Int, location: Int, count: Int, transpose: Boolean, vararg value: Float) {
        val length = count * 4L * 4L * 4L
        tempArr.ensureCapacity(length, false)
        val ptr = tempArr.ptr
        memcpy(value, ptr, length)
        glProgramUniformMatrix4fv(program, location, count, transpose, ptr)
    }

    fun glProgramUniformMatrix4fv(
        program: Int,
        location: Int,
        count: Int,
        transpose: Boolean,
        value: FloatArray,
        offset: Int
    ) {
        val length = count * 4L * 4L * 4L
        tempArr.ensureCapacity(length, false)
        val ptr = tempArr.ptr
        memcpy(value, offset * 4L, ptr, 0L, length)
        glProgramUniformMatrix4fv(program, location, count, transpose, ptr)
    }

    fun glProgramUniformMatrix2x3fv(program: Int, location: Int, count: Int, transpose: Boolean, value: Ptr) {
        glProgramUniformMatrix2x3fv(program, location, count, transpose, value.address)
    }

    fun glProgramUniformMatrix2x3fv(program: Int, location: Int, count: Int, transpose: Boolean, vararg value: Float) {
        val length = count * 2L * 3L * 4L
        tempArr.ensureCapacity(length, false)
        val ptr = tempArr.ptr
        memcpy(value, ptr, length)
        glProgramUniformMatrix2x3fv(program, location, count, transpose, ptr)
    }

    fun glProgramUniformMatrix2x3fv(
        program: Int,
        location: Int,
        count: Int,
        transpose: Boolean,
        value: FloatArray,
        offset: Int
    ) {
        val length = count * 2L * 3L * 4L
        tempArr.ensureCapacity(length, false)
        val ptr = tempArr.ptr
        memcpy(value, offset * 4L, ptr, 0L, length)
        glProgramUniformMatrix2x3fv(program, location, count, transpose, ptr)
    }

    fun glProgramUniformMatrix3x2fv(program: Int, location: Int, count: Int, transpose: Boolean, value: Ptr) {
        glProgramUniformMatrix3x2fv(program, location, count, transpose, value.address)
    }

    fun glProgramUniformMatrix3x2fv(program: Int, location: Int, count: Int, transpose: Boolean, vararg value: Float) {
        val length = count * 3L * 2L * 4L
        tempArr.ensureCapacity(length, false)
        val ptr = tempArr.ptr
        memcpy(value, ptr, length)
        glProgramUniformMatrix3x2fv(program, location, count, transpose, ptr)
    }

    fun glProgramUniformMatrix3x2fv(
        program: Int,
        location: Int,
        count: Int,
        transpose: Boolean,
        value: FloatArray,
        offset: Int
    ) {
        val length = count * 3L * 2L * 4L
        tempArr.ensureCapacity(length, false)
        val ptr = tempArr.ptr
        memcpy(value, offset * 4L, ptr, 0L, length)
        glProgramUniformMatrix3x2fv(program, location, count, transpose, ptr)
    }

    fun glProgramUniformMatrix2x4fv(program: Int, location: Int, count: Int, transpose: Boolean, value: Ptr) {
        glProgramUniformMatrix2x4fv(program, location, count, transpose, value.address)
    }

    fun glProgramUniformMatrix2x4fv(program: Int, location: Int, count: Int, transpose: Boolean, vararg value: Float) {
        val length = count * 2L * 4L * 4L
        tempArr.ensureCapacity(length, false)
        val ptr = tempArr.ptr
        memcpy(value, ptr, length)
        glProgramUniformMatrix2x4fv(program, location, count, transpose, ptr)
    }

    fun glProgramUniformMatrix2x4fv(
        program: Int,
        location: Int,
        count: Int,
        transpose: Boolean,
        value: FloatArray,
        offset: Int
    ) {
        val length = count * 2L * 4L * 4L
        tempArr.ensureCapacity(length, false)
        val ptr = tempArr.ptr
        memcpy(value, offset * 4L, ptr, 0L, length)
        glProgramUniformMatrix2x4fv(program, location, count, transpose, ptr)
    }

    fun glProgramUniformMatrix4x2fv(program: Int, location: Int, count: Int, transpose: Boolean, value: Ptr) {
        glProgramUniformMatrix4x2fv(program, location, count, transpose, value.address)
    }

    fun glProgramUniformMatrix4x2fv(program: Int, location: Int, count: Int, transpose: Boolean, vararg value: Float) {
        val length = count * 4L * 2L * 4L
        tempArr.ensureCapacity(length, false)
        val ptr = tempArr.ptr
        memcpy(value, ptr, length)
        glProgramUniformMatrix4x2fv(program, location, count, transpose, ptr)
    }

    fun glProgramUniformMatrix4x2fv(
        program: Int,
        location: Int,
        count: Int,
        transpose: Boolean,
        value: FloatArray,
        offset: Int
    ) {
        val length = count * 4L * 2L * 4L
        tempArr.ensureCapacity(length, false)
        val ptr = tempArr.ptr
        memcpy(value, offset * 4L, ptr, 0L, length)
        glProgramUniformMatrix4x2fv(program, location, count, transpose, ptr)
    }

    fun glProgramUniformMatrix3x4fv(program: Int, location: Int, count: Int, transpose: Boolean, value: Ptr) {
        glProgramUniformMatrix3x4fv(program, location, count, transpose, value.address)
    }

    fun glProgramUniformMatrix3x4fv(program: Int, location: Int, count: Int, transpose: Boolean, vararg value: Float) {
        val length = count * 3L * 4L * 4L
        tempArr.ensureCapacity(length, false)
        val ptr = tempArr.ptr
        memcpy(value, ptr, length)
        glProgramUniformMatrix3x4fv(program, location, count, transpose, ptr)
    }

    fun glProgramUniformMatrix3x4fv(
        program: Int,
        location: Int,
        count: Int,
        transpose: Boolean,
        value: FloatArray,
        offset: Int
    ) {
        val length = count * 3L * 4L * 4L
        tempArr.ensureCapacity(length, false)
        val ptr = tempArr.ptr
        memcpy(value, offset * 4L, ptr, 0L, length)
        glProgramUniformMatrix3x4fv(program, location, count, transpose, ptr)
    }

    fun glProgramUniformMatrix4x3fv(program: Int, location: Int, count: Int, transpose: Boolean, value: Ptr) {
        glProgramUniformMatrix4x3fv(program, location, count, transpose, value.address)
    }

    fun glProgramUniformMatrix4x3fv(program: Int, location: Int, count: Int, transpose: Boolean, vararg value: Float) {
        val length = count * 4L * 3L * 4L
        tempArr.ensureCapacity(length, false)
        val ptr = tempArr.ptr
        memcpy(value, ptr, length)
        glProgramUniformMatrix4x3fv(program, location, count, transpose, ptr)
    }

    fun glProgramUniformMatrix4x3fv(
        program: Int,
        location: Int,
        count: Int,
        transpose: Boolean,
        value: FloatArray,
        offset: Int
    ) {
        val length = count * 4L * 3L * 4L
        tempArr.ensureCapacity(length, false)
        val ptr = tempArr.ptr
        memcpy(value, offset * 4L, ptr, 0L, length)
        glProgramUniformMatrix4x3fv(program, location, count, transpose, ptr)
    }
}

abstract class PatchedGL41(protected val delegate: IGL41) : IGL41 by delegate