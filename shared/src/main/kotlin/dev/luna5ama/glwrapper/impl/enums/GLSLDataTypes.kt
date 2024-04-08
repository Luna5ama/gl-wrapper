package dev.luna5ama.glwrapper.impl.enums

import dev.luna5ama.glwrapper.api.*

sealed interface GLSLDataTypes : GLEnum {
    sealed interface UniformType {
        sealed interface Bool : UniformType
        sealed interface Int : UniformType
        sealed interface UInt : UniformType
        sealed interface Float : UniformType
        sealed interface Double : UniformType
    }

    sealed interface ImageType {
        sealed interface Float : ImageType
        sealed interface Int : ImageType
        sealed interface UInt : ImageType
        sealed interface Shadow : ImageType
    }

    sealed interface Value : GLSLDataTypes {
        sealed class Scalar(override val value: kotlin.Int) : Value
        sealed class Vector(override val value: kotlin.Int) : Value
        sealed class Matrix(override val value: kotlin.Int) : Value
    }

    sealed class Opaque : GLSLDataTypes {
        sealed class Sampler private constructor(): Opaque(), UniformType.Int {
            sealed class Sampler1D(override val value: kotlin.Int) : Sampler()
            sealed class Sampler2D(override val value: kotlin.Int) : Sampler()
            sealed class Sampler3D(override val value: kotlin.Int) : Sampler()
            sealed class SamplerCube(override val value: kotlin.Int) : Sampler()
            sealed class SamplerBuffer(override val value: kotlin.Int) : Sampler()
            sealed class Sampler1DArray(override val value: kotlin.Int) : Sampler()
            sealed class Sampler2DArray(override val value: kotlin.Int) : Sampler()
            sealed class Sampler2DMS(override val value: kotlin.Int) : Sampler()
            sealed class Sampler2DMSArray(override val value: kotlin.Int) : Sampler()
            sealed class SamplerCubeArray(override val value: kotlin.Int) : Sampler()
        }

        sealed class Image private constructor(): Opaque(), UniformType.Int {
            sealed class Image1D(override val value: kotlin.Int) : Image()
            sealed class Image2D(override val value: kotlin.Int) : Image()
            sealed class Image3D(override val value: kotlin.Int) : Image()
            sealed class ImageCube(override val value: kotlin.Int) : Image()
            sealed class ImageBuffer(override val value: kotlin.Int) : Image()
            sealed class Image1DArray(override val value: kotlin.Int) : Image()
            sealed class Image2DArray(override val value: kotlin.Int) : Image()
            sealed class Image2DMS(override val value: kotlin.Int) : Image()
            sealed class Image2DMSArray(override val value: kotlin.Int) : Image()
            sealed class ImageCubeArray(override val value: kotlin.Int) : Image()
        }

        sealed class AtomicCounter : Opaque()
        sealed class Subroutine : Opaque(), UniformType.Int
    }

    data object Bool : Value.Scalar(GL_BOOL), UniformType.Bool
    data object Int : Value.Scalar(GL_INT), UniformType.Int
    data object UInt : Value.Scalar(GL_UNSIGNED_INT), UniformType.Int
    data object Float : Value.Scalar(GL_FLOAT), UniformType.Float
    data object Double : Value.Scalar(GL_DOUBLE), UniformType.Double

    data object BVec2 : Value.Vector(GL_BOOL_VEC2), UniformType.Bool
    data object BVec3 : Value.Vector(GL_BOOL_VEC3), UniformType.Bool
    data object BVec4 : Value.Vector(GL_BOOL_VEC4), UniformType.Bool

    data object IVec2 : Value.Vector(GL_INT_VEC2), UniformType.Int
    data object IVec3 : Value.Vector(GL_INT_VEC3), UniformType.Int
    data object IVec4 : Value.Vector(GL_INT_VEC4), UniformType.Int

    data object UVec2 : Value.Vector(GL_UNSIGNED_INT_VEC2), UniformType.Int
    data object UVec3 : Value.Vector(GL_UNSIGNED_INT_VEC3), UniformType.Int
    data object UVec4 : Value.Vector(GL_UNSIGNED_INT_VEC4), UniformType.Int

    data object Vec2 : Value.Vector(GL_FLOAT_VEC2), UniformType.Float
    data object Vec3 : Value.Vector(GL_FLOAT_VEC3), UniformType.Float
    data object Vec4 : Value.Vector(GL_FLOAT_VEC4), UniformType.Float

