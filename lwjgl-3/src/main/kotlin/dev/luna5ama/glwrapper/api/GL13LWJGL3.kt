package dev.luna5ama.glwrapper.api

import dev.luna5ama.kmogus.Arr
import org.lwjgl.opengl.GL13C

open class GL13LWJGL3(override val tempArr: Arr) : IGL13 {
    override fun glSampleCoverage(value: Float, invert: Boolean) {
        GL13C.glSampleCoverage(value, invert)
    }
}