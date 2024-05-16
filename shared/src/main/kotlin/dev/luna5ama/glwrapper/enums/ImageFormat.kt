package dev.luna5ama.glwrapper.enums

import dev.luna5ama.glwrapper.api.*
import kotlin.reflect.KClass

@Suppress("ClassName")
sealed interface ImageFormat : GLEnum {
    val channels: Int

    sealed interface TextureViewAliasing : ImageFormat {
        val viewClass: KClass<out TextureViewAliasing>

        sealed interface UC_128Bit : TextureViewAliasing {
            override val viewClass
                get() = UC_128Bit::class
        }

        sealed interface UC_96Bit : TextureViewAliasing {
            override val viewClass
                get() = UC_96Bit::class
        }

        sealed interface UC_64Bit : TextureViewAliasing {
            override val viewClass
                get() = UC_64Bit::class
        }

        sealed interface UC_48Bit : TextureViewAliasing {
            override val viewClass
                get() = UC_48Bit::class
        }

        sealed interface UC_32Bit : TextureViewAliasing {
            override val viewClass
                get() = UC_32Bit::class
        }

        sealed interface UC_24Bit : TextureViewAliasing {
            override val viewClass
                get() = UC_24Bit::class
        }

        sealed interface UC_16Bit : TextureViewAliasing {
            override val viewClass
                get() = UC_16Bit::class
        }

        sealed interface UC_8Bit : TextureViewAliasing {
            override val viewClass
                get() = UC_8Bit::class
        }

        sealed interface RGTC1_RED : TextureViewAliasing {
            override val viewClass
                get() = RGTC1_RED::class
        }

        sealed interface RGTC2_RG : TextureViewAliasing {
            override val viewClass
                get() = RGTC2_RG::class
        }

        sealed interface BPTC_UNORM : TextureViewAliasing {
            override val viewClass
                get() = BPTC_UNORM::class
        }

        sealed interface BPTC_FLOAT : TextureViewAliasing {
            override val viewClass
                get() = BPTC_FLOAT::class
        }
    }

    sealed interface Sized : ImageFormat
    sealed interface Base : ImageFormat

    sealed interface Uncompressed : ImageFormat, Sized {
        val rBits: Int
        val gBits: Int
        val bBits: Int
        val aBits: Int
        val totalBits: Int
            get() = rBits + gBits + bBits + aBits
    }

    sealed interface Compressed : ImageFormat, Sized {
        sealed interface Generic : Compressed
        sealed interface Specific : Compressed
        sealed interface RGTC : Specific
        sealed interface BPTC : Specific
        sealed interface EAC : Specific
        sealed interface ETC2 : Specific
        sealed interface S3TC : Specific {
            sealed interface DXT1 : S3TC
            sealed interface DXT3 : S3TC
            sealed interface DXT5 : S3TC
        }
    }

    sealed interface Float : Sized
    sealed interface UnsignedNormalized : Sized
    sealed interface SignedNormalized : Sized
    sealed interface UnsignedInteger : Sized
    sealed interface SignedInteger : Sized

    sealed interface Color : ImageFormat

    sealed interface Depth : ImageFormat {
        val depthBits: Int
        override val channels: Int
            get() = 1
    }

    sealed interface Stencil : ImageFormat {
        val stencilBits: Int
        override val channels: Int
            get() = 1
    }

    sealed interface DepthStencil : Depth, Stencil {
        override val channels: Int
            get() = 2
    }

    sealed class R(override val value: Int) : Color {
        override val channels get() = 1

        companion object : R(GL_RED), Base
    }

    sealed class RG(override val value: Int) : Color {
        override val channels get() = 2

        companion object : RG(GL_RG), Base
    }

    sealed class RGB(override val value: Int) : Color {
        override val channels get() = 3

        companion object : RGB(GL_RGB), Base
    }

    sealed class RGBA(override val value: Int) : Color {
        override val channels get() = 4

        companion object : RGBA(GL_RGBA), Base
    }

    data object R8_UN : R(GL_R8), Uncompressed, TextureViewAliasing.UC_8Bit {
        override val rBits = 8
        override val gBits = 0
        override val bBits = 0
        override val aBits = 0
    }

