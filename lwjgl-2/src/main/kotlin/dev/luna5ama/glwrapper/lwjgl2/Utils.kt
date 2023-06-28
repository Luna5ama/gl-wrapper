@file:Suppress("DEPRECATION")

package dev.luna5ama.glwrapper.lwjgl2

import org.lwjgl.PointerBuffer
import org.lwjgl.opengl.GLContext
import sun.misc.Unsafe
import java.lang.invoke.MethodHandles
import java.lang.invoke.MethodType
import java.nio.*

internal val unsafe = run {
    val theUnsafe = Unsafe::class.java.getDeclaredField("theUnsafe")
    theUnsafe.isAccessible = true
    theUnsafe[null] as Unsafe
}

private val ADDRESS_OFFSET = unsafe.objectFieldOffset(Buffer::class.java.getDeclaredField("address"))
private val POSITION_OFFSET = unsafe.objectFieldOffset(Buffer::class.java.getDeclaredField("position"))
private val MARK_OFFSET = unsafe.objectFieldOffset(Buffer::class.java.getDeclaredField("mark"))
private val LIMIT_OFFSET = unsafe.objectFieldOffset(Buffer::class.java.getDeclaredField("limit"))
private val CAPACITY_OFFSET = unsafe.objectFieldOffset(Buffer::class.java.getDeclaredField("capacity"))

internal var Buffer.address
    get() = unsafe.getLong(this, ADDRESS_OFFSET)
    set(value) {
        unsafe.putLong(this, ADDRESS_OFFSET, value)
    }

private var Buffer.position
    get() = position()
    set(value) {
        unsafe.putInt(this, POSITION_OFFSET, value)
    }

private var Buffer.mark
    get() = unsafe.getInt(this, MARK_OFFSET)
    set(value) {
        unsafe.putInt(this, MARK_OFFSET, value)
    }

private var Buffer.limit
    get() = limit()
    set(value) {
        unsafe.putInt(this, LIMIT_OFFSET, value)
    }

private var Buffer.capacity
    get() = capacity()
    set(value) {
        unsafe.putInt(this, CAPACITY_OFFSET, value)
    }

private val DIRECT_BYTE_BUFFER_CLASS = ByteBuffer.allocateDirect(0).order(ByteOrder.nativeOrder()).javaClass

internal fun createBuffer(): ByteBuffer {
    val buffer = (unsafe.allocateInstance(DIRECT_BYTE_BUFFER_CLASS) as ByteBuffer).order(ByteOrder.nativeOrder())
    buffer.address = 0
    buffer.mark = -1
    buffer.limit = 0
    buffer.capacity = 0

    return buffer
}

internal fun wrapBuffer(buffer: ByteBuffer, address: Long, length: Int): ByteBuffer {
    buffer.address = address
    buffer.limit = length
    buffer.capacity = length

    return buffer
}

internal fun wrapBuffer(buffer: ByteBuffer, address: Long): ByteBuffer {
    buffer.address = address

    return buffer
}

internal fun wrapBuffer(buffer: ShortBuffer, address: Long, length: Int): ShortBuffer {
    buffer.address = address
    buffer.limit = length
    buffer.capacity = length

    return buffer
}

internal fun wrapBuffer(buffer: ShortBuffer, address: Long): ShortBuffer {
    buffer.address = address

    return buffer
}

internal fun wrapBuffer(buffer: IntBuffer, address: Long, length: Int): IntBuffer {
    buffer.address = address
    buffer.limit = length
    buffer.capacity = length

    return buffer
}

internal fun wrapBuffer(buffer: IntBuffer, address: Long): IntBuffer {
    buffer.address = address

    return buffer
}

internal fun wrapBuffer(buffer: LongBuffer, address: Long, length: Int): LongBuffer {
    buffer.address = address
    buffer.limit = length
    buffer.capacity = length

    return buffer
}

internal fun wrapBuffer(buffer: LongBuffer, address: Long): LongBuffer {
    buffer.address = address

    return buffer
}

internal fun wrapBuffer(buffer: FloatBuffer, address: Long, length: Int): FloatBuffer {
    buffer.address = address
    buffer.limit = length
    buffer.capacity = length

    return buffer
}

internal fun wrapBuffer(buffer: FloatBuffer, address: Long): FloatBuffer {
    buffer.address = address

    return buffer
}

internal fun wrapBuffer(buffer: DoubleBuffer, address: Long, length: Int): DoubleBuffer {
    buffer.address = address
    buffer.limit = length
    buffer.capacity = length

    return buffer
}

internal fun wrapBuffer(buffer: DoubleBuffer, address: Long): DoubleBuffer {
    buffer.address = address

    return buffer
}

internal fun wrapBuffer(buffer: PointerBuffer, address: Long, length: Int): PointerBuffer {
    val byteBuffer = buffer.buffer
    byteBuffer.address = address
    byteBuffer.limit = length * 8
    byteBuffer.capacity = length * 8

    return buffer
}

internal val trustedLookUp = run {
    val trustedLookupField = MethodHandles.Lookup::class.java.getDeclaredField("IMPL_LOOKUP")
    unsafe.getObject(
        unsafe.staticFieldBase(trustedLookupField),
        unsafe.staticFieldOffset(trustedLookupField)
    ) as MethodHandles.Lookup
}

private val getFunctionAddress = trustedLookUp.findStatic(
    GLContext::class.java,
    "getFunctionAddress",
    MethodType.methodType(
        Long::class.java,
        String::class.java
    )
)

internal fun getFunctionAddress(name: String): Long {
    return getFunctionAddress.invokeExact(name) as Long
}