@file:Suppress("UnstableApiUsage")
plugins {
    id("com.streamplayer.android-library")
    id("com.streamplayer.compose")
}

dependencies {
    implementation(libs.bundles.koin)
    implementation(libs.bundles.kotlin)
    implementation(libs.bundles.androidSupport)
    implementation(libs.bundles.media3)
    testImplementation(libs.bundles.test)
}