    data object R8_SN : R(GL_R8_SNORM), Uncompressed, SignedNormalized, TextureViewAliasing.UC_8Bit {
        override val rBits = 8
        override val gBits = 0
        override val bBits = 0
        override val aBits = 0
    }

    data object R8_UI : R(GL_R8UI), Uncompressed, UnsignedInteger, TextureViewAliasing.UC_8Bit {
        override val rBits = 8
        override val gBits = 0
        override val bBits = 0
        override val aBits = 0
    }

    data object R8_SI : R(GL_R8I), Uncompressed, SignedInteger, TextureViewAliasing.UC_8Bit {
        override val rBits = 8
        override val gBits = 0
        override val bBits = 0
        override val aBits = 0
    }

    data object R16_UN : R(GL_R16), Uncompressed, UnsignedNormalized, TextureViewAliasing.UC_16Bit {
        override val rBits = 16
        override val gBits = 0
        override val bBits = 0
        override val aBits = 0
    }

    data object R16_SN : R(GL_R16_SNORM), Uncompressed, SignedNormalized, TextureViewAliasing.UC_16Bit {
        override val rBits = 16
        override val gBits = 0
        override val bBits = 0
        override val aBits = 0
    }

    data object R16_UI : R(GL_R16UI), Uncompressed, UnsignedInteger, TextureViewAliasing.UC_16Bit {
        override val rBits = 16
        override val gBits = 0
        override val bBits = 0
        override val aBits = 0
    }

    data object R16_SI : R(GL_R16I), Uncompressed, SignedInteger, TextureViewAliasing.UC_16Bit {
        override val rBits = 16
        override val gBits = 0
        override val bBits = 0
        override val aBits = 0
    }

    data object R16_F : R(GL_R16F), Uncompressed, Float, TextureViewAliasing.UC_16Bit {
        override val rBits = 16
        override val gBits = 0
        override val bBits = 0
        override val aBits = 0
    }

    data object R32_UI : R(GL_R32UI), Uncompressed, UnsignedInteger, TextureViewAliasing.UC_32Bit {
        override val rBits = 32
        override val gBits = 0
        override val bBits = 0
        override val aBits = 0
    }

    data object R32_SI : R(GL_R32I), Uncompressed, SignedInteger, TextureViewAliasing.UC_32Bit {
        override val rBits = 32
        override val gBits = 0
        override val bBits = 0
        override val aBits = 0
    }

    data object R32_F : R(GL_R32F), Uncompressed, Float, TextureViewAliasing.UC_32Bit {
        override val rBits = 32
        override val gBits = 0
        override val bBits = 0
        override val aBits = 0
    }

    data object R8_G8_UN : RG(GL_RG8), Uncompressed, UnsignedNormalized, TextureViewAliasing.UC_16Bit {
        override val rBits = 8
        override val gBits = 8
        override val bBits = 0
        override val aBits = 0
    }

    data object R8_G8_SN : RG(GL_RG8_SNORM), Uncompressed, SignedNormalized, TextureViewAliasing.UC_16Bit {
        override val rBits = 8
        override val gBits = 8
        override val bBits = 0
        override val aBits = 0
    }

    data object R8_G8_UI : RG(GL_RG8UI), Uncompressed, UnsignedInteger, TextureViewAliasing.UC_16Bit {
        override val rBits = 8
        override val gBits = 8
        override val bBits = 0
        override val aBits = 0
    }

    data object R8_G8_SI : RG(GL_RG8I), Uncompressed, SignedInteger, TextureViewAliasing.UC_16Bit {
        override val rBits = 8
        override val gBits = 8
        override val bBits = 0
        override val aBits = 0
    }

    data object R16_G16_UN : RG(GL_RG16), Uncompressed, UnsignedNormalized, TextureViewAliasing.UC_32Bit {
        override val rBits = 16
        override val gBits = 16
        override val bBits = 0
        override val aBits = 0
    }

    data object R16_G16_SN : RG(GL_RG16_SNORM), Uncompressed, SignedNormalized, TextureViewAliasing.UC_32Bit {
        override val rBits = 16
        override val gBits = 16
        override val bBits = 0
        override val aBits = 0
    }

