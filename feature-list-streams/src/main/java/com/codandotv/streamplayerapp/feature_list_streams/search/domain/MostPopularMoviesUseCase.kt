package com.codandotv.streamplayerapp.feature_list_streams.search.domain

import com.codandotv.streamplayerapp.feature_list_streams.search.data.model.ListSearchStreamResponse
import com.codandotv.streamplayerapp.feature_list_streams.search.data.repository.MostPopularMoviesRepository
import kotlinx.coroutines.flow.Flow

interface MostPopularMoviesUseCase {
    suspend operator fun invoke(): Flow<ListSearchStreamResponse>
}

class MostPopularMoviesUseCaseImpl(
    val repository: MostPopularMoviesRepository
) : MostPopularMoviesUseCase {
    override suspend operator fun invoke(): Flow<ListSearchStreamResponse> {
        return repository.getMostPopularMovies()
    }
}
