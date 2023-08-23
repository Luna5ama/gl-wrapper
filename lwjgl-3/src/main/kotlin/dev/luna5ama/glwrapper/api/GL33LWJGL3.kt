package dev.luna5ama.glwrapper.api

import dev.luna5ama.kmogus.Arr
import org.lwjgl.opengl.GL33C

open class GL33LWJGL3(override val tempArr: Arr) : IGL33 {
    override fun glDeleteSamplers(count: Int, samplers: Long) {
        GL33C.nglDeleteSamplers(count, samplers)
    }

    override fun glIsSampler(sampler: Int): Boolean {
        return GL33C.glIsSampler(sampler)
    }

    override fun glBindSampler(unit: Int, sampler: Int) {
        GL33C.glBindSampler(unit, sampler)
    }

    override fun glDeleteSamplers(sampler: Int) {
        GL33C.glDeleteSamplers(sampler)
    }

    override fun glDeleteSamplers(vararg samplers: Int) {
        GL33C.glDeleteSamplers(samplers)
    }

    override fun glSamplerParameterfv(texture: Int, pname: Int, params: Long) {
        GL33C.nglSamplerParameterfv(texture, pname, params)
    }

    override fun glSamplerParameterf(texture: Int, pname: Int, param: Float) {
        GL33C.glSamplerParameterf(texture, pname, param)
    }

    override fun glSamplerParameteri(texture: Int, pname: Int, param: Int) {
        GL33C.glSamplerParameteri(texture, pname, param)
    }

    override fun glSamplerParameteriv(texture: Int, pname: Int, params: Long) {
        GL33C.nglSamplerParameteriv(texture, pname, params)
    }

    override fun glSamplerParameterIiv(texture: Int, pname: Int, params: Long) {
        GL33C.nglSamplerParameterIiv(texture, pname, params)
    }

    override fun glSamplerParameterIuiv(texture: Int, pname: Int, params: Long) {
        GL33C.nglSamplerParameterIuiv(texture, pname, params)
    }
}
