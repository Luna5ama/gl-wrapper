@file:JvmName("GL30")

package dev.luna5ama.glwrapper

import dev.luna5ama.kmogus.Ptr
import dev.luna5ama.kmogus.ensureCapacity
import dev.luna5ama.kmogus.memcpy

const val GL_MAJOR_VERSION = 0x821B
const val GL_MINOR_VERSION = 0x821C
const val GL_NUM_EXTENSIONS = 0x821D
const val GL_CONTEXT_FLAGS = 0x821E
const val GL_CONTEXT_FLAG_FORWARD_COMPATIBLE_BIT = 0x1

const val GL_COMPARE_REF_TO_TEXTURE = 0x884E
const val GL_CLIP_DISTANCE0 = 0x3000
const val GL_CLIP_DISTANCE1 = 0x3001
const val GL_CLIP_DISTANCE2 = 0x3002
const val GL_CLIP_DISTANCE3 = 0x3003
const val GL_CLIP_DISTANCE4 = 0x3004
const val GL_CLIP_DISTANCE5 = 0x3005
const val GL_CLIP_DISTANCE6 = 0x3006
const val GL_CLIP_DISTANCE7 = 0x3007
const val GL_MAX_CLIP_DISTANCES = 0xD32
const val GL_MAX_VARYING_COMPONENTS = 0x8B4B

const val GL_VERTEX_ATTRIB_ARRAY_INTEGER = 0x88FD

const val GL_SAMPLER_1D_ARRAY = 0x8DC0
const val GL_SAMPLER_2D_ARRAY = 0x8DC1
const val GL_SAMPLER_1D_ARRAY_SHADOW = 0x8DC3
const val GL_SAMPLER_2D_ARRAY_SHADOW = 0x8DC4
const val GL_SAMPLER_CUBE_SHADOW = 0x8DC5
const val GL_UNSIGNED_INT_VEC2 = 0x8DC6
const val GL_UNSIGNED_INT_VEC3 = 0x8DC7
const val GL_UNSIGNED_INT_VEC4 = 0x8DC8
const val GL_INT_SAMPLER_1D = 0x8DC9
const val GL_INT_SAMPLER_2D = 0x8DCA
const val GL_INT_SAMPLER_3D = 0x8DCB
const val GL_INT_SAMPLER_CUBE = 0x8DCC
const val GL_INT_SAMPLER_1D_ARRAY = 0x8DCE
const val GL_INT_SAMPLER_2D_ARRAY = 0x8DCF
const val GL_UNSIGNED_INT_SAMPLER_1D = 0x8DD1
const val GL_UNSIGNED_INT_SAMPLER_2D = 0x8DD2
const val GL_UNSIGNED_INT_SAMPLER_3D = 0x8DD3
const val GL_UNSIGNED_INT_SAMPLER_CUBE = 0x8DD4
const val GL_UNSIGNED_INT_SAMPLER_1D_ARRAY = 0x8DD6
const val GL_UNSIGNED_INT_SAMPLER_2D_ARRAY = 0x8DD7

const val GL_MIN_PROGRAM_TEXEL_OFFSET = 0x8904
const val GL_MAX_PROGRAM_TEXEL_OFFSET = 0x8905

const val GL_QUERY_WAIT = 0x8E13
const val GL_QUERY_NO_WAIT = 0x8E14
const val GL_QUERY_BY_REGION_WAIT = 0x8E15
const val GL_QUERY_BY_REGION_NO_WAIT = 0x8E16

const val GL_MAP_READ_BIT = 0x1
const val GL_MAP_WRITE_BIT = 0x2
const val GL_MAP_INVALIDATE_RANGE_BIT = 0x4
const val GL_MAP_INVALIDATE_BUFFER_BIT = 0x8
const val GL_MAP_FLUSH_EXPLICIT_BIT = 0x10
const val GL_MAP_UNSYNCHRONIZED_BIT = 0x20

const val GL_BUFFER_ACCESS_FLAGS = 0x911F
const val GL_BUFFER_MAP_LENGTH = 0x9120
const val GL_BUFFER_MAP_OFFSET = 0x9121

