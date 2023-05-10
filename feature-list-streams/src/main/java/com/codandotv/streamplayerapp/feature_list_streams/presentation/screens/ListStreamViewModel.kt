package com.codandotv.streamplayerapp.feature_list_streams.presentation.screens

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.codandotv.streamplayerapp.core_networking.handleError.catchFailure
import com.codandotv.streamplayerapp.feature_list_streams.domain.ListStreamAnalytics
import com.codandotv.streamplayerapp.feature_list_streams.domain.ListStreamUseCase
import com.codandotv.streamplayerapp.feature_list_streams.presentation.ListStreamUimodel
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class ListStreamViewModel(
    private val uiModel: ListStreamUimodel,
    private val useCase: ListStreamUseCase,
    private val analytics: ListStreamAnalytics
) : ViewModel() {

    private val _uiState = mutableStateOf(
        ListStreamsUIState(
            carousels = emptyList(),
            isLoading = false
        )
    )
    val uiState : State<ListStreamsUIState> = _uiState

    init {
        viewModelScope.launch {
            useCase.getMovies()
                .onStart { onLoading() }
                .catchFailure {
                    println(">>>> ${it.errorMessage}")
                }
                .onCompletion { loaded() }
                .collect {
                    _uiState.value = uiModel.convertToCardContent(it)
                }
        }
    }

    private fun loaded() {
        _uiState.value = _uiState.value.copy(
            isLoading = false
        )
    }

    private fun onLoading() {
        _uiState.value = _uiState.value.copy(
            isLoading = true
        )
    }
}