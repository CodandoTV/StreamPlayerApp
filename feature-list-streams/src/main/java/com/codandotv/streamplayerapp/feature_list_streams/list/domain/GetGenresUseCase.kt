package com.codandotv.streamplayerapp.feature_list_streams.list.domain

import com.codandotv.streamplayerapp.feature_list_streams.list.data.ListStreamRepository
import com.codandotv.streamplayerapp.feature_list_streams.list.domain.model.Genre
import kotlinx.coroutines.flow.Flow

interface GetGenresUseCase {
    suspend operator fun invoke(): Flow<List<Genre>>
}

class GetGenresUseCaseImpl(
    private val repository: ListStreamRepository
) : GetGenresUseCase {
    override suspend fun invoke(): Flow<List<Genre>> {
        return repository.getGenres()
    }
}