package dev.luna5ama.glwrapper.impl

import dev.luna5ama.glwrapper.api.*
import dev.luna5ama.kmogus.Ptr

sealed class BufferObject : IGLObject, IGLTargetBinding {
    final override var id = glCreateBuffers(); private set

    var size = -1L; private set

    override fun create() {
        super.create()
        id = glCreateBuffers()
    }

    open fun allocate(size: Long, flags: Int): BufferObject {
        // Intel workaround
        if (GpuVendor.get() == GpuVendor.INTEL && size != -1L) {
            destroy()
        }
        tryCreate()
        this.size = size
        return this
    }

    open fun allocate(size: Long, data: Ptr, flags: Int): BufferObject {
        // Intel workaround
        if (GpuVendor.get() == GpuVendor.INTEL && size != -1L) {
            destroy()
        }
        tryCreate()
        this.size = size
        return this
    }

    override fun destroy() {
        if (id != 0) {
            glDeleteBuffers(id)
            size = -1L
            id = 0
        }
    }

    override fun bind(target: Int) {
        glBindBuffer(target, id)
    }

    override fun unbind(target: Int) {
        glBindBuffer(target, 0)
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is BufferObject) return false

        return id == other.id
    }

    override fun hashCode(): Int {
        return id
    }

    class Immutable : BufferObject() {
        override fun allocate(size: Long, flags: Int): Immutable {
            super.allocate(size, flags)
            glNamedBufferStorage(id, size, Ptr.NULL, flags)
            return this
        }

        override fun allocate(size: Long, data: Ptr, flags: Int): Immutable {
            super.allocate(size, data, flags)
            glNamedBufferStorage(id, size, data, flags)
            return this
        }
    }

    class Mutable : BufferObject() {
        override fun allocate(size: Long, flags: Int): Mutable {
            super.allocate(size, flags)
            glNamedBufferData(id, size, Ptr.NULL, flags)
            return this
        }

        override fun allocate(size: Long, data: Ptr, flags: Int): Mutable {
            super.allocate(size, data, flags)
            glNamedBufferData(id, size, data, flags)
            return this
        }
    }
}