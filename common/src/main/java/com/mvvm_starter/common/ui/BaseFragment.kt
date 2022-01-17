package com.mvvm_starter.common.ui

import android.os.Bundle
import android.view.View
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import timber.log.Timber


abstract class BaseFragment<T : ViewBinding>(@LayoutRes layoutResId: Int) : Fragment(layoutResId) {

    protected abstract val binding: T
    abstract fun setupView(savedInstanceState: Bundle?)

    private var progressDialog: ProgressDialogFragment? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView(savedInstanceState)
    }

    protected open fun showProgress(show: Boolean) {
        if (show) showProgressDialog() else hideProgressDialog()
    }

    protected open fun showProgressDialog() {
        try {
            if (progressDialog == null || progressDialog?.isVisible == false) {
                progressDialog = ProgressDialogFragment()
                progressDialog?.show(parentFragmentManager, null)
            }
        } catch (e: Exception) {
            Timber.e(e)
        }
    }

    protected open fun hideProgressDialog() {
        try {
            progressDialog?.dismiss()
        } catch (e: Exception) {
            Timber.e(e)
        }
    }
}