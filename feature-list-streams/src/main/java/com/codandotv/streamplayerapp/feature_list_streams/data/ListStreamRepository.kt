package com.codandotv.streamplayerapp.feature_list_streams.data

import com.codandotv.streamplayerapp.feature_list_streams.domain.model.ListStream

interface ListStreamRepository {
    fun getMovies() : ListStream
}

class ListStreamRepositoryImpl(
//    private val service : ListStreamService
) : ListStreamRepository {

    override fun getMovies(): ListStream = ListStream("")
}