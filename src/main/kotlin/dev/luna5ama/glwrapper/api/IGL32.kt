@file:JvmName("GL32")

package dev.luna5ama.glwrapper.api

import dev.luna5ama.kmogus.Ptr

const val GL_CONTEXT_PROFILE_MASK = 0x9126

const val GL_CONTEXT_CORE_PROFILE_BIT = 0x1
const val GL_CONTEXT_COMPATIBILITY_PROFILE_BIT = 0x2

const val GL_MAX_VERTEX_OUTPUT_COMPONENTS = 0x9122
const val GL_MAX_GEOMETRY_INPUT_COMPONENTS = 0x9123
const val GL_MAX_GEOMETRY_OUTPUT_COMPONENTS = 0x9124
const val GL_MAX_FRAGMENT_INPUT_COMPONENTS = 0x9125

const val GL_FIRST_VERTEX_CONVENTION = 0x8E4D
const val GL_LAST_VERTEX_CONVENTION = 0x8E4E

const val GL_PROVOKING_VERTEX = 0x8E4F
const val GL_QUADS_FOLLOW_PROVOKING_VERTEX_CONVENTION = 0x8E4C

const val GL_TEXTURE_CUBE_MAP_SEAMLESS = 0x884F

const val GL_SAMPLE_POSITION = 0x8E50

const val GL_SAMPLE_MASK = 0x8E51

const val GL_SAMPLE_MASK_VALUE = 0x8E52

const val GL_TEXTURE_2D_MULTISAMPLE = 0x9100

const val GL_PROXY_TEXTURE_2D_MULTISAMPLE = 0x9101

const val GL_TEXTURE_2D_MULTISAMPLE_ARRAY = 0x9102

const val GL_PROXY_TEXTURE_2D_MULTISAMPLE_ARRAY = 0x9103

const val GL_MAX_SAMPLE_MASK_WORDS = 0x8E59
const val GL_MAX_COLOR_TEXTURE_SAMPLES = 0x910E
const val GL_MAX_DEPTH_TEXTURE_SAMPLES = 0x910F
const val GL_MAX_INTEGER_SAMPLES = 0x9110
const val GL_TEXTURE_BINDING_2D_MULTISAMPLE = 0x9104
const val GL_TEXTURE_BINDING_2D_MULTISAMPLE_ARRAY = 0x9105

const val GL_TEXTURE_SAMPLES = 0x9106
const val GL_TEXTURE_FIXED_SAMPLE_LOCATIONS = 0x9107

const val GL_SAMPLER_2D_MULTISAMPLE = 0x9108
const val GL_INT_SAMPLER_2D_MULTISAMPLE = 0x9109
const val GL_UNSIGNED_INT_SAMPLER_2D_MULTISAMPLE = 0x910A
const val GL_SAMPLER_2D_MULTISAMPLE_ARRAY = 0x910B
const val GL_INT_SAMPLER_2D_MULTISAMPLE_ARRAY = 0x910C
const val GL_UNSIGNED_INT_SAMPLER_2D_MULTISAMPLE_ARRAY = 0x910D

const val GL_DEPTH_CLAMP = 0x864F

const val GL_GEOMETRY_SHADER = 0x8DD9

const val GL_GEOMETRY_VERTICES_OUT = 0x8DDA
const val GL_GEOMETRY_INPUT_TYPE = 0x8DDB
const val GL_GEOMETRY_OUTPUT_TYPE = 0x8DDC

const val GL_MAX_GEOMETRY_TEXTURE_IMAGE_UNITS = 0x8C29
const val GL_MAX_GEOMETRY_UNIFORM_COMPONENTS = 0x8DDF
const val GL_MAX_GEOMETRY_OUTPUT_VERTICES = 0x8DE0
const val GL_MAX_GEOMETRY_TOTAL_OUTPUT_COMPONENTS = 0x8DE1

const val GL_LINES_ADJACENCY = 0xA
const val GL_LINE_STRIP_ADJACENCY = 0xB
const val GL_TRIANGLES_ADJACENCY = 0xC
const val GL_TRIANGLE_STRIP_ADJACENCY = 0xD

const val GL_FRAMEBUFFER_INCOMPLETE_LAYER_TARGETS = 0x8DA8

const val GL_FRAMEBUFFER_ATTACHMENT_LAYERED = 0x8DA7

const val GL_PROGRAM_POINT_SIZE = 0x8642

const val GL_MAX_SERVER_WAIT_TIMEOUT = 0x9111

const val GL_OBJECT_TYPE = 0x9112
const val GL_SYNC_CONDITION = 0x9113
const val GL_SYNC_STATUS = 0x9114
const val GL_SYNC_FLAGS = 0x9115

const val GL_SYNC_FENCE = 0x9116

const val GL_SYNC_GPU_COMMANDS_COMPLETE = 0x9117

const val GL_UNSIGNALED = 0x9118
const val GL_SIGNALED = 0x9119

const val GL_SYNC_FLUSH_COMMANDS_BIT = 0x1

const val GL_TIMEOUT_IGNORED = -0x1L

const val GL_ALREADY_SIGNALED = 0x911A
const val GL_TIMEOUT_EXPIRED = 0x911B
const val GL_CONDITION_SATISFIED = 0x911C
const val GL_WAIT_FAILED = 0x911D


interface IGL32 : GLBase {
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