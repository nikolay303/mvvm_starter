package com.mvvm_starter.core.utils

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.graphics.Rect
import android.os.Build
import android.util.TypedValue
import android.view.View
import android.view.ViewGroup
import android.view.ViewTreeObserver
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import timber.log.Timber


interface OnKeyboardVisibilityListener {
    fun onKeyboardShown(keyboardHeight: Int)
    fun onKeyboardHidden()
}

fun prepareKeyboardVisibilityListener(
    parentView: View?,
    keyboardVisibilityListener: OnKeyboardVisibilityListener,
) = object : ViewTreeObserver.OnGlobalLayoutListener {

    private var lastHeightDiff: Int = 0
    var alreadyOpen: Boolean = false
    val defaultKeyboardHeightDP = 100
    val EstimatedKeyboardDP =
        defaultKeyboardHeightDP + if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) 48 else 0
    val rect = Rect()

    override fun onGlobalLayout() {
        if (parentView == null) {
            return
        }
        val estimatedKeyboardHeight = TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            EstimatedKeyboardDP.toFloat(),
            parentView.resources?.displayMetrics
        ).toInt()

        parentView.getWindowVisibleDisplayFrame(rect)
        val heightDiff = parentView.height - (rect.bottom - rect.top)
        if (heightDiff >= estimatedKeyboardHeight && alreadyOpen && heightDiff != lastHeightDiff) {
            alreadyOpen = false
        }
        this.lastHeightDiff = heightDiff
        val isShown = heightDiff >= estimatedKeyboardHeight


        if (isShown == alreadyOpen) {
//            keyboardVisibilityListener.onKeyboardShown(heightDiff)
            Timber.i("Ignoring global layout change...")
            return
        }

        if (isShown) {
            keyboardVisibilityListener.onKeyboardShown(heightDiff)
        } else {
            keyboardVisibilityListener.onKeyboardHidden()
        }
        alreadyOpen = isShown
    }
}

fun hideSoftKeyboard(activity: Activity?) {
    if (activity == null) {
        return
    }
    val inputMethodManager = activity.getSystemService(
        Activity.INPUT_METHOD_SERVICE
    ) as InputMethodManager
    inputMethodManager.hideSoftInputFromWindow(
        activity.currentFocus?.windowToken, 0
    )
}

fun listenTouchesToHideKeyboard(activity: Activity) {
    tryToHideKeyboard(activity, activity.window.decorView)
}

fun listenTouchesToHideKeyboard(activity: Activity, view: View) {
    tryToHideKeyboard(activity, view)
}

fun clearFocusFromFocusedEditText(view: View) {
    if (view is ViewGroup && view.isAttachedToWindow) {
        view.focusedChild?.clearFocus()
    }
}

fun onStateKeyboardListener(activity: Activity, onShow: () -> Unit = {}, onHide: () -> Unit = {}) {
    val root = activity.window?.decorView ?: return
    var isKeyboardShown = false
    root.viewTreeObserver.addOnGlobalLayoutListener {
        val r = Rect()
        root.getWindowVisibleDisplayFrame(r)
        val heightDiff = root.rootView.height - r.bottom
        val isAlreadyKeyboardShown = heightDiff > 200
        if (isAlreadyKeyboardShown == isKeyboardShown) {
            return@addOnGlobalLayoutListener
        }

        isKeyboardShown = if (isAlreadyKeyboardShown) {
            onShow.invoke()
            true
        } else {
            onHide.invoke()
            false
        }
    }
}

@SuppressLint("ClickableViewAccessibility")
fun tryToHideKeyboard(activity: Activity, view: View) {
    // Set up touch listener for non-text box views to hide keyboard.
    if (view !is EditText) {
        view.setOnTouchListener { _, _ ->
            hideSoftKeyboard(activity)
            clearFocusFromFocusedEditText(view)
            false
        }
    }

    //If a layout container, iterate over children and seed recursion.
    if (view is ViewGroup) {
        (0 until view.childCount)
            .map { view.getChildAt(it) }
            .forEach { tryToHideKeyboard(activity, it) }
    }
}

fun showKeyboard(context: Context?, editText: EditText) {
    editText.requestFocus()
    editText.postDelayed({
        val keyboard = context?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        keyboard.showSoftInput(editText, 0)
    }, 200)
}