object Versions {
    const val compileSdk = 30
    const val minSdk = 23
    const val appCompat = "1.2.0"
    const val support = "1.0.0"
    const val material = "1.2.1"
    const val buildTools = "30.0.3"
    const val kotlin = "1.4.21"
    const val hilt = "2.31.2-alpha"
    const val anko = "0.10.1"
    const val timber = "4.7.1"
    const val retrofit = "2.9.0"
    const val okHttp = "4.9.0"
    const val constraintLayout = "2.0.0-beta4"
    const val gson = "2.8.6"
    const val junit = "4.12"
    const val atsl = "1.0.1"
    const val mockitoKotlin = "1.5.0"
    const val robolectric = "3.6.1"
    const val sourceCompat = "1.8"
    const val targetCompat = "1.8"
    const val glide = "4.10.0"
    const val prefs = "1.1.1"
    const val ktx = "1.3.2"
    const val activityKtx = "1.1.0"
    const val fragmentKtx = "1.2.5"
    const val lifecycle = "2.2.0"
    const val navigation = "2.3.2"
    const val annotations = "1.1.0"
    const val room = "2.2.6"
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
        const val ankoCommons = "org.jetbrains.anko:anko-commons:${Versions.anko}"
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
    }
}