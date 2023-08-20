@file:JvmName("GL12")

package dev.luna5ama.glwrapper.api

interface IGL12 : GLBase {
    companion object {
        internal const val GL_ALIASED_LINE_WIDTH_RANGE = 0x846E
        internal const val GL_SMOOTH_POINT_SIZE_RANGE = 0xB12
        internal const val GL_SMOOTH_POINT_SIZE_GRANULARITY = 0xB13
        internal const val GL_SMOOTH_LINE_WIDTH_RANGE = 0xB22
        internal const val GL_SMOOTH_LINE_WIDTH_GRANULARITY = 0xB23

        internal const val GL_TEXTURE_BINDING_3D = 0x806A

        internal const val GL_PACK_SKIP_IMAGES = 0x806B
        internal const val GL_PACK_IMAGE_HEIGHT = 0x806C
        internal const val GL_UNPACK_SKIP_IMAGES = 0x806D
        internal const val GL_UNPACK_IMAGE_HEIGHT = 0x806E

        internal const val GL_TEXTURE_3D = 0x806F

        internal const val GL_PROXY_TEXTURE_3D = 0x8070

        internal const val GL_TEXTURE_DEPTH = 0x8071

        internal const val GL_TEXTURE_WRAP_R = 0x8072
        internal const val GL_MAX_3D_TEXTURE_SIZE = 0x8073


        internal const val GL_BGR = 0x80E0
        internal const val GL_BGRA = 0x80E1

        internal const val GL_UNSIGNED_BYTE_3_3_2 = 0x8032
        internal const val GL_UNSIGNED_BYTE_2_3_3_REV = 0x8362
        internal const val GL_UNSIGNED_SHORT_5_6_5 = 0x8363
        internal const val GL_UNSIGNED_SHORT_5_6_5_REV = 0x8364
        internal const val GL_UNSIGNED_SHORT_4_4_4_4 = 0x8033
        internal const val GL_UNSIGNED_SHORT_4_4_4_4_REV = 0x8365
        internal const val GL_UNSIGNED_SHORT_5_5_5_1 = 0x8034
        internal const val GL_UNSIGNED_SHORT_1_5_5_5_REV = 0x8366
        internal const val GL_UNSIGNED_INT_8_8_8_8 = 0x8035
        internal const val GL_UNSIGNED_INT_8_8_8_8_REV = 0x8367
        internal const val GL_UNSIGNED_INT_10_10_10_2 = 0x8036
        internal const val GL_UNSIGNED_INT_2_10_10_10_REV = 0x8368

        internal const val GL_CLAMP_TO_EDGE = 0x812F

        internal const val GL_TEXTURE_MIN_LOD = 0x813A
        internal const val GL_TEXTURE_MAX_LOD = 0x813B
        internal const val GL_TEXTURE_BASE_LEVEL = 0x813C
        internal const val GL_TEXTURE_MAX_LEVEL = 0x813D

        internal const val GL_MAX_ELEMENTS_VERTICES = 0x80E8
        internal const val GL_MAX_ELEMENTS_INDICES = 0x80E9
    }

    fun glDrawRangeElements(mode: Int, start: Int, end: Int, count: Int, type: Int, indices: Long)
}
abstract class PatchedGL12(protected val delegate: IGL12) : IGL12 by delegate