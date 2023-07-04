package dev.luna5ama.glwrapper.api

import dev.luna5ama.kmogus.*
import org.lwjgl.opengl.GL41

open class GL41LWJGL2(override val tempArr: Arr) : IGL41 {
    override fun glProgramUniform1f(program: Int, location: Int, v0: Float) {
        GL41.glProgramUniform1f(program, location, v0)
    }

    override fun glProgramUniform2f(program: Int, location: Int, v0: Float, v1: Float) {
        GL41.glProgramUniform2f(program, location, v0, v1)
    }

    override fun glProgramUniform3f(program: Int, location: Int, v0: Float, v1: Float, v2: Float) {
        GL41.glProgramUniform3f(program, location, v0, v1, v2)
    }

    override fun glProgramUniform4f(program: Int, location: Int, v0: Float, v1: Float, v2: Float, v3: Float) {
        GL41.glProgramUniform4f(program, location, v0, v1, v2, v3)
    }

    override fun glProgramUniform1i(program: Int, location: Int, v0: Int) {
        GL41.glProgramUniform1i(program, location, v0)
    }

    override fun glProgramUniform2i(program: Int, location: Int, v0: Int, v1: Int) {
        GL41.glProgramUniform2i(program, location, v0, v1)
    }

    override fun glProgramUniform3i(program: Int, location: Int, v0: Int, v1: Int, v2: Int) {
        GL41.glProgramUniform3i(program, location, v0, v1, v2)
    }

    override fun glProgramUniform4i(program: Int, location: Int, v0: Int, v1: Int, v2: Int, v3: Int) {
        GL41.glProgramUniform4i(program, location, v0, v1, v2, v3)
    }

    override fun glProgramUniform1ui(program: Int, location: Int, v0: Int) {
        GL41.glProgramUniform1ui(program, location, v0)
    }

    override fun glProgramUniform2ui(program: Int, location: Int, v0: Int, v1: Int) {
        GL41.glProgramUniform2ui(program, location, v0, v1)
    }

    override fun glProgramUniform3ui(program: Int, location: Int, v0: Int, v1: Int, v2: Int) {
        GL41.glProgramUniform3ui(program, location, v0, v1, v2)
    }

    override fun glProgramUniform4ui(program: Int, location: Int, v0: Int, v1: Int, v2: Int, v3: Int) {
        GL41.glProgramUniform4ui(program, location, v0, v1, v2, v3)
    }


    private val glProgramUniform1fv = nullFloatBuffer()

    override fun glProgramUniform1fv(program: Int, location: Int, count: Int, value: Long) {
        glProgramUniform1fv(program, location, count, Ptr(value))
    }

    override fun glProgramUniform1fv(program: Int, location: Int, count: Int, value: Ptr) {
        GL41.glProgramUniform1(program, location, value.asFloatBuffer(count, glProgramUniform1fv))
    }

    private val glProgramUniform2fv = nullFloatBuffer()

    override fun glProgramUniform2fv(program: Int, location: Int, count: Int, value: Long) {
        glProgramUniform2fv(program, location, count, Ptr(value))
    }

    override fun glProgramUniform2fv(program: Int, location: Int, count: Int, value: Ptr) {
        GL41.glProgramUniform2(program, location, value.asFloatBuffer(count * 2, glProgramUniform2fv))
    }

    private val glProgramUniform3fv = nullFloatBuffer()

    override fun glProgramUniform3fv(program: Int, location: Int, count: Int, value: Long) {
        glProgramUniform3fv(program, location, count, Ptr(value))
    }

    override fun glProgramUniform3fv(program: Int, location: Int, count: Int, value: Ptr) {
        GL41.glProgramUniform3(program, location, value.asFloatBuffer(count * 3, glProgramUniform3fv))
    }

    private val glProgramUniform4fv = nullFloatBuffer()

    override fun glProgramUniform4fv(program: Int, location: Int, count: Int, value: Long) {
        glProgramUniform4fv(program, location, count, Ptr(value))
    }

    override fun glProgramUniform4fv(program: Int, location: Int, count: Int, value: Ptr) {
        GL41.glProgramUniform4(program, location, value.asFloatBuffer(count * 4, glProgramUniform4fv))
    }

    private val glProgramUniform1iv = nullIntBuffer()

    override fun glProgramUniform1iv(program: Int, location: Int, count: Int, value: Long) {
        glProgramUniform1iv(program, location, count, Ptr(value))
    }

