package dev.luna5ama.glwrapper.base

import dev.luna5ama.kmogus.Arr
import org.lwjgl.opengl.GL40

open class GL40LWJGL2(override val tempArr: Arr) : IGL40 {
    override fun glPatchParameteri(pname: Int, value: Int) {
        GL40.glPatchParameteri(pname, value)
    }

    override fun glBlendEquationi(buf: Int, mode: Int) {
        GL40.glBlendEquationi(buf, mode)
    }

    override fun glBlendEquationSeparatei(buf: Int, modeRGB: Int, modeAlpha: Int) {
        GL40.glBlendEquationSeparatei(buf, modeRGB, modeAlpha)
    }

    override fun glBlendFunci(buf: Int, src: Int, dst: Int) {
        GL40.glBlendFunci(buf, src, dst)
    }

    override fun glBlendFuncSeparatei(buf: Int, srcRGB: Int, dstRGB: Int, srcAlpha: Int, dstAlpha: Int) {
        GL40.glBlendFuncSeparatei(buf, srcRGB, dstRGB, srcAlpha, dstAlpha)
    }

    override fun glDrawArraysIndirect(mode: Int, indirect: Long) {
        GL40.glDrawArraysIndirect(mode, indirect)
    }

    override fun glDrawElementsIndirect(mode: Int, type: Int, indirect: Long) {
        GL40.glDrawElementsIndirect(mode, type, indirect)
    }

    override fun glMinSampleShading(value: Float) {
        GL40.glMinSampleShading(value)
    }
}
