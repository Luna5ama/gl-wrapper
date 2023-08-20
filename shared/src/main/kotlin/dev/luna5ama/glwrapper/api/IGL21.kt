@file:JvmName("GL21")

package dev.luna5ama.glwrapper.api

interface IGL21 : GLBase {
    companion object {
        internal const val GL_FLOAT_MAT2x3 = 0x8B65
        internal const val GL_FLOAT_MAT2x4 = 0x8B66
        internal const val GL_FLOAT_MAT3x2 = 0x8B67
        internal const val GL_FLOAT_MAT3x4 = 0x8B68
        internal const val GL_FLOAT_MAT4x2 = 0x8B69
        internal const val GL_FLOAT_MAT4x3 = 0x8B6A

        internal const val GL_PIXEL_PACK_BUFFER = 0x88EB
        internal const val GL_PIXEL_UNPACK_BUFFER = 0x88EC

        internal const val GL_PIXEL_PACK_BUFFER_BINDING = 0x88ED
        internal const val GL_PIXEL_UNPACK_BUFFER_BINDING = 0x88EF

        internal const val GL_SRGB = 0x8C40
        internal const val GL_SRGB8 = 0x8C41
        internal const val GL_SRGB_ALPHA = 0x8C42
        internal const val GL_SRGB8_ALPHA8 = 0x8C43
        internal const val GL_COMPRESSED_SRGB = 0x8C48
        internal const val GL_COMPRESSED_SRGB_ALPHA = 0x8C49
    }
}

abstract class PatchedGL21(protected val delegate: IGL21) : IGL21 by delegate