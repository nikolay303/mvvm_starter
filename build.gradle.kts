buildscript {
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        classpath("com.android.tools.build:gradle:7.0.4")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}")
        classpath("com.google.dagger:hilt-android-gradle-plugin:${Versions.hilt}")
        classpath("androidx.navigation:navigation-safe-args-gradle-plugin:${Versions.safeArgs}")
    }
}

allprojects {
    repositories {
        google()
        mavenCentral()
        maven("https://jitpack.io")
    }
}

tasks {
    val clean by registering(Delete::class) {
        delete(rootProject.buildDir)
    }
}