const val GL_CLAMP_READ_COLOR = 0x891C

const val GL_FIXED_ONLY = 0x891D

const val GL_DEPTH_COMPONENT32F = 0x8CAC
const val GL_DEPTH32F_STENCIL8 = 0x8CAD

const val GL_FLOAT_32_UNSIGNED_INT_24_8_REV = 0x8DAD

const val GL_TEXTURE_RED_TYPE = 0x8C10
const val GL_TEXTURE_GREEN_TYPE = 0x8C11
const val GL_TEXTURE_BLUE_TYPE = 0x8C12
const val GL_TEXTURE_ALPHA_TYPE = 0x8C13
const val GL_TEXTURE_DEPTH_TYPE = 0x8C16

const val GL_UNSIGNED_NORMALIZED = 0x8C17

const val GL_RGBA32F = 0x8814
const val GL_RGB32F = 0x8815
const val GL_RGBA16F = 0x881A
const val GL_RGB16F = 0x881B

const val GL_R11F_G11F_B10F = 0x8C3A

const val GL_UNSIGNED_INT_10F_11F_11F_REV = 0x8C3B

const val GL_RGB9_E5 = 0x8C3D

const val GL_UNSIGNED_INT_5_9_9_9_REV = 0x8C3E

const val GL_TEXTURE_SHARED_SIZE = 0x8C3F

const val GL_FRAMEBUFFER = 0x8D40
const val GL_READ_FRAMEBUFFER = 0x8CA8
const val GL_DRAW_FRAMEBUFFER = 0x8CA9

const val GL_RENDERBUFFER = 0x8D41

const val GL_STENCIL_INDEX1 = 0x8D46
const val GL_STENCIL_INDEX4 = 0x8D47
const val GL_STENCIL_INDEX8 = 0x8D48
const val GL_STENCIL_INDEX16 = 0x8D49

const val GL_RENDERBUFFER_WIDTH = 0x8D42
const val GL_RENDERBUFFER_HEIGHT = 0x8D43
const val GL_RENDERBUFFER_INTERNAL_FORMAT = 0x8D44
const val GL_RENDERBUFFER_RED_SIZE = 0x8D50
const val GL_RENDERBUFFER_GREEN_SIZE = 0x8D51
const val GL_RENDERBUFFER_BLUE_SIZE = 0x8D52
const val GL_RENDERBUFFER_ALPHA_SIZE = 0x8D53
const val GL_RENDERBUFFER_DEPTH_SIZE = 0x8D54
const val GL_RENDERBUFFER_STENCIL_SIZE = 0x8D55
const val GL_RENDERBUFFER_SAMPLES = 0x8CAB

const val GL_FRAMEBUFFER_ATTACHMENT_OBJECT_TYPE = 0x8CD0
const val GL_FRAMEBUFFER_ATTACHMENT_OBJECT_NAME = 0x8CD1
const val GL_FRAMEBUFFER_ATTACHMENT_TEXTURE_LEVEL = 0x8CD2
const val GL_FRAMEBUFFER_ATTACHMENT_TEXTURE_CUBE_MAP_FACE = 0x8CD3
const val GL_FRAMEBUFFER_ATTACHMENT_TEXTURE_LAYER = 0x8CD4
const val GL_FRAMEBUFFER_ATTACHMENT_COLOR_ENCODING = 0x8210
const val GL_FRAMEBUFFER_ATTACHMENT_COMPONENT_TYPE = 0x8211
const val GL_FRAMEBUFFER_ATTACHMENT_RED_SIZE = 0x8212
const val GL_FRAMEBUFFER_ATTACHMENT_GREEN_SIZE = 0x8213
const val GL_FRAMEBUFFER_ATTACHMENT_BLUE_SIZE = 0x8214
const val GL_FRAMEBUFFER_ATTACHMENT_ALPHA_SIZE = 0x8215
const val GL_FRAMEBUFFER_ATTACHMENT_DEPTH_SIZE = 0x8216
const val GL_FRAMEBUFFER_ATTACHMENT_STENCIL_SIZE = 0x8217

