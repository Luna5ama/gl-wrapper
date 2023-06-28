package dev.luna5ama.glwrapper.lwjgl2

import dev.luna5ama.glwrapper.IGL14
import dev.luna5ama.kmogus.Arr
import org.lwjgl.opengl.GL14

open class GL14LWJGL2(override val tempArr: Arr) : IGL14 {
    override fun glBlendColor(red: Float, green: Float, blue: Float, alpha: Float) {
        GL14.glBlendColor(red, green, blue, alpha)
    }

    override fun glBlendEquation(mode: Int) {
        GL14.glBlendEquation(mode)
    }

    private val glMultiDrawArrays_first = createBuffer().asIntBuffer()
    private val glMultiDrawArrays_count = createBuffer().asIntBuffer()

    override fun glMultiDrawArrays(mode: Int, first: Long, count: Long, drawcount: Int) {
        GL14.glMultiDrawArrays(
            mode,
            wrapBuffer(glMultiDrawArrays_first, first, drawcount),
            wrapBuffer(glMultiDrawArrays_count, count, drawcount)
        )
    }

    override fun glMultiDrawElements(mode: Int, count: Long, type: Int, indices: Long, drawcount: Int) {
        throw UnsupportedOperationException("glMultiDrawElements is not supported in LWJGL 2")
    }


    private val glPointParameterfv = wrapBuffer(createBuffer().asFloatBuffer(), 4)

    override fun glPointParameterfv(pname: Int, params: Long) {
        GL14.glPointParameter(pname, wrapBuffer(glPointParameterfv, params))
    }

    private val glPointParameteriv = wrapBuffer(createBuffer().asIntBuffer(), 4)

    override fun glPointParameteriv(pname: Int, params: Long) {
        GL14.glPointParameter(pname, wrapBuffer(glPointParameteriv, params))
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