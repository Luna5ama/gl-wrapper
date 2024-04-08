package dev.luna5ama.glwrapper.impl.enums

import dev.luna5ama.glwrapper.api.*

enum class WrapMode(override val value: Int) : GLEnum {
    ClampToEdge(GL_CLAMP_TO_EDGE),
    ClampToBorder(GL_CLAMP_TO_BORDER),
    MirroredRepeat(GL_MIRRORED_REPEAT),
    MirrorClampToEdge(GL_MIRROR_CLAMP_TO_EDGE),
    Repeat(GL_REPEAT)
}

sealed interface FilterMode : GLEnum {
    sealed interface Min : FilterMode
    sealed interface Mag : FilterMode
    sealed interface Mipmap : FilterMode

    data object Nearest: Min, Mag {
        override val value = GL_NEAREST
    }

    data object Linear: Min, Mag {
        override val value = GL_LINEAR
    }

    data object NearestMipmapNearest: Min, Mipmap {
        override val value = GL_NEAREST_MIPMAP_NEAREST
    }

    data object LinearMipmapNearest: Min, Mipmap {
        override val value = GL_LINEAR_MIPMAP_NEAREST
    }

    data object NearestMipmapLinear: Min, Mipmap {
        override val value = GL_NEAREST_MIPMAP_LINEAR
    }

    data object LinearMipmapLinear: Min, Mipmap {
        override val value = GL_LINEAR_MIPMAP_LINEAR
    }
}

enum class CompareMode(override val value: Int) : GLEnum {
    None(GL_NONE),
    CompareRefToTexture(GL_COMPARE_REF_TO_TEXTURE)
}

enum class CompareFunc(override val value: Int) : GLEnum {
    Never(GL_NEVER),
    Less(GL_LESS),
    Equal(GL_EQUAL),
    LessEqual(GL_LEQUAL),
    Greater(GL_GREATER),
    NotEqual(GL_NOTEQUAL),
    GreaterEqual(GL_GEQUAL),
    Always(GL_ALWAYS)
}