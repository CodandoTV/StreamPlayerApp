package com.codandotv.streamplayerapp.feature_list_streams.search.domain.mapper

import com.codandotv.streamplayerapp.core_networking.Url
import com.codandotv.streamplayerapp.feature_list_streams.search.data.model.ListSearchStreamResponse.SearchStreamResponse
import com.codandotv.streamplayerapp.feature_list_streams.search.presentation.widgets.SearchStreamCardModel

fun SearchStreamResponse.toSearchStreamCardModel() = SearchStreamCardModel(
    id = id,
    title = title,
    url = "${Url.IMAGE_URL_SIZE_200}${posterPath}"
)
