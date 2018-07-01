package com.example.ivanserbyniuk.mvvmarchitectureexample.viewmodels

sealed class   RequestState<T> {
    data class Progress<T>(var loading: Boolean) : RequestState<T>()
    data class Success<T>(var data: T) : RequestState<T>()
    data class Failure<T>(val e: Throwable) : RequestState<T>()

    companion object {
        fun <T> loading(isLoading: Boolean): RequestState<T> = Progress(isLoading)

        fun <T> success(data: T): RequestState<T> = Success(data)

        fun <T> failure(e: Throwable): RequestState<T> = Failure(e)
    }
}