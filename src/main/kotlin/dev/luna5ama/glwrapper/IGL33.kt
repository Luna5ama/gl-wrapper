@file:JvmName("GL33")

package dev.luna5ama.glwrapper

import dev.luna5ama.kmogus.Ptr
import dev.luna5ama.kmogus.ensureCapacity
import dev.luna5ama.kmogus.memcpy

const val GL_SRC1_COLOR = 0x88F9
const val GL_ONE_MINUS_SRC1_COLOR = 0x88FA
const val GL_ONE_MINUS_SRC1_ALPHA = 0x88FB

const val GL_MAX_DUAL_SOURCE_DRAW_BUFFERS = 0x88FC

const val GL_ANY_SAMPLES_PASSED = 0x8C2F

const val GL_SAMPLER_BINDING = 0x8919

const val GL_RGB10_A2UI = 0x906F

const val GL_TEXTURE_SWIZZLE_R = 0x8E42
const val GL_TEXTURE_SWIZZLE_G = 0x8E43
const val GL_TEXTURE_SWIZZLE_B = 0x8E44
const val GL_TEXTURE_SWIZZLE_A = 0x8E45

const val GL_TEXTURE_SWIZZLE_RGBA = 0x8E46

const val GL_TIME_ELAPSED = 0x88BF

const val GL_TIMESTAMP = 0x8E28

const val GL_VERTEX_ATTRIB_ARRAY_DIVISOR = 0x88FE

const val GL_INT_2_10_10_10_REV = 0x8D9F


interface IGL33 : GLBase {
    @Unsafe
    fun glDeleteSamplers(count: Int, samplers: Long)

    fun glIsSampler(sampler: Int): Boolean

    fun glBindSampler(unit: Int, sampler: Int)


    fun glDeleteSamplers(count: Int, samplers: Ptr) {
        glDeleteSamplers(count, samplers.address)
    }

    fun glDeleteSamplers(sampler: Int) {
        val ptr = tempArr.ptr
        ptr.setInt(sampler)
        glDeleteSamplers(1, ptr.address)
    }

    fun glDeleteSamplers(sampler1: Int, sampler2: Int) {
        val ptr = tempArr.ptr
        ptr.setInt(sampler1)
        ptr.setInt(4, sampler2)
        glDeleteSamplers(2, ptr.address)
    }

    fun glDeleteSamplers(sampler1: Int, sampler2: Int, sampler3: Int) {
        val ptr = tempArr.ptr
        ptr.setInt(sampler1)
        ptr.setInt(4, sampler2)
        ptr.setInt(8, sampler3)
        glDeleteSamplers(3, ptr.address)
    }

    fun glDeleteSamplers(sampler1: Int, sampler2: Int, sampler3: Int, sampler4: Int) {
        val ptr = tempArr.ptr
        ptr.setInt(sampler1)
        ptr.setInt(4, sampler2)
        ptr.setInt(8, sampler3)
        ptr.setInt(12, sampler4)
        glDeleteSamplers(4, ptr.address)
    }

    fun glDeleteSamplers(vararg samplers: Int) {
        tempArr.ensureCapacity(samplers.size * 4L, false)
        val ptr = tempArr.ptr
        memcpy(samplers, ptr, samplers.size * 4L)
        glDeleteSamplers(samplers.size, ptr.address)
    }

    fun glDeleteSamplers(samplers: IntArray, offset: Int, length: Int) {
        tempArr.ensureCapacity(length * 4L, false)
        val ptr = tempArr.ptr
        memcpy(samplers, offset * 4L, ptr, 0, length * 4L)
        glDeleteSamplers(length, ptr.address)
    }
}