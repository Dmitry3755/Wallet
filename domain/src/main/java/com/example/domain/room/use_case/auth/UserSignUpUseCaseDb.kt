package com.example.domain.room.use_case.auth

import com.example.domain.entities.User
import com.example.domain.room.repositories.UserAuthRepositoryDb
import javax.inject.Inject

class UserSignUpUseCaseDb @Inject constructor(private val userAuthRepositoryDb: UserAuthRepositoryDb) {
    suspend fun invoke(user : User) = userAuthRepositoryDb.insert(user)
}