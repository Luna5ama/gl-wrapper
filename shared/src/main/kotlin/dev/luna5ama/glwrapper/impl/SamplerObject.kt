package dev.luna5ama.glwrapper.impl

import dev.luna5ama.glwrapper.api.glBindSampler

@Suppress("PARAMETER_NAME_CHANGED_ON_OVERRIDE")
class SamplerObject : IGLObject by IGLObject.Impl(GLObjectType.SAMPLER), IGLTargetBinding {
    override fun bind(unit: Int) {
        glBindSampler(unit, id)
    }

    override fun unbind(unit: Int) {
        glBindSampler(unit, 0)
    }
}