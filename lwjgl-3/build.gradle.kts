dependencies {
    api(project(":"))
    compileOnly("org.lwjgl:lwjgl-opengl:3.3.1") {
        isTransitive = false
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