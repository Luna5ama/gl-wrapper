package dev.luna5ama.glwrapper.base

import java.net.URI
import java.net.URL
import kotlin.io.path.Path

interface ShaderPathResolver {
    fun resolve(path: String): Path

    object Default : ShaderPathResolver {
        override fun resolve(path: String): Path {
            return PathImpl(Default::class.java.getResource("/$path") ?: Path(path).toUri().toURL())
        }

        private class PathImpl(override val url: URL) : Path {
            private val uri: URI = url.toURI()

            override fun resolve(path: String): Path {
                if (path.startsWith('/')) {
                    return Default.resolve(path.substring(1))
                }
                return PathImpl(uri.resolve(path).toURL())
            }
        }
    }

    interface Path {
        val url: URL
        fun resolve(path: String): Path
    }
}