const val GL_FRAMEBUFFER_DEFAULT = 0x8218

const val GL_COLOR_ATTACHMENT0 = 0x8CE0
const val GL_COLOR_ATTACHMENT1 = 0x8CE1
const val GL_COLOR_ATTACHMENT2 = 0x8CE2
const val GL_COLOR_ATTACHMENT3 = 0x8CE3
const val GL_COLOR_ATTACHMENT4 = 0x8CE4
const val GL_COLOR_ATTACHMENT5 = 0x8CE5
const val GL_COLOR_ATTACHMENT6 = 0x8CE6
const val GL_COLOR_ATTACHMENT7 = 0x8CE7
const val GL_COLOR_ATTACHMENT8 = 0x8CE8
const val GL_COLOR_ATTACHMENT9 = 0x8CE9
const val GL_COLOR_ATTACHMENT10 = 0x8CEA
const val GL_COLOR_ATTACHMENT11 = 0x8CEB
const val GL_COLOR_ATTACHMENT12 = 0x8CEC
const val GL_COLOR_ATTACHMENT13 = 0x8CED
const val GL_COLOR_ATTACHMENT14 = 0x8CEE
const val GL_COLOR_ATTACHMENT15 = 0x8CEF
const val GL_COLOR_ATTACHMENT16 = 0x8CF0
const val GL_COLOR_ATTACHMENT17 = 0x8CF1
const val GL_COLOR_ATTACHMENT18 = 0x8CF2
const val GL_COLOR_ATTACHMENT19 = 0x8CF3
const val GL_COLOR_ATTACHMENT20 = 0x8CF4
const val GL_COLOR_ATTACHMENT21 = 0x8CF5
const val GL_COLOR_ATTACHMENT22 = 0x8CF6
const val GL_COLOR_ATTACHMENT23 = 0x8CF7
const val GL_COLOR_ATTACHMENT24 = 0x8CF8
const val GL_COLOR_ATTACHMENT25 = 0x8CF9
const val GL_COLOR_ATTACHMENT26 = 0x8CFA
const val GL_COLOR_ATTACHMENT27 = 0x8CFB
const val GL_COLOR_ATTACHMENT28 = 0x8CFC
const val GL_COLOR_ATTACHMENT29 = 0x8CFD
const val GL_COLOR_ATTACHMENT30 = 0x8CFE
const val GL_COLOR_ATTACHMENT31 = 0x8CFF
const val GL_DEPTH_ATTACHMENT = 0x8D00
const val GL_STENCIL_ATTACHMENT = 0x8D20
const val GL_DEPTH_STENCIL_ATTACHMENT = 0x821A

const val GL_MAX_SAMPLES = 0x8D57

const val GL_FRAMEBUFFER_COMPLETE = 0x8CD5
const val GL_FRAMEBUFFER_INCOMPLETE_ATTACHMENT = 0x8CD6
const val GL_FRAMEBUFFER_INCOMPLETE_MISSING_ATTACHMENT = 0x8CD7
const val GL_FRAMEBUFFER_INCOMPLETE_DRAW_BUFFER = 0x8CDB
const val GL_FRAMEBUFFER_INCOMPLETE_READ_BUFFER = 0x8CDC
const val GL_FRAMEBUFFER_UNSUPPORTED = 0x8CDD
const val GL_FRAMEBUFFER_INCOMPLETE_MULTISAMPLE = 0x8D56
const val GL_FRAMEBUFFER_UNDEFINED = 0x8219

const val GL_FRAMEBUFFER_BINDING = 0x8CA6
const val GL_DRAW_FRAMEBUFFER_BINDING = 0x8CA6
const val GL_READ_FRAMEBUFFER_BINDING = 0x8CAA
const val GL_RENDERBUFFER_BINDING = 0x8CA7
const val GL_MAX_COLOR_ATTACHMENTS = 0x8CDF
const val GL_MAX_RENDERBUFFER_SIZE = 0x84E8

