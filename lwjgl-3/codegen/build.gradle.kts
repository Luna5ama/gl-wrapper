import org.jetbrains.kotlin.gradle.dsl.JvmTarget

dependencies {
    implementation("dev.luna5ama:ktgen-api:1.0.0")
    implementation(project(":base"))
    implementation("org.lwjgl:lwjgl-opengl:3.3.3")
    implementation("org.ow2.asm:asm-tree:9.6")
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(17))
    }
}

kotlin {
    compilerOptions {
        jvmTarget.set(JvmTarget.JVM_17)
    }
}