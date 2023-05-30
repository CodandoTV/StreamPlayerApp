package com.codandotv.streamplayerapp.feature_list_streams.list.presentation.screens

import android.os.Parcelable
import com.codandotv.streamplayerapp.feature_list_streams.list.domain.model.HighlightBanner
import com.codandotv.streamplayerapp.feature_list_streams.list.presentation.widgets.StreamsCardContent
import kotlinx.parcelize.Parcelize

@Parcelize
data class CarouselData(
    val categoryName: String,
    val cards: List<StreamsCardContent>
) : Parcelable

@Parcelize
data class ListStreamsUIState(
    val highlightBanner: HighlightBanner? = null,
    val carousels: List<CarouselData>,
    val isLoading: Boolean
) : Parcelable