package com.codandotv.streamplayerapp.feature_list_streams.detail.presentation.screens

import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.codandotv.streamplayerapp.core_networking.handleError.catchFailure
import com.codandotv.streamplayerapp.feature_list_streams.detail.domain.DetailStream
import com.codandotv.streamplayerapp.feature_list_streams.detail.domain.DetailStreamUseCase
import com.codandotv.streamplayerapp.feature_list_streams.detail.presentation.screens.DetailStreamsUIState.DetailStreamsLoadedUIState
import com.codandotv.streamplayerapp.feature_list_streams.detail.presentation.screens.DetailStreamsUIState.LoadingStreamUIState
import kotlinx.coroutines.flow.*
import com.codandotv.streamplayerapp.feature_list_streams.detail.domain.VideoStreamsUseCase
import com.codandotv.streamplayerapp.feature_list_streams.detail.presentation.screens.DetailStreamsUIState.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.flow.zip
import kotlinx.coroutines.launch

class DetailStreamViewModel(
    private val detailStreamUseCase: DetailStreamUseCase,
    private val videoStreamsUseCase: VideoStreamsUseCase,
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
            detailStreamUseCase.getMovie()
                .zip(videoStreamsUseCase.getVideoStreams()) { detailStream, videoUrl ->
                    DetailStreamsLoadedUIState(
                        detailStream = detailStream,
                        videoId = videoUrl.firstOrNull()?.videoId
                    )
                }
                .onStart { onLoading() }
                .catchFailure {
                    println(">>>> ${it.errorMessage}")
                }
                .collect { result ->
                    _uiState.update {
                        result
                    }
                }
        }
    }

    private fun onLoading() {
        _uiState.update { LoadingStreamUIState }
    }

    fun toggleItemInFavorites(detailStream: DetailStream) {
        viewModelScope.launch {
            detailStreamUseCase.toggleItemInFavorites(detailStream)
        }
    }
}