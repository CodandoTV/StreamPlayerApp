@file:Suppress("UnstableApiUsage")

android {
    namespace = "${Config.packageName}core_shared_ui"

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = Versions.composeCompilerVersion
    }
}

dependencies {
    implementation(platform(Dependencies.Compose.composeBomVersion))
    implementation(project(Dependencies.Module.core_navigation))
    Dependencies.Compose.list.forEach { implementation(it) }
    Dependencies.Kotlin.list.forEach { implementation(it) }
    Dependencies.Support.list.forEach { implementation(it) }
    Dependencies.Koin.list.forEach { implementation(it) }
    Dependencies.UnitTest.list.forEach { testImplementation(it) }
}