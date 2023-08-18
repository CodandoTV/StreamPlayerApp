package com.codandotv.streamplayerapp.core_local_storage.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.codandotv.streamplayerapp.core_local_storage.entities.MovieEntity

@Dao
interface FavoriteDao {

    @Query("SELECT * FROM movie")
    suspend fun fetchAll(): List<MovieEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(favoriteMovie: MovieEntity)
}