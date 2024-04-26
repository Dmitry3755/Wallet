package com.example.domain.firebase.repositories

import com.example.domain.entities.User
import com.example.domain.utils.LoadingStatus
import kotlinx.coroutines.flow.Flow

interface UserAuthRepositoryFb {

    val currentUser: User?

    fun signIn(email: String, password: String): Flow<LoadingStatus<User>>

    fun createProfile(email: String, password: String, fullName: String): Flow<LoadingStatus<User>>

    suspend fun signOut()

    suspend fun sendResetPassword(email: String): Boolean

}