    override fun glProgramUniform1iv(program: Int, location: Int, count: Int, value: Ptr) {
        GL41.glProgramUniform1(program, location, value.asIntBuffer(count, glProgramUniform1iv))
    }

    private val glProgramUniform2iv = nullIntBuffer()

    override fun glProgramUniform2iv(program: Int, location: Int, count: Int, value: Long) {
        glProgramUniform2iv(program, location, count, Ptr(value))
    }

    override fun glProgramUniform2iv(program: Int, location: Int, count: Int, value: Ptr) {
        GL41.glProgramUniform2(program, location, value.asIntBuffer(count * 2, glProgramUniform2iv))
    }

    private val glProgramUniform3iv = nullIntBuffer()

    override fun glProgramUniform3iv(program: Int, location: Int, count: Int, value: Long) {
        glProgramUniform3iv(program, location, count, Ptr(value))
    }

    override fun glProgramUniform3iv(program: Int, location: Int, count: Int, value: Ptr) {
        GL41.glProgramUniform3(program, location, value.asIntBuffer(count * 3, glProgramUniform3iv))
    }

    private val glProgramUniform4iv = nullIntBuffer()

    override fun glProgramUniform4iv(program: Int, location: Int, count: Int, value: Long) {
        glProgramUniform4iv(program, location, count, Ptr(value))
    }

    override fun glProgramUniform4iv(program: Int, location: Int, count: Int, value: Ptr) {
        GL41.glProgramUniform4(program, location, value.asIntBuffer(count * 4, glProgramUniform4iv))
    }

    private val glProgramUniform1uiv = nullIntBuffer()

    override fun glProgramUniform1uiv(program: Int, location: Int, count: Int, value: Long) {
        glProgramUniform1uiv(program, location, count, Ptr(value))
    }

    override fun glProgramUniform1uiv(program: Int, location: Int, count: Int, value: Ptr) {
        GL41.glProgramUniform1(program, location, value.asIntBuffer(count, glProgramUniform1uiv))
    }

    private val glProgramUniform2uiv = nullIntBuffer()

    override fun glProgramUniform2uiv(program: Int, location: Int, count: Int, value: Long) {
        glProgramUniform2uiv(program, location, count, Ptr(value))
    }

    override fun glProgramUniform2uiv(program: Int, location: Int, count: Int, value: Ptr) {
        GL41.glProgramUniform2(program, location, value.asIntBuffer(count * 2, glProgramUniform2uiv))
    }

    private val glProgramUniform3uiv = nullIntBuffer()

    override fun glProgramUniform3uiv(program: Int, location: Int, count: Int, value: Long) {
        glProgramUniform3uiv(program, location, count, Ptr(value))
    }

    override fun glProgramUniform3uiv(program: Int, location: Int, count: Int, value: Ptr) {
        GL41.glProgramUniform3(program, location, value.asIntBuffer(count * 3, glProgramUniform3uiv))
    }

    private val glProgramUniform4uiv = nullIntBuffer()

    override fun glProgramUniform4uiv(program: Int, location: Int, count: Int, value: Long) {
        glProgramUniform4uiv(program, location, count, Ptr(value))
    }

    override fun glProgramUniform4uiv(program: Int, location: Int, count: Int, value: Ptr) {
        GL41.glProgramUniform4(program, location, value.asIntBuffer(count * 4, glProgramUniform4uiv))
    }

    private val glProgramUniformMatrix2fv = nullFloatBuffer()

    override fun glProgramUniformMatrix2fv(program: Int, location: Int, count: Int, transpose: Boolean, value: Long) {
        glProgramUniformMatrix2fv(program, location, count, transpose, Ptr(value))
    }

    override fun glProgramUniformMatrix2fv(program: Int, location: Int, count: Int, transpose: Boolean, value: Ptr) {
        GL41.glProgramUniformMatrix2(
            program,
            location,
            transpose,
            value.asFloatBuffer(count * 2 * 2, glProgramUniformMatrix2fv)
        )
    }

    private val glProgramUniformMatrix3fv = nullFloatBuffer()

    override fun glProgramUniformMatrix3fv(program: Int, location: Int, count: Int, transpose: Boolean, value: Long) {
        glProgramUniformMatrix3fv(program, location, count, transpose, Ptr(value))
    }

    override fun glProgramUniformMatrix3fv(program: Int, location: Int, count: Int, transpose: Boolean, value: Ptr) {
        GL41.glProgramUniformMatrix3(
            program,
            location,
            transpose,
            value.asFloatBuffer(count * 3 * 3, glProgramUniformMatrix3fv)
        )
    }

