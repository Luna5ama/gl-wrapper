package dev.luna5ama.glwrapper.api

import dev.luna5ama.glwrapper.ShaderSource

interface GLWrapperProvider {
    val priority: Int
    fun create(shaderSrcPathResolver: ShaderSource.PathResolver = ShaderSource.PathResolver.Default): GLWrapper
}