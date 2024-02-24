package dev.luna5ama.glwrapper.api

import dev.luna5ama.kmogus.Arr
import java.util.*

abstract class GLWrapperControl {
    private val provider by lazy { ServiceLoader.load(GLWrapperProvider::class.java).maxBy { it.priority } }

    private var instanceFastPath: GLWrapper? = null
    private var instanceFastPathThread: Thread? = null

    private var tempArrFastPath: Arr? = null
    private var tempArrFastPathThread: Thread? = null

    private val tempArr0 = ThreadLocal.withInitial {
        Arr.malloc(128)
    }

    val tempArr: Arr
        get() = if (Thread.currentThread() === tempArrFastPathThread) {
            tempArrFastPath ?: tempArr0.get()!!.also {
                tempArrFastPath = it
            }
        } else {
            tempArr0.get()!!
        }

    private val instance0 = ThreadLocal.withInitial {
        val delegate = provider.create()

        GLWrapper(
            delegate,
            GL45 = when (delegate.vendor) {
                GpuVendor.INTEL -> IntelGL45(delegate.GL45)
                else -> delegate.GL45
            }
        )
    }

    val instance: GLWrapper
        get() = if (Thread.currentThread() === instanceFastPathThread) {
            instanceFastPath ?: instance0.get()!!.also {
                instanceFastPath = it
            }
        } else {
            instance0.get()!!
        }
}