    data object R16_G16_UI : RG(GL_RG16UI), Uncompressed, UnsignedInteger, TextureViewAliasing.UC_32Bit {
        override val rBits = 16
        override val gBits = 16
        override val bBits = 0
        override val aBits = 0
    }

    data object R16_G16_SI : RG(GL_RG16I), Uncompressed, SignedInteger, TextureViewAliasing.UC_32Bit {
        override val rBits = 16
        override val gBits = 16
        override val bBits = 0
        override val aBits = 0
    }

    data object R16_G16_F : RG(GL_RG16F), Uncompressed, Float, TextureViewAliasing.UC_32Bit {
        override val rBits = 16
        override val gBits = 16
        override val bBits = 0
        override val aBits = 0
    }

    data object R32_G32_UI : RG(GL_RG32UI), Uncompressed, UnsignedInteger, TextureViewAliasing.UC_64Bit {
        override val rBits = 32
        override val gBits = 32
        override val bBits = 0
        override val aBits = 0
    }

    data object R32G_32_SI : RG(GL_RG32I), Uncompressed, SignedInteger, TextureViewAliasing.UC_64Bit {
        override val rBits = 32
        override val gBits = 32
        override val bBits = 0
        override val aBits = 0
    }

    data object R32_G32_F : RG(GL_RG32F), Uncompressed, Float, TextureViewAliasing.UC_64Bit {
        override val rBits = 32
        override val gBits = 32
        override val bBits = 0
        override val aBits = 0
    }

    data object R8_G8_B8_UN : RGB(GL_RGB8), Uncompressed, UnsignedNormalized, TextureViewAliasing.UC_24Bit {
        override val rBits = 8
        override val gBits = 8
        override val bBits = 8
        override val aBits = 0
    }

    data object R8_G8_B8_SN : RGB(GL_RGB8_SNORM), Uncompressed, SignedNormalized, TextureViewAliasing.UC_24Bit {
        override val rBits = 8
        override val gBits = 8
        override val bBits = 8
        override val aBits = 0
    }

    data object R8_G8_B8_UI : RGB(GL_RGB8UI), Uncompressed, UnsignedInteger, TextureViewAliasing.UC_24Bit {
        override val rBits = 8
        override val gBits = 8
        override val bBits = 8
        override val aBits = 0
    }

    data object R8_G8_B8_SI : RGB(GL_RGB8I), Uncompressed, SignedInteger, TextureViewAliasing.UC_24Bit {
        override val rBits = 8
        override val gBits = 8
        override val bBits = 8
        override val aBits = 0
    }

    data object R16_G16_B16UN : RGB(GL_RGB16), Uncompressed, UnsignedNormalized, TextureViewAliasing.UC_48Bit {
        override val rBits = 16
        override val gBits = 16
        override val bBits = 16
        override val aBits = 0
    }

    data object R16_G16_B16SN : RGB(GL_RGB16_SNORM), Uncompressed, SignedNormalized, TextureViewAliasing.UC_48Bit {
        override val rBits = 16
        override val gBits = 16
        override val bBits = 16
        override val aBits = 0
    }

    data object R16_G16_B16_UI : RGB(GL_RGB16UI), Uncompressed, UnsignedInteger, TextureViewAliasing.UC_48Bit {
        override val rBits = 16
        override val gBits = 16
        override val bBits = 16
        override val aBits = 0
    }

    data object R16_G16_B16_SI : RGB(GL_RGB16I), Uncompressed, SignedInteger, TextureViewAliasing.UC_48Bit {
        override val rBits = 16
        override val gBits = 16
        override val bBits = 16
        override val aBits = 0
    }

    data object R16_G16_B16_F : RGB(GL_RGB16F), Uncompressed, Float, TextureViewAliasing.UC_48Bit {
        override val rBits = 16
        override val gBits = 16
        override val bBits = 16
        override val aBits = 0
    }

    data object R32G32B32UI : RGB(GL_RGB32UI), Uncompressed, UnsignedInteger, TextureViewAliasing.UC_96Bit {
        override val rBits = 32
        override val gBits = 32
        override val bBits = 32
        override val aBits = 0
    }

