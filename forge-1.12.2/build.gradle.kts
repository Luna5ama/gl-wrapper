dependencies {
    api(project(":lwjgl-2"))
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