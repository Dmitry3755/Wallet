package com.example.data.room.mappers

import com.example.data.room.entities.UserDb
import com.example.domain.entities.User

fun UserDb.toUser(): User {
    return User(
        uid = this.uid,
        fullName = this.fullName,
        email = this.email,
        mobilePhone = this.mobilePhone
    )
}

fun User.toUserDb(): UserDb {
    return UserDb(
        uid = this.uid,
        fullName = this.fullName,
        email = this.email,
        mobilePhone = this.mobilePhone
    )
}