const val GL_INVALID_FRAMEBUFFER_OPERATION = 0x506

const val GL_DEPTH_STENCIL = 0x84F9

const val GL_UNSIGNED_INT_24_8 = 0x84FA

const val GL_DEPTH24_STENCIL8 = 0x88F0

const val GL_TEXTURE_STENCIL_SIZE = 0x88F1

const val GL_HALF_FLOAT = 0x140B
const val GL_RGBA32UI = 0x8D70
const val GL_RGB32UI = 0x8D71
const val GL_RGBA16UI = 0x8D76
const val GL_RGB16UI = 0x8D77
const val GL_RGBA8UI = 0x8D7C
const val GL_RGB8UI = 0x8D7D
const val GL_RGBA32I = 0x8D82
const val GL_RGB32I = 0x8D83
const val GL_RGBA16I = 0x8D88
const val GL_RGB16I = 0x8D89
const val GL_RGBA8I = 0x8D8E
const val GL_RGB8I = 0x8D8F

const val GL_RED_INTEGER = 0x8D94
const val GL_GREEN_INTEGER = 0x8D95
const val GL_BLUE_INTEGER = 0x8D96
const val GL_RGB_INTEGER = 0x8D98
const val GL_RGBA_INTEGER = 0x8D99
const val GL_BGR_INTEGER = 0x8D9A
const val GL_BGRA_INTEGER = 0x8D9B

const val GL_TEXTURE_1D_ARRAY = 0x8C18
const val GL_TEXTURE_2D_ARRAY = 0x8C1A

const val GL_PROXY_TEXTURE_2D_ARRAY = 0x8C1B

const val GL_PROXY_TEXTURE_1D_ARRAY = 0x8C19

const val GL_TEXTURE_BINDING_1D_ARRAY = 0x8C1C
const val GL_TEXTURE_BINDING_2D_ARRAY = 0x8C1D
const val GL_MAX_ARRAY_TEXTURE_LAYERS = 0x88FF

const val GL_COMPRESSED_RED_RGTC1 = 0x8DBB
const val GL_COMPRESSED_SIGNED_RED_RGTC1 = 0x8DBC
const val GL_COMPRESSED_RG_RGTC2 = 0x8DBD
const val GL_COMPRESSED_SIGNED_RG_RGTC2 = 0x8DBE

const val GL_R8 = 0x8229
const val GL_R16 = 0x822A
const val GL_RG8 = 0x822B
const val GL_RG16 = 0x822C
const val GL_R16F = 0x822D
const val GL_R32F = 0x822E
const val GL_RG16F = 0x822F
const val GL_RG32F = 0x8230
const val GL_R8I = 0x8231
const val GL_R8UI = 0x8232
const val GL_R16I = 0x8233
const val GL_R16UI = 0x8234
const val GL_R32I = 0x8235
const val GL_R32UI = 0x8236
const val GL_RG8I = 0x8237
const val GL_RG8UI = 0x8238
const val GL_RG16I = 0x8239
const val GL_RG16UI = 0x823A
const val GL_RG32I = 0x823B
const val GL_RG32UI = 0x823C
const val GL_RG = 0x8227
const val GL_COMPRESSED_RED = 0x8225
const val GL_COMPRESSED_RG = 0x8226

const val GL_RG_INTEGER = 0x8228

const val GL_TRANSFORM_FEEDBACK_BUFFER = 0x8C8E

const val GL_TRANSFORM_FEEDBACK_BUFFER_START = 0x8C84
const val GL_TRANSFORM_FEEDBACK_BUFFER_SIZE = 0x8C85

const val GL_TRANSFORM_FEEDBACK_BUFFER_BINDING = 0x8C8F

const val GL_INTERLEAVED_ATTRIBS = 0x8C8C
const val GL_SEPARATE_ATTRIBS = 0x8C8D

const val GL_PRIMITIVES_GENERATED = 0x8C87
const val GL_TRANSFORM_FEEDBACK_PRIMITIVES_WRITTEN = 0x8C88

