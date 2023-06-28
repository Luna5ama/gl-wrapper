package dev.luna5ama.glwrapper.api

import dev.luna5ama.kmogus.Arr
import org.lwjgl.opengl.GL41C

open class GL41LWJGL3(override val tempArr: Arr) : IGL41 {
    override fun glProgramUniform1f(program: Int, location: Int, v0: Float) {
        GL41C.glProgramUniform1f(program, location, v0)
    }

    override fun glProgramUniform2f(program: Int, location: Int, v0: Float, v1: Float) {
        GL41C.glProgramUniform2f(program, location, v0, v1)
    }

    override fun glProgramUniform3f(program: Int, location: Int, v0: Float, v1: Float, v2: Float) {
        GL41C.glProgramUniform3f(program, location, v0, v1, v2)
    }

    override fun glProgramUniform4f(program: Int, location: Int, v0: Float, v1: Float, v2: Float, v3: Float) {
        GL41C.glProgramUniform4f(program, location, v0, v1, v2, v3)
    }

    override fun glProgramUniform1i(program: Int, location: Int, v0: Int) {
        GL41C.glProgramUniform1i(program, location, v0)
    }

    override fun glProgramUniform2i(program: Int, location: Int, v0: Int, v1: Int) {
        GL41C.glProgramUniform2i(program, location, v0, v1)
    }

    override fun glProgramUniform3i(program: Int, location: Int, v0: Int, v1: Int, v2: Int) {
        GL41C.glProgramUniform3i(program, location, v0, v1, v2)
    }

    override fun glProgramUniform4i(program: Int, location: Int, v0: Int, v1: Int, v2: Int, v3: Int) {
        GL41C.glProgramUniform4i(program, location, v0, v1, v2, v3)
    }

    override fun glProgramUniform1ui(program: Int, location: Int, v0: Int) {
        GL41C.glProgramUniform1ui(program, location, v0)
    }

    override fun glProgramUniform2ui(program: Int, location: Int, v0: Int, v1: Int) {
        GL41C.glProgramUniform2ui(program, location, v0, v1)
    }

    override fun glProgramUniform3ui(program: Int, location: Int, v0: Int, v1: Int, v2: Int) {
        GL41C.glProgramUniform3ui(program, location, v0, v1, v2)
    }

    override fun glProgramUniform4ui(program: Int, location: Int, v0: Int, v1: Int, v2: Int, v3: Int) {
        GL41C.glProgramUniform4ui(program, location, v0, v1, v2, v3)
    }

    override fun glProgramUniform1fv(program: Int, location: Int, count: Int, value: Long) {
        GL41C.nglProgramUniform1fv(program, location, count, value)
    }

    override fun glProgramUniform2fv(program: Int, location: Int, count: Int, value: Long) {
        GL41C.nglProgramUniform2fv(program, location, count, value)
    }

    override fun glProgramUniform3fv(program: Int, location: Int, count: Int, value: Long) {
        GL41C.nglProgramUniform3fv(program, location, count, value)
    }

    override fun glProgramUniform4fv(program: Int, location: Int, count: Int, value: Long) {
        GL41C.nglProgramUniform4fv(program, location, count, value)
    }

    override fun glProgramUniform1iv(program: Int, location: Int, count: Int, value: Long) {
        GL41C.nglProgramUniform1iv(program, location, count, value)
    }

    override fun glProgramUniform2iv(program: Int, location: Int, count: Int, value: Long) {
        GL41C.nglProgramUniform2iv(program, location, count, value)
    }

    override fun glProgramUniform3iv(program: Int, location: Int, count: Int, value: Long) {
        GL41C.nglProgramUniform3iv(program, location, count, value)
    }

    override fun glProgramUniform4iv(program: Int, location: Int, count: Int, value: Long) {
        GL41C.nglProgramUniform4iv(program, location, count, value)
    }

    override fun glProgramUniform1uiv(program: Int, location: Int, count: Int, value: Long) {
        GL41C.nglProgramUniform1uiv(program, location, count, value)
    }

    override fun glProgramUniform2uiv(program: Int, location: Int, count: Int, value: Long) {
        GL41C.nglProgramUniform2uiv(program, location, count, value)
    }

    override fun glProgramUniform3uiv(program: Int, location: Int, count: Int, value: Long) {
        GL41C.nglProgramUniform3uiv(program, location, count, value)
    }

    override fun glProgramUniform4uiv(program: Int, location: Int, count: Int, value: Long) {
        GL41C.nglProgramUniform4uiv(program, location, count, value)
    }

    override fun glProgramUniformMatrix2fv(program: Int, location: Int, count: Int, transpose: Boolean, value: Long) {
        GL41C.nglProgramUniformMatrix2fv(program, location, count, transpose, value)
    }

    override fun glProgramUniformMatrix3fv(program: Int, location: Int, count: Int, transpose: Boolean, value: Long) {
        GL41C.nglProgramUniformMatrix3fv(program, location, count, transpose, value)
    }

    override fun glProgramUniformMatrix4fv(program: Int, location: Int, count: Int, transpose: Boolean, value: Long) {
        GL41C.nglProgramUniformMatrix4fv(program, location, count, transpose, value)
    }

    override fun glProgramUniformMatrix2x3fv(program: Int, location: Int, count: Int, transpose: Boolean, value: Long) {
        GL41C.nglProgramUniformMatrix2x3fv(program, location, count, transpose, value)
    }

    override fun glProgramUniformMatrix3x2fv(program: Int, location: Int, count: Int, transpose: Boolean, value: Long) {
        GL41C.nglProgramUniformMatrix3x2fv(program, location, count, transpose, value)
    }

    override fun glProgramUniformMatrix2x4fv(program: Int, location: Int, count: Int, transpose: Boolean, value: Long) {
        GL41C.nglProgramUniformMatrix2x4fv(program, location, count, transpose, value)
    }

    override fun glProgramUniformMatrix4x2fv(program: Int, location: Int, count: Int, transpose: Boolean, value: Long) {
        GL41C.nglProgramUniformMatrix4x2fv(program, location, count, transpose, value)
    }

    override fun glProgramUniformMatrix3x4fv(program: Int, location: Int, count: Int, transpose: Boolean, value: Long) {
        GL41C.nglProgramUniformMatrix3x4fv(program, location, count, transpose, value)
    }

    override fun glProgramUniformMatrix4x3fv(program: Int, location: Int, count: Int, transpose: Boolean, value: Long) {
        GL41C.nglProgramUniformMatrix4x3fv(program, location, count, transpose, value)
    }
}
