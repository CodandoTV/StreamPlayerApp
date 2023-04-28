package com.codandotv.streamplayerapp.feature_list_streams.domain

import com.codandotv.streamplayerapp.feature_list_streams.data.ListStreamRepository
import com.codandotv.streamplayerapp.feature_list_streams.domain.model.ListStream
import kotlinx.coroutines.flow.Flow

interface ListStreamUseCase {
    suspend fun getMovies() : Flow<ListStream>
}

class ListStreamUseCaseImpl(
    private val repository: ListStreamRepository
) : ListStreamUseCase {
    override suspend fun getMovies(): Flow<ListStream> {
        println(">>>>>>> Curta o video!!!")
        return repository.getMovies()
    }
}