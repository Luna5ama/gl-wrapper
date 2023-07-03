package dev.luna5ama.glwrapper.impl

import dev.luna5ama.glwrapper.api.*
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

sealed class ShaderSource(val name: String, val glTypeEnum: Int, val codeSrc: CharSequence) {
    private val lines by lazy { codeSrc.lines() }
    protected abstract val provider: Provider<*>
    protected abstract val typeName: String

    override fun toString(): String {
        return "[$typeName]$name"
    }

    class Vert private constructor(name: String, codeSrc: CharSequence) :
        ShaderSource(name, GL_VERTEX_SHADER, codeSrc) {
        override val provider: Provider<Vert> get() = Companion
        override val typeName get() = "Vert"

        companion object : Provider<Vert>() {
            override fun newInstance(name: String, codeSrc: CharSequence): Vert {
                return Vert(name, codeSrc)
            }
        }
    }

    class Geom private constructor(name: String, codeSrc: CharSequence) :
        ShaderSource(name, GL_GEOMETRY_SHADER, codeSrc) {
        override val provider: Provider<Geom> get() = Companion
        override val typeName get() = "Geom"

        companion object : Provider<Geom>() {
            override fun newInstance(name: String, codeSrc: CharSequence): Geom {
                return Geom(name, codeSrc)
            }
        }
    }

    class TessCtrl private constructor(name: String, codeSrc: CharSequence) :
        ShaderSource(name, GL_TESS_CONTROL_SHADER, codeSrc) {
        override val provider: Provider<TessCtrl> get() = Companion
        override val typeName get() = "TessCtrl"

        companion object : Provider<TessCtrl>() {
            override fun newInstance(name: String, codeSrc: CharSequence): TessCtrl {
                return TessCtrl(name, codeSrc)
            }
        }
    }

    class TessEval private constructor(name: String, codeSrc: CharSequence) :
        ShaderSource(name, GL_TESS_EVALUATION_SHADER, codeSrc) {
        override val provider: Provider<TessEval> get() = Companion
        override val typeName get() = "TessEval"

        companion object : Provider<TessEval>() {
            override fun newInstance(name: String, codeSrc: CharSequence): TessEval {
                return TessEval(name, codeSrc)
            }
        }
    }

    class Frag private constructor(name: String, codeSrc: CharSequence) :
        ShaderSource(name, GL_FRAGMENT_SHADER, codeSrc) {
        override val provider: Provider<Frag> get() = Companion
        override val typeName get() = "Frag"

        companion object : Provider<Frag>() {
            override fun newInstance(name: String, codeSrc: CharSequence): Frag {
                return Frag(name, codeSrc)
            }
        }
    }

    class Comp private constructor(name: String, codeSrc: CharSequence) :
        ShaderSource(name, GL_COMPUTE_SHADER, codeSrc) {
        override val provider: Provider<Comp> get() = Companion
        override val typeName get() = "Comp"

        companion object : Provider<Comp>() {
            override fun newInstance(name: String, codeSrc: CharSequence): Comp {
                return Comp(name, codeSrc)
            }
        }
    }

    private class Lib private constructor(name: String, codeSrc: CharSequence) : ShaderSource(name, -1, codeSrc) {
        override val provider: Provider<Lib> get() = Companion
        override val typeName = "Lib"

        companion object : Provider<Lib>() {
            override fun newInstance(name: String, codeSrc: CharSequence): Lib {
                return Lib(name, codeSrc)
            }
        }
    }

    abstract class Provider<T : ShaderSource> protected constructor() {
        private val cacheMap = Object2ObjectOpenHashMap<String, SoftReference<Cache>>()

        private val bufferArr = Arr.malloc(1024)
        private val byteBuffer = nullByteBuffer()

        protected abstract fun newInstance(name: String, codeSrc: CharSequence): T

        operator fun invoke(path: String): T {
            return getCache(path).instance
        }

        inline operator fun invoke(path: String, crossinline block: DefineBuilder.() -> Unit): T {
            return getWithDefines(path, DefineBuilder().apply(block))
        }

        fun getWithDefines(path: String, defines: DefineBuilder): T {
            return getWithDefines(getCache(path), defines)
        }

        private fun getWithDefines(cache: Cache, defines: DefineBuilder): T {
            return if (defines.stringBuilder.isEmpty()) {
                cache.instance
            } else {
                buildWithDefines(cache.name, cache.lines, defines)
            }
        }

        fun buildWithDefines(name: String, lines: List<CharSequence>, defines: DefineBuilder): T {
            val stringBuilder = StringBuilder()

            var inserted = false

            for (i in lines.indices) {
                val line = lines[i]
                if (!inserted && !line.startsWith("#version") && !line.startsWith("#define")) {
                    stringBuilder.append(defines.stringBuilder)
                    inserted = true
                }
                stringBuilder.appendLine(line)
            }

            return newInstance(name, stringBuilder)
        }

        private fun getCache(path: String): Cache {
            val url = javaClass.getResource(path) ?: throw IllegalArgumentException("Invalid shader path ($path)")
            val md5 = MessageDigest.getInstance("MD5")

            var offset = 0L

            url.openStream().use { inputStream ->
                DigestInputStream(inputStream, md5).use {
                    var byte = it.read()
                    while (byte != -1) {
                        bufferArr.ensureCapacity(offset + 1L, false)
                        bufferArr.ptr.setByte(offset++, byte.toByte())
                        byte = it.read()
                    }
                }
            }

            if (offset == 0L) throw IllegalArgumentException("Shader file is empty ($path)")

            val hash = MD5Hash(md5.digest())

            synchronized(this) {
                var source = cacheMap[path]?.get()
                if (source == null || source.hash != hash) {
                    val decoder = Charsets.UTF_8.newDecoder()
                        .onMalformedInput(CodingErrorAction.REPORT)
                        .onUnmappableCharacter(CodingErrorAction.REPORT)

                    val lines =
                        decoder.decode(bufferArr.ptr.asByteBuffer(offset.toInt(), byteBuffer)).lineSequence()
                            .mapTo(ObjectArrayList()) {
                                if (it.startsWith("#include")) {
                                    val importPath = it.substring(it.indexOf('/'))
                                    val importContent = Lib(importPath).codeSrc
                                    importContent
                                } else {
                                    it
                                }
                            }

                    source = Cache(path.substring(path.lastIndexOf('/') + 1, path.lastIndexOf('.')), lines, hash)
                    cacheMap[path] = SoftReference(source)
                }

                return source
            }
        }

        private inner class Cache(val name: String, val lines: List<CharSequence>, val hash: MD5Hash) {
            val str by lazy {
                buildString {
                    for (i in lines.indices) {
                        appendLine(lines[i])
                    }
                }
            }
            val instance by lazy { newInstance(name, str) }
        }
    }

    class DefineBuilder {
        internal val stringBuilder = StringBuilder()

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

    companion object {
        inline operator fun <T : ShaderSource> T.invoke(crossinline block: DefineBuilder.() -> Unit): T {
            return this.withDefines(DefineBuilder().apply(block))
        }

        fun <T : ShaderSource> T.withDefines(defines: DefineBuilder): T {
            return if (defines.stringBuilder.isEmpty()) {
                this
            } else {
                @Suppress("UNCHECKED_CAST")
                (this.provider as Provider<T>).buildWithDefines(this.name, this.lines, defines)
            }
        }
    }
}