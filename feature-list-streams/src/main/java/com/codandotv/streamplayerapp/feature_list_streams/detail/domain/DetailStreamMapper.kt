package com.codandotv.streamplayerapp.feature_list_streams.detail.domain

import com.codandotv.streamplayerapp.core_networking.Url.IMAGE_URL_SIZE_500
import com.codandotv.streamplayerapp.feature_list_streams.detail.data.model.DetailStreamResponse

fun DetailStreamResponse.toDetailStream(): DetailStream =
    DetailStream(
        id = this.id,
        title = this.title,
        overview = this.overview,
        tagline = this.tagline,
        imagePath = "$IMAGE_URL_SIZE_500${this.backdrop_path}"
    )