package dev.luna5ama.glwrapper.base

import com.mojang.blaze3d.platform.GlStateManager
import com.mojang.blaze3d.systems.RenderSystem
import org.lwjgl.opengl.GL11C

class GL11Mc(private val delegate: GL11) : GL11 by delegate {
    override fun glEnable(target: Int) {
        when (target) {
            GL_SCISSOR_TEST -> GlStateManager._enableScissorTest()
            GL_DEPTH_TEST -> RenderSystem.enableDepthTest()
            GL_BLEND -> RenderSystem.enableBlend()
            GL_CULL_FACE -> RenderSystem.enableCull()
            GL_POLYGON_OFFSET_FILL -> RenderSystem.enablePolygonOffset()
            GL_COLOR_LOGIC_OP -> RenderSystem.enableColorLogicOp()
            else -> delegate.glEnable(target)
        }
    }

    override fun glDisable(target: Int) {
        when (target) {
            GL_SCISSOR_TEST -> RenderSystem.disableScissor()
            GL_DEPTH_TEST -> RenderSystem.disableDepthTest()
            GL_BLEND -> RenderSystem.disableBlend()
            GL_CULL_FACE -> RenderSystem.disableCull()
            GL_POLYGON_OFFSET_FILL -> RenderSystem.disablePolygonOffset()
            GL_COLOR_LOGIC_OP -> RenderSystem.disableColorLogicOp()
            else -> delegate.glDisable(target)
        }
    }

    override fun glBlendFunc(sfactor: Int, dfactor: Int) {
        RenderSystem.blendFunc(sfactor, dfactor)
    }

    override fun glClearColor(red: Float, green: Float, blue: Float, alpha: Float) {
        RenderSystem.clearColor(red, green, blue, alpha)
    }

    override fun glClearDepth(depth: Double) {
        RenderSystem.clearDepth(depth)
    }

    override fun glColorMask(red: Boolean, green: Boolean, blue: Boolean, alpha: Boolean) {
        RenderSystem.colorMask(red, green, blue, alpha)
    }

    override fun glDepthFunc(func: Int) {
        RenderSystem.depthFunc(func)
    }

    override fun glDepthMask(flag: Boolean) {
        RenderSystem.depthMask(flag)
    }

    override fun glPolygonMode(face: Int, mode: Int) {
        RenderSystem.polygonMode(face, mode)
    }

    override fun glPolygonOffset(factor: Float, units: Float) {
        RenderSystem.polygonOffset(factor, units)
    }

    override fun glLogicOp(op: Int) {
        GlStateManager._logicOp(op)
    }

    override fun glViewport(x: Int, y: Int, w: Int, h: Int) {
        RenderSystem.viewport(x, y, w, h)
    }

    override fun glStencilFunc(func: Int, ref: Int, mask: Int) {
        RenderSystem.stencilFunc(func, ref, mask)
    }

    override fun glStencilOp(sfail: Int, dpfail: Int, dppass: Int) {
        RenderSystem.stencilOp(sfail, dpfail, dppass)
    }

    override fun glBindTexture(target: Int, texture: Int) {
        if (target == GL11C.GL_TEXTURE_2D) {
            RenderSystem.bindTexture(texture)
            return
        }
        delegate.glBindTexture(target, texture)
    }
}