package com.codandotv.streamplayerapp.core_local_storage.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.codandotv.streamplayerapp.core_local_storage.domain.model.MovieEntity

@Dao
interface FavoriteDao {

    @Query("SELECT * FROM movie")
    suspend fun fetchAll(): List<MovieEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(favoriteMovie: MovieEntity)

    @Query("""DELETE FROM movie WHERE id = :favoriteMovie""")
    suspend fun delete(favoriteMovie: String)
}