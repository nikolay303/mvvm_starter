package com.mvvm_starter.core.utils

import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.util.*


fun Float.isInteger() = this - this.toInt() <= 0.000000001f

fun Long.getTimerText(): String {
    var seconds = this / 1000L
    val minutes = seconds / 60
    seconds -= minutes * 60
    return String.format("%02d:%02d", minutes, seconds)
}

fun Long.getTimerTextWithHours(showHoursIfMoreZero: Boolean = true): String {
    var seconds = this / 1000L
    var minutes = seconds / 60L
    seconds %= 60L
    val hours = minutes / 60L
    minutes %= 60L
    return if (hours > 0 || (hours == 0L && showHoursIfMoreZero)) {
        String.format("%02d:%02d:%02d", hours, minutes, seconds)
    } else {
        String.format("%02d:%02d", minutes, seconds)
    }
}

fun Float.toNormalText(separator: Char = '.'): String {
    val decimalFormatSymbols = DecimalFormatSymbols()
    decimalFormatSymbols.decimalSeparator = separator
    val decimalFormat = DecimalFormat("#.##", decimalFormatSymbols)
    return decimalFormat.format(this)
}

fun Double.toNormalText(separator: Char = '.', pattern: String = "##0.00"): String {
    val decimalFormatSymbols = DecimalFormatSymbols()
    decimalFormatSymbols.decimalSeparator = separator
    val decimalFormat = DecimalFormat(pattern, decimalFormatSymbols)
    return decimalFormat.format(this)
}

fun Long?.millisToSeconds(): Long = (this ?: 0) / 1000

fun Long?.secondsToMillis(): Long = (this ?: 0) * 1000

fun Number.toAmount(currencySymbol: String = "$"): String {
    val decimalFormatSymbols = DecimalFormatSymbols(Locale.getDefault())
    val decimalFormat = DecimalFormat("###.00", decimalFormatSymbols)
    return "${decimalFormat.format(this.toDouble())} $currencySymbol"
}