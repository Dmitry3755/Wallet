package com.example.data.room.repositories

import com.example.data.room.dao.UserAuthDao
import com.example.data.room.mappers.toUser
import com.example.data.room.mappers.toUserDb
import com.example.domain.entities.User
import com.example.domain.room.repositories.UserAuthRepositoryDb
import javax.inject.Inject

class UserAuthRepositoryImplDb @Inject constructor(private val userAuthDao: UserAuthDao) :
    UserAuthRepositoryDb {
    override fun insert(user: User) {
        userAuthDao.insert(user.toUserDb())
    }

    override suspend fun update(user: User) {
        userAuthDao.update(user.toUserDb())
    }

    override fun isRegistrationUser(): Boolean {
        return userAuthDao.isRegistrationUser().isNotEmpty()
    }
}