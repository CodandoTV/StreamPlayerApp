package com.codandotv.streamplayerapp.feature_list_streams.data

import com.codandotv.streamplayerapp.core_networking.handleError.NetworkResponse
import com.codandotv.streamplayerapp.feature_list_streams.data.model.ListStreamResponse
import retrofit2.http.GET

interface ListStreamService {
    @GET("codandotv")
    suspend fun getMovies() : NetworkResponse<ListStreamResponse>
}