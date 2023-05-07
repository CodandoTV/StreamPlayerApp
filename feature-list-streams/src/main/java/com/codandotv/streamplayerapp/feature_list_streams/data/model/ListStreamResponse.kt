package com.codandotv.streamplayerapp.feature_list_streams.data.model

data class StreamResponse(
    val title : String,
    val overview : String,
    val poster_path: String,
)
data class ListStreamResponse(
    val results: List<StreamResponse>
)