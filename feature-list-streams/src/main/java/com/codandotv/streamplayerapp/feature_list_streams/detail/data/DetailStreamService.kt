package com.codandotv.streamplayerapp.feature_list_streams.detail.data

import com.codandotv.streamplayerapp.core_networking.handleError.NetworkResponse
import com.codandotv.streamplayerapp.feature_list_streams.detail.data.model.DetailStreamResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface DetailStreamService {
    @GET("movie/{movie_id}")
    suspend fun getMovie(@Path("movie_id") movieId: String) : NetworkResponse<DetailStreamResponse>
}