    data object R32G32B32SI : RGB(GL_RGB32I), Uncompressed, SignedInteger, TextureViewAliasing.UC_96Bit {
        override val rBits = 32
        override val gBits = 32
        override val bBits = 32
        override val aBits = 0
    }

    data object R32G32B32F : RGB(GL_RGB32F), Uncompressed, Float, TextureViewAliasing.UC_96Bit {
        override val rBits = 32
        override val gBits = 32
        override val bBits = 32
        override val aBits = 0
    }

    data object R8_G8_B8_A8_UN : RGBA(GL_RGBA8), Uncompressed, UnsignedNormalized, TextureViewAliasing.UC_32Bit {
        override val rBits = 8
        override val gBits = 8
        override val bBits = 8
        override val aBits = 8
    }

    data object R8_G8_B8_A8_SN : RGBA(GL_RGBA8_SNORM), Uncompressed, SignedNormalized, TextureViewAliasing.UC_32Bit {
        override val rBits = 8
        override val gBits = 8
        override val bBits = 8
        override val aBits = 8
    }

    data object R8_G8_B8_A8_UI : RGBA(GL_RGBA8UI), Uncompressed, UnsignedInteger, TextureViewAliasing.UC_32Bit {
        override val rBits = 8
        override val gBits = 8
        override val bBits = 8
        override val aBits = 8
    }

    data object R8_G8_B8_A8_SI : RGBA(GL_RGBA8I), Uncompressed, SignedInteger, TextureViewAliasing.UC_32Bit {
        override val rBits = 8
        override val gBits = 8
        override val bBits = 8
        override val aBits = 8
    }

    data object R16_G16_B16_A16_UN : RGBA(GL_RGBA16), Uncompressed, UnsignedNormalized, TextureViewAliasing.UC_64Bit {
        override val rBits = 16
        override val gBits = 16
        override val bBits = 16
        override val aBits = 16
    }

    data object R16_G16_B16_A16_SN : RGBA(GL_RGBA16_SNORM), Uncompressed, SignedNormalized,
        TextureViewAliasing.UC_64Bit {
        override val rBits = 16
        override val gBits = 16
        override val bBits = 16
        override val aBits = 16
    }

    data object R16_G16_B16_A16_UI : RGBA(GL_RGBA16UI), Uncompressed, UnsignedInteger, TextureViewAliasing.UC_64Bit {
        override val rBits = 16
        override val gBits = 16
        override val bBits = 16
        override val aBits = 16
    }

    data object R16_G16_B16_A16_SI : RGBA(GL_RGBA16I), Uncompressed, SignedInteger, TextureViewAliasing.UC_64Bit {
        override val rBits = 16
        override val gBits = 16
        override val bBits = 16
        override val aBits = 16
    }

    data object R16_G16_B16_A16_F : RGBA(GL_RGBA16F), Uncompressed, Float, TextureViewAliasing.UC_64Bit {
        override val rBits = 16
        override val gBits = 16
        override val bBits = 16
        override val aBits = 16
    }

    data object R32_G32_B32_A32_UI : RGBA(GL_RGBA32UI), Uncompressed, UnsignedInteger, TextureViewAliasing.UC_128Bit {
        override val rBits = 32
        override val gBits = 32
        override val bBits = 32
        override val aBits = 32
    }

    data object R32_G32_B32_A32_SI : RGBA(GL_RGBA32I), Uncompressed, SignedInteger, TextureViewAliasing.UC_128Bit {
        override val rBits = 32
        override val gBits = 32
        override val bBits = 32
        override val aBits = 32
    }

    data object R32_G32_B32_A32_F : RGBA(GL_RGBA32F), Uncompressed, Float, TextureViewAliasing.UC_128Bit {
        override val rBits = 32
        override val gBits = 32
        override val bBits = 32
        override val aBits = 32
    }

    data object R3_G3_B2_UN : RGB(GL_R3_G3_B2), Uncompressed, UnsignedNormalized {
        override val rBits = 3
        override val gBits = 3
        override val bBits = 2
        override val aBits = 0
    }

    data object R4_G4_B4_UN : RGB(GL_RGB4), Uncompressed, UnsignedNormalized {
        override val rBits = 4
        override val gBits = 4
        override val bBits = 4
        override val aBits = 0
    }

