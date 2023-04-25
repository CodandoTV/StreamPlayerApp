
package com.codandotv.streamplayerapp.core_networking.handleError

inline fun <T> Result<T>.onError(action: (exception: Failure) -> Unit) {
    if (isFailure && exceptionOrNull() is Failure) {
        val error = exceptionOrNull() as Failure
        action(error)
    }
}
