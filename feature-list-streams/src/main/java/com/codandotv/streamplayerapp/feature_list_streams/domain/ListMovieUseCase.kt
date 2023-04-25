package com.codandotv.streamplayerapp.feature_list_streams.domain

import com.codandotv.streamplayerapp.feature_list_streams.data.ListStreamRepository
import com.codandotv.streamplayerapp.feature_list_streams.domain.model.ListStream

interface ListStreamUseCase {
    suspend fun getMovies() : Result<ListStream>
}

class ListStreamUseCaseImpl(
    private val repository: ListStreamRepository
) : ListStreamUseCase {
    override suspend fun getMovies(): Result<ListStream> {
        println(">>>>>>> Curta o video!!!")
        return repository.getMovies()
    }
}