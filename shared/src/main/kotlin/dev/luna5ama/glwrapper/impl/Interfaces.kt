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
}

interface IGLSized2D : IGLSized1D {
    val sizeY: Int

    val height get() = sizeY
}

interface IGLSized3D : IGLSized2D {
    val sizeZ: Int

    val depth get() = sizeZ
}