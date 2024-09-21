@file:Suppress("DEPRECATION")

package dev.luna5ama.glwrapper.base

import com.mojang.blaze3d.platform.GlStateManager
import sun.misc.Unsafe
import java.lang.invoke.MethodHandle
import java.lang.invoke.MethodHandles
import java.lang.reflect.Field

internal val unsafe = run {
    val theUnsafe = Unsafe::class.java.getDeclaredField("theUnsafe")
    theUnsafe.isAccessible = true
    theUnsafe[null] as Unsafe
}

internal val trustedLookUp = run {
    val trustedLookupField = MethodHandles.Lookup::class.java.getDeclaredField("IMPL_LOOKUP")
    unsafe.getObject(
        unsafe.staticFieldBase(trustedLookupField),
        unsafe.staticFieldOffset(trustedLookupField)
    ) as MethodHandles.Lookup
}

internal fun tryGetField(clazz: Class<*>, vararg names: String): Field {
    return names.firstNotNullOf { runCatching { clazz.getDeclaredField(it) }.getOrNull() }
}

internal fun getSetter(clazz: Class<*>, vararg names: String): MethodHandle {
    val field = tryGetField(clazz, *names)
    return trustedLookUp.findSetter(clazz, field.name, field.type)
}

internal fun getStaticSetter(clazz: Class<*>, vararg names: String): MethodHandle {
    val field = tryGetField(clazz, *names)
    return trustedLookUp.findStaticSetter(clazz, field.name, field.type)
}

@Suppress("UNCHECKED_CAST")
private val textureStates = run {
    val field = tryGetField(
        GlStateManager::class.java,
        "f_84077_",
        "TEXTURES"
    )

    unsafe.getObject(
        unsafe.staticFieldBase(field),
        unsafe.staticFieldOffset(field)
    ) as Array<Any>
}

private val setTextureName = run {
    val textureStateClass = textureStates.javaClass.componentType

    getSetter(
        textureStateClass,
        "field_5167",
        "f_84801_",
        "boundTexture"
    )
}

internal fun setTextureUnit(unit: Int, texture: Int) {
    if (unit < textureStates.size) {
        setTextureName(textureStates[unit], texture)
    }
}