package dev.luna5ama.glwrapper.enums

import dev.luna5ama.glwrapper.base.*

sealed interface GLSLDataType : GLEnum {
    val codeStr: String

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

    sealed interface Value : GLSLDataType {
        sealed class Scalar(override val codeStr: String, override val value: kotlin.Int) : Value
        sealed class Vector(override val codeStr: String, override val value: kotlin.Int) : Value
        sealed class Matrix(override val codeStr: String, override val value: kotlin.Int) : Value
    }

    sealed class Opaque : GLSLDataType {
        sealed class Sampler private constructor() : Opaque(), UniformType.Int {
            sealed class Sampler1D(override val codeStr: String, override val value: kotlin.Int) : Sampler()
            sealed class Sampler2D(override val codeStr: String, override val value: kotlin.Int) : Sampler()
            sealed class Sampler3D(override val codeStr: String, override val value: kotlin.Int) : Sampler()
            sealed class SamplerCube(override val codeStr: String, override val value: kotlin.Int) : Sampler()
            sealed class SamplerBuffer(override val codeStr: String, override val value: kotlin.Int) : Sampler()
            sealed class Sampler1DArray(override val codeStr: String, override val value: kotlin.Int) : Sampler()
            sealed class Sampler2DArray(override val codeStr: String, override val value: kotlin.Int) : Sampler()
            sealed class Sampler2DMS(override val codeStr: String, override val value: kotlin.Int) : Sampler()
            sealed class Sampler2DMSArray(override val codeStr: String, override val value: kotlin.Int) : Sampler()
            sealed class SamplerCubeArray(override val codeStr: String, override val value: kotlin.Int) : Sampler()
        }

        sealed class Image private constructor() : Opaque(), UniformType.Int {
            sealed class Image1D(override val codeStr: String, override val value: kotlin.Int) : Image()
            sealed class Image2D(override val codeStr: String, override val value: kotlin.Int) : Image()
            sealed class Image3D(override val codeStr: String, override val value: kotlin.Int) : Image()
            sealed class ImageCube(override val codeStr: String, override val value: kotlin.Int) : Image()
            sealed class ImageBuffer(override val codeStr: String, override val value: kotlin.Int) : Image()
            sealed class Image1DArray(override val codeStr: String, override val value: kotlin.Int) : Image()
            sealed class Image2DArray(override val codeStr: String, override val value: kotlin.Int) : Image()
            sealed class Image2DMS(override val codeStr: String, override val value: kotlin.Int) : Image()
            sealed class Image2DMSArray(override val codeStr: String, override val value: kotlin.Int) : Image()
            sealed class ImageCubeArray(override val codeStr: String, override val value: kotlin.Int) : Image()
        }

        sealed class AtomicCounter : Opaque()
        sealed class Subroutine : Opaque(), UniformType.Int
    }

    data object Bool : Value.Scalar("bool", GL_BOOL), UniformType.Bool
    data object Int : Value.Scalar("int", GL_INT), UniformType.Int
    data object UInt : Value.Scalar("uint", GL_UNSIGNED_INT), UniformType.Int
    data object Float : Value.Scalar("float", GL_FLOAT), UniformType.Float
    data object Double : Value.Scalar("double", GL_DOUBLE), UniformType.Double

    data object BVec2 : Value.Vector("bvec2", GL_BOOL_VEC2), UniformType.Bool
    data object BVec3 : Value.Vector("bvec3", GL_BOOL_VEC3), UniformType.Bool
    data object BVec4 : Value.Vector("bvec4", GL_BOOL_VEC4), UniformType.Bool

    data object IVec2 : Value.Vector("ivec2", GL_INT_VEC2), UniformType.Int
    data object IVec3 : Value.Vector("ivec3", GL_INT_VEC3), UniformType.Int
    data object IVec4 : Value.Vector("ivec4", GL_INT_VEC4), UniformType.Int

    data object UVec2 : Value.Vector("uvec2", GL_UNSIGNED_INT_VEC2), UniformType.Int
    data object UVec3 : Value.Vector("uvec3", GL_UNSIGNED_INT_VEC3), UniformType.Int
    data object UVec4 : Value.Vector("uvec4", GL_UNSIGNED_INT_VEC4), UniformType.Int

