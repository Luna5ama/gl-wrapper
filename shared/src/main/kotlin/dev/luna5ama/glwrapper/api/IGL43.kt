@file:JvmName("GL43")

package dev.luna5ama.glwrapper.api

interface IGL43 : GLBase {
    companion object {
        const val GL_NUM_SHADING_LANGUAGE_VERSIONS = 0x82E9

        const val GL_VERTEX_ATTRIB_ARRAY_LONG = 0x874E

        const val GL_COMPRESSED_RGB8_ETC2 = 0x9274
        const val GL_COMPRESSED_SRGB8_ETC2 = 0x9275
        const val GL_COMPRESSED_RGB8_PUNCHTHROUGH_ALPHA1_ETC2 = 0x9276
        const val GL_COMPRESSED_SRGB8_PUNCHTHROUGH_ALPHA1_ETC2 = 0x9277
        const val GL_COMPRESSED_RGBA8_ETC2_EAC = 0x9278
        const val GL_COMPRESSED_SRGB8_ALPHA8_ETC2_EAC = 0x9279
        const val GL_COMPRESSED_R11_EAC = 0x9270
        const val GL_COMPRESSED_SIGNED_R11_EAC = 0x9271
        const val GL_COMPRESSED_RG11_EAC = 0x9272
        const val GL_COMPRESSED_SIGNED_RG11_EAC = 0x9273

        const val GL_PRIMITIVE_RESTART_FIXED_INDEX = 0x8D69

        const val GL_ANY_SAMPLES_PASSED_CONSERVATIVE = 0x8D6A

        const val GL_MAX_ELEMENT_INDEX = 0x8D6B

        const val GL_TEXTURE_IMMUTABLE_LEVELS = 0x82DF

        const val GL_COMPUTE_SHADER = 0x91B9

        const val GL_MAX_COMPUTE_UNIFORM_BLOCKS = 0x91BB
        const val GL_MAX_COMPUTE_TEXTURE_IMAGE_UNITS = 0x91BC
        const val GL_MAX_COMPUTE_IMAGE_UNIFORMS = 0x91BD
        const val GL_MAX_COMPUTE_SHARED_MEMORY_SIZE = 0x8262
        const val GL_MAX_COMPUTE_UNIFORM_COMPONENTS = 0x8263
        const val GL_MAX_COMPUTE_ATOMIC_COUNTER_BUFFERS = 0x8264
        const val GL_MAX_COMPUTE_ATOMIC_COUNTERS = 0x8265
        const val GL_MAX_COMBINED_COMPUTE_UNIFORM_COMPONENTS = 0x8266
        const val GL_MAX_COMPUTE_WORK_GROUP_INVOCATIONS = 0x90EB

        const val GL_MAX_COMPUTE_WORK_GROUP_COUNT = 0x91BE
        const val GL_MAX_COMPUTE_WORK_GROUP_SIZE = 0x91BF

        const val GL_COMPUTE_WORK_GROUP_SIZE = 0x8267

        const val GL_UNIFORM_BLOCK_REFERENCED_BY_COMPUTE_SHADER = 0x90EC

        const val GL_ATOMIC_COUNTER_BUFFER_REFERENCED_BY_COMPUTE_SHADER = 0x90ED

        const val GL_DISPATCH_INDIRECT_BUFFER = 0x90EE

        const val GL_DISPATCH_INDIRECT_BUFFER_BINDING = 0x90EF

        const val GL_COMPUTE_SHADER_BIT = 0x20

        const val GL_DEBUG_OUTPUT = 0x92E0
        const val GL_DEBUG_OUTPUT_SYNCHRONOUS = 0x8242

        const val GL_CONTEXT_FLAG_DEBUG_BIT = 0x2

        const val GL_MAX_DEBUG_MESSAGE_LENGTH = 0x9143
        const val GL_MAX_DEBUG_LOGGED_MESSAGES = 0x9144
        const val GL_DEBUG_LOGGED_MESSAGES = 0x9145
        const val GL_DEBUG_NEXT_LOGGED_MESSAGE_LENGTH = 0x8243
        const val GL_MAX_DEBUG_GROUP_STACK_DEPTH = 0x826C
        const val GL_DEBUG_GROUP_STACK_DEPTH = 0x826D
        const val GL_MAX_LABEL_LENGTH = 0x82E8
        const val GL_DEBUG_CALLBACK_FUNCTION = 0x8244
        const val GL_DEBUG_CALLBACK_USER_PARAM = 0x8245

        const val GL_DEBUG_SOURCE_API = 0x8246
        const val GL_DEBUG_SOURCE_WINDOW_SYSTEM = 0x8247
        const val GL_DEBUG_SOURCE_SHADER_COMPILER = 0x8248
        const val GL_DEBUG_SOURCE_THIRD_PARTY = 0x8249
        const val GL_DEBUG_SOURCE_APPLICATION = 0x824A
        const val GL_DEBUG_SOURCE_OTHER = 0x824B

        const val GL_DEBUG_TYPE_ERROR = 0x824C
        const val GL_DEBUG_TYPE_DEPRECATED_BEHAVIOR = 0x824D
        const val GL_DEBUG_TYPE_UNDEFINED_BEHAVIOR = 0x824E
        const val GL_DEBUG_TYPE_PORTABILITY = 0x824F
        const val GL_DEBUG_TYPE_PERFORMANCE = 0x8250
        const val GL_DEBUG_TYPE_OTHER = 0x8251
        const val GL_DEBUG_TYPE_MARKER = 0x8268

        const val GL_DEBUG_TYPE_PUSH_GROUP = 0x8269
        const val GL_DEBUG_TYPE_POP_GROUP = 0x826A

        const val GL_DEBUG_SEVERITY_HIGH = 0x9146
        const val GL_DEBUG_SEVERITY_MEDIUM = 0x9147
        const val GL_DEBUG_SEVERITY_LOW = 0x9148
        const val GL_DEBUG_SEVERITY_NOTIFICATION = 0x826B

        const val GL_BUFFER = 0x82E0
        const val GL_SHADER = 0x82E1
        const val GL_PROGRAM = 0x82E2
        const val GL_QUERY = 0x82E3
        const val GL_PROGRAM_PIPELINE = 0x82E4
        const val GL_SAMPLER = 0x82E6

        const val GL_MAX_UNIFORM_LOCATIONS = 0x826E

        const val GL_FRAMEBUFFER_DEFAULT_WIDTH = 0x9310
        const val GL_FRAMEBUFFER_DEFAULT_HEIGHT = 0x9311
        const val GL_FRAMEBUFFER_DEFAULT_LAYERS = 0x9312
        const val GL_FRAMEBUFFER_DEFAULT_SAMPLES = 0x9313
        const val GL_FRAMEBUFFER_DEFAULT_FIXED_SAMPLE_LOCATIONS = 0x9314

        const val GL_MAX_FRAMEBUFFER_WIDTH = 0x9315
        const val GL_MAX_FRAMEBUFFER_HEIGHT = 0x9316
        const val GL_MAX_FRAMEBUFFER_LAYERS = 0x9317
        const val GL_MAX_FRAMEBUFFER_SAMPLES = 0x9318

        const val GL_INTERNALFORMAT_SUPPORTED = 0x826F
        const val GL_INTERNALFORMAT_PREFERRED = 0x8270
        const val GL_INTERNALFORMAT_RED_SIZE = 0x8271
        const val GL_INTERNALFORMAT_GREEN_SIZE = 0x8272
        const val GL_INTERNALFORMAT_BLUE_SIZE = 0x8273
        const val GL_INTERNALFORMAT_ALPHA_SIZE = 0x8274
        const val GL_INTERNALFORMAT_DEPTH_SIZE = 0x8275
        const val GL_INTERNALFORMAT_STENCIL_SIZE = 0x8276
        const val GL_INTERNALFORMAT_SHARED_SIZE = 0x8277
        const val GL_INTERNALFORMAT_RED_TYPE = 0x8278
        const val GL_INTERNALFORMAT_GREEN_TYPE = 0x8279
        const val GL_INTERNALFORMAT_BLUE_TYPE = 0x827A
        const val GL_INTERNALFORMAT_ALPHA_TYPE = 0x827B
        const val GL_INTERNALFORMAT_DEPTH_TYPE = 0x827C
        const val GL_INTERNALFORMAT_STENCIL_TYPE = 0x827D
        const val GL_MAX_WIDTH = 0x827E
        const val GL_MAX_HEIGHT = 0x827F
        const val GL_MAX_DEPTH = 0x8280
        const val GL_MAX_LAYERS = 0x8281
        const val GL_MAX_COMBINED_DIMENSIONS = 0x8282
        const val GL_COLOR_COMPONENTS = 0x8283
        const val GL_DEPTH_COMPONENTS = 0x8284
        const val GL_STENCIL_COMPONENTS = 0x8285
        const val GL_COLOR_RENDERABLE = 0x8286
        const val GL_DEPTH_RENDERABLE = 0x8287
        const val GL_STENCIL_RENDERABLE = 0x8288
        const val GL_FRAMEBUFFER_RENDERABLE = 0x8289
        const val GL_FRAMEBUFFER_RENDERABLE_LAYERED = 0x828A
        const val GL_FRAMEBUFFER_BLEND = 0x828B
        const val GL_READ_PIXELS = 0x828C
        const val GL_READ_PIXELS_FORMAT = 0x828D
        const val GL_READ_PIXELS_TYPE = 0x828E
        const val GL_TEXTURE_IMAGE_FORMAT = 0x828F
        const val GL_TEXTURE_IMAGE_TYPE = 0x8290
        const val GL_GET_TEXTURE_IMAGE_FORMAT = 0x8291
        const val GL_GET_TEXTURE_IMAGE_TYPE = 0x8292
        const val GL_MIPMAP = 0x8293
        const val GL_MANUAL_GENERATE_MIPMAP = 0x8294
        const val GL_AUTO_GENERATE_MIPMAP = 0x8295
        const val GL_COLOR_ENCODING = 0x8296
        const val GL_SRGB_READ = 0x8297
        const val GL_SRGB_WRITE = 0x8298
        const val GL_FILTER = 0x829A
        const val GL_VERTEX_TEXTURE = 0x829B
        const val GL_TESS_CONTROL_TEXTURE = 0x829C
        const val GL_TESS_EVALUATION_TEXTURE = 0x829D
        const val GL_GEOMETRY_TEXTURE = 0x829E
        const val GL_FRAGMENT_TEXTURE = 0x829F
        const val GL_COMPUTE_TEXTURE = 0x82A0
        const val GL_TEXTURE_SHADOW = 0x82A1
        const val GL_TEXTURE_GATHER = 0x82A2
        const val GL_TEXTURE_GATHER_SHADOW = 0x82A3
        const val GL_SHADER_IMAGE_LOAD = 0x82A4
        const val GL_SHADER_IMAGE_STORE = 0x82A5
        const val GL_SHADER_IMAGE_ATOMIC = 0x82A6
        const val GL_IMAGE_TEXEL_SIZE = 0x82A7
        const val GL_IMAGE_COMPATIBILITY_CLASS = 0x82A8
        const val GL_IMAGE_PIXEL_FORMAT = 0x82A9
        const val GL_IMAGE_PIXEL_TYPE = 0x82AA
        const val GL_SIMULTANEOUS_TEXTURE_AND_DEPTH_TEST = 0x82AC
        const val GL_SIMULTANEOUS_TEXTURE_AND_STENCIL_TEST = 0x82AD
        const val GL_SIMULTANEOUS_TEXTURE_AND_DEPTH_WRITE = 0x82AE
        const val GL_SIMULTANEOUS_TEXTURE_AND_STENCIL_WRITE = 0x82AF
        const val GL_TEXTURE_COMPRESSED_BLOCK_WIDTH = 0x82B1
        const val GL_TEXTURE_COMPRESSED_BLOCK_HEIGHT = 0x82B2
        const val GL_TEXTURE_COMPRESSED_BLOCK_SIZE = 0x82B3
        const val GL_CLEAR_BUFFER = 0x82B4
        const val GL_TEXTURE_VIEW = 0x82B5
        const val GL_VIEW_COMPATIBILITY_CLASS = 0x82B6

        const val GL_FULL_SUPPORT = 0x82B7
        const val GL_CAVEAT_SUPPORT = 0x82B8
        const val GL_IMAGE_CLASS_4_X_32 = 0x82B9
        const val GL_IMAGE_CLASS_2_X_32 = 0x82BA
        const val GL_IMAGE_CLASS_1_X_32 = 0x82BB
        const val GL_IMAGE_CLASS_4_X_16 = 0x82BC
        const val GL_IMAGE_CLASS_2_X_16 = 0x82BD
        const val GL_IMAGE_CLASS_1_X_16 = 0x82BE
        const val GL_IMAGE_CLASS_4_X_8 = 0x82BF
        const val GL_IMAGE_CLASS_2_X_8 = 0x82C0
        const val GL_IMAGE_CLASS_1_X_8 = 0x82C1
        const val GL_IMAGE_CLASS_11_11_10 = 0x82C2
        const val GL_IMAGE_CLASS_10_10_10_2 = 0x82C3
        const val GL_VIEW_CLASS_128_BITS = 0x82C4
        const val GL_VIEW_CLASS_96_BITS = 0x82C5
        const val GL_VIEW_CLASS_64_BITS = 0x82C6
        const val GL_VIEW_CLASS_48_BITS = 0x82C7
        const val GL_VIEW_CLASS_32_BITS = 0x82C8
        const val GL_VIEW_CLASS_24_BITS = 0x82C9
        const val GL_VIEW_CLASS_16_BITS = 0x82CA
        const val GL_VIEW_CLASS_8_BITS = 0x82CB
        const val GL_VIEW_CLASS_S3TC_DXT1_RGB = 0x82CC
        const val GL_VIEW_CLASS_S3TC_DXT1_RGBA = 0x82CD
        const val GL_VIEW_CLASS_S3TC_DXT3_RGBA = 0x82CE
        const val GL_VIEW_CLASS_S3TC_DXT5_RGBA = 0x82CF
        const val GL_VIEW_CLASS_RGTC1_RED = 0x82D0
        const val GL_VIEW_CLASS_RGTC2_RG = 0x82D1
        const val GL_VIEW_CLASS_BPTC_UNORM = 0x82D2
        const val GL_VIEW_CLASS_BPTC_FLOAT = 0x82D3

        const val GL_UNIFORM = 0x92E1
        const val GL_UNIFORM_BLOCK = 0x92E2
        const val GL_PROGRAM_INPUT = 0x92E3
        const val GL_PROGRAM_OUTPUT = 0x92E4
        const val GL_BUFFER_VARIABLE = 0x92E5
        const val GL_SHADER_STORAGE_BLOCK = 0x92E6
        const val GL_VERTEX_SUBROUTINE = 0x92E8
        const val GL_TESS_CONTROL_SUBROUTINE = 0x92E9
        const val GL_TESS_EVALUATION_SUBROUTINE = 0x92EA
        const val GL_GEOMETRY_SUBROUTINE = 0x92EB
        const val GL_FRAGMENT_SUBROUTINE = 0x92EC
        const val GL_COMPUTE_SUBROUTINE = 0x92ED
        const val GL_VERTEX_SUBROUTINE_UNIFORM = 0x92EE
        const val GL_TESS_CONTROL_SUBROUTINE_UNIFORM = 0x92EF
        const val GL_TESS_EVALUATION_SUBROUTINE_UNIFORM = 0x92F0
        const val GL_GEOMETRY_SUBROUTINE_UNIFORM = 0x92F1
        const val GL_FRAGMENT_SUBROUTINE_UNIFORM = 0x92F2
        const val GL_COMPUTE_SUBROUTINE_UNIFORM = 0x92F3
        const val GL_TRANSFORM_FEEDBACK_VARYING = 0x92F4

        const val GL_ACTIVE_RESOURCES = 0x92F5
        const val GL_MAX_NAME_LENGTH = 0x92F6
        const val GL_MAX_NUM_ACTIVE_VARIABLES = 0x92F7
        const val GL_MAX_NUM_COMPATIBLE_SUBROUTINES = 0x92F8

        const val GL_NAME_LENGTH = 0x92F9
        const val GL_TYPE = 0x92FA
        const val GL_ARRAY_SIZE = 0x92FB
        const val GL_OFFSET = 0x92FC
        const val GL_BLOCK_INDEX = 0x92FD
        const val GL_ARRAY_STRIDE = 0x92FE
        const val GL_MATRIX_STRIDE = 0x92FF
        const val GL_IS_ROW_MAJOR = 0x9300
        const val GL_ATOMIC_COUNTER_BUFFER_INDEX = 0x9301
        const val GL_BUFFER_BINDING = 0x9302
        const val GL_BUFFER_DATA_SIZE = 0x9303
        const val GL_NUM_ACTIVE_VARIABLES = 0x9304
        const val GL_ACTIVE_VARIABLES = 0x9305
        const val GL_REFERENCED_BY_VERTEX_SHADER = 0x9306
        const val GL_REFERENCED_BY_TESS_CONTROL_SHADER = 0x9307
        const val GL_REFERENCED_BY_TESS_EVALUATION_SHADER = 0x9308
        const val GL_REFERENCED_BY_GEOMETRY_SHADER = 0x9309
        const val GL_REFERENCED_BY_FRAGMENT_SHADER = 0x930A
        const val GL_REFERENCED_BY_COMPUTE_SHADER = 0x930B
        const val GL_TOP_LEVEL_ARRAY_SIZE = 0x930C
        const val GL_TOP_LEVEL_ARRAY_STRIDE = 0x930D
        const val GL_LOCATION = 0x930E
        const val GL_LOCATION_INDEX = 0x930F
        const val GL_IS_PER_PATCH = 0x92E7

        const val GL_SHADER_STORAGE_BUFFER = 0x90D2

        const val GL_SHADER_STORAGE_BUFFER_BINDING = 0x90D3

        const val GL_SHADER_STORAGE_BUFFER_START = 0x90D4
        const val GL_SHADER_STORAGE_BUFFER_SIZE = 0x90D5

        const val GL_MAX_VERTEX_SHADER_STORAGE_BLOCKS = 0x90D6
        const val GL_MAX_GEOMETRY_SHADER_STORAGE_BLOCKS = 0x90D7
        const val GL_MAX_TESS_CONTROL_SHADER_STORAGE_BLOCKS = 0x90D8
        const val GL_MAX_TESS_EVALUATION_SHADER_STORAGE_BLOCKS = 0x90D9
        const val GL_MAX_FRAGMENT_SHADER_STORAGE_BLOCKS = 0x90DA
        const val GL_MAX_COMPUTE_SHADER_STORAGE_BLOCKS = 0x90DB
        const val GL_MAX_COMBINED_SHADER_STORAGE_BLOCKS = 0x90DC
        const val GL_MAX_SHADER_STORAGE_BUFFER_BINDINGS = 0x90DD
        const val GL_MAX_SHADER_STORAGE_BLOCK_SIZE = 0x90DE
        const val GL_SHADER_STORAGE_BUFFER_OFFSET_ALIGNMENT = 0x90DF

        const val GL_SHADER_STORAGE_BARRIER_BIT = 0x2000

        const val GL_MAX_COMBINED_SHADER_OUTPUT_RESOURCES = 0x8F39

        const val GL_DEPTH_STENCIL_TEXTURE_MODE = 0x90EA

        const val GL_TEXTURE_BUFFER_OFFSET = 0x919D
        const val GL_TEXTURE_BUFFER_SIZE = 0x919E

        const val GL_TEXTURE_BUFFER_OFFSET_ALIGNMENT = 0x919F

        const val GL_TEXTURE_VIEW_MIN_LEVEL = 0x82DB
        const val GL_TEXTURE_VIEW_NUM_LEVELS = 0x82DC
        const val GL_TEXTURE_VIEW_MIN_LAYER = 0x82DD
        const val GL_TEXTURE_VIEW_NUM_LAYERS = 0x82DE

        const val GL_VERTEX_ATTRIB_BINDING = 0x82D4
        const val GL_VERTEX_ATTRIB_RELATIVE_OFFSET = 0x82D5

        const val GL_VERTEX_BINDING_DIVISOR = 0x82D6
        const val GL_VERTEX_BINDING_OFFSET = 0x82D7
        const val GL_VERTEX_BINDING_STRIDE = 0x82D8
        const val GL_VERTEX_BINDING_BUFFER = 0x8F4F

        const val GL_MAX_VERTEX_ATTRIB_RELATIVE_OFFSET = 0x82D9
        const val GL_MAX_VERTEX_ATTRIB_BINDINGS = 0x82DA
    }

