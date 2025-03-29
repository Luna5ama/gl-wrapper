package dev.luna5ama.glwrapper.base

import java.net.URI
import java.net.URL

interface ShaderPathResolver {
    fun resolve(path: String): Path

    object Default : ShaderPathResolver {
        override fun resolve(path: String): Path {
            return PathImpl(URI("/$path"))
        }

        private class PathImpl(private val uri: URI) : Path {
            override val url: URL = uri.path.let {
                Default::class.java.getResource(it) ?: throw IllegalArgumentException("Invalid shader path: $it")
            }

            override fun resolve(path: String): Path {
                if (path.startsWith('/')) {
                    return PathImpl(URI(path))
                }
                return PathImpl(uri.resolve(path))
            }
        }
    }

    interface Path {
        val url: URL
        fun resolve(path: String): Path
    }
}