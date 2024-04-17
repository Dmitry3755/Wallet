package com.example.domain.room.use_case.auth

import com.example.domain.room.repositories.UserAuthRepositoryDb
import javax.inject.Inject

class UserIsRegistrationUseCaseDb @Inject constructor(private val userAuthRepositoryDb: UserAuthRepositoryDb) {
    suspend fun invoke() = userAuthRepositoryDb.isRegistrationUser()
}