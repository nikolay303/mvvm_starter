package com.android.PACKAGE


object Config {

    private const val TEST_API_URL = ""
    private const val PROD_API_URL = ""

    val baseUrl = if (BuildConfig.DEBUG) TEST_API_URL else PROD_API_URL
}