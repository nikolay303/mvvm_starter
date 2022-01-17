package com.mvvm_starter.common.ui

import android.os.Bundle
import android.view.View
import androidx.annotation.LayoutRes
import androidx.viewbinding.ViewBinding
import com.mvvm_starter.core.utils.toast


abstract class BaseFragmentVM<T : ViewBinding, VM : BaseViewModel>(@LayoutRes layoutResId: Int) :
    BaseFragment<T>(layoutResId) {

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