allprojects {
    repositories {
        google()
        mavenCentral()
    }
}

val newBuildDir: Directory = rootProject.layout.buildDirectory.dir("../../build").get()
rootProject.layout.buildDirectory.value(newBuildDir)

subprojects {
    val newSubprojectBuildDir: Directory = newBuildDir.dir(project.name)
    project.layout.buildDirectory.value(newSubprojectBuildDir)
}
subprojects {
    project.evaluationDependsOn(":app")
}

// Explicitly enforce the Android NDK version for all subprojects to avoid CI mismatches.
subprojects {
    plugins.withId("com.android.application") {
        extensions.configure<com.android.build.gradle.AppExtension>("android") {
            // This mirrors the app module setting to ensure consistency across configurations.
            ndkVersion = "27.0.12077973"
        }
    }
    plugins.withId("com.android.library") {
        extensions.configure<com.android.build.gradle.LibraryExtension>("android") {
            ndkVersion = "27.0.12077973"
        }
    }
}

tasks.register<Delete>("clean") {
    delete(rootProject.layout.buildDirectory)
}
