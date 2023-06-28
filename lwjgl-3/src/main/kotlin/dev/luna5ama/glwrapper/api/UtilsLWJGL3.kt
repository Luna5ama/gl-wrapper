@file:Suppress("DEPRECATION")

package dev.luna5ama.glwrapper.api

import sun.misc.Unsafe
import java.nio.Buffer

internal val unsafe = run {
    val theUnsafe = Unsafe::class.java.getDeclaredField("theUnsafe")
    theUnsafe.isAccessible = true
    theUnsafe[null] as Unsafe
}

private val ADDRESS_OFFSET = unsafe.objectFieldOffset(Buffer::class.java.getDeclaredField("address"))

internal var Buffer.address
    get() = unsafe.getLong(this, ADDRESS_OFFSET)
    set(value) {
        unsafe.putLong(this, ADDRESS_OFFSET, value)
    }