    data object Vec2 : Value.Vector("vec2", GL_FLOAT_VEC2), UniformType.Float
    data object Vec3 : Value.Vector("vec3", GL_FLOAT_VEC3), UniformType.Float
    data object Vec4 : Value.Vector("vec4", GL_FLOAT_VEC4), UniformType.Float

    data object DVec2 : Value.Vector("dvec2", GL_DOUBLE_VEC2), UniformType.Double
    data object DVec3 : Value.Vector("dvec3", GL_DOUBLE_VEC3), UniformType.Double
    data object DVec4 : Value.Vector("dvec4", GL_DOUBLE_VEC4), UniformType.Double

    data object Mat2 : Value.Matrix("mat2", GL_FLOAT_MAT2), UniformType.Float
    data object Mat3 : Value.Matrix("mat3", GL_FLOAT_MAT3), UniformType.Float
    data object Mat4 : Value.Matrix("mat4", GL_FLOAT_MAT4), UniformType.Float

    data object Mat2x3 : Value.Matrix("mat2x3", GL_FLOAT_MAT2x3), UniformType.Float
    data object Mat2x4 : Value.Matrix("mat2x4", GL_FLOAT_MAT2x4), UniformType.Float
    data object Mat3x2 : Value.Matrix("mat3x2", GL_FLOAT_MAT3x2), UniformType.Float
    data object Mat3x4 : Value.Matrix("mat3x4", GL_FLOAT_MAT3x4), UniformType.Float
    data object Mat4x2 : Value.Matrix("mat4x2", GL_FLOAT_MAT4x2), UniformType.Float
    data object Mat4x3 : Value.Matrix("mat4x3", GL_FLOAT_MAT4x3), UniformType.Float

    data object DMat2 : Value.Matrix("dmat2", GL_DOUBLE_MAT2), UniformType.Double
    data object DMat3 : Value.Matrix("dmat3", GL_DOUBLE_MAT3), UniformType.Double
    data object DMat4 : Value.Matrix("dmat4", GL_DOUBLE_MAT4), UniformType.Double

    data object DMat2x3 : Value.Matrix("dmat2x3", GL_DOUBLE_MAT2x3), UniformType.Double
    data object DMat2x4 : Value.Matrix("dmat2x4", GL_DOUBLE_MAT2x4), UniformType.Double
    data object DMat3x2 : Value.Matrix("dmat3x2", GL_DOUBLE_MAT3x2), UniformType.Double
    data object DMat3x4 : Value.Matrix("dmat3x4", GL_DOUBLE_MAT3x4), UniformType.Double
    data object DMat4x2 : Value.Matrix("dmat4x2", GL_DOUBLE_MAT4x2), UniformType.Double
    data object DMat4x3 : Value.Matrix("dmat4x3", GL_DOUBLE_MAT4x3), UniformType.Double

    data object Sampler1D : Opaque.Sampler.Sampler1D("sampler1D", GL_SAMPLER_1D), ImageType.Float
    data object Sampler2D : Opaque.Sampler.Sampler2D("sampler2D", GL_SAMPLER_2D), ImageType.Float
    data object Sampler3D : Opaque.Sampler.Sampler3D("sampler3D", GL_SAMPLER_3D), ImageType.Float
    data object SamplerCube : Opaque.Sampler.SamplerCube("samplerCube", GL_SAMPLER_CUBE), ImageType.Float
    data object SamplerBuffer : Opaque.Sampler.SamplerBuffer("samplerBuffer", GL_SAMPLER_BUFFER), ImageType.Float
    data object Sampler1DArray : Opaque.Sampler.Sampler1DArray("sampler1DArray", GL_SAMPLER_1D_ARRAY), ImageType.Float
    data object Sampler2DArray : Opaque.Sampler.Sampler2DArray("sampler2DArray", GL_SAMPLER_2D_ARRAY), ImageType.Float
    data object Sampler2DMS : Opaque.Sampler.Sampler2DMS("sampler2DMS", GL_SAMPLER_2D_MULTISAMPLE), ImageType.Float
    data object Sampler2DMSArray : Opaque.Sampler.Sampler2DMSArray("sampler2DMSArray", GL_SAMPLER_2D_MULTISAMPLE_ARRAY), ImageType.Float
    data object SamplerCubeArray : Opaque.Sampler.SamplerCubeArray("samplerCubeArray", GL_SAMPLER_CUBE_MAP_ARRAY), ImageType.Float

