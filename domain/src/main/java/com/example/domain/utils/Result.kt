package com.example.domain.utils

import java.lang.Exception

sealed class Result<out R> {

    data class Success<out T>(val result: T) : Result<T>()
    data class Failure(val exception: Exception) : Result<Nothing>()
    object Loading : Result<Nothing>()

}