package com.codandotv.streamplayerapp.feature_list_streams.search.data.datasource

import com.codandotv.streamplayerapp.core_networking.handleError.toFlow
import com.codandotv.streamplayerapp.feature_list_streams.list.data.model.ListStreamResponse
import com.codandotv.streamplayerapp.feature_list_streams.search.data.api.SearchStreamService
import com.codandotv.streamplayerapp.feature_list_streams.search.domain.datasource.SearchStreamDataSource
import kotlinx.coroutines.flow.Flow

class SearchStreamDataSourceImpl(
    private val service: SearchStreamService,
    private val required: String,
): SearchStreamDataSource {

    override suspend fun getMovieSearch(): Flow<ListStreamResponse> =
        service.getSearch(required)
            .toFlow()

}