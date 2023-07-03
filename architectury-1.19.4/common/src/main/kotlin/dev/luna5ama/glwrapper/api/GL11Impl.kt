package dev.luna5ama.glwrapper.api

import com.mojang.blaze3d.platform.GlStateManager
import dev.luna5ama.kmogus.Arr
import org.lwjgl.opengl.GL11
import org.lwjgl.opengl.GL11C

open class GL11Impl(tempArr: Arr) : GL11LWJGL3(tempArr) {
    override fun glEnable(cap: Int) {
        when (cap) {
            GL11C.GL_SCISSOR_TEST -> GlStateManager._enableScissorTest()
            GL11C.GL_DEPTH_TEST -> GlStateManager._enableDepthTest()
            GL11C.GL_BLEND -> GlStateManager._enableBlend()
            GL11C.GL_CULL_FACE -> GlStateManager._enableCull()
            GL11C.GL_POLYGON_OFFSET_FILL -> GlStateManager._enablePolygonOffset()
            GL11C.GL_COLOR_LOGIC_OP -> GlStateManager._enableColorLogicOp()
            else -> GL11.glEnable(cap)
        }
    }

    override fun glDisable(cap: Int) {
        when (cap) {
            GL11C.GL_SCISSOR_TEST -> GlStateManager._disableScissorTest()
            GL11C.GL_DEPTH_TEST -> GlStateManager._disableDepthTest()
            GL11C.GL_BLEND -> GlStateManager._disableBlend()
            GL11C.GL_CULL_FACE -> GlStateManager._disableCull()
            GL11C.GL_POLYGON_OFFSET_FILL -> GlStateManager._disablePolygonOffset()
            GL11C.GL_COLOR_LOGIC_OP -> GlStateManager._disableColorLogicOp()
            else -> GL11.glDisable(cap)
        }
    }

    override fun glBlendFunc(sfactor: Int, dfactor: Int) {
        GlStateManager._blendFunc(sfactor, dfactor)
    }

    override fun glClearColor(red: Float, green: Float, blue: Float, alpha: Float) {
        GlStateManager._clearColor(red, green, blue, alpha)
    }

    override fun glClearDepth(depth: Double) {
        GlStateManager._clearDepth(depth)
    }

    override fun glColorMask(red: Boolean, green: Boolean, blue: Boolean, alpha: Boolean) {
        GlStateManager._colorMask(red, green, blue, alpha)
    }

    override fun glDepthFunc(func: Int) {
        GlStateManager._depthFunc(func)
    }

    override fun glDepthMask(flag: Boolean) {
        GlStateManager._depthMask(flag)
    }
}