package dev.luna5ama.glwrapper.api

import com.mojang.blaze3d.platform.GlStateManager
import dev.luna5ama.kmogus.Arr

open class GL14Impl(tempArr: Arr) : GL14LWJGL3(tempArr) {
    override fun glBlendEquation(mode: Int) {
        GlStateManager.blendEquation(mode)
    }

    override fun glBlendFuncSeparate(sfactorRGB: Int, dfactorRGB: Int, sfactorAlpha: Int, dfactorAlpha: Int) {
        GlStateManager.blendFuncSeparate(sfactorRGB, dfactorRGB, sfactorAlpha, dfactorAlpha)
    }
}