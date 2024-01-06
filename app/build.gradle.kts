@file:Suppress("UnstableApiUsage")

plugins {
    id("com.streamplayer.application")
    alias(libs.plugins.kover)
}

android {
    koverReport {
        filters {
            excludes {
                packages(
                    "*.di",
                )

                classes(
                    "*.BuildConfig",
                    "*.ComposableSingletons",
                    "*ScreenKt*",
                )
                annotatedBy("Generated")
            }
        }
    }
}

dependencies {
    implementation(projects.featureFavorites)
    implementation(projects.featureListStreams)
    implementation(projects.featureProfile)
    implementation(projects.coreShared)
    implementation(projects.coreSharedUi)
    implementation(projects.coreNavigation)
    implementation(projects.coreNetworking)
    implementation(projects.coreLocalStorage)

    implementation(platform(libs.compose.bom))
    androidTestImplementation(platform(libs.compose.bom))

    implementation(libs.bundles.koin)
    implementation(libs.bundles.androidSupport)
    implementation(libs.bundles.compose)
    implementation(libs.bundles.kotlin)

    implementation(libs.lottie)
    implementation(libs.lottie)
    testImplementation(libs.bundles.test)

    // Kover - Combined report
    kover(projects.featureListStreams)
    kover(projects.featureProfile)
    kover(projects.featureFavorites)
}