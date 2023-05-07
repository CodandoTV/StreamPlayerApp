package com.codandotv.streamplayerapp.feature_list_streams.presentation

import android.content.res.Resources
import com.codandotv.streamplayerapp.feature_list_streams.domain.model.ListStream
import com.codandotv.streamplayerapp.feature_list_streams.presentation.screens.CarouselData
import com.codandotv.streamplayerapp.feature_list_streams.presentation.screens.ListStreamsUIState
import com.codandotv.streamplayerapp.feature_list_streams.presentation.widgets.StreamsCardContent

interface ListStreamUimodel {
    fun convertToCardContent(listStream: ListStream): ListStreamsUIState
}

class ListStreamUiModelImpl(
    private val resources: Resources
) : ListStreamUimodel {
    override fun convertToCardContent(listStream: ListStream): ListStreamsUIState {
        return ListStreamsUIState(
            carousels = listOf(
                CarouselData(
                    categoryName = "",
                    cards = listStream.streams.map {
                        StreamsCardContent(
                            contentDescription = it.name,
                            url = it.posterPathUrl
                        )
                    }
                )
            )
        )
    }
}