    private val glProgramUniformMatrix4fv = nullFloatBuffer()

    override fun glProgramUniformMatrix4fv(program: Int, location: Int, count: Int, transpose: Boolean, value: Long) {
        glProgramUniformMatrix4fv(program, location, count, transpose, Ptr(value))
    }

    override fun glProgramUniformMatrix4fv(program: Int, location: Int, count: Int, transpose: Boolean, value: Ptr) {
        GL41.glProgramUniformMatrix4(
            program,
            location,
            transpose,
            value.asFloatBuffer(count * 4 * 4, glProgramUniformMatrix4fv)
        )
    }

    private val glProgramUniformMatrix2x3fv = nullFloatBuffer()

    override fun glProgramUniformMatrix2x3fv(program: Int, location: Int, count: Int, transpose: Boolean, value: Long) {
        glProgramUniformMatrix2x3fv(program, location, count, transpose, Ptr(value))
    }

    override fun glProgramUniformMatrix2x3fv(program: Int, location: Int, count: Int, transpose: Boolean, value: Ptr) {
        GL41.glProgramUniformMatrix2x3(
            program,
            location,
            transpose,
            value.asFloatBuffer(count * 2 * 3, glProgramUniformMatrix2x3fv)
        )
    }

    private val glProgramUniformMatrix3x2fv = nullFloatBuffer()

    override fun glProgramUniformMatrix3x2fv(program: Int, location: Int, count: Int, transpose: Boolean, value: Long) {
        glProgramUniformMatrix3x2fv(program, location, count, transpose, Ptr(value))
    }

    override fun glProgramUniformMatrix3x2fv(program: Int, location: Int, count: Int, transpose: Boolean, value: Ptr) {
        GL41.glProgramUniformMatrix3x2(
            program,
            location,
            transpose,
            value.asFloatBuffer(count * 3 * 2, glProgramUniformMatrix3x2fv)
        )
    }

    private val glProgramUniformMatrix2x4fv = nullFloatBuffer()

    override fun glProgramUniformMatrix2x4fv(program: Int, location: Int, count: Int, transpose: Boolean, value: Long) {
        glProgramUniformMatrix2x4fv(program, location, count, transpose, Ptr(value))
    }

    override fun glProgramUniformMatrix2x4fv(program: Int, location: Int, count: Int, transpose: Boolean, value: Ptr) {
        GL41.glProgramUniformMatrix2x4(
            program,
            location,
            transpose,
            value.asFloatBuffer(count * 2 * 4, glProgramUniformMatrix2x4fv)
        )
    }

    private val glProgramUniformMatrix4x2fv = nullFloatBuffer()

    override fun glProgramUniformMatrix4x2fv(program: Int, location: Int, count: Int, transpose: Boolean, value: Long) {
        glProgramUniformMatrix4x2fv(program, location, count, transpose, Ptr(value))
    }

    override fun glProgramUniformMatrix4x2fv(program: Int, location: Int, count: Int, transpose: Boolean, value: Ptr) {
        GL41.glProgramUniformMatrix4x2(
            program,
            location,
            transpose,
            value.asFloatBuffer(count * 4 * 2, glProgramUniformMatrix4x2fv)
        )
    }

    private val glProgramUniformMatrix3x4fv = nullFloatBuffer()

    override fun glProgramUniformMatrix3x4fv(program: Int, location: Int, count: Int, transpose: Boolean, value: Long) {
        glProgramUniformMatrix3x4fv(program, location, count, transpose, Ptr(value))
    }

    override fun glProgramUniformMatrix3x4fv(program: Int, location: Int, count: Int, transpose: Boolean, value: Ptr) {
        GL41.glProgramUniformMatrix3x4(
            program,
            location,
            transpose,
            value.asFloatBuffer(count * 3 * 4, glProgramUniformMatrix3x4fv)
        )
    }

    private val glProgramUniformMatrix4x3fv = nullFloatBuffer()

    override fun glProgramUniformMatrix4x3fv(program: Int, location: Int, count: Int, transpose: Boolean, value: Long) {
        glProgramUniformMatrix4x3fv(program, location, count, transpose, Ptr(value))
    }

    override fun glProgramUniformMatrix4x3fv(program: Int, location: Int, count: Int, transpose: Boolean, value: Ptr) {
        GL41.glProgramUniformMatrix4x3(
            program,
            location,
            transpose,
            value.asFloatBuffer(count * 4 * 3, glProgramUniformMatrix4x3fv)
        )
    }
}
