package dev.luna5ama.glwrapper.base

import java.util.*

sealed class WrapperManager {
    private val provider by lazy { ServiceLoader.load(GLWrapperInitializer::class.java).maxBy { it.priority } }

    private var instanceFastPath: GLWrapper? = null
    private var instanceFastPathThread: Thread? = null

    private val instance0 = ThreadLocal.withInitial {
        val delegate = provider.createWrapperInstance()

        GLWrapper(
            delegate,
            GL45 = when (delegate.properties.vendor) {
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

    val pathResolver = provider.createPathResolver()
}