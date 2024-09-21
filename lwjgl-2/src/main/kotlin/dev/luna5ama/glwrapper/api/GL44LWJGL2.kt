package dev.luna5ama.glwrapper.base

import dev.luna5ama.kmogus.*
import org.lwjgl.PointerBuffer
import org.lwjgl.opengl.GL44

open class GL44LWJGL2(override val tempArr: Arr) : IGL44 {
    private val glClearTexSubImage = nullByteBuffer()

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
        glClearTexSubImage(
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
            Ptr(data)
        )
    }

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
        data: Ptr
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
            data.asByteBuffer(8, glClearTexSubImage)
        )
    }

    private val glClearTexImage = nullByteBuffer()

    override fun glClearTexImage(texture: Int, level: Int, format: Int, type: Int, data: Long) {
        glClearTexImage(texture, level, format, type, Ptr(data))
    }

    override fun glClearTexImage(texture: Int, level: Int, format: Int, type: Int, data: Ptr) {
        GL44.glClearTexImage(texture, level, format, type, data.asByteBuffer(8, glClearTexImage))
    }

    private val glBindBuffersBase = nullIntBuffer()

    override fun glBindBuffersBase(target: Int, first: Int, count: Int, buffers: Long) {
        glBindBuffersBase(target, first, count, Ptr(buffers))
    }

    override fun glBindBuffersBase(target: Int, first: Int, count: Int, buffers: Ptr) {
        GL44.glBindBuffersBase(target, first, count, buffers.asIntBuffer(4, glBindBuffersBase))
    }

    private val bindbuffersrangeBuffers = nullIntBuffer()
    private val glbindbuffersrangeOffsets = PointerBuffer(nullByteBuffer())
    private val glbindbuffersrangeSizes = PointerBuffer(nullByteBuffer())

    override fun glBindBuffersRange(target: Int, first: Int, count: Int, buffers: Long, offsets: Long, sizes: Long) {
        glBindBuffersRange(
            target,
            first,
            count,
            Ptr(buffers),
            Ptr(offsets),
            Ptr(sizes)
        )
    }

    override fun glBindBuffersRange(target: Int, first: Int, count: Int, buffers: Ptr, offsets: Ptr, sizes: Ptr) {
        GL44.glBindBuffersRange(
            target,
            first,
            count,
            buffers.asIntBuffer(count, bindbuffersrangeBuffers),
            offsets.asPointerBuffer(count, glbindbuffersrangeOffsets),
            sizes.asPointerBuffer(count, glbindbuffersrangeSizes)
        )
    }

    private val glBindTextures = nullIntBuffer()

    override fun glBindTextures(first: Int, count: Int, textures: Long) {
        glBindTextures(first, count, Ptr(textures))
    }

    override fun glBindTextures(first: Int, count: Int, textures: Ptr) {
        GL44.glBindTextures(first, count, textures.asIntBuffer(4, glBindTextures))
    }

    private val glBindSamplers = nullIntBuffer()

    override fun glBindSamplers(first: Int, count: Int, samplers: Long) {
        glBindSamplers(first, count, Ptr(samplers))
    }

    override fun glBindSamplers(first: Int, count: Int, samplers: Ptr) {
        GL44.glBindSamplers(first, count, samplers.asIntBuffer(4, glBindSamplers))
    }

    private val glBindImageTextures = nullIntBuffer()

    override fun glBindImageTextures(first: Int, count: Int, textures: Long) {
        glBindImageTextures(first, count, Ptr(textures))
    }

    override fun glBindImageTextures(first: Int, count: Int, textures: Ptr) {
        GL44.glBindImageTextures(first, count, textures.asIntBuffer(4, glBindImageTextures))
    }
}
