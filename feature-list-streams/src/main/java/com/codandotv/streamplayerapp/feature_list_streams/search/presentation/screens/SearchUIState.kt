package com.codandotv.streamplayerapp.feature_list_streams.search.presentation.screens

import com.codandotv.streamplayerapp.feature_list_streams.list.data.model.ListStreamResponse

sealed class SearchUIState {
    data class Success(val listCharacters: ListStreamResponse): SearchUIState()
    data class Error(val messageError: String = String()): SearchUIState()
    data class Loading(val isLoading: Boolean): SearchUIState()

}