package com.mvvm_starter.core.utils

import android.animation.Animator
import android.animation.TimeInterpolator
import android.annotation.SuppressLint
import android.content.Context
import android.content.res.ColorStateList
import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.drawable.Drawable
import android.text.Editable
import android.text.InputFilter
import android.text.TextWatcher
import android.util.Range
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewTreeObserver
import android.widget.*
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat
import androidx.viewpager.widget.ViewPager
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.tabs.TabLayout
import com.google.android.material.textfield.TextInputLayout
import kotlin.math.abs


fun AppBarLayout.setupHideableViewByOffset(view: View) {
    addOnOffsetChangedListener(AppBarLayout.OnOffsetChangedListener { _, offset ->
        view.alpha = 1f - abs(offset) / totalScrollRange.toFloat()
    })
}

fun AppBarLayout.setOnOffsetChanged(action: (Int) -> Unit) {
    addOnOffsetChangedListener(AppBarLayout.OnOffsetChangedListener { _, offset ->
        action.invoke(offset)
    })
}

@SuppressLint("UseCompatLoadingForDrawables")
fun View.getResDrawable(@DrawableRes resId: Int): Drawable? {
    return try {
        context?.getDrawable(resId)
    } catch (e: Resources.NotFoundException) {
        null
    }
}

fun TextView.isEllipsized(): Boolean {
    if (layout != null) {
        val lines: Int = layout.lineCount
        if (lines > 0) {
            val ellipsisCount: Int = layout.getEllipsisCount(lines - 1)
            return ellipsisCount > 0
        }
    }
    return false
}

fun TextView.setMaxLength(length: Int) {
    filters = arrayOf(InputFilter.LengthFilter(length))
}

fun TextView.strikeThruText() {
    paintFlags = paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
}

fun TextView.underline() {
    paintFlags = paintFlags or Paint.UNDERLINE_TEXT_FLAG
}

fun TextView.afterTextChanged(action: (text: String) -> Unit) {
    addTextChangedListener(object : TextWatcher {
        override fun afterTextChanged(s: Editable?) {
            action(s?.toString() ?: "")
        }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        }
    })
}

fun afterTextChangeListener(action: (text: String) -> Unit): TextWatcher {
    return object : TextWatcher {
        override fun afterTextChanged(s: Editable?) {
            action(s?.toString().orEmpty())
        }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
    }
}

fun TextInputLayout.clearError() {
    error = null
}

fun View.setMargins(left: Int, top: Int, right: Int, bottom: Int) {
    val lp = layoutParams as ViewGroup.MarginLayoutParams
    lp.setMargins(left, top, right, bottom)
    requestLayout()
}

fun View.setMargin(margin: Int) {
    val lp = layoutParams as ViewGroup.MarginLayoutParams
    lp.setMargins(margin, margin, margin, margin)
    requestLayout()
}

fun View.setStartMargin(margin: Int) {
    val lp = layoutParams as ViewGroup.MarginLayoutParams
    lp.marginStart = margin
    requestLayout()
}

fun View.setEndMargin(margin: Int) {
    val lp = layoutParams as ViewGroup.MarginLayoutParams
    lp.marginEnd = margin
    requestLayout()
}

fun View.setBottomMargin(margin: Int) {
    val lp = layoutParams as ViewGroup.MarginLayoutParams
    lp.bottomMargin = margin
    requestLayout()
}

fun View.setTopMargin(margin: Int) {
    val lp = layoutParams as ViewGroup.MarginLayoutParams
    lp.topMargin = margin
    requestLayout()
}

fun View.setVerticalMargins(margin: Int) {
    val lp = layoutParams as ViewGroup.MarginLayoutParams
    lp.topMargin = margin
    lp.bottomMargin = margin
    requestLayout()
}

fun View.setHorizontalMargins(margin: Int) {
    val lp = layoutParams as ViewGroup.MarginLayoutParams
    lp.marginStart = margin
    lp.marginEnd = margin
    requestLayout()
}

fun ViewPager.doOnPageSelected(action: (position: Int) -> Unit) {
    addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
        override fun onPageScrollStateChanged(state: Int) {

        }

        override fun onPageScrolled(
            position: Int,
            positionOffset: Float,
            positionOffsetPixels: Int,
        ) {
        }

        override fun onPageSelected(position: Int) {
            action.invoke(position)
        }
    })
}

fun TabLayout.doOnTabSelected(action: (position: Int) -> Unit) {
    addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
        override fun onTabReselected(tab: TabLayout.Tab) {
        }

        override fun onTabUnselected(tab: TabLayout.Tab) {
        }

        override fun onTabSelected(tab: TabLayout.Tab) {
            action.invoke(tab.position)
        }
    })
}

