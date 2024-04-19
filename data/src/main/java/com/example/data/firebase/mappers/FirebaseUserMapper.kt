package com.example.data.firebase.mappers

import com.example.domain.entities.User
import com.google.firebase.auth.FirebaseUser

fun FirebaseUser.toUser(password : String?): User {
    return User(
        uid = uid,
        fullName = displayName,
        email = email,
        password = password,
        mobilePhone = phoneNumber
    )
}