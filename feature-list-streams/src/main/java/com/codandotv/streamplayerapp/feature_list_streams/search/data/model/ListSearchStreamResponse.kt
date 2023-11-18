package com.codandotv.streamplayerapp.feature_list_streams.search.data.model

import com.squareup.moshi.Json

data class ListSearchStreamResponse(
    @Json(name = "results")
    val results: List<SearchStreamResponse>
) {
    data class SearchStreamResponse(
        @Json(name = "id")
        val id: String,
        @Json(name = "title")
        val title: String,
        @Json(name="overview")
        val overview: String,
        @Json(name = "poster_path")
        val posterPath: String,
    )
}
