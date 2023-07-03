import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

allprojects {
    group = "dev.luna5ama"
    version = "1.0.0-SNAPSHOT"
}

plugins {
    java
    kotlin("jvm")
    id("dev.fastmc.maven-repo").version("1.0.0")
    id("com.google.devtools.ksp")
}

allprojects {
    group = "dev.luna5ama"
    version = "1.0.0-SNAPSHOT"

    apply {
        plugin("kotlin")
        plugin("dev.fastmc.maven-repo")
    }

    repositories {
        mavenCentral()
        maven("https://maven.luna5ama.dev")
        maven("https://maven.fastmc.dev/")
        maven("https://libraries.minecraft.net/")
    }

    java {
        toolchain {
            languageVersion.set(JavaLanguageVersion.of(8))
        }
        withSourcesJar()
    }

    kotlin {
        val jvmArgs = mutableSetOf<String>()
        (rootProject.findProperty("kotlin.daemon.jvm.options") as? String)
            ?.split("\\s+".toRegex())?.toCollection(jvmArgs)
        System.getProperty("gradle.kotlin.daemon.jvm.options")
            ?.split("\\s+".toRegex())?.toCollection(jvmArgs)

        kotlinDaemonJvmArgs = jvmArgs.toList()
    }

    tasks {
        withType<JavaCompile> {
            options.encoding = "UTF-8"
        }

        withType<KotlinCompile> {
            kotlinOptions {
                jvmTarget = "1.8"
                freeCompilerArgs = listOf(
                    "-opt-in=kotlin.RequiresOptIn",
                    "-opt-in=kotlin.contracts.ExperimentalContracts",
                    "-Xlambdas=indy",
                    "-Xjvm-default=all",
                    "-Xbackend-threads=0"
                )
            }
        }
    }
}

subprojects {
    base {
        archivesName.set("${rootProject.name.lowercase()}-${project.name}")
    }
}

base {
    archivesName.set("${rootProject.name.lowercase()}-core")
}

dependencies {
    api("dev.luna5ama:kmogus-core:1.0.0-SNAPSHOT")
    ksp(project(":codegen"))
    compileOnly("it.unimi.dsi:fastutil:7.1.0")
}

publishing {
    publications {
        create<MavenPublication>(project.name) {
            artifactId = base.archivesName.get()
            from(components["java"])
        }
    }
}