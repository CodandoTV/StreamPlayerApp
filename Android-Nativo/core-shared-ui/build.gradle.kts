@file:Suppress("UnstableApiUsage")
plugins {
    id("com.streamplayer.android-library")
    id("com.streamplayer.compose")
}

dependencies {
    implementation(projects.coreShared)
    implementation(libs.bundles.koin)
    implementation(libs.bundles.kotlin)
    implementation(libs.bundles.androidSupport)
    testImplementation(libs.bundles.test)
}