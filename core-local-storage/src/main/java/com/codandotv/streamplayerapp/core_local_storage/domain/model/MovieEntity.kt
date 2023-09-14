package com.codandotv.streamplayerapp.core_local_storage.domain.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movie")
data class MovieEntity(
    @PrimaryKey val id: String,
    @ColumnInfo("title") val title: String,
    @ColumnInfo("overview") val overview: String,
    @ColumnInfo("tagline") val tagline: String,
    @ColumnInfo("url") val url: String,
    @ColumnInfo("release_year")val releaseYear: String
)