package com.mvvm_starter.core.utils

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Context
import android.text.format.DateFormat
import com.mvvm_starter.core.R
import java.text.SimpleDateFormat
import java.util.*


const val MILLIS_IN_HOUR = 1000 * 60 * 60L
const val MILLIS_IN_DAY = MILLIS_IN_HOUR * 24L
const val SERVER_DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss"
const val SIMPLE_DATE_FORMAT = "dd.MM.yyyy"
const val TIME_FORMAT = "HH:mm"
const val SHORT_DATE_FORMAT = "dd MMM"
const val DISPLAY_DATE_FORMAT = "EEEE, d MMMM"
const val DISPLAY_TADAY_DATE_FORMAT = ", d MMMM"
const val DISPLAY_TADAY_SHORT_DATE_FORMAT = "d MMM"
const val NOW_CAPS = "Сегодня"
const val NOW = "cегодня"


/**
 * Date extensions
 */

@SuppressLint("SimpleDateFormat")
fun Date.weekDayOneChar(): String =
    SimpleDateFormat("E").format(this).first().toUpperCase().toString()

fun Date.copy() = this.clone() as Date

@SuppressLint("SimpleDateFormat")
fun Date.inMonthYearFormat(): String = SimpleDateFormat("LLLL, yyyy").format(this)

@SuppressLint("SimpleDateFormat")
fun Date.inFormat(pattern: String): String = SimpleDateFormat(pattern).format(this)

@SuppressLint("SimpleDateFormat")
fun Date.inSimpleFormat(): String = SimpleDateFormat(SIMPLE_DATE_FORMAT).format(this)

fun Date.inDateFormat(context: Context): String = DateFormat.getMediumDateFormat(context).format(this)

fun Date.inServerDateFormat(): String = SimpleDateFormat(SERVER_DATE_FORMAT).format(this)


@SuppressLint("SimpleDateFormat")
fun Date.inShortDateFormat(format: String = SHORT_DATE_FORMAT): String = SimpleDateFormat(format).format(this)

@SuppressLint("SimpleDateFormat")
fun Date.inTimeFormat(format: String = TIME_FORMAT): String = SimpleDateFormat(format).format(this)

fun Date.toDateTime(pattern: String, locale: Locale = Locale.getDefault()): String =
    SimpleDateFormat(pattern, locale).format(this)

@SuppressLint("SimpleDateFormat")
fun Date.toServerDateFormat(): String = SimpleDateFormat(SERVER_DATE_FORMAT).format(this)


fun Date.toCalendar(): Calendar =
    Calendar.getInstance().apply { time = this@toCalendar }

fun Date?.toDateOfBirthFormat(): String {
    return this?.toDateTime("LLLL d, yyyy")?.capitalize().orEmpty()
}

fun Date.isSameTime(d: Date?) =
    this.hours == d?.hours
            && this.minutes == d.minutes
            && this.seconds == d.seconds


/**
 * Calendar extensions
 */

fun Calendar.copy() = this.clone() as Calendar

fun Calendar.openDatePicker(
    context: Context,
    callback: (year: Int, month: Int, dayOfMonth: Int) -> Unit,
) {
    DatePickerDialog(
        context, { _, year, month, dayOfMonth ->
            callback.invoke(year, month, dayOfMonth)
        },
        get(Calendar.YEAR),
        get(Calendar.MONTH),
        get(Calendar.DAY_OF_MONTH)
    ).show()
}

fun Calendar.openTimePicker(context: Context, callback: (hourOfDay: Int, minute: Int) -> Unit) {
    TimePickerDialog(
        context, { _, hourOfDay, minute ->
            callback.invoke(hourOfDay, minute)
        },
        get(Calendar.HOUR_OF_DAY),
        get(Calendar.MINUTE), DateFormat.is24HourFormat(context)
    ).show()
}

fun Calendar.toEndDay() {
    set(Calendar.HOUR_OF_DAY, 23)
    set(Calendar.MINUTE, 59)
    set(Calendar.SECOND, 59)
    set(Calendar.MILLISECOND, 999)
}

fun Calendar.isInFuture(): Boolean {
    val todayEnd = Calendar.getInstance().apply { toEndDay() }
    return this.timeInMillis > todayEnd.timeInMillis
}

fun Calendar.startDay() = copy().apply {
    set(Calendar.HOUR_OF_DAY, 0)
    set(Calendar.MINUTE, 0)
    set(Calendar.SECOND, 0)
}

