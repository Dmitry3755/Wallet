package com.example.domain.room.repositories

import com.example.domain.entities.User

interface UserAuthRepositoryDb {

    fun insert(user: User)

    fun isRegistrationUser() : Boolean

    suspend fun update(user: User)

}