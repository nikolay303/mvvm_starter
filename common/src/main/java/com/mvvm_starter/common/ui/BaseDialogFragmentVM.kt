package com.mvvm_starter.common.ui

import android.os.Bundle
import android.view.View
import com.mvvm_starter.core.utils.toast


abstract class BaseDialogFragmentVM<VM : BaseViewModel> : BaseDialogFragment() {

    protected abstract val viewModel: VM// by viewModels<VM>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.message.observe(viewLifecycleOwner, this::handleHttpError)
        viewModel.httpError.observe(viewLifecycleOwner, this::handleHttpError)
        viewModel.process.observe(viewLifecycleOwner, this::showProgress)
    }

    private fun handleHttpError(message: String) {
        requireContext().toast(message)
        hideProgressDialog()
    }

}