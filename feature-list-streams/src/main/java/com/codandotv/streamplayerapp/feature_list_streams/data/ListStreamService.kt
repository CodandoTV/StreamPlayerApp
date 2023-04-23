package com.codandotv.streamplayerapp.feature_list_streams.data

import com.codandotv.streamplayerapp.feature_list_streams.data.model.ListStreamResponse

interface ListStreamService {
    fun getMovies() : ListStreamResponse
}