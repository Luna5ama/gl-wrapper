@file:JvmName("GL46")

package dev.luna5ama.glwrapper.api

interface IGL46 : GLBase {
    companion object {
        internal const val GL_PARAMETER_BUFFER = 0x80EE

        internal const val GL_PARAMETER_BUFFER_BINDING = 0x80EF

        internal const val GL_VERTICES_SUBMITTED = 0x82EE
        internal const val GL_PRIMITIVES_SUBMITTED = 0x82EF
        internal const val GL_VERTEX_SHADER_INVOCATIONS = 0x82F0
        internal const val GL_TESS_CONTROL_SHADER_PATCHES = 0x82F1
        internal const val GL_TESS_EVALUATION_SHADER_INVOCATIONS = 0x82F2
        internal const val GL_GEOMETRY_SHADER_PRIMITIVES_EMITTED = 0x82F3
        internal const val GL_FRAGMENT_SHADER_INVOCATIONS = 0x82F4
        internal const val GL_COMPUTE_SHADER_INVOCATIONS = 0x82F5
        internal const val GL_CLIPPING_INPUT_PRIMITIVES = 0x82F6
        internal const val GL_CLIPPING_OUTPUT_PRIMITIVES = 0x82F7

        internal const val GL_POLYGON_OFFSET_CLAMP = 0x8E1B

        internal const val GL_CONTEXT_FLAG_NO_ERROR_BIT = 0x8

        internal const val GL_SHADER_BINARY_FORMAT_SPIR_V = 0x9551

        internal const val GL_SPIR_V_BINARY = 0x9552

        internal const val GL_SPIR_V_EXTENSIONS = 0x9553

        internal const val GL_NUM_SPIR_V_EXTENSIONS = 0x9554

        internal const val GL_TEXTURE_MAX_ANISOTROPY = 0x84FE

        internal const val GL_MAX_TEXTURE_MAX_ANISOTROPY = 0x84FF

        internal const val GL_TRANSFORM_FEEDBACK_OVERFLOW = 0x82EC
        internal const val GL_TRANSFORM_FEEDBACK_STREAM_OVERFLOW = 0x82ED
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

abstract class PatchedGL46(protected val delegate: IGL46) : IGL46 by delegate