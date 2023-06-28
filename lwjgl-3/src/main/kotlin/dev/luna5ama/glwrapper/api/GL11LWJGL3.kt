package dev.luna5ama.glwrapper.api

import dev.luna5ama.kmogus.Arr
import org.lwjgl.opengl.GL11C

open class GL11LWJGL3(override val tempArr: Arr) : IGL11 {
    override fun glEnable(target: Int) {
        GL11C.glEnable(target)
    }

    override fun glDisable(target: Int) {
        GL11C.glDisable(target)
    }

    override fun glBlendFunc(sfactor: Int, dfactor: Int) {
        GL11C.glBlendFunc(sfactor, dfactor)
    }

    override fun glClear(mask: Int) {
        GL11C.glClear(mask)
    }

    override fun glClearColor(red: Float, green: Float, blue: Float, alpha: Float) {
        GL11C.glClearColor(red, green, blue, alpha)
    }

    override fun glClearDepth(depth: Double) {
        GL11C.glClearDepth(depth)
    }

    override fun glClearStencil(s: Int) {
        GL11C.glClearStencil(s)
    }

    override fun glColorMask(red: Boolean, green: Boolean, blue: Boolean, alpha: Boolean) {
        GL11C.glColorMask(red, green, blue, alpha)
    }

    override fun glCullFace(mode: Int) {
        GL11C.glCullFace(mode)
    }

    override fun glDepthFunc(func: Int) {
        GL11C.glDepthFunc(func)
    }

    override fun glDepthMask(flag: Boolean) {
        GL11C.glDepthMask(flag)
    }

    override fun glDepthRange(zNear: Double, zFar: Double) {
        GL11C.glDepthRange(zNear, zFar)
    }

    override fun glDrawArrays(mode: Int, first: Int, count: Int) {
        GL11C.glDrawArrays(mode, first, count)
    }

    override fun glDrawBuffer(buf: Int) {
        GL11C.glDrawBuffer(buf)
    }

    override fun glDrawElements(mode: Int, count: Int, type: Int, indices: Long) {
        GL11C.glDrawElements(mode, count, type, indices)
    }

    override fun glFinish() {
        GL11C.glFinish()
    }

    override fun glFlush() {
        GL11C.glFlush()
    }

    override fun glFrontFace(mode: Int) {
        GL11C.glFrontFace(mode)
    }


    override fun glDeleteTextures(n: Int, textures: Long) {
        GL11C.nglDeleteTextures(n, textures)
    }

    override fun glDeleteTextures(texture: Int) {
        GL11C.glDeleteTextures(texture)
    }

    override fun glDeleteTextures(vararg textures: Int) {
        GL11C.glDeleteTextures(textures)
    }

    override fun glGetBooleanv(pname: Int, params: Long) {
        GL11C.nglGetBooleanv(pname, params)
    }

    override fun glGetBoolean(pname: Int): Boolean {
        return GL11C.glGetBoolean(pname)
    }

    override fun glGetIntegerv(pname: Int, params: Long) {
        GL11C.nglGetIntegerv(pname, params)
    }

    override fun glGetInteger(pname: Int): Int {
        return GL11C.glGetInteger(pname)
    }

    override fun glGetFloatv(pname: Int, params: Long) {
        GL11C.nglGetFloatv(pname, params)
    }

    override fun glGetFloat(pname: Int): Float {
        return GL11C.glGetFloat(pname)
    }

    override fun glGetDoublev(pname: Int, params: Long) {
        GL11C.nglGetDoublev(pname, params)
    }

    override fun glGetDouble(pname: Int): Double {
        return GL11C.glGetDouble(pname)
    }

    override fun glGetError(): Int {
        return GL11C.glGetError()
    }

    override fun glGetString(name: Int): String? {
        return GL11C.glGetString(name)
    }

    override fun glHint(target: Int, mode: Int) {
        GL11C.glHint(target, mode)
    }

    override fun glIsEnabled(cap: Int): Boolean {
        return GL11C.glIsEnabled(cap)
    }

    override fun glIsTexture(texture: Int): Boolean {
        return GL11C.glIsTexture(texture)
    }

    override fun glLineWidth(width: Float) {
        GL11C.glLineWidth(width)
    }

    override fun glPointSize(size: Float) {
        GL11C.glPointSize(size)
    }

    override fun glPolygonMode(face: Int, mode: Int) {
        GL11C.glPolygonMode(face, mode)
    }

    override fun glPolygonOffset(factor: Float, units: Float) {
        GL11C.glPolygonOffset(factor, units)
    }

    override fun glReadBuffer(src: Int) {
        GL11C.glReadBuffer(src)
    }

    override fun glReadPixels(x: Int, y: Int, width: Int, height: Int, format: Int, type: Int, pixels: Long) {
        GL11C.glReadPixels(x, y, width, height, format, type, pixels)
    }

    override fun glScissor(x: Int, y: Int, width: Int, height: Int) {
        GL11C.glScissor(x, y, width, height)
    }

    override fun glStencilFunc(func: Int, ref: Int, mask: Int) {
        GL11C.glStencilFunc(func, ref, mask)
    }

    override fun glStencilMask(mask: Int) {
        GL11C.glStencilMask(mask)
    }

    override fun glStencilOp(fail: Int, zfail: Int, zpass: Int) {
        GL11C.glStencilOp(fail, zfail, zpass)
    }

    override fun glViewport(x: Int, y: Int, width: Int, height: Int) {
        GL11C.glViewport(x, y, width, height)
    }
}