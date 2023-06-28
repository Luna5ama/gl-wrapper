package dev.luna5ama.glwrapper.api

import dev.luna5ama.kmogus.Arr
import org.lwjgl.opengl.GL31

open class GL31LWJGL2(override val tempArr: Arr) : IGL31 {
    override fun glDrawArraysInstanced(mode: Int, first: Int, count: Int, instancecount: Int) {
        GL31.glDrawArraysInstanced(mode, first, count, instancecount)
    }

    override fun glDrawElementsInstanced(mode: Int, count: Int, type: Int, indices: Long, instancecount: Int) {
        GL31.glDrawElementsInstanced(mode, count, type, indices, instancecount)
    }

    override fun glPrimitiveRestartIndex(index: Int) {
        GL31.glPrimitiveRestartIndex(index)
    }

    override fun glUniformBlockBinding(program: Int, uniformBlockIndex: Int, uniformBlockBinding: Int) {
        GL31.glUniformBlockBinding(program, uniformBlockIndex, uniformBlockBinding)
    }
}