package dev.luna5ama.glwrapper.api

interface GLWrapperProvider {
    val priority: Int
    fun create(): GLWrapper
}