package dev.luna5ama.glwrapper.api

import dev.luna5ama.kmogus.Arr
import org.lwjgl.opengl.GL43C

open class GL43LWJGL3(override val tempArr: Arr) : IGL43 {
    override fun glObjectLabel(identifier: Int, name: Int, label: String) {
        GL43C.glObjectLabel(identifier, name, label)
    }

    override fun glDispatchCompute(num_groups_x: Int, num_groups_y: Int, num_groups_z: Int) {
        GL43C.glDispatchCompute(num_groups_x, num_groups_y, num_groups_z)
    }

    override fun glDispatchComputeIndirect(indirect: Long) {
        GL43C.glDispatchComputeIndirect(indirect)
    }

    override fun glCopyImageSubData(
        srcName: Int,
        srcTarget: Int,
        srcLevel: Int,
        srcX: Int,
        srcY: Int,
        srcZ: Int,
        dstName: Int,
        dstTarget: Int,
        dstLevel: Int,
        dstX: Int,
        dstY: Int,
        dstZ: Int,
        srcWidth: Int,
        srcHeight: Int,
        srcDepth: Int
    ) {
        GL43C.glCopyImageSubData(
            srcName,
            srcTarget,
            srcLevel,
            srcX,
            srcY,
            srcZ,
            dstName,
            dstTarget,
            dstLevel,
            dstX,
            dstY,
            dstZ,
            srcWidth,
            srcHeight,
            srcDepth
        )
    }

    override fun glInvalidateTexSubImage(
        texture: Int,
        level: Int,
        xoffset: Int,
        yoffset: Int,
        zoffset: Int,
        width: Int,
        height: Int,
        depth: Int
    ) {
        GL43C.glInvalidateTexSubImage(texture, level, xoffset, yoffset, zoffset, width, height, depth)
    }

    override fun glInvalidateTexImage(texture: Int, level: Int) {
        GL43C.glInvalidateTexImage(texture, level)
    }

    override fun glInvalidateBufferSubData(buffer: Int, offset: Long, length: Long) {
        GL43C.glInvalidateBufferSubData(buffer, offset, length)
    }

    override fun glInvalidateBufferData(buffer: Int) {
        GL43C.glInvalidateBufferData(buffer)
    }

    override fun glMultiDrawArraysIndirect(mode: Int, indirect: Long, drawcount: Int, stride: Int) {
        GL43C.glMultiDrawArraysIndirect(mode, indirect, drawcount, stride)
    }

    override fun glMultiDrawElementsIndirect(mode: Int, type: Int, indirect: Long, drawcount: Int, stride: Int) {
        GL43C.glMultiDrawElementsIndirect(mode, type, indirect, drawcount, stride)
    }

    override fun glShaderStorageBlockBinding(program: Int, storageBlockIndex: Int, storageBlockBinding: Int) {
        GL43C.glShaderStorageBlockBinding(program, storageBlockIndex, storageBlockBinding)
    }

    override fun glGetProgramResourceIndex(program: Int, programInterface: Int, name: String): Int {
        return GL43C.glGetProgramResourceIndex(program, programInterface, name)
    }
}
