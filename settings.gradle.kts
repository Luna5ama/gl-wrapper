rootProject.name = "gl-wrapper"

pluginManagement {
    repositories {
        gradlePluginPortal()
        maven("https://maven.luna5ama.dev")
        maven("https://maven.fastmc.dev/")
        maven("https://maven.fabricmc.net/")
        maven("https://maven.architectury.dev/")
        maven("https://files.minecraftforge.net/maven/")
        maven("https://repo.spongepowered.org/repository/maven-public/")
    }

    val kotlinVersion: String by settings
    val kspVersion: String by settings

    plugins {
        id("org.jetbrains.kotlin.jvm") version kotlinVersion
        id("com.google.devtools.ksp") version kspVersion
    }
}

include("shared", "shared:codegen")
include("lwjgl-2", "lwjgl-3")
//include("forge-1.12.2")
//include("architectury-1.16.5", "architectury-1.16.5:common")
//include("architectury-1.18.2", "architectury-1.18.2:common")
//include("architectury-1.19.4", "architectury-1.19.4:common")
