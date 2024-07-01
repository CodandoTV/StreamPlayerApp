package com.codandotv.streamplayerapp.feature_list_streams.list.di

import com.codandotv.streamplayerapp.feature_list_streams.list.data.ListStreamService
import org.koin.core.annotation.ComponentScan
import org.koin.core.annotation.Factory
import org.koin.core.annotation.Module
import org.koin.core.context.GlobalContext
import retrofit2.Retrofit

@Module
@ComponentScan("com.codandotv.streamplayerapp.feature_list_streams.list")
class ListStreamModule {

    @Factory
    fun service(): ListStreamService {
        val koin = GlobalContext.get()
        val retrofit = koin.get<Retrofit>()
        return retrofit.create(ListStreamService::class.java)
    }
}