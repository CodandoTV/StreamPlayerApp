package com.codandotv.streamplayerapp.di

import android.content.res.Resources
import com.codandotv.streamplayerapp.core_local_storage.di.LocalStorageModule
import com.codandotv.streamplayerapp.core_networking.di.NetworkModule
import com.codandotv.streamplayerapp.core_shared.qualifier.QualifierDispatcherIO
import kotlinx.coroutines.Dispatchers
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

object AppModule {
    private val module = module {
        single<Resources> { androidContext().resources }
        single(QualifierDispatcherIO) { Dispatchers.IO }
    }
    val list  = module + NetworkModule.module + LocalStorageModule.module
}