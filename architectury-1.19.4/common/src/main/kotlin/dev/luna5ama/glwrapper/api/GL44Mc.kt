package dev.luna5ama.glwrapper.api

import dev.luna5ama.kmogus.Ptr

class GL44Mc(private val delegate: GL44) : GL44 by delegate {
    override fun glBindTextures(first: Int, count: Int, textures: Long) {
        glBindTextures(first, count, Ptr(textures))
    }

    override fun glBindTextures(first: Int, count: Int, textures: Ptr) {
        for (i in 0 until count) {
            setTextureUnit(i + first, textures.getInt(i * 4L))
        }

        delegate.glBindTextures(first, count, textures)
    }
}