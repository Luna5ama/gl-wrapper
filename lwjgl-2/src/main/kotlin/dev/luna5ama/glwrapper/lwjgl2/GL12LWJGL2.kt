package dev.luna5ama.glwrapper.lwjgl2

import dev.luna5ama.glwrapper.IGL12
import dev.luna5ama.kmogus.Arr
import org.lwjgl.opengl.GL12

open class GL12LWJGL2(override val tempArr: Arr) : IGL12 {
    override fun glDrawRangeElements(mode: Int, start: Int, end: Int, count: Int, type: Int, indices: Long) {
        GL12.glDrawRangeElements(mode, start, end, count, type, indices)
    }
}