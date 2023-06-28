package dev.luna5ama.glwrapper.api

import dev.luna5ama.kmogus.Arr
import org.lwjgl.opengl.GL12C

open class GL12LWJGL3(override val tempArr: Arr) : IGL12 {
    override fun glDrawRangeElements(mode: Int, start: Int, end: Int, count: Int, type: Int, indices: Long) {
        GL12C.glDrawRangeElements(mode, start, end, count, type, indices)
    }
}