package com.codandotv.streamplayerapp.feature_list_streams.search.domain.usecase

import com.codandotv.streamplayerapp.feature_list_streams.list.data.model.ListStreamResponse
import com.codandotv.streamplayerapp.feature_list_streams.search.domain.repository.SearchStreamRepository
import kotlinx.coroutines.flow.Flow

interface SearchUseCase {
    suspend operator fun invoke(query:String): Flow<ListStreamResponse>
}

class SearchUseCaseImpl(val repository: SearchStreamRepository) : SearchUseCase {
    override suspend operator fun invoke(query:String): Flow<ListStreamResponse> {
        return repository.getMovieSearch(query = query)
    }
}