fun ViewPager.doOnPageScrolled(action: (position: Int, positionOffset: Float, positionOffsetPixels: Int) -> Unit) {
    addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
        override fun onPageScrollStateChanged(state: Int) {

        }

        override fun onPageScrolled(
            position: Int,
            positionOffset: Float,
            positionOffsetPixels: Int,
        ) {
            action(position, positionOffset, positionOffsetPixels)
        }

        override fun onPageSelected(position: Int) {
        }
    })
}

fun View.visible() {
    visibility = View.VISIBLE
}

fun View.isVisible() =
    visibility == View.VISIBLE

fun View.invisible() {
    visibility = View.INVISIBLE
}

fun View.gone() {
    visibility = View.GONE
}

fun View.visibleIf(condition: Boolean, _else: Int = View.GONE) {
    if (condition) {
        visible()
    } else {
        visibility = _else
    }
}

fun View.setDelayedOnClickListener(onClick: () -> Unit, delay: Long = 1000) =
    setOnClickListener(object : View.OnClickListener {
        private var lastClickTime = 0L

        override fun onClick(p0: View?) {
            if (System.currentTimeMillis() - lastClickTime > delay) {
                lastClickTime = System.currentTimeMillis()
                onClick()
            }
        }
    })

fun View.doOnGlobalLayoutOnce(action: () -> Unit) {
    viewTreeObserver.addOnGlobalLayoutListener(object : ViewTreeObserver.OnGlobalLayoutListener {
        override fun onGlobalLayout() {
            action.invoke()
            viewTreeObserver.removeOnGlobalLayoutListener(this)
        }
    })
}

fun ViewGroup.inflate(layoutRes: Int) =
    LayoutInflater.from(context).inflate(layoutRes, this, false)

fun Context.colorCompat(colorResId: Int) =
    ContextCompat.getColor(this, colorResId)

fun Context.tintList(colorResId: Int) =
    ColorStateList.valueOf(colorCompat(colorResId))

fun ImageView.setImageTint(color: Int) {
    imageTintList = ColorStateList.valueOf(color)
}

fun ImageView.setImageTintResId(@ColorRes colorRes: Int) {
    imageTintList = ColorStateList.valueOf(context.getColor(colorRes))
}

fun Animator.withInterpolator(interpolator: TimeInterpolator): Animator {
    setInterpolator(interpolator)
    return this
}

fun Animator.doOnEnd(action: () -> Unit): Animator {
    addListener(object : Animator.AnimatorListener {
        override fun onAnimationRepeat(animation: Animator?) {

        }

        override fun onAnimationEnd(animation: Animator?) {
            action.invoke()
        }

        override fun onAnimationCancel(animation: Animator?) {
        }

        override fun onAnimationStart(animation: Animator?) {
        }
    })

    return this
}

fun Spinner.doOnItemSelected(action: (position: Int) -> Unit) {
    onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
        override fun onNothingSelected(parent: AdapterView<*>?) {
        }

        override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
            action.invoke(position)
        }
    }
}

fun View.toBitmap(): Bitmap {
    val bitmap = Bitmap.createBitmap(measuredWidth, measuredHeight, Bitmap.Config.ARGB_8888)
    val canvas = Canvas(bitmap)
    draw(canvas)
    return bitmap
}

fun EditText.disable() {
    isFocusable = false
    isEnabled = false
}

fun View.show(duration: Long = 200, onShow: () -> Unit = {}) {
    if (!isVisible()) {
        animation?.cancel()
        alpha = 0f
        visible()
        animate()
            .alpha(1f)
            .setDuration(duration)
            .withEndAction(onShow)
            .start()
    }
}

fun View.hide(duration: Long = 200, onHide: () -> Unit = { gone() }) {
    animation?.cancel()
    animate()
        .alpha(0f)
        .setDuration(duration)
        .withEndAction(onHide)
        .start()
}

fun View.animVisibleIf(condition: Boolean, another: Int = View.GONE, duration: Long = 200) {
    if (condition) {
        show(duration)
    } else {
        hide(duration) { if (another == View.GONE) gone() else invisible() }
    }
}

fun TextView.setDrawableStart(@DrawableRes resId: Int) {
    setCompoundDrawablesWithIntrinsicBounds(resId, 0, 0, 0)
}

fun View.getBoundsOnScreen(): Pair<Range<Int>, Range<Int>> {
    val coordinates = IntArray(2)
    getLocationOnScreen(coordinates)

    val startX = coordinates[0]
    val endX = startX + measuredWidth
    val startY = coordinates[1]
    val endY = startY + measuredHeight

    return Pair(Range(startX, endX), Range(startY, endY))
}

fun View.matchesBounds(view: View): Boolean {
    val currentBounds = getBoundsOnScreen()
    val viewBounds = view.getBoundsOnScreen()
    return try {
        val intersectX = currentBounds.first.intersect(viewBounds.first)
        val intersectY = currentBounds.second.intersect(viewBounds.second)
        (intersectX.upper - intersectX.lower) > 0 && (intersectY.upper - intersectY.lower) > 0
    } catch (e: IllegalArgumentException) {
        e.printStackTrace()
        false
    }
}