const val GL_RASTERIZER_DISCARD = 0x8C89

const val GL_MAX_TRANSFORM_FEEDBACK_INTERLEAVED_COMPONENTS = 0x8C8A
const val GL_MAX_TRANSFORM_FEEDBACK_SEPARATE_ATTRIBS = 0x8C8B
const val GL_MAX_TRANSFORM_FEEDBACK_SEPARATE_COMPONENTS = 0x8C80

const val GL_TRANSFORM_FEEDBACK_VARYINGS = 0x8C83
const val GL_TRANSFORM_FEEDBACK_BUFFER_MODE = 0x8C7F
const val GL_TRANSFORM_FEEDBACK_VARYING_MAX_LENGTH = 0x8C76

const val GL_VERTEX_ARRAY_BINDING = 0x85B5

const val GL_FRAMEBUFFER_SRGB = 0x8DB9


interface IGL30 : GLBase {
    fun glGetStringi(name: Int, index: Int): String

    fun glIsRenderbuffer(renderbuffer: Int): Boolean
    fun glBindRenderbuffer(target: Int, renderbuffer: Int)

    @Unsafe
    fun glDeleteRenderbuffers(n: Int, renderbuffers: Long)

    fun glIsFramebuffer(framebuffer: Int): Boolean
    fun glBindFramebuffer(target: Int, framebuffer: Int)

    @Unsafe
    fun glDeleteFramebuffers(n: Int, framebuffers: Long)

    fun glColorMaski(buf: Int, r: Boolean, g: Boolean, b: Boolean, a: Boolean)

    fun glBindBufferRange(target: Int, index: Int, buffer: Int, offset: Long, size: Long)
    fun glBindBufferBase(target: Int, index: Int, buffer: Int)

    fun glBindVertexArray(array: Int)

    @Unsafe
    fun glDeleteVertexArrays(n: Int, arrays: Long)
    fun glIsVertexArray(array: Int): Boolean


    fun glDeleteRenderbuffers(n: Int, renderbuffers: Ptr) {
        glDeleteRenderbuffers(n, renderbuffers.address)
    }

    fun glDeleteRenderbuffers(renderbuffer: Int) {
        val ptr = tempArr.ptr
        ptr.setInt(renderbuffer)
        glDeleteRenderbuffers(1, ptr)
    }

    fun glDeleteRenderbuffers(renderbuffer1: Int, renderbuffer2: Int) {
        val ptr = tempArr.ptr
        ptr.setInt(renderbuffer1)
        ptr.setInt(4, renderbuffer2)
        glDeleteRenderbuffers(2, ptr)
    }

    fun glDeleteRenderbuffers(renderbuffer1: Int, renderbuffer2: Int, renderbuffer3: Int) {
        val ptr = tempArr.ptr
        ptr.setInt(renderbuffer1)
        ptr.setInt(4, renderbuffer2)
        ptr.setInt(8, renderbuffer3)
        glDeleteRenderbuffers(3, ptr)
    }

    fun glDeleteRenderbuffers(renderbuffer1: Int, renderbuffer2: Int, renderbuffer3: Int, renderbuffer4: Int) {
        val ptr = tempArr.ptr
        ptr.setInt(renderbuffer1)
        ptr.setInt(4, renderbuffer2)
        ptr.setInt(8, renderbuffer3)
        ptr.setInt(12, renderbuffer4)
        glDeleteRenderbuffers(4, ptr)
    }

    fun glDeleteRenderbuffers(vararg renderbuffers: Int) {
        tempArr.ensureCapacity(renderbuffers.size * 4L, false)
        val ptr = tempArr.ptr
        memcpy(renderbuffers, ptr, renderbuffers.size * 4L)
        glDeleteRenderbuffers(renderbuffers.size, ptr)
    }

    fun glDeleteRenderbuffers(renderbuffer: IntArray, offset: Int, length: Int) {
        tempArr.ensureCapacity(length * 4L, false)
        val ptr = tempArr.ptr
        memcpy(renderbuffer, offset * 4L, ptr, 0L, length * 4L)
        glDeleteRenderbuffers(length, ptr)
    }

