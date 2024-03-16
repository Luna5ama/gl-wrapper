package dev.luna5ama.glwrapper.impl

import dev.luna5ama.glwrapper.api.*
import dev.luna5ama.kmogus.Ptr

sealed class GLObjectType(val identifier: Int, val isContainer: Boolean) {
    data object Buffer : GLObjectType(GL_BUFFER, false) {
        override fun create(arg: Int): Int {
            return glCreateBuffers()
        }

        override fun create(arg: Int, n: Int, ptr: Ptr) {
            glCreateBuffers(n, ptr)
        }

        fun create(): Int {
            return create(0)
        }

        fun create(n: Int, ptr: Ptr) {
            create(0, n, ptr)
        }

        override fun destroy(id: Int) {
            glDeleteBuffers(id)
        }

        override fun destroy(n: Int, ptr: Ptr) {
            glDeleteBuffers(n, ptr)
        }
    }

    data object Shader : GLObjectType(GL_SHADER, false) {
        override fun create(arg: Int): Int {
            return glCreateShader(arg)
        }

        override fun create(arg: Int, n: Int, ptr: Ptr) {
            var ptr1 = ptr
            repeat(n) {
                ptr1 = ptr1.setIntInc(glCreateShader(arg))
            }
        }

        override fun destroy(id: Int) {
            glDeleteShader(id)
        }

        override fun destroy(n: Int, ptr: Ptr) {
            repeat(n) {
                destroy(ptr.getInt(it * 4L))
            }
        }
    }

    data object Program : GLObjectType(GL_PROGRAM, false) {
        override fun create(arg: Int): Int {
            return glCreateProgram()
        }

        override fun create(arg: Int, n: Int, ptr: Ptr) {
            var ptr1 = ptr
            repeat(n) {
                ptr1 = ptr1.setIntInc(glCreateProgram())
            }
        }

        fun create(): Int {
            return create(0)
        }

        fun create(n: Int, ptr: Ptr) {
            create(0, n, ptr)
        }

        override fun destroy(id: Int) {
            glDeleteProgram(id)
        }

        override fun destroy(n: Int, ptr: Ptr) {
            repeat(n) {
                destroy(ptr.getInt(it * 4L))
            }
        }
    }

    data object Query : GLObjectType(GL_QUERY, false) {
        override fun create(arg: Int): Int {
            throw UnsupportedOperationException()
        }

        override fun create(arg: Int, n: Int, ptr: Ptr) {
            throw UnsupportedOperationException()
        }

        fun create(): Int {
            return create(0)
        }

        fun create(n: Int, ptr: Ptr) {
            create(0, n, ptr)
        }

        override fun destroy(id: Int) {
            throw UnsupportedOperationException()
        }

        override fun destroy(n: Int, ptr: Ptr) {
            throw UnsupportedOperationException()
        }
    }

    data object Renderbuffer : GLObjectType(GL_RENDERBUFFER, false) {
        override fun create(arg: Int): Int {
            return glCreateRenderbuffers()
        }

        override fun create(arg: Int, n: Int, ptr: Ptr) {
            glCreateRenderbuffers(n, ptr)
        }

        fun create(): Int {
            return create(0)
        }

        fun create(n: Int, ptr: Ptr) {
            create(0, n, ptr)
        }

        override fun destroy(id: Int) {
            glDeleteRenderbuffers(id)
        }

        override fun destroy(n: Int, ptr: Ptr) {
            glDeleteRenderbuffers(n, ptr)
        }
    }

    data object Sampler : GLObjectType(GL_SAMPLER, false) {
        override fun create(arg: Int): Int {
            return glCreateSamplers()
        }

        override fun create(arg: Int, n: Int, ptr: Ptr) {
            glCreateSamplers(n, ptr)
        }

        fun create(): Int {
            return create(0)
        }

        fun create(n: Int, ptr: Ptr) {
            create(0, n, ptr)
        }

        override fun destroy(id: Int) {
            glDeleteSamplers(id)
        }

        override fun destroy(n: Int, ptr: Ptr) {
            glDeleteSamplers(n, ptr)
        }
    }

    sealed class Texture : GLObjectType(GL_TEXTURE, false) {
        final override fun destroy(id: Int) {
            glDeleteTextures(id)
        }

        final override fun destroy(n: Int, ptr: Ptr) {
            glDeleteTextures(n, ptr)
        }

        data object Storage : Texture() {
            override fun create(arg: Int): Int {
                return glCreateTextures(arg)
            }

            override fun create(arg: Int, n: Int, ptr: Ptr) {
                glCreateTextures(arg, n, ptr)
            }
        }

        data object View : Texture() {
            override fun create(arg: Int): Int {
                return glGenTextures()
            }

            override fun create(arg: Int, n: Int, ptr: Ptr) {
                glGenTextures(n, ptr)
            }
        }
    }

    data object Framebuffer : GLObjectType(GL_FRAMEBUFFER, true) {
        override fun create(arg: Int): Int {
            return glCreateFramebuffers()
        }

        override fun create(arg: Int, n: Int, ptr: Ptr) {
            glCreateFramebuffers(n, ptr)
        }

        fun create(): Int {
            return create(0)
        }

        fun create(n: Int, ptr: Ptr) {
            create(0, n, ptr)
        }

        override fun destroy(id: Int) {
            glDeleteFramebuffers(id)
        }

        override fun destroy(n: Int, ptr: Ptr) {
            glDeleteFramebuffers(n, ptr)
        }
    }

    data object ProgramPipeline : GLObjectType(GL_PROGRAM_PIPELINE, true) {
        override fun create(arg: Int): Int {
            throw UnsupportedOperationException()
        }

        override fun create(arg: Int, n: Int, ptr: Ptr) {
            throw UnsupportedOperationException()
        }

        fun create(): Int {
            return create(0)
        }

        fun create(n: Int, ptr: Ptr) {
            create(0, n, ptr)
        }

        override fun destroy(id: Int) {
            throw UnsupportedOperationException()
        }

        override fun destroy(n: Int, ptr: Ptr) {
            throw UnsupportedOperationException()
        }
    }

    data object TransformFeedback : GLObjectType(GL_TRANSFORM_FEEDBACK, true) {
        override fun create(arg: Int): Int {
            throw UnsupportedOperationException()
        }

        override fun create(arg: Int, n: Int, ptr: Ptr) {
            throw UnsupportedOperationException()
        }

        fun create(): Int {
            return create(0)
        }

        fun create(n: Int, ptr: Ptr) {
            create(0, n, ptr)
        }

        override fun destroy(id: Int) {
            throw UnsupportedOperationException()
        }

        override fun destroy(n: Int, ptr: Ptr) {
            throw UnsupportedOperationException()
        }
    }

    data object VertexArray : GLObjectType(GL_VERTEX_ARRAY, true) {
        override fun create(arg: Int): Int {
            return glCreateVertexArrays()
        }

        override fun create(arg: Int, n: Int, ptr: Ptr) {
            glCreateVertexArrays(n, ptr)
        }

        fun create(): Int {
            return create(0)
        }

        fun create(n: Int, ptr: Ptr) {
            create(0, n, ptr)
        }

        override fun destroy(id: Int) {
            glDeleteVertexArrays(id)
        }

        override fun destroy(n: Int, ptr: Ptr) {
            glDeleteVertexArrays(n, ptr)
        }
    };

    abstract fun create(arg: Int): Int
    abstract fun create(arg: Int, n: Int, ptr: Ptr)

    abstract fun destroy(id: Int)
    abstract fun destroy(n: Int, ptr: Ptr)
}