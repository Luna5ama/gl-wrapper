@file:JvmName("GL42")

package dev.luna5ama.glwrapper.api

interface IGL42 : GLBase {
    companion object {
        const val GL_COPY_READ_BUFFER_BINDING = IGL31.GL_COPY_READ_BUFFER
        const val GL_COPY_WRITE_BUFFER_BINDING = IGL31.GL_COPY_WRITE_BUFFER
        const val GL_TRANSFORM_FEEDBACK_ACTIVE = IGL40.GL_TRANSFORM_FEEDBACK_BUFFER_ACTIVE
        const val GL_TRANSFORM_FEEDBACK_PAUSED = IGL40.GL_TRANSFORM_FEEDBACK_BUFFER_PAUSED

        const val GL_COMPRESSED_RGBA_BPTC_UNORM = 0x8E8C
        const val GL_COMPRESSED_SRGB_ALPHA_BPTC_UNORM = 0x8E8D
        const val GL_COMPRESSED_RGB_BPTC_SIGNED_FLOAT = 0x8E8E
        const val GL_COMPRESSED_RGB_BPTC_UNSIGNED_FLOAT = 0x8E8F

        const val GL_UNPACK_COMPRESSED_BLOCK_WIDTH = 0x9127
        const val GL_UNPACK_COMPRESSED_BLOCK_HEIGHT = 0x9128
        const val GL_UNPACK_COMPRESSED_BLOCK_DEPTH = 0x9129
        const val GL_UNPACK_COMPRESSED_BLOCK_SIZE = 0x912A
        const val GL_PACK_COMPRESSED_BLOCK_WIDTH = 0x912B
        const val GL_PACK_COMPRESSED_BLOCK_HEIGHT = 0x912C
        const val GL_PACK_COMPRESSED_BLOCK_DEPTH = 0x912D
        const val GL_PACK_COMPRESSED_BLOCK_SIZE = 0x912E

        const val GL_ATOMIC_COUNTER_BUFFER = 0x92C0

        const val GL_ATOMIC_COUNTER_BUFFER_BINDING = 0x92C1

        const val GL_ATOMIC_COUNTER_BUFFER_START = 0x92C2
        const val GL_ATOMIC_COUNTER_BUFFER_SIZE = 0x92C3

        const val GL_ATOMIC_COUNTER_BUFFER_DATA_SIZE = 0x92C4
        const val GL_ATOMIC_COUNTER_BUFFER_ACTIVE_ATOMIC_COUNTERS = 0x92C5
        const val GL_ATOMIC_COUNTER_BUFFER_ACTIVE_ATOMIC_COUNTER_INDICES = 0x92C6
        const val GL_ATOMIC_COUNTER_BUFFER_REFERENCED_BY_VERTEX_SHADER = 0x92C7
        const val GL_ATOMIC_COUNTER_BUFFER_REFERENCED_BY_TESS_CONTROL_SHADER = 0x92C8
        const val GL_ATOMIC_COUNTER_BUFFER_REFERENCED_BY_TESS_EVALUATION_SHADER = 0x92C9
        const val GL_ATOMIC_COUNTER_BUFFER_REFERENCED_BY_GEOMETRY_SHADER = 0x92CA
        const val GL_ATOMIC_COUNTER_BUFFER_REFERENCED_BY_FRAGMENT_SHADER = 0x92CB

        const val GL_MAX_VERTEX_ATOMIC_COUNTER_BUFFERS = 0x92CC
        const val GL_MAX_TESS_CONTROL_ATOMIC_COUNTER_BUFFERS = 0x92CD
        const val GL_MAX_TESS_EVALUATION_ATOMIC_COUNTER_BUFFERS = 0x92CE
        const val GL_MAX_GEOMETRY_ATOMIC_COUNTER_BUFFERS = 0x92CF
        const val GL_MAX_FRAGMENT_ATOMIC_COUNTER_BUFFERS = 0x92D0
        const val GL_MAX_COMBINED_ATOMIC_COUNTER_BUFFERS = 0x92D1
        const val GL_MAX_VERTEX_ATOMIC_COUNTERS = 0x92D2
        const val GL_MAX_TESS_CONTROL_ATOMIC_COUNTERS = 0x92D3
        const val GL_MAX_TESS_EVALUATION_ATOMIC_COUNTERS = 0x92D4
        const val GL_MAX_GEOMETRY_ATOMIC_COUNTERS = 0x92D5
        const val GL_MAX_FRAGMENT_ATOMIC_COUNTERS = 0x92D6
        const val GL_MAX_COMBINED_ATOMIC_COUNTERS = 0x92D7
        const val GL_MAX_ATOMIC_COUNTER_BUFFER_SIZE = 0x92D8

        const val GL_MAX_ATOMIC_COUNTER_BUFFER_BINDINGS = 0x92DC

        const val GL_ACTIVE_ATOMIC_COUNTER_BUFFERS = 0x92D9

        const val GL_UNIFORM_ATOMIC_COUNTER_BUFFER_INDEX = 0x92DA

        const val GL_UNSIGNED_INT_ATOMIC_COUNTER = 0x92DB

        const val GL_TEXTURE_IMMUTABLE_FORMAT = 0x912F

        const val GL_MAX_IMAGE_UNITS = 0x8F38
        const val GL_MAX_COMBINED_IMAGE_UNITS_AND_FRAGMENT_OUTPUTS = 0x8F39
        const val GL_MAX_IMAGE_SAMPLES = 0x906D
        const val GL_MAX_VERTEX_IMAGE_UNIFORMS = 0x90CA
        const val GL_MAX_TESS_CONTROL_IMAGE_UNIFORMS = 0x90CB
        const val GL_MAX_TESS_EVALUATION_IMAGE_UNIFORMS = 0x90CC
        const val GL_MAX_GEOMETRY_IMAGE_UNIFORMS = 0x90CD
        const val GL_MAX_FRAGMENT_IMAGE_UNIFORMS = 0x90CE
        const val GL_MAX_COMBINED_IMAGE_UNIFORMS = 0x90CF

        const val GL_IMAGE_BINDING_NAME = 0x8F3A
        const val GL_IMAGE_BINDING_LEVEL = 0x8F3B
        const val GL_IMAGE_BINDING_LAYERED = 0x8F3C
        const val GL_IMAGE_BINDING_LAYER = 0x8F3D
        const val GL_IMAGE_BINDING_ACCESS = 0x8F3E
        const val GL_IMAGE_BINDING_FORMAT = 0x906E

        const val GL_VERTEX_ATTRIB_ARRAY_BARRIER_BIT = 0x1
        const val GL_ELEMENT_ARRAY_BARRIER_BIT = 0x2
        const val GL_UNIFORM_BARRIER_BIT = 0x4
        const val GL_TEXTURE_FETCH_BARRIER_BIT = 0x8
        const val GL_SHADER_IMAGE_ACCESS_BARRIER_BIT = 0x20
        const val GL_COMMAND_BARRIER_BIT = 0x40
        const val GL_PIXEL_BUFFER_BARRIER_BIT = 0x80
        const val GL_TEXTURE_UPDATE_BARRIER_BIT = 0x100
        const val GL_BUFFER_UPDATE_BARRIER_BIT = 0x200
        const val GL_FRAMEBUFFER_BARRIER_BIT = 0x400
        const val GL_TRANSFORM_FEEDBACK_BARRIER_BIT = 0x800
        const val GL_ATOMIC_COUNTER_BARRIER_BIT = 0x1000
        const val GL_ALL_BARRIER_BITS = -0x1

        const val GL_IMAGE_1D = 0x904C
        const val GL_IMAGE_2D = 0x904D
        const val GL_IMAGE_3D = 0x904E
        const val GL_IMAGE_2D_RECT = 0x904F
        const val GL_IMAGE_CUBE = 0x9050
        const val GL_IMAGE_BUFFER = 0x9051
        const val GL_IMAGE_1D_ARRAY = 0x9052
        const val GL_IMAGE_2D_ARRAY = 0x9053
        const val GL_IMAGE_CUBE_MAP_ARRAY = 0x9054
        const val GL_IMAGE_2D_MULTISAMPLE = 0x9055
        const val GL_IMAGE_2D_MULTISAMPLE_ARRAY = 0x9056
        const val GL_INT_IMAGE_1D = 0x9057
        const val GL_INT_IMAGE_2D = 0x9058
        const val GL_INT_IMAGE_3D = 0x9059
        const val GL_INT_IMAGE_2D_RECT = 0x905A
        const val GL_INT_IMAGE_CUBE = 0x905B
        const val GL_INT_IMAGE_BUFFER = 0x905C
        const val GL_INT_IMAGE_1D_ARRAY = 0x905D
        const val GL_INT_IMAGE_2D_ARRAY = 0x905E
        const val GL_INT_IMAGE_CUBE_MAP_ARRAY = 0x905F
        const val GL_INT_IMAGE_2D_MULTISAMPLE = 0x9060
        const val GL_INT_IMAGE_2D_MULTISAMPLE_ARRAY = 0x9061
        const val GL_UNSIGNED_INT_IMAGE_1D = 0x9062
        const val GL_UNSIGNED_INT_IMAGE_2D = 0x9063
        const val GL_UNSIGNED_INT_IMAGE_3D = 0x9064
        const val GL_UNSIGNED_INT_IMAGE_2D_RECT = 0x9065
        const val GL_UNSIGNED_INT_IMAGE_CUBE = 0x9066
        const val GL_UNSIGNED_INT_IMAGE_BUFFER = 0x9067
        const val GL_UNSIGNED_INT_IMAGE_1D_ARRAY = 0x9068
        const val GL_UNSIGNED_INT_IMAGE_2D_ARRAY = 0x9069
        const val GL_UNSIGNED_INT_IMAGE_CUBE_MAP_ARRAY = 0x906A
        const val GL_UNSIGNED_INT_IMAGE_2D_MULTISAMPLE = 0x906B
        const val GL_UNSIGNED_INT_IMAGE_2D_MULTISAMPLE_ARRAY = 0x906C

        const val GL_IMAGE_FORMAT_COMPATIBILITY_TYPE = 0x90C7

        const val GL_IMAGE_FORMAT_COMPATIBILITY_BY_SIZE = 0x90C8
        const val GL_IMAGE_FORMAT_COMPATIBILITY_BY_CLASS = 0x90C9

        const val GL_NUM_SAMPLE_COUNTS = 0x9380

        const val GL_MIN_MAP_BUFFER_ALIGNMENT = 0x90BC
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