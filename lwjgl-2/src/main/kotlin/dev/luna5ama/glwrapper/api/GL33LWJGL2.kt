package dev.luna5ama.glwrapper.api

import dev.luna5ama.kmogus.Arr
import dev.luna5ama.kmogus.Ptr
import dev.luna5ama.kmogus.asIntBuffer
import dev.luna5ama.kmogus.nullIntBuffer
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
}
