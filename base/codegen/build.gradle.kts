import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

dependencies {
    implementation("dev.luna5ama:ktgen-api:1.0.0")
    implementation("org.lwjgl:lwjgl-opengl:3.3.3")
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(17))
    }
}

tasks {
    withType<KotlinCompile> {
        kotlinOptions {
            jvmTarget = "17"
        }
    }
}