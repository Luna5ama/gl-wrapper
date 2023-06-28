package dev.luna5ama.glwrapper.api

import dev.luna5ama.kmogus.Arr
import org.lwjgl.opengl.GL31C

open class GL31LWJGL3(override val tempArr: Arr) : IGL31 {
    override fun glDrawArraysInstanced(mode: Int, first: Int, count: Int, instancecount: Int) {
        GL31C.glDrawArraysInstanced(mode, first, count, instancecount)
    }

    override fun glDrawElementsInstanced(mode: Int, count: Int, type: Int, indices: Long, instancecount: Int) {
        GL31C.glDrawElementsInstanced(mode, count, type, indices, instancecount)
    }

    override fun glPrimitiveRestartIndex(index: Int) {
        GL31C.glPrimitiveRestartIndex(index)
    }

    override fun glUniformBlockBinding(program: Int, uniformBlockIndex: Int, uniformBlockBinding: Int) {
        GL31C.glUniformBlockBinding(program, uniformBlockIndex, uniformBlockBinding)
    }
}