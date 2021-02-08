package com.mvvm_starter.core.logger.debug

import android.util.Log
import com.mvvm_starter.core.logger.LogPriority
import timber.log.Timber


class DebugTree : Timber.DebugTree() {

    override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {
        if (priority == LogPriority.FILE) {
            super.log(Log.DEBUG, tag, message, t)
        } else {
            super.log(priority, tag, message, t)
        }
    }
}