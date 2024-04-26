package com.example.nfctagreader.dependency_injection.modules.room

import android.content.Context
import androidx.room.Room
import com.example.data.room.app_db.AppDatabase
import com.example.data.room.dao.UserAuthDao
import com.example.nfctagreader.dependency_injection.annotations.ApplicationContext
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {

    @Provides
    @Singleton
    fun provideRoomDatabase(@ApplicationContext appContext: Context): AppDatabase {
        return Room.databaseBuilder(
            appContext,
            AppDatabase::class.java,
            "wallet.db"
        ).build()
    }

    @Provides
    @Singleton
    fun provideUserAuthDao(appDatabase: AppDatabase): UserAuthDao {
        return appDatabase.userAuthDao()
    }

}