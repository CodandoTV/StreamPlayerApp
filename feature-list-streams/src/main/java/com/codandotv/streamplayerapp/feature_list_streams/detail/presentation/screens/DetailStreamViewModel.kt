package com.codandotv.streamplayerapp.feature_list_streams.detail.presentation.screens

import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.codandotv.streamplayerapp.core_networking.handleError.catchFailure
import com.codandotv.streamplayerapp.feature_list_streams.detail.domain.DetailStreamUseCase
import com.codandotv.streamplayerapp.feature_list_streams.detail.presentation.screens.DetailStreamsUIState.DetailStreamsLoadedUIState
import com.codandotv.streamplayerapp.feature_list_streams.detail.presentation.screens.DetailStreamsUIState.LoadingStreamUIState
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class DetailStreamViewModel(
    private val useCase: DetailStreamUseCase,
) : ViewModel(), DefaultLifecycleObserver {

    private val _uiState = MutableStateFlow<DetailStreamsUIState>(LoadingStreamUIState)
    val uiState: StateFlow<DetailStreamsUIState> = _uiState.stateIn(
        viewModelScope,
        SharingStarted.Eagerly,
        initialValue = _uiState.value
    )

    override fun onCreate(owner: LifecycleOwner) {
        super.onCreate(owner)

        viewModelScope.launch {
            useCase.getMovie()
                .onStart { onLoading() }
                .catchFailure {
                    println(">>>> ${it.errorMessage}")
                }
                .collect { detailStream ->
                    _uiState.update {
                        DetailStreamsLoadedUIState(
                            detailStream = detailStream
                        )
                    }
                }
        }
    }

    private fun onLoading() {
        _uiState.update { LoadingStreamUIState }
    }

    fun addToMyList(detailStreamId: String) {
        viewModelScope.launch {
            useCase.insertToFavorites(detailStreamId)
        }
    }
}