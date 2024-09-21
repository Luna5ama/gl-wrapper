package dev.luna5ama.glwrapper.base

import com.mojang.blaze3d.platform.GlStateManager
import com.mojang.blaze3d.systems.RenderSystem
import org.lwjgl.opengl.GL11
import org.lwjgl.opengl.GL11C
import org.lwjgl.opengl.GL12

@Suppress("DEPRECATION")
class GL11Mc(private val delegate: dev.luna5ama.glwrapper.base.GL11) : dev.luna5ama.glwrapper.base.GL11 by delegate {
    override fun glEnable(target: Int) {
        when (target) {
            GL11.GL_ALPHA_TEST -> RenderSystem.enableAlphaTest()
            GL11.GL_LIGHTING -> RenderSystem.enableLighting()
            GL11.GL_COLOR_MATERIAL -> RenderSystem.enableColorMaterial()
            GL_SCISSOR_TEST -> GlStateManager.method_31319()
            GL_DEPTH_TEST -> RenderSystem.enableDepthTest()
            GL_BLEND -> RenderSystem.enableBlend()
            GL11.GL_FOG -> RenderSystem.enableFog()
            GL_CULL_FACE -> RenderSystem.enableCull()
            GL_POLYGON_OFFSET_FILL -> RenderSystem.enablePolygonOffset()
            GL_POLYGON_OFFSET_LINE -> RenderSystem.enableLineOffset()
            GL_COLOR_LOGIC_OP -> RenderSystem.enableColorLogicOp()
            GL_TEXTURE_2D -> RenderSystem.enableTexture()
            GL12.GL_RESCALE_NORMAL -> RenderSystem.enableRescaleNormal()
            else -> GL11.glEnable(target)
        }
    }

    override fun glDisable(target: Int) {
        when (target) {
            GL11.GL_ALPHA_TEST -> RenderSystem.disableAlphaTest()
            GL11.GL_LIGHTING -> RenderSystem.disableLighting()
            GL11.GL_COLOR_MATERIAL -> RenderSystem.disableColorMaterial()
            GL_SCISSOR_TEST -> RenderSystem.disableScissor()
            GL_DEPTH_TEST -> RenderSystem.disableDepthTest()
            GL_BLEND -> RenderSystem.disableBlend()
            GL11.GL_FOG -> RenderSystem.disableFog()
            GL_CULL_FACE -> RenderSystem.disableCull()
            GL_POLYGON_OFFSET_FILL -> RenderSystem.disablePolygonOffset()
            GL_POLYGON_OFFSET_LINE -> RenderSystem.disableLineOffset()
            GL_COLOR_LOGIC_OP -> RenderSystem.disableColorLogicOp()
            GL_TEXTURE_2D -> RenderSystem.disableTexture()
            GL12.GL_RESCALE_NORMAL -> RenderSystem.disableRescaleNormal()
            else -> GL11.glDisable(target)
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
        GlStateManager.logicOp(op)
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