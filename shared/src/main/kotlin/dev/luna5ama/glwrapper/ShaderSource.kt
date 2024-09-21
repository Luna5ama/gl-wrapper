package dev.luna5ama.glwrapper

import dev.luna5ama.glwrapper.base.GLWrapper
import dev.luna5ama.glwrapper.base.ShaderPathResolver
import dev.luna5ama.glwrapper.enums.ShaderStage
import dev.luna5ama.kmogus.Arr
import dev.luna5ama.kmogus.asByteBuffer
import dev.luna5ama.kmogus.ensureCapacity
import dev.luna5ama.kmogus.nullByteBuffer
import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap
import it.unimi.dsi.fastutil.objects.ObjectArrayList
import java.lang.ref.SoftReference
import java.nio.charset.CodingErrorAction
import java.security.DigestInputStream
import java.security.MessageDigest

sealed class ShaderSource(internal val provider: Provider<*>, internal val sourceKey: SourceKey) {
    val name = with(sourceKey.path.url.toString()) {
        substring(lastIndexOf('/') + 1, lastIndexOf('.'))
    }

    fun resolveCodeSrc(): String {
        return provider.resolveCodeSrc(sourceKey)
    }

    abstract val shaderStage: ShaderStage?
    abstract val typeName: String

    override fun toString(): String {
        return "[$typeName]$name"
    }

    class Vert private constructor(provider: Provider<Vert>, sourceKey: SourceKey) : ShaderSource(provider, sourceKey) {
        override val shaderStage get() = ShaderStage.VertexShader
        override val typeName get() = "Vert"

        companion object : Provider<Vert>() {
            override fun newInstance(sourceKey: SourceKey): Vert {
                return Vert(this, sourceKey)
            }
        }
    }

    class Geom private constructor(provider: Provider<Geom>, sourceKey: SourceKey) : ShaderSource(provider, sourceKey) {
        override val shaderStage get() = ShaderStage.GeometryShader
        override val typeName get() = "Geom"

        companion object : Provider<Geom>() {
            override fun newInstance(sourceKey: SourceKey): Geom {
                return Geom(this, sourceKey)
            }
        }
    }

    class TessCtrl private constructor(provider: Provider<TessCtrl>, sourceKey: SourceKey) : ShaderSource(provider, sourceKey) {
        override val shaderStage get() = ShaderStage.TessCtrlShader
        override val typeName get() = "TessCtrl"

        companion object : Provider<TessCtrl>() {
            override fun newInstance(sourceKey: SourceKey): TessCtrl {
                return TessCtrl(this, sourceKey)
            }
        }
    }

    class TessEval private constructor(provider: Provider<TessEval>, sourceKey: SourceKey) : ShaderSource(provider, sourceKey) {
        override val shaderStage get() = ShaderStage.TessEvalShader
        override val typeName get() = "TessEval"

        companion object : Provider<TessEval>() {
            override fun newInstance(sourceKey: SourceKey): TessEval {
                return TessEval(this, sourceKey)
            }
        }
    }

    class Frag private constructor(provider: Provider<Frag>, sourceKey: SourceKey) : ShaderSource(provider, sourceKey) {
        override val shaderStage get() = ShaderStage.FragmentShader
        override val typeName get() = "Frag"

        companion object : Provider<Frag>() {
            override fun newInstance(sourceKey: SourceKey): Frag {
                return Frag(this, sourceKey)
            }
        }
    }

    class Comp private constructor(provider: Provider<Comp>, sourceKey: SourceKey) : ShaderSource(provider, sourceKey) {
        override val shaderStage get() = ShaderStage.ComputeShader
        override val typeName get() = "Comp"

        companion object : Provider<Comp>() {
            override fun newInstance(sourceKey: SourceKey): Comp {
                return Comp(this, sourceKey)
            }
        }
    }

    class Task private constructor(provider: Provider<Task>, sourceKey: SourceKey) : ShaderSource(provider, sourceKey) {
        override val shaderStage get() = ShaderStage.TaskShader
        override val typeName get() = "Task"

        companion object : Provider<Task>() {
            override fun newInstance(sourceKey: SourceKey): Task {
                return Task(this, sourceKey)
            }
        }
    }

    class Mesh private constructor(provider: Provider<Mesh>, sourceKey: SourceKey) : ShaderSource(provider, sourceKey) {
        override val shaderStage get() = ShaderStage.MeshShader
        override val typeName get() = "Mesh"

        companion object : Provider<Mesh>() {
            override fun newInstance(sourceKey: SourceKey): Mesh {
                return Mesh(this, sourceKey)
            }
        }
    }

    class Lib private constructor(provider: Provider<Lib>, sourceKey: SourceKey) : ShaderSource(provider, sourceKey) {
        override val shaderStage get() = null
        override val typeName = "Lib"

        companion object : Provider<Lib>() {
            override fun newInstance(sourceKey: SourceKey): Lib {
                return Lib(this, sourceKey)
            }
        }
    }

    abstract class Provider<T : ShaderSource> {
        private val cacheMap = Object2ObjectOpenHashMap<SourceKey, SoftReference<Cache>>()

        private val bufferArr = Arr.malloc(1024)
        private val byteBuffer = nullByteBuffer()

        abstract fun newInstance(sourceKey: SourceKey): T

        private fun resolveCodeSrc(stringBuilder: StringBuilder, sourceKey: SourceKey) {
            var inserted = false
            getCache(sourceKey).lines.forEachIndexed { index, line ->
                when (line) {
                    is String -> {
                        if (!inserted && !line.startsWith("#version") && !line.startsWith("#define")) {
                            stringBuilder.appendLine(sourceKey.defines)
                            inserted = true
                        }
                        stringBuilder.appendLine(line)
                    }
                    is Lib -> line.provider.resolveCodeSrc(stringBuilder, line.sourceKey)
                }
            }
        }

