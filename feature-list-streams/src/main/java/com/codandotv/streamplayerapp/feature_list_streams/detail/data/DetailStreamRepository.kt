package com.codandotv.streamplayerapp.feature_list_streams.detail.data

import com.codandotv.streamplayerapp.core_networking.handleError.toFlow
import com.codandotv.streamplayerapp.feature_list_streams.detail.domain.DetailStream
import com.codandotv.streamplayerapp.feature_list_streams.detail.domain.VideoStream
import com.codandotv.streamplayerapp.feature_list_streams.detail.domain.toDetailStream
import com.codandotv.streamplayerapp.feature_list_streams.detail.domain.toVideoStreams
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map

interface DetailStreamRepository {
    suspend fun getMovie(): Flow<DetailStream>

    suspend fun getVideoStreams(): Flow<List<VideoStream>>
}

class DetailStreamRepositoryImpl(
    private val movieId: String,
    private val service: DetailStreamService
) : DetailStreamRepository {

    override suspend fun getMovie(): Flow<DetailStream> =
        service.getMovie(movieId)
            .toFlow()
            .map {
                it.toDetailStream()
            }

    override suspend fun getVideoStreams(): Flow<List<VideoStream>> =
        service.getVideoStreams(movieId)
            .toFlow()
            .map {
                it.toVideoStreams()
            }
}