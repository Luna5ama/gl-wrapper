package dev.luna5ama.glwrapper.api

import dev.luna5ama.kmogus.Arr
import dev.luna5ama.kmogus.Ptr
import org.lwjgl.opengl.GL32C

open class GL32LWJGL3(override val tempArr: Arr) : IGL32 {
    override fun glDrawElementsBaseVertex(mode: Int, count: Int, type: Int, indices: Long, basevertex: Int) {
        GL32C.glDrawElementsBaseVertex(mode, count, type, indices, basevertex)
    }

    override fun glDrawRangeElementsBaseVertex(
        mode: Int,
        start: Int,
        end: Int,
        count: Int,
        type: Int,
        indices: Long,
        basevertex: Int
    ) {
        GL32C.glDrawRangeElementsBaseVertex(mode, start, end, count, type, indices, basevertex)
    }

    override fun glDrawElementsInstancedBaseVertex(
        mode: Int,
        count: Int,
        type: Int,
        indices: Long,
        instancecount: Int,
        basevertex: Int
    ) {
        GL32C.glDrawElementsInstancedBaseVertex(mode, count, type, indices, instancecount, basevertex)
    }

    override fun glMultiDrawElementsBaseVertex(
        mode: Int,
        count: Long,
        type: Int,
        indices: Long,
        drawcount: Int,
        basevertex: Long
    ) {
        throw UnsupportedOperationException("glMultiDrawElementsBaseVertex is not supported in LWJGL 2")
    }

    override fun glProvokingVertex(mode: Int) {
        GL32C.glProvokingVertex(mode)
    }

    override fun glSampleMaski(maskNumber: Int, mask: Int) {
        GL32C.glSampleMaski(maskNumber, mask)
    }

    override fun glFenceSync(condition: Int, flags: Int): Long {
        return GL32C.glFenceSync(condition, flags)
    }

    override fun glIsSync(sync: Long): Boolean {
        return GL32C.glIsSync(sync)
    }

    override fun glDeleteSync(sync: Long) {
        GL32C.glDeleteSync(sync)
    }

    override fun glClientWaitSync(sync: Long, flags: Int, timeout: Long): Int {
        return GL32C.glClientWaitSync(sync, flags, timeout)
    }

    override fun glWaitSync(sync: Long, flags: Int, timeout: Long) {
        GL32C.glWaitSync(sync, flags, timeout)
    }

    private val glGetSynci_length = Arr.malloc(4).apply {
        ptr.setInt(1)
    }

    override fun glGetSynci(sync: Long, pname: Int): Int {
        val ptr = tempArr.ptr
        GL32C.nglGetSynciv(sync, pname, 1, glGetSynci_length.ptr.address, ptr.address)
        return ptr.getInt()
    }

    override fun glMultiDrawElementsBaseVertex(
        mode: Int,
        count: Ptr,
        type: Int,
        indices: Ptr,
        drawcount: Int,
        basevertex: Ptr
    ) {
        GL32C.nglMultiDrawElementsBaseVertex(mode, count.address, type, indices.address, drawcount, basevertex.address)
    }
}