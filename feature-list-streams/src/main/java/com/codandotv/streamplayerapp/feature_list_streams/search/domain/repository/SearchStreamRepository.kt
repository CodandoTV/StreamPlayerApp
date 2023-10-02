package com.codandotv.streamplayerapp.feature_list_streams.search.domain.repository

import com.codandotv.streamplayerapp.feature_list_streams.list.data.model.ListStreamResponse
import kotlinx.coroutines.flow.Flow

interface SearchStreamRepository {
    suspend fun getMovieSearch(query: String) : Flow<ListStreamResponse>

}