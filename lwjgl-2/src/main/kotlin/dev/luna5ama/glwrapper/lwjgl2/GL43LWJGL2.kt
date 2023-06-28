package dev.luna5ama.glwrapper.lwjgl2

import dev.luna5ama.glwrapper.IGL43
import dev.luna5ama.kmogus.Arr
import org.lwjgl.opengl.GL43

open class GL43LWJGL2(override val tempArr: Arr) : IGL43 {
    override fun glDispatchCompute(num_groups_x: Int, num_groups_y: Int, num_groups_z: Int) {
        GL43.glDispatchCompute(num_groups_x, num_groups_y, num_groups_z)
    }

    override fun glDispatchComputeIndirect(indirect: Long) {
        GL43.glDispatchComputeIndirect(indirect)
    }

    override fun glInvalidateTextSubImage(
        texture: Int,
        level: Int,
        xoffset: Int,
        yoffset: Int,
        zoffset: Int,
        width: Int,
        height: Int,
        depth: Int
    ) {
        GL43.glInvalidateTexSubImage(texture, level, xoffset, yoffset, zoffset, width, height, depth)
    }

    override fun glInvalidateTexImage(texture: Int, level: Int) {
        GL43.glInvalidateTexImage(texture, level)
    }

    override fun glInvalidateBufferSubData(buffer: Int, offset: Long, length: Long) {
        GL43.glInvalidateBufferSubData(buffer, offset, length)
    }

    override fun glInvalidateBufferData(buffer: Int) {
        GL43.glInvalidateBufferData(buffer)
    }

    override fun glMultiDrawArraysIndirect(mode: Int, indirect: Long, drawcount: Int, stride: Int) {
        GL43.glMultiDrawArraysIndirect(mode, indirect, drawcount, stride)
    }

    override fun glMultiDrawElementsIndirect(mode: Int, type: Int, indirect: Long, drawcount: Int, stride: Int) {
        GL43.glMultiDrawElementsIndirect(mode, type, indirect, drawcount, stride)
    }

    override fun glShaderStorageBlockBinding(program: Int, storageBlockIndex: Int, storageBlockBinding: Int) {
        GL43.glShaderStorageBlockBinding(program, storageBlockIndex, storageBlockBinding)
    }

    override fun glGetProgramResourceIndex(program: Int, programInterface: Int, name: String): Int {
        return GL43.glGetProgramResourceIndex(program, programInterface, name)
    }
}
