package com.codandotv.streamplayerapp.feature_list_streams.di

import com.codandotv.streamplayerapp.feature_list_streams.data.ListStreamRepositoryImpl
import com.codandotv.streamplayerapp.feature_list_streams.domain.ListStreamAnalytics
import com.codandotv.streamplayerapp.feature_list_streams.domain.ListStreamAnalyticsImpl
import com.codandotv.streamplayerapp.feature_list_streams.domain.ListStreamUseCase
import com.codandotv.streamplayerapp.feature_list_streams.domain.ListStreamUseCaseImpl
import com.codandotv.streamplayerapp.feature_list_streams.presentation.ListMovieViewModel
import com.codandotv.streamplayerapp.feature_list_streams.presentation.ListStreamUiModelImpl
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object ListStreamModule {
    val module = module {
        viewModel {
            ListMovieViewModel(
                    uiModel = get(),
                    useCase = get(),
                    analytics = get()
            )
        }
        factory<ListStreamUseCase> {
            ListStreamUseCaseImpl(
                repository = get()
            )
        }

        factory<ListStreamAnalytics> {
            ListStreamAnalyticsImpl()
        }

        factory<com.codandotv.streamplayerapp.feature_list_streams.data.ListStreamRepository> {
            ListStreamRepositoryImpl()
        }


        factory<com.codandotv.streamplayerapp.feature_list_streams.presentation.ListStreamUimodel> {
            ListStreamUiModelImpl(
                    resources = get()
            )
        }
    }
}