@file:Suppress("UnstableApiUsage")
plugins {
    id("com.streamplayer.android-library")
    id("com.streamplayer.compose")
    alias(libs.plugins.kover)
}

dependencies {
    implementation(libs.bundles.kotlin)
}