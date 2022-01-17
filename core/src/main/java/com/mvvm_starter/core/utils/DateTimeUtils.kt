package com.mvvm_starter.core.utils

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Context
import android.text.format.DateFormat
import android.util.Range
import com.mvvm_starter.core.R
import java.text.SimpleDateFormat
import java.time.*
import java.time.format.DateTimeFormatter
import java.time.temporal.WeekFields
import java.util.*


const val MILLIS_IN_HOUR = 1000 * 60 * 60L
const val MILLIS_IN_DAY = MILLIS_IN_HOUR * 24L
const val SERVER_DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss"
const val SIMPLE_DATE_FORMAT = "dd.MM.yyyy"
const val RUSSIAN_DATE_FORMAT = "d MMMM, EE"
const val TIME_FORMAT = "HH:mm"
const val SHORT_DATE_FORMAT = "dd MMM"
const val DISPLAY_DATE_FORMAT = "EEEE, d MMMM"
const val DISPLAY_TADAY_DATE_FORMAT = ", d MMMM"
const val DISPLAY_TADAY_SHORT_DATE_FORMAT = "d MMM"
const val DATE_TIME_FORMAT_ISO_8601_WITH_MILLIS = "yyyy-MM-dd'T'HH:mm:ss[.SSS]X"
const val DATE_TIME_FORMAT_ISO_8601_WITH_X = "yyyy-MM-dd'T'HH:mm:ssX"
const val DATE_TIME_FORMAT_ISO_8601 = "yyyy-MM-dd'T'HH:mm:ss"


