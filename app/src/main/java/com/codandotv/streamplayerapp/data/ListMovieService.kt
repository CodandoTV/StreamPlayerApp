package com.codandotv.streamplayerapp.data

import com.codandotv.streamplayerapp.data.model.ListMovieResponse

interface ListMovieService {
    fun getMovies() : ListMovieResponse
}