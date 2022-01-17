plugins {
    id("com.android.library")
    id("kotlin-android")
    id("kotlin-kapt")
}

android {
    compileSdk = Versions.compileSdk
    buildToolsVersion = Versions.buildTools

    defaultConfig {
        minSdk = Versions.minSdk
        targetSdk = Versions.compileSdk

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildFeatures {
        viewBinding = true
    }

    buildTypes {
        maybeCreate("release").apply {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

}

dependencies {
    implementation(fileTree(mapOf("include" to listOf("*.jar"), "dir" to "libs")))

    implementation(Deps.Common.appCompat)
    implementation(Deps.Common.timber)
    implementation(Deps.Common.material)
    implementation(Deps.Common.kotlinStdLib)
    implementation(Deps.Common.lifecycleViewModelKtx)
    api(Deps.Common.viewBinding)
    api(Deps.Common.viewBindingNoReflection)

    api(project(":core"))
}