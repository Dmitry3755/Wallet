package com.example.domain.firebase.use_case.auth

import com.example.domain.firebase.repositories.UserAuthRepositoryFb
import com.example.domain.room.repositories.UserAuthRepositoryDb
import javax.inject.Inject

class UserSignInUseCaseFb @Inject constructor(private val userAuthRepositoryFb: UserAuthRepositoryFb) {
    suspend fun invoke(email: String, password: String) =
        userAuthRepositoryFb.signIn(email, password)
}