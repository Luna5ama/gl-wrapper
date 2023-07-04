@file:Suppress("DEPRECATION")

package dev.luna5ama.glwrapper.api

import dev.luna5ama.kmogus.Ptr
import dev.luna5ama.kmogus.asByteBuffer
import org.lwjgl.PointerBuffer
import org.lwjgl.opengl.GLContext
import sun.misc.Unsafe
import java.lang.invoke.MethodHandles
import java.lang.invoke.MethodType
import java.nio.Buffer

internal val unsafe = run {
    val theUnsafe = Unsafe::class.java.getDeclaredField("theUnsafe")
    theUnsafe.isAccessible = true
    theUnsafe[null] as Unsafe
}

private val ADDRESS_OFFSET = unsafe.objectFieldOffset(Buffer::class.java.getDeclaredField("address"))

internal val Buffer.address
    get() = unsafe.getLong(this, ADDRESS_OFFSET)

internal val trustedLookUp = run {
    val trustedLookupField = MethodHandles.Lookup::class.java.getDeclaredField("IMPL_LOOKUP")
    unsafe.getObject(
        unsafe.staticFieldBase(trustedLookupField),
        unsafe.staticFieldOffset(trustedLookupField)
    ) as MethodHandles.Lookup
}

internal fun Ptr.asPointerBuffer(size: Int, buffer: PointerBuffer): PointerBuffer {
    asByteBuffer(size * 8, buffer.buffer)
    return buffer
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