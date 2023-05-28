package com.codandotv.streamplayerapp.feature_list_streams.list.presentation

import android.content.res.Resources
import com.codandotv.streamplayerapp.core_shared_ui.R.*
import com.codandotv.streamplayerapp.feature_list_streams.R
import com.codandotv.streamplayerapp.feature_list_streams.core.ContentType
import com.codandotv.streamplayerapp.feature_list_streams.list.domain.model.HighlightBanner
import com.codandotv.streamplayerapp.feature_list_streams.list.domain.model.IconAndTextInfo
import com.codandotv.streamplayerapp.feature_list_streams.list.domain.model.ListStream
import com.codandotv.streamplayerapp.feature_list_streams.list.presentation.screens.CarouselData
import com.codandotv.streamplayerapp.feature_list_streams.list.presentation.screens.ListStreamsUIState
import com.codandotv.streamplayerapp.feature_list_streams.list.presentation.widgets.StreamsCardContent

interface ListStreamUimodel {
    fun convertToCardContent(listStream: List<ListStream>): ListStreamsUIState
}

class ListStreamUiModelImpl(
    private val resources: Resources
) : ListStreamUimodel {
    override fun convertToCardContent(listStream: List<ListStream>): ListStreamsUIState {
        val item = listStream.random().streams.random()
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
            highlightBanner = HighlightBanner(
                name = item.name,
                imageUrl = item.posterPathUrl,
                contentType = ContentType.getContentName(ContentType.FILM),
                contentTypeAsPlural = ContentType.getContentNameAsPlural(ContentType.FILM),
                extraInfo = IconAndTextInfo(R.drawable.ic_top_10, R.string.highlight_banner_stream_ranking),
                leftButton = IconAndTextInfo(drawable.ic_add, R.string.highlight_banner_add),
                centralButton = IconAndTextInfo(drawable.ic_play, R.string.highlight_banner_watch),
                rightButton = IconAndTextInfo(drawable.ic_info, R.string.highlight_banner_info),
            )
        )
    }
}