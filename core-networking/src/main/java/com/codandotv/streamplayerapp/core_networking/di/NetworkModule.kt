package com.codandotv.streamplayerapp.core_networking.di

import com.codandotv.streamplayerapp.core.networking.BuildConfig
import com.codandotv.streamplayerapp.core_networking.coroutines.NetworkResponseAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

object NetworkModule {
    val module = module {
        single(QualifierHost) {
            BuildConfig.HOST
        }

        single(QualifierProfile) {
            BuildConfig.PROFILE
        }

        single {
            Moshi.Builder()
                .add(KotlinJsonAdapterFactory())
                .build()
        }

        single(QualifierAuthInterceptor) {
            Interceptor { chain ->
                val newRequest =
                    chain.request()
                        .newBuilder()
                        .addHeader(
                            "Authorization",
                            BuildConfig.API_BEARER_AUTH
                        )
                        .addHeader("Content-Type", "application/json;charset=utf-8")
                        .build()
                chain.proceed(newRequest)
            }
        }

        single<Interceptor>(QualifierLoggerInterceptor) {
            HttpLoggingInterceptor().setLevel(
                if (BuildConfig.DEBUG) {
                    HttpLoggingInterceptor.Level.BODY
                } else {
                    HttpLoggingInterceptor.Level.NONE
                }
            )
        }
        single {
            provideRetrofit(
                okHttpClient = get(),
                moshi = get(),
                baseUrl = get(QualifierHost)
            )
        }

        single(QualifierProfileRetrofit) {
            provideRetrofit(
                okHttpClient = get(),
                moshi = get(),
                baseUrl = get(QualifierProfile)
            )
        }

        single {
            provideOkhttp(
                get(QualifierAuthInterceptor),
                get(QualifierLoggerInterceptor),
            )
        }
    }

    private fun provideOkhttp(
        vararg interceptor: Interceptor
    ): OkHttpClient {
        val okHttpClientBuilder = OkHttpClient.Builder()
        interceptor.forEach {
            okHttpClientBuilder.addInterceptor(it)
        }
        return okHttpClientBuilder
            .connectTimeout(15, TimeUnit.SECONDS)
            .build()
    }

    private fun provideRetrofit(
        okHttpClient: OkHttpClient,
        moshi: Moshi,
        baseUrl: String
    ): Retrofit =
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(okHttpClient)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .addCallAdapterFactory(NetworkResponseAdapterFactory(moshi))
            .build()
}