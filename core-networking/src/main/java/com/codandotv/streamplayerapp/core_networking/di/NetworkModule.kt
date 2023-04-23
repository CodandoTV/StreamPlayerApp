package com.codandotv.streamplayerapp.core_networking.di

import com.codandotv.streamplayerapp.core_networking.BuildConfig
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

        single {
            Moshi.Builder()
                .add(KotlinJsonAdapterFactory())
                .build()
        }

        single<Interceptor> {
            HttpLoggingInterceptor().apply {
                level = if (BuildConfig.DEBUG) {
                    HttpLoggingInterceptor.Level.BODY
                } else {
                    HttpLoggingInterceptor.Level.NONE
                }
            }
        }
        single {
            provideRetrofit(
                okHttpClient = get(),
                moshi = get(),
                baseUrl = get(QualifierHost)
            )
        }

        single {
            provideOkhttp(
                interceptor = get()
            )
        }
    }

    private fun provideOkhttp(
        interceptor: Interceptor
    ): OkHttpClient {
        val okHttpClientBuilder = OkHttpClient.Builder()
        okHttpClientBuilder.addInterceptor(interceptor)

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
            .build()
}