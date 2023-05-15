package com.codandotv.streamplayerapp.feature_list_streams.list.domain

import com.codandotv.streamplayerapp.feature_list_streams.list.data.ListStreamRepository
import com.codandotv.streamplayerapp.feature_list_streams.list.domain.model.ListStream
import kotlinx.coroutines.flow.Flow

interface ListStreamUseCase {
    suspend fun getMovies(): Flow<List<ListStream>>
}

class ListStreamUseCaseImpl(
    private val repository: ListStreamRepository
) : ListStreamUseCase {
    override suspend fun getMovies(): Flow<List<ListStream>> =
        repository.getMovies()
}