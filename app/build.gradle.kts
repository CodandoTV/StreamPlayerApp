@file:Suppress("UnstableApiUsage")

import extensions.setupCompose

plugins {
    id("com.streamplayer.application")
}

val catalog: VersionCatalog = extensions.getByType<VersionCatalogsExtension>().named("libs")

android {
    setupCompose(catalog)
}

dependencies {

    implementation(projects.featureListStreams)
    implementation(projects.coreShared)
    implementation(projects.coreSharedUi)
    implementation(projects.coreNavigation)
    implementation(projects.coreNetworking)

    implementation(platform(libs.compose.bom))
    androidTestImplementation(platform(libs.compose.bom))

    implementation(libs.bundles.koin)
    implementation(libs.bundles.androidSupport)
    implementation(libs.bundles.compose)
    implementation(libs.bundles.kotlin)

    implementation(libs.lottie)
    implementation(libs.lottie)
    testImplementation(libs.bundles.test)
}