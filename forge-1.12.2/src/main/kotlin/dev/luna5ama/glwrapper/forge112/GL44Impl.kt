package dev.luna5ama.glwrapper.forge112

import dev.luna5ama.glwrapper.lwjgl2.GL44LWJGL2
import dev.luna5ama.kmogus.Arr
import dev.luna5ama.kmogus.Ptr
import dev.luna5ama.kmogus.ensureCapacity
import dev.luna5ama.kmogus.memcpy
import org.lwjgl.opengl.GL44

open class GL44Impl(tempArr: Arr) : GL44LWJGL2(tempArr) {
    private val glBindTextures = createBuffer().asIntBuffer()

    override fun glBindTextures(first: Int, count: Int, textures: Long) {
        glBindTextures(first, count, Ptr(textures))
    }

    override fun glBindTextures(first: Int, count: Int, textures: Ptr) {
        for (i in 0 until count) {
            setTextureUnit(i + first, textures.getInt(i * 4L))
        }

        GL44.glBindTextures(first, count, wrapBuffer(glBindTextures, textures.address, count))
    }

    override fun glBindTextures(first: Int, vararg textures: Int) {
        for (i in textures.indices) {
            setTextureUnit(i + first, textures[i])
        }

        val length = textures.size * 4L
        tempArr.ensureCapacity(length, true)
        val ptr = tempArr.ptr
        memcpy(textures, ptr, length)
        GL44.glBindTextures(first, textures.size, wrapBuffer(glBindTextures, ptr.address, textures.size))
    }

    override fun glBindTextures(first: Int, textures: IntArray, offset: Int, length: Int) {
        for (i in 0 until length) {
            setTextureUnit(i + first, textures[i + offset])
        }

        val byteLength = length * 4L
        tempArr.ensureCapacity(byteLength, true)
        val ptr = tempArr.ptr
        memcpy(textures, offset * 4L, ptr, 0L, byteLength)
        GL44.glBindTextures(first, length, wrapBuffer(glBindTextures, ptr.address, length))
    }
}