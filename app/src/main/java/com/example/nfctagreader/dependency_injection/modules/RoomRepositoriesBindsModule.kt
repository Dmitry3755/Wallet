package com.example.nfctagreader.dependency_injection.modules

import com.example.data.room.repositories.UserAuthRepositoryImplDb
import com.example.domain.room.repositories.UserAuthRepositoryDb
import com.example.nfctagreader.dependency_injection.annotations.RoomRepository
import dagger.Module
import dagger.Provides

@RoomRepository
@Module(includes = [DatabaseModule::class])
class RoomRepositoriesBindsModule {

    @Provides
    fun provideUserAuthRepositoryDb(userAuthRepositoryImplDb: UserAuthRepositoryImplDb): UserAuthRepositoryDb =
        userAuthRepositoryImplDb

}