package com.mvvm_starter.core.utils

import android.telephony.PhoneNumberUtils
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.util.*
import java.util.regex.Pattern


const val REGEX_SQUARE_BRASKETS = "\\[(.*?)\\]"
const val REGEX_ROUDED_BRASKETS = "\\((.*?)\\)"
private const val REGEX_NORMALIZE_EMAIL = "(\\+.*)@"
const val LENGTH_USA_PHONE_NUMBER = 12
const val USA_CODE_PHONE_NUMBER = "+1"
const val INPUT_MASK_USA_PHONE_NUMBER = "([000]) [000]-[0000]"

fun String.normalizePhone(): String =
        PhoneNumberUtils.normalizeNumber(this)

fun String.normalizeEmail(): String {
    return REGEX_NORMALIZE_EMAIL.toRegex().replace(this, "@")
}

fun Float.toNormalText(pattern: String = "###,###.00"): String {
    val separator = DecimalFormatSymbols()
    separator.decimalSeparator = '.'
    separator.groupingSeparator = ','
    val decimalFormat = DecimalFormat(pattern, separator)
    return decimalFormat.format(this)
}

fun Float.toFormatText(pattern: String): String {
    return String.format(Locale.US, pattern, this)
}

//FOR USA
fun String.phoneText(): String {
    val phone = if (this.startsWith(USA_CODE_PHONE_NUMBER)) {
        this.replaceFirst(USA_CODE_PHONE_NUMBER, "")
    } else {
        this
    }
    val regex = Regex("^(\\d{3})(\\d{3})(\\d{4})")
    return regex.replace(phone, "$USA_CODE_PHONE_NUMBER (\$1) \$2-\$3")
}

fun String.phoneTextWithoutCode(): String {
    val regex = Regex("^(\\d{3})(\\d{3})(\\d{4})")
    return regex.replace(this.replaceFirst(USA_CODE_PHONE_NUMBER, ""), "(\$1) \$2-\$3")
}

fun String.clearPhoneUSACode(): String {
    return if (this.startsWith(USA_CODE_PHONE_NUMBER)) {
        this.replaceFirst(USA_CODE_PHONE_NUMBER, "")
    } else {
        this
    }
}

fun String.appendUSAPhoneCode(): String {
    return "$USA_CODE_PHONE_NUMBER$this"
}

fun String.simplePhoneTextWithoutCode(): String {
    val regex = Regex("^(\\d{3})(\\d{3})(\\d{4})")
    return regex.replace(this.replaceFirst(USA_CODE_PHONE_NUMBER, ""), "\$1-\$2-\$3")
}

fun String.firstSubstringNumberWithLength(length: Int): String? {
    val matcher = Pattern.compile("((\\D|^)\\d{$length}(\\D|\$))").matcher(this)
    val foundStrings = mutableListOf<String>()
    while (matcher.find()) {
        foundStrings.add(matcher.group().replace(Regex("\\D"), ""))
    }
    return foundStrings.firstOrNull()
}

fun String.substringByRegex(regex: String): String {
    val pattern = Pattern.compile(regex)
    val matcher = pattern.matcher(this)
    if (matcher.find()) {
        return matcher.group(1).orEmpty()
    }
    return ""
}