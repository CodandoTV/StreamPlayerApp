package com.codandotv.streamplayerapp.core_local_storage.di

import androidx.room.Room
import com.codandotv.streamplayerapp.core_local_storage.data.database.StreamPlayerAppDatabase
import com.codandotv.streamplayerapp.core_local_storage.data.database.StreamPlayerAppDatabase.Companion.DATABASE_NAME
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

object LocalStorageModule {
    val module = module {
        single { Room.databaseBuilder(androidContext(), StreamPlayerAppDatabase::class.java, DATABASE_NAME).build() }
        single { StreamPlayerAppDatabase.getInstance(get()) }
        single { get<StreamPlayerAppDatabase>().favoriteDao() }
    }
}