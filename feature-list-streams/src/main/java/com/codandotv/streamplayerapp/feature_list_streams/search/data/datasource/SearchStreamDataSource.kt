package com.codandotv.streamplayerapp.feature_list_streams.search.data.datasource

import com.codandotv.streamplayerapp.core_networking.handleError.toFlow
import com.codandotv.streamplayerapp.feature_list_streams.search.data.model.ListSearchStreamResponse
import com.codandotv.streamplayerapp.feature_list_streams.search.data.api.SearchStreamService
import kotlinx.coroutines.flow.Flow

interface SearchStreamDataSource {
    suspend fun getMovieSearch(query: String): Flow<ListSearchStreamResponse>
}
class SearchStreamDataSourceImpl(
    private val service: SearchStreamService
): SearchStreamDataSource {

    override suspend fun getMovieSearch(query:String): Flow<ListSearchStreamResponse> =
        service.getSearch(query = query).toFlow()
}
