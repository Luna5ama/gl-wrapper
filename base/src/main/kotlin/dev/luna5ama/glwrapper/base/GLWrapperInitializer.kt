package dev.luna5ama.glwrapper.base

interface GLWrapperInitializer {
    val priority: Int
    fun createWrapperInstance(): GLWrapper
    fun createPathResolver(): ShaderPathResolver {
        return ShaderPathResolver.Default
    }
}