fun Calendar.finishDay() = copy().apply {
    set(Calendar.HOUR_OF_DAY, 23)
    set(Calendar.MINUTE, 59)
    set(Calendar.SECOND, 59)
}

fun Calendar.finishDayMillis(): Long =
    finishDay().timeInMillis

fun Calendar.startDayMillis(): Long =
    startDay().timeInMillis

fun Calendar.toServerDateFormat(): String = time.toServerDateFormat()

fun Calendar.isToday() = isSameDay(Calendar.getInstance())

fun Calendar.isTomorrow() = isSameDay(tomorrow())

fun Calendar.isSameDay(c: Calendar) =
    this.get(Calendar.DAY_OF_MONTH) == c.get(Calendar.DAY_OF_MONTH)
            && this.get(Calendar.MONTH) == c.get(Calendar.MONTH)
            && this.get(Calendar.YEAR) == c.get(Calendar.YEAR)

fun Calendar.isSameTime(c: Calendar, compareSeconds: Boolean = true) =
    this.get(Calendar.HOUR_OF_DAY) == c.get(Calendar.HOUR_OF_DAY)
            && this.get(Calendar.MINUTE) == c.get(Calendar.MINUTE)
            && ((compareSeconds && this.get(Calendar.SECOND) == c.get(Calendar.SECOND))
            || !compareSeconds)

fun Calendar.isSameDateTime(c: Calendar, compareSeconds: Boolean = true) =
    isSameDay(c) && isSameTime(c, compareSeconds)

fun Calendar.afterDate(c: Calendar): Boolean {
    if (this.get(Calendar.YEAR) > c.get(Calendar.YEAR)) {
        return true
    }

    if (this.get(Calendar.YEAR) == c.get(Calendar.YEAR)
        && this.get(Calendar.MONTH) > c.get(Calendar.MONTH)
    ) {
        return true
    }

    return this.get(Calendar.YEAR) == c.get(Calendar.YEAR)
            && this.get(Calendar.MONTH) == c.get(Calendar.MONTH)
            && this.get(Calendar.DAY_OF_MONTH) > c.get(Calendar.DAY_OF_MONTH)
}

fun Calendar.beforeDate(c: Calendar): Boolean {
    if (this.get(Calendar.YEAR) < c.get(Calendar.YEAR)) {
        return true
    }

    if (this.get(Calendar.YEAR) == c.get(Calendar.YEAR)
        && this.get(Calendar.MONTH) < c.get(Calendar.MONTH)
    ) {
        return true
    }

    return this.get(Calendar.YEAR) == c.get(Calendar.YEAR)
            && this.get(Calendar.MONTH) == c.get(Calendar.MONTH)
            && this.get(Calendar.DAY_OF_MONTH) < c.get(Calendar.DAY_OF_MONTH)
}

fun Calendar.afterTime(c: Calendar, compareSeconds: Boolean = true): Boolean {
    if (this.get(Calendar.HOUR_OF_DAY) > c.get(Calendar.HOUR_OF_DAY)) {
        return true
    }

    if (this.get(Calendar.HOUR_OF_DAY) == c.get(Calendar.HOUR_OF_DAY)
        && this.get(Calendar.MINUTE) > c.get(Calendar.MINUTE)
    ) {
        return true
    }

    return this.get(Calendar.HOUR_OF_DAY) == c.get(Calendar.HOUR_OF_DAY)
            && this.get(Calendar.MINUTE) == c.get(Calendar.MINUTE)
            && (!compareSeconds || (compareSeconds && this.get(Calendar.SECOND) > c.get(Calendar.SECOND)))
}

fun Calendar.beforeTime(c: Calendar, compareSeconds: Boolean = true): Boolean {
    if (this.get(Calendar.HOUR_OF_DAY) < c.get(Calendar.HOUR_OF_DAY)) {
        return true
    }

    if (this.get(Calendar.HOUR_OF_DAY) == c.get(Calendar.HOUR_OF_DAY)
        && this.get(Calendar.MINUTE) < c.get(Calendar.MINUTE)
    ) {
        return true
    }

    return this.get(Calendar.HOUR_OF_DAY) == c.get(Calendar.HOUR_OF_DAY)
            && this.get(Calendar.MINUTE) == c.get(Calendar.MINUTE)
            && (!compareSeconds || (compareSeconds && this.get(Calendar.SECOND) < c.get(Calendar.SECOND)))
}

fun Calendar.afterDateTime(c: Calendar, compareSeconds: Boolean = true): Boolean {
    if (afterDate(c) && !isSameDay(c)) {
        return true
    }

    return isSameDay(c) && afterTime(c, compareSeconds)
}

