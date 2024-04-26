package com.example.nfctagreader.dependency_injection.modules.preference

import com.example.data.preference.PreferenceData
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class PreferenceModule {

    @Provides
    @Singleton
    fun providePreferenceData(): PreferenceData = PreferenceData()

}