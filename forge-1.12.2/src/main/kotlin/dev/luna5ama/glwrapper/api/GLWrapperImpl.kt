package dev.luna5ama.glwrapper.api

class GLWrapperImpl : AbstractGLWrapper() {
    override val GL11 = GL11Impl(tempArr)
    override val GL12 = GL12Impl(tempArr)
    override val GL13 = GL13Impl(tempArr)
    override val GL14 = GL14Impl(tempArr)
    override val GL15 = GL15Impl(tempArr)

    override val GL20 = GL20Impl(tempArr)
    override val GL21 = GL21Impl(tempArr)

    override val GL30 = GL30Impl(tempArr)
    override val GL31 = GL31Impl(tempArr)
    override val GL32 = GL32Impl(tempArr)
    override val GL33 = GL33Impl(tempArr)

    override val GL40 = GL40Impl(tempArr)
    override val GL41 = GL41Impl(tempArr)
    override val GL42 = GL42Impl(tempArr)
    override val GL43 = GL43Impl(tempArr)
    override val GL44 = GL44Impl(tempArr)
    override val GL45 = GL45Impl(tempArr)
    override val GL46 = GL46Impl(tempArr)
}