package dev.luna5ama.glwrapper.base

import dev.luna5ama.kmogus.Arr
import net.minecraft.client.renderer.GlStateManager
import org.lwjgl.opengl.GL11
import org.lwjgl.opengl.GL12

open class GL11Impl(tempArr: Arr) : GL11LWJGL2(tempArr) {
    override fun glEnable(target: Int) {
        when (target) {
            GL11.GL_ALPHA_TEST -> GlStateManager.enableAlpha()
            GL11.GL_LIGHTING -> GlStateManager.enableLighting()
            GL11.GL_COLOR_MATERIAL -> GlStateManager.enableColorMaterial()
            GL11.GL_DEPTH_TEST -> GlStateManager.enableDepth()
            GL11.GL_BLEND -> GlStateManager.enableBlend()
            GL11.GL_FOG -> GlStateManager.enableFog()
            GL11.GL_CULL_FACE -> GlStateManager.enableCull()
            GL11.GL_POLYGON_OFFSET_FILL -> GlStateManager.enablePolygonOffset()
            GL11.GL_COLOR_LOGIC_OP -> GlStateManager.enableColorLogic()
            GL11.GL_TEXTURE_2D -> GlStateManager.enableTexture2D()
            GL11.GL_NORMALIZE -> GlStateManager.enableNormalize()
            GL12.GL_RESCALE_NORMAL -> GlStateManager.enableRescaleNormal()
            else -> GL11.glEnable(target)
        }
    }

    override fun glDisable(target: Int) {
        when (target) {
            GL11.GL_ALPHA_TEST -> GlStateManager.disableAlpha()
            GL11.GL_LIGHTING -> GlStateManager.disableLighting()
            GL11.GL_COLOR_MATERIAL -> GlStateManager.disableColorMaterial()
            GL11.GL_DEPTH_TEST -> GlStateManager.disableDepth()
            GL11.GL_BLEND -> GlStateManager.disableBlend()
            GL11.GL_FOG -> GlStateManager.disableFog()
            GL11.GL_CULL_FACE -> GlStateManager.disableCull()
            GL11.GL_POLYGON_OFFSET_FILL -> GlStateManager.disablePolygonOffset()
            GL11.GL_COLOR_LOGIC_OP -> GlStateManager.disableColorLogic()
            GL11.GL_TEXTURE_2D -> GlStateManager.disableTexture2D()
            GL11.GL_NORMALIZE -> GlStateManager.disableNormalize()
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

    override fun glCullFace(mode: Int) {
        val modeEnum = when (mode) {
            GL11.GL_FRONT -> GlStateManager.CullFace.FRONT
            GL11.GL_BACK -> GlStateManager.CullFace.BACK
            GL11.GL_FRONT_AND_BACK -> GlStateManager.CullFace.FRONT_AND_BACK
            else -> throw IllegalArgumentException("Invalid mode: $mode")
        }

        GlStateManager.cullFace(modeEnum)
    }

    override fun glDepthFunc(func: Int) {
        GlStateManager.depthFunc(func)
    }

    override fun glDepthMask(flag: Boolean) {
        GlStateManager.depthMask(flag)
    }
}