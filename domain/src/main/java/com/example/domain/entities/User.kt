package com.example.domain.entities

data class User(
    val uid: String,
    val fullName: String?,
    val email: String?,
    val password: String?,
    val mobilePhone: String?
)
