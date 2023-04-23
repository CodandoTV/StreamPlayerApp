package com.codandotv.streamplayerapp.feature_list_streams.data

import com.codandotv.streamplayerapp.feature_list_streams.data.model.ListStreamResponse
import retrofit2.http.GET

interface ListStreamService {
    @GET("3/movie/76341")
    suspend fun getMovies() : ListStreamResponse
}