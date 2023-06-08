plugins {
    id("com.streamplayer.android-library")
}
android{
    buildTypes{
        getByName("debug"){
            buildConfigField("String","HOST",Config.BuildField.host_debug)
        }
        getByName("release"){
            buildConfigField("String","HOST",Config.BuildField.host_release)
        }
    }
}
dependencies {
    implementation(libs.bundles.kotlin)
    implementation(libs.bundles.networking)
    implementation(libs.bundles.koin)
    testImplementation(libs.bundles.test)
}