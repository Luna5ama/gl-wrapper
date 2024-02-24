package dev.luna5ama.glwrapper.api


class GL45Mc(private val delegate: GL45) : GL45 by delegate {
    override fun glBindTextureUnit(unit: Int, texture: Int) {
        setTextureUnit(unit, texture)
        delegate.glBindTextureUnit(unit, texture)
    }
}