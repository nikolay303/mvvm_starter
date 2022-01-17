package com.android.PACKAGE

import android.content.SharedPreferences

class Preferences(private val preferences: SharedPreferences) {
    companion object {
        private const val ACCESS_TOKEN = "access_token"
    }

    var accessToken: String
        set(value) = preferences.edit().putString(ACCESS_TOKEN, value).apply()
        get() = preferences.getString(ACCESS_TOKEN, "").orEmpty()



    fun clear() {
    }
}