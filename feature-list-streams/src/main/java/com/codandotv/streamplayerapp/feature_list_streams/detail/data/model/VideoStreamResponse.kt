package com.codandotv.streamplayerapp.feature_list_streams.detail.data.model

data class VideoStreamResponse(
    val id: String,
    val name: String,
    val key: String,
    val site: String,
    val size: Int,
    val official: Boolean,
    val type: String,
)

data class VideoStreamsResponse(
    val id: Long,
    val results: List<VideoStreamResponse>
)