package dev.luna5ama.glwrapper.objects

import dev.luna5ama.glwrapper.api.*
import dev.luna5ama.glwrapper.enums.GLObjectType

class SamplerObject : IGLObject by IGLObject.Impl(GLObjectType.Sampler), IGLSampler {
    fun bindSamplerUnit(unit: Int) {
        checkCreated()
        glBindSampler(unit, id)
    }

    fun unbindSamplerUnit(unit: Int) {
        checkCreated()
        glBindSampler(unit, 0)
    }

    override fun parameterf(pname: Int, param: Float) {
        tryCreate()
        glSamplerParameterf(id, pname, param)
    }

    override fun parameteri(pname: Int, param: Int) {
        tryCreate()
        glSamplerParameteri(id, pname, param)
    }

    override fun parameterfv(pname: Int, v1: Float, v2: Float, v3: Float, v4: Float) {
        tryCreate()
        glSamplerParameterfv(id, pname, v1, v2, v3, v4)
    }

    override fun parameteriv(pname: Int, v1: Int, v2: Int, v3: Int, v4: Int) {
        tryCreate()
        glSamplerParameteriv(id, pname, v1, v2, v3, v4)
    }
}