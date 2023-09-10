package com.codandotv.streamplayerapp.feature_list_streams.detail.domain

import com.codandotv.streamplayerapp.core_networking.Url.IMAGE_URL_SIZE_500
import com.codandotv.streamplayerapp.feature_list_streams.detail.data.model.DetailStreamResponse
import com.codandotv.streamplayerapp.feature_list_streams.detail.data.model.VideoStreamsResponse

fun DetailStreamResponse.toDetailStream(): DetailStream =
    DetailStream(
        id = this.id,
        title = this.title,
        overview = this.overview,
        tagline = this.tagline,
        url = "$IMAGE_URL_SIZE_500${this.backdrop_path}",
        releaseYear = this.release_date.substring(0, 4)
    )

fun VideoStreamsResponse.toVideoStreams(): List<VideoStream> =
    results.map {
        VideoStream(
            videoId = it.key,
            movieId = this.id
        )
    }