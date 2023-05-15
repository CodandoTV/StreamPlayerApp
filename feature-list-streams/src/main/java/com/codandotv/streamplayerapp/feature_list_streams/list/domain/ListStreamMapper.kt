package com.codandotv.streamplayerapp.feature_list_streams.list.domain

import com.codandotv.streamplayerapp.core_networking.Url
import com.codandotv.streamplayerapp.feature_list_streams.list.data.model.ListStreamResponse
import com.codandotv.streamplayerapp.feature_list_streams.list.domain.model.ListStream
import com.codandotv.streamplayerapp.feature_list_streams.list.domain.model.Stream

fun ListStreamResponse.toListStream(genre: String): ListStream =
    ListStream(
        categoryName = genre,
        streams = this.results.map {
            Stream(
                description = it.overview,
                name = it.title,
                posterPathUrl = "${Url.IMAGE_URL_SIZE_300}${it.poster_path}",
                id = it.id
            )
        }
    )