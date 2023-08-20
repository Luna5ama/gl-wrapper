@file:JvmName("GL42")

package dev.luna5ama.glwrapper.api

interface IGL42 : GLBase {
    companion object {
        internal const val GL_COPY_READ_BUFFER_BINDING = IGL31.GL_COPY_READ_BUFFER
        internal const val GL_COPY_WRITE_BUFFER_BINDING = IGL31.GL_COPY_WRITE_BUFFER
        internal const val GL_TRANSFORM_FEEDBACK_ACTIVE = IGL40.GL_TRANSFORM_FEEDBACK_BUFFER_ACTIVE
        internal const val GL_TRANSFORM_FEEDBACK_PAUSED = IGL40.GL_TRANSFORM_FEEDBACK_BUFFER_PAUSED

        internal const val GL_COMPRESSED_RGBA_BPTC_UNORM = 0x8E8C
        internal const val GL_COMPRESSED_SRGB_ALPHA_BPTC_UNORM = 0x8E8D
        internal const val GL_COMPRESSED_RGB_BPTC_SIGNED_FLOAT = 0x8E8E
        internal const val GL_COMPRESSED_RGB_BPTC_UNSIGNED_FLOAT = 0x8E8F

        internal const val GL_UNPACK_COMPRESSED_BLOCK_WIDTH = 0x9127
        internal const val GL_UNPACK_COMPRESSED_BLOCK_HEIGHT = 0x9128
        internal const val GL_UNPACK_COMPRESSED_BLOCK_DEPTH = 0x9129
        internal const val GL_UNPACK_COMPRESSED_BLOCK_SIZE = 0x912A
        internal const val GL_PACK_COMPRESSED_BLOCK_WIDTH = 0x912B
        internal const val GL_PACK_COMPRESSED_BLOCK_HEIGHT = 0x912C
        internal const val GL_PACK_COMPRESSED_BLOCK_DEPTH = 0x912D
        internal const val GL_PACK_COMPRESSED_BLOCK_SIZE = 0x912E

        internal const val GL_ATOMIC_COUNTER_BUFFER = 0x92C0

        internal const val GL_ATOMIC_COUNTER_BUFFER_BINDING = 0x92C1

        internal const val GL_ATOMIC_COUNTER_BUFFER_START = 0x92C2
        internal const val GL_ATOMIC_COUNTER_BUFFER_SIZE = 0x92C3

        internal const val GL_ATOMIC_COUNTER_BUFFER_DATA_SIZE = 0x92C4
        internal const val GL_ATOMIC_COUNTER_BUFFER_ACTIVE_ATOMIC_COUNTERS = 0x92C5
        internal const val GL_ATOMIC_COUNTER_BUFFER_ACTIVE_ATOMIC_COUNTER_INDICES = 0x92C6
        internal const val GL_ATOMIC_COUNTER_BUFFER_REFERENCED_BY_VERTEX_SHADER = 0x92C7
        internal const val GL_ATOMIC_COUNTER_BUFFER_REFERENCED_BY_TESS_CONTROL_SHADER = 0x92C8
        internal const val GL_ATOMIC_COUNTER_BUFFER_REFERENCED_BY_TESS_EVALUATION_SHADER = 0x92C9
        internal const val GL_ATOMIC_COUNTER_BUFFER_REFERENCED_BY_GEOMETRY_SHADER = 0x92CA
        internal const val GL_ATOMIC_COUNTER_BUFFER_REFERENCED_BY_FRAGMENT_SHADER = 0x92CB

        internal const val GL_MAX_VERTEX_ATOMIC_COUNTER_BUFFERS = 0x92CC
        internal const val GL_MAX_TESS_CONTROL_ATOMIC_COUNTER_BUFFERS = 0x92CD
        internal const val GL_MAX_TESS_EVALUATION_ATOMIC_COUNTER_BUFFERS = 0x92CE
        internal const val GL_MAX_GEOMETRY_ATOMIC_COUNTER_BUFFERS = 0x92CF
        internal const val GL_MAX_FRAGMENT_ATOMIC_COUNTER_BUFFERS = 0x92D0
        internal const val GL_MAX_COMBINED_ATOMIC_COUNTER_BUFFERS = 0x92D1
        internal const val GL_MAX_VERTEX_ATOMIC_COUNTERS = 0x92D2
        internal const val GL_MAX_TESS_CONTROL_ATOMIC_COUNTERS = 0x92D3
        internal const val GL_MAX_TESS_EVALUATION_ATOMIC_COUNTERS = 0x92D4
        internal const val GL_MAX_GEOMETRY_ATOMIC_COUNTERS = 0x92D5
        internal const val GL_MAX_FRAGMENT_ATOMIC_COUNTERS = 0x92D6
        internal const val GL_MAX_COMBINED_ATOMIC_COUNTERS = 0x92D7
        internal const val GL_MAX_ATOMIC_COUNTER_BUFFER_SIZE = 0x92D8

        internal const val GL_MAX_ATOMIC_COUNTER_BUFFER_BINDINGS = 0x92DC

        internal const val GL_ACTIVE_ATOMIC_COUNTER_BUFFERS = 0x92D9

        internal const val GL_UNIFORM_ATOMIC_COUNTER_BUFFER_INDEX = 0x92DA

        internal const val GL_UNSIGNED_INT_ATOMIC_COUNTER = 0x92DB

        internal const val GL_TEXTURE_IMMUTABLE_FORMAT = 0x912F

        internal const val GL_MAX_IMAGE_UNITS = 0x8F38
        internal const val GL_MAX_COMBINED_IMAGE_UNITS_AND_FRAGMENT_OUTPUTS = 0x8F39
        internal const val GL_MAX_IMAGE_SAMPLES = 0x906D
        internal const val GL_MAX_VERTEX_IMAGE_UNIFORMS = 0x90CA
        internal const val GL_MAX_TESS_CONTROL_IMAGE_UNIFORMS = 0x90CB
        internal const val GL_MAX_TESS_EVALUATION_IMAGE_UNIFORMS = 0x90CC
        internal const val GL_MAX_GEOMETRY_IMAGE_UNIFORMS = 0x90CD
        internal const val GL_MAX_FRAGMENT_IMAGE_UNIFORMS = 0x90CE
        internal const val GL_MAX_COMBINED_IMAGE_UNIFORMS = 0x90CF

        internal const val GL_IMAGE_BINDING_NAME = 0x8F3A
        internal const val GL_IMAGE_BINDING_LEVEL = 0x8F3B
        internal const val GL_IMAGE_BINDING_LAYERED = 0x8F3C
        internal const val GL_IMAGE_BINDING_LAYER = 0x8F3D
        internal const val GL_IMAGE_BINDING_ACCESS = 0x8F3E
        internal const val GL_IMAGE_BINDING_FORMAT = 0x906E

        internal const val GL_VERTEX_ATTRIB_ARRAY_BARRIER_BIT = 0x1
        internal const val GL_ELEMENT_ARRAY_BARRIER_BIT = 0x2
        internal const val GL_UNIFORM_BARRIER_BIT = 0x4
        internal const val GL_TEXTURE_FETCH_BARRIER_BIT = 0x8
        internal const val GL_SHADER_IMAGE_ACCESS_BARRIER_BIT = 0x20
        internal const val GL_COMMAND_BARRIER_BIT = 0x40
        internal const val GL_PIXEL_BUFFER_BARRIER_BIT = 0x80
        internal const val GL_TEXTURE_UPDATE_BARRIER_BIT = 0x100
        internal const val GL_BUFFER_UPDATE_BARRIER_BIT = 0x200
        internal const val GL_FRAMEBUFFER_BARRIER_BIT = 0x400
        internal const val GL_TRANSFORM_FEEDBACK_BARRIER_BIT = 0x800
        internal const val GL_ATOMIC_COUNTER_BARRIER_BIT = 0x1000
        internal const val GL_ALL_BARRIER_BITS = -0x1

        internal const val GL_IMAGE_1D = 0x904C
        internal const val GL_IMAGE_2D = 0x904D
        internal const val GL_IMAGE_3D = 0x904E
        internal const val GL_IMAGE_2D_RECT = 0x904F
        internal const val GL_IMAGE_CUBE = 0x9050
        internal const val GL_IMAGE_BUFFER = 0x9051
        internal const val GL_IMAGE_1D_ARRAY = 0x9052
        internal const val GL_IMAGE_2D_ARRAY = 0x9053
        internal const val GL_IMAGE_CUBE_MAP_ARRAY = 0x9054
        internal const val GL_IMAGE_2D_MULTISAMPLE = 0x9055
        internal const val GL_IMAGE_2D_MULTISAMPLE_ARRAY = 0x9056
        internal const val GL_INT_IMAGE_1D = 0x9057
        internal const val GL_INT_IMAGE_2D = 0x9058
        internal const val GL_INT_IMAGE_3D = 0x9059
        internal const val GL_INT_IMAGE_2D_RECT = 0x905A
        internal const val GL_INT_IMAGE_CUBE = 0x905B
        internal const val GL_INT_IMAGE_BUFFER = 0x905C
        internal const val GL_INT_IMAGE_1D_ARRAY = 0x905D
        internal const val GL_INT_IMAGE_2D_ARRAY = 0x905E
        internal const val GL_INT_IMAGE_CUBE_MAP_ARRAY = 0x905F
        internal const val GL_INT_IMAGE_2D_MULTISAMPLE = 0x9060
        internal const val GL_INT_IMAGE_2D_MULTISAMPLE_ARRAY = 0x9061
        internal const val GL_UNSIGNED_INT_IMAGE_1D = 0x9062
        internal const val GL_UNSIGNED_INT_IMAGE_2D = 0x9063
        internal const val GL_UNSIGNED_INT_IMAGE_3D = 0x9064
        internal const val GL_UNSIGNED_INT_IMAGE_2D_RECT = 0x9065
        internal const val GL_UNSIGNED_INT_IMAGE_CUBE = 0x9066
        internal const val GL_UNSIGNED_INT_IMAGE_BUFFER = 0x9067
        internal const val GL_UNSIGNED_INT_IMAGE_1D_ARRAY = 0x9068
        internal const val GL_UNSIGNED_INT_IMAGE_2D_ARRAY = 0x9069
        internal const val GL_UNSIGNED_INT_IMAGE_CUBE_MAP_ARRAY = 0x906A
        internal const val GL_UNSIGNED_INT_IMAGE_2D_MULTISAMPLE = 0x906B
        internal const val GL_UNSIGNED_INT_IMAGE_2D_MULTISAMPLE_ARRAY = 0x906C

        internal const val GL_IMAGE_FORMAT_COMPATIBILITY_TYPE = 0x90C7

        internal const val GL_IMAGE_FORMAT_COMPATIBILITY_BY_SIZE = 0x90C8
        internal const val GL_IMAGE_FORMAT_COMPATIBILITY_BY_CLASS = 0x90C9

        internal const val GL_NUM_SAMPLE_COUNTS = 0x9380

        internal const val GL_MIN_MAP_BUFFER_ALIGNMENT = 0x90BC
    }

    fun glDrawArraysInstancedBaseInstance(mode: Int, first: Int, count: Int, instancecount: Int, baseinstance: Int)
    fun glDrawElementsInstancedBaseInstance(
        mode: Int,
        count: Int,
        type: Int,
        indices: Long,
        instancecount: Int,
        baseinstance: Int
    )

    fun glDrawElementsInstancedBaseVertexBaseInstance(
        mode: Int,
        count: Int,
        type: Int,
        indices: Long,
        instancecount: Int,
        basevertex: Int,
        baseinstance: Int
    )

    fun glBindImageTexture(unit: Int, texture: Int, level: Int, layered: Boolean, layer: Int, access: Int, format: Int)

    fun glMemoryBarrier(barriers: Int)
}

abstract class PatchedGL42(protected val delegate: IGL42) : IGL42 by delegate