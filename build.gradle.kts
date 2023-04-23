import Dependencies.modules

buildscript {
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        classpath(Dependencies.Gradle.kotlinPlugin)
        classpath(Dependencies.Gradle.kotlinGradlePlugin)
        classpath(Dependencies.Gradle.androidTools)
        classpath(kotlin(Dependencies.Gradle.gradlePlugin, Versions.kotlin_version))
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.8.0")
    }
}

allprojects {
    repositories {
        google()
        mavenCentral()
    }
    configureAndroid()
}


fun Project.configureAndroid() {
    val isAppModule = name == "app"

    when {
        isAppModule -> configureAppAndroid()
        modules.contains(name) -> configureAndroidLibrary()
        else -> return
    }

    apply(plugin = "kotlin-android")
    apply(plugin = "kotlin-kapt")

    configure<com.android.build.gradle.BaseExtension> {
        compileSdkVersion(Config.compileSdkVersion)
        buildToolsVersion(Config.buildToolsVersion)
        namespace = Config.applicationId

        defaultConfig {
            minSdk = Config.minSdkVersion
            targetSdk = Config.targetSdkVersion
            versionCode = Config.versionCode
            versionName = Config.versionName
            testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
            vectorDrawables.useSupportLibrary = true
            multiDexEnabled = true
        }

        lintOptions {
            isCheckReleaseBuilds = false
            isCheckDependencies = true
            isCheckAllWarnings = true
            isWarningsAsErrors = true
            isIgnoreWarnings = true
            isAbortOnError = false
        }

        compileOptions {
            sourceCompatibility = JavaVersion.VERSION_1_8
            targetCompatibility = JavaVersion.VERSION_1_8
        }
    }
}


fun Project.configureAppAndroid() {
    apply(plugin = "com.android.application")

    configure<com.android.build.gradle.BaseExtension> {
        defaultConfig {
            applicationId = Config.applicationId
        }

        testOptions {
            animationsDisabled = true
        }

        buildTypes {
            getByName("release") {
                isMinifyEnabled = true
                proguardFiles(
                    getDefaultProguardFile("proguard-android-optimize.txt"),
                    "proguard-rules.pro"
                )
            }
        }
    }
}

fun Project.configureAndroidLibrary() {
    apply(plugin = "com.android.library")
}

tasks.register("clean").configure {
    delete("build")
}
