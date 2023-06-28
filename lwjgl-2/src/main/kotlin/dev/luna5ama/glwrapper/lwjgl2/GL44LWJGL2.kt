package dev.luna5ama.glwrapper.lwjgl2

import dev.luna5ama.glwrapper.IGL44
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
    private val glBindBuffersRange_offsets = createBuffer()
    private val glBindBuffersRange_offsets_ptr = PointerBuffer(glBindBuffersRange_offsets)
    private val glBindBuffersRange_sizes = createBuffer()
    private val glBindBuffersRange_sizes_ptr = PointerBuffer(glBindBuffersRange_sizes)

    override fun glBindBuffersRange(target: Int, first: Int, count: Int, buffers: Long, offsets: Long, sizes: Long) {
        wrapBuffer(glBindBuffersRange_offsets, offsets, count * 8)
        wrapBuffer(glBindBuffersRange_sizes, sizes, count * 8)

        GL44.glBindBuffersRange(
            target,
            first,
            count,
            wrapBuffer(glBindBuffersRange_buffers, buffers, count),
            glBindBuffersRange_offsets_ptr,
            glBindBuffersRange_sizes_ptr
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