    data object R5_G5_B5_UN : RGB(GL_RGB5), Uncompressed, UnsignedNormalized {
        override val rBits = 5
        override val gBits = 5
        override val bBits = 5
        override val aBits = 0
    }

    data object R5_G6_B5_UN : RGB(GL_RGB565), Uncompressed, UnsignedNormalized {
        override val rBits = 5
        override val gBits = 6
        override val bBits = 5
        override val aBits = 0
    }

    data object R10_G10_B10_UN : RGB(GL_RGB10), Uncompressed, UnsignedNormalized {
        override val rBits = 10
        override val gBits = 10
        override val bBits = 10
        override val aBits = 0
    }

    data object R12_G12_B12_UN : RGB(GL_RGB12), Uncompressed, UnsignedNormalized {
        override val rBits = 12
        override val gBits = 12
        override val bBits = 12
        override val aBits = 0
    }

    data object R2_G2_B2_A2_UN : RGBA(GL_RGBA2), Uncompressed, UnsignedNormalized {
        override val rBits = 2
        override val gBits = 2
        override val bBits = 2
        override val aBits = 2
    }

    data object R4_G4_B4_A4_UN : RGBA(GL_RGBA4), Uncompressed, UnsignedNormalized {
        override val rBits = 4
        override val gBits = 4
        override val bBits = 4
        override val aBits = 4
    }

    data object R5_G5_B5_A1_UN : RGBA(GL_RGB5_A1), Uncompressed, UnsignedNormalized {
        override val rBits = 5
        override val gBits = 5
        override val bBits = 5
        override val aBits = 1
    }

    data object R10_G10_B10_A2_UN : RGBA(GL_RGB10_A2), Uncompressed, UnsignedNormalized, TextureViewAliasing.UC_32Bit {
        override val rBits = 10
        override val gBits = 10
        override val bBits = 10
        override val aBits = 2
    }

    data object R10_G10_B10_A2_UI : RGBA(GL_RGB10_A2UI), Uncompressed, UnsignedInteger, TextureViewAliasing.UC_32Bit {
        override val rBits = 10
        override val gBits = 10
        override val bBits = 10
        override val aBits = 2
    }

    data object R8_G8_B8_SRGB : RGB(GL_SRGB8), Uncompressed, UnsignedNormalized, TextureViewAliasing.UC_24Bit {
        override val rBits = 8
        override val gBits = 8
        override val bBits = 8
        override val aBits = 0
    }

    data object R8_G8_B8_A8_SRGB : RGBA(GL_SRGB8_ALPHA8), Uncompressed, UnsignedNormalized,
        TextureViewAliasing.UC_32Bit {
        override val rBits = 8
        override val gBits = 8
        override val bBits = 8
        override val aBits = 8
    }

    data object R11_G11_B10_F : RGB(GL_R11F_G11F_B10F), Uncompressed, Float, TextureViewAliasing.UC_32Bit {
        override val rBits = 11
        override val gBits = 11
        override val bBits = 10
        override val aBits = 0
    }

    data object R9_G9_B9_E5 : RGB(GL_RGB9_E5), Uncompressed, Float {
        override val rBits = 9
        override val gBits = 9
        override val bBits = 9
        override val aBits = 5
    }

    data object R_UN_C : R(GL_COMPRESSED_RED), Compressed, Compressed.Generic, UnsignedNormalized
    data object RG_UN_C : RG(GL_COMPRESSED_RG), Compressed, Compressed.Generic, UnsignedNormalized
    data object RGB_UN_C : RGB(GL_COMPRESSED_RGB), Compressed, Compressed.Generic, UnsignedNormalized
    data object RGBA_UN_C : RGBA(GL_COMPRESSED_RGBA), Compressed, Compressed.Generic, UnsignedNormalized
    data object RGB_SRGB_C : RGB(GL_COMPRESSED_SRGB), Compressed, Compressed.Generic, UnsignedNormalized
    data object RGBA_SRGB_C : RGBA(GL_COMPRESSED_SRGB_ALPHA), Compressed, Compressed.Generic, UnsignedNormalized

