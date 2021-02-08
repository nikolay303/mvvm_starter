package com.mvvm_starter.common.ui

import android.os.Bundle
import android.view.View
import org.jetbrains.anko.toast


abstract class BaseFragmentVM<VM : BaseViewModel> : BaseFragment() {

    protected abstract val viewModel: VM// by viewModels<VM>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.message.observe(viewLifecycleOwner, this::handleHttpError)
        viewModel.httpError.observe(viewLifecycleOwner, this::handleHttpError)
        viewModel.process.observe(this, this::showProgress)
    }

    private fun handleHttpError(message: String) {
        requireContext().toast(message)
        hideProgressDialog()
    }
}