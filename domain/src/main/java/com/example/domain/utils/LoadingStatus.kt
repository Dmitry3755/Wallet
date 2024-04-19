package com.example.domain.utils

sealed class LoadingStatus<T> {
    class Success<T>(val result: T) : LoadingStatus<T>()
    class Failure<T>(val exception: String) : LoadingStatus<T>()
    class Loading<T> : LoadingStatus<T>()
}