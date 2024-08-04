package dev.luna5ama.glwrapper.enums

import dev.luna5ama.glwrapper.api.*

sealed interface BufferTarget : GLEnum {
    sealed interface Shader : BufferTarget
    sealed interface VertexData : BufferTarget
    sealed interface Copy : BufferTarget
    sealed interface Indirect : BufferTarget
    sealed interface Pixel : BufferTarget

    data object Vertex : VertexData {
        override val value = GL_ARRAY_BUFFER
    }

    data object Index : VertexData {
        override val value = GL_ELEMENT_ARRAY_BUFFER
    }

    data object CopyRead : Copy {
        override val value = GL_COPY_READ_BUFFER
    }

    data object CopyWrite : Copy {
        override val value = GL_COPY_WRITE_BUFFER
    }

    data object Parameter : Indirect {
        override val value = GL_PARAMETER_BUFFER
    }

    data object DrawIndirect : Indirect {
        override val value = GL_DRAW_INDIRECT_BUFFER
    }

    data object DispatchIndirect : Indirect {
        override val value = GL_DISPATCH_INDIRECT_BUFFER
    }

    data object Uniform : Shader {
        override val value = GL_UNIFORM_BUFFER
    }

    data object ShaderStorage : Shader {
        override val value = GL_SHADER_STORAGE_BUFFER
    }

    data object Texture : BufferTarget {
        override val value: Int
            get() = GL_TEXTURE_BUFFER
    }

    data object Query : BufferTarget {
        override val value: Int
            get() = GL_QUERY_BUFFER
    }
}