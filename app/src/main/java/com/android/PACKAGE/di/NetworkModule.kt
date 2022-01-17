package com.android.PACKAGE.di

import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

//    @Singleton
//    @Provides
//    fun provideAuthService(restApiClient: RestApiClient): AuthService =
//        restApiClient.retrofit.create()
}