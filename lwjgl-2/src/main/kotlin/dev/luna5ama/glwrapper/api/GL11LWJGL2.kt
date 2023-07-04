package dev.luna5ama.glwrapper.api

import dev.luna5ama.kmogus.*
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

    private val glDeleteTextures = nullIntBuffer()

    override fun glDeleteTextures(n: Int, textures: Long) {
        glDeleteTextures(n, Ptr(textures))
    }

    override fun glDeleteTextures(n: Int, textures: Ptr) {
        GL11.glDeleteTextures(textures.asIntBuffer(n, glDeleteTextures))
    }

    override fun glDeleteTextures(texture: Int) {
        GL11.glDeleteTextures(texture)
    }

    private val glGetBooleanv = nullByteBuffer()

    override fun glGetBooleanv(pname: Int, params: Long) {
        glGetBooleanv(pname, Ptr(params))
    }

    override fun glGetBooleanv(pname: Int, params: Ptr) {
        GL11.glGetBoolean(pname, params.asByteBuffer(16, glGetBooleanv))
    }

    override fun glGetBoolean(pname: Int): Boolean {
        return GL11.glGetBoolean(pname)
    }

    private val glGetIntegerv = nullIntBuffer()

    override fun glGetIntegerv(pname: Int, params: Long) {
        glGetIntegerv(pname, Ptr(params))
    }

    override fun glGetIntegerv(pname: Int, params: Ptr) {
        GL11.glGetInteger(pname, params.asIntBuffer(16, glGetIntegerv))
    }

    override fun glGetInteger(pname: Int): Int {
        return GL11.glGetInteger(pname)
    }

    private val glGetFloatv = nullFloatBuffer()

    override fun glGetFloatv(pname: Int, params: Long) {
        glGetFloatv(pname, Ptr(params))
    }

    override fun glGetFloatv(pname: Int, params: Ptr) {
        GL11.glGetFloat(pname, params.asFloatBuffer(16, glGetFloatv))
    }

    override fun glGetFloat(pname: Int): Float {
        return GL11.glGetFloat(pname)
    }

    private val glGetDoublev = nullDoubleBuffer()

    override fun glGetDoublev(pname: Int, params: Long) {
        glGetDoublev(pname, Ptr(params))
    }

    override fun glGetDoublev(pname: Int, params: Ptr) {
        GL11.glGetDouble(pname, params.asDoubleBuffer(16, glGetDoublev))
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

    private val glReadPixels = nullByteBuffer()

    override fun glReadPixels(
        x: Int,
        y: Int,
        width: Int,
        height: Int,
        format: Int,
        type: Int,
        pixels: Long
    ) {
        glReadPixels(x, y, width, height, format, type, Ptr(pixels))
    }

    override fun glReadPixels(
        x: Int,
        y: Int,
        width: Int,
        height: Int,
        format: Int,
        type: Int,
        pixels: Ptr
    ) {
        GL11.glReadPixels(x, y, width, height, format, type, pixels.asByteBuffer(width * height * 8, glReadPixels))
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