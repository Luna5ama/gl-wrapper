package dev.luna5ama.glwrapper.objects

import dev.luna5ama.glwrapper.base.*
import dev.luna5ama.glwrapper.enums.GLObjectType
import dev.luna5ama.kmogus.Ptr

interface IGLBinding {
    fun bind()
    fun unbind()
}

interface IGLObject {
    val id: Int
    val type: GLObjectType
    var label: String

    fun create()
    fun destroy()

    fun tryCreate()
    fun checkCreated()

    fun resetID()

    class Impl(override val type: GLObjectType, private val createArg: Int = -1) : IGLObject {
        override var label = ""
            set(value) {
                if (field != value) {
                    if (value.isNotEmpty()) {
                        glObjectLabel(type.identifier, id, value)
                    } else {
                        glObjectLabel(type.identifier, id, 0, Ptr.NULL)
                    }
                }
                field = value
            }

        internal var id0 = 0; private set
        override val id: Int
            get() {
                tryCreate()
                return id0
            }

        override fun create() {
            check(id0 == 0) { "Object already created" }
            id0 = type.create(createArg)
            if (label.isNotEmpty()) {
                glObjectLabel(type.identifier, id0, label)
            }
        }

        override fun destroy() {
            if (id0 != 0) {
                type.destroy(id)
                id0 = 0
            }
        }

        override fun tryCreate() {
            if (id0 == 0) {
                create()
            }
        }

        override fun checkCreated() {
            check(id0 != 0) { "Object not created" }
        }

        override fun resetID() {
            id0 = 0
        }
    }
}

interface IGLTargetBinding : IGLObject {
    fun bind(target: Int)
    fun unbind(target: Int)
}

@Suppress("PARAMETER_NAME_CHANGED_ON_OVERRIDE")
interface IGLSampler : IGLObject {
    fun parameteri(pname: Int, param: Int)
    fun parameteriv(pname: Int, v1: Int, v2: Int, v3: Int, v4: Int)
    fun parameterf(pname: Int, param: Float)
    fun parameterfv(pname: Int, v1: Float, v2: Float, v3: Float, v4: Float)
}

fun IGLSampler.setFilter(min: Int, mag: Int) {
    parameteri(GL_TEXTURE_MIN_FILTER, min)
    parameteri(GL_TEXTURE_MAG_FILTER, mag)
}

fun IGLSampler.setMipLevels(base: Int, max: Int) {
    parameteri(GL_TEXTURE_BASE_LEVEL, base)
    parameteri(GL_TEXTURE_MAX_LEVEL, max)
}

fun IGLSampler.setMipLod(min: Float, max: Float, bias: Float = 0.0f) {
    parameterf(GL_TEXTURE_MIN_LOD, min)
    parameterf(GL_TEXTURE_MAX_LOD, max)
    parameterf(GL_TEXTURE_LOD_BIAS, bias)
}

fun IGLSampler.setAnisotropy(anisotropy: Float) {
    return parameterf(GL_TEXTURE_MAX_ANISOTROPY, anisotropy)
}

fun IGLSampler.setWrap(s: Int, t: Int) {
    parameteri(GL_TEXTURE_WRAP_S, s)
    parameteri(GL_TEXTURE_WRAP_T, t)
}

fun IGLSampler.setWrap(s: Int, t: Int, r: Int) {
    parameteri(GL_TEXTURE_WRAP_S, s)
    parameteri(GL_TEXTURE_WRAP_T, t)
    parameteri(GL_TEXTURE_WRAP_R, r)
}

fun IGLSampler.setBorderColor(r: Float, g: Float, b: Float, a: Float) {
    return parameterfv(GL_TEXTURE_BORDER_COLOR, r, g, b, a)
}

fun IGLSampler.setCompare(mode: Int, func: Int) {
    parameteri(GL_TEXTURE_COMPARE_MODE, mode)
    parameteri(GL_TEXTURE_COMPARE_FUNC, func)
}

interface IGLSized1D : IGLObject {
    val sizeX: Int

    val width get() = sizeX

    val sizeBit: Long
        get() = (0b001L shl 61) or (sizeX and 0xFF).toLong()
}

interface IGLSized2D : IGLSized1D {
    val sizeY: Int

    val height get() = sizeY

    override val sizeBit: Long
        get() = (0b010L shl 61) or ((sizeY and 0xFF).toLong() shl 16) or (sizeX and 0xFF).toLong()
}

interface IGLSized3D : IGLSized2D {
    val sizeZ: Int

    val depth get() = sizeZ

    override val sizeBit: Long
        get() = (0b100L shl 61) or ((sizeZ and 0xFF).toLong() shl 32) or ((sizeY and 0xFF).toLong() shl 16) or (sizeX and 0xFF).toLong()
}