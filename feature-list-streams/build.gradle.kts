@file:Suppress("UnstableApiUsage")

android {
    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = Versions.composeCompilerVersion
    }

    dependencies {
        Dependencies.Retrofit.list.forEach { implementation(it) }
        Dependencies.Kotlin.list.forEach { implementation(it) }
        Dependencies.Support.list.forEach { implementation(it) }

        val composeBom = platform(Dependencies.Compose.composeBomVersion)
        implementation(composeBom)
        Dependencies.Compose.list.forEach { implementation(it) }

        Dependencies.UnitTest.list.forEach { testImplementation(it) }
        //Dependencies.AndroidTest.list.forEach { androidTestImplementation(it) }
    }
}