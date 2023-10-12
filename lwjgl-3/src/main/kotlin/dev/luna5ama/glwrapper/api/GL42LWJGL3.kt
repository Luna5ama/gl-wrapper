package dev.luna5ama.glwrapper.api

import dev.luna5ama.kmogus.Arr
import dev.luna5ama.kmogus.Ptr
import org.lwjgl.opengl.GL42
import org.lwjgl.opengl.GL42C

open class GL42LWJGL3(override val tempArr: Arr) : IGL42 {
    override fun glDrawArraysInstancedBaseInstance(
        mode: Int,
        first: Int,
        count: Int,
        instancecount: Int,
        baseinstance: Int
    ) {
        GL42C.glDrawArraysInstancedBaseInstance(mode, first, count, instancecount, baseinstance)
    }

    override fun glDrawElementsInstancedBaseInstance(
        mode: Int,
        count: Int,
        type: Int,
        indices: Long,
        instancecount: Int,
        baseinstance: Int
    ) {
        GL42C.glDrawElementsInstancedBaseInstance(mode, count, type, indices, instancecount, baseinstance)
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
        GL42C.glDrawElementsInstancedBaseVertexBaseInstance(
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
        GL42C.glBindImageTexture(unit, texture, level, layered, layer, access, format)
    }

    override fun glMemoryBarrier(barriers: Int) {
        GL42C.glMemoryBarrier(barriers)
    }

    override fun glGetActiveAtomicCounterBufferi(program: Int, bufferIndex: Int, pname: Int): Int {
        return GL42.glGetActiveAtomicCounterBufferi(program, bufferIndex, pname)
    }

    override fun glGetActiveAtomicCounterBufferiv(program: Int, bufferIndex: Int, pname: Int, params: Ptr) {
        GL42.nglGetActiveAtomicCounterBufferiv(program, bufferIndex, pname, params.address)
    }
}
