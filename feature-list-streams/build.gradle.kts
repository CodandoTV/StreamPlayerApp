android {
    namespace = "${Config.packageName}feature_list_streams"
}

dependencies {
    implementation(project(Dependencies.Module.core_networking))
    implementation(Dependencies.Koin.koin)
    Dependencies.Retrofit.list.forEach { implementation(it) }
    Dependencies.Kotlin.list.forEach { implementation(it) }
    Dependencies.Support.list.forEach { implementation(it) }
    Dependencies.Compose.list.forEach { implementation(it) }

    Dependencies.UnitTest.list.forEach { testImplementation(it) }
    Dependencies.AndroidTest.list.forEach { androidTestImplementation(it) }
}