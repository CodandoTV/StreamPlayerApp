package com.codandotv.streamplayerapp.feature_list_streams.detail.presentation.screens

import com.codandotv.streamplayerapp.feature_list_streams.detail.domain.DetailStream
import com.codandotv.streamplayerapp.feature_list_streams.list.presentation.widgets.StreamsCardContent

data class CarouselData(
    val categoryName: String,
    val cards: List<StreamsCardContent>
)

data class DetailStreamsUIState(
    val detailStream: DetailStream? = null,
    val isLoading: Boolean,
)