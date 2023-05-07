package com.codandotv.streamplayerapp.feature_list_streams.domain.model

data class Stream(
    val name : String,
    val description : String,
    val posterPathUrl: String,
)
data class ListStream(
    val streams: List<Stream>
)