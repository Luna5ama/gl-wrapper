package dev.luna5ama.glwrapper.impl

import dev.luna5ama.glwrapper.api.*

class SamplerObject : IGLObject by IGLObject.Impl(GLObjectType.SAMPLER), IGLSampler {
    fun bindSamplerUnit(unit: Int) {
        checkCreated()
        glBindSampler(unit, id)
    }

    fun unbindSamplerUnit(unit: Int) {
        checkCreated()
        glBindSampler(unit, 0)
    }

    override fun parameterf0(pname: Int, param: Float) {
        glSamplerParameterf(id, pname, param)
    }

    override fun parameteri0(pname: Int, param: Int) {
        glSamplerParameteri(id, pname, param)
    }

    override fun parameterfv0(pname: Int, v1: Float, v2: Float, v3: Float, v4: Float) {
        glSamplerParameterfv(id, pname, v1, v2, v3, v4)
    }

    override fun parameteriv0(pname: Int, v1: Int, v2: Int, v3: Int, v4: Int) {
        glSamplerParameteriv(id, pname, v1, v2, v3, v4)
    }
}