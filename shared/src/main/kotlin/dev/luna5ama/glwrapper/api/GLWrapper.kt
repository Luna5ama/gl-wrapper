package dev.luna5ama.glwrapper.api

import dev.luna5ama.kmogus.Arr

interface GLWrapper : GLBase {
    val vendor: GpuVendor

    val gl11: IGL11
    val gl12: IGL12
    val gl13: IGL13
    val gl14: IGL14
    val gl15: IGL15

    val gl20: IGL20
    val gl21: IGL21

    val gl30: IGL30
    val gl31: IGL31
    val gl32: IGL32
    val gl33: IGL33

    val gl40: IGL40
    val gl41: IGL41
    val gl42: IGL42
    val gl43: IGL43
    val gl44: IGL44
    val gl45: IGL45
    val gl46: IGL46

    companion object {
        private lateinit var backupClass: Class<GLWrapper>
        private lateinit var instanceFastPath: GLWrapper
        private lateinit var instanceFastPathThread: Thread

        private val instance0 = ThreadLocal.withInitial {
            PatchedGLWrapper(backupClass.getConstructor().newInstance())
        }

        val instance: GLWrapper
            get() = if (Thread.currentThread() === instanceFastPathThread) {
                instanceFastPath
            } else {
                instance0.get()!!
            }

        fun init(glWrapper: GLWrapper) {
            if (!::instanceFastPath.isInitialized) {
                backupClass = glWrapper.javaClass
                instanceFastPath = PatchedGLWrapper(glWrapper)
                instanceFastPathThread = Thread.currentThread()
            }
            instance0.set(PatchedGLWrapper(glWrapper))
        }
    }
}

abstract class AbstractGLWrapper : GLWrapper {
    override val vendor: GpuVendor by lazy {
        val vendorStr = gl11.glGetString(GL_VENDOR) ?: ""
        GpuVendor.values().find {
            vendorStr.contains(it.name, true)
        } ?: GpuVendor.UNKNOWN
    }

    final override val tempArr = Arr.malloc(128L)
}