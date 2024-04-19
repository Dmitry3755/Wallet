package com.example.nfctagreader.dependency_injection.modules

import com.example.data.preference.PreferenceData
import com.example.data.preference.PreferenceRepositoryImpl
import com.example.data.room.app_db.AppDatabase
import com.example.data.room.dao.UserAuthDao
import com.example.domain.preference.PreferenceRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class PreferenceModule {

    @Provides
    fun providePreferenceRepository(preferenceRepositoryImpl: PreferenceRepositoryImpl): PreferenceRepository =
        preferenceRepositoryImpl

    @Provides
    @Singleton
    fun providePreferenceData(): PreferenceData = PreferenceData()
}