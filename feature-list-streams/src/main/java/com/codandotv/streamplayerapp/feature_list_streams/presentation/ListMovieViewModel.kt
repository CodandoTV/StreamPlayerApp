package com.codandotv.streamplayerapp.feature_list_streams.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.codandotv.streamplayerapp.core_networking.handleError.onError
import com.codandotv.streamplayerapp.feature_list_streams.domain.ListStreamAnalytics
import com.codandotv.streamplayerapp.feature_list_streams.domain.ListStreamUseCase
import com.codandotv.streamplayerapp.feature_list_streams.domain.model.ListStream
import kotlinx.coroutines.launch

sealed class ListStreamState {
    data class Success(val listMovie: ListStream)
}

class ListMovieViewModel(
        private val uiModel: ListStreamUimodel,
        private val useCase: ListStreamUseCase,
        private val analytics: ListStreamAnalytics
) : ViewModel() {
    fun curtaVideo() {
        viewModelScope.launch {
            useCase.getMovies()
                .onSuccess {
                    println(">>>> $it")
                }
                .onError {
                    println(">>>>error ${it.errorMessage}")
                }
        }
    }
}