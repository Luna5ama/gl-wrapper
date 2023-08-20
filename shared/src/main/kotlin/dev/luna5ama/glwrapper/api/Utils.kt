package dev.luna5ama.glwrapper.api

@Retention(AnnotationRetention.SOURCE)
annotation class Unsafe

enum class GpuVendor {
    NVIDIA,
    AMD,
    INTEL,
    UNKNOWN
}