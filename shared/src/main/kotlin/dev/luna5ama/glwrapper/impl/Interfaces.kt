package dev.luna5ama.glwrapper.impl

interface IGLBinding {
    fun bind()
    fun unbind()
}

interface IGLObject {
    val id: Int

    fun destroy()
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