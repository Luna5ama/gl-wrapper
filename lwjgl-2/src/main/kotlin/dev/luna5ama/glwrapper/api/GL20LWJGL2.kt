package dev.luna5ama.glwrapper.api

import dev.luna5ama.kmogus.Arr
import org.lwjgl.opengl.GL11
import org.lwjgl.opengl.GL20
import java.lang.invoke.MethodType

open class GL20LWJGL2(override val tempArr: Arr) : IGL20 {
    override fun glCreateProgram(): Int {
        return GL20.glCreateProgram()
    }

    override fun glDeleteProgram(program: Int) {
        GL20.glDeleteProgram(program)
    }

    override fun glIsProgram(program: Int): Boolean {
        return GL20.glIsProgram(program)
    }

    override fun glCreateShader(type: Int): Int {
        return GL20.glCreateShader(type)
    }

    override fun glDeleteShader(shader: Int) {
        GL20.glDeleteShader(shader)
    }

    override fun glIsShader(shader: Int): Boolean {
        return GL20.glIsShader(shader)
    }

    override fun glAttachShader(program: Int, shader: Int) {
        GL20.glAttachShader(program, shader)
    }

    override fun glDetachShader(program: Int, shader: Int) {
        GL20.glDetachShader(program, shader)
    }

    override fun glShaderSource(shader: Int, string: CharSequence) {
        GL20.glShaderSource(shader, string)
    }

    override fun glShaderSource(shader: Int, vararg string: CharSequence) {
        GL20.glShaderSource(shader, string)
    }

    override fun glCompileShader(shader: Int) {
        GL20.glCompileShader(shader)
    }

    override fun glLinkProgram(program: Int) {
        GL20.glLinkProgram(program)
    }

    override fun glUseProgram(program: Int) {
        GL20.glUseProgram(program)
    }

    override fun glValidateProgram(program: Int) {
        GL20.glValidateProgram(program)
    }

    private val glGetShaderiv = wrapBuffer(createBuffer().asIntBuffer(), 1)

    override fun glGetShaderiv(shader: Int, pname: Int, params: Long) {
        GL20.glGetShader(shader, pname, wrapBuffer(glGetShaderiv, params))
    }

    private val glGetProgramiv = getFunctionAddress("glGetProgramiv")
    private val nglGetProgramiv = trustedLookUp.findStatic(
        GL11::class.java,
        "nglGetProgramiv",
        MethodType.methodType(
            Void.TYPE,
            Int::class.java,
            Int::class.java,
            Long::class.java,
            Long::class.java,
        )
    )

    override fun glGetProgramiv(program: Int, pname: Int, params: Long) {
        nglGetProgramiv.invokeExact(program, pname, params, glGetProgramiv)
    }

    override fun glGetShaderInfoLog(shader: Int, maxLength: Int): String {
        return GL20.glGetShaderInfoLog(shader, maxLength)
    }

    override fun glGetProgramInfoLog(program: Int, maxLength: Int): String {
        return GL20.glGetProgramInfoLog(program, maxLength)
    }

    override fun glGetUniformLocation(program: Int, name: CharSequence): Int {
        return GL20.glGetUniformLocation(program, name)
    }

    private val glDrawBuffers = getFunctionAddress("glDrawBuffers")
    private val nglDrawBuffers = trustedLookUp.findStatic(
        GL11::class.java,
        "nglDrawBuffers",
        MethodType.methodType(
            Void.TYPE,
            Int::class.java,
            Long::class.java,
            Long::class.java,
        )
    )

    override fun glDrawBuffers(n: Int, bufs: Long) {
        nglDrawBuffers.invokeExact(n, bufs, glDrawBuffers)
    }

    override fun glBlendEquationSeparate(modeRGB: Int, modeAlpha: Int) {
        GL20.glBlendEquationSeparate(modeRGB, modeAlpha)
    }

    override fun glStencilOpSeparate(face: Int, sfail: Int, dpfail: Int, dppass: Int) {
        GL20.glStencilOpSeparate(face, sfail, dpfail, dppass)
    }

    override fun glStencilFuncSeparate(face: Int, func: Int, ref: Int, mask: Int) {
        GL20.glStencilFuncSeparate(face, func, ref, mask)
    }

    override fun glStencilMaskSeparate(face: Int, mask: Int) {
        GL20.glStencilMaskSeparate(face, mask)
    }

    override fun glGetShaderi(shader: Int, pname: Int): Int {
        return GL20.glGetShaderi(shader, pname)
    }

    override fun glGetProgrami(program: Int, pname: Int): Int {
        return GL20.glGetProgrami(program, pname)
    }

    override fun glDrawBuffers(buffer: Int) {
        GL20.glDrawBuffers(buffer)
    }
}