fun LocalTime.format(pattern: String): String {
    return this.format(DateTimeFormatter.ofPattern(pattern))
}
fun LocalDate.format(pattern: String): String {
    return this.format(DateTimeFormatter.ofPattern(pattern).withLocale(Locale.getDefault()))
}
fun LocalDateTime.format(pattern: String): String {
    return this.format(DateTimeFormatter.ofPattern(pattern))
}
fun OffsetDateTime.format(pattern: String): String {
    return this.format(DateTimeFormatter.ofPattern(pattern))
}
fun YearMonth.format(pattern: String): String {
    return this.format(DateTimeFormatter.ofPattern(pattern))
}
fun LocalDate.isWeekendDay(): Boolean {
    return dayOfWeek in weekendDaysByLocale()
}
fun weekendDaysByLocale(locale: Locale = Locale.getDefault()): Array<DayOfWeek> {
    val weekendSatSun = arrayOf(DayOfWeek.SATURDAY, DayOfWeek.SUNDAY)
    val weekendFriSat = arrayOf(DayOfWeek.FRIDAY, DayOfWeek.SATURDAY)
    val weekendThuFri = arrayOf(DayOfWeek.THURSDAY, DayOfWeek.FRIDAY)
    val weekendFriSun = arrayOf(DayOfWeek.FRIDAY, DayOfWeek.SUNDAY)
    val weekendFri = arrayOf(DayOfWeek.FRIDAY)
    val weekendSat = arrayOf(DayOfWeek.SATURDAY)
    val weekendSun = arrayOf(DayOfWeek.SUNDAY)
    return when (locale.country) {
        "CO" -> weekendSun // Colombia
        "GQ" -> weekendSun // Equatorial Guinea
        "IN" -> weekendSun // India
        "MX" -> weekendSun // Mexico
        "KP" -> weekendSun // North Korea
        "UG" -> weekendSun // Uganda
        "BN" -> weekendFriSun // Brunei Darussalam
        "DJ" -> weekendFri // Djibouti
        "IR" -> weekendFri // Iran
        "AF" -> weekendThuFri // Afghanistan
        "NP" -> weekendSat // Nepal
        "DZ" -> weekendFriSat // Algeria
        "BH" -> weekendFriSat // Bahrain
        "BD" -> weekendFriSat // Bangladesh
        "EG" -> weekendFriSat // Egypt
        "IQ" -> weekendFriSat // Iraq
        "IL" -> weekendFriSat // Israel
        "JO" -> weekendFriSat // Jordan
        "KW" -> weekendFriSat // Kuwait
        "LY" -> weekendFriSat // Libya
        "MV" -> weekendFriSat // Maldives
        "MR" -> weekendFriSat // Mauritania
        "MY" -> weekendFriSat // Malaysia
        "OM" -> weekendFriSat // Oman
        "PS" -> weekendFriSat // Palestine
        "QA" -> weekendFriSat // Qatar
        "SA" -> weekendFriSat // Saudi Arabia
        "SD" -> weekendFriSat // Sudan
        "SY" -> weekendFriSat // Syria
        "AE" -> weekendFriSat // United Arab Emirates
        "UAE" -> weekendFriSat // United Arab Emirates new code
        "YE" -> weekendFriSat // Yemen
        else -> weekendSatSun
    }
}
fun LocalDate.toFirstMondayDate(): LocalDate {
    if (dayOfWeek == DayOfWeek.MONDAY) {
        return this
    }
    var target = this
    while (target.dayOfWeek != DayOfWeek.MONDAY) {
        target = target.plusDays(1)
    }
    return target
}
fun Range<LocalDate>.getWeekendsDaysCount(): Long {
    var weekendDaysCount = 0L
    var date = lower
    val end = upper.plusDays(1)
    while (date != end) {
        if (date.isWeekendDay()) {
            weekendDaysCount++
        }
        date = date.plusDays(1)
    }
    return weekendDaysCount
}
fun LocalDate.isFirstWeekDay(): Boolean {
    val firstDayOfWeek = WeekFields.of(Locale.getDefault()).firstDayOfWeek
    return dayOfWeek == firstDayOfWeek
}
fun LocalDate.isLastWeekDay(): Boolean {
    val firstDayOfWeek = WeekFields.of(Locale.getDefault()).firstDayOfWeek
    return if (firstDayOfWeek == DayOfWeek.MONDAY) {
        dayOfWeek == DayOfWeek.SUNDAY
    } else {
        dayOfWeek == DayOfWeek.SATURDAY
    }
}
fun LocalDate.isFirstWeekWorkingDay(): Boolean {
    val lastWeekendDay = weekendDaysByLocale().lastOrNull() ?: return false
    val dayIndex = when (lastWeekendDay) {
        DayOfWeek.SUNDAY -> DayOfWeek.MONDAY.value
        else -> lastWeekendDay.value + 1
    }
    return dayOfWeek == DayOfWeek.of(dayIndex)
}
fun LocalDate.isLastWeekWorkingDay(): Boolean {
    val firstWeekendDay = weekendDaysByLocale().firstOrNull() ?: return false
    return dayOfWeek == DayOfWeek.of(firstWeekendDay.value - 1)
}
fun String.toLocalDateTime(pattern: String = DATE_TIME_FORMAT_ISO_8601): LocalDateTime {
    return LocalDateTime.parse(this, DateTimeFormatter.ofPattern(pattern))
}
fun buildDateRangeWithoutWeekends(range: Range<LocalDate>): Range<LocalDate> {
    val weekendDays = range.getWeekendsDaysCount()
    var newRange = Range(range.lower, range.upper.plusDays(weekendDays))
    val newWeekendDays = newRange.getWeekendsDaysCount()
    if (weekendDays != newWeekendDays) {
        newRange = buildDateRangeWithoutWeekends(range, newWeekendDays)
    }
    return newRange
}
private fun buildDateRangeWithoutWeekends(
    range: Range<LocalDate>,
    weekendDays: Long
): Range<LocalDate> {
    var newRange = Range(range.lower, range.upper.plusDays(weekendDays))
    val newWeekendDays = newRange.getWeekendsDaysCount()
    if (weekendDays != newWeekendDays) {
        newRange = Range(newRange.lower, newRange.upper.plusDays(newWeekendDays - weekendDays))
    }
    return newRange
}
fun LocalDateTime.formatToDeviceTimeFormat(context: Context): String {
    return if (DateFormat.is24HourFormat(context)) {
        format("H:mm")
    } else {
        format("h:mm a")
    }
}
