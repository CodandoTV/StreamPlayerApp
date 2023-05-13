package com.codandotv.streamplayerapp.feature_list_streams.detail.presentation.screens

import com.codandotv.streamplayerapp.feature_list_streams.detail.domain.DetailStream

sealed class DetailStreamsUIState {
    data class DetailStreamsLoadedUIState(
        val detailStream: DetailStream,
    ) : DetailStreamsUIState()

    object LoadingStreamUIState : DetailStreamsUIState()
}