    data object DVec2 : Value.Vector(GL_DOUBLE_VEC2), UniformType.Double
    data object DVec3 : Value.Vector(GL_DOUBLE_VEC3), UniformType.Double
    data object DVec4 : Value.Vector(GL_DOUBLE_VEC4), UniformType.Double

    data object Mat2 : Value.Matrix(GL_FLOAT_MAT2), UniformType.Float
    data object Mat3 : Value.Matrix(GL_FLOAT_MAT3), UniformType.Float
    data object Mat4 : Value.Matrix(GL_FLOAT_MAT4), UniformType.Float

    data object Mat2x3 : Value.Matrix(GL_FLOAT_MAT2x3), UniformType.Float
    data object Mat2x4 : Value.Matrix(GL_FLOAT_MAT2x4), UniformType.Float
    data object Mat3x2 : Value.Matrix(GL_FLOAT_MAT3x2), UniformType.Float
    data object Mat3x4 : Value.Matrix(GL_FLOAT_MAT3x4), UniformType.Float
    data object Mat4x2 : Value.Matrix(GL_FLOAT_MAT4x2), UniformType.Float
    data object Mat4x3 : Value.Matrix(GL_FLOAT_MAT4x3), UniformType.Float

    data object DMat2 : Value.Matrix(GL_DOUBLE_MAT2), UniformType.Double
    data object DMat3 : Value.Matrix(GL_DOUBLE_MAT3), UniformType.Double
    data object DMat4 : Value.Matrix(GL_DOUBLE_MAT4), UniformType.Double

    data object DMat2x3 : Value.Matrix(GL_DOUBLE_MAT2x3), UniformType.Double
    data object DMat2x4 : Value.Matrix(GL_DOUBLE_MAT2x4), UniformType.Double
    data object DMat3x2 : Value.Matrix(GL_DOUBLE_MAT3x2), UniformType.Double
    data object DMat3x4 : Value.Matrix(GL_DOUBLE_MAT3x4), UniformType.Double
    data object DMat4x2 : Value.Matrix(GL_DOUBLE_MAT4x2), UniformType.Double
    data object DMat4x3 : Value.Matrix(GL_DOUBLE_MAT4x3), UniformType.Double

    data object Sampler1D : Opaque.Sampler.Sampler1D(GL_SAMPLER_1D), ImageType.Float
    data object Sampler2D : Opaque.Sampler.Sampler2D(GL_SAMPLER_2D), ImageType.Float
    data object Sampler3D : Opaque.Sampler.Sampler3D(GL_SAMPLER_3D), ImageType.Float
    data object SamplerCube : Opaque.Sampler.SamplerCube(GL_SAMPLER_CUBE), ImageType.Float
    data object SamplerBuffer : Opaque.Sampler.SamplerBuffer(GL_SAMPLER_BUFFER), ImageType.Float
    data object Sampler1DArray : Opaque.Sampler.Sampler1DArray(GL_SAMPLER_1D_ARRAY), ImageType.Float
    data object Sampler2DArray : Opaque.Sampler.Sampler2DArray(GL_SAMPLER_2D_ARRAY), ImageType.Float
    data object Sampler2DMS : Opaque.Sampler.Sampler2DMS(GL_SAMPLER_2D_MULTISAMPLE), ImageType.Float
    data object Sampler2DMSArray : Opaque.Sampler.Sampler2DMSArray(GL_SAMPLER_2D_MULTISAMPLE_ARRAY), ImageType.Float
    data object SamplerCubeArray : Opaque.Sampler.SamplerCubeArray(GL_SAMPLER_CUBE_MAP_ARRAY), ImageType.Float

    data object ISampler1D : Opaque.Sampler.Sampler1D(GL_INT_SAMPLER_1D), ImageType.Int
    data object ISampler2D : Opaque.Sampler.Sampler2D(GL_INT_SAMPLER_2D), ImageType.Int
    data object ISampler3D : Opaque.Sampler.Sampler3D(GL_INT_SAMPLER_3D), ImageType.Int
    data object ISamplerCube : Opaque.Sampler.SamplerCube(GL_INT_SAMPLER_CUBE), ImageType.Int
    data object ISamplerBuffer : Opaque.Sampler.SamplerBuffer(GL_INT_SAMPLER_BUFFER), ImageType.Int
    data object ISampler1DArray : Opaque.Sampler.Sampler1DArray(GL_INT_SAMPLER_1D_ARRAY), ImageType.Int
    data object ISampler2DArray : Opaque.Sampler.Sampler2DArray(GL_INT_SAMPLER_2D_ARRAY), ImageType.Int
    data object ISampler2DMS : Opaque.Sampler.Sampler2DMS(GL_INT_SAMPLER_2D_MULTISAMPLE), ImageType.Int
    data object ISampler2DMSArray : Opaque.Sampler.Sampler2DMSArray(GL_INT_SAMPLER_2D_MULTISAMPLE_ARRAY), ImageType.Int
    data object ISamplerCubeArray : Opaque.Sampler.SamplerCubeArray(GL_INT_SAMPLER_CUBE_MAP_ARRAY), ImageType.Int

