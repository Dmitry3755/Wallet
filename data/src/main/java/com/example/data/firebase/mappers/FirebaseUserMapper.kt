package com.example.data.firebase.mappers

import com.example.domain.entities.User
import com.google.firebase.auth.FirebaseUser

fun FirebaseUser.toUser(): User {
    return User(
        uid = uid,
        fullName = displayName,
        email = email,
        mobilePhone = phoneNumber
    )
}