package com.codandotv.streamplayerapp.feature_list_streams.detail.data.model

data class DetailStreamResponse(
    val id : String,
    val title : String,
    val overview : String,
    val tagline : String,
    val backdrop_path : String,
    val release_date : String
)