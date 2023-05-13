package com.codandotv.streamplayerapp.feature_list_streams.list.presentation

import android.content.res.Resources
import com.codandotv.streamplayerapp.feature_list_streams.detail.presentation.screens.CarouselData
import com.codandotv.streamplayerapp.feature_list_streams.list.domain.model.ListStream
import com.codandotv.streamplayerapp.feature_list_streams.list.presentation.screens.ListStreamsUIState
import com.codandotv.streamplayerapp.feature_list_streams.list.presentation.widgets.StreamsCardContent

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
            CarouselData(categoryName = it.categoryName,
                cards = it.streams.map { stream ->
                    StreamsCardContent(
                        contentDescription = stream.name,
                        url = stream.posterPathUrl,
                        id = stream.id
                    )
                })
        })
    }
}