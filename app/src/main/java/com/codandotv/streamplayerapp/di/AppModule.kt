package com.codandotv.streamplayerapp.di

import android.content.res.Resources
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

object AppModule {

    val module = module {
        single<Resources> { androidContext().resources }
    }
}