@file:Suppress("UnstableApiUsage")

android {
    namespace = "${Config.packageName}core_shared_ui"

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = Versions.compose_compiler_version
    }
}

dependencies {
    implementation(project(Dependencies.Module.core_shared))
    implementation(platform(Dependencies.Compose.composeBomVersion))
    Dependencies.Compose.list.forEach { implementation(it) }
    Dependencies.Kotlin.list.forEach { implementation(it) }
    Dependencies.Support.list.forEach { implementation(it) }
    Dependencies.UnitTest.list.forEach { testImplementation(it) }
}