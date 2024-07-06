package dev.luna5ama.glwrapper

import dev.luna5ama.glwrapper.objects.BufferObject

interface BufferView {
    val viewBuffer : BufferObject
    val viewOffset : Long
    val viewSize : Long

    fun createView(offset: Long, size: Long) : BufferView
    fun createView(offset: Long) : BufferView
}