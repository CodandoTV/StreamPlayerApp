@file:Suppress("UnstableApiUsage")

import extensions.dokkaPlugin
import extensions.getLibrary
import extensions.setupAndroidDefaultConfig
import extensions.setupCompileOptions
import extensions.setupCompose
import extensions.setupPackingOptions

val libs: VersionCatalog = extensions.getByType<VersionCatalogsExtension>().named("libs")

plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-kapt")
    id("kotlin-parcelize")
    id("com.streamplayer.dokka")
}
val catalog: VersionCatalog = extensions.getByType<VersionCatalogsExtension>().named("libs")


android {
    namespace = Config.applicationId

    setupCompileOptions()
    setupPackingOptions()
    setupAndroidDefaultConfig()
    setupCompose(catalog)

    defaultConfig {
        applicationId = Config.applicationId
        minSdk = Config.minSdkVersion
        targetSdk = Config.targetSdkVersion
        versionCode = Config.versionCode
        versionName = Config.versionName
        multiDexEnabled = true
    }
}

dependencies {
    dokkaPlugin(libs.getLibrary("dokka"))
}