plugins {
    id("com.google.devtools.ksp")
}

base {
    archivesName.set("${rootProject.name.lowercase()}-core")
}

dependencies {
    api("dev.luna5ama:kmogus-core:1.0-SNAPSHOT")
    ksp(project(":shared:codegen"))
    compileOnly("it.unimi.dsi:fastutil:7.1.0")
}

publishing {
    publications {
        create<MavenPublication>(project.name) {
            artifactId = base.archivesName.get()
            from(components["java"])
        }
    }
}