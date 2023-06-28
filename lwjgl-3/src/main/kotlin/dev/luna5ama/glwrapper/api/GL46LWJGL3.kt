package dev.luna5ama.glwrapper.api

import dev.luna5ama.kmogus.Arr
import org.lwjgl.opengl.GL46C

open class GL46LWJGL3(override val tempArr: Arr) : IGL46 {
    override fun glMultiDrawArraysIndirectCount(
        mode: Int,
        indirect: Long,
        drawcount: Long,
        maxdrawcount: Int,
        stride: Int
    ) {
        GL46C.glMultiDrawArraysIndirectCount(mode, indirect, drawcount, maxdrawcount, stride)
    }

    override fun glMultiDrawElementsIndirectCount(
        mode: Int,
        type: Int,
        indirect: Long,
        drawcount: Long,
        maxdrawcount: Int,
        stride: Int
    ) {
        GL46C.glMultiDrawElementsIndirectCount(mode, type, indirect, drawcount, maxdrawcount, stride)
    }
}
