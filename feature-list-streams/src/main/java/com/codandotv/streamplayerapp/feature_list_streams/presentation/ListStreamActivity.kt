package com.codandotv.streamplayerapp.feature_list_streams.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.codandotv.streamplayerapp.feature_list_streams.R
import com.codandotv.streamplayerapp.feature_list_streams.di.ListStreamModule
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.core.context.unloadKoinModules

class ListStreamActivity  : AppCompatActivity() {
    private val viewModel : ListMovieViewModel by viewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_stream)
        loadKoinModules(ListStreamModule.module)
        viewModel.curtaVideo()
    }

    override fun onDestroy() {
        unloadKoinModules(ListStreamModule.module)
        super.onDestroy()
    }
}