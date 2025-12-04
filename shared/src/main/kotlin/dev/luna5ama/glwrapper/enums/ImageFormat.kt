package dev.luna5ama.glwrapper.enums

import dev.luna5ama.glwrapper.base.*
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
        val totalBits: Int
        val pixelFormat: Base
        val pixelType: Int
    }

    sealed interface UncompressedColor : Uncompressed {
        val rBits: Int
        val gBits: Int
        val bBits: Int
        val aBits: Int
        override val totalBits: Int
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

    sealed interface Depth : ImageFormat, Uncompressed {
        val depthBits: Int
        override val totalBits: Int
            get() = depthBits
        override val channels: Int
            get() = 1
    }

    sealed interface Stencil : ImageFormat, Uncompressed {
        val stencilBits: Int
        override val totalBits: Int
            get() = stencilBits
        override val channels: Int
            get() = 1
    }

    sealed interface DepthStencil : Depth, Stencil, Uncompressed {
        override val totalBits: Int
            get() = depthBits + stencilBits
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
        final override val channels get() = 4

        companion object : RGBA(GL_RGBA), Base
    }

    data object R8_UN : R(GL_R8), UncompressedColor, TextureViewAliasing.UC_8Bit {
        override val pixelFormat: Base
            get() = R

        override val pixelType: Int
            get() = GL_UNSIGNED_BYTE

        override val rBits = 8
        override val gBits = 0
        override val bBits = 0
        override val aBits = 0
    }

    data object R8_SN : R(GL_R8_SNORM), UncompressedColor, SignedNormalized, TextureViewAliasing.UC_8Bit {
        override val pixelFormat: Base
            get() = R

        override val pixelType: Int
            get() = GL_BYTE

        override val rBits = 8
        override val gBits = 0
        override val bBits = 0
        override val aBits = 0
    }

    data object R8_UI : R(GL_R8UI), UncompressedColor, UnsignedInteger, TextureViewAliasing.UC_8Bit {
        override val pixelFormat: Base
            get() = R

        override val pixelType: Int
            get() = GL_UNSIGNED_BYTE

        override val rBits = 8
        override val gBits = 0
        override val bBits = 0
        override val aBits = 0
    }

    data object R8_SI : R(GL_R8I), UncompressedColor, SignedInteger, TextureViewAliasing.UC_8Bit {
        override val pixelFormat: Base
            get() = R

        override val pixelType: Int
            get() = GL_BYTE

        override val rBits = 8
        override val gBits = 0
        override val bBits = 0
        override val aBits = 0
    }

    data object R16_UN : R(GL_R16), UncompressedColor, UnsignedNormalized, TextureViewAliasing.UC_16Bit {
        override val pixelFormat: Base
            get() = R

        override val pixelType: Int
            get() = GL_UNSIGNED_SHORT

        override val rBits = 16
        override val gBits = 0
        override val bBits = 0
        override val aBits = 0
    }

    data object R16_SN : R(GL_R16_SNORM), UncompressedColor, SignedNormalized, TextureViewAliasing.UC_16Bit {
        override val pixelFormat: Base
            get() = R

        override val pixelType: Int
            get() = GL_SHORT

        override val rBits = 16
        override val gBits = 0
        override val bBits = 0
        override val aBits = 0
    }

    data object R16_UI : R(GL_R16UI), UncompressedColor, UnsignedInteger, TextureViewAliasing.UC_16Bit {
        override val pixelFormat: Base
            get() = R

        override val pixelType: Int
            get() = GL_UNSIGNED_SHORT

        override val rBits = 16
        override val gBits = 0
        override val bBits = 0
        override val aBits = 0
    }

    data object R16_SI : R(GL_R16I), UncompressedColor, SignedInteger, TextureViewAliasing.UC_16Bit {
        override val pixelFormat: Base
            get() = R

        override val pixelType: Int
            get() = GL_SHORT

        override val rBits = 16
        override val gBits = 0
        override val bBits = 0
        override val aBits = 0
    }

    data object R16_F : R(GL_R16F), UncompressedColor, Float, TextureViewAliasing.UC_16Bit {
        override val pixelFormat: Base
            get() = R

        override val pixelType: Int
            get() = GL_HALF_FLOAT

        override val rBits = 16
        override val gBits = 0
        override val bBits = 0
        override val aBits = 0
    }

    data object R32_UI : R(GL_R32UI), UncompressedColor, UnsignedInteger, TextureViewAliasing.UC_32Bit {
        override val pixelFormat: Base
            get() = R

        override val pixelType: Int
            get() = GL_UNSIGNED_INT

        override val rBits = 32
        override val gBits = 0
        override val bBits = 0
        override val aBits = 0
    }

    data object R32_SI : R(GL_R32I), UncompressedColor, SignedInteger, TextureViewAliasing.UC_32Bit {
        override val pixelFormat: Base
            get() = R

        override val pixelType: Int
            get() = GL_INT

        override val rBits = 32
        override val gBits = 0
        override val bBits = 0
        override val aBits = 0
    }

    data object R32_F : R(GL_R32F), UncompressedColor, Float, TextureViewAliasing.UC_32Bit {
        override val pixelFormat: Base
            get() = R

        override val pixelType: Int
            get() = GL_FLOAT

        override val rBits = 32
        override val gBits = 0
        override val bBits = 0
        override val aBits = 0
    }

    data object R8G8_UN : RG(GL_RG8), UncompressedColor, UnsignedNormalized, TextureViewAliasing.UC_16Bit {
        override val pixelFormat: Base
            get() = RG

        override val pixelType: Int
            get() = GL_UNSIGNED_BYTE

        override val rBits = 8
        override val gBits = 8
        override val bBits = 0
        override val aBits = 0
    }

    data object R8G8_SN : RG(GL_RG8_SNORM), UncompressedColor, SignedNormalized, TextureViewAliasing.UC_16Bit {
        override val pixelFormat: Base
            get() = RG

        override val pixelType: Int
            get() = GL_BYTE

        override val rBits = 8
        override val gBits = 8
        override val bBits = 0
        override val aBits = 0
    }

    data object R8G8_UI : RG(GL_RG8UI), UncompressedColor, UnsignedInteger, TextureViewAliasing.UC_16Bit {
        override val pixelFormat: Base
            get() = RG

        override val pixelType: Int
            get() = GL_UNSIGNED_BYTE

        override val rBits = 8
        override val gBits = 8
        override val bBits = 0
        override val aBits = 0
    }

    data object R8G8_SI : RG(GL_RG8I), UncompressedColor, SignedInteger, TextureViewAliasing.UC_16Bit {
        override val pixelFormat: Base
            get() = RG

        override val pixelType: Int
            get() = GL_BYTE

        override val rBits = 8
        override val gBits = 8
        override val bBits = 0
        override val aBits = 0
    }

    data object R16G16_UN : RG(GL_RG16), UncompressedColor, UnsignedNormalized, TextureViewAliasing.UC_32Bit {
        override val pixelFormat: Base
            get() = RG

        override val pixelType: Int
            get() = GL_UNSIGNED_SHORT

        override val rBits = 16
        override val gBits = 16
        override val bBits = 0
        override val aBits = 0
    }

    data object R16G16_SN : RG(GL_RG16_SNORM), UncompressedColor, SignedNormalized, TextureViewAliasing.UC_32Bit {
        override val pixelFormat: Base
            get() = RG

        override val pixelType: Int
            get() = GL_SHORT

        override val rBits = 16
        override val gBits = 16
        override val bBits = 0
        override val aBits = 0
    }

    data object R16G16_UI : RG(GL_RG16UI), UncompressedColor, UnsignedInteger, TextureViewAliasing.UC_32Bit {
        override val pixelFormat: Base
            get() = RG

        override val pixelType: Int
            get() = GL_UNSIGNED_SHORT

        override val rBits = 16
        override val gBits = 16
        override val bBits = 0
        override val aBits = 0
    }

    data object R16G16_SI : RG(GL_RG16I), UncompressedColor, SignedInteger, TextureViewAliasing.UC_32Bit {
        override val pixelFormat: Base
            get() = RG

        override val pixelType: Int
            get() = GL_SHORT

        override val rBits = 16
        override val gBits = 16
        override val bBits = 0
        override val aBits = 0
    }

    data object R16G16_F : RG(GL_RG16F), UncompressedColor, Float, TextureViewAliasing.UC_32Bit {
        override val pixelFormat: Base
            get() = RG

        override val pixelType: Int
            get() = GL_HALF_FLOAT

        override val rBits = 16
        override val gBits = 16
        override val bBits = 0
        override val aBits = 0
    }

    data object R32G32_UI : RG(GL_RG32UI), UncompressedColor, UnsignedInteger, TextureViewAliasing.UC_64Bit {
        override val pixelFormat: Base
            get() = RG

        override val pixelType: Int
            get() = GL_UNSIGNED_INT

        override val rBits = 32
        override val gBits = 32
        override val bBits = 0
        override val aBits = 0
    }

    data object R32G32_SI : RG(GL_RG32I), UncompressedColor, SignedInteger, TextureViewAliasing.UC_64Bit {
        override val pixelFormat: Base
            get() = RG

        override val pixelType: Int
            get() = GL_INT

        override val rBits = 32
        override val gBits = 32
        override val bBits = 0
        override val aBits = 0
    }

    data object R32G32_F : RG(GL_RG32F), UncompressedColor, Float, TextureViewAliasing.UC_64Bit {
        override val pixelFormat: Base
            get() = RG

        override val pixelType: Int
            get() = GL_FLOAT

        override val rBits = 32
        override val gBits = 32
        override val bBits = 0
        override val aBits = 0
    }

    data object R8G8B8_UN : RGB(GL_RGB8), UncompressedColor, UnsignedNormalized, TextureViewAliasing.UC_24Bit {
        override val pixelFormat: Base
            get() = RGB

        override val pixelType: Int
            get() = GL_UNSIGNED_BYTE

        override val rBits = 8
        override val gBits = 8
        override val bBits = 8
        override val aBits = 0
    }

    data object R8G8B8_SN : RGB(GL_RGB8_SNORM), UncompressedColor, SignedNormalized, TextureViewAliasing.UC_24Bit {
        override val pixelFormat: Base
            get() = RGB

        override val pixelType: Int
            get() = GL_BYTE

        override val rBits = 8
        override val gBits = 8
        override val bBits = 8
        override val aBits = 0
    }

    data object R8G8B8_UI : RGB(GL_RGB8UI), UncompressedColor, UnsignedInteger, TextureViewAliasing.UC_24Bit {
        override val pixelFormat: Base
            get() = RGB

        override val pixelType: Int
            get() = GL_UNSIGNED_BYTE

        override val rBits = 8
        override val gBits = 8
        override val bBits = 8
        override val aBits = 0
    }

    data object R8G8B8_SI : RGB(GL_RGB8I), UncompressedColor, SignedInteger, TextureViewAliasing.UC_24Bit {
        override val pixelFormat: Base
            get() = RGB

        override val pixelType: Int
            get() = GL_BYTE

        override val rBits = 8
        override val gBits = 8
        override val bBits = 8
        override val aBits = 0
    }

    data object R16G16B16_UN : RGB(GL_RGB16), UncompressedColor, UnsignedNormalized, TextureViewAliasing.UC_48Bit {
        override val pixelFormat: Base
            get() = RGB

        override val pixelType: Int
            get() = GL_UNSIGNED_SHORT

        override val rBits = 16
        override val gBits = 16
        override val bBits = 16
        override val aBits = 0
    }

    data object R16G16B16_SN : RGB(GL_RGB16_SNORM), UncompressedColor, SignedNormalized, TextureViewAliasing.UC_48Bit {
        override val pixelFormat: Base
            get() = RGB

        override val pixelType: Int
            get() = GL_SHORT

        override val rBits = 16
        override val gBits = 16
        override val bBits = 16
        override val aBits = 0
    }

    data object R16G16B16_UI : RGB(GL_RGB16UI), UncompressedColor, UnsignedInteger, TextureViewAliasing.UC_48Bit {
        override val pixelFormat: Base
            get() = RGB

        override val pixelType: Int
            get() = GL_UNSIGNED_SHORT

        override val rBits = 16
        override val gBits = 16
        override val bBits = 16
        override val aBits = 0
    }

    data object R16G16B16_SI : RGB(GL_RGB16I), UncompressedColor, SignedInteger, TextureViewAliasing.UC_48Bit {
        override val pixelFormat: Base
            get() = RGB

        override val pixelType: Int
            get() = GL_SHORT

        override val rBits = 16
        override val gBits = 16
        override val bBits = 16
        override val aBits = 0
    }

    data object R16G16B16_F : RGB(GL_RGB16F), UncompressedColor, Float, TextureViewAliasing.UC_48Bit {
        override val pixelFormat: Base
            get() = RGB

        override val pixelType: Int
            get() = GL_HALF_FLOAT

        override val rBits = 16
        override val gBits = 16
        override val bBits = 16
        override val aBits = 0
    }

    data object R32G32B32_UI : RGB(GL_RGB32UI), UncompressedColor, UnsignedInteger, TextureViewAliasing.UC_96Bit {
        override val pixelFormat: Base
            get() = RGB

        override val pixelType: Int
            get() = GL_UNSIGNED_INT

        override val rBits = 32
        override val gBits = 32
        override val bBits = 32
        override val aBits = 0
    }

    data object R32G32B32_SI : RGB(GL_RGB32I), UncompressedColor, SignedInteger, TextureViewAliasing.UC_96Bit {
        override val pixelFormat: Base
            get() = RGB

        override val pixelType: Int
            get() = GL_INT

        override val rBits = 32
        override val gBits = 32
        override val bBits = 32
        override val aBits = 0
    }

    data object R32G32B32_F : RGB(GL_RGB32F), UncompressedColor, Float, TextureViewAliasing.UC_96Bit {
        override val pixelFormat: Base
            get() = RGB

        override val pixelType: Int
            get() = GL_FLOAT

        override val rBits = 32
        override val gBits = 32
        override val bBits = 32
        override val aBits = 0
    }

    data object R8G8B8A8_UN : RGBA(GL_RGBA8), UncompressedColor, UnsignedNormalized, TextureViewAliasing.UC_32Bit {
        override val pixelFormat: Base
            get() = RGBA

        override val pixelType: Int
            get() = GL_UNSIGNED_BYTE

        override val rBits = 8
        override val gBits = 8
        override val bBits = 8
        override val aBits = 8
    }

    data object R8G8B8A8_SN : RGBA(GL_RGBA8_SNORM), UncompressedColor, SignedNormalized, TextureViewAliasing.UC_32Bit {
        override val pixelFormat: Base
            get() = RGBA

        override val pixelType: Int
            get() = GL_BYTE

        override val rBits = 8
        override val gBits = 8
        override val bBits = 8
        override val aBits = 8
    }

    data object R8G8B8A8_UI : RGBA(GL_RGBA8UI), UncompressedColor, UnsignedInteger, TextureViewAliasing.UC_32Bit {
        override val pixelFormat: Base
            get() = RGBA

        override val pixelType: Int
            get() = GL_UNSIGNED_BYTE

        override val rBits = 8
        override val gBits = 8
        override val bBits = 8
        override val aBits = 8
    }

    data object R8G8B8A8_SI : RGBA(GL_RGBA8I), UncompressedColor, SignedInteger, TextureViewAliasing.UC_32Bit {
        override val pixelFormat: Base
            get() = RGBA

        override val pixelType: Int
            get() = GL_BYTE

        override val rBits = 8
        override val gBits = 8
        override val bBits = 8
        override val aBits = 8
    }

    data object R16G16B16A16_UN : RGBA(GL_RGBA16), UncompressedColor, UnsignedNormalized, TextureViewAliasing.UC_64Bit {
        override val pixelFormat: Base
            get() = RGBA

        override val pixelType: Int
            get() = GL_UNSIGNED_SHORT

        override val rBits = 16
        override val gBits = 16
        override val bBits = 16
        override val aBits = 16
    }

    data object R16G16B16A16_SN : RGBA(GL_RGBA16_SNORM), UncompressedColor, SignedNormalized, TextureViewAliasing.UC_64Bit {
        override val pixelFormat: Base
            get() = RGBA

        override val pixelType: Int
            get() = GL_SHORT

        override val rBits = 16
        override val gBits = 16
        override val bBits = 16
        override val aBits = 16
    }

    data object R16G16B16A16_UI : RGBA(GL_RGBA16UI), UncompressedColor, UnsignedInteger, TextureViewAliasing.UC_64Bit {
        override val pixelFormat: Base
            get() = RGBA

        override val pixelType: Int
            get() = GL_UNSIGNED_SHORT

        override val rBits = 16
        override val gBits = 16
        override val bBits = 16
        override val aBits = 16
    }

    data object R16G16B16A16_SI : RGBA(GL_RGBA16I), UncompressedColor, SignedInteger, TextureViewAliasing.UC_64Bit {
        override val pixelFormat: Base
            get() = RGBA

        override val pixelType: Int
            get() = GL_SHORT

        override val rBits = 16
        override val gBits = 16
        override val bBits = 16
        override val aBits = 16
    }

    data object R16G16B16A16_F : RGBA(GL_RGBA16F), UncompressedColor, Float, TextureViewAliasing.UC_64Bit {
        override val pixelFormat: Base
            get() = RGBA

        override val pixelType: Int
            get() = GL_HALF_FLOAT

        override val rBits = 16
        override val gBits = 16
        override val bBits = 16
        override val aBits = 16
    }

    data object R32G32B32A32_UI : RGBA(GL_RGBA32UI), UncompressedColor, UnsignedInteger, TextureViewAliasing.UC_128Bit {
        override val pixelFormat: Base
            get() = RGBA

        override val pixelType: Int
            get() = GL_UNSIGNED_INT

        override val rBits = 32
        override val gBits = 32
        override val bBits = 32
        override val aBits = 32
    }

    data object R32G32B32A32_SI : RGBA(GL_RGBA32I), UncompressedColor, SignedInteger, TextureViewAliasing.UC_128Bit {
        override val pixelFormat: Base
            get() = RGBA

        override val pixelType: Int
            get() = GL_INT

        override val rBits = 32
        override val gBits = 32
        override val bBits = 32
        override val aBits = 32
    }

    data object R32G32B32A32_F : RGBA(GL_RGBA32F), UncompressedColor, Float, TextureViewAliasing.UC_128Bit {
        override val pixelFormat: Base
            get() = RGBA

        override val pixelType: Int
            get() = GL_FLOAT

        override val rBits = 32
        override val gBits = 32
        override val bBits = 32
        override val aBits = 32
    }

    data object R3G3B2_UN : RGB(GL_R3_G3_B2), UncompressedColor, UnsignedNormalized {
        override val pixelFormat: Base
            get() = RGB

        override val pixelType: Int
            get() = GL_UNSIGNED_BYTE_3_3_2

        override val rBits = 3
        override val gBits = 3
        override val bBits = 2
        override val aBits = 0
    }

    data object R4G4B4_UN : RGB(GL_RGB4), UncompressedColor, UnsignedNormalized {
        override val pixelFormat: Base
            get() = RGB

        override val pixelType: Int
            get() = GL_UNSIGNED_SHORT_4_4_4_4

        override val rBits = 4
        override val gBits = 4
        override val bBits = 4
        override val aBits = 0
    }

    data object R5G5B5_UN : RGB(GL_RGB5), UncompressedColor, UnsignedNormalized {
        override val pixelFormat: Base
            get() = RGB

        override val pixelType: Int
            get() = GL_UNSIGNED_SHORT_5_5_5_1

        override val rBits = 5
        override val gBits = 5
        override val bBits = 5
        override val aBits = 0
    }

    data object R5G6B5_UN : RGB(GL_RGB565), UncompressedColor, UnsignedNormalized {
        override val pixelFormat: Base
            get() = RGB

        override val pixelType: Int
            get() = GL_UNSIGNED_SHORT_5_6_5

        override val rBits = 5
        override val gBits = 6
        override val bBits = 5
        override val aBits = 0
    }

    data object R10G10B10_UN : RGB(GL_RGB10), UncompressedColor, UnsignedNormalized {
        override val pixelFormat: Base
            get() = RGB

        override val pixelType: Int
            get() = GL_UNSIGNED_INT_10_10_10_2

        override val rBits = 10
        override val gBits = 10
        override val bBits = 10
        override val aBits = 0
    }

    data object R12G12B12_UN : RGB(GL_RGB12), UncompressedColor, UnsignedNormalized {
        override val pixelFormat: Base
            get() = RGB

        override val pixelType: Int
            get() = GL_UNSIGNED_INT

        override val rBits = 12
        override val gBits = 12
        override val bBits = 12
        override val aBits = 0
    }

    data object R2G2B2A2_UN : RGBA(GL_RGBA2), UncompressedColor, UnsignedNormalized {
        override val pixelFormat: Base
            get() = RGBA

        override val pixelType: Int
            get() = GL_UNSIGNED_BYTE_2_3_3_REV

        override val rBits = 2
        override val gBits = 2
        override val bBits = 2
        override val aBits = 2
    }

    data object R4G4B4A4_UN : RGBA(GL_RGBA4), UncompressedColor, UnsignedNormalized {
        override val pixelFormat: Base
            get() = RGBA

        override val pixelType: Int
            get() = GL_UNSIGNED_SHORT_4_4_4_4

        override val rBits = 4
        override val gBits = 4
        override val bBits = 4
        override val aBits = 4
    }

    data object R5G5B5_1_UN : RGBA(GL_RGB5_A1), UncompressedColor, UnsignedNormalized {
        override val pixelFormat: Base
            get() = RGBA

        override val pixelType: Int
            get() = GL_UNSIGNED_SHORT_5_5_5_1

        override val rBits = 5
        override val gBits = 5
        override val bBits = 5
        override val aBits = 1
    }

    data object R10G10B10A2_UN : RGBA(GL_RGB10_A2), UncompressedColor, UnsignedNormalized, TextureViewAliasing.UC_32Bit {
        override val pixelFormat: Base
            get() = RGBA

        override val pixelType: Int
            get() = GL_UNSIGNED_INT_2_10_10_10_REV

        override val rBits = 10
        override val gBits = 10
        override val bBits = 10
        override val aBits = 2
    }

    data object R10G10B10A2_UI : RGBA(GL_RGB10_A2UI), UncompressedColor, UnsignedInteger, TextureViewAliasing.UC_32Bit {
        override val pixelFormat: Base
            get() = RGBA

        override val pixelType: Int
            get() = GL_UNSIGNED_INT_2_10_10_10_REV

        override val rBits = 10
        override val gBits = 10
        override val bBits = 10
        override val aBits = 2
    }

    data object R8G8B8_SRGB : RGB(GL_SRGB8), UncompressedColor, UnsignedNormalized, TextureViewAliasing.UC_24Bit {
        override val pixelFormat: Base
            get() = RGB

        override val pixelType: Int
            get() = GL_UNSIGNED_BYTE

        override val rBits = 8
        override val gBits = 8
        override val bBits = 8
        override val aBits = 0
    }

    data object R8G8B8A8_SRGB : RGBA(GL_SRGB8_ALPHA8), UncompressedColor, UnsignedNormalized,
        TextureViewAliasing.UC_32Bit {
        override val pixelFormat: Base
            get() = RGBA

        override val pixelType: Int
            get() = GL_UNSIGNED_BYTE

        override val rBits = 8
        override val gBits = 8
        override val bBits = 8
        override val aBits = 8
    }

    data object R11G11B10_F : RGB(GL_R11F_G11F_B10F), UncompressedColor, Float, TextureViewAliasing.UC_32Bit {
        override val pixelFormat: Base
            get() = RGB

        override val pixelType: Int
            get() = GL_UNSIGNED_INT_10F_11F_11F_REV

        override val rBits = 11
        override val gBits = 11
        override val bBits = 10
        override val aBits = 0
    }

    data object R9G9B9E5_UN : RGB(GL_RGB9_E5), UncompressedColor, Float {
        override val pixelFormat: Base
            get() = RGB

        override val pixelType: Int
            get() = GL_UNSIGNED_INT_5_9_9_9_REV

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

        override val pixelFormat: Base
            get() = R

        override val pixelType: Int
            get() = GL_UNSIGNED_SHORT
    }

    data object Depth24 : Depth, Sized, UnsignedNormalized {
        override val value = GL_DEPTH_COMPONENT24
        override val depthBits = 24

        override val pixelFormat: Base
            get() = R

        override val pixelType: Int
            get() = GL_UNSIGNED_INT
    }

    data object Depth32 : Depth, Sized, UnsignedNormalized {
        override val value = GL_DEPTH_COMPONENT32
        override val depthBits = 32

        override val pixelFormat: Base
            get() = R

        override val pixelType: Int
            get() = GL_UNSIGNED_INT
    }

    data object Depth32F : Depth, Sized, Float {
        override val value = GL_DEPTH_COMPONENT32F
        override val depthBits = 32

        override val pixelFormat: Base
            get() = R

        override val pixelType: Int
            get() = GL_FLOAT
    }

    data object Depth24Stencil8 : DepthStencil, Sized, UnsignedNormalized {
        override val value = GL_DEPTH24_STENCIL8
        override val depthBits = 24
        override val stencilBits = 8

        override val pixelFormat: Base
            get() = R

        override val pixelType: Int
            get() = GL_UNSIGNED_INT_24_8
    }

    data object Depth32FStencil8 : DepthStencil, Sized, Float {
        override val value = GL_DEPTH32F_STENCIL8
        override val depthBits = 32
        override val stencilBits = 8

        override val pixelFormat: Base
            get() = R

        override val pixelType: Int
            get() = GL_FLOAT_32_UNSIGNED_INT_24_8_REV
    }

    data object Stencil8 : Stencil, Sized {
        override val value = GL_STENCIL_INDEX8
        override val stencilBits = 8

        override val pixelFormat: Base
            get() = R

        override val pixelType: Int
            get() = GL_UNSIGNED_BYTE
    }

    companion object {
        operator fun get(enum: Int): ImageFormat = when (enum) {
            GL_R8 -> R8_UN
            GL_R8_SNORM -> R8_SN
            GL_R8UI -> R8_UI
            GL_R8I -> R8_SI

            GL_R16 -> R16_UN
            GL_R16_SNORM -> R16_SN
            GL_R16UI -> R16_UI
            GL_R16I -> R16_SI
            GL_R16F -> R16_F

            GL_R32UI -> R32_UI
            GL_R32I -> R32_SI
            GL_R32F -> R32_F

            GL_RG8 -> R8G8_UN
            GL_RG8_SNORM -> R8G8_SN
            GL_RG8UI -> R8G8_UI
            GL_RG8I -> R8G8_SI

            GL_RG16 -> R16G16_UN
            GL_RG16_SNORM -> R16G16_SN
            GL_RG16UI -> R16G16_UI
            GL_RG16I -> R16G16_SI
            GL_RG16F -> R16G16_F

            GL_RG32UI -> R32G32_UI
            GL_RG32I -> R32G32_SI
            GL_RG32F -> R32G32_F

            GL_RGB8 -> R8G8B8_UN
            GL_RGB8_SNORM -> R8G8B8_SN
            GL_RGB8UI -> R8G8B8_UI
            GL_RGB8I -> R8G8B8_SI

            GL_RGB16 -> R16G16B16_UN
            GL_RGB16_SNORM -> R16G16B16_SN
            GL_RGB16UI -> R16G16B16_UI
            GL_RGB16I -> R16G16B16_SI
            GL_RGB16F -> R16G16B16_F

            GL_RGB32UI -> R32G32B32_UI
            GL_RGB32I -> R32G32B32_SI
            GL_RGB32F -> R32G32B32_F

            GL_RGBA8 -> R8G8B8A8_UN
            GL_RGBA8_SNORM -> R8G8B8A8_SN
            GL_RGBA8UI -> R8G8B8A8_UI
            GL_RGBA8I -> R8G8B8A8_SI

            GL_RGBA16 -> R16G16B16A16_UN
            GL_RGBA16_SNORM -> R16G16B16A16_SN
            GL_RGBA16UI -> R16G16B16A16_UI
            GL_RGBA16I -> R16G16B16A16_SI
            GL_RGBA16F -> R16G16B16A16_F

            GL_RGBA32UI -> R32G32B32A32_UI
            GL_RGBA32I -> R32G32B32A32_SI
            GL_RGBA32F -> R32G32B32A32_F

            GL_R3_G3_B2 -> R3G3B2_UN
            GL_RGB4 -> R4G4B4_UN
            GL_RGB5 -> R5G5B5_UN
            GL_RGB565 -> R5G6B5_UN
            GL_RGB10 -> R10G10B10_UN
            GL_RGB12 -> R12G12B12_UN

            GL_RGBA2 -> R2G2B2A2_UN
            GL_RGBA4 -> R4G4B4A4_UN
            GL_RGB5_A1 -> R5G5B5_1_UN
            GL_RGB10_A2 -> R10G10B10A2_UN
            GL_RGB10_A2UI -> R10G10B10A2_UI

            GL_SRGB8 -> R8G8B8_SRGB
            GL_SRGB8_ALPHA8 -> R8G8B8A8_SRGB

            GL_R11F_G11F_B10F -> R11G11B10_F
            GL_RGB9_E5 -> R9G9B9E5_UN

            GL_COMPRESSED_RED -> R_UN_C
            GL_COMPRESSED_RG -> RG_UN_C
            GL_COMPRESSED_RGB -> RGB_UN_C
            GL_COMPRESSED_RGBA -> RGBA_UN_C
            GL_COMPRESSED_SRGB -> RGB_SRGB_C
            GL_COMPRESSED_SRGB_ALPHA -> RGBA_SRGB_C

            GL_COMPRESSED_RED_RGTC1 -> R_UN_RGTC1
            GL_COMPRESSED_SIGNED_RED_RGTC1 -> R_SN_RGTC1
            GL_COMPRESSED_RG_RGTC2 -> RG_UN_RGTC2
            GL_COMPRESSED_SIGNED_RG_RGTC2 -> RG_SN_RGTC2

            GL_COMPRESSED_RGBA_BPTC_UNORM -> RGBA_UN_BPTC
            GL_COMPRESSED_SRGB_ALPHA_BPTC_UNORM -> RGBA_SRGB_BPTC
            GL_COMPRESSED_RGB_BPTC_SIGNED_FLOAT -> RGB_SF_BPTC
            GL_COMPRESSED_RGB_BPTC_UNSIGNED_FLOAT -> RGB_UF_BPTC

            GL_COMPRESSED_RGB_S3TC_DXT1_EXT -> RGB_S3TC_DXT1
            GL_COMPRESSED_RGBA_S3TC_DXT1_EXT -> RGBA_S3TC_DXT1
            GL_COMPRESSED_RGBA_S3TC_DXT3_EXT -> RGBA_S3TC_DXT3
            GL_COMPRESSED_RGBA_S3TC_DXT5_EXT -> RGBA_S3TC_DXT5

            GL_DEPTH_COMPONENT16 -> Depth16
            GL_DEPTH_COMPONENT24 -> Depth24
            GL_DEPTH_COMPONENT32 -> Depth32
            GL_DEPTH_COMPONENT32F -> Depth32F
            GL_DEPTH24_STENCIL8 -> Depth24Stencil8
            GL_DEPTH32F_STENCIL8 -> Depth32FStencil8
            GL_STENCIL_INDEX8 -> Stencil8

            else -> throw UnsupportedOperationException("Unsupported image format: $enum")
        }
    }
}