package com.codandotv.streamplayerapp.feature_list_streams.list.domain.model

data class Stream(
    val id : String,
    val name : String,
    val description : String,
    val posterPathUrl: String,
)
data class ListStream(
    val categoryName: String,
    val streams: List<Stream>
)