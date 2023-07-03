repositories {
    maven("https://libraries.minecraft.net/")
}

dependencies {
    api(project(":shared"))
    compileOnly("org.lwjgl.lwjgl:lwjgl:2.9.4-nightly-20150209") {
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