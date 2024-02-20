package dev.luna5ama.glwrapper.impl

import dev.luna5ama.glwrapper.api.*
import dev.luna5ama.kmogus.Arr
import dev.luna5ama.kmogus.Ptr

sealed class BufferObject : IGLObject by IGLObject.Impl(GLObjectType.BUFFER), IGLTargetBinding {
    var size = -1L; private set

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

    fun invalidate() {
        checkAllocated()
        glInvalidateBufferData(id)
    }

    fun invalidate(offset: Long, length: Long) {
        checkAllocated()
        glInvalidateBufferSubData(id, offset, length)
    }

    fun upload(data: Ptr) {
        checkAllocated()
        glNamedBufferSubData(id, 0L, size, data)
    }

    fun upload(offset: Long, length: Long, data: Ptr) {
        checkAllocated()
        glNamedBufferSubData(id, offset, length, data)
    }

    fun clear(internalformat: Int, format: Int, type: Int, data: Ptr) {
        checkAllocated()
        glClearNamedBufferData(id, internalformat, format, type, data)
    }

    fun clear(internalformat: Int, offset: Long, length: Long, format: Int, type: Int, data: Ptr) {
        checkAllocated()
        glClearNamedBufferSubData(id, internalformat, offset, length, format, type, data)
    }

    fun map(access: Int): Arr {
        checkAllocated()
        return glMapNamedBufferRange(id, 0L, size, access)
    }

    fun map(offset: Long, length: Long, access: Int): Arr {
        checkAllocated()
        return glMapNamedBufferRange(id, offset, length, access)
    }

    fun flush() {
        checkAllocated()
        glFlushMappedNamedBufferRange(id, 0L, size)
    }

    fun flush(offset: Long, length: Long) {
        checkAllocated()
        glFlushMappedNamedBufferRange(id, offset, length)
    }

    fun unmap(): Boolean {
        checkAllocated()
        return glUnmapNamedBuffer(id)
    }

    fun copyTo(dst: BufferObject, srcOffset: Long, dstOffset: Long, len: Long) {
        checkAllocated()
        glCopyNamedBufferSubData(id, dst.id, srcOffset, dstOffset, len)
    }

    fun copyTo(dst: BufferObject) {
        require(size == dst.size) { "Buffer sizes must be equal" }
        checkAllocated()
        glCopyNamedBufferSubData(id, dst.id, 0, 0, size)
    }

    fun checkAllocated() {
        checkCreated()
        check(size != -1L) { "Buffer is not allocated" }
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
            require(flags != 0) { "Mutable buffer usage must not be 0" }
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