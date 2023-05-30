import kotlin.reflect.full.memberProperties

interface GroupLibs {
    val list: List<String>
}

object Dependencies {
    const val runnerPackage = "androidx.test.runner.AndroidJUnitRunner"
    const val coil = "io.coil-kt:coil-compose:${Versions.coil_version}"
    const val lottie = "com.airbnb.android:lottie-compose:${Versions.lottie}"

    object Module {
        const val core_networking = ":core-networking"
        const val core_shared_ui = ":core-shared-ui"
        const val feature_list_streams = ":feature-list-streams"
        const val core_navigation = ":core-navigation"
    }

    val modules: List<String> by lazy {
        Module::class.memberProperties.map {
            it.name.replace("_", "-")
        }
    }

    object Gradle {
        const val kotlinPlugin =
            "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin_version}"
        const val androidTools = "com.android.tools.build:gradle:${Versions.gradle_plugin_version}"
        const val gradlePlugin = "gradle-plugin"
        const val kotlinGradlePlugin =
            "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin_version}"
    }

    object Kotlin : GroupLibs {
        const val stdlib = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.kotlin_version}"
        const val reflect = "org.jetbrains.kotlin:kotlin-reflect:${Versions.kotlin_version}"
        const val coreKtx = "androidx.core:core-ktx:${Versions.androidx_core_ktx}"

        override val list: List<String>
            get() = listOf(
                Kotlin.stdlib,
                Kotlin.reflect,
                Kotlin.coreKtx
            )
    }

    object UnitTest : GroupLibs {
        const val core = "androidx.test:core:${Versions.test_core_version}"
        const val junit = "junit:junit:${Versions.test_junit_version}"
        const val runner = "androidx.test:runner:${Versions.test_core_version}"
        const val viewModelTest = "androidx.arch.core:core-testing:${Versions.viewmodel_version}"
        const val mockk = "io.mockk:mockk:${Versions.mockk}"

        override val list: List<String>
            get() = listOf(
                UnitTest.core,
                UnitTest.junit,
                UnitTest.viewModelTest,
                UnitTest.mockk
            )
    }

    object AndroidTest : GroupLibs {
        const val runner = "androidx.test:runner:${Versions.test_core_version}"
        const val espresso =
            "androidx.test.espresso:espresso-core:${Versions.test_espresso_version}"
        const val rules = "androidx.test:rules:${Versions.test_rules_version}"
        const val hamcrest = "org.hamcrest:hamcrest-library:${Versions.test_hamcrest}"
        const val uiautomator = "androidx.test.uiautomator:uiautomator:${Versions.test_uiautomator}"
        const val ext = "androidx.test.ext:junit:${Versions.test_ext}"
        const val mockWebServer = "com.squareup.okhttp3:mockwebserver:${Versions.mockWebServer}"
        const val intent =
            "androidx.test.espresso:espresso-intents:${Versions.test_espresso_version}"

        override val list: List<String>
            get() = listOf(
                AndroidTest.espresso,
                AndroidTest.hamcrest,
                AndroidTest.rules,
                AndroidTest.uiautomator,
                AndroidTest.runner,
                AndroidTest.mockWebServer,
                AndroidTest.ext,
                AndroidTest.intent
            )
    }

    object Support : GroupLibs {
        const val appCompat = "androidx.appcompat:appcompat:${Versions.support_version}"
        const val androidXCore = "androidx.core:core-ktx:${Versions.support_version}"
        const val constraintLayout =
            "androidx.constraintlayout:constraintlayout:${Versions.constraint_motion_version}"
        const val material = "com.google.android.material:material:${Versions.material_version}"
        const val animation = "androidx.dynamicanimation:dynamicanimation:${Versions.dynamic_animation}"

        override val list: List<String>
            get() = listOf(
                Support.appCompat,
                Support.constraintLayout,
                Support.material,
                Support.animation,
            )
    }

    object Koin : GroupLibs {
        const val koin = "io.insert-koin:koin-android:${Versions.koin}"
        const val compose = "io.insert-koin:koin-androidx-compose:${Versions.koin}"

        override val list: List<String>
            get() = listOf(
                koin,
                compose
            )
    }

    object Retrofit : GroupLibs {
        const val core = "com.squareup.retrofit2:retrofit:${Versions.retrofit_version}"
        const val moshi = "com.squareup.moshi:moshi-kotlin:${Versions.moshi_version}"
        const val moshiConverter =
            "com.squareup.retrofit2:converter-moshi:${Versions.retrofit_version}"
        const val coreOkhttp = "com.squareup.okhttp3:okhttp:${Versions.okhttp_version}"
        const val interceptor =
            "com.squareup.okhttp3:logging-interceptor:${Versions.okhttp_version}"

        override val list: List<String>
            get() = listOf(
                Retrofit.core,
                Retrofit.moshi,
                Retrofit.moshiConverter,
                Retrofit.coreOkhttp,
                Retrofit.interceptor
            )
    }

    object ViewModel : GroupLibs {
        const val lifecycleExtensions =
            "androidx.lifecycle:lifecycle-extensions:${Versions.viewmodel_version}"
        const val lifecycleConvertRxToLivedata =
            "androidx.lifecycle:lifecycle-reactivestreams:${Versions.viewmodel_version}"

        override val list: List<String>
            get() = listOf(
                ViewModel.lifecycleExtensions,
                ViewModel.lifecycleConvertRxToLivedata
            )
    }

    object Compose : GroupLibs {
        const val composeBomVersion = "androidx.compose:compose-bom:${Versions.compose_bom_version}"
        const val composeUI = "androidx.compose.ui:ui"
        const val coposeUIToolingPreview = "androidx.compose.ui:ui-tooling-preview"
        const val composeActivityCompose =
            "androidx.activity:activity-compose:${Versions.compose_activity}"
        const val composeUITooling = "androidx.compose.ui:ui-tooling"
        const val composeMaterial3 = "androidx.compose.material3:material3:${Versions.compose_material_3_version}"
        const val composeNavigation = "androidx.navigation:navigation-compose:${Versions.compose_navigation_version}"
        const val composeIcons = "androidx.compose.material:material-icons-extended:${Versions.compose_icons}"
        const val lifecycleComposeRuntime = "androidx.lifecycle:lifecycle-runtime-compose:${Versions.lifecycle_compose_runtime}"
        const val pagingCompose = "androidx.paging:paging-compose:${Versions.paging_compose}"

        override val list: List<String>
            get() = listOf(
                Compose.composeUI,
                Compose.coposeUIToolingPreview,
                Compose.composeActivityCompose,
                Compose.composeUITooling,
                Compose.composeMaterial3,
                Compose.composeNavigation,
                Compose.composeIcons,
                Compose.lifecycleComposeRuntime,
                Compose.pagingCompose
            )
    }
}