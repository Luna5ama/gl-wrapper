allprojects {
    group = "dev.luna5ama"
    version = "1.1.0"
}

plugins {
    id("dev.fastmc.mod-setup").version("1.3.2")
    id("dev.fastmc.maven-repo").version("1.0.0")
}

buildscript {
    val kotlinVersion: String by rootProject
    configurations.all {
        resolutionStrategy {
            eachDependency {
                if (requested.group == "org.jetbrains.kotlin") {
                    useVersion(kotlinVersion)
                }
            }
        }
    }
}

subprojects {

    apply {
        plugin("dev.fastmc.maven-repo")
    }

    repositories {
        maven("https://maven.luna5ama.dev")
        mavenCentral()
        maven("https://libraries.minecraft.net/")
    }

    kotlin {
            compilerOptions {
                freeCompilerArgs.map {
                    it + listOf(
                    "-opt-in=kotlin.RequiresOptIn",
                    "-opt-in=kotlin.contracts.ExperimentalContracts",
                    "-Xbackend-threads=0"
                    )
                }
                javaParameters = true
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
