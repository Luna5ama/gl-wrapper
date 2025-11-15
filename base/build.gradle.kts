plugins {
    id("dev.luna5ama.ktgen").version("1.0.0")
}

base {
    archivesName.set("${rootProject.name.lowercase()}-base")
}

dependencies {
    val kmogusVersion: String by project
    api("dev.luna5ama:kmogus-core:$kmogusVersion")

    ktgen(project("codegen"))
    ktgenInput("org.lwjgl:lwjgl:3.3.3:sources")
    ktgenInput("org.lwjgl:lwjgl-opengl:3.3.3:sources")
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