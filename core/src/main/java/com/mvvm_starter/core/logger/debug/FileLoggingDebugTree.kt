package com.mvvm_starter.core.logger.debug

import android.text.TextUtils
import com.mvvm_starter.core.logger.LogPriority
import com.mvvm_starter.core.logger.buildFileLogMessage
import timber.log.Timber


class FileLoggingDebugTree : Timber.DebugTree() {


    override fun isLoggable(tag: String?, priority: Int): Boolean {
        return priority == LogPriority.FILE
    }

    override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {
        tryLogToFile(tag, t, message)
    }

    private fun tryLogToFile(tag: String?, t: Throwable?, message: String?, vararg args: Any?) {
        val resultMessage = buildFileLogMessage(tag, t, message, args)
        if (!TextUtils.isEmpty(resultMessage)) {
            logToFile(resultMessage)
        }
    }

    private fun logToFile(resultMessage: String) {
        appendLog(resultMessage)
    }
}