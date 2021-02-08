package com.android.PACKAGE.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.android.PACKAGE.BuildConfig
import com.android.PACKAGE.R


/**
 * Created by Nikolay Siliuk on 2/1/21.
 */

//@Database(
//    entities = [],
//    version = 1
//)
//abstract class AppDatabase : RoomDatabase() {
//
//    companion object {
//        @Volatile
//        private var instance: AppDatabase? = null
//
//        fun getDatabase(context: Context): AppDatabase =
//            instance ?: synchronized(this) {
//                instance ?: buildDatabase(context).also { instance = it }
//            }
//
//        private fun buildDatabase(appContext: Context) =
//            Room.databaseBuilder(
//                appContext,
//                AppDatabase::class.java,
//                "${appContext.getString(R.string.app_name)}.db"
//            ).apply {
//                if (BuildConfig.DEBUG) {
//                    fallbackToDestructiveMigration()
//                }
//            }.build()
//    }
//}