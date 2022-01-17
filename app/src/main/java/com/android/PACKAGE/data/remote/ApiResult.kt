package com.android.PACKAGE.data.remote

import com.android.PACKAGE.App
import com.android.PACKAGE.R
import kotlinx.coroutines.*
import org.json.JSONException
import org.json.JSONObject
import retrofit2.HttpException
import timber.log.Timber


typealias Complete = () -> Unit
typealias Success<T> = (T) -> Unit
typealias Error = (HttpError) -> Unit
typealias Completable = Any


data class HttpError(val code: Int? = -1, val message: String? = "")


inline fun <reified T> CoroutineScope.apiCall(
    dispatcher: CoroutineDispatcher = Dispatchers.IO,
    noinline request: suspend () -> T,
    crossinline onSuccess: Success<T>,
    crossinline onError: Error = {}
): Job {
    return launch(dispatcher) {
        try {
            val result = request.invoke()
            withContext(Dispatchers.Main) {
                onSuccess.invoke(result ?: T::class.java.newInstance())
            }
        } catch (t: Throwable) {
            Timber.e(t)
            val httpError = handleNetworkError(t)
            withContext(Dispatchers.Main) {
                onError.invoke(httpError)
            }
        }
    }
}


fun handleNetworkError(throwable: Throwable): HttpError {
    Timber.e(throwable)
    return when (throwable) {
        is HttpException -> processHttpException(throwable)
        else -> HttpError(message = throwable.localizedMessage)
    }
}

fun processHttpException(throwable: HttpException): HttpError {
    val errorBody = throwable.response()?.errorBody()?.string()
    if (errorBody.isNullOrBlank()) {
        return processCommonNetworkError(throwable)
    }

    return try {
        val jsonObject = JSONObject(errorBody)
        val message = if (jsonObject.has("message")) jsonObject.getString("message") else ""
        HttpError(code = throwable.code(), message = message)
    } catch (e: JSONException) {
        HttpError(message = e.localizedMessage)
    }
}

fun processCommonNetworkError(e: HttpException?): HttpError {
    val code = e?.code()
    val message = when (code) {
        500 -> App.instance.getString(R.string.message_server_error)
        502 -> App.instance.getString(R.string.message_bad_gateway)
        401 -> App.instance.getString(R.string.message_unauthorized)
        404 -> App.instance.getString(R.string.message_page_not_found)
        else -> App.instance.getString(R.string.message_network_error)
    }
    return HttpError(code, message)
}