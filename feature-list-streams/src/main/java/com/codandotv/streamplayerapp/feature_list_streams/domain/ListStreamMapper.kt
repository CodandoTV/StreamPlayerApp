package com.codandotv.streamplayerapp.feature_list_streams.domain

import com.codandotv.streamplayerapp.feature_list_streams.data.model.ListStreamResponse
import com.codandotv.streamplayerapp.feature_list_streams.domain.model.ListStream
import com.codandotv.streamplayerapp.feature_list_streams.domain.model.Stream

fun ListStreamResponse.toListStream(): ListStream =
    ListStream(
        streams = this.results.map {
            Stream(
                description = it.overview,
                name = it.title,
                posterPathUrl =  "https://image.tmdb.org/t/p/w300${it.poster_path}"
            )
        }
    )