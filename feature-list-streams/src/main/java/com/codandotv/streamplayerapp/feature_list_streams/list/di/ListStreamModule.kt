package com.codandotv.streamplayerapp.feature_list_streams.list.di

import com.codandotv.streamplayerapp.core_shared.qualifier.QualifierDispatcherIO
import com.codandotv.streamplayerapp.feature_list_streams.list.data.ListStreamRepository
import com.codandotv.streamplayerapp.feature_list_streams.list.data.ListStreamRepositoryImpl
import com.codandotv.streamplayerapp.feature_list_streams.list.data.ListStreamService
import com.codandotv.streamplayerapp.feature_list_streams.list.domain.ListStreamAnalytics
import com.codandotv.streamplayerapp.feature_list_streams.list.domain.ListStreamAnalyticsImpl
import com.codandotv.streamplayerapp.feature_list_streams.list.domain.ListStreamUseCase
import com.codandotv.streamplayerapp.feature_list_streams.list.domain.ListStreamUseCaseImpl
import com.codandotv.streamplayerapp.feature_list_streams.list.presentation.ListStreamUiModelImpl
import com.codandotv.streamplayerapp.feature_list_streams.list.presentation.ListStreamUimodel
import com.codandotv.streamplayerapp.feature_list_streams.list.presentation.screens.ListStreamViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit

object ListStreamModule {
    val module = module {
        viewModel {
            ListStreamViewModel(
                uiModel = get(),
                useCase = get(),
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
                service = get(),
                dispatcher = get(QualifierDispatcherIO)
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