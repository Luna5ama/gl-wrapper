package dev.luna5ama.glwrapper.api

import com.mojang.blaze3d.systems.RenderSystem

class GL13Mc(private val delegate: GL13) : GL13 by delegate {
    override fun glActiveTexture(texture: Int) {
        RenderSystem.activeTexture(texture)
    }
}