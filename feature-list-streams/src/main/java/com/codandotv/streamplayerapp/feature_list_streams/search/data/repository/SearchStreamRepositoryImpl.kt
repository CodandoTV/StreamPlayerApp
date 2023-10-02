package com.codandotv.streamplayerapp.feature_list_streams.search.data.repository

import com.codandotv.streamplayerapp.feature_list_streams.list.data.model.ListStreamResponse
import com.codandotv.streamplayerapp.feature_list_streams.search.domain.datasource.SearchStreamDataSource
import com.codandotv.streamplayerapp.feature_list_streams.search.domain.repository.SearchStreamRepository
import kotlinx.coroutines.flow.Flow

class SearchStreamRepositoryImpl(
    private val dataSource: SearchStreamDataSource
) : SearchStreamRepository {
    override suspend fun getMovieSearch(query: String): Flow<ListStreamResponse> =
        dataSource.getMovieSearch(query)
}