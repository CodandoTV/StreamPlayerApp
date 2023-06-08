package com.codandotv.streamplayerapp.feature_list_streams.list.presentation

import android.content.res.Resources
import com.codandotv.streamplayerapp.feature.list.streams.R
import com.codandotv.streamplayerapp.feature_list_streams.core.ContentType
import com.codandotv.streamplayerapp.feature_list_streams.list.domain.model.HighlightBanner
import com.codandotv.streamplayerapp.feature_list_streams.list.domain.model.IconAndTextInfo
import com.codandotv.streamplayerapp.feature_list_streams.list.domain.model.ListStream
import com.codandotv.streamplayerapp.feature_list_streams.list.presentation.screens.CarouselData
import com.codandotv.streamplayerapp.feature_list_streams.list.presentation.screens.ListStreamsUIState
import com.codandotv.streamplayerapp.feature_list_streams.list.presentation.widgets.StreamsCardContent
import com.codandotv.streamplayerapp.core.shared.ui.R as SharedUiR

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
            },
            highlightBanner = getHighlightBanner(listStream)
        )
    }

    private fun getHighlightBanner(listStream: List<ListStream>): HighlightBanner? {
        if (listStream.isEmpty()) return null

        val item = listStream.random().streams.random()

        return HighlightBanner(
            name = item.name,
            imageUrl = item.posterPathUrl,
            contentType = ContentType.getContentName(ContentType.FILM),
            contentTypeAsPlural = ContentType.getContentNameAsPlural(ContentType.FILM),
            extraInfo = IconAndTextInfo(
                R.drawable.ic_top_10,
                R.string.list_highlight_banner_stream_ranking
            ),
            leftButton = IconAndTextInfo(SharedUiR.drawable.ic_add, R.string.list_highlight_banner_add),
            centralButton = IconAndTextInfo(SharedUiR.drawable.ic_play, R.string.list_highlight_banner_watch),
            rightButton = IconAndTextInfo(SharedUiR.drawable.ic_info, R.string.list_highlight_banner_info),
        )
    }
}