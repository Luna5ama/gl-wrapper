package dev.luna5ama.glwrapper.lwjgl2

import dev.luna5ama.glwrapper.IGL42
import dev.luna5ama.kmogus.Arr
import org.lwjgl.opengl.GL42

open class GL42LWJGL2(override val tempArr: Arr) : IGL42 {
    override fun glDrawArraysInstancedBaseInstance(
        mode: Int,
        first: Int,
        count: Int,
        instancecount: Int,
        baseinstance: Int
    ) {
        GL42.glDrawArraysInstancedBaseInstance(mode, first, count, instancecount, baseinstance)
    }

    override fun glDrawElementsInstancedBaseInstance(
        mode: Int,
        count: Int,
        type: Int,
        indices: Long,
        instancecount: Int,
        baseinstance: Int
    ) {
        GL42.glDrawElementsInstancedBaseInstance(mode, count, type, indices, instancecount, baseinstance)
    }

    override fun glDrawElementsInstancedBaseVertexBaseInstance(
        mode: Int,
        count: Int,
        type: Int,
        indices: Long,
        instancecount: Int,
        basevertex: Int,
        baseinstance: Int
    ) {
        GL42.glDrawElementsInstancedBaseVertexBaseInstance(
            mode,
            count,
            type,
            indices,
            instancecount,
            basevertex,
            baseinstance
        )
    }

    override fun glBindImageTexture(
        unit: Int,
        texture: Int,
        level: Int,
        layered: Boolean,
        layer: Int,
        access: Int,
        format: Int
    ) {
        GL42.glBindImageTexture(unit, texture, level, layered, layer, access, format)
    }

    override fun glMemoryBarrier(barriers: Int) {
        GL42.glMemoryBarrier(barriers)
    }
}
