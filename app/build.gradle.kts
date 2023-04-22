dependencies {
    implementation(project(Dependencies.Module.feature_list_streams))
    implementation(project(Dependencies.Module.core_networking))
    implementation(Dependencies.Koin.koin)
    Dependencies.Kotlin.list.forEach { implementation(it) }
    Dependencies.Support.list.forEach { implementation(it) }
    Dependencies.UnitTest.list.forEach { testImplementation(it) }
    Dependencies.AndroidTest.list.forEach { androidTestImplementation(it) }
}