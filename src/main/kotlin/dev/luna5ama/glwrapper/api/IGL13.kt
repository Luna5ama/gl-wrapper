@file:JvmName("GL13")

package dev.luna5ama.glwrapper.api

interface IGL13 : GLBase {
    companion object {
        const val GL_COMPRESSED_RGB = 0x84ED
        const val GL_COMPRESSED_RGBA = 0x84EE

        const val GL_TEXTURE_COMPRESSION_HINT = 0x84EF

        const val GL_TEXTURE_COMPRESSED_IMAGE_SIZE = 0x86A0
        const val GL_TEXTURE_COMPRESSED = 0x86A1

        const val GL_NUM_COMPRESSED_TEXTURE_FORMATS = 0x86A2
        const val GL_COMPRESSED_TEXTURE_FORMATS = 0x86A3

        const val GL_TEXTURE_CUBE_MAP = 0x8513

        const val GL_TEXTURE_BINDING_CUBE_MAP = 0x8514

        const val GL_TEXTURE_CUBE_MAP_POSITIVE_X = 0x8515
        const val GL_TEXTURE_CUBE_MAP_NEGATIVE_X = 0x8516
        const val GL_TEXTURE_CUBE_MAP_POSITIVE_Y = 0x8517
        const val GL_TEXTURE_CUBE_MAP_NEGATIVE_Y = 0x8518
        const val GL_TEXTURE_CUBE_MAP_POSITIVE_Z = 0x8519
        const val GL_TEXTURE_CUBE_MAP_NEGATIVE_Z = 0x851A

        const val GL_PROXY_TEXTURE_CUBE_MAP = 0x851B

        const val GL_MAX_CUBE_MAP_TEXTURE_SIZE = 0x851C

        const val GL_MULTISAMPLE = 0x809D
        const val GL_SAMPLE_ALPHA_TO_COVERAGE = 0x809E
        const val GL_SAMPLE_ALPHA_TO_ONE = 0x809F
        const val GL_SAMPLE_COVERAGE = 0x80A0

        const val GL_SAMPLE_BUFFERS = 0x80A8
        const val GL_SAMPLES = 0x80A9
        const val GL_SAMPLE_COVERAGE_VALUE = 0x80AA
        const val GL_SAMPLE_COVERAGE_INVERT = 0x80AB

        const val GL_TEXTURE0 = 0x84C0
        const val GL_TEXTURE1 = 0x84C1
        const val GL_TEXTURE2 = 0x84C2
        const val GL_TEXTURE3 = 0x84C3
        const val GL_TEXTURE4 = 0x84C4
        const val GL_TEXTURE5 = 0x84C5
        const val GL_TEXTURE6 = 0x84C6
        const val GL_TEXTURE7 = 0x84C7
        const val GL_TEXTURE8 = 0x84C8
        const val GL_TEXTURE9 = 0x84C9
        const val GL_TEXTURE10 = 0x84CA
        const val GL_TEXTURE11 = 0x84CB
        const val GL_TEXTURE12 = 0x84CC
        const val GL_TEXTURE13 = 0x84CD
        const val GL_TEXTURE14 = 0x84CE
        const val GL_TEXTURE15 = 0x84CF
        const val GL_TEXTURE16 = 0x84D0
        const val GL_TEXTURE17 = 0x84D1
        const val GL_TEXTURE18 = 0x84D2
        const val GL_TEXTURE19 = 0x84D3
        const val GL_TEXTURE20 = 0x84D4
        const val GL_TEXTURE21 = 0x84D5
        const val GL_TEXTURE22 = 0x84D6
        const val GL_TEXTURE23 = 0x84D7
        const val GL_TEXTURE24 = 0x84D8
        const val GL_TEXTURE25 = 0x84D9
        const val GL_TEXTURE26 = 0x84DA
        const val GL_TEXTURE27 = 0x84DB
        const val GL_TEXTURE28 = 0x84DC
        const val GL_TEXTURE29 = 0x84DD
        const val GL_TEXTURE30 = 0x84DE
        const val GL_TEXTURE31 = 0x84DF

        const val GL_ACTIVE_TEXTURE = 0x84E0
        const val GL_CLAMP_TO_BORDER = 0x812D
    }

    fun glSampleCoverage(value: Float, invert: Boolean)
}