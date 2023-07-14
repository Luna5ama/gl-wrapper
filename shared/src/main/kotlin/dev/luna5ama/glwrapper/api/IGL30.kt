@file:JvmName("GL30")

package dev.luna5ama.glwrapper.api

import dev.luna5ama.kmogus.Ptr
import dev.luna5ama.kmogus.ensureCapacity
import dev.luna5ama.kmogus.memcpy

interface IGL30 : GLBase {
    companion object {
        internal const val GL_MAJOR_VERSION = 0x821B
        internal const val GL_MINOR_VERSION = 0x821C
        internal const val GL_NUM_EXTENSIONS = 0x821D
        internal const val GL_CONTEXT_FLAGS = 0x821E
        internal const val GL_CONTEXT_FLAG_FORWARD_COMPATIBLE_BIT = 0x1

        internal const val GL_COMPARE_REF_TO_TEXTURE = 0x884E
        internal const val GL_CLIP_DISTANCE0 = 0x3000
        internal const val GL_CLIP_DISTANCE1 = 0x3001
        internal const val GL_CLIP_DISTANCE2 = 0x3002
        internal const val GL_CLIP_DISTANCE3 = 0x3003
        internal const val GL_CLIP_DISTANCE4 = 0x3004
        internal const val GL_CLIP_DISTANCE5 = 0x3005
        internal const val GL_CLIP_DISTANCE6 = 0x3006
        internal const val GL_CLIP_DISTANCE7 = 0x3007
        internal const val GL_MAX_CLIP_DISTANCES = 0xD32
        internal const val GL_MAX_VARYING_COMPONENTS = 0x8B4B

        internal const val GL_VERTEX_ATTRIB_ARRAY_INTEGER = 0x88FD

        internal const val GL_SAMPLER_1D_ARRAY = 0x8DC0
        internal const val GL_SAMPLER_2D_ARRAY = 0x8DC1
        internal const val GL_SAMPLER_1D_ARRAY_SHADOW = 0x8DC3
        internal const val GL_SAMPLER_2D_ARRAY_SHADOW = 0x8DC4
        internal const val GL_SAMPLER_CUBE_SHADOW = 0x8DC5
        internal const val GL_UNSIGNED_INT_VEC2 = 0x8DC6
        internal const val GL_UNSIGNED_INT_VEC3 = 0x8DC7
        internal const val GL_UNSIGNED_INT_VEC4 = 0x8DC8
        internal const val GL_INT_SAMPLER_1D = 0x8DC9
        internal const val GL_INT_SAMPLER_2D = 0x8DCA
        internal const val GL_INT_SAMPLER_3D = 0x8DCB
        internal const val GL_INT_SAMPLER_CUBE = 0x8DCC
        internal const val GL_INT_SAMPLER_1D_ARRAY = 0x8DCE
        internal const val GL_INT_SAMPLER_2D_ARRAY = 0x8DCF
        internal const val GL_UNSIGNED_INT_SAMPLER_1D = 0x8DD1
        internal const val GL_UNSIGNED_INT_SAMPLER_2D = 0x8DD2
        internal const val GL_UNSIGNED_INT_SAMPLER_3D = 0x8DD3
        internal const val GL_UNSIGNED_INT_SAMPLER_CUBE = 0x8DD4
        internal const val GL_UNSIGNED_INT_SAMPLER_1D_ARRAY = 0x8DD6
        internal const val GL_UNSIGNED_INT_SAMPLER_2D_ARRAY = 0x8DD7

        internal const val GL_MIN_PROGRAM_TEXEL_OFFSET = 0x8904
        internal const val GL_MAX_PROGRAM_TEXEL_OFFSET = 0x8905

        internal const val GL_QUERY_WAIT = 0x8E13
        internal const val GL_QUERY_NO_WAIT = 0x8E14
        internal const val GL_QUERY_BY_REGION_WAIT = 0x8E15
        internal const val GL_QUERY_BY_REGION_NO_WAIT = 0x8E16

        internal const val GL_MAP_READ_BIT = 0x1
        internal const val GL_MAP_WRITE_BIT = 0x2
        internal const val GL_MAP_INVALIDATE_RANGE_BIT = 0x4
        internal const val GL_MAP_INVALIDATE_BUFFER_BIT = 0x8
        internal const val GL_MAP_FLUSH_EXPLICIT_BIT = 0x10
        internal const val GL_MAP_UNSYNCHRONIZED_BIT = 0x20

        internal const val GL_BUFFER_ACCESS_FLAGS = 0x911F
        internal const val GL_BUFFER_MAP_LENGTH = 0x9120
        internal const val GL_BUFFER_MAP_OFFSET = 0x9121

        internal const val GL_CLAMP_READ_COLOR = 0x891C

        internal const val GL_FIXED_ONLY = 0x891D

        internal const val GL_DEPTH_COMPONENT32F = 0x8CAC
        internal const val GL_DEPTH32F_STENCIL8 = 0x8CAD

        internal const val GL_FLOAT_32_UNSIGNED_INT_24_8_REV = 0x8DAD

        internal const val GL_TEXTURE_RED_TYPE = 0x8C10
        internal const val GL_TEXTURE_GREEN_TYPE = 0x8C11
        internal const val GL_TEXTURE_BLUE_TYPE = 0x8C12
        internal const val GL_TEXTURE_ALPHA_TYPE = 0x8C13
        internal const val GL_TEXTURE_DEPTH_TYPE = 0x8C16

        internal const val GL_UNSIGNED_NORMALIZED = 0x8C17

        internal const val GL_RGBA32F = 0x8814
        internal const val GL_RGB32F = 0x8815
        internal const val GL_RGBA16F = 0x881A
        internal const val GL_RGB16F = 0x881B

        internal const val GL_R11F_G11F_B10F = 0x8C3A

        internal const val GL_UNSIGNED_INT_10F_11F_11F_REV = 0x8C3B

        internal const val GL_RGB9_E5 = 0x8C3D

        internal const val GL_UNSIGNED_INT_5_9_9_9_REV = 0x8C3E

        internal const val GL_TEXTURE_SHARED_SIZE = 0x8C3F

        internal const val GL_FRAMEBUFFER = 0x8D40
        internal const val GL_READ_FRAMEBUFFER = 0x8CA8
        internal const val GL_DRAW_FRAMEBUFFER = 0x8CA9

        internal const val GL_RENDERBUFFER = 0x8D41

        internal const val GL_STENCIL_INDEX1 = 0x8D46
        internal const val GL_STENCIL_INDEX4 = 0x8D47
        internal const val GL_STENCIL_INDEX8 = 0x8D48
        internal const val GL_STENCIL_INDEX16 = 0x8D49

        internal const val GL_RENDERBUFFER_WIDTH = 0x8D42
        internal const val GL_RENDERBUFFER_HEIGHT = 0x8D43
        internal const val GL_RENDERBUFFER_INTERNAL_FORMAT = 0x8D44
        internal const val GL_RENDERBUFFER_RED_SIZE = 0x8D50
        internal const val GL_RENDERBUFFER_GREEN_SIZE = 0x8D51
        internal const val GL_RENDERBUFFER_BLUE_SIZE = 0x8D52
        internal const val GL_RENDERBUFFER_ALPHA_SIZE = 0x8D53
        internal const val GL_RENDERBUFFER_DEPTH_SIZE = 0x8D54
        internal const val GL_RENDERBUFFER_STENCIL_SIZE = 0x8D55
        internal const val GL_RENDERBUFFER_SAMPLES = 0x8CAB

        internal const val GL_FRAMEBUFFER_ATTACHMENT_OBJECT_TYPE = 0x8CD0
        internal const val GL_FRAMEBUFFER_ATTACHMENT_OBJECT_NAME = 0x8CD1
        internal const val GL_FRAMEBUFFER_ATTACHMENT_TEXTURE_LEVEL = 0x8CD2
        internal const val GL_FRAMEBUFFER_ATTACHMENT_TEXTURE_CUBE_MAP_FACE = 0x8CD3
        internal const val GL_FRAMEBUFFER_ATTACHMENT_TEXTURE_LAYER = 0x8CD4
        internal const val GL_FRAMEBUFFER_ATTACHMENT_COLOR_ENCODING = 0x8210
        internal const val GL_FRAMEBUFFER_ATTACHMENT_COMPONENT_TYPE = 0x8211
        internal const val GL_FRAMEBUFFER_ATTACHMENT_RED_SIZE = 0x8212
        internal const val GL_FRAMEBUFFER_ATTACHMENT_GREEN_SIZE = 0x8213
        internal const val GL_FRAMEBUFFER_ATTACHMENT_BLUE_SIZE = 0x8214
        internal const val GL_FRAMEBUFFER_ATTACHMENT_ALPHA_SIZE = 0x8215
        internal const val GL_FRAMEBUFFER_ATTACHMENT_DEPTH_SIZE = 0x8216
        internal const val GL_FRAMEBUFFER_ATTACHMENT_STENCIL_SIZE = 0x8217

        internal const val GL_FRAMEBUFFER_DEFAULT = 0x8218

        internal const val GL_COLOR_ATTACHMENT0 = 0x8CE0
        internal const val GL_COLOR_ATTACHMENT1 = 0x8CE1
        internal const val GL_COLOR_ATTACHMENT2 = 0x8CE2
        internal const val GL_COLOR_ATTACHMENT3 = 0x8CE3
        internal const val GL_COLOR_ATTACHMENT4 = 0x8CE4
        internal const val GL_COLOR_ATTACHMENT5 = 0x8CE5
        internal const val GL_COLOR_ATTACHMENT6 = 0x8CE6
        internal const val GL_COLOR_ATTACHMENT7 = 0x8CE7
        internal const val GL_COLOR_ATTACHMENT8 = 0x8CE8
        internal const val GL_COLOR_ATTACHMENT9 = 0x8CE9
        internal const val GL_COLOR_ATTACHMENT10 = 0x8CEA
        internal const val GL_COLOR_ATTACHMENT11 = 0x8CEB
        internal const val GL_COLOR_ATTACHMENT12 = 0x8CEC
        internal const val GL_COLOR_ATTACHMENT13 = 0x8CED
        internal const val GL_COLOR_ATTACHMENT14 = 0x8CEE
        internal const val GL_COLOR_ATTACHMENT15 = 0x8CEF
        internal const val GL_COLOR_ATTACHMENT16 = 0x8CF0
        internal const val GL_COLOR_ATTACHMENT17 = 0x8CF1
        internal const val GL_COLOR_ATTACHMENT18 = 0x8CF2
        internal const val GL_COLOR_ATTACHMENT19 = 0x8CF3
        internal const val GL_COLOR_ATTACHMENT20 = 0x8CF4
        internal const val GL_COLOR_ATTACHMENT21 = 0x8CF5
        internal const val GL_COLOR_ATTACHMENT22 = 0x8CF6
        internal const val GL_COLOR_ATTACHMENT23 = 0x8CF7
        internal const val GL_COLOR_ATTACHMENT24 = 0x8CF8
        internal const val GL_COLOR_ATTACHMENT25 = 0x8CF9
        internal const val GL_COLOR_ATTACHMENT26 = 0x8CFA
        internal const val GL_COLOR_ATTACHMENT27 = 0x8CFB
        internal const val GL_COLOR_ATTACHMENT28 = 0x8CFC
        internal const val GL_COLOR_ATTACHMENT29 = 0x8CFD
        internal const val GL_COLOR_ATTACHMENT30 = 0x8CFE
        internal const val GL_COLOR_ATTACHMENT31 = 0x8CFF
        internal const val GL_DEPTH_ATTACHMENT = 0x8D00
        internal const val GL_STENCIL_ATTACHMENT = 0x8D20
        internal const val GL_DEPTH_STENCIL_ATTACHMENT = 0x821A

        internal const val GL_MAX_SAMPLES = 0x8D57

        internal const val GL_FRAMEBUFFER_COMPLETE = 0x8CD5
        internal const val GL_FRAMEBUFFER_INCOMPLETE_ATTACHMENT = 0x8CD6
        internal const val GL_FRAMEBUFFER_INCOMPLETE_MISSING_ATTACHMENT = 0x8CD7
        internal const val GL_FRAMEBUFFER_INCOMPLETE_DRAW_BUFFER = 0x8CDB
        internal const val GL_FRAMEBUFFER_INCOMPLETE_READ_BUFFER = 0x8CDC
        internal const val GL_FRAMEBUFFER_UNSUPPORTED = 0x8CDD
        internal const val GL_FRAMEBUFFER_INCOMPLETE_MULTISAMPLE = 0x8D56
        internal const val GL_FRAMEBUFFER_UNDEFINED = 0x8219

        internal const val GL_FRAMEBUFFER_BINDING = 0x8CA6
        internal const val GL_DRAW_FRAMEBUFFER_BINDING = 0x8CA6
        internal const val GL_READ_FRAMEBUFFER_BINDING = 0x8CAA
        internal const val GL_RENDERBUFFER_BINDING = 0x8CA7
        internal const val GL_MAX_COLOR_ATTACHMENTS = 0x8CDF
        internal const val GL_MAX_RENDERBUFFER_SIZE = 0x84E8

        internal const val GL_INVALID_FRAMEBUFFER_OPERATION = 0x506

        internal const val GL_DEPTH_STENCIL = 0x84F9

        internal const val GL_UNSIGNED_INT_24_8 = 0x84FA

        internal const val GL_DEPTH24_STENCIL8 = 0x88F0

        internal const val GL_TEXTURE_STENCIL_SIZE = 0x88F1

        internal const val GL_HALF_FLOAT = 0x140B
        internal const val GL_RGBA32UI = 0x8D70
        internal const val GL_RGB32UI = 0x8D71
        internal const val GL_RGBA16UI = 0x8D76
        internal const val GL_RGB16UI = 0x8D77
        internal const val GL_RGBA8UI = 0x8D7C
        internal const val GL_RGB8UI = 0x8D7D
        internal const val GL_RGBA32I = 0x8D82
        internal const val GL_RGB32I = 0x8D83
        internal const val GL_RGBA16I = 0x8D88
        internal const val GL_RGB16I = 0x8D89
        internal const val GL_RGBA8I = 0x8D8E
        internal const val GL_RGB8I = 0x8D8F

        internal const val GL_RED_INTEGER = 0x8D94
        internal const val GL_GREEN_INTEGER = 0x8D95
        internal const val GL_BLUE_INTEGER = 0x8D96
        internal const val GL_RGB_INTEGER = 0x8D98
        internal const val GL_RGBA_INTEGER = 0x8D99
        internal const val GL_BGR_INTEGER = 0x8D9A
        internal const val GL_BGRA_INTEGER = 0x8D9B

        internal const val GL_TEXTURE_1D_ARRAY = 0x8C18
        internal const val GL_TEXTURE_2D_ARRAY = 0x8C1A

        internal const val GL_PROXY_TEXTURE_2D_ARRAY = 0x8C1B

        internal const val GL_PROXY_TEXTURE_1D_ARRAY = 0x8C19

        internal const val GL_TEXTURE_BINDING_1D_ARRAY = 0x8C1C
        internal const val GL_TEXTURE_BINDING_2D_ARRAY = 0x8C1D
        internal const val GL_MAX_ARRAY_TEXTURE_LAYERS = 0x88FF

        internal const val GL_COMPRESSED_RED_RGTC1 = 0x8DBB
        internal const val GL_COMPRESSED_SIGNED_RED_RGTC1 = 0x8DBC
        internal const val GL_COMPRESSED_RG_RGTC2 = 0x8DBD
        internal const val GL_COMPRESSED_SIGNED_RG_RGTC2 = 0x8DBE

        internal const val GL_R8 = 0x8229
        internal const val GL_R16 = 0x822A
        internal const val GL_RG8 = 0x822B
        internal const val GL_RG16 = 0x822C
        internal const val GL_R16F = 0x822D
        internal const val GL_R32F = 0x822E
        internal const val GL_RG16F = 0x822F
        internal const val GL_RG32F = 0x8230
        internal const val GL_R8I = 0x8231
        internal const val GL_R8UI = 0x8232
        internal const val GL_R16I = 0x8233
        internal const val GL_R16UI = 0x8234
        internal const val GL_R32I = 0x8235
        internal const val GL_R32UI = 0x8236
        internal const val GL_RG8I = 0x8237
        internal const val GL_RG8UI = 0x8238
        internal const val GL_RG16I = 0x8239
        internal const val GL_RG16UI = 0x823A
        internal const val GL_RG32I = 0x823B
        internal const val GL_RG32UI = 0x823C
        internal const val GL_RG = 0x8227
        internal const val GL_COMPRESSED_RED = 0x8225
        internal const val GL_COMPRESSED_RG = 0x8226

        internal const val GL_RG_INTEGER = 0x8228

        internal const val GL_TRANSFORM_FEEDBACK_BUFFER = 0x8C8E

        internal const val GL_TRANSFORM_FEEDBACK_BUFFER_START = 0x8C84
        internal const val GL_TRANSFORM_FEEDBACK_BUFFER_SIZE = 0x8C85

        internal const val GL_TRANSFORM_FEEDBACK_BUFFER_BINDING = 0x8C8F

        internal const val GL_INTERLEAVED_ATTRIBS = 0x8C8C
        internal const val GL_SEPARATE_ATTRIBS = 0x8C8D

        internal const val GL_PRIMITIVES_GENERATED = 0x8C87
        internal const val GL_TRANSFORM_FEEDBACK_PRIMITIVES_WRITTEN = 0x8C88

        internal const val GL_RASTERIZER_DISCARD = 0x8C89

        internal const val GL_MAX_TRANSFORM_FEEDBACK_INTERLEAVED_COMPONENTS = 0x8C8A
        internal const val GL_MAX_TRANSFORM_FEEDBACK_SEPARATE_ATTRIBS = 0x8C8B
        internal const val GL_MAX_TRANSFORM_FEEDBACK_SEPARATE_COMPONENTS = 0x8C80

        internal const val GL_TRANSFORM_FEEDBACK_VARYINGS = 0x8C83
        internal const val GL_TRANSFORM_FEEDBACK_BUFFER_MODE = 0x8C7F
        internal const val GL_TRANSFORM_FEEDBACK_VARYING_MAX_LENGTH = 0x8C76

        internal const val GL_VERTEX_ARRAY_BINDING = 0x85B5

        internal const val GL_FRAMEBUFFER_SRGB = 0x8DB9
    }

    fun glGetStringi(name: Int, index: Int): String?

    fun glIsRenderbuffer(renderbuffer: Int): Boolean
    fun glBindRenderbuffer(target: Int, renderbuffer: Int)

    @Unsafe
    fun glDeleteRenderbuffers(n: Int, renderbuffers: Long)

    fun glIsFramebuffer(framebuffer: Int): Boolean
    fun glBindFramebuffer(target: Int, framebuffer: Int)

    @Unsafe
    fun glDeleteFramebuffers(n: Int, framebuffers: Long)

    fun glColorMaski(buf: Int, r: Boolean, g: Boolean, b: Boolean, a: Boolean)

    fun glEnablei(cap: Int, index: Int)
    fun glDisablei(cap: Int, index: Int)
    fun glIsEnabledi(cap: Int, index: Int): Boolean

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