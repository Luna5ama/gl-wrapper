package dev.luna5ama.glwrapper.lwjgl2

import dev.luna5ama.glwrapper.GLWrapper

class GLWrapperLWJGL2 : GLWrapper() {
    override val gl11 = GL11LWJGL2(tempArr)
    override val gl12 = GL12LWJGL2(tempArr)
    override val gl13 = GL13LWJGL2(tempArr)
    override val gl14 = GL14LWJGL2(tempArr)
    override val gl15 = GL15LWJGL2(tempArr)

    override val gl20 = GL20LWJGL2(tempArr)
    override val gl21 = GL21LWJGL2(tempArr)

    override val gl30 = GL30LWJGL2(tempArr)
    override val gl31 = GL31LWJGL2(tempArr)
    override val gl32 = GL32LWJGL2(tempArr)
    override val gl33 = GL33LWJGL2(tempArr)

    override val gl40 = GL40LWJGL2(tempArr)
    override val gl41 = GL41LWJGL2(tempArr)
    override val gl42 = GL42LWJGL2(tempArr)
    override val gl43 = GL43LWJGL2(tempArr)
    override val gl44 = GL44LWJGL2(tempArr)
    override val gl45 = GL45LWJGL2(tempArr)
    override val gl46 = GL46LWJGL2(tempArr)
}