package com.codandotv.streamplayerapp.data

import com.codandotv.streamplayerapp.domain.model.ListMovie
import com.codandotv.streamplayerapp.domain.toListMovie

interface ListMovieRepository {
    fun getMovies() : ListMovie
}

class ListMovieRepositoryImpl(
    private val service : ListMovieService
) : ListMovieRepository {

    override fun getMovies(): ListMovie =
        service.getMovies()
            .toListMovie()
}