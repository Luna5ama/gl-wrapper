package dev.luna5ama.glwrapper.api

@Retention(AnnotationRetention.SOURCE)
annotation class Unsafe

enum class GpuVendor {
    NVIDIA,
    AMD,
    INTEL,
    UNKNOWN;

    companion object {
        val isIntel get() = get() == INTEL
        val isNvidia get() = get() == NVIDIA
        val isAmd get() = get() == AMD

        fun get(): GpuVendor {
            return GLWrapper.instance.vendor
        }
    }
}