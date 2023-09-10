package com.codandotv.streamplayerapp.feature_list_streams.search.domain.usecase

import com.codandotv.streamplayerapp.feature_list_streams.list.data.model.ListStreamResponse
import com.codandotv.streamplayerapp.feature_list_streams.search.domain.repository.SearchStreamRepository
import kotlinx.coroutines.flow.Flow

class SearchUseCase(val repository: SearchStreamRepository) {
    suspend operator fun invoke(): Flow<ListStreamResponse> {
        return repository.getMovieSearch()
    }
}