package com.codandotv.streamplayerapp

import android.app.Application
import di.AppModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class CustomApplication : Application() {

    override fun onCreate() {
        super.onCreate()
       startKoin{
            androidContext(this@CustomApplication.applicationContext)
            modules(AppModule.module)
       }
    }
}