package dev.luna5ama.glwrapper.impl

import dev.luna5ama.glwrapper.api.glCreateRenderbuffers
import dev.luna5ama.glwrapper.api.glDeleteRenderbuffers
import dev.luna5ama.glwrapper.api.glNamedRenderbufferStorage
import dev.luna5ama.glwrapper.api.glNamedRenderbufferStorageMultisample

class RenderbufferObject : IGLObject, FramebufferObject.Attachment {
    override val id = glCreateRenderbuffers()

    override var sizeX = 0; private set
    override var sizeY = 0; private set

    fun allocate(width: Int, height: Int, internalFormat: Int) {
        sizeX = width
        sizeY = height
        glNamedRenderbufferStorage(id, internalFormat, width, height)
    }

    fun allocateMultisample(samples: Int, width: Int, height: Int, internalFormat: Int) {
        glNamedRenderbufferStorageMultisample(id, samples, internalFormat, width, height)
    }

    override fun destroy() {
        glDeleteRenderbuffers(id)
    }
}