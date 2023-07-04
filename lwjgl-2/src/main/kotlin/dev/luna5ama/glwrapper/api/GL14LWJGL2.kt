package dev.luna5ama.glwrapper.api

import dev.luna5ama.kmogus.*
import org.lwjgl.opengl.GL14

open class GL14LWJGL2(override val tempArr: Arr) : IGL14 {
    override fun glBlendColor(red: Float, green: Float, blue: Float, alpha: Float) {
        GL14.glBlendColor(red, green, blue, alpha)
    }

    override fun glBlendEquation(mode: Int) {
        GL14.glBlendEquation(mode)
    }

    private val glMultiDrawArrays_first = nullIntBuffer()
    private val glMultiDrawArrays_count = nullIntBuffer()

    override fun glMultiDrawArrays(mode: Int, first: Long, count: Long, drawcount: Int) {
        glMultiDrawArrays(mode, Ptr(first), Ptr(count), drawcount)
    }

    override fun glMultiDrawArrays(mode: Int, first: Ptr, count: Ptr, drawcount: Int) {
        GL14.glMultiDrawArrays(
            mode,
            first.asIntBuffer(drawcount, glMultiDrawArrays_first),
            count.asIntBuffer(drawcount, glMultiDrawArrays_count)
        )
    }

    override fun glMultiDrawElements(mode: Int, count: Long, type: Int, indices: Long, drawcount: Int) {
        throw UnsupportedOperationException("glMultiDrawElements is not supported in LWJGL 2")
    }


    private val glPointParameterfv = nullFloatBuffer()

    override fun glPointParameterfv(pname: Int, params: Long) {
        glPointParameterfv(pname, Ptr(params))
    }

    override fun glPointParameterfv(pname: Int, params: Ptr) {
        GL14.glPointParameter(pname, params.asFloatBuffer(4, glPointParameterfv))
    }

    private val glPointParameteriv = nullIntBuffer()

    override fun glPointParameteriv(pname: Int, params: Long) {
        glPointParameteriv(pname, Ptr(params))
    }

    override fun glPointParameteriv(pname: Int, params: Ptr) {
        GL14.glPointParameter(pname, params.asIntBuffer(4, glPointParameteriv))
    }

    override fun glBlendFuncSeparate(sfactorRGB: Int, dfactorRGB: Int, sfactorAlpha: Int, dfactorAlpha: Int) {
        GL14.glBlendFuncSeparate(sfactorRGB, dfactorRGB, sfactorAlpha, dfactorAlpha)
    }

    override fun glPointParameterf(pname: Int, param: Float) {
        GL14.glPointParameterf(pname, param)
    }

    override fun glPointParameteri(pname: Int, param: Int) {
        GL14.glPointParameteri(pname, param)
    }
}