    data object ISampler1D : Opaque.Sampler.Sampler1D("isampler1D", GL_INT_SAMPLER_1D), ImageType.Int
    data object ISampler2D : Opaque.Sampler.Sampler2D("isampler2D", GL_INT_SAMPLER_2D), ImageType.Int
    data object ISampler3D : Opaque.Sampler.Sampler3D("isampler3D", GL_INT_SAMPLER_3D), ImageType.Int
    data object ISamplerCube : Opaque.Sampler.SamplerCube("isamplerCube", GL_INT_SAMPLER_CUBE), ImageType.Int
    data object ISamplerBuffer : Opaque.Sampler.SamplerBuffer("isamplerBuffer", GL_INT_SAMPLER_BUFFER), ImageType.Int
    data object ISampler1DArray : Opaque.Sampler.Sampler1DArray("isampler1DArray", GL_INT_SAMPLER_1D_ARRAY), ImageType.Int
    data object ISampler2DArray : Opaque.Sampler.Sampler2DArray("isampler2DArray", GL_INT_SAMPLER_2D_ARRAY), ImageType.Int
    data object ISampler2DMS : Opaque.Sampler.Sampler2DMS("isampler2DMS", GL_INT_SAMPLER_2D_MULTISAMPLE), ImageType.Int
    data object ISampler2DMSArray : Opaque.Sampler.Sampler2DMSArray("isampler2DMSArray", GL_INT_SAMPLER_2D_MULTISAMPLE_ARRAY), ImageType.Int
    data object ISamplerCubeArray : Opaque.Sampler.SamplerCubeArray("isamplerCubeArray", GL_INT_SAMPLER_CUBE_MAP_ARRAY), ImageType.Int

    data object USampler1D : Opaque.Sampler.Sampler1D("usampler1D", GL_UNSIGNED_INT_SAMPLER_1D), ImageType.UInt
    data object USampler2D : Opaque.Sampler.Sampler2D("usampler2D", GL_UNSIGNED_INT_SAMPLER_2D), ImageType.UInt
    data object USampler3D : Opaque.Sampler.Sampler3D("usampler3D", GL_UNSIGNED_INT_SAMPLER_3D), ImageType.UInt
    data object USamplerCube : Opaque.Sampler.SamplerCube("usamplerCube", GL_UNSIGNED_INT_SAMPLER_CUBE), ImageType.UInt
    data object USamplerBuffer : Opaque.Sampler.SamplerBuffer("usamplerBuffer", GL_UNSIGNED_INT_SAMPLER_BUFFER), ImageType.UInt
    data object USampler1DArray : Opaque.Sampler.Sampler1DArray("usampler1DArray", GL_UNSIGNED_INT_SAMPLER_1D_ARRAY), ImageType.UInt
    data object USampler2DArray : Opaque.Sampler.Sampler2DArray("usampler2DArray", GL_UNSIGNED_INT_SAMPLER_2D_ARRAY), ImageType.UInt
    data object USampler2DMS : Opaque.Sampler.Sampler2DMS("usampler2DMS", GL_UNSIGNED_INT_SAMPLER_2D_MULTISAMPLE), ImageType.UInt
    data object USampler2DMSArray : Opaque.Sampler.Sampler2DMSArray("usampler2DMSArray", GL_UNSIGNED_INT_SAMPLER_2D_MULTISAMPLE_ARRAY),
        ImageType.UInt

    data object USamplerCubeArray : Opaque.Sampler.SamplerCubeArray("usamplerCubeArray", GL_UNSIGNED_INT_SAMPLER_CUBE_MAP_ARRAY),
        ImageType.UInt

    data object Sampler1DShadow : Opaque.Sampler.Sampler1D("sampler1DShadow", GL_SAMPLER_1D_SHADOW), ImageType.Shadow
    data object Sampler2DShadow : Opaque.Sampler.Sampler2D("sampler2DShadow", GL_SAMPLER_2D_SHADOW), ImageType.Shadow
    data object Sampler1DArrayShadow : Opaque.Sampler.Sampler1DArray("sampler1DArrayShadow", GL_SAMPLER_1D_ARRAY_SHADOW), ImageType.Shadow
    data object Sampler2DArrayShadow : Opaque.Sampler.Sampler2DArray("sampler2DArrayShadow", GL_SAMPLER_2D_ARRAY_SHADOW), ImageType.Shadow
    data object SamplerCubeShadow : Opaque.Sampler.SamplerCube("samplerCubeShadow", GL_SAMPLER_CUBE_SHADOW), ImageType.Shadow
    data object SamplerCubeArrayShadow : Opaque.Sampler.SamplerCubeArray("samplerCubeArrayShadow", GL_SAMPLER_CUBE_MAP_ARRAY_SHADOW),
        ImageType.Shadow

