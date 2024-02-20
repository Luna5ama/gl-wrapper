@file:JvmName("GL40")

package dev.luna5ama.glwrapper.api

import dev.luna5ama.kmogus.Ptr

interface IGL40 : GLBase {
    companion object {
        internal const val GL_DRAW_INDIRECT_BUFFER = 0x8F3F

        internal const val GL_DRAW_INDIRECT_BUFFER_BINDING = 0x8F43

        internal const val GL_GEOMETRY_SHADER_INVOCATIONS = 0x887F

        internal const val GL_MAX_GEOMETRY_SHADER_INVOCATIONS = 0x8E5A
        internal const val GL_MIN_FRAGMENT_INTERPOLATION_OFFSET = 0x8E5B
        internal const val GL_MAX_FRAGMENT_INTERPOLATION_OFFSET = 0x8E5C
        internal const val GL_FRAGMENT_INTERPOLATION_OFFSET_BITS = 0x8E5D

        internal const val GL_DOUBLE_VEC2 = 0x8FFC
        internal const val GL_DOUBLE_VEC3 = 0x8FFD
        internal const val GL_DOUBLE_VEC4 = 0x8FFE
        internal const val GL_DOUBLE_MAT2 = 0x8F46
        internal const val GL_DOUBLE_MAT3 = 0x8F47
        internal const val GL_DOUBLE_MAT4 = 0x8F48
        internal const val GL_DOUBLE_MAT2x3 = 0x8F49
        internal const val GL_DOUBLE_MAT2x4 = 0x8F4A
        internal const val GL_DOUBLE_MAT3x2 = 0x8F4B
        internal const val GL_DOUBLE_MAT3x4 = 0x8F4C
        internal const val GL_DOUBLE_MAT4x2 = 0x8F4D
        internal const val GL_DOUBLE_MAT4x3 = 0x8F4E

        internal const val GL_SAMPLE_SHADING = 0x8C36

        internal const val GL_MIN_SAMPLE_SHADING_VALUE = 0x8C37

        internal const val GL_ACTIVE_SUBROUTINES = 0x8DE5
        internal const val GL_ACTIVE_SUBROUTINE_UNIFORMS = 0x8DE6
        internal const val GL_ACTIVE_SUBROUTINE_UNIFORM_LOCATIONS = 0x8E47
        internal const val GL_ACTIVE_SUBROUTINE_MAX_LENGTH = 0x8E48
        internal const val GL_ACTIVE_SUBROUTINE_UNIFORM_MAX_LENGTH = 0x8E49

        internal const val GL_MAX_SUBROUTINES = 0x8DE7
        internal const val GL_MAX_SUBROUTINE_UNIFORM_LOCATIONS = 0x8DE8

        internal const val GL_NUM_COMPATIBLE_SUBROUTINES = 0x8E4A
        internal const val GL_COMPATIBLE_SUBROUTINES = 0x8E4B

        internal const val GL_PATCHES = 0xE

        internal const val GL_PATCH_VERTICES = 0x8E72

        internal const val GL_PATCH_DEFAULT_INNER_LEVEL = 0x8E73
        internal const val GL_PATCH_DEFAULT_OUTER_LEVEL = 0x8E74

        internal const val GL_TESS_CONTROL_OUTPUT_VERTICES = 0x8E75
        internal const val GL_TESS_GEN_MODE = 0x8E76
        internal const val GL_TESS_GEN_SPACING = 0x8E77
        internal const val GL_TESS_GEN_VERTEX_ORDER = 0x8E78
        internal const val GL_TESS_GEN_POINT_MODE = 0x8E79

        internal const val GL_ISOLINES = 0x8E7A

        internal const val GL_FRACTIONAL_ODD = 0x8E7B
        internal const val GL_FRACTIONAL_EVEN = 0x8E7C

        internal const val GL_MAX_PATCH_VERTICES = 0x8E7D
        internal const val GL_MAX_TESS_GEN_LEVEL = 0x8E7E
        internal const val GL_MAX_TESS_CONTROL_UNIFORM_COMPONENTS = 0x8E7F
        internal const val GL_MAX_TESS_EVALUATION_UNIFORM_COMPONENTS = 0x8E80
        internal const val GL_MAX_TESS_CONTROL_TEXTURE_IMAGE_UNITS = 0x8E81
        internal const val GL_MAX_TESS_EVALUATION_TEXTURE_IMAGE_UNITS = 0x8E82
        internal const val GL_MAX_TESS_CONTROL_OUTPUT_COMPONENTS = 0x8E83
        internal const val GL_MAX_TESS_PATCH_COMPONENTS = 0x8E84
        internal const val GL_MAX_TESS_CONTROL_TOTAL_OUTPUT_COMPONENTS = 0x8E85
        internal const val GL_MAX_TESS_EVALUATION_OUTPUT_COMPONENTS = 0x8E86
        internal const val GL_MAX_TESS_CONTROL_UNIFORM_BLOCKS = 0x8E89
        internal const val GL_MAX_TESS_EVALUATION_UNIFORM_BLOCKS = 0x8E8A
        internal const val GL_MAX_TESS_CONTROL_INPUT_COMPONENTS = 0x886C
        internal const val GL_MAX_TESS_EVALUATION_INPUT_COMPONENTS = 0x886D
        internal const val GL_MAX_COMBINED_TESS_CONTROL_UNIFORM_COMPONENTS = 0x8E1E
        internal const val GL_MAX_COMBINED_TESS_EVALUATION_UNIFORM_COMPONENTS = 0x8E1F

        internal const val GL_UNIFORM_BLOCK_REFERENCED_BY_TESS_CONTROL_SHADER = 0x84F0
        internal const val GL_UNIFORM_BLOCK_REFERENCED_BY_TESS_EVALUATION_SHADER = 0x84F1

        internal const val GL_TESS_EVALUATION_SHADER = 0x8E87
        internal const val GL_TESS_CONTROL_SHADER = 0x8E88

        internal const val GL_TEXTURE_CUBE_MAP_ARRAY = 0x9009

        internal const val GL_TEXTURE_BINDING_CUBE_MAP_ARRAY = 0x900A

        internal const val GL_PROXY_TEXTURE_CUBE_MAP_ARRAY = 0x900B

        internal const val GL_SAMPLER_CUBE_MAP_ARRAY = 0x900C
        internal const val GL_SAMPLER_CUBE_MAP_ARRAY_SHADOW = 0x900D
        internal const val GL_INT_SAMPLER_CUBE_MAP_ARRAY = 0x900E
        internal const val GL_UNSIGNED_INT_SAMPLER_CUBE_MAP_ARRAY = 0x900F

        internal const val GL_MIN_PROGRAM_TEXTURE_GATHER_OFFSET = 0x8E5E
        internal const val GL_MAX_PROGRAM_TEXTURE_GATHER_OFFSET = 0x8E5F

        internal const val GL_TRANSFORM_FEEDBACK = 0x8E22

        internal const val GL_TRANSFORM_FEEDBACK_BUFFER_PAUSED = 0x8E23
        internal const val GL_TRANSFORM_FEEDBACK_BUFFER_ACTIVE = 0x8E24
        internal const val GL_TRANSFORM_FEEDBACK_BINDING = 0x8E25

        internal const val GL_MAX_TRANSFORM_FEEDBACK_BUFFERS = 0x8E70
        internal const val GL_MAX_VERTEX_STREAMS = 0x8E71
    }

    fun glPatchParameteri(pname: Int, value: Int)

    fun glBlendEquationi(buf: Int, mode: Int)
    fun glBlendEquationSeparatei(buf: Int, modeRGB: Int, modeAlpha: Int)

    fun glBlendFunci(buf: Int, src: Int, dst: Int)
    fun glBlendFuncSeparatei(buf: Int, srcRGB: Int, dstRGB: Int, srcAlpha: Int, dstAlpha: Int)

    fun glDrawArraysIndirect(mode: Int, indirect: Long)
    fun glDrawElementsIndirect(mode: Int, type: Int, indirect: Long)

    fun glMinSampleShading(value: Float)
}

abstract class PatchedGL40(protected val delegate: IGL40) : IGL40 by delegate