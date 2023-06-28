package dev.luna5ama.glwrapper.api

import dev.luna5ama.kmogus.Arr
import org.lwjgl.PointerBuffer
import org.lwjgl.opengl.GL44

open class GL44LWJGL2(override val tempArr: Arr) : IGL44 {
    private val glClearTexSubImage = wrapBuffer(createBuffer(), 0L, 8)

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
        GL44.glClearTexSubImage(
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
            wrapBuffer(glClearTexSubImage, data)
        )
    }

    private val glClearTexImage = wrapBuffer(createBuffer(), 0L, 8)

    override fun glClearTexImage(texture: Int, level: Int, format: Int, type: Int, data: Long) {
        GL44.glClearTexImage(texture, level, format, type, wrapBuffer(glClearTexImage, data))
    }

    private val glBindBuffersBase = createBuffer().asIntBuffer()

    override fun glBindBuffersBase(target: Int, first: Int, count: Int, buffers: Long) {
        GL44.glBindBuffersBase(target, first, count, wrapBuffer(glBindBuffersBase, buffers, count))
    }

    private val glBindBuffersRange_buffers = createBuffer().asIntBuffer()
    private val glBindBuffersRange_offsets = PointerBuffer(createBuffer())
    private val glBindBuffersRange_sizes = PointerBuffer(createBuffer())

    override fun glBindBuffersRange(target: Int, first: Int, count: Int, buffers: Long, offsets: Long, sizes: Long) {
        GL44.glBindBuffersRange(
            target,
            first,
            count,
            wrapBuffer(glBindBuffersRange_buffers, buffers, count),
            wrapBuffer(glBindBuffersRange_offsets, offsets, count),
            wrapBuffer(glBindBuffersRange_sizes, sizes, count)
        )
    }

    private val glBindTextures = createBuffer().asIntBuffer()

    override fun glBindTextures(first: Int, count: Int, textures: Long) {
        GL44.glBindTextures(first, count, wrapBuffer(glBindTextures, textures, count))
    }

    private val glBindSamplers = createBuffer().asIntBuffer()

    override fun glBindSamplers(first: Int, count: Int, samplers: Long) {
        GL44.glBindSamplers(first, count, wrapBuffer(glBindSamplers, samplers, count))
    }

    private val glBindImageTextures = createBuffer().asIntBuffer()

    override fun glBindImageTextures(first: Int, count: Int, textures: Long) {
        GL44.glBindImageTextures(first, count, wrapBuffer(glBindImageTextures, textures, count))
    }
}
