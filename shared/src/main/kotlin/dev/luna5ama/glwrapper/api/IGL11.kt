@file:JvmName("GL11")

package dev.luna5ama.glwrapper.api

import dev.luna5ama.kmogus.Ptr
import dev.luna5ama.kmogus.ensureCapacity
import dev.luna5ama.kmogus.memcpy

interface IGL11 : GLBase {
    companion object {
        internal const val GL_NEVER = 0x200
        internal const val GL_LESS = 0x201
        internal const val GL_EQUAL = 0x202
        internal const val GL_LEQUAL = 0x203
        internal const val GL_GREATER = 0x204
        internal const val GL_NOTEQUAL = 0x205
        internal const val GL_GEQUAL = 0x206
        internal const val GL_ALWAYS = 0x207

        internal const val GL_DEPTH_BUFFER_BIT = 0x100
        internal const val GL_STENCIL_BUFFER_BIT = 0x400
        internal const val GL_COLOR_BUFFER_BIT = 0x4000

        internal const val GL_POINTS = 0x0
        internal const val GL_LINES = 0x1
        internal const val GL_LINE_LOOP = 0x2
        internal const val GL_LINE_STRIP = 0x3
        internal const val GL_TRIANGLES = 0x4
        internal const val GL_TRIANGLE_STRIP = 0x5
        internal const val GL_TRIANGLE_FAN = 0x6
        internal const val GL_QUADS = 0x7

        internal const val GL_ZERO = 0
        internal const val GL_ONE = 1
        internal const val GL_SRC_COLOR = 0x300
        internal const val GL_ONE_MINUS_SRC_COLOR = 0x301
        internal const val GL_SRC_ALPHA = 0x302
        internal const val GL_ONE_MINUS_SRC_ALPHA = 0x303
        internal const val GL_DST_ALPHA = 0x304
        internal const val GL_ONE_MINUS_DST_ALPHA = 0x305

        internal const val GL_DST_COLOR = 0x306
        internal const val GL_ONE_MINUS_DST_COLOR = 0x307
        internal const val GL_SRC_ALPHA_SATURATE = 0x308

        internal const val GL_TRUE = 1
        internal const val GL_FALSE = 0

        internal const val GL_BYTE = 0x1400
        internal const val GL_UNSIGNED_BYTE = 0x1401
        internal const val GL_SHORT = 0x1402
        internal const val GL_UNSIGNED_SHORT = 0x1403
        internal const val GL_INT = 0x1404
        internal const val GL_UNSIGNED_INT = 0x1405
        internal const val GL_FLOAT = 0x1406
        internal const val GL_DOUBLE = 0x140A

        internal const val GL_NONE = 0
        internal const val GL_FRONT_LEFT = 0x400
        internal const val GL_FRONT_RIGHT = 0x401
        internal const val GL_BACK_LEFT = 0x402
        internal const val GL_BACK_RIGHT = 0x403
        internal const val GL_FRONT = 0x404
        internal const val GL_BACK = 0x405
        internal const val GL_LEFT = 0x406
        internal const val GL_RIGHT = 0x407
        internal const val GL_FRONT_AND_BACK = 0x408

        internal const val GL_NO_ERROR = 0
        internal const val GL_INVALID_ENUM = 0x500
        internal const val GL_INVALID_VALUE = 0x501
        internal const val GL_INVALID_OPERATION = 0x502
        internal const val GL_STACK_OVERFLOW = 0x503
        internal const val GL_STACK_UNDERFLOW = 0x504
        internal const val GL_OUT_OF_MEMORY = 0x505

        internal const val GL_CW = 0x900
        internal const val GL_CCW = 0x901

        internal const val GL_POINT_SIZE = 0xB11
        internal const val GL_POINT_SIZE_RANGE = 0xB12
        internal const val GL_POINT_SIZE_GRANULARITY = 0xB13
        internal const val GL_LINE_SMOOTH = 0xB20
        internal const val GL_LINE_WIDTH = 0xB21
        internal const val GL_LINE_WIDTH_RANGE = 0xB22
        internal const val GL_LINE_WIDTH_GRANULARITY = 0xB23
        internal const val GL_POLYGON_MODE = 0xB40
        internal const val GL_POLYGON_SMOOTH = 0xB41
        internal const val GL_CULL_FACE = 0xB44
        internal const val GL_CULL_FACE_MODE = 0xB45
        internal const val GL_FRONT_FACE = 0xB46
        internal const val GL_DEPTH_RANGE = 0xB70
        internal const val GL_DEPTH_TEST = 0xB71
        internal const val GL_DEPTH_WRITEMASK = 0xB72
        internal const val GL_DEPTH_CLEAR_VALUE = 0xB73
        internal const val GL_DEPTH_FUNC = 0xB74
        internal const val GL_STENCIL_TEST = 0xB90
        internal const val GL_STENCIL_CLEAR_VALUE = 0xB91
        internal const val GL_STENCIL_FUNC = 0xB92
        internal const val GL_STENCIL_VALUE_MASK = 0xB93
        internal const val GL_STENCIL_FAIL = 0xB94
        internal const val GL_STENCIL_PASS_DEPTH_FAIL = 0xB95
        internal const val GL_STENCIL_PASS_DEPTH_PASS = 0xB96
        internal const val GL_STENCIL_REF = 0xB97
        internal const val GL_STENCIL_WRITEMASK = 0xB98
        internal const val GL_VIEWPORT = 0xBA2
        internal const val GL_DITHER = 0xBD0
        internal const val GL_BLEND_DST = 0xBE0
        internal const val GL_BLEND_SRC = 0xBE1
        internal const val GL_BLEND = 0xBE2
        internal const val GL_LOGIC_OP_MODE = 0xBF0
        internal const val GL_COLOR_LOGIC_OP = 0xBF2
        internal const val GL_DRAW_BUFFER = 0xC01
        internal const val GL_READ_BUFFER = 0xC02
        internal const val GL_SCISSOR_BOX = 0xC10
        internal const val GL_SCISSOR_TEST = 0xC11
        internal const val GL_COLOR_CLEAR_VALUE = 0xC22
        internal const val GL_COLOR_WRITEMASK = 0xC23
        internal const val GL_DOUBLEBUFFER = 0xC32
        internal const val GL_STEREO = 0xC33
        internal const val GL_LINE_SMOOTH_HINT = 0xC52
        internal const val GL_POLYGON_SMOOTH_HINT = 0xC53
        internal const val GL_UNPACK_SWAP_BYTES = 0xCF0
        internal const val GL_UNPACK_LSB_FIRST = 0xCF1
        internal const val GL_UNPACK_ROW_LENGTH = 0xCF2
        internal const val GL_UNPACK_SKIP_ROWS = 0xCF3
        internal const val GL_UNPACK_SKIP_PIXELS = 0xCF4
        internal const val GL_UNPACK_ALIGNMENT = 0xCF5
        internal const val GL_PACK_SWAP_BYTES = 0xD00
        internal const val GL_PACK_LSB_FIRST = 0xD01
        internal const val GL_PACK_ROW_LENGTH = 0xD02
        internal const val GL_PACK_SKIP_ROWS = 0xD03
        internal const val GL_PACK_SKIP_PIXELS = 0xD04
        internal const val GL_PACK_ALIGNMENT = 0xD05
        internal const val GL_MAX_TEXTURE_SIZE = 0xD33
        internal const val GL_MAX_VIEWPORT_DIMS = 0xD3A
        internal const val GL_SUBPIXEL_BITS = 0xD50
        internal const val GL_TEXTURE_1D = 0xDE0
        internal const val GL_TEXTURE_2D = 0xDE1

        internal const val GL_TEXTURE_WIDTH = 0x1000
        internal const val GL_TEXTURE_HEIGHT = 0x1001
        internal const val GL_TEXTURE_INTERNAL_FORMAT = 0x1003
        internal const val GL_TEXTURE_BORDER_COLOR = 0x1004

        internal const val GL_DONT_CARE = 0x1100
        internal const val GL_FASTEST = 0x1101
        internal const val GL_NICEST = 0x1102

        internal const val GL_CLEAR = 0x1500
        internal const val GL_AND = 0x1501
        internal const val GL_AND_REVERSE = 0x1502
        internal const val GL_COPY = 0x1503
        internal const val GL_AND_INVERTED = 0x1504
        internal const val GL_NOOP = 0x1505
        internal const val GL_XOR = 0x1506
        internal const val GL_OR = 0x1507
        internal const val GL_NOR = 0x1508
        internal const val GL_EQUIV = 0x1509
        internal const val GL_INVERT = 0x150A
        internal const val GL_OR_REVERSE = 0x150B
        internal const val GL_COPY_INVERTED = 0x150C
        internal const val GL_OR_INVERTED = 0x150D
        internal const val GL_NAND = 0x150E
        internal const val GL_SET = 0x150F

        internal const val GL_TEXTURE = 0x1702

        internal const val GL_COLOR = 0x1800
        internal const val GL_DEPTH = 0x1801
        internal const val GL_STENCIL = 0x1802

        internal const val GL_STENCIL_INDEX = 0x1901
        internal const val GL_DEPTH_COMPONENT = 0x1902
        internal const val GL_RED = 0x1903
        internal const val GL_GREEN = 0x1904
        internal const val GL_BLUE = 0x1905
        internal const val GL_ALPHA = 0x1906
        internal const val GL_RGB = 0x1907
        internal const val GL_RGBA = 0x1908

        internal const val GL_POINT = 0x1B00
        internal const val GL_LINE = 0x1B01
        internal const val GL_FILL = 0x1B02

        internal const val GL_KEEP = 0x1E00
        internal const val GL_REPLACE = 0x1E01
        internal const val GL_INCR = 0x1E02
        internal const val GL_DECR = 0x1E03

        internal const val GL_VENDOR = 0x1F00
        internal const val GL_RENDERER = 0x1F01
        internal const val GL_VERSION = 0x1F02
        internal const val GL_EXTENSIONS = 0x1F03

        internal const val GL_NEAREST = 0x2600
        internal const val GL_LINEAR = 0x2601

        internal const val GL_NEAREST_MIPMAP_NEAREST = 0x2700
        internal const val GL_LINEAR_MIPMAP_NEAREST = 0x2701
        internal const val GL_NEAREST_MIPMAP_LINEAR = 0x2702
        internal const val GL_LINEAR_MIPMAP_LINEAR = 0x2703

        internal const val GL_TEXTURE_MAG_FILTER = 0x2800
        internal const val GL_TEXTURE_MIN_FILTER = 0x2801
        internal const val GL_TEXTURE_WRAP_S = 0x2802
        internal const val GL_TEXTURE_WRAP_T = 0x2803

        internal const val GL_REPEAT = 0x2901

        internal const val GL_POLYGON_OFFSET_FACTOR = 0x8038
        internal const val GL_POLYGON_OFFSET_UNITS = 0x2A00
        internal const val GL_POLYGON_OFFSET_POINT = 0x2A01
        internal const val GL_POLYGON_OFFSET_LINE = 0x2A02
        internal const val GL_POLYGON_OFFSET_FILL = 0x8037

        internal const val GL_R3_G3_B2 = 0x2A10
        internal const val GL_RGB4 = 0x804F
        internal const val GL_RGB5 = 0x8050
        internal const val GL_RGB8 = 0x8051
        internal const val GL_RGB10 = 0x8052
        internal const val GL_RGB12 = 0x8053
        internal const val GL_RGB16 = 0x8054
        internal const val GL_RGBA2 = 0x8055
        internal const val GL_RGBA4 = 0x8056
        internal const val GL_RGB5_A1 = 0x8057
        internal const val GL_RGBA8 = 0x8058
        internal const val GL_RGB10_A2 = 0x8059
        internal const val GL_RGBA12 = 0x805A
        internal const val GL_RGBA16 = 0x805B
        internal const val GL_TEXTURE_RED_SIZE = 0x805C
        internal const val GL_TEXTURE_GREEN_SIZE = 0x805D
        internal const val GL_TEXTURE_BLUE_SIZE = 0x805E
        internal const val GL_TEXTURE_ALPHA_SIZE = 0x805F
        internal const val GL_PROXY_TEXTURE_1D = 0x8063
        internal const val GL_PROXY_TEXTURE_2D = 0x8064

        internal const val GL_TEXTURE_BINDING_1D = 0x8068
        internal const val GL_TEXTURE_BINDING_2D = 0x8069

        internal const val GL_VERTEX_ARRAY = 0x8074
    }

