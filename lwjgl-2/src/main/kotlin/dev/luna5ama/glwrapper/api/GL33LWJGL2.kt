package dev.luna5ama.glwrapper.api

import dev.luna5ama.kmogus.*
import org.lwjgl.opengl.GL33

open class GL33LWJGL2(override val tempArr: Arr) : IGL33 {
    private val glDeleteSamplers = nullIntBuffer()

    override fun glDeleteSamplers(count: Int, samplers: Long) {
        glDeleteSamplers(count, Ptr(samplers))
    }

    override fun glDeleteSamplers(count: Int, samplers: Ptr) {
        GL33.glDeleteSamplers(samplers.asIntBuffer(count, glDeleteSamplers))
    }

    override fun glIsSampler(sampler: Int): Boolean {
        return GL33.glIsSampler(sampler)
    }

    override fun glBindSampler(unit: Int, sampler: Int) {
        GL33.glBindSampler(unit, sampler)
    }

    override fun glDeleteSamplers(sampler: Int) {
        GL33.glDeleteSamplers(sampler)
    }

    override fun glSamplerParameterf(texture: Int, pname: Int, param: Float) {
        GL33.glSamplerParameterf(texture, pname, param)
    }

    override fun glSamplerParameteri(texture: Int, pname: Int, param: Int) {
        GL33.glSamplerParameteri(texture, pname, param)
    }

    override fun glSamplerParameterfv(texture: Int, pname: Int, params: Long) {
        glSamplerParameterfv(texture, pname, Ptr(params))
    }

    private val glSamplerParameterfv = nullFloatBuffer()

    override fun glSamplerParameterfv(texture: Int, pname: Int, params: Ptr) {
        GL33.glSamplerParameter(texture, pname, params.asFloatBuffer(4, glSamplerParameterfv))
    }

    override fun glSamplerParameteriv(texture: Int, pname: Int, params: Long) {
        glSamplerParameteriv(texture, pname, Ptr(params))
    }

    private val glSamplerParameteriv = nullIntBuffer()

    override fun glSamplerParameteriv(texture: Int, pname: Int, params: Ptr) {
        GL33.glSamplerParameter(texture, pname, params.asIntBuffer(4, glSamplerParameteriv))
    }

    override fun glSamplerParameterIiv(texture: Int, pname: Int, params: Long) {
        glSamplerParameteriv(texture, pname, Ptr(params))
    }

    private val glSamplerParameterIiv = nullIntBuffer()

    override fun glSamplerParameterIiv(texture: Int, pname: Int, params: Ptr) {
        GL33.glSamplerParameter(texture, pname, params.asIntBuffer(4, glSamplerParameterIiv))
    }

    override fun glSamplerParameterIuiv(texture: Int, pname: Int, params: Long) {
        glSamplerParameterIiv(texture, pname, Ptr(params))
    }

    private val glSamplerParameterIuiv = nullIntBuffer()

    override fun glSamplerParameterIuiv(texture: Int, pname: Int, params: Ptr) {
        GL33.glSamplerParameter(texture, pname, params.asIntBuffer(4, glSamplerParameterIuiv))
    }
}
