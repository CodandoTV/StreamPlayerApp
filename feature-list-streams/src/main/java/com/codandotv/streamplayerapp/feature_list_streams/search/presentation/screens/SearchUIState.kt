package com.codandotv.streamplayerapp.feature_list_streams.search.presentation.screens

import com.codandotv.streamplayerapp.feature_list_streams.search.data.model.ListSearchStreamResponse

sealed class SearchUIState {
    data class Success(val listCharacters: ListSearchStreamResponse): SearchUIState()
    data class Error(val messageError: String = String()): SearchUIState()
    data class Loading(val isLoading: Boolean): SearchUIState()
    data class Empty(val isEmpty: Boolean): SearchUIState()

}
