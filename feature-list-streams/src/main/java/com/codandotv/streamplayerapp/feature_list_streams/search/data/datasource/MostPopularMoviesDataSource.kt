package com.codandotv.streamplayerapp.feature_list_streams.search.data.datasource

import com.codandotv.streamplayerapp.core_networking.handleError.toFlow
import com.codandotv.streamplayerapp.feature_list_streams.search.data.model.ListSearchStreamResponse
import com.codandotv.streamplayerapp.feature_list_streams.search.data.api.MostPopularMoviesService
import kotlinx.coroutines.flow.Flow

interface MostPopularMoviesDataSource {
    suspend fun getMostPopularMovies(): Flow<ListSearchStreamResponse>
}

class MostPopularMoviesDataSourceImpl(
    private val service: MostPopularMoviesService
) : MostPopularMoviesDataSource {

    override suspend fun getMostPopularMovies(): Flow<ListSearchStreamResponse> =
        service.getPopular().toFlow()
}