    fun glEnable(target: Int)
    fun glDisable(target: Int)

    fun glBlendFunc(sfactor: Int, dfactor: Int)

    fun glClear(mask: Int)
    fun glClearColor(red: Float, green: Float, blue: Float, alpha: Float)
    fun glClearDepth(depth: Double)
    fun glClearStencil(s: Int)

    fun glColorMask(red: Boolean, green: Boolean, blue: Boolean, alpha: Boolean)

    fun glCullFace(mode: Int)

    fun glDepthFunc(func: Int)
    fun glDepthMask(flag: Boolean)
    fun glDepthRange(zNear: Double, zFar: Double)

    fun glDrawArrays(mode: Int, first: Int, count: Int)

    fun glDrawBuffer(buf: Int)

    fun glDrawElements(mode: Int, count: Int, type: Int, indices: Long)

    fun glFinish()
    fun glFlush()

    fun glFrontFace(mode: Int)

    @Unsafe
    fun glDeleteTextures(n: Int, textures: Long)

    @Unsafe
    fun glGetBooleanv(pname: Int, params: Long)

    @Unsafe
    fun glGetIntegerv(pname: Int, params: Long)

    @Unsafe
    fun glGetFloatv(pname: Int, params: Long)

    @Unsafe
    fun glGetDoublev(pname: Int, params: Long)
    fun glGetError(): Int
    fun glGetString(name: Int): String?

