package dev.luna5ama.glwrapper.api

import dev.luna5ama.kmogus.Arr
import org.lwjgl.opengl.GL40C

open class GL40LWJGL3(override val tempArr: Arr) : IGL40 {
    override fun glBlendEquationi(buf: Int, mode: Int) {
        GL40C.glBlendEquationi(buf, mode)
    }

    override fun glBlendEquationSeparatei(buf: Int, modeRGB: Int, modeAlpha: Int) {
        GL40C.glBlendEquationSeparatei(buf, modeRGB, modeAlpha)
    }

    override fun glBlendFunci(buf: Int, src: Int, dst: Int) {
        GL40C.glBlendFunci(buf, src, dst)
    }

    override fun glBlendFuncSeparatei(buf: Int, srcRGB: Int, dstRGB: Int, srcAlpha: Int, dstAlpha: Int) {
        GL40C.glBlendFuncSeparatei(buf, srcRGB, dstRGB, srcAlpha, dstAlpha)
    }

    override fun glDrawArraysIndirect(mode: Int, indirect: Long) {
        GL40C.glDrawArraysIndirect(mode, indirect)
    }

    override fun glDrawElementsIndirect(mode: Int, type: Int, indirect: Long) {
        GL40C.glDrawElementsIndirect(mode, type, indirect)
    }

    override fun glMinSampleShading(value: Float) {
        GL40C.glMinSampleShading(value)
    }
}
