package com.mvvm_starter.core.ui

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.viewpager.widget.ViewPager


class WrappingViewPager : ViewPager {

    constructor(context: Context?) : super(context!!)
    constructor(context: Context?, attrs: AttributeSet?) : super(context!!, attrs)

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        var h = heightMeasureSpec
        val mode = MeasureSpec.getMode(h)
        // Unspecified means that the ViewPager is in a ScrollView WRAP_CONTENT.
        // At Most means that the ViewPager is not in a ScrollView WRAP_CONTENT.
        if (mode == MeasureSpec.UNSPECIFIED || mode == MeasureSpec.AT_MOST) { // super has to be called in the beginning so the child views can be initialized.
            super.onMeasure(widthMeasureSpec, h)
            var height = 0
            for (i in 0 until childCount) {
                val child: View = getChildAt(i)
                child.measure(widthMeasureSpec, MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED))
                val h: Int = child.measuredHeight
                if (h > height) height = h + paddingTop + paddingBottom
            }
            h = MeasureSpec.makeMeasureSpec(height, MeasureSpec.EXACTLY)
        }
        // super has to be called again so the new specs are treated as exact measurements
        super.onMeasure(widthMeasureSpec, h)
    }
}