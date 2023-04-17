package com.codandotv.streamplayerapp.presentation

import com.codandotv.streamplayerapp.domain.ListMovieAnalytics
import com.codandotv.streamplayerapp.domain.ListMovieUseCase
import com.codandotv.streamplayerapp.domain.model.ListMovie

sealed class ListMovieState {
    data class Success(val listMovie : ListMovie)
}

class ListMovieViewModel(
    private val uiModel: ListMovieUimodel,
    private val useCase: ListMovieUseCase,
    private val analytics: ListMovieAnalytics
) {
    fun curtaVideo() {
        useCase.getMovies()
    }
}