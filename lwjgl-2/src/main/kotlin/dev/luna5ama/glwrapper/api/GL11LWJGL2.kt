package dev.luna5ama.glwrapper.api

import dev.luna5ama.kmogus.Arr
import org.lwjgl.opengl.GL11
import java.lang.invoke.MethodType

open class GL11LWJGL2(override val tempArr: Arr) : IGL11 {
    override fun glEnable(target: Int) {
        GL11.glEnable(target)
    }

    override fun glDisable(target: Int) {
        GL11.glDisable(target)
    }

    override fun glBlendFunc(sfactor: Int, dfactor: Int) {
        GL11.glBlendFunc(sfactor, dfactor)
    }

    override fun glClear(mask: Int) {
        GL11.glClear(mask)
    }

    override fun glClearColor(red: Float, green: Float, blue: Float, alpha: Float) {
        GL11.glClearColor(red, green, blue, alpha)
    }

    override fun glClearDepth(depth: Double) {
        GL11.glClearDepth(depth)
    }

    override fun glClearStencil(s: Int) {
        GL11.glClearStencil(s)
    }

    override fun glColorMask(red: Boolean, green: Boolean, blue: Boolean, alpha: Boolean) {
        GL11.glColorMask(red, green, blue, alpha)
    }

    override fun glCullFace(mode: Int) {
        GL11.glCullFace(mode)
    }

    override fun glDepthFunc(func: Int) {
        GL11.glDepthFunc(func)
    }

    override fun glDepthMask(flag: Boolean) {
        GL11.glDepthMask(flag)
    }

    override fun glDepthRange(zNear: Double, zFar: Double) {
        GL11.glDepthRange(zNear, zFar)
    }

    override fun glDrawArrays(mode: Int, first: Int, count: Int) {
        GL11.glDrawArrays(mode, first, count)
    }

    override fun glDrawBuffer(buf: Int) {
        GL11.glDrawBuffer(buf)
    }

    private val glDrawElements = getFunctionAddress("glDrawElements")

    private val nglDrawElementsBOMethod = trustedLookUp.findStatic(
        GL11::class.java,
        "nglDrawElementsBO",
        MethodType.methodType(
            Void.TYPE,
            Int::class.java,
            Int::class.java,
            Int::class.java,
            Long::class.java,
            Long::class.java
        )
    )

    override fun glDrawElements(mode: Int, count: Int, type: Int, indices: Long) {
        nglDrawElementsBOMethod.invokeExact(mode, count, type, indices, glDrawElements)
    }

    override fun glFinish() {
        GL11.glFinish()
    }

    override fun glFlush() {
        GL11.glFlush()
    }

    override fun glFrontFace(mode: Int) {
        GL11.glFrontFace(mode)
    }

    private val glDeleteTextures = createBuffer().asIntBuffer()

    override fun glDeleteTextures(n: Int, textures: Long) {
        GL11.glDeleteTextures(wrapBuffer(glDeleteTextures, textures, n))
    }

    override fun glDeleteTextures(texture: Int) {
        GL11.glDeleteTextures(texture)
    }

    private val glGetBooleanv = wrapBuffer(createBuffer(), 16)

    override fun glGetBooleanv(pname: Int, params: Long) {
        GL11.glGetBoolean(pname, wrapBuffer(glGetBooleanv, params))
    }

    override fun glGetBoolean(pname: Int): Boolean {
        return GL11.glGetBoolean(pname)
    }

    private val glGetIntegerv =
        wrapBuffer(createBuffer().asIntBuffer(), 16)

    override fun glGetIntegerv(pname: Int, params: Long) {
        GL11.glGetInteger(pname, wrapBuffer(glGetIntegerv, params))
    }

    override fun glGetInteger(pname: Int): Int {
        return GL11.glGetInteger(pname)
    }

    private val glGetFloatv =
        wrapBuffer(createBuffer().asFloatBuffer(), 16)

    override fun glGetFloatv(pname: Int, params: Long) {
        GL11.glGetFloat(pname, wrapBuffer(glGetFloatv, params))
    }

    override fun glGetFloat(pname: Int): Float {
        return GL11.glGetFloat(pname)
    }

    private val glGetDoublev =
        wrapBuffer(createBuffer().asDoubleBuffer(), 16)

    override fun glGetDoublev(pname: Int, params: Long) {
        GL11.glGetDouble(pname, wrapBuffer(glGetDoublev, params))
    }

    override fun glGetDouble(pname: Int): Double {
        return GL11.glGetDouble(pname)
    }

    override fun glGetError(): Int {
        return GL11.glGetError()
    }

    override fun glGetString(name: Int): String? {
        return GL11.glGetString(name)
    }

    override fun glHint(target: Int, mode: Int) {
        GL11.glHint(target, mode)
    }

    override fun glIsEnabled(cap: Int): Boolean {
        return GL11.glIsEnabled(cap)
    }

    override fun glIsTexture(texture: Int): Boolean {
        return GL11.glIsTexture(texture)
    }

    override fun glLineWidth(width: Float) {
        GL11.glLineWidth(width)
    }

    override fun glPointSize(size: Float) {
        GL11.glPointSize(size)
    }

    override fun glPolygonMode(face: Int, mode: Int) {
        GL11.glPolygonMode(face, mode)
    }

    override fun glPolygonOffset(factor: Float, units: Float) {
        GL11.glPolygonOffset(factor, units)
    }

    override fun glReadBuffer(src: Int) {
        GL11.glReadBuffer(src)
    }

    private val glReadPixels = createBuffer()

    override fun glReadPixels(x: Int, y: Int, width: Int, height: Int, format: Int, type: Int, pixels: Long) {
        GL11.glReadPixels(x, y, width, height, format, type, wrapBuffer(glReadPixels, pixels))
    }

    override fun glScissor(x: Int, y: Int, width: Int, height: Int) {
        GL11.glScissor(x, y, width, height)
    }

    override fun glStencilFunc(func: Int, ref: Int, mask: Int) {
        GL11.glStencilFunc(func, ref, mask)
    }

    override fun glStencilMask(mask: Int) {
        GL11.glStencilMask(mask)
    }

    override fun glStencilOp(fail: Int, zfail: Int, zpass: Int) {
        GL11.glStencilOp(fail, zfail, zpass)
    }

    override fun glViewport(x: Int, y: Int, width: Int, height: Int) {
        GL11.glViewport(x, y, width, height)
    }
}