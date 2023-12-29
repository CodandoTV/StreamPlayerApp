package com.codandotv.streamplayerapp.feature_list_streams.search.data.api

import com.codandotv.streamplayerapp.core_networking.handleError.NetworkResponse
import com.codandotv.streamplayerapp.feature_list_streams.search.data.model.ListSearchStreamResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchStreamService {
    @GET("search/movie")
    suspend fun getSearch(@Query("query") query: String) : NetworkResponse<ListSearchStreamResponse>
}
