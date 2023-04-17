package com.codandotv.streamplayerapp.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity


class ListMovieActivity  : AppCompatActivity(){
    val viewModel = ListMovieViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.curtaVideo()
    }
}