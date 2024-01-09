package com.codandotv.streamplayerapp.core_networking.coroutines

import com.codandotv.streamplayerapp.core_networking.handleError.NetworkResponse
import com.squareup.moshi.Moshi
import retrofit2.Call
import retrofit2.CallAdapter
import retrofit2.Retrofit
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type

class NetworkResponseAdapterFactory(private val moshi : Moshi): CallAdapter.Factory() {
    override fun get(returnType: Type,
                     annotations: Array<Annotation>,
                     retrofit: Retrofit
    ): CallAdapter<*, *>? {
            return try {
                // suspend functions wrap the response type in `Call`
                if (Call::class.java != getRawType(returnType)) {
                    return null
                }

                // check first that the return type is `ParameterizedType`
                check(returnType is ParameterizedType) {
                    "return type must be parameterized as Call<NetworkResponse<<Foo>> or Call<NetworkResponse<out Foo>>"
                }

                // get the response type inside the `Call` type
                val responseType = getParameterUpperBound(0, returnType)

                // if the response type is not ApiResponse then we can't handle this type, so we return null
                if (getRawType(responseType) != NetworkResponse::class.java) {
                    return null
                }

                // the response type is ApiResponse and should be parameterized
                check(responseType is ParameterizedType) { "Response must be parameterized as NetworkResponse<Foo> or NetworkResponse<out Foo>" }

                val successBodyType = getParameterUpperBound(0, responseType)

                return NetworkResponseAdapter(successBodyType,moshi)
            } catch (ex: ClassCastException) {
                null
            }
    }
}