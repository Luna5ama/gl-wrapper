package dev.luna5ama.glwrapper.api

object Config {
    val checks = System.getProperty("glwrapper.checks").toBoolean()
}