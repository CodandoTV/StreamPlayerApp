package com.codandotv.streamplayerapp.feature_list_streams.data

import com.codandotv.streamplayerapp.core_networking.handleError.toFlow
import com.codandotv.streamplayerapp.core_networking.handleError.toResult
import com.codandotv.streamplayerapp.feature_list_streams.domain.model.ListStream
import com.codandotv.streamplayerapp.feature_list_streams.domain.toListStream
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

interface ListStreamRepository {
    suspend fun getMovies(): Flow<ListStream>
}

class ListStreamRepositoryImpl(
    private val service: ListStreamService
) : ListStreamRepository {

    override suspend fun getMovies(): Flow<ListStream> =
        service.getMovies()
            .toFlow()
            .map {
                it.toListStream()
            }
}