package com.codandotv.streamplayerapp.feature_list_streams.data

import com.codandotv.streamplayerapp.core_networking.handleError.NetworkResponse
import com.codandotv.streamplayerapp.feature_list_streams.data.model.GenresResponse
import com.codandotv.streamplayerapp.feature_list_streams.data.model.ListStreamResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ListStreamService {
    @GET("3/discover/movie")
    suspend fun getMovies(@Query("with_genres") genres: String) : NetworkResponse<ListStreamResponse>

    @GET("3/genre/movie/list")
    suspend fun getGenres(): NetworkResponse<GenresResponse>
}