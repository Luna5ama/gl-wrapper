@file:JvmName("GL31")

package dev.luna5ama.glwrapper.api

interface IGL31 : GLBase {
    companion object {
        internal const val GL_R8_SNORM = 0x8F94
        internal const val GL_RG8_SNORM = 0x8F95
        internal const val GL_RGB8_SNORM = 0x8F96
        internal const val GL_RGBA8_SNORM = 0x8F97
        internal const val GL_R16_SNORM = 0x8F98
        internal const val GL_RG16_SNORM = 0x8F99
        internal const val GL_RGB16_SNORM = 0x8F9A
        internal const val GL_RGBA16_SNORM = 0x8F9B

        internal const val GL_SIGNED_NORMALIZED = 0x8F9C

        internal const val GL_SAMPLER_BUFFER = 0x8DC2
        internal const val GL_INT_SAMPLER_2D_RECT = 0x8DCD
        internal const val GL_INT_SAMPLER_BUFFER = 0x8DD0
        internal const val GL_UNSIGNED_INT_SAMPLER_2D_RECT = 0x8DD5
        internal const val GL_UNSIGNED_INT_SAMPLER_BUFFER = 0x8DD8

        internal const val GL_COPY_READ_BUFFER = 0x8F36
        internal const val GL_COPY_WRITE_BUFFER = 0x8F37

        internal const val GL_PRIMITIVE_RESTART = 0x8F9D

        internal const val GL_PRIMITIVE_RESTART_INDEX = 0x8F9E

        internal const val GL_TEXTURE_BUFFER = 0x8C2A

        internal const val GL_MAX_TEXTURE_BUFFER_SIZE = 0x8C2B
        internal const val GL_TEXTURE_BINDING_BUFFER = 0x8C2C
        internal const val GL_TEXTURE_BUFFER_DATA_STORE_BINDING = 0x8C2D

        internal const val GL_TEXTURE_RECTANGLE = 0x84F5

        internal const val GL_TEXTURE_BINDING_RECTANGLE = 0x84F6

        internal const val GL_PROXY_TEXTURE_RECTANGLE = 0x84F7

        internal const val GL_MAX_RECTANGLE_TEXTURE_SIZE = 0x84F8

        internal const val GL_SAMPLER_2D_RECT = 0x8B63

        internal const val GL_SAMPLER_2D_RECT_SHADOW = 0x8B64

        internal const val GL_UNIFORM_BUFFER = 0x8A11

        internal const val GL_UNIFORM_BUFFER_BINDING = 0x8A28

        internal const val GL_UNIFORM_BUFFER_START = 0x8A29
        internal const val GL_UNIFORM_BUFFER_SIZE = 0x8A2A

        internal const val GL_MAX_VERTEX_UNIFORM_BLOCKS = 0x8A2B
        internal const val GL_MAX_GEOMETRY_UNIFORM_BLOCKS = 0x8A2C
        internal const val GL_MAX_FRAGMENT_UNIFORM_BLOCKS = 0x8A2D
        internal const val GL_MAX_COMBINED_UNIFORM_BLOCKS = 0x8A2E
        internal const val GL_MAX_UNIFORM_BUFFER_BINDINGS = 0x8A2F
        internal const val GL_MAX_UNIFORM_BLOCK_SIZE = 0x8A30
        internal const val GL_MAX_COMBINED_VERTEX_UNIFORM_COMPONENTS = 0x8A31
        internal const val GL_MAX_COMBINED_GEOMETRY_UNIFORM_COMPONENTS = 0x8A32
        internal const val GL_MAX_COMBINED_FRAGMENT_UNIFORM_COMPONENTS = 0x8A33
        internal const val GL_UNIFORM_BUFFER_OFFSET_ALIGNMENT = 0x8A34

        internal const val GL_ACTIVE_UNIFORM_BLOCK_MAX_NAME_LENGTH = 0x8A35
        internal const val GL_ACTIVE_UNIFORM_BLOCKS = 0x8A36

        internal const val GL_UNIFORM_TYPE = 0x8A37
        internal const val GL_UNIFORM_SIZE = 0x8A38
        internal const val GL_UNIFORM_NAME_LENGTH = 0x8A39
        internal const val GL_UNIFORM_BLOCK_INDEX = 0x8A3A
        internal const val GL_UNIFORM_OFFSET = 0x8A3B
        internal const val GL_UNIFORM_ARRAY_STRIDE = 0x8A3C
        internal const val GL_UNIFORM_MATRIX_STRIDE = 0x8A3D
        internal const val GL_UNIFORM_IS_ROW_MAJOR = 0x8A3E

        internal const val GL_UNIFORM_BLOCK_BINDING = 0x8A3F
        internal const val GL_UNIFORM_BLOCK_DATA_SIZE = 0x8A40
        internal const val GL_UNIFORM_BLOCK_NAME_LENGTH = 0x8A41
        internal const val GL_UNIFORM_BLOCK_ACTIVE_UNIFORMS = 0x8A42
        internal const val GL_UNIFORM_BLOCK_ACTIVE_UNIFORM_INDICES = 0x8A43
        internal const val GL_UNIFORM_BLOCK_REFERENCED_BY_VERTEX_SHADER = 0x8A44
        internal const val GL_UNIFORM_BLOCK_REFERENCED_BY_GEOMETRY_SHADER = 0x8A45
        internal const val GL_UNIFORM_BLOCK_REFERENCED_BY_FRAGMENT_SHADER = 0x8A46

        internal const val GL_INVALID_INDEX = -0x1
    }

    fun glDrawArraysInstanced(mode: Int, first: Int, count: Int, instancecount: Int)
    fun glDrawElementsInstanced(mode: Int, count: Int, type: Int, indices: Long, instancecount: Int)

    fun glPrimitiveRestartIndex(index: Int)
    fun glUniformBlockBinding(program: Int, uniformBlockIndex: Int, uniformBlockBinding: Int)
}

abstract class PatchedGL31(protected val delegate: IGL31) : IGL31 by delegate