    data object Image1D : Opaque.Image.Image1D("image1D", GL_IMAGE_1D), ImageType.Float
    data object Image2D : Opaque.Image.Image2D("image2D", GL_IMAGE_2D), ImageType.Float
    data object Image3D : Opaque.Image.Image3D("image3D", GL_IMAGE_3D), ImageType.Float
    data object ImageCube : Opaque.Image.ImageCube("imageCube", GL_IMAGE_CUBE), ImageType.Float
    data object ImageBuffer : Opaque.Image.ImageBuffer("imageBuffer", GL_IMAGE_BUFFER), ImageType.Float
    data object Image1DArray : Opaque.Image.Image1DArray("image1DArray", GL_IMAGE_1D_ARRAY), ImageType.Float
    data object Image2DArray : Opaque.Image.Image2DArray("image2DArray", GL_IMAGE_2D_ARRAY), ImageType.Float
    data object Image2DMS : Opaque.Image.Image2DMS("image2DMS", GL_IMAGE_2D_MULTISAMPLE), ImageType.Float
    data object Image2DMSArray : Opaque.Image.Image2DMSArray("image2DMSArray", GL_IMAGE_2D_MULTISAMPLE_ARRAY), ImageType.Float
    data object ImageCubeArray : Opaque.Image.ImageCubeArray("imageCubeArray", GL_IMAGE_CUBE_MAP_ARRAY), ImageType.Float

    data object IImage1D : Opaque.Image.Image1D("iimage1D", GL_INT_IMAGE_1D), ImageType.Int
    data object IImage2D : Opaque.Image.Image2D("iimage2D", GL_INT_IMAGE_2D), ImageType.Int
    data object IImage3D : Opaque.Image.Image3D("iimage3D", GL_INT_IMAGE_3D), ImageType.Int
    data object IImageCube : Opaque.Image.ImageCube("iimageCube", GL_INT_IMAGE_CUBE), ImageType.Int
    data object IImageBuffer : Opaque.Image.ImageBuffer("iimageBuffer", GL_INT_IMAGE_BUFFER), ImageType.Int
    data object IImage1DArray : Opaque.Image.Image1DArray("iimage1DArray", GL_INT_IMAGE_1D_ARRAY), ImageType.Int
    data object IImage2DArray : Opaque.Image.Image2DArray("iimage2DArray", GL_INT_IMAGE_2D_ARRAY), ImageType.Int
    data object IImage2DMS : Opaque.Image.Image2DMS("iimage2DMS", GL_INT_IMAGE_2D_MULTISAMPLE), ImageType.Int
    data object IImage2DMSArray : Opaque.Image.Image2DMSArray("iimage2DMSArray", GL_INT_IMAGE_2D_MULTISAMPLE_ARRAY), ImageType.Int
    data object IImageCubeArray : Opaque.Image.ImageCubeArray("iimageCubeArray", GL_INT_IMAGE_CUBE_MAP_ARRAY), ImageType.Int

    data object UImage1D : Opaque.Image.Image1D("uimage1D", GL_UNSIGNED_INT_IMAGE_1D), ImageType.UInt
    data object UImage2D : Opaque.Image.Image2D("uimage2D", GL_UNSIGNED_INT_IMAGE_2D), ImageType.UInt
    data object UImage3D : Opaque.Image.Image3D("uimage3D", GL_UNSIGNED_INT_IMAGE_3D), ImageType.UInt
    data object UImageCube : Opaque.Image.ImageCube("uimageCube", GL_UNSIGNED_INT_IMAGE_CUBE), ImageType.UInt
    data object UImageBuffer : Opaque.Image.ImageBuffer("uimageBuffer", GL_UNSIGNED_INT_IMAGE_BUFFER), ImageType.UInt
    data object UImage1DArray : Opaque.Image.Image1DArray("uimage1DArray", GL_UNSIGNED_INT_IMAGE_1D_ARRAY), ImageType.UInt
    data object UImage2DArray : Opaque.Image.Image2DArray("uimage2DArray", GL_UNSIGNED_INT_IMAGE_2D_ARRAY), ImageType.UInt
    data object UImage2DMS : Opaque.Image.Image2DMS("uimage2DMS", GL_UNSIGNED_INT_IMAGE_2D_MULTISAMPLE), ImageType.UInt
    data object UImage2DMSArray : Opaque.Image.Image2DMSArray("uimage2DMSArray", GL_UNSIGNED_INT_IMAGE_2D_MULTISAMPLE_ARRAY),
        ImageType.UInt

