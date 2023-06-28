package dev.luna5ama.glwrapper.lwjgl2

import dev.luna5ama.glwrapper.IGL13
import dev.luna5ama.kmogus.Arr
import org.lwjgl.opengl.GL13

open class GL13LWJGL2(override val tempArr: Arr) : IGL13 {
    override fun glSampleCoverage(value: Float, invert: Boolean) {
        GL13.glSampleCoverage(value, invert)
    }
}