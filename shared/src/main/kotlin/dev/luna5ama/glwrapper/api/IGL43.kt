@file:JvmName("GL43")

package dev.luna5ama.glwrapper.api

interface IGL43 : GLBase {
    companion object {
        internal const val GL_NUM_SHADING_LANGUAGE_VERSIONS = 0x82E9

        internal const val GL_VERTEX_ATTRIB_ARRAY_LONG = 0x874E

        internal const val GL_COMPRESSED_RGB8_ETC2 = 0x9274
        internal const val GL_COMPRESSED_SRGB8_ETC2 = 0x9275
        internal const val GL_COMPRESSED_RGB8_PUNCHTHROUGH_ALPHA1_ETC2 = 0x9276
        internal const val GL_COMPRESSED_SRGB8_PUNCHTHROUGH_ALPHA1_ETC2 = 0x9277
        internal const val GL_COMPRESSED_RGBA8_ETC2_EAC = 0x9278
        internal const val GL_COMPRESSED_SRGB8_ALPHA8_ETC2_EAC = 0x9279
        internal const val GL_COMPRESSED_R11_EAC = 0x9270
        internal const val GL_COMPRESSED_SIGNED_R11_EAC = 0x9271
        internal const val GL_COMPRESSED_RG11_EAC = 0x9272
        internal const val GL_COMPRESSED_SIGNED_RG11_EAC = 0x9273

        internal const val GL_PRIMITIVE_RESTART_FIXED_INDEX = 0x8D69

        internal const val GL_ANY_SAMPLES_PASSED_CONSERVATIVE = 0x8D6A

        internal const val GL_MAX_ELEMENT_INDEX = 0x8D6B

        internal const val GL_TEXTURE_IMMUTABLE_LEVELS = 0x82DF

        internal const val GL_COMPUTE_SHADER = 0x91B9

        internal const val GL_MAX_COMPUTE_UNIFORM_BLOCKS = 0x91BB
        internal const val GL_MAX_COMPUTE_TEXTURE_IMAGE_UNITS = 0x91BC
        internal const val GL_MAX_COMPUTE_IMAGE_UNIFORMS = 0x91BD
        internal const val GL_MAX_COMPUTE_SHARED_MEMORY_SIZE = 0x8262
        internal const val GL_MAX_COMPUTE_UNIFORM_COMPONENTS = 0x8263
        internal const val GL_MAX_COMPUTE_ATOMIC_COUNTER_BUFFERS = 0x8264
        internal const val GL_MAX_COMPUTE_ATOMIC_COUNTERS = 0x8265
        internal const val GL_MAX_COMBINED_COMPUTE_UNIFORM_COMPONENTS = 0x8266
        internal const val GL_MAX_COMPUTE_WORK_GROUP_INVOCATIONS = 0x90EB

        internal const val GL_MAX_COMPUTE_WORK_GROUP_COUNT = 0x91BE
        internal const val GL_MAX_COMPUTE_WORK_GROUP_SIZE = 0x91BF

        internal const val GL_COMPUTE_WORK_GROUP_SIZE = 0x8267

        internal const val GL_UNIFORM_BLOCK_REFERENCED_BY_COMPUTE_SHADER = 0x90EC

        internal const val GL_ATOMIC_COUNTER_BUFFER_REFERENCED_BY_COMPUTE_SHADER = 0x90ED

        internal const val GL_DISPATCH_INDIRECT_BUFFER = 0x90EE

        internal const val GL_DISPATCH_INDIRECT_BUFFER_BINDING = 0x90EF

        internal const val GL_COMPUTE_SHADER_BIT = 0x20

        internal const val GL_DEBUG_OUTPUT = 0x92E0
        internal const val GL_DEBUG_OUTPUT_SYNCHRONOUS = 0x8242

        internal const val GL_CONTEXT_FLAG_DEBUG_BIT = 0x2

        internal const val GL_MAX_DEBUG_MESSAGE_LENGTH = 0x9143
        internal const val GL_MAX_DEBUG_LOGGED_MESSAGES = 0x9144
        internal const val GL_DEBUG_LOGGED_MESSAGES = 0x9145
        internal const val GL_DEBUG_NEXT_LOGGED_MESSAGE_LENGTH = 0x8243
        internal const val GL_MAX_DEBUG_GROUP_STACK_DEPTH = 0x826C
        internal const val GL_DEBUG_GROUP_STACK_DEPTH = 0x826D
        internal const val GL_MAX_LABEL_LENGTH = 0x82E8
        internal const val GL_DEBUG_CALLBACK_FUNCTION = 0x8244
        internal const val GL_DEBUG_CALLBACK_USER_PARAM = 0x8245

        internal const val GL_DEBUG_SOURCE_API = 0x8246
        internal const val GL_DEBUG_SOURCE_WINDOW_SYSTEM = 0x8247
        internal const val GL_DEBUG_SOURCE_SHADER_COMPILER = 0x8248
        internal const val GL_DEBUG_SOURCE_THIRD_PARTY = 0x8249
        internal const val GL_DEBUG_SOURCE_APPLICATION = 0x824A
        internal const val GL_DEBUG_SOURCE_OTHER = 0x824B

        internal const val GL_DEBUG_TYPE_ERROR = 0x824C
        internal const val GL_DEBUG_TYPE_DEPRECATED_BEHAVIOR = 0x824D
        internal const val GL_DEBUG_TYPE_UNDEFINED_BEHAVIOR = 0x824E
        internal const val GL_DEBUG_TYPE_PORTABILITY = 0x824F
        internal const val GL_DEBUG_TYPE_PERFORMANCE = 0x8250
        internal const val GL_DEBUG_TYPE_OTHER = 0x8251
        internal const val GL_DEBUG_TYPE_MARKER = 0x8268

        internal const val GL_DEBUG_TYPE_PUSH_GROUP = 0x8269
        internal const val GL_DEBUG_TYPE_POP_GROUP = 0x826A

        internal const val GL_DEBUG_SEVERITY_HIGH = 0x9146
        internal const val GL_DEBUG_SEVERITY_MEDIUM = 0x9147
        internal const val GL_DEBUG_SEVERITY_LOW = 0x9148
        internal const val GL_DEBUG_SEVERITY_NOTIFICATION = 0x826B

        internal const val GL_BUFFER = 0x82E0
        internal const val GL_SHADER = 0x82E1
        internal const val GL_PROGRAM = 0x82E2
        internal const val GL_QUERY = 0x82E3
        internal const val GL_PROGRAM_PIPELINE = 0x82E4
        internal const val GL_SAMPLER = 0x82E6

        internal const val GL_MAX_UNIFORM_LOCATIONS = 0x826E

        internal const val GL_FRAMEBUFFER_DEFAULT_WIDTH = 0x9310
        internal const val GL_FRAMEBUFFER_DEFAULT_HEIGHT = 0x9311
        internal const val GL_FRAMEBUFFER_DEFAULT_LAYERS = 0x9312
        internal const val GL_FRAMEBUFFER_DEFAULT_SAMPLES = 0x9313
        internal const val GL_FRAMEBUFFER_DEFAULT_FIXED_SAMPLE_LOCATIONS = 0x9314

        internal const val GL_MAX_FRAMEBUFFER_WIDTH = 0x9315
        internal const val GL_MAX_FRAMEBUFFER_HEIGHT = 0x9316
        internal const val GL_MAX_FRAMEBUFFER_LAYERS = 0x9317
        internal const val GL_MAX_FRAMEBUFFER_SAMPLES = 0x9318

        internal const val GL_INTERNALFORMAT_SUPPORTED = 0x826F
        internal const val GL_INTERNALFORMAT_PREFERRED = 0x8270
        internal const val GL_INTERNALFORMAT_RED_SIZE = 0x8271
        internal const val GL_INTERNALFORMAT_GREEN_SIZE = 0x8272
        internal const val GL_INTERNALFORMAT_BLUE_SIZE = 0x8273
        internal const val GL_INTERNALFORMAT_ALPHA_SIZE = 0x8274
        internal const val GL_INTERNALFORMAT_DEPTH_SIZE = 0x8275
        internal const val GL_INTERNALFORMAT_STENCIL_SIZE = 0x8276
        internal const val GL_INTERNALFORMAT_SHARED_SIZE = 0x8277
        internal const val GL_INTERNALFORMAT_RED_TYPE = 0x8278
        internal const val GL_INTERNALFORMAT_GREEN_TYPE = 0x8279
        internal const val GL_INTERNALFORMAT_BLUE_TYPE = 0x827A
        internal const val GL_INTERNALFORMAT_ALPHA_TYPE = 0x827B
        internal const val GL_INTERNALFORMAT_DEPTH_TYPE = 0x827C
        internal const val GL_INTERNALFORMAT_STENCIL_TYPE = 0x827D
        internal const val GL_MAX_WIDTH = 0x827E
        internal const val GL_MAX_HEIGHT = 0x827F
        internal const val GL_MAX_DEPTH = 0x8280
        internal const val GL_MAX_LAYERS = 0x8281
        internal const val GL_MAX_COMBINED_DIMENSIONS = 0x8282
        internal const val GL_COLOR_COMPONENTS = 0x8283
        internal const val GL_DEPTH_COMPONENTS = 0x8284
        internal const val GL_STENCIL_COMPONENTS = 0x8285
        internal const val GL_COLOR_RENDERABLE = 0x8286
        internal const val GL_DEPTH_RENDERABLE = 0x8287
        internal const val GL_STENCIL_RENDERABLE = 0x8288
        internal const val GL_FRAMEBUFFER_RENDERABLE = 0x8289
        internal const val GL_FRAMEBUFFER_RENDERABLE_LAYERED = 0x828A
        internal const val GL_FRAMEBUFFER_BLEND = 0x828B
        internal const val GL_READ_PIXELS = 0x828C
        internal const val GL_READ_PIXELS_FORMAT = 0x828D
        internal const val GL_READ_PIXELS_TYPE = 0x828E
        internal const val GL_TEXTURE_IMAGE_FORMAT = 0x828F
        internal const val GL_TEXTURE_IMAGE_TYPE = 0x8290
        internal const val GL_GET_TEXTURE_IMAGE_FORMAT = 0x8291
        internal const val GL_GET_TEXTURE_IMAGE_TYPE = 0x8292
        internal const val GL_MIPMAP = 0x8293
        internal const val GL_MANUAL_GENERATE_MIPMAP = 0x8294
        internal const val GL_AUTO_GENERATE_MIPMAP = 0x8295
        internal const val GL_COLOR_ENCODING = 0x8296
        internal const val GL_SRGB_READ = 0x8297
        internal const val GL_SRGB_WRITE = 0x8298
        internal const val GL_FILTER = 0x829A
        internal const val GL_VERTEX_TEXTURE = 0x829B
        internal const val GL_TESS_CONTROL_TEXTURE = 0x829C
        internal const val GL_TESS_EVALUATION_TEXTURE = 0x829D
        internal const val GL_GEOMETRY_TEXTURE = 0x829E
        internal const val GL_FRAGMENT_TEXTURE = 0x829F
        internal const val GL_COMPUTE_TEXTURE = 0x82A0
        internal const val GL_TEXTURE_SHADOW = 0x82A1
        internal const val GL_TEXTURE_GATHER = 0x82A2
        internal const val GL_TEXTURE_GATHER_SHADOW = 0x82A3
        internal const val GL_SHADER_IMAGE_LOAD = 0x82A4
        internal const val GL_SHADER_IMAGE_STORE = 0x82A5
        internal const val GL_SHADER_IMAGE_ATOMIC = 0x82A6
        internal const val GL_IMAGE_TEXEL_SIZE = 0x82A7
        internal const val GL_IMAGE_COMPATIBILITY_CLASS = 0x82A8
        internal const val GL_IMAGE_PIXEL_FORMAT = 0x82A9
        internal const val GL_IMAGE_PIXEL_TYPE = 0x82AA
        internal const val GL_SIMULTANEOUS_TEXTURE_AND_DEPTH_TEST = 0x82AC
        internal const val GL_SIMULTANEOUS_TEXTURE_AND_STENCIL_TEST = 0x82AD
        internal const val GL_SIMULTANEOUS_TEXTURE_AND_DEPTH_WRITE = 0x82AE
        internal const val GL_SIMULTANEOUS_TEXTURE_AND_STENCIL_WRITE = 0x82AF
        internal const val GL_TEXTURE_COMPRESSED_BLOCK_WIDTH = 0x82B1
        internal const val GL_TEXTURE_COMPRESSED_BLOCK_HEIGHT = 0x82B2
        internal const val GL_TEXTURE_COMPRESSED_BLOCK_SIZE = 0x82B3
        internal const val GL_CLEAR_BUFFER = 0x82B4
        internal const val GL_TEXTURE_VIEW = 0x82B5
        internal const val GL_VIEW_COMPATIBILITY_CLASS = 0x82B6

        internal const val GL_FULL_SUPPORT = 0x82B7
        internal const val GL_CAVEAT_SUPPORT = 0x82B8
        internal const val GL_IMAGE_CLASS_4_X_32 = 0x82B9
        internal const val GL_IMAGE_CLASS_2_X_32 = 0x82BA
        internal const val GL_IMAGE_CLASS_1_X_32 = 0x82BB
        internal const val GL_IMAGE_CLASS_4_X_16 = 0x82BC
        internal const val GL_IMAGE_CLASS_2_X_16 = 0x82BD
        internal const val GL_IMAGE_CLASS_1_X_16 = 0x82BE
        internal const val GL_IMAGE_CLASS_4_X_8 = 0x82BF
        internal const val GL_IMAGE_CLASS_2_X_8 = 0x82C0
        internal const val GL_IMAGE_CLASS_1_X_8 = 0x82C1
        internal const val GL_IMAGE_CLASS_11_11_10 = 0x82C2
        internal const val GL_IMAGE_CLASS_10_10_10_2 = 0x82C3
        internal const val GL_VIEW_CLASS_128_BITS = 0x82C4
        internal const val GL_VIEW_CLASS_96_BITS = 0x82C5
        internal const val GL_VIEW_CLASS_64_BITS = 0x82C6
        internal const val GL_VIEW_CLASS_48_BITS = 0x82C7
        internal const val GL_VIEW_CLASS_32_BITS = 0x82C8
        internal const val GL_VIEW_CLASS_24_BITS = 0x82C9
        internal const val GL_VIEW_CLASS_16_BITS = 0x82CA
        internal const val GL_VIEW_CLASS_8_BITS = 0x82CB
        internal const val GL_VIEW_CLASS_S3TC_DXT1_RGB = 0x82CC
        internal const val GL_VIEW_CLASS_S3TC_DXT1_RGBA = 0x82CD
        internal const val GL_VIEW_CLASS_S3TC_DXT3_RGBA = 0x82CE
        internal const val GL_VIEW_CLASS_S3TC_DXT5_RGBA = 0x82CF
        internal const val GL_VIEW_CLASS_RGTC1_RED = 0x82D0
        internal const val GL_VIEW_CLASS_RGTC2_RG = 0x82D1
        internal const val GL_VIEW_CLASS_BPTC_UNORM = 0x82D2
        internal const val GL_VIEW_CLASS_BPTC_FLOAT = 0x82D3

        internal const val GL_UNIFORM = 0x92E1
        internal const val GL_UNIFORM_BLOCK = 0x92E2
        internal const val GL_PROGRAM_INPUT = 0x92E3
        internal const val GL_PROGRAM_OUTPUT = 0x92E4
        internal const val GL_BUFFER_VARIABLE = 0x92E5
        internal const val GL_SHADER_STORAGE_BLOCK = 0x92E6
        internal const val GL_VERTEX_SUBROUTINE = 0x92E8
        internal const val GL_TESS_CONTROL_SUBROUTINE = 0x92E9
        internal const val GL_TESS_EVALUATION_SUBROUTINE = 0x92EA
        internal const val GL_GEOMETRY_SUBROUTINE = 0x92EB
        internal const val GL_FRAGMENT_SUBROUTINE = 0x92EC
        internal const val GL_COMPUTE_SUBROUTINE = 0x92ED
        internal const val GL_VERTEX_SUBROUTINE_UNIFORM = 0x92EE
        internal const val GL_TESS_CONTROL_SUBROUTINE_UNIFORM = 0x92EF
        internal const val GL_TESS_EVALUATION_SUBROUTINE_UNIFORM = 0x92F0
        internal const val GL_GEOMETRY_SUBROUTINE_UNIFORM = 0x92F1
        internal const val GL_FRAGMENT_SUBROUTINE_UNIFORM = 0x92F2
        internal const val GL_COMPUTE_SUBROUTINE_UNIFORM = 0x92F3
        internal const val GL_TRANSFORM_FEEDBACK_VARYING = 0x92F4

        internal const val GL_ACTIVE_RESOURCES = 0x92F5
        internal const val GL_MAX_NAME_LENGTH = 0x92F6
        internal const val GL_MAX_NUM_ACTIVE_VARIABLES = 0x92F7
        internal const val GL_MAX_NUM_COMPATIBLE_SUBROUTINES = 0x92F8

        internal const val GL_NAME_LENGTH = 0x92F9
        internal const val GL_TYPE = 0x92FA
        internal const val GL_ARRAY_SIZE = 0x92FB
        internal const val GL_OFFSET = 0x92FC
        internal const val GL_BLOCK_INDEX = 0x92FD
        internal const val GL_ARRAY_STRIDE = 0x92FE
        internal const val GL_MATRIX_STRIDE = 0x92FF
        internal const val GL_IS_ROW_MAJOR = 0x9300
        internal const val GL_ATOMIC_COUNTER_BUFFER_INDEX = 0x9301
        internal const val GL_BUFFER_BINDING = 0x9302
        internal const val GL_BUFFER_DATA_SIZE = 0x9303
        internal const val GL_NUM_ACTIVE_VARIABLES = 0x9304
        internal const val GL_ACTIVE_VARIABLES = 0x9305
        internal const val GL_REFERENCED_BY_VERTEX_SHADER = 0x9306
        internal const val GL_REFERENCED_BY_TESS_CONTROL_SHADER = 0x9307
        internal const val GL_REFERENCED_BY_TESS_EVALUATION_SHADER = 0x9308
        internal const val GL_REFERENCED_BY_GEOMETRY_SHADER = 0x9309
        internal const val GL_REFERENCED_BY_FRAGMENT_SHADER = 0x930A
        internal const val GL_REFERENCED_BY_COMPUTE_SHADER = 0x930B
        internal const val GL_TOP_LEVEL_ARRAY_SIZE = 0x930C
        internal const val GL_TOP_LEVEL_ARRAY_STRIDE = 0x930D
        internal const val GL_LOCATION = 0x930E
        internal const val GL_LOCATION_INDEX = 0x930F
        internal const val GL_IS_PER_PATCH = 0x92E7

        internal const val GL_SHADER_STORAGE_BUFFER = 0x90D2

        internal const val GL_SHADER_STORAGE_BUFFER_BINDING = 0x90D3

        internal const val GL_SHADER_STORAGE_BUFFER_START = 0x90D4
        internal const val GL_SHADER_STORAGE_BUFFER_SIZE = 0x90D5

        internal const val GL_MAX_VERTEX_SHADER_STORAGE_BLOCKS = 0x90D6
        internal const val GL_MAX_GEOMETRY_SHADER_STORAGE_BLOCKS = 0x90D7
        internal const val GL_MAX_TESS_CONTROL_SHADER_STORAGE_BLOCKS = 0x90D8
        internal const val GL_MAX_TESS_EVALUATION_SHADER_STORAGE_BLOCKS = 0x90D9
        internal const val GL_MAX_FRAGMENT_SHADER_STORAGE_BLOCKS = 0x90DA
        internal const val GL_MAX_COMPUTE_SHADER_STORAGE_BLOCKS = 0x90DB
        internal const val GL_MAX_COMBINED_SHADER_STORAGE_BLOCKS = 0x90DC
        internal const val GL_MAX_SHADER_STORAGE_BUFFER_BINDINGS = 0x90DD
        internal const val GL_MAX_SHADER_STORAGE_BLOCK_SIZE = 0x90DE
        internal const val GL_SHADER_STORAGE_BUFFER_OFFSET_ALIGNMENT = 0x90DF

        internal const val GL_SHADER_STORAGE_BARRIER_BIT = 0x2000

        internal const val GL_MAX_COMBINED_SHADER_OUTPUT_RESOURCES = 0x8F39

        internal const val GL_DEPTH_STENCIL_TEXTURE_MODE = 0x90EA

        internal const val GL_TEXTURE_BUFFER_OFFSET = 0x919D
        internal const val GL_TEXTURE_BUFFER_SIZE = 0x919E

        internal const val GL_TEXTURE_BUFFER_OFFSET_ALIGNMENT = 0x919F

        internal const val GL_TEXTURE_VIEW_MIN_LEVEL = 0x82DB
        internal const val GL_TEXTURE_VIEW_NUM_LEVELS = 0x82DC
        internal const val GL_TEXTURE_VIEW_MIN_LAYER = 0x82DD
        internal const val GL_TEXTURE_VIEW_NUM_LAYERS = 0x82DE

        internal const val GL_VERTEX_ATTRIB_BINDING = 0x82D4
        internal const val GL_VERTEX_ATTRIB_RELATIVE_OFFSET = 0x82D5

        internal const val GL_VERTEX_BINDING_DIVISOR = 0x82D6
        internal const val GL_VERTEX_BINDING_OFFSET = 0x82D7
        internal const val GL_VERTEX_BINDING_STRIDE = 0x82D8
        internal const val GL_VERTEX_BINDING_BUFFER = 0x8F4F

        internal const val GL_MAX_VERTEX_ATTRIB_RELATIVE_OFFSET = 0x82D9
        internal const val GL_MAX_VERTEX_ATTRIB_BINDINGS = 0x82DA
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

abstract class PatchedGL43(protected val delegate: IGL43) : IGL43 by delegate