    fun glDeleteFramebuffers(n: Int, framebuffers: Ptr) {
        glDeleteFramebuffers(n, framebuffers.address)
    }

    fun glDeleteFramebuffers(framebuffer: Int) {
        val ptr = tempArr.ptr
        ptr.setInt(framebuffer)
        glDeleteFramebuffers(1, ptr)
    }

    fun glDeleteFramebuffers(framebuffer1: Int, framebuffer2: Int) {
        val ptr = tempArr.ptr
        ptr.setInt(framebuffer1)
        ptr.setInt(4, framebuffer2)
        glDeleteFramebuffers(2, ptr)
    }

    fun glDeleteFramebuffers(framebuffer1: Int, framebuffer2: Int, framebuffer3: Int) {
        val ptr = tempArr.ptr
        ptr.setInt(framebuffer1)
        ptr.setInt(4, framebuffer2)
        ptr.setInt(8, framebuffer3)
        glDeleteFramebuffers(3, ptr)
    }

    fun glDeleteFramebuffers(framebuffer1: Int, framebuffer2: Int, framebuffer3: Int, framebuffer4: Int) {
        val ptr = tempArr.ptr
        ptr.setInt(framebuffer1)
        ptr.setInt(4, framebuffer2)
        ptr.setInt(8, framebuffer3)
        ptr.setInt(12, framebuffer4)
        glDeleteFramebuffers(4, ptr)
    }

    fun glDeleteFramebuffers(vararg framebuffers: Int) {
        tempArr.ensureCapacity(framebuffers.size * 4L, false)
        val ptr = tempArr.ptr
        memcpy(framebuffers, ptr, framebuffers.size * 4L)
        glDeleteFramebuffers(framebuffers.size, ptr)
    }

    fun glDeleteFramebuffers(framebuffer: IntArray, offset: Int, length: Int) {
        tempArr.ensureCapacity(length * 4L, false)
        val ptr = tempArr.ptr
        memcpy(framebuffer, offset * 4L, ptr, 0L, length * 4L)
        glDeleteFramebuffers(length, ptr)
    }

    fun glDeleteVertexArrays(n: Int, arrays: Ptr) {
        glDeleteVertexArrays(n, arrays.address)
    }

    fun glDeleteVertexArrays(array: Int) {
        val ptr = tempArr.ptr
        ptr.setInt(array)
        glDeleteVertexArrays(1, ptr)
    }

    fun glDeleteVertexArrays(array1: Int, array2: Int) {
        val ptr = tempArr.ptr
        ptr.setInt(array1)
        ptr.setInt(4, array2)
        glDeleteVertexArrays(2, ptr)
    }

    fun glDeleteVertexArrays(array1: Int, array2: Int, array3: Int) {
        val ptr = tempArr.ptr
        ptr.setInt(array1)
        ptr.setInt(4, array2)
        ptr.setInt(8, array3)
        glDeleteVertexArrays(3, ptr)
    }

    fun glDeleteVertexArrays(array1: Int, array2: Int, array3: Int, array4: Int) {
        val ptr = tempArr.ptr
        ptr.setInt(array1)
        ptr.setInt(4, array2)
        ptr.setInt(8, array3)
        ptr.setInt(12, array4)
        glDeleteVertexArrays(4, ptr)
    }

    fun glDeleteVertexArrays(vararg arrays: Int) {
        tempArr.ensureCapacity(arrays.size * 4L, false)
        val ptr = tempArr.ptr
        memcpy(arrays, ptr, arrays.size * 4L)
        glDeleteVertexArrays(arrays.size, ptr)
    }

    fun glDeleteVertexArrays(array: IntArray, offset: Int, length: Int) {
        tempArr.ensureCapacity(length * 4L, false)
        val ptr = tempArr.ptr
        memcpy(array, offset * 4L, ptr, 0L, length * 4L)
        glDeleteVertexArrays(length, ptr)
    }
}