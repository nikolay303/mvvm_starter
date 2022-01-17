package com.android.PACKAGE.di

import android.content.Context
import androidx.preference.PreferenceManager
import com.android.PACKAGE.Preferences
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object StorageModule {

    @Singleton
    @Provides
    fun providePreferences(@ApplicationContext context: Context) =
        Preferences(PreferenceManager.getDefaultSharedPreferences(context))
//
//    @Singleton
//    @Provides
//    fun provideDatabase(@ApplicationContext context: Context) = AppDatabase.getDatabase(context)

//    @Singleton
//    @Provides
//    fun provideProfileDao(database: AppDatabase) = database.profileDao()
}