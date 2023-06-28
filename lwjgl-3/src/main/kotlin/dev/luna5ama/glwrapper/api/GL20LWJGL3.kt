package dev.luna5ama.glwrapper.api

import dev.luna5ama.kmogus.Arr
import org.lwjgl.opengl.GL20C

open class GL20LWJGL3(override val tempArr: Arr) : IGL20 {
    override fun glCreateProgram(): Int {
        return GL20C.glCreateProgram()
    }

    override fun glDeleteProgram(program: Int) {
        GL20C.glDeleteProgram(program)
    }

    override fun glIsProgram(program: Int): Boolean {
        return GL20C.glIsProgram(program)
    }

    override fun glCreateShader(type: Int): Int {
        return GL20C.glCreateShader(type)
    }

    override fun glDeleteShader(shader: Int) {
        GL20C.glDeleteShader(shader)
    }

    override fun glIsShader(shader: Int): Boolean {
        return GL20C.glIsShader(shader)
    }

    override fun glAttachShader(program: Int, shader: Int) {
        GL20C.glAttachShader(program, shader)
    }

    override fun glDetachShader(program: Int, shader: Int) {
        GL20C.glDetachShader(program, shader)
    }

    override fun glShaderSource(shader: Int, string: CharSequence) {
        GL20C.glShaderSource(shader, string)
    }

    override fun glShaderSource(shader: Int, vararg string: CharSequence) {
        GL20C.glShaderSource(shader, *string)
    }

    override fun glCompileShader(shader: Int) {
        GL20C.glCompileShader(shader)
    }

    override fun glLinkProgram(program: Int) {
        GL20C.glLinkProgram(program)
    }

    override fun glUseProgram(program: Int) {
        GL20C.glUseProgram(program)
    }

    override fun glValidateProgram(program: Int) {
        GL20C.glValidateProgram(program)
    }

    override fun glGetShaderiv(shader: Int, pname: Int, params: Long) {
        GL20C.nglGetShaderiv(shader, pname, params)
    }

    override fun glGetProgramiv(program: Int, pname: Int, params: Long) {
        GL20C.nglGetProgramiv(program, pname, params)
    }

    override fun glGetShaderInfoLog(shader: Int, maxLength: Int): String {
        return GL20C.glGetShaderInfoLog(shader, maxLength)
    }

    override fun glGetProgramInfoLog(program: Int, maxLength: Int): String {
        return GL20C.glGetProgramInfoLog(program, maxLength)
    }

    override fun glGetUniformLocation(program: Int, name: CharSequence): Int {
        return GL20C.glGetUniformLocation(program, name)
    }

    override fun glDrawBuffers(n: Int, bufs: Long) {
        GL20C.nglDrawBuffers(n, bufs)
    }

    override fun glBlendEquationSeparate(modeRGB: Int, modeAlpha: Int) {
        GL20C.glBlendEquationSeparate(modeRGB, modeAlpha)
    }

    override fun glStencilOpSeparate(face: Int, sfail: Int, dpfail: Int, dppass: Int) {
        GL20C.glStencilOpSeparate(face, sfail, dpfail, dppass)
    }

    override fun glStencilFuncSeparate(face: Int, func: Int, ref: Int, mask: Int) {
        GL20C.glStencilFuncSeparate(face, func, ref, mask)
    }

    override fun glStencilMaskSeparate(face: Int, mask: Int) {
        GL20C.glStencilMaskSeparate(face, mask)
    }

    override fun glGetShaderi(shader: Int, pname: Int): Int {
        return GL20C.glGetShaderi(shader, pname)
    }

    override fun glGetProgrami(program: Int, pname: Int): Int {
        return GL20C.glGetProgrami(program, pname)
    }

    override fun glDrawBuffers(buffer: Int) {
        GL20C.glDrawBuffers(buffer)
    }

    override fun glDrawBuffers(vararg buffers: Int) {
        GL20C.glDrawBuffers(buffers)
    }
}