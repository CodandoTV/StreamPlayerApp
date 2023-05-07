package com.codandotv.streamplayerapp.feature_list_streams.presentation.screens

import com.codandotv.streamplayerapp.feature_list_streams.presentation.widgets.StreamsCardContent

data class CarouselData(
    val categoryName: String,
    val cards: List<StreamsCardContent>
)

data class ListStreamsUIState(
    val carousels: List<CarouselData>
)