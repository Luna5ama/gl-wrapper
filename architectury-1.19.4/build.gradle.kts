import dev.fastmc.modsetup.minecraftVersion

architecturyProject {
    commonProject {
        dependencies {
            api(project(":lwjgl-3"))
        }
    }

    platformProject {
        base {
            archivesName.set("${rootProject.name}-${project.name}-${project.minecraftVersion}")
        }

        publishing {
            publications {
                create<MavenPublication>(project.name) {
                    artifactId = base.archivesName.get()
                    from(components["java"])
                }
            }
        }
    }
}