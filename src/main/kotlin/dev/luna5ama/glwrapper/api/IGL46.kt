@file:JvmName("GL46")

package dev.luna5ama.glwrapper.api

interface IGL46 : GLBase {
    companion object {
        const val GL_PARAMETER_BUFFER = 0x80EE

        const val GL_PARAMETER_BUFFER_BINDING = 0x80EF

        const val GL_VERTICES_SUBMITTED = 0x82EE
        const val GL_PRIMITIVES_SUBMITTED = 0x82EF
        const val GL_VERTEX_SHADER_INVOCATIONS = 0x82F0
        const val GL_TESS_CONTROL_SHADER_PATCHES = 0x82F1
        const val GL_TESS_EVALUATION_SHADER_INVOCATIONS = 0x82F2
        const val GL_GEOMETRY_SHADER_PRIMITIVES_EMITTED = 0x82F3
        const val GL_FRAGMENT_SHADER_INVOCATIONS = 0x82F4
        const val GL_COMPUTE_SHADER_INVOCATIONS = 0x82F5
        const val GL_CLIPPING_INPUT_PRIMITIVES = 0x82F6
        const val GL_CLIPPING_OUTPUT_PRIMITIVES = 0x82F7

        const val GL_POLYGON_OFFSET_CLAMP = 0x8E1B

        const val GL_CONTEXT_FLAG_NO_ERROR_BIT = 0x8

        const val GL_SHADER_BINARY_FORMAT_SPIR_V = 0x9551

        const val GL_SPIR_V_BINARY = 0x9552

        const val GL_SPIR_V_EXTENSIONS = 0x9553

        const val GL_NUM_SPIR_V_EXTENSIONS = 0x9554

        const val GL_TEXTURE_MAX_ANISOTROPY = 0x84FE

        const val GL_MAX_TEXTURE_MAX_ANISOTROPY = 0x84FF

        const val GL_TRANSFORM_FEEDBACK_OVERFLOW = 0x82EC
        const val GL_TRANSFORM_FEEDBACK_STREAM_OVERFLOW = 0x82ED
    }

    fun glMultiDrawArraysIndirectCount(mode: Int, indirect: Long, drawcount: Long, maxdrawcount: Int, stride: Int)
    fun glMultiDrawElementsIndirectCount(
        mode: Int,
        type: Int,
        indirect: Long,
        drawcount: Long,
        maxdrawcount: Int,
        stride: Int
    )
}