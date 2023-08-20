package dev.luna5ama.glwrapper.api.intel

import dev.luna5ama.glwrapper.api.*

class IntelGL45(delegate: IGL45) : PatchedGL45(delegate) {
    override fun glVertexArrayElementBuffer(vaobj: Int, buffer: Int) {
        if (buffer == 0) {
            val prevVao = glGetInteger(GL_VERTEX_ARRAY_BINDING)
            glBindVertexArray(vaobj)
            glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, 0)
            glBindVertexArray(prevVao)
        } else {
            super.glVertexArrayElementBuffer(vaobj, buffer)
        }
    }
}