package com.codandotv.streamplayerapp.feature_list_streams.presentation

import android.content.res.Resources
import com.codandotv.streamplayerapp.feature_list_streams.domain.model.ListStream
import com.codandotv.streamplayerapp.feature_list_streams.presentation.screens.CarouselData
import com.codandotv.streamplayerapp.feature_list_streams.presentation.screens.ListStreamsUIState
import com.codandotv.streamplayerapp.feature_list_streams.presentation.widgets.StreamsCardContent

interface ListStreamUimodel {
    fun convertToCardContent(listStream: List<ListStream>): ListStreamsUIState
}

class ListStreamUiModelImpl(
    private val resources: Resources
) : ListStreamUimodel {
    override fun convertToCardContent(listStream: List<ListStream>): ListStreamsUIState {
        return ListStreamsUIState(
            isLoading = false,
            carousels = listStream.map {
                CarouselData(
                    categoryName = it.categoryName,
                    cards = it.streams.map { stream ->
                        StreamsCardContent(
                            contentDescription = stream.name,
                            url = stream.posterPathUrl
                        )
                    }
                )
            }
        )
    }
}