fun Calendar.beforeDateTime(c: Calendar, compareSeconds: Boolean = true): Boolean {
    if (beforeDate(c) && !isSameDay(c)) {
        return true
    }

    return isSameDay(c) && beforeTime(c, compareSeconds)
}

/**
 * Other functions
 */

fun calendar(): Calendar = Calendar.getInstance()

fun calendar(timeInMillis: Long) =
    calendar().apply { this.timeInMillis = timeInMillis }

@SuppressLint("SimpleDateFormat")
fun parseServerDateFormat(date: String): Date? {
    return try {
        SimpleDateFormat(SERVER_DATE_FORMAT)
            .apply { timeZone = TimeZone.getTimeZone("GMT") }
            .parse(date)
    } catch (e: Exception) {
        e.printStackTrace()
        return null
    }
}

fun serverDateToCalendar(date: String): Calendar? {
    val d = parseDate(SERVER_DATE_FORMAT, date) ?: return null
    val c = Calendar.getInstance()
    c.time = d
    return c
}

@SuppressLint("SimpleDateFormat")
fun parseDate(pattern: String, date: String): Date? {
    return try {
        SimpleDateFormat(pattern).parse(date)
    } catch (e: Exception) {
        e.printStackTrace()
        return null
    }
}

fun nowMillis() = System.currentTimeMillis()

fun tomorrow() = Calendar.getInstance().apply {
    timeInMillis = System.currentTimeMillis()
    add(Calendar.DATE, 1)
    time
}

fun todayStartMillis() = Calendar.getInstance().apply {
    timeInMillis = System.currentTimeMillis()
    timeInMillis
    set(Calendar.HOUR_OF_DAY, 0)
    set(Calendar.MINUTE, 0)
    set(Calendar.SECOND, 0)
    set(Calendar.MILLISECOND, 0)
}.timeInMillis

fun todayEndMillis() = Calendar.getInstance().apply {
    timeInMillis = System.currentTimeMillis()
    set(Calendar.HOUR_OF_DAY, 23)
    set(Calendar.MINUTE, 59)
    set(Calendar.SECOND, 59)
    set(Calendar.MILLISECOND, 999)
}.timeInMillis

fun tomorrowStartMillis() = tomorrow().apply {
    set(Calendar.HOUR_OF_DAY, 0)
    set(Calendar.MINUTE, 0)
    set(Calendar.SECOND, 0)
    set(Calendar.MILLISECOND, 0)
}.timeInMillis

fun tomorrowEndMillis() = tomorrow().apply {
    set(Calendar.HOUR_OF_DAY, 23)
    set(Calendar.MINUTE, 59)
    set(Calendar.SECOND, 59)
    set(Calendar.MILLISECOND, 999)
}.timeInMillis

fun firstDayCurrentWeek(): Calendar {
    val calendar = Calendar.getInstance()
    val i = calendar[Calendar.DAY_OF_WEEK] - calendar.firstDayOfWeek
    calendar.add(Calendar.DATE, -i)
    return calendar
}

fun lastDayCurrentWeek(): Calendar {
    val calendar = firstDayCurrentWeek()
    calendar.add(Calendar.DATE, 6)
    return calendar
}

fun firstDayLastWeek(): Calendar {
    val calendar = Calendar.getInstance()
    val i = calendar[Calendar.DAY_OF_WEEK] - calendar.firstDayOfWeek
    calendar.add(Calendar.DATE, -i - 7)
    return calendar
}

fun lastDayLastWeek(): Calendar {
    val calendar = firstDayLastWeek()
    calendar.add(Calendar.DATE, 6)
    return calendar
}

fun now(): Calendar = Calendar.getInstance()

@SuppressLint("SimpleDateFormat", "DefaultLocale")
fun Calendar.inDisplayFormat(): String  =
    if (get(Calendar.DAY_OF_YEAR) != now().get(Calendar.DAY_OF_YEAR)) {
        SimpleDateFormat(DISPLAY_DATE_FORMAT).format(this.time).capitalize()
    } else {
        NOW_CAPS + SimpleDateFormat(DISPLAY_TADAY_DATE_FORMAT).format(this.time)
    }

@SuppressLint("SimpleDateFormat")
fun Calendar.inShortDisplayFormat(): String  =
    if (get(Calendar.DAY_OF_YEAR) != now().get(Calendar.DAY_OF_YEAR)) {
        SimpleDateFormat(DISPLAY_TADAY_SHORT_DATE_FORMAT).format(this.time)
    } else {
        NOW
    }

