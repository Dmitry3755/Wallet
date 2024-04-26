package com.example.domain.firebase.use_case.auth

import com.example.domain.firebase.repositories.UserAuthRepositoryFb
import javax.inject.Inject

class GetCurrentUserUseCaseFb @Inject constructor(private val userAuthRepositoryFb: UserAuthRepositoryFb) {
    operator fun invoke() = userAuthRepositoryFb.currentUser
}