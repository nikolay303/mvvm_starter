package com.mvvm_starter.core.utils

import android.telephony.PhoneNumberUtils
import android.util.Patterns
import android.util.Range
import java.util.*


const val MIN_LENGTH_PASSWORD = 6
const val MAX_LENGTH_PASSWORD = 64
const val USA_PHONE_NUMBER_LENGTH = 12
const val USA_PHONE_NUMBER_LENGTH_WITHOUT_CODE = 10
const val MIN_LENGTH_FIRST_NAME = 2
const val MIN_LENGTH_LAST_NAME = 2
const val MAX_LENGTH_FIRST_NAME = 35
const val MAX_LENGTH_LAST_NAME = 50

fun String.normalizePhoneNumber(prefix: String = USA_CODE_PHONE_NUMBER): String {
    return "$prefix${PhoneNumberUtils.normalizeNumber(this)}"
}

fun String?.isValidName(minLength: Int, maxLength: Int): Boolean {
    return !isNullOrBlank() && this.length in Range(minLength, maxLength)
}

fun String?.isValidPhone(inWorldFormat: Boolean = true): Boolean {
    return !isNullOrBlank() && length == if (inWorldFormat) USA_PHONE_NUMBER_LENGTH else USA_PHONE_NUMBER_LENGTH_WITHOUT_CODE
}

fun String?.isValidEmail(): Boolean {
    return !isNullOrBlank() && Patterns.EMAIL_ADDRESS.matcher(this).matches()
}

fun String?.isValidPassword(): Boolean {
    return !isNullOrBlank() && this.length in Range(MIN_LENGTH_PASSWORD, MAX_LENGTH_PASSWORD)
}

fun Calendar?.isValidDateOfBirth(minYears: Int): Boolean {
    val dobYear = this?.get(Calendar.YEAR) ?: return false
    val dobMonth = this.get(Calendar.MONTH)
    val dobDay = this.get(Calendar.DAY_OF_MONTH)

    val now = Calendar.getInstance()
    val nowYear = now.get(Calendar.YEAR)
    val nowMonth = now.get(Calendar.MONTH)
    val nowDay = now.get(Calendar.DAY_OF_MONTH)

    if (nowYear < dobYear || nowYear - dobYear < minYears ||
        (nowYear == dobYear && nowMonth < dobMonth) ||
        (nowYear - dobYear <= minYears && nowMonth <= dobMonth && nowDay < dobDay)
    ) {
        return false
    }
    return true
}