package com.example.domain.firebase.repositories

import com.example.domain.entities.User
import com.example.domain.utils.Result

interface UserAuthRepositoryFb {

    val currentUser: User?

    suspend fun signIn(email: String, password: String): Result<User?>

    suspend fun signUp(email: String, password: String, fullName : String): Result<User?>

    suspend fun signOut()

    suspend fun sendResetPassword(email: String): Boolean

}