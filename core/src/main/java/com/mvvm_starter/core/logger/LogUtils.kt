package com.mvvm_starter.core.logger

import android.text.TextUtils
import java.text.SimpleDateFormat
import java.util.*


const val DATE_TIME_FORMAT: String = "MM:dd hh:mm:ss.SSS"

fun buildFileLogMessage(tag: String?, t: Throwable?, message: String?, vararg args: Any?): String {
    val dateTime = SimpleDateFormat(DATE_TIME_FORMAT, Locale.US).format(Date())
    var resultMessage = "$dateTime: "
    if (!TextUtils.isEmpty(tag)) {
        resultMessage += "$tag "
    }
    resultMessage += if (t != null && !TextUtils.isEmpty(message)) {
        "ERROR=${t.message} ${String.format(message!!, args)}"
    } else if (t != null) {
        "ERROR=${t.message}"
    } else if (!TextUtils.isEmpty(message)) {
        String.format(message!!, args)
    } else {
        ""
    }
    return resultMessage
}