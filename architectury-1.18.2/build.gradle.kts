tasks {
    listOf(
        remapRuntime,
        generateConstants,
        compileConstants,
        modPackaging,
        modLoaderJar
    ).forEach {
        it.configure {
            isEnabled = false
        }
    }

    jar {
        archiveClassifier.set("")
    }
}