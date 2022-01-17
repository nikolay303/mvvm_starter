package com.mvvm_starter.common.ui

import android.graphics.Rect
import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import com.mvvm_starter.core.utils.clearFocusFromFocusedEditText
import com.mvvm_starter.core.utils.listenTouchesToHideKeyboard


abstract class BaseActivity<T : ViewBinding>(@LayoutRes layoutResId: Int)  : AppCompatActivity(layoutResId) {

    protected abstract val binding: T

    private var progressDialog: ProgressDialogFragment? = null

    protected abstract fun setupView(savedInstanceState: Bundle?)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        if (hideKeyboardByClickOutside()) {
            listenTouchesToHideKeyboard(this)
            onStateKeyboardListener(onHide = { clearFocusFromFocusedEditText(binding.root) })
        }
        setupView(savedInstanceState)
    }

    protected open fun hideKeyboardByClickOutside(): Boolean {
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
        val root = binding.root
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
                clearFocusFromFocusedEditText(binding.root)
                onHide.invoke()
                false
            }
        }
    }
}