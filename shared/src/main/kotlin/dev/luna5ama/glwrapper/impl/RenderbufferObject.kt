package dev.luna5ama.glwrapper.impl

import dev.luna5ama.glwrapper.api.glCreateRenderbuffers
import dev.luna5ama.glwrapper.api.glDeleteRenderbuffers
import dev.luna5ama.glwrapper.api.glNamedRenderbufferStorage
import dev.luna5ama.glwrapper.api.glNamedRenderbufferStorageMultisample

class RenderbufferObject : IGLObject, FramebufferObject.Attachment {
    override val id = glCreateRenderbuffers()

    override var sizeX = 0; private set
    override var sizeY = 0; private set

    fun allocate(width: Int, height: Int, internalFormat: Int): RenderbufferObject {
        sizeX = width
        sizeY = height
        glNamedRenderbufferStorage(id, internalFormat, width, height)
        return this
    }

    fun allocateMultisample(samples: Int, width: Int, height: Int, internalFormat: Int): RenderbufferObject {
        sizeX = width
        sizeY = height
        glNamedRenderbufferStorageMultisample(id, samples, internalFormat, width, height)
        return this
    }

    override fun destroy() {
        glDeleteRenderbuffers(id)
    }
}