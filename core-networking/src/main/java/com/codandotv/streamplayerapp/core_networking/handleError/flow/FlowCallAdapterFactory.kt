package com.codandotv.streamplayerapp.core_networking.handleError.flow

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import retrofit2.CallAdapter
import retrofit2.Retrofit
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type

class FlowCallAdapterFactory(
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : CallAdapter.Factory() {

    override fun get(
        returnType: Type,
        annotations: Array<Annotation>,
        retrofit: Retrofit
    ): CallAdapter<*, *>? {
        if (Flow::class.java != getRawType(returnType)) {
            return null
        }
        // check first that the return type is `ParameterizedType`
        check(returnType is ParameterizedType) {
            "return type must be parameterized as Call<Flow<<Foo>> or Call<Flow<out Foo>>"
        }
        val responseType = getParameterUpperBound(0, returnType)

        if (getRawType(responseType) != Flow::class.java) {
            return null
        }

        return FlowCallAdapter<Any>(responseType)
    }
}
