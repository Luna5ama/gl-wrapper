@file:Suppress("DEPRECATION")

package dev.luna5ama.glwrapper.api

import net.minecraft.client.renderer.GlStateManager
import sun.misc.Unsafe
import java.lang.invoke.MethodHandles

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


@Suppress("UNCHECKED_CAST")
private val textureStates = run {
    val field = try {
        GlStateManager::class.java.getDeclaredField("field_179174_p")
    } catch (e: NoSuchFieldException) {
        GlStateManager::class.java.getDeclaredField("textureState")
    }

    unsafe.getObject(
        unsafe.staticFieldBase(field),
        unsafe.staticFieldOffset(field)
    ) as Array<Any>
}

private val setTextureName = run {
    val textureStateClass = textureStates.javaClass.componentType
    val textureNameField = try {
        textureStateClass.getDeclaredField("field_179059_b")
    } catch (e: NoSuchFieldException) {
        textureStateClass.getDeclaredField("textureName")
    }

    trustedLookUp.findSetter(
        textureStateClass,
        textureNameField.name,
        textureNameField.type
    )
}

internal fun setTextureUnit(unit: Int, texture: Int) {
    if (unit < textureStates.size) {
        setTextureName(textureStates[unit], texture)
    }
}