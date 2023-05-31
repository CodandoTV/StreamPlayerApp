@file:Suppress("UnstableApiUsage")
android {
    namespace = "${Config.packageName}app"

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = Versions.compose_compiler_version
    }
}
dependencies {
    implementation(project(Dependencies.Module.feature_list_streams))
    implementation(project(Dependencies.Module.core_networking))
    implementation(project(Dependencies.Module.core_shared_ui))
    implementation(project(Dependencies.Module.core_navigation))
    implementation(project(Dependencies.Module.core_shared))

    implementation(Dependencies.Koin.koin)
    Dependencies.Kotlin.list.forEach { implementation(it) }
    Dependencies.Support.list.forEach { implementation(it) }
    Dependencies.UnitTest.list.forEach { testImplementation(it) }
    Dependencies.AndroidTest.list.forEach { androidTestImplementation(it) }

    val composeBom = platform(Dependencies.Compose.composeBomVersion)
    implementation(composeBom)
    Dependencies.Compose.list.forEach { implementation(it) }
    implementation(Dependencies.lottie)
}