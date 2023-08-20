package dev.luna5ama.glwrapper.api

class GLWrapperImpl : AbstractGLWrapper() {
    override val gl11 = GL11Impl(tempArr)
    override val gl12 = GL12Impl(tempArr)
    override val gl13 = GL13Impl(tempArr)
    override val gl14 = GL14Impl(tempArr)
    override val gl15 = GL15Impl(tempArr)

    override val gl20 = GL20Impl(tempArr)
    override val gl21 = GL21Impl(tempArr)

    override val gl30 = GL30Impl(tempArr)
    override val gl31 = GL31Impl(tempArr)
    override val gl32 = GL32Impl(tempArr)
    override val gl33 = GL33Impl(tempArr)

    override val gl40 = GL40Impl(tempArr)
    override val gl41 = GL41Impl(tempArr)
    override val gl42 = GL42Impl(tempArr)
    override val gl43 = GL43Impl(tempArr)
    override val gl44 = GL44Impl(tempArr)
    override val gl45 = GL45Impl(tempArr)
    override val gl46 = GL46Impl(tempArr)
}