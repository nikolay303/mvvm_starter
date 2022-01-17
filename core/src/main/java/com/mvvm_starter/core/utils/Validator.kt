package com.mvvm_starter.core.utils

import android.content.Context
import com.mvvm_starter.core.R
import java.util.*


open class Validator(open val context: Context) {
    companion object {
        private const val SSN_LENGTH = 9
        private const val MIN_AGE = 18
    }


    fun isValidFirstName(firstName: String, onError: (String) -> Unit): Boolean {
        if (!firstName.isValidName(MIN_LENGTH_FIRST_NAME, MAX_LENGTH_FIRST_NAME)) {
            val error = if (firstName.length <= MIN_LENGTH_FIRST_NAME) {
                context.getString(R.string.error_enter_first_name)
            } else {
                context.getString(R.string.format_error_name_length, MAX_LENGTH_FIRST_NAME)
            }
            onError.invoke(error)
            return false
        }
        return true
    }

    fun isValidLastName(lastName: String, onError: (String) -> Unit): Boolean {
        if (!lastName.isValidName(MIN_LENGTH_LAST_NAME, MAX_LENGTH_LAST_NAME)) {
            val error = if (lastName.length <= MIN_LENGTH_LAST_NAME) {
                context.getString(R.string.error_enter_last_name)
            } else {
                context.getString(R.string.format_error_name_length, MAX_LENGTH_LAST_NAME)
            }
            onError.invoke(error)
            return false
        }
        return true
    }

    fun isValidPhone(phone: String, onError: (String) -> Unit): Boolean {
        if (!phone.isValidPhone()) {
            val error = if (phone.isBlank()) {
                context.getString(R.string.error_empty_phone_number)
            } else {
                context.getString(R.string.error_phone_must_be_10_digits)
            }
            onError.invoke(error)
            return false
        }
        return true
    }

    fun isValidEmail(email: String, onError: (String) -> Unit): Boolean {
        if (!email.isValidEmail()) {
            val error = if (email.isBlank()) {
                context.getString(R.string.error_empty_email)
            } else {
                context.getString(R.string.error_invalid_email)
            }
            onError.invoke(error)
            return false
        }
        return true
    }

    fun isValidPassword(password: String, onError: (String) -> Unit): Boolean {
        if (!password.isValidPassword()) {
            val error = when {
                password.isBlank() -> context.getString(R.string.error_cannot_be_blank)
                password.length < MIN_LENGTH_PASSWORD -> {
                    context.getString(
                        R.string.format_error_password_min_length,
                        MIN_LENGTH_PASSWORD
                    )
                }
                else -> context.getString(
                    R.string.format_error_password_length,
                    MAX_LENGTH_PASSWORD
                )
            }
            onError.invoke(error)
            return false
        }
        return true
    }

    fun isValidDateOfBirth(date: Calendar?, onError: (String) -> Unit): Boolean {
        if (date == null) {
            onError.invoke(context.getString(R.string.error_cannot_be_blank))
            return false
        }

        if (!date.isValidDateOfBirth(MIN_AGE)) {
            onError.invoke(context.getString(R.string.error_dob_you_must_be_least_18_to_continue))
            return false
        }
        return true
    }

    fun isValidSSN(ssn: String, onError: (String) -> Unit): Boolean {
        if (ssn.isBlank() || ssn.length < SSN_LENGTH) {
            val error = when {
                ssn.isBlank() -> context.getString(R.string.error_cannot_be_blank)
                ssn.length < SSN_LENGTH -> context.getString(R.string.error_ssn_must_be_9_digits)
                else -> ""
            }
            onError.invoke(error)
            return false
        }
        return true
    }

    fun isValidMaritalStatus(maritalStatus: String, onError: (String) -> Unit): Boolean {
        if (maritalStatus.isBlank()) {
            val error = context.getString(R.string.error_cannot_be_blank)
            onError.invoke(error)
            return false
        }
        return true
    }

    fun isValidAddress(address: String, onError: (String) -> Unit): Boolean {
        if (address.isBlank()) {
            val error = context.getString(R.string.error_cannot_be_blank)
            onError.invoke(error)
            return false
        }
        return true
    }
}