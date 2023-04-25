package com.codandotv.streamplayerapp.core_networking.handleError.flow

import com.codandotv.streamplayerapp.core_networking.handleError.Failure
import com.codandotv.streamplayerapp.core_networking.handleError.NetworkResponse
import com.squareup.moshi.Moshi
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.CallAdapter
import retrofit2.Callback
import retrofit2.Response
import java.lang.reflect.Type

class FlowCallAdapter<R>(
    private val responseType: Type,
    private val moshi: Moshi,
    private val coroutineDispatcher: CoroutineDispatcher = Dispatchers.IO
) : CallAdapter<R, Flow<R>> {

    override fun responseType(): Type {
        return responseType
    }

    override fun adapt(call: Call<R>): Flow<R> {
        return flow {
            val response = call.execute()
            if (response.isSuccessful) {
                emit(responseSuccessful(response.body()!!, response.code()))
            } else {
                responseError(response.errorBody()!!,response.code())
            }
        }.flowOn(coroutineDispatcher)
    }

    private fun responseSuccessful(
        body: R,
        code: Int
    ) = body ?: throw Failure.NoDataContent(
        code
    )

    private fun responseError(
        error: ResponseBody,
        code: Int
    ) {
        val errorBody = when {
            error == null -> null
            error.contentLength() == 0L -> null
            else -> try {
                moshi
                    .adapter(Failure::class.java)
                    .fromJson(error.string())
            } catch (ex: Exception) {
                throw Failure.UnparsableResponseException(throwable = ex)
            }
        }
        if (errorBody != null) {
            throw Failure.ServerError(code)
        } else {
            throw Failure.UnknownError(
                code
            )
        }
    }

}