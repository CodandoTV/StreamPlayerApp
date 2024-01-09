package com.codandotv.streamplayerapp.core_networking.coroutines

import com.codandotv.streamplayerapp.core_networking.handleError.Failure
import com.codandotv.streamplayerapp.core_networking.handleError.NetworkResponse
import com.squareup.moshi.Moshi
import okhttp3.Request
import okhttp3.ResponseBody
import okio.Timeout
import retrofit2.Call
import retrofit2.Callback
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException

class NetworkResponseCall<T>(
    proxy: Call<T>,
    private val moshi: Moshi
) :
    CallDelegate<T, NetworkResponse<T>>(proxy) {

    override fun enqueueImpl(callback: Callback<NetworkResponse<T>>) =
        proxy.enqueue(object : Callback<T> {
            override fun onResponse(call: Call<T>, response: Response<T>) {
                val body = response.body()
                val code = response.code()
                val error = response.errorBody()
                if (response.isSuccessful) {
                    responseSuccessful(body, callback, code)
                } else {
                    responseError(callback,error, code)
                }
            }

            override fun onFailure(call: Call<T>, throwable: Throwable) {
                val networkResponse = when (throwable) {
                    is IOException -> Failure.NetworkError(throwable = throwable)
                    is HttpException -> convertException(throwable)
                    else -> Failure.GenericError(msg = throwable.message)
                }
                callback.onResponse(
                    this@NetworkResponseCall,
                    Response.success(NetworkResponse.Error(exception = networkResponse))
                )
            }


            private fun convertException(exception: HttpException): Failure {
                return try {
                    val response = getErrorResponse(exception)
                    Failure.UnexpectedApiException(throwable = response.exception)
                } catch (ex: Failure.ClientException) {
                    ex
                }catch (ex : Failure.UnparsableResponseException){
                    ex
                }
            }

            private fun getErrorResponse(ex: HttpException): NetworkResponse.Error {
                val error = ex.response()?.errorBody()?.string()
                if (error?.isEmpty() != false) {
                    throw Failure.ClientException(throwable = ex)
                }
                return parseError(error, ex)
            }

            private fun parseError(error: String, ex: HttpException): NetworkResponse.Error {
                try {
                    return moshi
                        .adapter(NetworkResponse.Error::class.java)
                        .fromJson(error)!!
                } catch (e: Exception) {
                    throw Failure.UnparsableResponseException(throwable = ex)
                }
            }
        })

    private fun responseSuccessful(
        body: T?,
        callback: Callback<NetworkResponse<T>>,
        code: Int
    ) {
        if (body != null) {
            callback.onResponse(
                this@NetworkResponseCall,
                Response.success(NetworkResponse.Success(body))
            )
        } else {
            callback.onResponse(
                this@NetworkResponseCall,
                Response.success(
                    NetworkResponse.Error(
                        exception = Failure.NoDataContent(
                            code
                        )
                    )
                )
            )
        }
    }

    private fun responseError(callback: Callback<NetworkResponse<T>>, error: ResponseBody?, code: Int) {
        val errorBody = when {
            error == null -> null
            error.contentLength() == 0L -> null
            else -> try {
                moshi
                    .adapter(NetworkResponse.Error::class.java)
                    .fromJson(error.string())
            } catch (ex: Exception) {
                null
            }
        }
        if (errorBody != null) {
            callback.onResponse(
                this@NetworkResponseCall,
                Response.success(
                    NetworkResponse.Error(
                        body = errorBody,
                        exception = Failure.ServerError(code)
                    )
                )
            )
        } else {
            callback.onResponse(
                this@NetworkResponseCall,
                Response.success(
                    NetworkResponse.Error(
                        exception = Failure.UnknownError(
                            code
                        )
                    )
                )
            )
        }
    }


    override fun cloneImpl(): Call<NetworkResponse<T>> =
        NetworkResponseCall(proxy.clone(), moshi)
}

abstract class CallDelegate<TIn, TOut>(
    protected val proxy: Call<TIn>
) : Call<TOut> {
    override fun execute(): Response<TOut> = throw NotImplementedError()
    override final fun enqueue(callback: Callback<TOut>) = enqueueImpl(callback)
    override final fun clone(): Call<TOut> = cloneImpl()

    override fun cancel() = proxy.cancel()
    override fun request(): Request = proxy.request()
    override fun isExecuted() = proxy.isExecuted
    override fun isCanceled() = proxy.isCanceled

    abstract fun enqueueImpl(callback: Callback<TOut>)
    abstract fun cloneImpl(): Call<TOut>
    override fun timeout(): Timeout = proxy.timeout()
}