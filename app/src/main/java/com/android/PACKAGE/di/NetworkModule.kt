package com.android.PACKAGE.di

import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


/**
 * Created by Nikolay Siliuk on 1/31/21.
 */

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

//    @Singleton
//    @Provides
//    fun provideAuthService(restApiClient: RestApiClient): AuthService =
//        restApiClient.retrofit.create()
}