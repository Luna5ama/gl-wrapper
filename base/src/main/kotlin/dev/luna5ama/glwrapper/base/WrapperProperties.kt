package dev.luna5ama.glwrapper.base

import dev.luna5ama.kmogus.Arr

class WrapperProperties(private val glWrapper: GLWrapper) {
    val tempArr = Arr.malloc(128)
    val vendor: GpuVendor
        get() = GpuVendor.get(glWrapper)
}