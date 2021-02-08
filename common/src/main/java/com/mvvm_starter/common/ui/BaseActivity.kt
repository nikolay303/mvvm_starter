package com.mvvm_starter.common.ui

import android.graphics.Rect
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.mvvm_starter.core.utils.clearFocusFromFocusedEditText
import com.mvvm_starter.core.utils.listenTouchesToHideKeyboard
import org.jetbrains.anko.contentView


abstract class BaseActivity : AppCompatActivity() {

    protected abstract val layoutResId: Int

    private var progressDialog: ProgressDialogFragment? = null

    protected abstract fun setupView(savedInstanceState: Bundle?)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutResId)
        if (hideKeyboardByClickOutside()) {
            listenTouchesToHideKeyboard(this)
            onStateKeyboardListener(onHide = { contentView?.let(::clearFocusFromFocusedEditText) })
        }
        setupView(savedInstanceState)
    }

    protected open fun hideKeyboardByClickOutside(): Boolean {
        return true
    }

    protected open fun shouldCheckUserVerification(): Boolean {
        return true
    }

    protected open fun showProgress(show: Boolean) {
        if (show) showProgressDialog() else hideProgressDialog()
    }

    protected open fun showProgressDialog(title: String = "") {
        try {
            if (progressDialog == null || progressDialog?.isAdded == false) {
                progressDialog = ProgressDialogFragment()
                progressDialog?.show(supportFragmentManager, null)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    protected open fun hideProgressDialog() {
        try {
            progressDialog?.dismiss()
            progressDialog = null
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    protected fun onStateKeyboardListener(onShow: () -> Unit = {}, onHide: () -> Unit = {}) {
        val root = contentView ?: return
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
                contentView?.let(::clearFocusFromFocusedEditText)
                onHide.invoke()
                false
            }
        }
    }
}