package com.codandotv.streamplayerapp.feature_list_streams.list.presentation.screens

import com.codandotv.streamplayerapp.feature_list_streams.list.domain.model.Genre

data class ListStreamsUIState(
    val genres: List<Genre>,
    val isLoading: Boolean
)