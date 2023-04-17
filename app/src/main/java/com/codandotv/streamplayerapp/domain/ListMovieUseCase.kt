package com.codandotv.streamplayerapp.domain

import com.codandotv.streamplayerapp.data.ListMovieRepository
import com.codandotv.streamplayerapp.domain.model.ListMovie

interface ListMovieUseCase {
    fun getMovies() : ListMovie
}

class ListMovieUseCaseImpl(
    private val repository: ListMovieRepository
) : ListMovieUseCase{

    override fun getMovies(): ListMovie =
        repository.getMovies()

}