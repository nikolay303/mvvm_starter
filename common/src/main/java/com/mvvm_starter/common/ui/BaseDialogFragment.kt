package com.mvvm_starter.common.ui

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatDialogFragment


abstract class BaseDialogFragment : AppCompatDialogFragment() {

    abstract val layoutResId: Int

    abstract fun setupView(view: View, savedInstanceState: Bundle?)

    private var progressDialog: ProgressDialogFragment? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(layoutResId, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView(view, savedInstanceState)
    }

    override fun onResume() {
        super.onResume()
        if (isFullscreen()) {
            setupFullscreen()
        }
    }

    open fun isFullscreen(): Boolean {
        return false
    }

    private fun setupFullscreen() {
        val window: Window? = dialog?.window
        window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        window?.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.MATCH_PARENT
        )
        window?.setGravity(Gravity.CENTER)
    }

    protected open fun showProgress(show: Boolean) {
        if (show) showProgressDialog() else hideProgressDialog()
    }

    protected open fun showProgressDialog() {
        try {
            if (progressDialog == null || progressDialog?.isAdded == false) {
                progressDialog = ProgressDialogFragment()
                progressDialog?.show(childFragmentManager, null)
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
}