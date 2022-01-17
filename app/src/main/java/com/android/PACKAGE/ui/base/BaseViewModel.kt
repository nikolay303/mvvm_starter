package com.android.PACKAGE.ui.base

import com.android.PACKAGE.data.remote.HttpError
import com.mvvm_starter.common.ui.BaseViewModel


open class BaseViewModel : BaseViewModel() {

    protected fun handleHttpFailure(error: HttpError?) {
        handleHttpFailure(error?.message)
    }
}