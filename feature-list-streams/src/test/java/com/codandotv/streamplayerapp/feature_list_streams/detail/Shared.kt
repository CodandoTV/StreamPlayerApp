package com.codandotv.streamplayerapp.feature_list_streams.detail

import com.codandotv.streamplayerapp.feature_list_streams.detail.data.model.DetailStreamResponse
import com.codandotv.streamplayerapp.feature_list_streams.detail.domain.DetailStream
import com.codandotv.streamplayerapp.feature_list_streams.detail.domain.VideoStream

val videoStream = VideoStream(
    movieId = 123,
    videoId = "123"
)

val movieIdString = "123"

val videoStream1 = VideoStream(
    movieId = 1234565,
    videoId = "123565"
)

val videosStreamsList = listOf(
    videoStream,
    videoStream1
)

val detailStreamResponse = DetailStreamResponse(
    id = "id",
    title = "title",
    overview = "overview",
    tagline = "tagline",
    backdrop_path = "backdrop",
    release_date = "release"
)

val detailStream = DetailStream(
    id = "id",
    title = "title",
    overview = "overview",
    tagline = "tagline",
    isFavorite = false,
    releaseYear = "backdrop",
    url = "palmeiras"
)