package com.mvvm_starter.common.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import timber.log.Timber


abstract class BaseFragment : Fragment() {

    abstract val layoutResId: Int
    abstract fun setupView(savedInstanceState: Bundle?)

    private var progressDialog: ProgressDialogFragment? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        return inflater.inflate(layoutResId, container, false)
    }

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
                progressDialog?.show(requireFragmentManager(), null)
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