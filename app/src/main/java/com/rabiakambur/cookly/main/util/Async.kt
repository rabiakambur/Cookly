package com.rabiakambur.cookly.main.util

sealed class Async<T> {

    data class Success<T>(val data: T) : Async<T>()

    data class Error<T>(val exception: Throwable) : Async<T>()

    data class Loading<T>(val data: T? = null) : Async<T>()
}