@file:Suppress("DEPRECATION")

package dev.luna5ama.glwrapper.base

import dev.luna5ama.kmogus.Arr
import dev.luna5ama.kmogus.Ptr
import org.lwjgl.PointerWrapperAbstract
import org.lwjgl.opengl.GL32
import org.lwjgl.opengl.GLSync
import java.lang.invoke.MethodType

open class GL32LWJGL2(override val tempArr: Arr) : IGL32 {
    override fun glDrawElementsBaseVertex(mode: Int, count: Int, type: Int, indices: Long, basevertex: Int) {
        GL32.glDrawElementsBaseVertex(mode, count, type, indices, basevertex)
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
        GL32.glDrawRangeElementsBaseVertex(mode, start, end, count, type, indices, basevertex)
    }

    override fun glDrawElementsInstancedBaseVertex(
        mode: Int,
        count: Int,
        type: Int,
        indices: Long,
        instancecount: Int,
        basevertex: Int
    ) {
        GL32.glDrawElementsInstancedBaseVertex(mode, count, type, indices, instancecount, basevertex)
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
        GL32.glProvokingVertex(mode)
    }

    override fun glSampleMaski(maskNumber: Int, mask: Int) {
        GL32.glSampleMaski(maskNumber, mask)
    }

    private fun createGLSync(): GLSync {
        return unsafe.allocateInstance(GLSync::class.java) as GLSync
    }

    private val pointerFieldOffset =
        unsafe.objectFieldOffset(PointerWrapperAbstract::class.java.getDeclaredField("pointer"))

    private fun GLSync.setPointerField(pointer: Long) {
        unsafe.putLong(this, pointerFieldOffset, pointer)
    }

    private val glFenceSync = getFunctionAddress("glFenceSync")
    private val nglFenceSync = trustedLookUp.findStatic(
        GL32::class.java,
        "nglFenceSync",
        MethodType.methodType(
            Long::class.java,
            Int::class.java,
            Int::class.java,
            Long::class.java,
        )
    )

    override fun glFenceSync(condition: Int, flags: Int): Long {
        return nglFenceSync.invokeExact(condition, flags, glFenceSync) as Long
    }

    private val glIsSync = createGLSync()

    override fun glIsSync(sync: Long): Boolean {
        glIsSync.setPointerField(sync)
        return GL32.glIsSync(glIsSync)
    }

    private val glDeleteSync = createGLSync()

    override fun glDeleteSync(sync: Long) {
        glDeleteSync.setPointerField(sync)
        GL32.glDeleteSync(glDeleteSync)
    }

    private val glClientWaitSync = createGLSync()

    override fun glClientWaitSync(sync: Long, flags: Int, timeout: Long): Int {
        glClientWaitSync.setPointerField(sync)
        return GL32.glClientWaitSync(glClientWaitSync, flags, timeout)
    }

    private val glWaitSync = createGLSync()

    override fun glWaitSync(sync: Long, flags: Int, timeout: Long) {
        glWaitSync.setPointerField(sync)
        GL32.glWaitSync(glWaitSync, flags, timeout)
    }

    private val glGetSynciv = createGLSync()

    override fun glGetSynci(sync: Long, pname: Int): Int {
        glGetSynciv.setPointerField(sync)
        return GL32.glGetSynci(glGetSynciv, pname)
    }

    override fun glMultiDrawElementsBaseVertex(
        mode: Int,
        count: Ptr,
        type: Int,
        indices: Ptr,
        drawcount: Int,
        basevertex: Ptr
    ) {
        throw UnsupportedOperationException("glMultiDrawElementsBaseVertex is not supported in LWJGL 2")
    }
}