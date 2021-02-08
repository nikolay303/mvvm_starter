package com.mvvm_starter.core.logger.release

import android.util.Log
import timber.log.Timber


class ReleaseTree : Timber.Tree() {
    override fun isLoggable(tag: String?, priority: Int) = priority == Log.ERROR || priority == Log.INFO

    override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {
        if (priority == Log.ERROR && t != null) {
//            Crashlytics.logException(t)
        } else {
            Log.i(tag, message)
        }
    }
}