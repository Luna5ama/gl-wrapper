package dev.luna5ama.glwrapper.api

import dev.luna5ama.kmogus.Arr
import org.lwjgl.opengl.GL33

open class GL33LWJGL2(override val tempArr: Arr) : IGL33 {
    private val glDeleteSamplers = createBuffer().asIntBuffer()

    override fun glDeleteSamplers(count: Int, samplers: Long) {
        GL33.glDeleteSamplers(wrapBuffer(glDeleteSamplers, samplers, count))
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
}
