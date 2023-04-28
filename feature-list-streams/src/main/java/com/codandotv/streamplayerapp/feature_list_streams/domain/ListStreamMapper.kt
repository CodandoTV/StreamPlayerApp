package com.codandotv.streamplayerapp.feature_list_streams.domain

import com.codandotv.streamplayerapp.feature_list_streams.data.model.ListStreamResponse
import com.codandotv.streamplayerapp.feature_list_streams.domain.model.ListStream

fun ListStreamResponse.toListStream() : ListStream =
    ListStream(
        name = this.title,
        description = this.overview
    )