package dev.luna5ama.glwrapper.impl

import dev.luna5ama.glwrapper.api.*

interface IGLBinding {
    fun bind()
    fun unbind()
}

interface IGLObject {
    val id: Int
    val type: GLObjectType

    fun create()
    fun destroy()

    fun tryCreate()
    fun checkCreated()

    fun resetID()

    class Impl(override val type: GLObjectType, private val createArg: Int = -1) : IGLObject {
        internal var id0 = 0; private set
        override val id: Int
            get() {
                tryCreate()
                return id0
            }

        override fun create() {
            check(id0 == 0) { "Object already created" }
            id0 = type.create(createArg)
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
interface IGLSampler : IGLObject, IGLTargetBinding {
    override fun bind(unit: Int)
    override fun unbind(unit: Int)

    fun parameteri0(pname: Int, param: Int)
    fun parameteriv0(pname: Int, v1: Int, v2: Int, v3: Int, v4: Int)
    fun parameterf0(pname: Int, param: Float)
    fun parameterfv0(pname: Int, v1: Float, v2: Float, v3: Float, v4: Float)
}

fun <T : IGLSampler> T.parameteri(pname: Int, param: Int): T {
    parameteri0(pname, param)
    return this
}

fun <T : IGLSampler> T.parameteriv(pname: Int, v1: Int, v2: Int, v3: Int, v4: Int): T {
    parameteriv0(pname, v1, v2, v3, v4)
    return this
}

fun <T : IGLSampler> T.parameterf(pname: Int, param: Float): T {
    parameterf0(pname, param)
    return this
}

fun <T : IGLSampler> T.parameterfv(pname: Int, v1: Float, v2: Float, v3: Float, v4: Float): T {
    parameterfv0(pname, v1, v2, v3, v4)
    return this
}

fun <T : IGLSampler> T.setFilter(min: Int, mag: Int): T {
    return parameteri(GL_TEXTURE_MIN_FILTER, min)
        .parameteri(GL_TEXTURE_MAG_FILTER, mag)
}

fun <T : IGLSampler> T.setMipLevels(base: Int, max: Int): T {
    return parameteri(GL_TEXTURE_BASE_LEVEL, base)
        .parameteri(GL_TEXTURE_MAX_LEVEL, max)
}

fun <T : IGLSampler> T.setMipLod(min: Float, max: Float, bias: Float = 0.0f): T {
    return parameterf(GL_TEXTURE_MIN_LOD, min)
        .parameterf(GL_TEXTURE_MAX_LOD, max)
        .parameterf(GL_TEXTURE_LOD_BIAS, bias)
}

fun <T : IGLSampler> T.setAnisotropy(anisotropy: Float): T {
    return parameterf(GL_TEXTURE_MAX_ANISOTROPY, anisotropy)
}

fun <T : IGLSampler> T.setWrap(s: Int, t: Int): T {
    return parameteri(GL_TEXTURE_WRAP_S, s)
        .parameteri(GL_TEXTURE_WRAP_T, t)
}

fun <T : IGLSampler> T.setWrap(s: Int, t: Int, r: Int): T {
    return parameteri(GL_TEXTURE_WRAP_S, s)
        .parameteri(GL_TEXTURE_WRAP_T, t)
        .parameteri(GL_TEXTURE_WRAP_R, r)
}

fun <T : IGLSampler> T.setBorderColor(r: Float, g: Float, b: Float, a: Float): T {
    return parameterfv(GL_TEXTURE_BORDER_COLOR, r, g, b, a)
}

fun <T : IGLSampler> T.setCompare(mode: Int, func: Int): T {
    return parameteri(GL_TEXTURE_COMPARE_MODE, mode)
        .parameteri(GL_TEXTURE_COMPARE_FUNC, func)
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