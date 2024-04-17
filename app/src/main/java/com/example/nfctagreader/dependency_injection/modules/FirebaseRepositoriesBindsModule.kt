package com.example.nfctagreader.dependency_injection.modules

import com.example.data.firebase.repositories.UserAuthRepositoryImplFb
import com.example.domain.firebase.repositories.UserAuthRepositoryFb
import com.example.nfctagreader.dependency_injection.annotations.FirebaseRepository
import dagger.Module
import dagger.Provides
import javax.inject.Qualifier

@FirebaseRepository
@Module(includes = [FirebaseAuthModule::class])
class FirebaseRepositoriesBindsModule {

    @Provides
    fun provideUserRepositoryFb(userRepositoryImpl: UserAuthRepositoryImplFb): UserAuthRepositoryFb =
        userRepositoryImpl

    /* @Binds
     abstract fun bindsCardRepository(cardRepositoryImpl: CardRepositoryImpl) : CardRepository*/
}