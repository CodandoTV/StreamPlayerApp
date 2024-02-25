plugins {
    id("com.streamplayer.android-library")
    id("com.google.devtools.ksp")
    alias(libs.plugins.kover)
}

dependencies {

    ksp(libs.roomCompiler)
    implementation(libs.bundles.room)
    implementation(libs.bundles.kotlin)
    implementation(libs.bundles.koin)
    testImplementation(libs.bundles.test)
}