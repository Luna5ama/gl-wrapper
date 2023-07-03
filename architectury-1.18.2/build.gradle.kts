import dev.fastmc.modsetup.minecraftVersion

architecturyProject {
    commonProject {
        dependencies {
            api(project(":lwjgl-3"))
        }

        base {
            archivesName.set("${rootProject.name.lowercase()}-${project.minecraftVersion}")
        }

        tasks {
            jar {
                destinationDirectory.set(file("${project.buildDir}/libs"))
                destinationDirectory.disallowChanges()
            }
        }

        afterEvaluate {
            publishing {
                publications {
                    create<MavenPublication>(project.name) {
                        artifactId = base.archivesName.get()
                        artifact(tasks["sourcesJar"])
                        artifact(tasks.jar)
                    }
                }
            }
        }
    }
}