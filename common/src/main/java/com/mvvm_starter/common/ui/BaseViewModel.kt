package com.mvvm_starter.common.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel


/**
 * Created by Nikolay Siliuk on 1/25/21.
 */

abstract class BaseViewModel : ViewModel() {

    private val _httpError = MutableLiveData<String>()
    val httpError: LiveData<String> get() = _httpError

    private val _message = MutableLiveData<String>()
    val message: LiveData<String> get() = _message

    private val _progress = MutableLiveData<Boolean>()
    val process: LiveData<Boolean> get() = _progress


    protected fun handleHttpFailure(message: String?) {
        this._httpError.postValue(message.orEmpty())
    }

    protected fun showMessage(message: String?) {
        this._message.postValue(message)
    }

    protected fun showProgress(progress: Boolean) {
        this._progress.postValue(progress)
    }

}