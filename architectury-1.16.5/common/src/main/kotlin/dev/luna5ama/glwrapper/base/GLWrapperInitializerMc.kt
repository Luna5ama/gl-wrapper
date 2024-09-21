package dev.luna5ama.glwrapper.base

class GLWrapperInitializerMc : GLWrapperInitializer {
    override val priority: Int = 10

    private val lwjgl3 = GLWrapperInitializerLWJGL3()

    override fun createWrapperInstance(): GLWrapper {
        val delegate = lwjgl3.createWrapperInstance()
        return GLWrapper(
            delegate,
            GL11 = GL11Mc(delegate.GL11),
            GL13 = GL13Mc(delegate.GL13),
            GL14 = GL14Mc(delegate.GL14),
            GL44 = GL44Mc(delegate.GL44),
            GL45 = GL45Mc(delegate.GL45)
        )
    }
}