@file:JvmName("GL13")

package dev.luna5ama.glwrapper.api

interface IGL13 : GLBase {
    companion object {
        internal const val GL_COMPRESSED_RGB = 0x84ED
        internal const val GL_COMPRESSED_RGBA = 0x84EE

        internal const val GL_TEXTURE_COMPRESSION_HINT = 0x84EF

        internal const val GL_TEXTURE_COMPRESSED_IMAGE_SIZE = 0x86A0
        internal const val GL_TEXTURE_COMPRESSED = 0x86A1

        internal const val GL_NUM_COMPRESSED_TEXTURE_FORMATS = 0x86A2
        internal const val GL_COMPRESSED_TEXTURE_FORMATS = 0x86A3

        internal const val GL_TEXTURE_CUBE_MAP = 0x8513

        internal const val GL_TEXTURE_BINDING_CUBE_MAP = 0x8514

        internal const val GL_TEXTURE_CUBE_MAP_POSITIVE_X = 0x8515
        internal const val GL_TEXTURE_CUBE_MAP_NEGATIVE_X = 0x8516
        internal const val GL_TEXTURE_CUBE_MAP_POSITIVE_Y = 0x8517
        internal const val GL_TEXTURE_CUBE_MAP_NEGATIVE_Y = 0x8518
        internal const val GL_TEXTURE_CUBE_MAP_POSITIVE_Z = 0x8519
        internal const val GL_TEXTURE_CUBE_MAP_NEGATIVE_Z = 0x851A

        internal const val GL_PROXY_TEXTURE_CUBE_MAP = 0x851B

        internal const val GL_MAX_CUBE_MAP_TEXTURE_SIZE = 0x851C

        internal const val GL_MULTISAMPLE = 0x809D
        internal const val GL_SAMPLE_ALPHA_TO_COVERAGE = 0x809E
        internal const val GL_SAMPLE_ALPHA_TO_ONE = 0x809F
        internal const val GL_SAMPLE_COVERAGE = 0x80A0

        internal const val GL_SAMPLE_BUFFERS = 0x80A8
        internal const val GL_SAMPLES = 0x80A9
        internal const val GL_SAMPLE_COVERAGE_VALUE = 0x80AA
        internal const val GL_SAMPLE_COVERAGE_INVERT = 0x80AB

        internal const val GL_TEXTURE0 = 0x84C0
        internal const val GL_TEXTURE1 = 0x84C1
        internal const val GL_TEXTURE2 = 0x84C2
        internal const val GL_TEXTURE3 = 0x84C3
        internal const val GL_TEXTURE4 = 0x84C4
        internal const val GL_TEXTURE5 = 0x84C5
        internal const val GL_TEXTURE6 = 0x84C6
        internal const val GL_TEXTURE7 = 0x84C7
        internal const val GL_TEXTURE8 = 0x84C8
        internal const val GL_TEXTURE9 = 0x84C9
        internal const val GL_TEXTURE10 = 0x84CA
        internal const val GL_TEXTURE11 = 0x84CB
        internal const val GL_TEXTURE12 = 0x84CC
        internal const val GL_TEXTURE13 = 0x84CD
        internal const val GL_TEXTURE14 = 0x84CE
        internal const val GL_TEXTURE15 = 0x84CF
        internal const val GL_TEXTURE16 = 0x84D0
        internal const val GL_TEXTURE17 = 0x84D1
        internal const val GL_TEXTURE18 = 0x84D2
        internal const val GL_TEXTURE19 = 0x84D3
        internal const val GL_TEXTURE20 = 0x84D4
        internal const val GL_TEXTURE21 = 0x84D5
        internal const val GL_TEXTURE22 = 0x84D6
        internal const val GL_TEXTURE23 = 0x84D7
        internal const val GL_TEXTURE24 = 0x84D8
        internal const val GL_TEXTURE25 = 0x84D9
        internal const val GL_TEXTURE26 = 0x84DA
        internal const val GL_TEXTURE27 = 0x84DB
        internal const val GL_TEXTURE28 = 0x84DC
        internal const val GL_TEXTURE29 = 0x84DD
        internal const val GL_TEXTURE30 = 0x84DE
        internal const val GL_TEXTURE31 = 0x84DF

        internal const val GL_ACTIVE_TEXTURE = 0x84E0
        internal const val GL_CLAMP_TO_BORDER = 0x812D
    }

    fun glSampleCoverage(value: Float, invert: Boolean)
}

abstract class PatchedGL13(protected val delegate: IGL13) : IGL13 by delegate