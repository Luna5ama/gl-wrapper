package dev.luna5ama.glwrapper.api

import dev.luna5ama.kmogus.Arr
import org.lwjgl.opengl.GL44C

open class GL44LWJGL3(override val tempArr: Arr) : IGL44 {
    override fun glClearTexSubImage(
        texture: Int,
        level: Int,
        xOffset: Int,
        yOffset: Int,
        zOffset: Int,
        width: Int,
        height: Int,
        depth: Int,
        format: Int,
        type: Int,
        data: Long
    ) {
        GL44C.nglClearTexSubImage(
            texture,
            level,
            xOffset,
            yOffset,
            zOffset,
            width,
            height,
            depth,
            format,
            type,
            data
        )
    }

    override fun glClearTexImage(texture: Int, level: Int, format: Int, type: Int, data: Long) {
        GL44C.nglClearTexImage(texture, level, format, type, data)
    }

    override fun glBindBuffersBase(target: Int, first: Int, count: Int, buffers: Long) {
        GL44C.nglBindBuffersBase(target, first, count, buffers)
    }

    override fun glBindBuffersRange(target: Int, first: Int, count: Int, buffers: Long, offsets: Long, sizes: Long) {
        GL44C.nglBindBuffersRange(target, first, count, buffers, offsets, sizes)
    }

    override fun glBindTextures(first: Int, count: Int, textures: Long) {
        GL44C.nglBindTextures(first, count, textures)
    }

    override fun glBindSamplers(first: Int, count: Int, samplers: Long) {
        GL44C.nglBindSamplers(first, count, samplers)
    }

    override fun glBindImageTextures(first: Int, count: Int, textures: Long) {
        GL44C.nglBindImageTextures(first, count, textures)
    }
}
