package com.codandotv.streamplayerapp.core_networking.handleError

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch

inline fun <T> Result<T>.onError(action: (exception: Failure) -> Unit): Result<T> {
    if(isFailure && exceptionOrNull() is Failure){
        val error = exceptionOrNull() as Failure
        action(error)
    }
    return this
}

fun <T> Flow<T>.catchFailure(action: suspend kotlinx.coroutines.flow.FlowCollector<T>.(Failure) -> Unit): Flow<T> =
    catch {
        if(it is Failure){
            action(it)
        }else{
            action(Failure.GenericError())
        }
    }
