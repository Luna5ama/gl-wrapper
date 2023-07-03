@file:JvmName("GL31")

package dev.luna5ama.glwrapper.api

interface IGL31 : GLBase {
    companion object {
        const val GL_R8_SNORM = 0x8F94
        const val GL_RG8_SNORM = 0x8F95
        const val GL_RGB8_SNORM = 0x8F96
        const val GL_RGBA8_SNORM = 0x8F97
        const val GL_R16_SNORM = 0x8F98
        const val GL_RG16_SNORM = 0x8F99
        const val GL_RGB16_SNORM = 0x8F9A
        const val GL_RGBA16_SNORM = 0x8F9B

        const val GL_SIGNED_NORMALIZED = 0x8F9C

        const val GL_SAMPLER_BUFFER = 0x8DC2
        const val GL_INT_SAMPLER_2D_RECT = 0x8DCD
        const val GL_INT_SAMPLER_BUFFER = 0x8DD0
        const val GL_UNSIGNED_INT_SAMPLER_2D_RECT = 0x8DD5
        const val GL_UNSIGNED_INT_SAMPLER_BUFFER = 0x8DD8

        const val GL_COPY_READ_BUFFER = 0x8F36
        const val GL_COPY_WRITE_BUFFER = 0x8F37

        const val GL_PRIMITIVE_RESTART = 0x8F9D

        const val GL_PRIMITIVE_RESTART_INDEX = 0x8F9E

        const val GL_TEXTURE_BUFFER = 0x8C2A

        const val GL_MAX_TEXTURE_BUFFER_SIZE = 0x8C2B
        const val GL_TEXTURE_BINDING_BUFFER = 0x8C2C
        const val GL_TEXTURE_BUFFER_DATA_STORE_BINDING = 0x8C2D

        const val GL_TEXTURE_RECTANGLE = 0x84F5

        const val GL_TEXTURE_BINDING_RECTANGLE = 0x84F6

        const val GL_PROXY_TEXTURE_RECTANGLE = 0x84F7

        const val GL_MAX_RECTANGLE_TEXTURE_SIZE = 0x84F8

        const val GL_SAMPLER_2D_RECT = 0x8B63

        const val GL_SAMPLER_2D_RECT_SHADOW = 0x8B64

        const val GL_UNIFORM_BUFFER = 0x8A11

        const val GL_UNIFORM_BUFFER_BINDING = 0x8A28

        const val GL_UNIFORM_BUFFER_START = 0x8A29
        const val GL_UNIFORM_BUFFER_SIZE = 0x8A2A

        const val GL_MAX_VERTEX_UNIFORM_BLOCKS = 0x8A2B
        const val GL_MAX_GEOMETRY_UNIFORM_BLOCKS = 0x8A2C
        const val GL_MAX_FRAGMENT_UNIFORM_BLOCKS = 0x8A2D
        const val GL_MAX_COMBINED_UNIFORM_BLOCKS = 0x8A2E
        const val GL_MAX_UNIFORM_BUFFER_BINDINGS = 0x8A2F
        const val GL_MAX_UNIFORM_BLOCK_SIZE = 0x8A30
        const val GL_MAX_COMBINED_VERTEX_UNIFORM_COMPONENTS = 0x8A31
        const val GL_MAX_COMBINED_GEOMETRY_UNIFORM_COMPONENTS = 0x8A32
        const val GL_MAX_COMBINED_FRAGMENT_UNIFORM_COMPONENTS = 0x8A33
        const val GL_UNIFORM_BUFFER_OFFSET_ALIGNMENT = 0x8A34

        const val GL_ACTIVE_UNIFORM_BLOCK_MAX_NAME_LENGTH = 0x8A35
        const val GL_ACTIVE_UNIFORM_BLOCKS = 0x8A36

        const val GL_UNIFORM_TYPE = 0x8A37
        const val GL_UNIFORM_SIZE = 0x8A38
        const val GL_UNIFORM_NAME_LENGTH = 0x8A39
        const val GL_UNIFORM_BLOCK_INDEX = 0x8A3A
        const val GL_UNIFORM_OFFSET = 0x8A3B
        const val GL_UNIFORM_ARRAY_STRIDE = 0x8A3C
        const val GL_UNIFORM_MATRIX_STRIDE = 0x8A3D
        const val GL_UNIFORM_IS_ROW_MAJOR = 0x8A3E

        const val GL_UNIFORM_BLOCK_BINDING = 0x8A3F
        const val GL_UNIFORM_BLOCK_DATA_SIZE = 0x8A40
        const val GL_UNIFORM_BLOCK_NAME_LENGTH = 0x8A41
        const val GL_UNIFORM_BLOCK_ACTIVE_UNIFORMS = 0x8A42
        const val GL_UNIFORM_BLOCK_ACTIVE_UNIFORM_INDICES = 0x8A43
        const val GL_UNIFORM_BLOCK_REFERENCED_BY_VERTEX_SHADER = 0x8A44
        const val GL_UNIFORM_BLOCK_REFERENCED_BY_GEOMETRY_SHADER = 0x8A45
        const val GL_UNIFORM_BLOCK_REFERENCED_BY_FRAGMENT_SHADER = 0x8A46

        const val GL_INVALID_INDEX = -0x1
    }

    fun glDrawArraysInstanced(mode: Int, first: Int, count: Int, instancecount: Int)
    fun glDrawElementsInstanced(mode: Int, count: Int, type: Int, indices: Long, instancecount: Int)

    fun glPrimitiveRestartIndex(index: Int)
    fun glUniformBlockBinding(program: Int, uniformBlockIndex: Int, uniformBlockBinding: Int)
}