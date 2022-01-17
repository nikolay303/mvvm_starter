package com.mvvm_starter.core.ui

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import androidx.viewpager.widget.ViewPager


class SwipeableViewPager : ViewPager {
    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)

    var swipeable: Boolean = true

    override fun onTouchEvent(ev: MotionEvent?): Boolean {
        if (!swipeable) {
            return false
        }
        return super.onTouchEvent(ev)
    }

    override fun onInterceptTouchEvent(ev: MotionEvent?): Boolean {
        if (!swipeable) {
            return false
        }
        return super.onInterceptTouchEvent(ev)
    }
}