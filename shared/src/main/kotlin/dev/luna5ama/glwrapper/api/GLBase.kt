package dev.luna5ama.glwrapper.api

import dev.luna5ama.kmogus.Arr

interface GLBase {
    val tempArr: Arr

    abstract class Impl : GLBase {
        override val tempArr = GLWrapper.tempArr
    }
}