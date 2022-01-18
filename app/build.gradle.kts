plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
}

android {
    compileSdk = Versions.compileSdk
    buildToolsVersion = Versions.buildTools

    defaultConfig {
        minSdk = Versions.minSdk
        targetSdk = Versions.compileSdk
        applicationId = "com.android.PACKAGE"
        versionCode = 1
        versionName = "0.0.1"
        testInstrumentationRunner = "android.support.test.runner.AndroidJUnitRunner"
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }

    buildFeatures {
        viewBinding = true
    }

//    signingConfigs {
//        create("staging") {
//            storeFile = file("keys/staging/staging_keystore.jks")
//            storePassword = ""
//            keyAlias = "staging"
//            keyPassword = ""
//        }
//        create("release") {
//            storeFile = file("keys/release/release_keystore.jks")
//            storePassword = ""
//            keyAlias = "release"
//            keyPassword = ""
//        }
//    }


    buildTypes {
        maybeCreate("debug").apply {
//            signingConfig = null
        }

        maybeCreate("release").apply {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
        }
    }
    flavorDimensions += "default"


    productFlavors {
//        create("staging").apply {
//            dimension = "default"
//            applicationIdSuffix = ".staging"
//            signingConfig = signingConfigs["staging"]
//            versionNameSuffix = ".staging"
//        }
//        create("release").apply {
//            dimension = "default"
//            signingConfig = signingConfigs["release"]
//        }
    }
}

repositories {
    mavenCentral()
}
dependencies {
    implementation(fileTree(mapOf("include" to listOf("*.jar"), "dir" to "libs")))
    testImplementation(Deps.Test.junit)
    testImplementation(Deps.Test.mockito)
    testImplementation(Deps.Test.robolectric)
    testImplementation(Deps.Test.mockwebserver)

    androidTestImplementation(Deps.AndroidTest.junit)
    androidTestImplementation(Deps.AndroidTest.mockito)
    androidTestImplementation(Deps.AndroidTest.testRunner)
    androidTestImplementation(Deps.AndroidTest.testRules)

    implementation(Deps.Common.annotations)
    implementation(Deps.Common.appCompat)
    implementation(Deps.Common.constraintLayout)
    implementation(Deps.Common.material)
    implementation(Deps.Common.hilt)
    kapt(Deps.Common.hiltCompiler)
    implementation(Deps.Network.gson)
    implementation(Deps.Network.retrofit)
    implementation(Deps.Network.retrofitGsonConverter)
    implementation(Deps.Network.okHttp)
    implementation(Deps.Network.loggingInterceptor)
    implementation(Deps.Common.timber)
    implementation(Deps.Common.preferences)
    implementation(Deps.Common.crashlytics) {
        isTransitive = true
    }
    implementation(Deps.Common.kotlinStdLib)
    implementation(Deps.Common.ktx)
    implementation(Deps.Common.lifecycleLiveDataKtx)
    implementation(Deps.Common.lifecycleViewModelKtx)
    implementation(Deps.Common.lifecycleRuntimeKtx)
    implementation(Deps.Common.lifecycleCommon)
    implementation(Deps.Common.navigationFragment)
    implementation(Deps.Common.navigationUi)
    implementation(Deps.Common.lifecycleExtensions)
    implementation(Deps.Common.activityKtx)
    implementation(Deps.Common.fragmentKtx)
    implementation(Deps.Database.room)
    annotationProcessor(Deps.Database.roomCompiler)
    kapt(Deps.Database.roomKtx)

    implementation(project(":common"))
}
kapt {
    generateStubs = true
}
repositories {
    google()
    mavenCentral()
}