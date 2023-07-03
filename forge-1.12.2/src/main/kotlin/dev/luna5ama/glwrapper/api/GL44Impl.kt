package dev.luna5ama.glwrapper.api

import dev.luna5ama.kmogus.*
import org.lwjgl.opengl.GL44

open class GL44Impl(tempArr: Arr) : GL44LWJGL2(tempArr) {
    private val glBindTextures = nullIntBuffer()

    override fun glBindTextures(first: Int, count: Int, textures: Long) {
        glBindTextures(first, count, Ptr(textures))
    }

    override fun glBindTextures(first: Int, count: Int, textures: Ptr) {
        for (i in 0 until count) {
            setTextureUnit(i + first, textures.getInt(i * 4L))
        }

        GL44.glBindTextures(first, count, textures.asIntBuffer(count, glBindTextures))
    }

    override fun glBindTextures(first: Int, vararg textures: Int) {
        for (i in textures.indices) {
            setTextureUnit(i + first, textures[i])
        }

        val length = textures.size * 4L
        tempArr.ensureCapacity(length, true)
        val ptr = tempArr.ptr
        memcpy(textures, ptr, length)
        GL44.glBindTextures(first, textures.size, ptr.asIntBuffer(textures.size, glBindTextures))
    }

    override fun glBindTextures(first: Int, textures: IntArray, offset: Int, length: Int) {
        for (i in 0 until length) {
            setTextureUnit(i + first, textures[i + offset])
        }

        val byteLength = length * 4L
        tempArr.ensureCapacity(byteLength, true)
        val ptr = tempArr.ptr
        memcpy(textures, offset * 4L, ptr, 0L, byteLength)
        GL44.glBindTextures(first, length, ptr.asIntBuffer(length, glBindTextures))
    }
}