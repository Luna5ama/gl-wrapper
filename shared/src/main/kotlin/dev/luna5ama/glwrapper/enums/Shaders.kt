package dev.luna5ama.glwrapper.enums

import dev.luna5ama.glwrapper.base.*

enum class ShaderStage(
    override val value: Int,
    val shortName: String,
    val subroutine: Subroutine,
    val subroutineUniform: SubroutineUniform
) : GLEnum {
    VertexShader(GL_VERTEX_SHADER, "vert", Subroutine.VertexShader, SubroutineUniform.VertexShader),
    TessCtrlShader(GL_TESS_CONTROL_SHADER, "tesc", Subroutine.TessCtrlShader, SubroutineUniform.TessCtrlShader),
    TessEvalShader(GL_TESS_EVALUATION_SHADER, "tese", Subroutine.TessEvalShader, SubroutineUniform.TessEvalShader),
    GeometryShader(GL_GEOMETRY_SHADER, "geom", Subroutine.GeometryShader, SubroutineUniform.GeometryShader),
    FragmentShader(GL_FRAGMENT_SHADER, "frag", Subroutine.FragmentShader, SubroutineUniform.FragmentShader),
    ComputeShader(GL_COMPUTE_SHADER, "comp", Subroutine.ComputeShader, SubroutineUniform.ComputeShader),
    TaskShader(GL_TASK_SHADER_NV, "task", Subroutine.TaskShader, SubroutineUniform.TaskShader),
    MeshShader(GL_MESH_SHADER_NV, "mesh", Subroutine.MeshShader, SubroutineUniform.MeshShader),;

    enum class Subroutine(override val value: Int, shaderStage: () -> ShaderStage) : GLEnum {
        VertexShader(GL_VERTEX_SUBROUTINE, { ShaderStage.VertexShader }),
        TessCtrlShader(GL_TESS_CONTROL_SUBROUTINE, { ShaderStage.TessCtrlShader }),
        TessEvalShader(GL_TESS_EVALUATION_SUBROUTINE, { ShaderStage.TessEvalShader }),
        GeometryShader(GL_GEOMETRY_SUBROUTINE, { ShaderStage.GeometryShader }),
        FragmentShader(GL_FRAGMENT_SUBROUTINE, { ShaderStage.FragmentShader }),
        ComputeShader(GL_COMPUTE_SUBROUTINE, { ShaderStage.ComputeShader }),
        TaskShader(GL_TASK_SUBROUTINE_NV, { ShaderStage.TaskShader }),
        MeshShader(GL_MESH_SUBROUTINE_NV, { ShaderStage.MeshShader });

        val shaderStage by lazy(shaderStage)

        companion object {
            operator fun get(value: Int) = when (value) {
                GL_VERTEX_SUBROUTINE -> VertexShader
                GL_TESS_CONTROL_SUBROUTINE -> TessCtrlShader
                GL_TESS_EVALUATION_SUBROUTINE -> TessEvalShader
                GL_GEOMETRY_SUBROUTINE -> GeometryShader
                GL_FRAGMENT_SUBROUTINE -> FragmentShader
                GL_COMPUTE_SUBROUTINE -> ComputeShader
                else -> throw IllegalArgumentException("Invalid value: $value")
            }
        }
    }

    enum class SubroutineUniform(override val value: Int, shaderStage: () -> ShaderStage) : GLEnum {
        VertexShader(GL_VERTEX_SUBROUTINE_UNIFORM, { ShaderStage.VertexShader }),
        TessCtrlShader(GL_TESS_CONTROL_SUBROUTINE_UNIFORM, { ShaderStage.TessCtrlShader }),
        TessEvalShader(GL_TESS_EVALUATION_SUBROUTINE_UNIFORM, { ShaderStage.TessEvalShader }),
        GeometryShader(GL_GEOMETRY_SUBROUTINE_UNIFORM, { ShaderStage.GeometryShader }),
        FragmentShader(GL_FRAGMENT_SUBROUTINE_UNIFORM, { ShaderStage.FragmentShader }),
        ComputeShader(GL_COMPUTE_SUBROUTINE_UNIFORM, { ShaderStage.ComputeShader }),
        TaskShader(GL_TASK_SUBROUTINE_UNIFORM_NV, { ShaderStage.TaskShader }),
        MeshShader(GL_MESH_SUBROUTINE_UNIFORM_NV, { ShaderStage.MeshShader });

        val shaderStage by lazy(shaderStage)

        companion object {
            operator fun get(value: Int) = when (value) {
                GL_VERTEX_SUBROUTINE_UNIFORM -> VertexShader
                GL_TESS_CONTROL_SUBROUTINE_UNIFORM -> TessCtrlShader
                GL_TESS_EVALUATION_SUBROUTINE_UNIFORM -> TessEvalShader
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