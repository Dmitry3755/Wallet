package com.example.domain.firebase.use_case.auth

import com.example.domain.firebase.repositories.UserAuthRepositoryFb
import javax.inject.Inject

class UserSignOutUseCaseFb @Inject constructor(private val userAuthRepositoryFb: UserAuthRepositoryFb) {
    suspend fun invoke() = userAuthRepositoryFb.signOut()
}