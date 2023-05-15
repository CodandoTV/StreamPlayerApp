package com.codandotv.streamplayerapp.feature_list_streams.list.data

import com.codandotv.streamplayerapp.core_networking.handleError.NetworkResponse
import com.codandotv.streamplayerapp.feature_list_streams.list.data.model.GenresResponse
import com.codandotv.streamplayerapp.feature_list_streams.list.data.model.ListStreamResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ListStreamService {
    @GET("discover/movie")
    suspend fun getMovies(@Query("with_genres") genres: String) : NetworkResponse<ListStreamResponse>

    @GET("genre/movie/list")
    suspend fun getGenres(): NetworkResponse<GenresResponse>
}