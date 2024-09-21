package dev.luna5ama.glwrapper.base

import dev.luna5ama.kmogus.Arr
import org.lwjgl.opengl.GL45

open class GL45Impl(tempArr: Arr) : GL45LWJGL2(tempArr) {
    override fun glBindTextureUnit(unit: Int, texture: Int) {
        setTextureUnit(unit, texture)
        GL45.glBindTextureUnit(unit, texture)
    }
}