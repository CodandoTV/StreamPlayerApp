package com.codandotv.streamplayerapp.feature_list_streams.presentation.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.codandotv.streamplayerapp.core_networking.handleError.catchFailure
import com.codandotv.streamplayerapp.feature_list_streams.domain.ListStreamAnalytics
import com.codandotv.streamplayerapp.feature_list_streams.domain.ListStreamUseCase
import com.codandotv.streamplayerapp.feature_list_streams.domain.model.ListStream
import com.codandotv.streamplayerapp.feature_list_streams.presentation.ListStreamUimodel
import kotlinx.coroutines.launch

sealed class ListStreamState {
    data class Success(val listMovie: ListStream)
}

class ListStreamViewModel(
    private val uiModel: ListStreamUimodel,
    private val useCase: ListStreamUseCase,
    private val analytics: ListStreamAnalytics
) : ViewModel() {
    fun curtaVideo() {
        viewModelScope.launch {
            useCase.getMovies()
                .catchFailure {
                    println(">>>> ${it.errorMessage}")
                }
                .collect {
                    println(">>> $it")
                }
        }
    }
}