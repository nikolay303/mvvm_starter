object Versions {
    const val compileSdk = 31
    const val minSdk = 23
    const val appCompat = "1.4.0"
    const val material = "1.4.0"
    const val buildTools = "30.0.3"
    const val kotlin = "1.6.10"
    const val hilt = "2.38.1"
    const val timber = "5.0.1"
    const val retrofit = "2.9.0"
    const val okHttp = "4.9.0"
    const val constraintLayout = "2.1.2"
    const val gson = "2.8.9"
    const val junit = "4.12"
    const val atsl = "1.4.0"
    const val mockitoKotlin = "1.6.0"
    const val robolectric = "4.6.1"
    const val glide = "4.12.0"
    const val prefs = "1.1.1"
    const val ktx = "1.7.0"
    const val activityKtx = "1.2.4"
    const val fragmentKtx = "1.4.0"
    const val lifecycle = "2.2.0"
    const val safeArgs = "2.3.5"
    const val navigation = "2.4.0-rc01"
    const val annotations = "1.3.0"
    const val room = "2.4.1"
    const val desugarJdkLibs = "1.1.5"
    const val viewBinding = "1.5.2"
}

object Deps {
    object Test {
        const val junit = "junit:junit:${Versions.junit}"
        const val mockito = "com.nhaarman:mockito-kotlin:${Versions.mockitoKotlin}"
        const val robolectric = "org.robolectric:robolectric:${Versions.robolectric}"
        const val mockwebserver = "com.squareup.okhttp3:mockwebserver:${Versions.okHttp}"
    }

    object AndroidTest {
        const val junit = "junit:junit:${Versions.junit}"
        const val mockito = "com.nhaarman:mockito-kotlin:${Versions.mockitoKotlin}"
        const val testRunner = "com.android.support.test:runner:${Versions.atsl}"
        const val testRules = "com.android.support.test:rules:${Versions.atsl}"
    }

    object Network {
        const val gson = "com.google.code.gson:gson:${Versions.gson}"
        const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
        const val retrofitGsonConverter = "com.squareup.retrofit2:converter-gson:${Versions.retrofit}"
        const val okHttp = "com.squareup.okhttp3:okhttp:${Versions.okHttp}"
        const val loggingInterceptor = "com.squareup.okhttp3:logging-interceptor:${Versions.okHttp}"
    }

    object Database {
        const val room = "androidx.room:room-runtime:${Versions.room}"
        const val roomCompiler = "androidx.room:room-compiler:${Versions.room}"
        const val roomKtx = "androidx.room:room-ktx:${Versions.room}"
    }

    object Common {
        const val appCompat = "androidx.appcompat:appcompat:${Versions.appCompat}"
        const val constraintLayout = "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"
        const val material = "com.google.android.material:material:${Versions.material}"
        const val timber = "com.jakewharton.timber:timber:${Versions.timber}"
        const val crashlytics = "com.google.firebase:firebase-crashlytics:17.2.1"
        const val kotlinStdLib = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.kotlin}"
        const val preferences = "androidx.preference:preference:${Versions.prefs}"
        const val ktx = "androidx.core:core-ktx:${Versions.ktx}"
        const val activityKtx = "androidx.activity:activity-ktx:${Versions.activityKtx}"
        const val fragmentKtx = "androidx.fragment:fragment-ktx:${Versions.fragmentKtx}"
        const val lifecycleExtensions =  "androidx.lifecycle:lifecycle-extensions:${Versions.lifecycle}"
        const val lifecycleCommon =  "androidx.lifecycle:lifecycle-common-java8:${Versions.lifecycle}"
        const val lifecycleViewModelKtx =  "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycle}"
        const val lifecycleRuntimeKtx =  "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.lifecycle}"
        const val lifecycleLiveDataKtx =  "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.lifecycle}"
        const val navigationFragment = "androidx.navigation:navigation-fragment-ktx:${Versions.navigation}"
        const val navigationUi = "androidx.navigation:navigation-ui-ktx:${Versions.navigation}"
        const val annotations = "androidx.annotation:annotation:${Versions.annotations}"
        const val hilt = "com.google.dagger:hilt-android:${Versions.hilt}"
        const val hiltCompiler = "com.google.dagger:hilt-android-compiler:${Versions.hilt}"
        const val glide = "com.github.bumptech.glide:glide:${Versions.glide}"
        const val glideCompiler = "com.github.bumptech.glide:compiler:${Versions.glide}"
        const val desugarJdkLibs = "com.android.tools:desugar_jdk_libs:${Versions.desugarJdkLibs}"
        const val viewBindingNoReflection = "com.github.kirich1409:viewbindingpropertydelegate-noreflection:${Versions.viewBinding}"
        const val viewBinding = "com.github.kirich1409:viewbindingpropertydelegate:${Versions.viewBinding}"
        const val lifecycleCommonJava8 = "androidx.lifecycle:lifecycle-common-java8:2.4.0"
    }
}