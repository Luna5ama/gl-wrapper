package dev.luna5ama.glwrapper.enums

enum class GLDataType(val glEnum: Int, val size: Int) {
    GL_BYTE(dev.luna5ama.glwrapper.api.GL_BYTE, 1),
    GL_UNSIGNED_BYTE(dev.luna5ama.glwrapper.api.GL_UNSIGNED_BYTE, 1),
    GL_SHORT(dev.luna5ama.glwrapper.api.GL_SHORT, 2),
    GL_UNSIGNED_SHORT(dev.luna5ama.glwrapper.api.GL_UNSIGNED_SHORT, 2),
    GL_INT(dev.luna5ama.glwrapper.api.GL_INT, 4),
    GL_UNSIGNED_INT(dev.luna5ama.glwrapper.api.GL_UNSIGNED_INT, 4),
    GL_FLOAT(dev.luna5ama.glwrapper.api.GL_FLOAT, 4),
}