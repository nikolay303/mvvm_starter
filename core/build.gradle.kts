plugins {
    id("com.android.library")
    id("kotlin-android")
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

    compileOptions {
        isCoreLibraryDesugaringEnabled = true
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
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
    api(Deps.Common.timber)
    implementation(Deps.Common.crashlytics) { isTransitive = true }
    implementation(Deps.Common.kotlinStdLib)
    implementation(Deps.Common.material)
    api(Deps.Common.glide)
    api(Deps.Common.glideCompiler)
    coreLibraryDesugaring(Deps.Common.desugarJdkLibs)
    implementation(Deps.Common.activityKtx)
    implementation(Deps.Common.lifecycleCommonJava8)

    //Custom Chrome Tab
    implementation("androidx.browser:browser:1.3.0") {
        exclude(group = "com.google.guava", module = "listenablefuture")
    }

}
