package com.mvvm_starter.core.utils

import android.content.res.Resources
import android.util.TypedValue


fun Number.spToPx() = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, this.toFloat(), Resources.getSystem().displayMetrics)

fun Number.dpToPx() = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, this.toFloat(), Resources.getSystem().displayMetrics)

fun Number.spToPxInt() = spToPx().toInt()

fun Number.dpToPxInt() = dpToPx().toInt()