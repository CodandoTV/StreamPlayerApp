package com.codandotv.streamplayerapp.feature_list_streams.search.data.repository

import com.codandotv.streamplayerapp.feature_list_streams.search.data.datasource.SearchStreamDataSource
import com.codandotv.streamplayerapp.feature_list_streams.search.data.model.ListSearchStreamResponse
import kotlinx.coroutines.flow.Flow

interface SearchStreamRepository {
    suspend fun getMovieSearch(query: String) : Flow<ListSearchStreamResponse>

}
class SearchStreamRepositoryImp(
    private val dataSource: SearchStreamDataSource
) : SearchStreamRepository {
    override suspend fun getMovieSearch(query: String): Flow<ListSearchStreamResponse> =
        dataSource.getMovieSearch(query)
}
