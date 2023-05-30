package com.codandotv.streamplayerapp.feature_list_streams.list.presentation.screens

import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.map
import com.codandotv.streamplayerapp.core_networking.handleError.catchFailure
import com.codandotv.streamplayerapp.feature_list_streams.list.domain.ListGenresUseCase
import com.codandotv.streamplayerapp.feature_list_streams.list.domain.ListStreamUseCase
import com.codandotv.streamplayerapp.feature_list_streams.list.domain.model.Genre
import com.codandotv.streamplayerapp.feature_list_streams.list.presentation.widgets.StreamsCardContent
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ListStreamViewModel(
    private val listStreams: ListStreamUseCase,
    private val listGenres: ListGenresUseCase,
) : ViewModel(), DefaultLifecycleObserver {

    private val _uiState = MutableStateFlow(
        ListStreamsUIState(
            genres = emptyList(),
            isLoading = false
        )
    )
    val uiState = _uiState.stateIn(
        viewModelScope,
        SharingStarted.Eagerly,
        initialValue = _uiState.value
    )

    override fun onCreate(owner: LifecycleOwner) {
        super.onCreate(owner)
        viewModelScope.launch {
            if (uiState.value.genres.isEmpty()) {
                listGenres()
                    .onStart { onLoading() }
                    .catchFailure {
                        println(">>>> ${it.errorMessage}")
                    }
                    .onCompletion { loaded() }
                    .collect { genres ->
                        _uiState.update {
                            it.copy(genres = genres)
                        }
                    }
            }
        }
    }

    fun loadMovies(genre: Genre): Flow<PagingData<StreamsCardContent>> {
        return listStreams(genre).map {
            it.map {stream ->
                StreamsCardContent(
                    contentDescription = stream.name,
                    url = stream.posterPathUrl,
                    id = stream.id
                )
            }
        }
    }

    private fun loaded() {
        this._uiState.update {
            it.copy(isLoading = false)
        }
    }

    private fun onLoading() {
        this._uiState.update {
            it.copy(isLoading = true)
        }
    }
}