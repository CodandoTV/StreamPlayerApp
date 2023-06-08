@file:Suppress("UnstableApiUsage")
import extensions.getBundle
import extensions.getLibrary
import extensions.setupCompose

plugins {
    id("com.streamplayer.android-library")
}

val libs: VersionCatalog = extensions.getByType<VersionCatalogsExtension>().named("libs")

android {
   setupCompose(libs)
}

dependencies {
    implementation(platform(libs.getLibrary("compose.bom")))
    androidTestImplementation(platform(libs.getLibrary("compose.bom")))

    implementation(libs.getBundle("compose"))
    debugImplementation(libs.getLibrary("compose.ui.tooling"))
}