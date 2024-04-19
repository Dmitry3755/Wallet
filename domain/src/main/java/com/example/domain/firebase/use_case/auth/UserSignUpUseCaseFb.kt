package com.example.domain.firebase.use_case.auth

import com.example.domain.firebase.repositories.UserAuthRepositoryFb
import javax.inject.Inject

class UserSignUpUseCaseFb @Inject constructor(private val userAuthRepository: UserAuthRepositoryFb) {

     fun invoke(email: String, password: String, fullName: String) =
        userAuthRepository.createProfile(email, password, fullName)

}