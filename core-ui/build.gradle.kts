@file:Suppress("UnstableApiUsage")
android {
    namespace = "${Config.packageName}core_ui"

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = Versions.composeCompilerVersion
    }
}

dependencies {
    Dependencies.Kotlin.list.forEach { implementation(it) }
    Dependencies.Compose.list.forEach { implementation(it) }
    implementation(platform(Dependencies.Compose.composeBomVersion))
}

