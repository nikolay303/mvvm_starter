package com.mvvm_starter.core.utils

import androidx.viewpager2.widget.ViewPager2

fun ViewPager2.doOnPageScrolled(
    action: (position: Int, positionOffset: Float, positionOffsetPixels: Int) -> Unit
) {
    registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
        override fun onPageScrolled(
            position: Int,
            positionOffset: Float,
            positionOffsetPixels: Int
        ) {
            action(position, positionOffset, positionOffsetPixels)
        }
    })
}

fun ViewPager2.doOnPageSelected(action: (position: Int) -> Unit) {
    registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
        override fun onPageSelected(position: Int) {
            action.invoke(position)
        }
    })
}