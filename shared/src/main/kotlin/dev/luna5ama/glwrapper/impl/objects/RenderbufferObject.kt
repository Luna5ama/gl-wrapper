package dev.luna5ama.glwrapper.impl.objects

import dev.luna5ama.glwrapper.api.glNamedRenderbufferStorage
import dev.luna5ama.glwrapper.api.glNamedRenderbufferStorageMultisample
import dev.luna5ama.glwrapper.impl.GLObjectType

class RenderbufferObject :
    IGLObject by IGLObject.Impl(GLObjectType.Renderbuffer),
    FramebufferObject.Attachment,
    IGLSized2D {
    override var sizeX = 0; private set
    override var sizeY = 0; private set

    fun allocate(width: Int, height: Int, internalFormat: Int): RenderbufferObject {
        tryCreate()
        sizeX = width
        sizeY = height
        glNamedRenderbufferStorage(id, internalFormat, width, height)
        return this
    }

    fun allocateMultisample(samples: Int, width: Int, height: Int, internalFormat: Int): RenderbufferObject {
        tryCreate()
        sizeX = width
        sizeY = height
        glNamedRenderbufferStorageMultisample(id, samples, internalFormat, width, height)
        return this
    }
}