package com.example.domain.firebase.use_case.auth

import com.example.domain.firebase.repositories.UserAuthRepositoryFb
import javax.inject.Inject

class UserSendResetPswdUseCaseFb @Inject constructor(private val userAuthRepositoryFb: UserAuthRepositoryFb) {
    suspend fun invoke(email : String) = userAuthRepositoryFb.sendResetPassword(email)
}