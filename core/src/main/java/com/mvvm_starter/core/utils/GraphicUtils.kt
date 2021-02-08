package com.mvvm_starter.core.utils

import android.content.res.Resources
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.graphics.RectF


val screenWidth get() = Resources.getSystem().displayMetrics.widthPixels

val screenHeight get() = Resources.getSystem().displayMetrics.heightPixels

val statusBarHeight: Int
    get() {
        val myResources = Resources.getSystem()
        val idStatusBarHeight = myResources.getIdentifier("status_bar_height", "dimen", "android")
        return myResources.getDimensionPixelSize(idStatusBarHeight)
    }


fun Paint.withStyle(style: Paint.Style): Paint {
    this.style = style
    return this
}

fun Paint.withColor(color: Int): Paint {
    this.color = color
    return this
}

fun Paint.withStrokeWidth(strokeWidth: Float): Paint {
    this.strokeWidth = strokeWidth
    return this
}

fun Paint.getTextBounds(text: String): Rect {
    val rect = Rect()
    getTextBounds(text, 0, text.length, rect)
    return rect
}

fun Paint.getTextHeight(text: String): Int {
    val rect = getTextBounds(text)
    return rect.height()
}

fun Paint.getTextWidth(text: String): Int {
    return measureText(text).toInt()
}

fun RectF.copyShrinked(value: Float) =
    RectF(
        this.left + value,
        this.top + value,
        this.right - value,
        this.bottom - value
    )

fun Rect.shrink(value: Int) {
    this.left += value
    this.top += value
    this.right -= value
    this.bottom -= value
}

fun Int.toSemiTransparent(alpha: Int) =
    Color.argb(alpha, Color.red(this), Color.green(this), Color.blue(this))