    data object USampler1D : Opaque.Sampler.Sampler1D(GL_UNSIGNED_INT_SAMPLER_1D), ImageType.UInt
    data object USampler2D : Opaque.Sampler.Sampler2D(GL_UNSIGNED_INT_SAMPLER_2D), ImageType.UInt
    data object USampler3D : Opaque.Sampler.Sampler3D(GL_UNSIGNED_INT_SAMPLER_3D), ImageType.UInt
    data object USamplerCube : Opaque.Sampler.SamplerCube(GL_UNSIGNED_INT_SAMPLER_CUBE), ImageType.UInt
    data object USamplerBuffer : Opaque.Sampler.SamplerBuffer(GL_UNSIGNED_INT_SAMPLER_BUFFER), ImageType.UInt
    data object USampler1DArray : Opaque.Sampler.Sampler1DArray(GL_UNSIGNED_INT_SAMPLER_1D_ARRAY), ImageType.UInt
    data object USampler2DArray : Opaque.Sampler.Sampler2DArray(GL_UNSIGNED_INT_SAMPLER_2D_ARRAY), ImageType.UInt
    data object USampler2DMS : Opaque.Sampler.Sampler2DMS(GL_UNSIGNED_INT_SAMPLER_2D_MULTISAMPLE), ImageType.UInt
    data object USampler2DMSArray : Opaque.Sampler.Sampler2DMSArray(GL_UNSIGNED_INT_SAMPLER_2D_MULTISAMPLE_ARRAY),
        ImageType.UInt
    data object USamplerCubeArray : Opaque.Sampler.SamplerCubeArray(GL_UNSIGNED_INT_SAMPLER_CUBE_MAP_ARRAY),
        ImageType.UInt

    data object Sampler1DShadow : Opaque.Sampler.Sampler1D(GL_SAMPLER_1D_SHADOW), ImageType.Shadow
    data object Sampler2DShadow : Opaque.Sampler.Sampler2D(GL_SAMPLER_2D_SHADOW), ImageType.Shadow
    data object Sampler1DArrayShadow : Opaque.Sampler.Sampler1DArray(GL_SAMPLER_1D_ARRAY_SHADOW), ImageType.Shadow
    data object Sampler2DArrayShadow : Opaque.Sampler.Sampler2DArray(GL_SAMPLER_2D_ARRAY_SHADOW), ImageType.Shadow
    data object SamplerCubeShadow : Opaque.Sampler.SamplerCube(GL_SAMPLER_CUBE_SHADOW), ImageType.Shadow
    data object SamplerCubeArrayShadow : Opaque.Sampler.SamplerCubeArray(GL_SAMPLER_CUBE_MAP_ARRAY_SHADOW),
        ImageType.Shadow

    data object AtomicCounter : Opaque.AtomicCounter() {
        override val value: kotlin.Int
            get() = GL_UNSIGNED_INT_ATOMIC_COUNTER
    }

    data object Subroutine : Opaque.Subroutine() {
        override val value: kotlin.Int
            get() = throw UnsupportedOperationException()
    }

