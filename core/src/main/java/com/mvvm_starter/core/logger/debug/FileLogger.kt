package com.mvvm_starter.core.logger.debug

import java.io.BufferedWriter
import java.io.File
import java.io.FileWriter


// For file logging you should provide WRITE_EXTERNAL_STORAGE permission

var DEFAULT_PATH = "sdcard/mycoach_log.txt"

fun appendLog(message: String, filePath: String = DEFAULT_PATH) {
    try {
        val logFile = File(filePath)
        if (!logFile.exists()) {
            logFile.createNewFile()

        }
        val bufWriter = BufferedWriter(FileWriter(logFile, true))
        bufWriter.append(message)
        bufWriter.newLine()
        bufWriter.close()
    } catch (e: Exception) {
        e.printStackTrace()
    }
}