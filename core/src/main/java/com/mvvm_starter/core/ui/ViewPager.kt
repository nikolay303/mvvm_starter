package com.mvvm_starter.core.ui

import android.content.Context
import android.util.AttributeSet
import androidx.viewpager.widget.ViewPager


class ViewPager : ViewPager {
    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)

    private var mMaxHeight = 0

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        var hms = heightMeasureSpec
        for (i in 0 until childCount) {
            val child = getChildAt(i)
            child.measure(widthMeasureSpec, MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED))
            val h = child.measuredHeight
            if (h > mMaxHeight) {
                mMaxHeight = h
            }
        }
        if (mMaxHeight != 0) {
            hms = MeasureSpec.makeMeasureSpec(mMaxHeight, MeasureSpec.EXACTLY)
        }
        super.onMeasure(widthMeasureSpec, hms)
    }
}