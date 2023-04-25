package com.codandotv.streamplayerapp.feature_list_streams.data

import com.codandotv.streamplayerapp.core_networking.handleError.toResult
import com.codandotv.streamplayerapp.feature_list_streams.domain.model.ListStream
import com.codandotv.streamplayerapp.feature_list_streams.domain.toListStream

interface ListStreamRepository {
    suspend fun getMovies(): Result<ListStream>
}

class ListStreamRepositoryImpl(
    private val service: ListStreamService
) : ListStreamRepository {

    override suspend fun getMovies(): Result<ListStream> =
        service.getMovies()
            .toResult()
            .map {
                it.toListStream()
            }

}