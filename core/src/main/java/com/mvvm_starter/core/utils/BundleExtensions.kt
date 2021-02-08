package com.mvvm_starter.core.utils

import android.os.Bundle
import android.os.Parcelable
import java.io.Serializable


fun Bundle.withString(key: String, value: String): Bundle {
    putString(key, value)
    return this
}

fun Bundle.withBool(key: String, value: Boolean): Bundle {
    putBoolean(key, value)
    return this
}

fun Bundle.withInt(key: String, value: Int): Bundle {
    putInt(key, value)
    return this
}

fun Bundle.withLong(key: String, value: Long): Bundle {
    putLong(key, value)
    return this
}

fun Bundle.withFloat(key: String, value: Float): Bundle {
    putFloat(key, value)
    return this
}

fun Bundle.withDouble(key: String, value: Double): Bundle {
    putDouble(key, value)
    return this
}

fun Bundle.withParcelable(key: String, value: Parcelable): Bundle {
    putParcelable(key, value)
    return this
}

fun Bundle.withSerializable(key: String, value: Serializable): Bundle {
    putSerializable(key, value)
    return this
}

fun Bundle.withStringArray(key: String, value: Array<String>): Bundle {
    putStringArray(key, value)
    return this
}

fun Bundle.toMap(): Map<String, Any>? {
    val map = hashMapOf<String, Any>()
    val keys = keySet()
    for (key in keys) {
        get(key)?.let { map[key] = it }
    }
    return map
}