package dev.luna5ama.glwrapper.base

import com.mojang.blaze3d.platform.GlStateManager

class GL14Mc(private val delegate: GL14) : GL14 by delegate {
    override fun glBlendEquation(mode: Int) {
        GlStateManager.blendEquation(mode)
    }

    override fun glBlendFuncSeparate(sfactorRGB: Int, dfactorRGB: Int, sfactorAlpha: Int, dfactorAlpha: Int) {
        GlStateManager.blendFuncSeparate(sfactorRGB, dfactorRGB, sfactorAlpha, dfactorAlpha)
    }
}