    data object R_UN_RGTC1 : R(GL_COMPRESSED_RED_RGTC1), Compressed.RGTC, UnsignedNormalized,
        TextureViewAliasing.RGTC1_RED

    data object R_SN_RGTC1 : R(GL_COMPRESSED_SIGNED_RED_RGTC1), Compressed.RGTC, SignedNormalized,
        TextureViewAliasing.RGTC1_RED

    data object RG_UN_RGTC2 : RG(GL_COMPRESSED_RG_RGTC2), Compressed.RGTC, UnsignedNormalized,
        TextureViewAliasing.RGTC2_RG

    data object RG_SN_RGTC2 : RG(GL_COMPRESSED_SIGNED_RG_RGTC2), Compressed.RGTC, SignedNormalized,
        TextureViewAliasing.RGTC2_RG

    data object RGBA_UN_BPTC : RGBA(GL_COMPRESSED_RGBA_BPTC_UNORM), Compressed.BPTC, UnsignedNormalized,
        TextureViewAliasing.BPTC_UNORM

    data object RGBA_SRGB_BPTC : RGBA(GL_COMPRESSED_SRGB_ALPHA_BPTC_UNORM), Compressed.BPTC, UnsignedNormalized,
        TextureViewAliasing.BPTC_UNORM

    data object RGB_SF_BPTC : RGB(GL_COMPRESSED_RGB_BPTC_SIGNED_FLOAT), Compressed.BPTC, Float,
        TextureViewAliasing.BPTC_FLOAT

    data object RGB_UF_BPTC : RGB(GL_COMPRESSED_RGB_BPTC_UNSIGNED_FLOAT), Compressed.BPTC, Float,
        TextureViewAliasing.BPTC_FLOAT

    data object RGB_S3TC_DXT1 : RGB(GL_COMPRESSED_RGB_S3TC_DXT1_EXT), Compressed.S3TC, Compressed.S3TC.DXT1,
        UnsignedNormalized

    data object RGBA_S3TC_DXT1 : RGBA(GL_COMPRESSED_RGBA_S3TC_DXT1_EXT), Compressed.S3TC, Compressed.S3TC.DXT1,
        UnsignedNormalized

    data object RGBA_S3TC_DXT3 : RGBA(GL_COMPRESSED_RGBA_S3TC_DXT3_EXT), Compressed.S3TC, Compressed.S3TC.DXT3,
        UnsignedNormalized

    data object RGBA_S3TC_DXT5 : RGBA(GL_COMPRESSED_RGBA_S3TC_DXT5_EXT), Compressed.S3TC, Compressed.S3TC.DXT5,
        UnsignedNormalized

    data object Depth16 : Depth, Sized, UnsignedNormalized {
        override val value = GL_DEPTH_COMPONENT16
        override val depthBits = 16
    }

    data object Depth24 : Depth, Sized, UnsignedNormalized {
        override val value = GL_DEPTH_COMPONENT24
        override val depthBits = 24
    }

    data object Depth32 : Depth, Sized, UnsignedNormalized {
        override val value = GL_DEPTH_COMPONENT32
        override val depthBits = 32
    }

    data object Depth32F : Depth, Sized, Float {
        override val value = GL_DEPTH_COMPONENT32F
        override val depthBits = 32
    }

    data object Depth32FNV : Depth, Sized, Float {
        override val value = GL_DEPTH_COMPONENT32F_NV
        override val depthBits = 32
    }

    data object Depth24Stencil8 : DepthStencil, Sized, UnsignedNormalized {
        override val value = GL_DEPTH24_STENCIL8
        override val depthBits = 24
        override val stencilBits = 8
    }

    data object Depth32FStencil8 : DepthStencil, Sized, Float {
        override val value = GL_DEPTH32F_STENCIL8
        override val depthBits = 32
        override val stencilBits = 8
    }

    data object Depth32FStencil8NV : DepthStencil, Sized, Float {
        override val value = GL_DEPTH32F_STENCIL8_NV
        override val depthBits = 32
        override val stencilBits = 8
    }

    data object Stencil8 : Stencil, Sized {
        override val value = GL_STENCIL_INDEX8
        override val stencilBits = 8
    }
}