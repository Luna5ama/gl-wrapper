package dev.luna5ama.glwrapper.api

import dev.luna5ama.glwrapper.api.intel.IntelGL45

internal class PatchedGLWrapper(private val delegate: GLWrapper) : GLWrapper by delegate {
    override val gl45 = when (vendor) {
        GpuVendor.INTEL -> IntelGL45(delegate.gl45)
        else -> delegate.gl45
    }
}