    fun glDispatchCompute(num_groups_x: Int, num_groups_y: Int, num_groups_z: Int)
    fun glDispatchComputeIndirect(indirect: Long)

    fun glCopyImageSubData(
        srcName: Int,
        srcTarget: Int,
        srcLevel: Int,
        srcX: Int,
        srcY: Int,
        srcZ: Int,
        dstName: Int,
        dstTarget: Int,
        dstLevel: Int,
        dstX: Int,
        dstY: Int,
        dstZ: Int,
        srcWidth: Int,
        srcHeight: Int,
        srcDepth: Int
    )

    fun glInvalidateTextSubImage(
        texture: Int,
        level: Int,
        xoffset: Int,
        yoffset: Int,
        zoffset: Int,
        width: Int,
        height: Int,
        depth: Int
    )

    fun glInvalidateTexImage(texture: Int, level: Int)

    fun glInvalidateBufferSubData(buffer: Int, offset: Long, length: Long)
    fun glInvalidateBufferData(buffer: Int)

    fun glMultiDrawArraysIndirect(mode: Int, indirect: Long, drawcount: Int, stride: Int)
    fun glMultiDrawElementsIndirect(mode: Int, type: Int, indirect: Long, drawcount: Int, stride: Int)

    fun glShaderStorageBlockBinding(program: Int, storageBlockIndex: Int, storageBlockBinding: Int)
    fun glGetProgramResourceIndex(program: Int, programInterface: Int, name: String): Int
}