    fun glHint(target: Int, mode: Int)

    fun glIsEnabled(cap: Int): Boolean
    fun glIsTexture(texture: Int): Boolean

    fun glLineWidth(width: Float)

    fun glPointSize(size: Float)

    fun glPolygonMode(face: Int, mode: Int)
    fun glPolygonOffset(factor: Float, units: Float)

    fun glReadBuffer(src: Int)

    @Unsafe
    fun glReadPixels(x: Int, y: Int, width: Int, height: Int, format: Int, type: Int, pixels: Long)

    fun glScissor(x: Int, y: Int, width: Int, height: Int)

    fun glStencilFunc(func: Int, ref: Int, mask: Int)
    fun glStencilMask(mask: Int)
    fun glStencilOp(fail: Int, zfail: Int, zpass: Int)

    fun glViewport(x: Int, y: Int, width: Int, height: Int)


    fun glDeleteTextures(n: Int, textures: Ptr) {
        glDeleteTextures(n, textures.address)
    }


    fun glDeleteTextures(texture: Int) {
        val ptr = tempArr.ptr
        ptr.setInt(texture)
        glDeleteTextures(1, ptr)
    }

    fun glDeleteTextures(texture1: Int, texture2: Int) {
        val ptr = tempArr.ptr
        ptr.setInt(texture1)
        ptr.setInt(4, texture2)
        glDeleteTextures(2, ptr)
    }

