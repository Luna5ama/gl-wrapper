@file:JvmName("GL32")

package dev.luna5ama.glwrapper.api

import dev.luna5ama.kmogus.Ptr

interface IGL32 : GLBase {
    companion object {
        internal const val GL_CONTEXT_PROFILE_MASK = 0x9126

        internal const val GL_CONTEXT_CORE_PROFILE_BIT = 0x1
        internal const val GL_CONTEXT_COMPATIBILITY_PROFILE_BIT = 0x2

        internal const val GL_MAX_VERTEX_OUTPUT_COMPONENTS = 0x9122
        internal const val GL_MAX_GEOMETRY_INPUT_COMPONENTS = 0x9123
        internal const val GL_MAX_GEOMETRY_OUTPUT_COMPONENTS = 0x9124
        internal const val GL_MAX_FRAGMENT_INPUT_COMPONENTS = 0x9125

        internal const val GL_FIRST_VERTEX_CONVENTION = 0x8E4D
        internal const val GL_LAST_VERTEX_CONVENTION = 0x8E4E

        internal const val GL_PROVOKING_VERTEX = 0x8E4F
        internal const val GL_QUADS_FOLLOW_PROVOKING_VERTEX_CONVENTION = 0x8E4C

        internal const val GL_TEXTURE_CUBE_MAP_SEAMLESS = 0x884F

        internal const val GL_SAMPLE_POSITION = 0x8E50

        internal const val GL_SAMPLE_MASK = 0x8E51

        internal const val GL_SAMPLE_MASK_VALUE = 0x8E52

        internal const val GL_TEXTURE_2D_MULTISAMPLE = 0x9100

        internal const val GL_PROXY_TEXTURE_2D_MULTISAMPLE = 0x9101

        internal const val GL_TEXTURE_2D_MULTISAMPLE_ARRAY = 0x9102

        internal const val GL_PROXY_TEXTURE_2D_MULTISAMPLE_ARRAY = 0x9103

        internal const val GL_MAX_SAMPLE_MASK_WORDS = 0x8E59
        internal const val GL_MAX_COLOR_TEXTURE_SAMPLES = 0x910E
        internal const val GL_MAX_DEPTH_TEXTURE_SAMPLES = 0x910F
        internal const val GL_MAX_INTEGER_SAMPLES = 0x9110
        internal const val GL_TEXTURE_BINDING_2D_MULTISAMPLE = 0x9104
        internal const val GL_TEXTURE_BINDING_2D_MULTISAMPLE_ARRAY = 0x9105

        internal const val GL_TEXTURE_SAMPLES = 0x9106
        internal const val GL_TEXTURE_FIXED_SAMPLE_LOCATIONS = 0x9107

        internal const val GL_SAMPLER_2D_MULTISAMPLE = 0x9108
        internal const val GL_INT_SAMPLER_2D_MULTISAMPLE = 0x9109
        internal const val GL_UNSIGNED_INT_SAMPLER_2D_MULTISAMPLE = 0x910A
        internal const val GL_SAMPLER_2D_MULTISAMPLE_ARRAY = 0x910B
        internal const val GL_INT_SAMPLER_2D_MULTISAMPLE_ARRAY = 0x910C
        internal const val GL_UNSIGNED_INT_SAMPLER_2D_MULTISAMPLE_ARRAY = 0x910D

        internal const val GL_DEPTH_CLAMP = 0x864F

        internal const val GL_GEOMETRY_SHADER = 0x8DD9

        internal const val GL_GEOMETRY_VERTICES_OUT = 0x8DDA
        internal const val GL_GEOMETRY_INPUT_TYPE = 0x8DDB
        internal const val GL_GEOMETRY_OUTPUT_TYPE = 0x8DDC

        internal const val GL_MAX_GEOMETRY_TEXTURE_IMAGE_UNITS = 0x8C29
        internal const val GL_MAX_GEOMETRY_UNIFORM_COMPONENTS = 0x8DDF
        internal const val GL_MAX_GEOMETRY_OUTPUT_VERTICES = 0x8DE0
        internal const val GL_MAX_GEOMETRY_TOTAL_OUTPUT_COMPONENTS = 0x8DE1

        internal const val GL_LINES_ADJACENCY = 0xA
        internal const val GL_LINE_STRIP_ADJACENCY = 0xB
        internal const val GL_TRIANGLES_ADJACENCY = 0xC
        internal const val GL_TRIANGLE_STRIP_ADJACENCY = 0xD

        internal const val GL_FRAMEBUFFER_INCOMPLETE_LAYER_TARGETS = 0x8DA8

        internal const val GL_FRAMEBUFFER_ATTACHMENT_LAYERED = 0x8DA7

        internal const val GL_PROGRAM_POINT_SIZE = 0x8642

        internal const val GL_MAX_SERVER_WAIT_TIMEOUT = 0x9111

        internal const val GL_OBJECT_TYPE = 0x9112
        internal const val GL_SYNC_CONDITION = 0x9113
        internal const val GL_SYNC_STATUS = 0x9114
        internal const val GL_SYNC_FLAGS = 0x9115

        internal const val GL_SYNC_FENCE = 0x9116

        internal const val GL_SYNC_GPU_COMMANDS_COMPLETE = 0x9117

        internal const val GL_UNSIGNALED = 0x9118
        internal const val GL_SIGNALED = 0x9119

        internal const val GL_SYNC_FLUSH_COMMANDS_BIT = 0x1

        internal const val GL_TIMEOUT_IGNORED = -0x1L

        internal const val GL_ALREADY_SIGNALED = 0x911A
        internal const val GL_TIMEOUT_EXPIRED = 0x911B
        internal const val GL_CONDITION_SATISFIED = 0x911C
        internal const val GL_WAIT_FAILED = 0x911D
    }

    fun glDrawElementsBaseVertex(mode: Int, count: Int, type: Int, indices: Long, basevertex: Int)
    fun glDrawRangeElementsBaseVertex(
        mode: Int,
        start: Int,
        end: Int,
        count: Int,
        type: Int,
        indices: Long,
        basevertex: Int
    )

    fun glDrawElementsInstancedBaseVertex(
        mode: Int,
        count: Int,
        type: Int,
        indices: Long,
        instancecount: Int,
        basevertex: Int
    )

    @Unsafe
    fun glMultiDrawElementsBaseVertex(
        mode: Int,
        count: Long,
        type: Int,
        indices: Long,
        drawcount: Int,
        basevertex: Long
    )

    fun glProvokingVertex(mode: Int)

    fun glSampleMaski(maskNumber: Int, mask: Int)

    fun glFenceSync(condition: Int, flags: Int): Long
    fun glIsSync(sync: Long): Boolean
    fun glDeleteSync(sync: Long)
    fun glClientWaitSync(sync: Long, flags: Int, timeout: Long): Int
    fun glWaitSync(sync: Long, flags: Int, timeout: Long)

    fun glGetSynci(sync: Long, pname: Int): Int


    fun glMultiDrawElementsBaseVertex(
        mode: Int,
        count: Ptr,
        type: Int,
        indices: Ptr,
        drawcount: Int,
        basevertex: Ptr
    )
}

abstract class PatchedGL32(protected val delegate: IGL32) : IGL32 by delegate