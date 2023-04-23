package com.codandotv.streamplayerapp.feature_list_streams.presentation

import androidx.lifecycle.ViewModel
import com.codandotv.streamplayerapp.feature_list_streams.domain.ListStreamUseCase
import com.codandotv.streamplayerapp.feature_list_streams.domain.model.ListStream

sealed class ListStreamState {
    data class Success(val listMovie: ListStream)
}

class ListMovieViewModel(
        private val uiModel: ListStreamUimodel,
        private val useCase: ListStreamUseCase,
        private val analytics: com.codandotv.streamplayerapp.feature_list_streams.domain.ListStreamAnalytics
) : ViewModel() {
    fun curtaVideo() {
        useCase.getMovies()
    }
}