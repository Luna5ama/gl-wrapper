package dev.luna5ama.glwrapper.api

import com.mojang.blaze3d.platform.GlStateManager
import dev.luna5ama.kmogus.Arr
import org.lwjgl.opengl.GL11
import org.lwjgl.opengl.GL11C
import org.lwjgl.opengl.GL12

@Suppress("DEPRECATION")
open class GL11Impl(tempArr: Arr) : GL11LWJGL3(tempArr) {
    override fun glEnable(target: Int) {
        when (target) {
            GL11.GL_ALPHA_TEST -> GlStateManager.enableAlphaTest()
            GL11.GL_LIGHTING -> GlStateManager.enableLighting()
            GL11.GL_COLOR_MATERIAL -> GlStateManager.enableColorMaterial()
            GL11C.GL_SCISSOR_TEST -> GlStateManager.method_31319()
            GL11C.GL_DEPTH_TEST -> GlStateManager.enableDepthTest()
            GL11C.GL_BLEND -> GlStateManager.enableBlend()
            GL11.GL_FOG -> GlStateManager.enableFog()
            GL11C.GL_CULL_FACE -> GlStateManager.enableCull()
            GL11C.GL_POLYGON_OFFSET_FILL -> GlStateManager.enablePolygonOffset()
            GL11C.GL_POLYGON_OFFSET_LINE -> GlStateManager.enableLineOffset()
            GL11C.GL_COLOR_LOGIC_OP -> GlStateManager.enableColorLogicOp()
            GL11C.GL_TEXTURE_2D -> GlStateManager.enableTexture()
            GL12.GL_RESCALE_NORMAL -> GlStateManager.enableRescaleNormal()
            else -> GL11.glEnable(target)
        }
    }

    override fun glDisable(target: Int) {
        when (target) {
            GL11.GL_ALPHA_TEST -> GlStateManager.disableAlphaTest()
            GL11.GL_LIGHTING -> GlStateManager.disableLighting()
            GL11.GL_COLOR_MATERIAL -> GlStateManager.disableColorMaterial()
            GL11C.GL_SCISSOR_TEST -> GlStateManager.method_31318()
            GL11C.GL_DEPTH_TEST -> GlStateManager.disableDepthTest()
            GL11C.GL_BLEND -> GlStateManager.disableBlend()
            GL11.GL_FOG -> GlStateManager.disableFog()
            GL11C.GL_CULL_FACE -> GlStateManager.disableCull()
            GL11C.GL_POLYGON_OFFSET_FILL -> GlStateManager.disablePolygonOffset()
            GL11C.GL_POLYGON_OFFSET_LINE -> GlStateManager.disableLineOffset()
            GL11C.GL_COLOR_LOGIC_OP -> GlStateManager.disableColorLogicOp()
            GL11C.GL_TEXTURE_2D -> GlStateManager.disableTexture()
            GL12.GL_RESCALE_NORMAL -> GlStateManager.disableRescaleNormal()
            else -> GL11.glDisable(target)
        }
    }

    override fun glBlendFunc(sfactor: Int, dfactor: Int) {
        GlStateManager.blendFunc(sfactor, dfactor)
    }

    override fun glClearColor(red: Float, green: Float, blue: Float, alpha: Float) {
        GlStateManager.clearColor(red, green, blue, alpha)
    }

    override fun glClearDepth(depth: Double) {
        GlStateManager.clearDepth(depth)
    }

    override fun glColorMask(red: Boolean, green: Boolean, blue: Boolean, alpha: Boolean) {
        GlStateManager.colorMask(red, green, blue, alpha)
    }

    override fun glDepthFunc(func: Int) {
        GlStateManager.depthFunc(func)
    }

    override fun glDepthMask(flag: Boolean) {
        GlStateManager.depthMask(flag)
    }
}