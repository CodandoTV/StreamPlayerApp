package com.codandotv.streamplayerapp.domain

import com.codandotv.streamplayerapp.data.model.ListMovieResponse
import com.codandotv.streamplayerapp.domain.model.ListMovie

fun ListMovieResponse.toListMovie() : ListMovie =
    ListMovie(
        name = this.name
    )