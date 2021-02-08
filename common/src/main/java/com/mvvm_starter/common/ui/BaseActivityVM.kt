package com.mvvm_starter.common.ui

import android.os.Bundle
import org.jetbrains.anko.toast

abstract class BaseActivityVM<VM : BaseViewModel> : BaseActivity() {

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