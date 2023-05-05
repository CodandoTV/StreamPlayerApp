@file:Suppress("UnstableApiUsage")
android {
    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = Versions.composeCompilerVersion
    }
}

dependencies {
    implementation(project(Dependencies.Module.feature_list_streams))
    implementation(project(Dependencies.Module.core_networking))
    implementation(project(Dependencies.Module.core_ui))
    implementation(Dependencies.Koin.koin)
    implementation(Dependencies.Compose.composeUI)
    implementation(Dependencies.Compose.composeMaterial3)
    implementation(Dependencies.Compose.composeNavigation)
    Dependencies.Kotlin.list.forEach { implementation(it) }
    Dependencies.Support.list.forEach { implementation(it) }
    Dependencies.UnitTest.list.forEach { testImplementation(it) }
    Dependencies.AndroidTest.list.forEach { androidTestImplementation(it) }
}