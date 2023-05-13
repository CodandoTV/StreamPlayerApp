package com.codandotv.streamplayerapp.feature_list_streams.list.data.model

data class GenreResponse(
    val id: Long,
    val name: String
)

data class GenresResponse(
    val genres: List<GenreResponse>
)