package com.codandotv.streamplayerapp.di

import com.codandotv.streamplayerapp.data.ListMovieRepository
import com.codandotv.streamplayerapp.data.ListMovieRepositoryImpl
import com.codandotv.streamplayerapp.domain.ListMovieAnalytics
import com.codandotv.streamplayerapp.domain.ListMovieAnalyticsImpl
import com.codandotv.streamplayerapp.domain.ListMovieUseCase
import com.codandotv.streamplayerapp.domain.ListMovieUseCaseImpl
import com.codandotv.streamplayerapp.presentation.ListMovieUiModelImpl
import com.codandotv.streamplayerapp.presentation.ListMovieUimodel
import com.codandotv.streamplayerapp.presentation.ListMovieViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object ListMovieModule {
    val module = module {
        viewModel {
            ListMovieViewModel(
                uiModel = get(),
                useCase = get(),
                analytics = get()
            )
        }
        factory<ListMovieUseCase> {
            ListMovieUseCaseImpl(
                repository = get()
            )
        }

        factory<ListMovieAnalytics> {
            ListMovieAnalyticsImpl()
        }

        factory<ListMovieRepository> {
            ListMovieRepositoryImpl()
        }


        factory<ListMovieUimodel> {
            ListMovieUiModelImpl(
                resources = get()
            )
        }
    }
}