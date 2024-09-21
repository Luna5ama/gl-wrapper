package dev.luna5ama.glwrapper.base

class GLWrapperLWJGL2 : AbstractGLWrapper() {
    override val GL11 = GL11LWJGL2(tempArr)
    override val GL12 = GL12LWJGL2(tempArr)
    override val GL13 = GL13LWJGL2(tempArr)
    override val GL14 = GL14LWJGL2(tempArr)
    override val GL15 = GL15LWJGL2(tempArr)

    override val GL20 = GL20LWJGL2(tempArr)
    override val GL21 = GL21LWJGL2(tempArr)

    override val GL30 = GL30LWJGL2(tempArr)
    override val GL31 = GL31LWJGL2(tempArr)
    override val GL32 = GL32LWJGL2(tempArr)
    override val GL33 = GL33LWJGL2(tempArr)

    override val GL40 = GL40LWJGL2(tempArr)
    override val GL41 = GL41LWJGL2(tempArr)
    override val GL42 = GL42LWJGL2(tempArr)
    override val GL43 = GL43LWJGL2(tempArr)
    override val GL44 = GL44LWJGL2(tempArr)
    override val GL45 = GL45LWJGL2(tempArr)
    override val GL46 = GL46LWJGL2(tempArr)
}