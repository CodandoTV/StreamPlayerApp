plugins {
    id("com.streamplayer.android-library")
    alias(libs.plugins.kover)
}

dependencies {
    implementation(libs.bundles.koin)
}