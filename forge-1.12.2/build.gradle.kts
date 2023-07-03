import dev.fastmc.modsetup.minecraftVersion

dependencies {
    api(project(":lwjgl-2"))
}

base {
    archivesName.set("${rootProject.name.lowercase()}-${project.minecraftVersion}")
}

publishing {
    publications {
        create<MavenPublication>(project.name) {
            artifactId = base.archivesName.get()
            artifact(tasks["sourcesJar"])
            artifact(tasks.jar)
        }
    }
}