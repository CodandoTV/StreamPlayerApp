package com.codandotv.streamplayerapp.di

import android.content.res.Resources
import com.codandotv.streamplayerapp.core_networking.di.NetworkModule
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

object AppModule {

    val module = module {
        single<Resources> { androidContext().resources }
    }


    val list  = module + NetworkModule.module
}