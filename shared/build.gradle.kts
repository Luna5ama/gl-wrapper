plugins {
    id("dev.luna5ama.ktgen").version("1.0.0")
}

base {
    archivesName.set("${rootProject.name.lowercase()}-core")
}

dependencies {
    val kmogusVersion: String by project
    api("dev.luna5ama:kmogus-core:$kmogusVersion")
    compileOnly("it.unimi.dsi:fastutil:7.1.0")

    ktgen(project(":shared:codegen"))
    ktgenInput("org.lwjgl:lwjgl:3.3.3:sources")
    ktgenInput("org.lwjgl:lwjgl-opengl:3.3.3:sources")
}

publishing {
    publications {
        create<MavenPublication>(project.name) {
            artifactId = base.archivesName.get()
            from(components["java"])
        }
    }
}