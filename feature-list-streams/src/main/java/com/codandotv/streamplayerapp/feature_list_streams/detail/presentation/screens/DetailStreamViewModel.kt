package com.codandotv.streamplayerapp.feature_list_streams.detail.presentation.screens

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.codandotv.streamplayerapp.core_networking.handleError.catchFailure
import com.codandotv.streamplayerapp.feature_list_streams.detail.domain.DetailStreamUseCase
import com.codandotv.streamplayerapp.feature_list_streams.list.presentation.ListStreamUimodel
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class DetailStreamViewModel(
    private val useCase: DetailStreamUseCase,
) :  ViewModel(), DefaultLifecycleObserver {

    private val _uiState = mutableStateOf(
        DetailStreamsUIState(
            isLoading = false
        )
    )
    val uiState : State<DetailStreamsUIState> = _uiState

    override fun onCreate(owner: LifecycleOwner) {
        super.onCreate(owner)

        viewModelScope.launch {
            useCase.getMovie()
                .onStart { onLoading() }
                .catchFailure {
                    println(">>>> ${it.errorMessage}")
                }
                .onCompletion { loaded() }
                .collect {
                    _uiState.value = _uiState.value.copy(
                        detailStream = it
                    )
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