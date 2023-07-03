import net.minecraftforge.gradle.userdev.UserDevExtension

buildscript {
    repositories {
        mavenCentral()
        maven("https://files.minecraftforge.net/maven")
    }

    dependencies {
        classpath("net.minecraftforge.gradle:ForgeGradle:6.+")
    }
}

apply {
    plugin("net.minecraftforge.gradle")
}

repositories {
    maven("https://repo.spongepowered.org/repository/maven-public/")
}

val minecraftVersion: String by project
val forgeVersion: String by project
val mappingsChannel: String by project
val mappingsVersion: String by project

dependencies {
    // Forge
    "minecraft"("net.minecraftforge:forge:$minecraftVersion-$forgeVersion")

    api(project(":lwjgl-2"))

    implementation("org.spongepowered:mixin:0.7.11-SNAPSHOT") {
        exclude("commons-io")
        exclude("gson")
        exclude("guava")
        exclude("launchwrapper")
        exclude(group = "org.apache.logging.log4j")
    }
}


configure<UserDevExtension> {
    mappings(mappingsChannel, mappingsVersion)
}

tasks {
    listOf(
        remapRuntime,
        generateConstants,
        compileConstants,
        fastRemapJar,
        optimizeFatJar,
        modPackaging,
        modLoaderJar,
        fatJar
    ).forEach {
        it.configure {
            isEnabled = false
        }
    }

    jar {
        archiveClassifier.set("")
    }
}

publishing {
    publications {
        create<MavenPublication>(project.name) {
            artifactId = base.archivesName.get()
            from(components["java"])
        }
    }
}