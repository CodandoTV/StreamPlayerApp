@file:Suppress("UnstableApiUsage")

plugins {
    id("com.streamplayer.android-library")
    id("com.streamplayer.compose")
    id("com.google.devtools.ksp")
    alias(libs.plugins.kover)
}

dependencies {
    implementation(projects.coreNetworking)
    implementation(projects.coreNavigation)
    implementation(projects.coreShared)
    implementation(projects.coreSharedUi)

    implementation(libs.bundles.koin)
    implementation(libs.koin.annotations)
    ksp(libs.koin.compiler)

    implementation(libs.bundles.networking)
    implementation(libs.bundles.androidSupport)
    implementation(libs.coil)

    testImplementation(libs.bundles.test)
}

ksp {
    arg("KOIN_CONFIG_CHECK","true")
}
