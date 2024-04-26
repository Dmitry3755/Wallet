package com.example.nfctagreader.dependency_injection.modules.firebase

import com.example.data.firebase.repositories.UserAuthRepositoryImplFb
import com.example.domain.firebase.repositories.UserAuthRepositoryFb
import com.example.nfctagreader.dependency_injection.annotations.qualifiers.FirebaseQualifier
import dagger.Module
import dagger.Provides

@FirebaseQualifier
@Module(includes = [FirebaseAuthModule::class])
class FirebaseRepositoriesProvidesModule {

    @Provides
    fun provideUserRepositoryFb(userRepositoryImpl: UserAuthRepositoryImplFb): UserAuthRepositoryFb =
        userRepositoryImpl
}