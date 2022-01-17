package com.mvvm_starter.common.ui

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.viewbinding.ViewBinding
import com.mvvm_starter.core.utils.toast

abstract class BaseActivityVM<T : ViewBinding, VM : BaseViewModel>(@LayoutRes layoutResId: Int) :
    BaseActivity<T>(layoutResId) {

    protected abstract val viewModel: VM//by activityViewModels<VM>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.message.observe(this, this::handleHttpError)
        viewModel.httpError.observe(this, this::handleHttpError)
        viewModel.process.observe(this, this::showProgress)
    }

    private fun handleHttpError(message: String) {
        toast(message)
        hideProgressDialog()
    }
}