package com.codandotv.streamplayerapp.feature_list_streams.list.di

import com.codandotv.streamplayerapp.feature_list_streams.list.data.ListStreamRepository
import com.codandotv.streamplayerapp.feature_list_streams.list.data.ListStreamRepositoryImpl
import com.codandotv.streamplayerapp.feature_list_streams.list.data.ListStreamService
import com.codandotv.streamplayerapp.feature_list_streams.list.domain.ListGenresUseCase
import com.codandotv.streamplayerapp.feature_list_streams.list.domain.ListGenresUseCaseImpl
import com.codandotv.streamplayerapp.feature_list_streams.list.domain.ListStreamAnalytics
import com.codandotv.streamplayerapp.feature_list_streams.list.domain.ListStreamAnalyticsImpl
import com.codandotv.streamplayerapp.feature_list_streams.list.domain.ListStreamUseCase
import com.codandotv.streamplayerapp.feature_list_streams.list.domain.ListStreamUseCaseImpl
import com.codandotv.streamplayerapp.feature_list_streams.list.presentation.screens.ListStreamViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit

object ListStreamModule {
    val module = module {
        viewModel {
            ListStreamViewModel(
                listStreams = get(),
                listGenres = get()
            )
        }

        factory<ListStreamUseCase> {
            ListStreamUseCaseImpl(
                repository = get()
            )
        }

        factory<ListGenresUseCase> {
            ListGenresUseCaseImpl(
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
    }
}