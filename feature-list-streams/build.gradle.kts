@file:Suppress("UnstableApiUsage")

import extensions.setupCompose

plugins {
    id("com.streamplayer.android-library")
    id("com.streamplayer.compose")
}

val catalog: VersionCatalog = extensions.getByType<VersionCatalogsExtension>().named("libs")

android{
    setupCompose(catalog)
}

dependencies {
    implementation(projects.coreNetworking)
    implementation(projects.coreNavigation)
    implementation(projects.coreShared)
    implementation(projects.coreSharedUi)

    implementation(libs.bundles.koin)
    implementation(libs.bundles.networking)
    implementation(libs.bundles.androidSupport)
    implementation(libs.coil)

    testImplementation(libs.bundles.test)
}