package dev.luna5ama.glwrapper.api

class GLWrapperProviderMc : GLWrapperProvider {
    override val priority: Int = 10

    private val lwjgl3 = GLWrapperProviderLWJGL3()

    override fun create(): GLWrapper {
        val delegate = lwjgl3.create()
        return GLWrapper(
            delegate,
            GL11 = GL11Mc(delegate.GL11),
            GL14 = GL14Mc(delegate.GL14),
            GL15 = GL15Mc(delegate.GL15),
            GL30 = GL30Mc(delegate.GL30),
            GL44 = GL44Mc(delegate.GL44),
            GL45 = GL45Mc(delegate.GL45)
        )
    }
}