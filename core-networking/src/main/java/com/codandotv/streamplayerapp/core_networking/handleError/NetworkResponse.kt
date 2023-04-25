package com.codandotv.streamplayerapp.core_networking.handleError

sealed class NetworkResponse<out T> {
    data class Success<out T>(
        val value: T
    ) : NetworkResponse<T>()

    data class Error(
        val body: Any? = null,
        @Transient
        val exception: Failure? = null
    ) : NetworkResponse<Nothing>()
}

fun <T> NetworkResponse<T>.toResult(): Result<T> =
    when (this) {
        is NetworkResponse.Success -> {
            Result.success(this.value)
        }
        is NetworkResponse.Error -> {
            Result.failure(this.exception ?: Failure.GenericError())
        }
    }