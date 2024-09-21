package dev.luna5ama.glwrapper.base

interface GLBase {
    val glWrapperInstance: GLWrapper
    val tempArr get() = glWrapperInstance.properties.tempArr

    fun interface Constructor<T: GLBase> {
        operator fun invoke(glWrapper: GLWrapper): T
    }
}