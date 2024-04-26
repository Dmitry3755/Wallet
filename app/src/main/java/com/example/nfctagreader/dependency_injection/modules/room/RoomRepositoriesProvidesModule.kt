package com.example.nfctagreader.dependency_injection.modules.room

import com.example.data.room.repositories.UserAuthRepositoryImplDb
import com.example.domain.room.repositories.UserAuthRepositoryDb
import com.example.nfctagreader.dependency_injection.annotations.qualifiers.RoomQualifier
import dagger.Module
import dagger.Provides

@RoomQualifier
@Module(includes = [DatabaseModule::class])
class RoomRepositoriesProvidesModule {

    @Provides
    fun provideUserAuthRepositoryDb(userAuthRepositoryImplDb: UserAuthRepositoryImplDb): UserAuthRepositoryDb =
        userAuthRepositoryImplDb

}