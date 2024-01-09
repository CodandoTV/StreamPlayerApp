package com.codandotv.streamplayerapp.feature_list_streams.detail.data

import com.codandotv.streamplayerapp.core_networking.handleError.toFlow
import com.codandotv.streamplayerapp.feature_list_streams.detail.domain.DetailStream
import com.codandotv.streamplayerapp.feature_list_streams.detail.domain.toDetailStream
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

interface DetailStreamRepository {
    suspend fun getMovie() : Flow<DetailStream>
}

class DetailStreamRepositoryImpl(
    private val movieId: String,
    private val service : DetailStreamService
) : DetailStreamRepository {

    override suspend fun getMovie(): Flow<DetailStream> =
        service.getMovie(movieId)
            .toFlow()
            .map {
                it.toDetailStream()
            }
}