    companion object {
        operator fun get(value: kotlin.Int): GLSLDataTypes = when (value) {
            GL_BOOL -> Bool
            GL_INT -> Int
            GL_UNSIGNED_INT -> UInt
            GL_FLOAT -> Float
            GL_DOUBLE -> Double

            GL_BOOL_VEC2 -> BVec2
            GL_BOOL_VEC3 -> BVec3
            GL_BOOL_VEC4 -> BVec4

            GL_INT_VEC2 -> IVec2
            GL_INT_VEC3 -> IVec3
            GL_INT_VEC4 -> IVec4

            GL_UNSIGNED_INT_VEC2 -> UVec2
            GL_UNSIGNED_INT_VEC3 -> UVec3
            GL_UNSIGNED_INT_VEC4 -> UVec4

            GL_FLOAT_VEC2 -> Vec2
            GL_FLOAT_VEC3 -> Vec3
            GL_FLOAT_VEC4 -> Vec4

            GL_DOUBLE_VEC2 -> DVec2
            GL_DOUBLE_VEC3 -> DVec3
            GL_DOUBLE_VEC4 -> DVec4

            GL_FLOAT_MAT2 -> Mat2
            GL_FLOAT_MAT3 -> Mat3
            GL_FLOAT_MAT4 -> Mat4

            GL_FLOAT_MAT2x3 -> Mat2x3
            GL_FLOAT_MAT2x4 -> Mat2x4
            GL_FLOAT_MAT3x2 -> Mat3x2
            GL_FLOAT_MAT3x4 -> Mat3x4
            GL_FLOAT_MAT4x2 -> Mat4x2
            GL_FLOAT_MAT4x3 -> Mat4x3

            GL_DOUBLE_MAT2 -> DMat2
            GL_DOUBLE_MAT3 -> DMat3
            GL_DOUBLE_MAT4 -> DMat4

            GL_DOUBLE_MAT2x3 -> DMat2x3
            GL_DOUBLE_MAT2x4 -> DMat2x4
            GL_DOUBLE_MAT3x2 -> DMat3x2
            GL_DOUBLE_MAT3x4 -> DMat3x4
            GL_DOUBLE_MAT4x2 -> DMat4x2
            GL_DOUBLE_MAT4x3 -> DMat4x3

            GL_SAMPLER_1D -> Sampler1D
            GL_SAMPLER_2D -> Sampler2D
            GL_SAMPLER_3D -> Sampler3D
            GL_SAMPLER_CUBE -> SamplerCube
            GL_SAMPLER_BUFFER -> SamplerBuffer
            GL_SAMPLER_1D_ARRAY -> Sampler1DArray
            GL_SAMPLER_2D_ARRAY -> Sampler2DArray
            GL_SAMPLER_2D_MULTISAMPLE -> Sampler2DMS
            GL_SAMPLER_2D_MULTISAMPLE_ARRAY -> Sampler2DMSArray
            GL_SAMPLER_CUBE_MAP_ARRAY -> SamplerCubeArray

            GL_INT_SAMPLER_1D -> ISampler1D
            GL_INT_SAMPLER_2D -> ISampler2D
            GL_INT_SAMPLER_3D -> ISampler3D
            GL_INT_SAMPLER_CUBE -> ISamplerCube
            GL_INT_SAMPLER_BUFFER -> ISamplerBuffer
            GL_INT_SAMPLER_1D_ARRAY -> ISampler1DArray
            GL_INT_SAMPLER_2D_ARRAY -> ISampler2DArray
            GL_INT_SAMPLER_2D_MULTISAMPLE -> ISampler2DMS
            GL_INT_SAMPLER_2D_MULTISAMPLE_ARRAY -> ISampler2DMSArray
            GL_INT_SAMPLER_CUBE_MAP_ARRAY -> ISamplerCubeArray


            GL_UNSIGNED_INT_SAMPLER_1D -> USampler1D
            GL_UNSIGNED_INT_SAMPLER_2D -> USampler2D
            GL_UNSIGNED_INT_SAMPLER_3D -> USampler3D
            GL_UNSIGNED_INT_SAMPLER_CUBE -> USamplerCube
            GL_UNSIGNED_INT_SAMPLER_BUFFER -> USamplerBuffer
            GL_UNSIGNED_INT_SAMPLER_1D_ARRAY -> USampler1DArray
            GL_UNSIGNED_INT_SAMPLER_2D_ARRAY -> USampler2DArray
            GL_UNSIGNED_INT_SAMPLER_2D_MULTISAMPLE -> USampler2DMS
            GL_UNSIGNED_INT_SAMPLER_2D_MULTISAMPLE_ARRAY -> USampler2DMSArray
            GL_UNSIGNED_INT_SAMPLER_CUBE_MAP_ARRAY -> USamplerCubeArray

            GL_SAMPLER_1D_SHADOW -> Sampler1DShadow
            GL_SAMPLER_2D_SHADOW -> Sampler2DShadow
            GL_SAMPLER_1D_ARRAY_SHADOW -> Sampler1DArrayShadow
            GL_SAMPLER_2D_ARRAY_SHADOW -> Sampler2DArrayShadow
            GL_SAMPLER_CUBE_SHADOW -> SamplerCubeShadow
            GL_SAMPLER_CUBE_MAP_ARRAY_SHADOW -> SamplerCubeArrayShadow

            else -> throw IllegalArgumentException("Invalid value: $value")
        }
    }
}