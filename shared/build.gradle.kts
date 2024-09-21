base {
    archivesName.set("${rootProject.name.lowercase()}-core")
}

dependencies {
    val kmogusVersion: String by project
    api("dev.luna5ama:kmogus-core:$kmogusVersion")
    compileOnly("it.unimi.dsi:fastutil:7.1.0")

    implementation(project(":base"))
}

publishing {
    publications {
        create<MavenPublication>(project.name) {
            artifactId = base.archivesName.get()
            from(components["java"])
        }
    }
}