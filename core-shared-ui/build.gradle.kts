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
    Dependencies.Kotlin.list.forEach { implementation(it) }
    Dependencies.Support.list.forEach { implementation(it) }
    Dependencies.UnitTest.list.forEach { testImplementation(it) }
}