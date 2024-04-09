package dev.luna5ama.glwrapper.enums

import dev.luna5ama.glwrapper.api.*

enum class ShaderType(override val value: Int) : GLEnum {
    VERTEX_SHADER(GL_VERTEX_SHADER),
    TESS_CONTROL_SHADER(GL_TESS_CONTROL_SHADER),
    TESS_EVALUATION_SHADER(GL_TESS_EVALUATION_SHADER),
    GEOMETRY_SHADER(GL_GEOMETRY_SHADER),
    FRAGMENT_SHADER(GL_FRAGMENT_SHADER),
    COMPUTE_SHADER(GL_COMPUTE_SHADER);

    companion object {
        operator fun get(value: Int) = when (value) {
            GL_VERTEX_SHADER -> VERTEX_SHADER
            GL_TESS_CONTROL_SHADER -> TESS_CONTROL_SHADER
            GL_TESS_EVALUATION_SHADER -> TESS_EVALUATION_SHADER
            GL_GEOMETRY_SHADER -> GEOMETRY_SHADER
            GL_FRAGMENT_SHADER -> FRAGMENT_SHADER
            GL_COMPUTE_SHADER -> COMPUTE_SHADER
            else -> throw IllegalArgumentException("Invalid value: $value")
        }
    }
}