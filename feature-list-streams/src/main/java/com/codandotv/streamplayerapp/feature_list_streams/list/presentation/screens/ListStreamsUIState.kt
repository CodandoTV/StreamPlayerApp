package com.codandotv.streamplayerapp.feature_list_streams.list.presentation.screens

import com.codandotv.streamplayerapp.feature_list_streams.detail.presentation.screens.CarouselData
import com.codandotv.streamplayerapp.feature_list_streams.list.presentation.widgets.StreamsCardContent

data class CarouselData(
    val categoryName: String,
    val cards: List<StreamsCardContent>
)

data class ListStreamsUIState(
    val carousels: List<CarouselData>,
    val isLoading: Boolean
)