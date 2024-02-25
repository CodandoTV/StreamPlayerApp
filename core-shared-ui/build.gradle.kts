@file:Suppress("UnstableApiUsage")
plugins {
    id("com.streamplayer.android-library")
    id("com.streamplayer.compose")
    alias(libs.plugins.kover)
}

dependencies {
    implementation(projects.coreShared)
    implementation(libs.bundles.koin)
    implementation(libs.bundles.kotlin)
    implementation(libs.bundles.androidSupport)
    implementation(libs.android.youtube.player)
    testImplementation(libs.bundles.test)
    implementation(libs.coil)
}