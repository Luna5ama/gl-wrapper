package dev.luna5ama.glwrapper.forge112

import dev.luna5ama.glwrapper.lwjgl2.GL14LWJGL2
import dev.luna5ama.kmogus.Arr
import net.minecraft.client.renderer.GlStateManager
import org.lwjgl.opengl.GL14

open class GL14Impl(tempArr: Arr) : GL14LWJGL2(tempArr) {
    override fun glBlendEquation(mode: Int) {
        GL14.glBlendEquation(mode)
    }

    override fun glBlendFuncSeparate(sfactorRGB: Int, dfactorRGB: Int, sfactorAlpha: Int, dfactorAlpha: Int) {
        GlStateManager.tryBlendFuncSeparate(sfactorRGB, dfactorRGB, sfactorAlpha, dfactorAlpha)
    }
}