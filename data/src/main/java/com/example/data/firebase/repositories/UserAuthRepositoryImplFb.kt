package com.example.data.firebase.repositories

import com.example.data.R
import com.example.data.firebase.mappers.toUser
import com.example.data.utils.AppResourceRepository
import com.example.domain.entities.User
import com.example.domain.firebase.repositories.UserAuthRepositoryFb
import com.example.domain.utils.LoadingStatus
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserProfileChangeRequest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class UserAuthRepositoryImplFb @Inject constructor(private val firebaseAuth: FirebaseAuth) :
    UserAuthRepositoryFb {

    override val currentUser: User?
        get() = firebaseAuth.currentUser?.toUser(null)


    override suspend fun sendResetPassword(email: String): Boolean {
        firebaseAuth.sendPasswordResetEmail(email).await()
        return true
    }

    override suspend fun signOut() {
        firebaseAuth.signOut()
    }

    override fun signIn(email: String, password: String) =
        flow {
            emit(LoadingStatus.Loading())
            try {
                val result = firebaseAuth.signInWithEmailAndPassword(email, password).await()
                emit(LoadingStatus.Success(result.user!!.toUser(password)))
            } catch (e: Exception) {
                emit(
                    LoadingStatus.Failure(
                        e.message
                            ?: AppResourceRepository.getString(R.string.error_something_went_wrong)
                    )
                )
            }
        }

    override fun createProfile(email: String, password: String, fullName: String) =
        flow {
            emit(LoadingStatus.Loading())
            try {
                val result = firebaseAuth.createUserWithEmailAndPassword(email, password).await()
                result.user!!.updateProfile(
                    UserProfileChangeRequest.Builder().setDisplayName(fullName).build()
                ).await()
                emit(LoadingStatus.Success(result.user!!.toUser(password)))
            } catch (e: Exception) {
                emit(
                    LoadingStatus.Failure(
                        e.message
                            ?: AppResourceRepository.getString(R.string.error_something_went_wrong)
                    )
                )
            }
        }
}