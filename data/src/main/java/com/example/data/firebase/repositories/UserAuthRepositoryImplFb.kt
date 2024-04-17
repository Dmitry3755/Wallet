package com.example.data.firebase.repositories

import com.example.data.firebase.mappers.toUser
import com.example.domain.entities.User
import com.example.domain.firebase.repositories.UserAuthRepositoryFb
import com.example.domain.utils.Result
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserProfileChangeRequest
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class UserAuthRepositoryImplFb @Inject constructor(private val firebaseAuth: FirebaseAuth) :
    UserAuthRepositoryFb {

    override val currentUser: User?
        get() = firebaseAuth.currentUser?.toUser()

    override suspend fun sendResetPassword(email: String): Boolean {
        firebaseAuth.sendPasswordResetEmail(email).await()
        return true
    }

    override suspend fun signOut() {
        firebaseAuth.signOut()
    }

    override suspend fun signIn(email: String, password: String): Result<User?> {
        return try {
            val result = firebaseAuth.signInWithEmailAndPassword(email, password).await()
            Result.Success(result.user!!.toUser())
        } catch (e: Exception) {
            Result.Failure(e)
        }
    }

    override suspend fun signUp(email: String, password: String, fullName : String): Result<User?> {
        return try {
            val result = firebaseAuth.createUserWithEmailAndPassword(email, password).await()
            result.user!!.updateProfile(UserProfileChangeRequest.Builder().setDisplayName(fullName).build()).await()
            return Result.Success(result.user!!.toUser())
        } catch (e: Exception) {
            Result.Failure(e)
        }
    }

}