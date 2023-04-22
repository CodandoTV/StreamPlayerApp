package com.codandotv.streamplayerapp.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import com.codandotv.streamplayerapp.R
import com.codandotv.streamplayerapp.di.ListMovieModule
import com.codandotv.streamplayerapp.domain.model.ListMovie
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.core.context.unloadKoinModules
import org.koin.dsl.module

class ListMovieActivity  : AppCompatActivity() {
    private val viewModel : ListMovieViewModel by viewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        loadKoinModules(ListMovieModule.module)
        viewModel.curtaVideo()
    }

    override fun onDestroy() {
        unloadKoinModules(ListMovieModule.module)
        super.onDestroy()
    }
}