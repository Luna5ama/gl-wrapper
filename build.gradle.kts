import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

allprojects {
    group = "dev.luna5ama"
    version = "1.0.0"
}

plugins {
    id("dev.fastmc.mod-setup").version("1.3.1")
    id("dev.fastmc.maven-repo").version("1.0.0")
}

subprojects {
    apply {
        plugin("dev.fastmc.maven-repo")
    }

    repositories {
        mavenCentral()
        maven("https://maven.luna5ama.dev")
        maven("https://maven.fastmc.dev/")
        maven("https://libraries.minecraft.net/")
    }

    tasks {
        withType<KotlinCompile> {
            kotlinOptions {
                freeCompilerArgs = listOf(
                    "-opt-in=kotlin.RequiresOptIn",
                    "-opt-in=kotlin.contracts.ExperimentalContracts",
                    "-Xbackend-threads=0"
                )
            }
        }
    }

    base {
        archivesName.set("${rootProject.name.lowercase()}-${project.name}")
    }

    java {
        withSourcesJar()
    }
}

allprojects {
    tasks {
        listOf(
            "remapRuntime",
            "generateConstants",
            "compileConstants",
            "fastRemapJar",
            "optimizeFatJar",
            "modPackaging",
            "modLoaderJar",
            "fatJar",
            "remapJar",
            "devModJar"
        ).forEach {
            findByName(it)?.enabled = false
        }

        afterEvaluate {
            jar {
                archiveClassifier.set("")
                archiveClassifier.disallowChanges()
            }
        }
    }
}
