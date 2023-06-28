package dev.luna5ama.glwrapper.lwjgl2

import dev.luna5ama.glwrapper.IGL41
import dev.luna5ama.kmogus.Arr
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


    private val glProgramUniform1fv = createBuffer().asFloatBuffer()

    override fun glProgramUniform1fv(program: Int, location: Int, count: Int, value: Long) {
        GL41.glProgramUniform1(program, location, wrapBuffer(glProgramUniform1fv, value, count))
    }

    private val glProgramUniform2fv = createBuffer().asFloatBuffer()

    override fun glProgramUniform2fv(program: Int, location: Int, count: Int, value: Long) {
        GL41.glProgramUniform2(program, location, wrapBuffer(glProgramUniform2fv, value, count))
    }

    private val glProgramUniform3fv = createBuffer().asFloatBuffer()

    override fun glProgramUniform3fv(program: Int, location: Int, count: Int, value: Long) {
        GL41.glProgramUniform3(program, location, wrapBuffer(glProgramUniform3fv, value, count))
    }

    private val glProgramUniform4fv = createBuffer().asFloatBuffer()

    override fun glProgramUniform4fv(program: Int, location: Int, count: Int, value: Long) {
        GL41.glProgramUniform4(program, location, wrapBuffer(glProgramUniform4fv, value, count))
    }

    private val glProgramUniform1iv = createBuffer().asIntBuffer()

    override fun glProgramUniform1iv(program: Int, location: Int, count: Int, value: Long) {
        GL41.glProgramUniform1(program, location, wrapBuffer(glProgramUniform1iv, value, count))
    }

    private val glProgramUniform2iv = createBuffer().asIntBuffer()

    override fun glProgramUniform2iv(program: Int, location: Int, count: Int, value: Long) {
        GL41.glProgramUniform2(program, location, wrapBuffer(glProgramUniform2iv, value, count))
    }

    private val glProgramUniform3iv = createBuffer().asIntBuffer()

    override fun glProgramUniform3iv(program: Int, location: Int, count: Int, value: Long) {
        GL41.glProgramUniform3(program, location, wrapBuffer(glProgramUniform3iv, value, count))
    }

    private val glProgramUniform4iv = createBuffer().asIntBuffer()

    override fun glProgramUniform4iv(program: Int, location: Int, count: Int, value: Long) {
        GL41.glProgramUniform4(program, location, wrapBuffer(glProgramUniform4iv, value, count))
    }

    private val glProgramUniform1uiv = createBuffer().asIntBuffer()

    override fun glProgramUniform1uiv(program: Int, location: Int, count: Int, value: Long) {
        GL41.glProgramUniform1u(program, location, wrapBuffer(glProgramUniform1uiv, value, count))
    }

    private val glProgramUniform2uiv = createBuffer().asIntBuffer()

    override fun glProgramUniform2uiv(program: Int, location: Int, count: Int, value: Long) {
        GL41.glProgramUniform2u(program, location, wrapBuffer(glProgramUniform2uiv, value, count))
    }

    private val glProgramUniform3uiv = createBuffer().asIntBuffer()

    override fun glProgramUniform3uiv(program: Int, location: Int, count: Int, value: Long) {
        GL41.glProgramUniform3u(program, location, wrapBuffer(glProgramUniform3uiv, value, count))
    }

    private val glProgramUniform4uiv = createBuffer().asIntBuffer()

    override fun glProgramUniform4uiv(program: Int, location: Int, count: Int, value: Long) {
        GL41.glProgramUniform4u(program, location, wrapBuffer(glProgramUniform4uiv, value, count))
    }

    private val glProgramUniformMatrix2fv = createBuffer().asFloatBuffer()

    override fun glProgramUniformMatrix2fv(program: Int, location: Int, count: Int, transpose: Boolean, value: Long) {
        GL41.glProgramUniformMatrix2(
            program,
            location,
            transpose,
            wrapBuffer(glProgramUniformMatrix2fv, value, count * 2 * 2)
        )
    }

    private val glProgramUniformMatrix3fv = createBuffer().asFloatBuffer()

    override fun glProgramUniformMatrix3fv(program: Int, location: Int, count: Int, transpose: Boolean, value: Long) {
        GL41.glProgramUniformMatrix3(
            program,
            location,
            transpose,
            wrapBuffer(glProgramUniformMatrix3fv, value, count * 3 * 3)
        )
    }

    private val glProgramUniformMatrix4fv = createBuffer().asFloatBuffer()

    override fun glProgramUniformMatrix4fv(program: Int, location: Int, count: Int, transpose: Boolean, value: Long) {
        GL41.glProgramUniformMatrix4(
            program,
            location,
            transpose,
            wrapBuffer(glProgramUniformMatrix4fv, value, count * 4 * 4)
        )
    }

    private val glProgramUniformMatrix2x3fv = createBuffer().asFloatBuffer()

    override fun glProgramUniformMatrix2x3fv(program: Int, location: Int, count: Int, transpose: Boolean, value: Long) {
        GL41.glProgramUniformMatrix2x3(
            program,
            location,
            transpose,
            wrapBuffer(glProgramUniformMatrix2x3fv, value, count * 2 * 3)
        )
    }

    private val glProgramUniformMatrix3x2fv = createBuffer().asFloatBuffer()

    override fun glProgramUniformMatrix3x2fv(program: Int, location: Int, count: Int, transpose: Boolean, value: Long) {
        GL41.glProgramUniformMatrix3x2(
            program,
            location,
            transpose,
            wrapBuffer(glProgramUniformMatrix3x2fv, value, count * 3 * 2)
        )
    }

    private val glProgramUniformMatrix2x4fv = createBuffer().asFloatBuffer()

    override fun glProgramUniformMatrix2x4fv(program: Int, location: Int, count: Int, transpose: Boolean, value: Long) {
        GL41.glProgramUniformMatrix2x4(
            program,
            location,
            transpose,
            wrapBuffer(glProgramUniformMatrix2x4fv, value, count * 2 * 4)
        )
    }

    private val glProgramUniformMatrix4x2fv = createBuffer().asFloatBuffer()

    override fun glProgramUniformMatrix4x2fv(program: Int, location: Int, count: Int, transpose: Boolean, value: Long) {
        GL41.glProgramUniformMatrix4x2(
            program,
            location,
            transpose,
            wrapBuffer(glProgramUniformMatrix4x2fv, value, count * 4 * 2)
        )
    }

    private val glProgramUniformMatrix3x4fv = createBuffer().asFloatBuffer()

    override fun glProgramUniformMatrix3x4fv(program: Int, location: Int, count: Int, transpose: Boolean, value: Long) {
        GL41.glProgramUniformMatrix3x4(
            program,
            location,
            transpose,
            wrapBuffer(glProgramUniformMatrix3x4fv, value, count * 3 * 4)
        )
    }

    private val glProgramUniformMatrix4x3fv = createBuffer().asFloatBuffer()

    override fun glProgramUniformMatrix4x3fv(program: Int, location: Int, count: Int, transpose: Boolean, value: Long) {
        GL41.glProgramUniformMatrix4x3(
            program,
            location,
            transpose,
            wrapBuffer(glProgramUniformMatrix4x3fv, value, count * 4 * 3)
        )
    }
}
