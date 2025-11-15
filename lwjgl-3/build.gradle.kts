plugins {
    id("dev.luna5ama.ktgen").version("1.0.0")
}

dependencies {
    api(project(":base"))
    compileOnly("org.lwjgl:lwjgl-opengl:3.3.3") {
        isTransitive = false
    }

    ktgen(project(":lwjgl-3:codegen"))
}

val launcher = javaToolchains.launcherFor {
    languageVersion.set(JavaLanguageVersion.of(17))
}

tasks {
    ktgen {
        javaLauncher.set(launcher)
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