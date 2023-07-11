@file:Suppress("UnstableApiUsage")
plugins {
    id("com.streamplayer.android-library")
    id("com.streamplayer.compose")
}

dependencies {
    implementation(libs.bundles.koin)
    implementation(libs.bundles.kotlin)
    implementation(libs.bundles.androidSupport)
    implementation(libs.android.youtube.player)
    testImplementation(libs.bundles.test)
}