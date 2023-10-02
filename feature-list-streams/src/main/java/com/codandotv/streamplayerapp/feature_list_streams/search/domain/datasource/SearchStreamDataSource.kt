package com.codandotv.streamplayerapp.feature_list_streams.search.domain.datasource

import com.codandotv.streamplayerapp.feature_list_streams.list.data.model.ListStreamResponse
import kotlinx.coroutines.flow.Flow

interface SearchStreamDataSource {
    suspend fun getMovieSearch(query: String): Flow<ListStreamResponse>
}