        internal fun resolveCodeSrc(sourceKey: SourceKey): String {
            return buildString {
                resolveCodeSrc(this, sourceKey)
            }
        }

        private fun getCache(sourceKey: SourceKey): Cache {
            val md5 = MessageDigest.getInstance("MD5")
            var offset = 0L

            sourceKey.path.url.openStream().use { inputStream ->
                DigestInputStream(inputStream, md5).use {
                    var byte = it.read()
                    while (byte != -1) {
                        bufferArr.ensureCapacity(offset + 1L, false)
                        bufferArr.ptr.setByte(offset++, byte.toByte())
                        byte = it.read()
                    }
                }
            }

            if (offset == 0L) throw IllegalArgumentException("Shader file is empty (${sourceKey.path.url})")

            val newHash = MD5Hash(md5.digest())

            fun resolveIncludeURL(includePath: String): ShaderPathResolver.Path {
                return sourceKey.path.resolve(includePath)
            }

            fun processLines(input: String): Any {
                return includeRegex.matchEntire(input)?.let {
                    Lib.newInstance(SourceKey(resolveIncludeURL(it.groupValues[1]), ""))
                } ?: input
            }

            synchronized(this) {
                var source = cacheMap[sourceKey]?.get()
                if (source == null || source.srcHash != newHash) {
                    val decoder = Charsets.UTF_8.newDecoder()
                        .onMalformedInput(CodingErrorAction.REPORT)
                        .onUnmappableCharacter(CodingErrorAction.REPORT)

                    val lines = decoder.decode(bufferArr.ptr.asByteBuffer(offset.toInt(), byteBuffer)).lineSequence()
                        .mapTo(ObjectArrayList()) { processLines(it) }

                    source = Cache(newHash, lines)
                    cacheMap[sourceKey] = SoftReference(source)
                }

                return source
            }
        }

        private val includeRegex = "#include\\s+\"([^\"]+)\"".toRegex()

        fun clearCache() {
            synchronized(this) {
                cacheMap.clear()
            }
        }

        operator fun invoke(path: String): T {
            return newInstance(SourceKey(GLWrapper.pathResolver.resolve(path), ""))
        }

        inline operator fun invoke(
            path: String,
            block: DefineBuilder.() -> Unit
        ): T {
            return newInstance(SourceKey(GLWrapper.pathResolver.resolve(path), DefineBuilder().apply(block).build()))
        }

        private inner class Cache(val srcHash: MD5Hash, val lines: List<Any>)
    }

    class DefineBuilder {
        private val stringBuilder = StringBuilder()

        fun define(name: String) {
            stringBuilder.append("#define")
            stringBuilder.append(' ')
            stringBuilder.append(name)
            stringBuilder.appendLine()
        }

        fun define(name: String, value: Any) {
            stringBuilder.append("#define")
            stringBuilder.append(' ')
            stringBuilder.append(name)
            stringBuilder.append(' ')
            stringBuilder.append(value.toString())
            stringBuilder.appendLine()
        }

        fun define(name: String, value: Boolean) {
            stringBuilder.append("#define")
            stringBuilder.append(' ')
            stringBuilder.append(name)
            stringBuilder.append(' ')
            stringBuilder.append(value)
            stringBuilder.appendLine()
        }

        fun define(name: String, value: Int) {
            stringBuilder.append("#define")
            stringBuilder.append(' ')
            stringBuilder.append(name)
            stringBuilder.append(' ')
            stringBuilder.append(value)
            stringBuilder.appendLine()
        }

        fun build() = stringBuilder.toString()
    }

    private class MD5Hash(array: ByteArray) {
        val a: Long
        val b: Long
        val hash: Int

        init {
            var a = array[0].toLong() shl 56
            a = a or array[1].toLong() shl 48
            a = a or array[2].toLong() shl 40
            a = a or array[3].toLong() shl 32
            a = a or array[4].toLong() shl 24
            a = a or array[5].toLong() shl 16
            a = a or array[6].toLong() shl 8
            a = a or array[7].toLong()
            this.a = a

            var b = array[8].toLong() shl 56
            b = b or array[9].toLong() shl 48
            b = b or array[10].toLong() shl 40
            b = b or array[11].toLong() shl 32
            b = b or array[12].toLong() shl 24
            b = b or array[13].toLong() shl 16
            b = b or array[14].toLong() shl 8
            b = b or array[15].toLong()
            this.b = b

            hash = a.hashCode() * 31 + b.hashCode()
        }

        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (other !is MD5Hash) return false

            if (a != other.a) return false
            if (b != other.b) return false

            return true
        }

        override fun hashCode(): Int {
            return hash
        }
    }

    data class SourceKey(val path: ShaderPathResolver.Path, val defines: String)

    companion object {
        inline operator fun <T : ShaderSource> T.invoke(crossinline block: DefineBuilder.() -> Unit): T {
            return this.withDefines(DefineBuilder().apply(block))
        }

        fun <T : ShaderSource> T.withDefines(defines: DefineBuilder): T {
            val definesStr = defines.build()
            return if (definesStr.isEmpty()) {
                this
            } else {
                @Suppress("UNCHECKED_CAST")
                provider.newInstance(SourceKey(sourceKey.path, sourceKey.defines + "\n" + definesStr)) as T
            }
        }

        fun clearCache() {
            Vert.clearCache()
            Geom.clearCache()
            TessCtrl.clearCache()
            TessEval.clearCache()
            Frag.clearCache()
            Comp.clearCache()
            Task.clearCache()
            Mesh.clearCache()
            Lib.clearCache()
        }
    }
}