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

publishing {
    publications {
        create<MavenPublication>(project.name) {
            artifactId = base.archivesName.get()
            from(components["java"])
        }
    }
}