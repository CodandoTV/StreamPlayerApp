plugins {
    id("com.streamplayer.android-library")
}
android {
    buildTypes {
        debug {
            buildConfigField("String", "HOST", Config.BuildField.host_debug)
            buildConfigField("String", "API_BEARER_AUTH", Keys.BuildField.api_bearer_debug)
        }
        release {
            buildConfigField("String", "HOST", Config.BuildField.host_release)
            buildConfigField("String", "API_BEARER_AUTH", Keys.BuildField.api_bearer_release)
        }
    }
}
dependencies {
    implementation(libs.bundles.kotlin)
    implementation(libs.bundles.networking)
    implementation(libs.bundles.koin)
    testImplementation(libs.bundles.test)
}