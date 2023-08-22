package dev.luna5ama.glwrapper.impl

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
    }
}

interface IGLTargetBinding {
    fun bind(target: Int)
    fun unbind(target: Int)
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