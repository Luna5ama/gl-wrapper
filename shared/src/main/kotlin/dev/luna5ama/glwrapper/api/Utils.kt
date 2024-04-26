package dev.luna5ama.glwrapper.api

@Retention(AnnotationRetention.RUNTIME)
annotation class Unsafe

@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.FUNCTION)
annotation class PtrReturn

@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.FUNCTION)
annotation class PtrParameter(val ptrParamIdx: IntArray)

@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.FUNCTION)
annotation class NullableReturn

@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.FUNCTION)
annotation class CoreOverload

enum class GpuVendor {
    NVIDIA,
    AMD,
    INTEL,
    UNKNOWN;

    companion object {
        val isIntel get() = get() == INTEL
        val isNvidia get() = get() == NVIDIA
        val isAmd get() = get() == AMD

        fun get(glWrapper: GLWrapper = GLWrapper.instance): GpuVendor {
            val vendorStr = glWrapper.GL11.glGetString(GL_VENDOR) ?: ""
            return GpuVendor.entries.find {
                vendorStr.contains(it.name, true)
            } ?: UNKNOWN
        }
    }
}