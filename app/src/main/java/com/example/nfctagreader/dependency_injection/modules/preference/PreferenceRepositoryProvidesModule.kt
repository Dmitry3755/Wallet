package com.example.nfctagreader.dependency_injection.modules.preference

import com.example.data.preference.PreferenceRepositoryImpl
import com.example.domain.preference.repositories.PreferenceRepository
import com.example.nfctagreader.dependency_injection.annotations.qualifiers.PreferenceQualifier
import dagger.Module
import dagger.Provides

@PreferenceQualifier
@Module(includes = [PreferenceModule::class])
class PreferenceRepositoryProvidesModule {

    @Provides
    fun providePreferenceRepository(preferenceRepositoryImpl: PreferenceRepositoryImpl): PreferenceRepository =
        preferenceRepositoryImpl

}