    fun glDeleteTextures(texture1: Int, texture2: Int, texture3: Int) {
        val ptr = tempArr.ptr
        ptr.setInt(texture1)
        ptr.setInt(4, texture2)
        ptr.setInt(8, texture3)
        glDeleteTextures(3, ptr)
    }

    fun glDeleteTextures(texture1: Int, texture2: Int, texture3: Int, texture4: Int) {
        val ptr = tempArr.ptr
        ptr.setInt(texture1)
        ptr.setInt(4, texture2)
        ptr.setInt(8, texture3)
        ptr.setInt(12, texture4)
        glDeleteTextures(4, ptr)
    }

    fun glDeleteTextures(vararg textures: Int) {
        tempArr.ensureCapacity(textures.size * 4L, false)
        val ptr = tempArr.ptr
        memcpy(textures, ptr, textures.size * 4L)
        glDeleteTextures(textures.size, ptr)
    }

    fun glDeleteTextures(textures: IntArray, offset: Int, length: Int) {
        tempArr.ensureCapacity(length * 4L, false)
        val ptr = tempArr.ptr
        memcpy(textures, offset * 4L, ptr, 0L, length * 4L)
        glDeleteTextures(length, ptr)
    }

    fun glGetBooleanv(pname: Int, params: Ptr) {
        glGetBooleanv(pname, params.address)
    }

    fun glGetBoolean(pname: Int): Boolean {
        val ptr = tempArr.ptr
        glGetBooleanv(pname, ptr)
        return ptr.getByte() != 0.toByte()
    }

    fun glGetIntegerv(pname: Int, params: Ptr) {
        glGetIntegerv(pname, params.address)
    }

    fun glGetInteger(pname: Int): Int {
        val ptr = tempArr.ptr
        glGetIntegerv(pname, ptr)
        return ptr.getInt()
    }

    fun glGetFloatv(pname: Int, params: Ptr) {
        glGetFloatv(pname, params.address)
    }

    fun glGetFloat(pname: Int): Float {
        val ptr = tempArr.ptr
        glGetFloatv(pname, ptr)
        return ptr.getFloat()
    }

    fun glGetDoublev(pname: Int, params: Ptr) {
        glGetDoublev(pname, params.address)
    }

    fun glGetDouble(pname: Int): Double {
        val ptr = tempArr.ptr
        glGetDoublev(pname, ptr)
        return ptr.getDouble()
    }

    fun glReadPixels(x: Int, y: Int, width: Int, height: Int, format: Int, type: Int, pixels: Ptr) {
        glReadPixels(x, y, width, height, format, type, pixels.address)
    }
}

abstract class PatchedGL11(protected val delegate: IGL11) : IGL11 by delegate
