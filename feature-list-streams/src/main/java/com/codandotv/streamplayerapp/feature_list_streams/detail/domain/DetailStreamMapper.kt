package com.codandotv.streamplayerapp.feature_list_streams.detail.domain

import com.codandotv.streamplayerapp.core_local_storage.domain.model.MovieEntity
import com.codandotv.streamplayerapp.core_networking.Url.IMAGE_URL_SIZE_500
import com.codandotv.streamplayerapp.feature_list_streams.detail.data.model.DetailStreamResponse
import com.codandotv.streamplayerapp.feature_list_streams.detail.data.model.VideoStreamsResponse

fun DetailStreamResponse.toDetailStream(isFavorite: Boolean = false): DetailStream =
    DetailStream(
        id = this.id,
        title = this.title,
        overview = this.overview,
        tagline = this.tagline,
        url = "$IMAGE_URL_SIZE_500${this.backdrop_path}",
        releaseYear = this.release_date.substring(0, 4),
        isFavorite = isFavorite
    )

fun DetailStream.toDetailStreamLocal(): MovieEntity =
    MovieEntity(
        id = this.id,
        title = this.title,
        overview = this.overview,
        tagline = this.tagline,
        url = this.url,
        releaseYear = this.releaseYear,
    )

fun VideoStreamsResponse.toVideoStreams(): List<VideoStream> =
    results.map {
        VideoStream(
            videoId = it.key,
            movieId = this.id
        )
    }
