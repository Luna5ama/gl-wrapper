package dev.luna5ama.glwrapper.base

import dev.luna5ama.kmogus.Arr
import dev.luna5ama.kmogus.Ptr
import dev.luna5ama.kmogus.asIntBuffer
import dev.luna5ama.kmogus.nullIntBuffer
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

    override fun glGetActiveAtomicCounterBufferi(program: Int, bufferIndex: Int, pname: Int): Int {
        return GL42.glGetActiveAtomicCounterBuffer(program, bufferIndex, pname)
    }

    private val glGetActiveAtomicCounterBufferiv = nullIntBuffer()

    override fun glGetActiveAtomicCounterBufferiv(program: Int, bufferIndex: Int, pname: Int, params: Ptr) {
        GL42.glGetActiveAtomicCounterBuffer(
            program,
            bufferIndex,
            pname,
            params.asIntBuffer(4, glGetActiveAtomicCounterBufferiv)
        )
    }
}
