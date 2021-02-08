package com.mvvm_starter.core.utils

import android.app.Activity
import android.os.Bundle
import androidx.fragment.app.Fragment


inline fun <reified T : Any> Activity.extra(
    key: String,
    defaultValue: T? = null
): T {
    return intent.extras?.get(key, defaultValue) as T
}

inline fun <reified T : Any> Fragment.argument(
    key: String,
    defaultValue: T? = null
): T {
    return arguments?.get(key, defaultValue) as T
}

inline fun <reified T> Bundle.get(
    key: String,
    defaultValue: T? = null
): T {
    val result = get(key) ?: defaultValue
    if (result != null && result !is T) {
        throw ClassCastException("Property $key has different class type")
    }
    return result as T
}