package com.codandotv.streamplayerapp.core_local_storage.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.codandotv.streamplayerapp.core_local_storage.data.dao.FavoriteDao
import com.codandotv.streamplayerapp.core_local_storage.domain.model.MovieEntity

@Database(entities = [MovieEntity::class], version = 1)
abstract class StreamPlayerAppDatabase : RoomDatabase() {

    abstract fun favoriteDao(): FavoriteDao

    companion object {

        private var instance: StreamPlayerAppDatabase? = null

        fun getInstance(context: Context): StreamPlayerAppDatabase {
            if (instance == null) {
                synchronized(this) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        StreamPlayerAppDatabase::class.java,
                        DATABASE_NAME
                    ).build()
                }
            }
            return instance!!
        }

        const val DATABASE_NAME = "app-database.db"
    }
}