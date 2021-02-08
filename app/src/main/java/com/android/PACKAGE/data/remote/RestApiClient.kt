package com.android.PACKAGE.data.remote

import com.android.PACKAGE.Config
import com.android.PACKAGE.Preferences
import com.google.gson.GsonBuilder
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import timber.log.Timber
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import javax.inject.Singleton


/**
 * Created by Nikolay Siliuk on 1/31/21.
 */

@Singleton
class RestApiClient @Inject constructor(
    private val preferences: Preferences,
) {

    var retrofit: Retrofit
        private set

    init {
        val client = OkHttpClient.Builder()
            .readTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
            .addInterceptor(RequestInterceptor())
            .addInterceptor(
                HttpLoggingInterceptor { message ->
                    Timber.tag("OkHttp").d(message)
                }.apply { level = HttpLoggingInterceptor.Level.BODY }
            )
            .build()

        val gson = GsonBuilder().create()

        retrofit = Retrofit.Builder()
            .client(client)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .baseUrl(Config.baseUrl)
            .build()
    }

    inner class RequestInterceptor : Interceptor {

        override fun intercept(chain: Interceptor.Chain): Response {
            val request = chain.buildRequest()
            val response = chain.proceed(request)
//            if (response.code == 401 && !request.isRefreshTokenRequest()) {
//                refreshToken()
//                return chain.proceed(chain.buildRequest())
//            }
            return response
        }

        private fun Interceptor.Chain.buildRequest(): Request {
            val request = request()
            return request
                .newBuilder()
                .apply {
//                    if (preferences.accessToken.isNotBlank() && !request.isRefreshTokenRequest()) {
//                        addHeader("Authorization", "Bearer ${preferences.accessToken}")
//                    }
                }
                .build()
        }

//        private fun refreshToken() {
//            try {
//                val result = retrofit.create<AuthService>()
//                    .refreshToken(RefreshTokenRequest(preferences.refreshToken))
//                    .execute()
//                    .body() ?: return
//                if (result.isSuccess == true) {
//                    val data = result.getData<RefreshTokenResponse>() ?: return
//                    preferences.accessToken = data.accessToken.orEmpty()
//                    preferences.refreshToken = data.refreshToken.orEmpty()
//                    preferences.accessTokenExpiration = data.accessTokenExpiration.orEmpty()
//                } else {
//                    Timber.e(result.errorMessage)
//                }
//            } catch (t: Throwable) {
//                Timber.e(t)
//            }
//        }
//
//        private fun Request.isRefreshTokenRequest(): Boolean {
//            return url.toString().contains(AuthService.PATH_REFRESH_TOKEN)
//        }
    }
}