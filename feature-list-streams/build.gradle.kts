@file:Suppress("UnstableApiUsage")

android {
    namespace = "${Config.packageName}feature_list_streams"
        
    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = Versions.composeCompilerVersion
    }

    dependencies {
        implementation(Dependencies.Koin.koin)
        Dependencies.Retrofit.list.forEach { implementation(it) }
        Dependencies.Kotlin.list.forEach { implementation(it) }
        Dependencies.Support.list.forEach { implementation(it) }

        val composeBom = platform(Dependencies.Compose.composeBomVersion)
        implementation(composeBom)
        Dependencies.Compose.list.forEach { implementation(it) }

        Dependencies.UnitTest.list.forEach { testImplementation(it) }
        //Dependencies.AndroidTest.list.forEach { androidTestImplementation(it) }

        Dependencies.Coil.list.forEach { implementation(it) }
    }
}