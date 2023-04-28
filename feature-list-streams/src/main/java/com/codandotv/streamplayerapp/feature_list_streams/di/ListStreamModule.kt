package com.codandotv.streamplayerapp.feature_list_streams.di

import com.codandotv.streamplayerapp.feature_list_streams.data.ListStreamRepository
import com.codandotv.streamplayerapp.feature_list_streams.data.ListStreamRepositoryImpl
import com.codandotv.streamplayerapp.feature_list_streams.data.ListStreamService
import com.codandotv.streamplayerapp.feature_list_streams.domain.ListStreamAnalytics
import com.codandotv.streamplayerapp.feature_list_streams.domain.ListStreamAnalyticsImpl
import com.codandotv.streamplayerapp.feature_list_streams.domain.ListStreamUseCase
import com.codandotv.streamplayerapp.feature_list_streams.domain.ListStreamUseCaseImpl
import com.codandotv.streamplayerapp.feature_list_streams.presentation.ListMovieViewModel
import com.codandotv.streamplayerapp.feature_list_streams.presentation.ListStreamUiModelImpl
import com.codandotv.streamplayerapp.feature_list_streams.presentation.ListStreamUimodel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit

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

        factory<ListStreamRepository> {
            ListStreamRepositoryImpl(
                service = get()
            )
        }

        factory { get<Retrofit>().create(ListStreamService::class.java) }

        factory<ListStreamUimodel> {
            ListStreamUiModelImpl(
                resources = get()
            )
        }
    }
}