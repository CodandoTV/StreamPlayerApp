plugins {
    id("com.streamplayer.android-library")
    id("com.google.devtools.ksp")
}

dependencies {

    implementation(libs.roomRuntime)
    implementation(libs.roomKtx)
    ksp(libs.roomCompiler)

    implementation(libs.bundles.kotlin)
    implementation(libs.bundles.koin)
    testImplementation(libs.bundles.test)
}