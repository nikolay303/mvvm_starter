package com.android.PACKAGE.ui.base

import com.android.PACKAGE.data.remote.HttpError
import com.mvvm_starter.common.ui.BaseViewModel

/**
 * Created by Nikolay Siliuk on 1/31/21.
 */

open class BaseViewModel : BaseViewModel() {

    protected fun handleHttpFailure(error: HttpError?) {
        handleHttpFailure(error?.message)
    }
}