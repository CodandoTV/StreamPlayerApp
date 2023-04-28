package com.codandotv.streamplayerapp.feature_list_streams.data

import com.codandotv.streamplayerapp.feature_list_streams.domain.model.ListStream
import com.codandotv.streamplayerapp.feature_list_streams.domain.toListStream

interface ListStreamRepository {
    suspend fun getMovies() : ListStream
}

class ListStreamRepositoryImpl(
    private val service : ListStreamService
) : ListStreamRepository {

    override suspend fun getMovies(): ListStream =
        service.getMovies()
            .toListStream()
}