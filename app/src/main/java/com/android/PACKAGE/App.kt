package com.android.PACKAGE

import android.app.Application
import com.mvvm_starter.core.logger.debug.*
import com.mvvm_starter.core.logger.release.ReleaseTree
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber


/**
 * Created by Nikolay Siliuk on 9/3/20.
 */

@HiltAndroidApp
class App : Application() {
    companion object {
        lateinit var instance: App
    }

    override fun onCreate() {
        super.onCreate()
        setupLogger()
        instance = this
    }

    private fun setupLogger() {
        if (BuildConfig.DEBUG) {
            Timber.plant(DebugTree())
            Timber.plant(FileLoggingDebugTree())
        } else {
            Timber.plant(ReleaseTree())
        }
    }
}