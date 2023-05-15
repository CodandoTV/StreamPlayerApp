package com.codandotv.streamplayerapp.feature_list_streams.list.presentation.screens

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.codandotv.streamplayerapp.core_networking.handleError.catchFailure
import com.codandotv.streamplayerapp.feature_list_streams.list.domain.ListStreamUseCase
import com.codandotv.streamplayerapp.feature_list_streams.list.presentation.ListStreamUimodel
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class ListStreamViewModel(
    private val uiModel: ListStreamUimodel,
    private val useCase: ListStreamUseCase,
) : ViewModel(), DefaultLifecycleObserver {

    val uiState = mutableStateOf(
        ListStreamsUIState(
            carousels = emptyList(),
            isLoading = false
        )
    )

    override fun onCreate(owner: LifecycleOwner) {
        super.onCreate(owner)
        viewModelScope.launch {
            if (uiState.value.carousels.isEmpty()) {
                useCase.getMovies()
                    .onStart { onLoading() }
                    .catchFailure {
                        println(">>>> ${it.errorMessage}")
                    }
                    .onCompletion { loaded() }
                    .collect {
                        this@ListStreamViewModel.uiState.value = uiModel.convertToCardContent(it)
                    }
            }
        }
    }

    private fun loaded() {
        this.uiState.value = this.uiState.value.copy(
            isLoading = false
        )
    }

    private fun onLoading() {
        this.uiState.value = this.uiState.value.copy(
            isLoading = true
        )
    }
}