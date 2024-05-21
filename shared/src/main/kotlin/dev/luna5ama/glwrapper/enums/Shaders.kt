package dev.luna5ama.glwrapper.enums

import dev.luna5ama.glwrapper.api.*

enum class ShaderStage(
    override val value: Int,
    val subroutine: Subroutine,
    val subroutineUniform: SubroutineUniform
) : GLEnum {
    VertexShader(GL_VERTEX_SHADER, Subroutine.VertexShader, SubroutineUniform.VertexShader),
    TessCtrlShader(GL_TESS_CONTROL_SHADER, Subroutine.TessControlShader, SubroutineUniform.TessControlShader),
    TessEvalShader(
        GL_TESS_EVALUATION_SHADER,
        Subroutine.TessEvaluationShader,
        SubroutineUniform.TessEvaluationShader
    ),
    GeometryShader(GL_GEOMETRY_SHADER, Subroutine.GeometryShader, SubroutineUniform.GeometryShader),
    FragmentShader(GL_FRAGMENT_SHADER, Subroutine.FragmentShader, SubroutineUniform.FragmentShader),
    ComputeShader(GL_COMPUTE_SHADER, Subroutine.ComputeShader, SubroutineUniform.ComputeShader);

    enum class Subroutine(override val value: Int, shaderStage: () -> ShaderStage) : GLEnum {
        VertexShader(GL_VERTEX_SUBROUTINE, { ShaderStage.VertexShader }),
        TessControlShader(GL_TESS_CONTROL_SUBROUTINE, { ShaderStage.TessCtrlShader }),
        TessEvaluationShader(GL_TESS_EVALUATION_SUBROUTINE, { ShaderStage.TessEvalShader }),
        GeometryShader(GL_GEOMETRY_SUBROUTINE, { ShaderStage.GeometryShader }),
        FragmentShader(GL_FRAGMENT_SUBROUTINE, { ShaderStage.FragmentShader }),
        ComputeShader(GL_COMPUTE_SUBROUTINE, { ShaderStage.ComputeShader });

        val shaderStage by lazy(shaderStage)

        companion object {
            operator fun get(value: Int) = when (value) {
                GL_VERTEX_SUBROUTINE -> VertexShader
                GL_TESS_CONTROL_SUBROUTINE -> TessControlShader
                GL_TESS_EVALUATION_SUBROUTINE -> TessEvaluationShader
                GL_GEOMETRY_SUBROUTINE -> GeometryShader
                GL_FRAGMENT_SUBROUTINE -> FragmentShader
                GL_COMPUTE_SUBROUTINE -> ComputeShader
                else -> throw IllegalArgumentException("Invalid value: $value")
            }
        }
    }

    enum class SubroutineUniform(override val value: Int, shaderStage: () -> ShaderStage) : GLEnum {
        VertexShader(GL_VERTEX_SUBROUTINE_UNIFORM, { ShaderStage.VertexShader }),
        TessControlShader(GL_TESS_CONTROL_SUBROUTINE_UNIFORM, { ShaderStage.TessCtrlShader }),
        TessEvaluationShader(GL_TESS_EVALUATION_SUBROUTINE_UNIFORM, { ShaderStage.TessEvalShader }),
        GeometryShader(GL_GEOMETRY_SUBROUTINE_UNIFORM, { ShaderStage.GeometryShader }),
        FragmentShader(GL_FRAGMENT_SUBROUTINE_UNIFORM, { ShaderStage.FragmentShader }),
        ComputeShader(GL_COMPUTE_SUBROUTINE_UNIFORM, { ShaderStage.ComputeShader });

        val shaderStage by lazy(shaderStage)

        companion object {
            operator fun get(value: Int) = when (value) {
                GL_VERTEX_SUBROUTINE_UNIFORM -> VertexShader
                GL_TESS_CONTROL_SUBROUTINE_UNIFORM -> TessControlShader
                GL_TESS_EVALUATION_SUBROUTINE_UNIFORM -> TessEvaluationShader
                GL_GEOMETRY_SUBROUTINE_UNIFORM -> GeometryShader
                GL_FRAGMENT_SUBROUTINE_UNIFORM -> FragmentShader
                GL_COMPUTE_SUBROUTINE_UNIFORM -> ComputeShader
                else -> throw IllegalArgumentException("Invalid value: $value")
            }
        }
    }

    companion object {
        operator fun get(value: Int) = when (value) {
            GL_VERTEX_SHADER -> VertexShader
            GL_TESS_CONTROL_SHADER -> TessCtrlShader
            GL_TESS_EVALUATION_SHADER -> TessEvalShader
            GL_GEOMETRY_SHADER -> GeometryShader
            GL_FRAGMENT_SHADER -> FragmentShader
            GL_COMPUTE_SHADER -> ComputeShader
            else -> throw IllegalArgumentException("Invalid value: $value")
        }
    }
}