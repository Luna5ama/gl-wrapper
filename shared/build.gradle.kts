plugins {
    id("dev.luna5ama.ktgen").version("1.0.0")
}

base {
    archivesName.set("${rootProject.name.lowercase()}-core")
}

dependencies {
    api("dev.luna5ama:kmogus-core:1.0-SNAPSHOT")
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