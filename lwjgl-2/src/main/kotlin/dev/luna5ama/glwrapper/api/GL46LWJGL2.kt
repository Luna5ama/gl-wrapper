package dev.luna5ama.glwrapper.api

import dev.luna5ama.kmogus.Arr
import org.lwjgl.opengl.ARBIndirectParameters

open class GL46LWJGL2(override val tempArr: Arr) : IGL46 {
    override fun glMultiDrawArraysIndirectCount(
        mode: Int,
        indirect: Long,
        drawcount: Long,
        maxdrawcount: Int,
        stride: Int
    ) {
        ARBIndirectParameters.glMultiDrawArraysIndirectCountARB(mode, indirect, drawcount, maxdrawcount, stride)
    }

    override fun glMultiDrawElementsIndirectCount(
        mode: Int,
        type: Int,
        indirect: Long,
        drawcount: Long,
        maxdrawcount: Int,
        stride: Int
    ) {
        ARBIndirectParameters.glMultiDrawElementsIndirectCountARB(mode, type, indirect, drawcount, maxdrawcount, stride)
    }
}
