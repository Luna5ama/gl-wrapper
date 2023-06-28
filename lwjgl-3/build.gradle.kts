dependencies {
    api(project(":"))
    compileOnly("org.lwjgl:lwjgl-opengl:3.2.2") {
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