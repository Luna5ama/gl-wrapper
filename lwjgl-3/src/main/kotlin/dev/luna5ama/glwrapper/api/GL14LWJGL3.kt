package dev.luna5ama.glwrapper.api

import dev.luna5ama.kmogus.Arr
import org.lwjgl.opengl.GL14C

open class GL14LWJGL3(override val tempArr: Arr) : IGL14 {
    override fun glBlendColor(red: Float, green: Float, blue: Float, alpha: Float) {
        GL14C.glBlendColor(red, green, blue, alpha)
    }

    override fun glBlendEquation(mode: Int) {
        GL14C.glBlendEquation(mode)
    }

    override fun glMultiDrawArrays(mode: Int, first: Long, count: Long, drawcount: Int) {
        GL14C.nglMultiDrawArrays(mode, first, count, drawcount)
    }

    override fun glMultiDrawElements(mode: Int, count: Long, type: Int, indices: Long, drawcount: Int) {
        GL14C.nglMultiDrawElements(mode, count, type, indices, drawcount)
    }

    override fun glPointParameterfv(pname: Int, params: Long) {
        GL14C.nglPointParameterfv(pname, params)
    }

    override fun glPointParameteriv(pname: Int, params: Long) {
        GL14C.nglPointParameteriv(pname, params)
    }

    override fun glBlendFuncSeparate(sfactorRGB: Int, dfactorRGB: Int, sfactorAlpha: Int, dfactorAlpha: Int) {
        GL14C.glBlendFuncSeparate(sfactorRGB, dfactorRGB, sfactorAlpha, dfactorAlpha)
    }

    override fun glPointParameterf(pname: Int, param: Float) {
        GL14C.glPointParameterf(pname, param)
    }

    override fun glPointParameteri(pname: Int, param: Int) {
        GL14C.glPointParameteri(pname, param)
    }
}