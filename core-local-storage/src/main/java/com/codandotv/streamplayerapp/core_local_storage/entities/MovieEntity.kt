package com.codandotv.streamplayerapp.core_local_storage.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movie")
data class MovieEntity(
    @PrimaryKey val id: String,
    val title: String,
    val overview: String,
    val tagline: String,
    val url: String,
    val releaseYear: String
)