    data object UImageCubeArray : Opaque.Image.ImageCubeArray("uimageCubeArray", GL_UNSIGNED_INT_IMAGE_CUBE_MAP_ARRAY), ImageType.UInt


    data object AtomicCounter : Opaque.AtomicCounter() {
        override val codeStr: String = "atomic_uint"
        override val value: kotlin.Int
            get() = GL_UNSIGNED_INT_ATOMIC_COUNTER
    }

    data object Subroutine : Opaque.Subroutine() {
        override val codeStr: String = "subroutine"
        override val value: kotlin.Int
            get() = throw UnsupportedOperationException()
    }

    companion object {
        operator fun get(value: kotlin.Int): GLSLDataType = when (value) {
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

            GL_IMAGE_1D -> Image1D
            GL_IMAGE_2D -> Image2D
            GL_IMAGE_3D -> Image3D
            GL_IMAGE_CUBE -> ImageCube
            GL_IMAGE_BUFFER -> ImageBuffer
            GL_IMAGE_1D_ARRAY -> Image1DArray
            GL_IMAGE_2D_ARRAY -> Image2DArray
            GL_IMAGE_2D_MULTISAMPLE -> Image2DMS
            GL_IMAGE_2D_MULTISAMPLE_ARRAY -> Image2DMSArray
            GL_IMAGE_CUBE_MAP_ARRAY -> ImageCubeArray

            GL_INT_IMAGE_1D -> IImage1D
            GL_INT_IMAGE_2D -> IImage2D
            GL_INT_IMAGE_3D -> IImage3D
            GL_INT_IMAGE_CUBE -> IImageCube
            GL_INT_IMAGE_BUFFER -> IImageBuffer
            GL_INT_IMAGE_1D_ARRAY -> IImage1DArray
            GL_INT_IMAGE_2D_ARRAY -> IImage2DArray
            GL_INT_IMAGE_2D_MULTISAMPLE -> IImage2DMS
            GL_INT_IMAGE_2D_MULTISAMPLE_ARRAY -> IImage2DMSArray
            GL_INT_IMAGE_CUBE_MAP_ARRAY -> IImageCubeArray

            GL_UNSIGNED_INT_IMAGE_1D -> UImage1D
            GL_UNSIGNED_INT_IMAGE_2D -> UImage2D
            GL_UNSIGNED_INT_IMAGE_3D -> UImage3D
            GL_UNSIGNED_INT_IMAGE_CUBE -> UImageCube
            GL_UNSIGNED_INT_IMAGE_BUFFER -> UImageBuffer
            GL_UNSIGNED_INT_IMAGE_1D_ARRAY -> UImage1DArray
            GL_UNSIGNED_INT_IMAGE_2D_ARRAY -> UImage2DArray
            GL_UNSIGNED_INT_IMAGE_2D_MULTISAMPLE -> UImage2DMS
            GL_UNSIGNED_INT_IMAGE_2D_MULTISAMPLE_ARRAY -> UImage2DMSArray
            GL_UNSIGNED_INT_IMAGE_CUBE_MAP_ARRAY -> UImageCubeArray

            GL_UNSIGNED_INT_ATOMIC_COUNTER -> AtomicCounter

            else -> throw IllegalArgumentException("Invalid value: $value")
        }

        operator fun get(codeStr: String): GLSLDataType = when (codeStr) {
            "bool" -> Bool
            "int" -> Int
            "uint" -> UInt
            "float" -> Float
            "double" -> Double

            "bvec2" -> BVec2
            "bvec3" -> BVec3
            "bvec4" -> BVec4

            "ivec2" -> IVec2
            "ivec3" -> IVec3
            "ivec4" -> IVec4

            "uvec2" -> UVec2
            "uvec3" -> UVec3
            "uvec4" -> UVec4

            "vec2" -> Vec2
            "vec3" -> Vec3
            "vec4" -> Vec4

            "dvec2" -> DVec2
            "dvec3" -> DVec3
            "dvec4" -> DVec4

            "mat2" -> Mat2
            "mat3" -> Mat3
            "mat4" -> Mat4

            "mat2x3" -> Mat2x3
            "mat2x4" -> Mat2x4
            "mat3x2" -> Mat3x2
            "mat3x4" -> Mat3x4
            "mat4x2" -> Mat4x2
            "mat4x3" -> Mat4x3

            "dmat2" -> DMat2
            "dmat3" -> DMat3
            "dmat4" -> DMat4

            "dmat2x3" -> DMat2x3
            "dmat2x4" -> DMat2x4
            "dmat3x2" -> DMat3x2
            "dmat3x4" -> DMat3x4
            "dmat4x2" -> DMat4x2
            "dmat4x3" -> DMat4x3

            "sampler1D" -> Sampler1D
            "sampler2D" -> Sampler2D
            "sampler3D" -> Sampler3D
            "samplerCube" -> SamplerCube
            "samplerBuffer" -> SamplerBuffer
            "sampler1DArray" -> Sampler1DArray
            "sampler2DArray" -> Sampler2DArray
            "sampler2DMS" -> Sampler2DMS
            "sampler2DMSArray" -> Sampler2DMSArray
            "samplerCubeArray" -> SamplerCubeArray

            "isampler1D" -> ISampler1D
            "isampler2D" -> ISampler2D
            "isampler3D" -> ISampler3D
            "isamplerCube" -> ISamplerCube
            "isamplerBuffer" -> ISamplerBuffer
            "isampler1DArray" -> ISampler1DArray
            "isampler2DArray" -> ISampler2DArray
            "isampler2DMS" -> ISampler2DMS
            "isampler2DMSArray" -> ISampler2DMSArray
            "isamplerCubeArray" -> ISamplerCubeArray

            "usampler1D" -> USampler1D
            "usampler2D" -> USampler2D
            "usampler3D" -> USampler3D
            "usamplerCube" -> USamplerCube
            "usamplerBuffer" -> USamplerBuffer
            "usampler1DArray" -> USampler1DArray
            "usampler2DArray" -> USampler2DArray
            "usampler2DMS" -> USampler2DMS
            "usampler2DMSArray" -> USampler2DMSArray
            "usamplerCubeArray" -> USamplerCubeArray

            "sampler1DShadow" -> Sampler1DShadow
            "sampler2DShadow" -> Sampler2DShadow
            "sampler1DArrayShadow" -> Sampler1DArrayShadow
            "sampler2DArrayShadow" -> Sampler2DArrayShadow
            "samplerCubeShadow" -> SamplerCubeShadow
            "samplerCubeArrayShadow" -> SamplerCubeArrayShadow

            "image1D" -> Image1D
            "image2D" -> Image2D
            "image3D" -> Image3D
            "imageCube" -> ImageCube
            "imageBuffer" -> ImageBuffer
            "image1DArray" -> Image1DArray
            "image2DArray" -> Image2DArray
            "image2DMS" -> Image2DMS
            "image2DMSArray" -> Image2DMSArray
            "imageCubeArray" -> ImageCubeArray

            "iimage1D" -> IImage1D
            "iimage2D" -> IImage2D
            "iimage3D" -> IImage3D
            "iimageCube" -> IImageCube
            "iimageBuffer" -> IImageBuffer
            "iimage1DArray" -> IImage1DArray
            "iimage2DArray" -> IImage2DArray
            "iimage2DMS" -> IImage2DMS
            "iimage2DMSArray" -> IImage2DMSArray
            "iimageCubeArray" -> IImageCubeArray

            "uimage1D" -> UImage1D
            "uimage2D" -> UImage2D
            "uimage3D" -> UImage3D
            "uimageCube" -> UImageCube
            "uimageBuffer" -> UImageBuffer
            "uimage1DArray" -> UImage1DArray
            "uimage2DArray" -> UImage2DArray
            "uimage2DMS" -> UImage2DMS
            "uimage2DMSArray" -> UImage2DMSArray
            "uimageCubeArray" -> UImageCubeArray

            "atomic_uint" -> AtomicCounter
            "subroutine" -> Subroutine

            else -> throw IllegalArgumentException("Invalid codeStr: $codeStr")
        }
    }
}