@file:Suppress("UnstableApiUsage")

android {
    namespace = "${Config.packageName}feature_onboarding"

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = Versions.composeCompilerVersion
    }
}

dependencies {
    implementation(project(Dependencies.Module.core_shared_ui))
    Dependencies.Kotlin.list.forEach { implementation(it) }
    Dependencies.Support.list.forEach { implementation(it) }

    val composeBom = platform(Dependencies.Compose.composeBomVersion)
    implementation(composeBom)
    Dependencies.Compose.list.forEach { implementation(it) }
    Dependencies.Lottie.list.forEach { implementation(it) }
    Dependencies.UnitTest.list.forEach { testImplementation(it) }

}