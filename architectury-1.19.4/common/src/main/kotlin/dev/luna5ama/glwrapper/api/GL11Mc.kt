package dev.luna5ama.glwrapper.api

import com.mojang.blaze3d.platform.GlStateManager

class GL11Mc(private val delegate: GL11) : GL11 by delegate {
    override fun glEnable(target: Int) {
        when (target) {
            GL_SCISSOR_TEST -> GlStateManager._enableScissorTest()
            GL_DEPTH_TEST -> GlStateManager._enableDepthTest()
            GL_BLEND -> GlStateManager._enableBlend()
            GL_CULL_FACE -> GlStateManager._enableCull()
            GL_POLYGON_OFFSET_FILL -> GlStateManager._enablePolygonOffset()
            GL_COLOR_LOGIC_OP -> GlStateManager._enableColorLogicOp()
            else -> delegate.glEnable(target)
        }
    }

    override fun glDisable(target: Int) {
        when (target) {
            GL_SCISSOR_TEST -> GlStateManager._disableScissorTest()
            GL_DEPTH_TEST -> GlStateManager._disableDepthTest()
            GL_BLEND -> GlStateManager._disableBlend()
            GL_CULL_FACE -> GlStateManager._disableCull()
            GL_POLYGON_OFFSET_FILL -> GlStateManager._disablePolygonOffset()
            GL_COLOR_LOGIC_OP -> GlStateManager._disableColorLogicOp()
            else -> delegate.glDisable(target)
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