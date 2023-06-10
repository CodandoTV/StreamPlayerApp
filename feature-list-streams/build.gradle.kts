@file:Suppress("UnstableApiUsage")
android {
    namespace = "${Config.packageName}feature_list_streams"

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = Versions.compose_compiler_version
    }

    dependencies {
        implementation(project(Dependencies.Module.core_networking))
        implementation(project(Dependencies.Module.core_shared_ui))
        implementation(project(Dependencies.Module.core_navigation))
        implementation(project(Dependencies.Module.core_shared))
        Dependencies.Koin.list.forEach { implementation(it) }
        Dependencies.Retrofit.list.forEach { implementation(it) }
        Dependencies.Kotlin.list.forEach { implementation(it) }
        Dependencies.Support.list.forEach { implementation(it) }

        val composeBom = platform(Dependencies.Compose.composeBomVersion)
        implementation(composeBom)
        Dependencies.Compose.list.forEach { implementation(it) }

        Dependencies.UnitTest.list.forEach { testImplementation(it) }

        implementation(Dependencies.coil)
    }
}