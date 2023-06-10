package com.codandotv.streamplayerapp.feature_list_streams.list.presentation.screens

import com.codandotv.streamplayerapp.feature_list_streams.list.domain.model.Genre
import com.codandotv.streamplayerapp.feature_list_streams.list.domain.model.HighlightBanner

data class ListStreamsUIState(
    val highlightBanner: HighlightBanner? = null,
    val genres: List<Genre>,
    val isLoading: Boolean
)