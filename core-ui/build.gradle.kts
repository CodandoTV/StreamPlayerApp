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
    implementation("androidx.compose.ui:ui-tooling-preview:1.4.3")
    debugImplementation("androidx.compose.ui:ui-tooling:1.4.3")
}

