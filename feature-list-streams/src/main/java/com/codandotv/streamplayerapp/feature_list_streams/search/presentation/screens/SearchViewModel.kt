package com.codandotv.streamplayerapp.feature_list_streams.search.presentation.screens

import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.codandotv.streamplayerapp.core_networking.handleError.catchFailure
import com.codandotv.streamplayerapp.feature_list_streams.search.domain.MostPopularMoviesUseCase
import com.codandotv.streamplayerapp.feature_list_streams.search.domain.SearchUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SearchViewModel(
    private val searchUseCase: SearchUseCase,
    private val mostPopularMoviesUseCase: MostPopularMoviesUseCase
) : ViewModel(), DefaultLifecycleObserver {

    private val _uiState = MutableStateFlow<SearchUIState>(SearchUIState.Loading(true))
    val uiState: StateFlow<SearchUIState> = _uiState.stateIn(
        scope = viewModelScope,
        started = SharingStarted.Eagerly,
        initialValue = _uiState.value
    )

    private val _currentSearchText = MutableStateFlow("")
    val currentSearchText = _currentSearchText.asStateFlow()

    init {
        fetchMostPopularMovies()
    }

    fun fetchMovies() {
        if (_currentSearchText.value.isBlank()) {
            fetchMostPopularMovies()
        } else {
            fetchMovieByQuery()
        }
    }

    private fun fetchMovieByQuery() {
        viewModelScope.launch {
            searchUseCase(
                query = _currentSearchText.value
            ).onStart {
                SearchUIState.Loading(true)
            }.catchFailure {
                println(">>>> ${it.errorMessage}")
            }.collect { result ->
                _uiState.update {
                    SearchUIState.Success(result)
                }
            }
        }
    }

    private fun fetchMostPopularMovies() {
        viewModelScope.launch {
            mostPopularMoviesUseCase().onStart {
                SearchUIState.Loading(true)
            }.catchFailure {
                println(">>>> ${it.errorMessage}")
            }.collect { result ->
                _uiState.update {
                    SearchUIState.Success(result)
                }
            }
            SearchUIState.Loading(false)
        }
    }

    fun setCurrentSearchText(newText: String) {
        _currentSearchText.value = newText
    }

    fun onCleanText() {
        _currentSearchText.value = ""
        fetchMostPopularMovies()
    }
}
