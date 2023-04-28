android{
    namespace = "${Config.packageName}core_networking"

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
    implementation(Dependencies.Koin.koin)
    Dependencies.Retrofit.list.forEach { implementation(it) }
    Dependencies.Kotlin.list.forEach { implementation(it) }
    Dependencies.UnitTest.list.forEach { testImplementation(it) }
    Dependencies.AndroidTest.list.forEach { androidTestImplementation(it) }
}