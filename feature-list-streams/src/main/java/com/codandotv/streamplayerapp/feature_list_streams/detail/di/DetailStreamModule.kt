package com.codandotv.streamplayerapp.feature_list_streams.detail.di

import com.codandotv.streamplayerapp.feature_list_streams.detail.data.DetailStreamRepository
import com.codandotv.streamplayerapp.feature_list_streams.detail.data.DetailStreamRepositoryImpl
import com.codandotv.streamplayerapp.feature_list_streams.detail.data.DetailStreamService
import com.codandotv.streamplayerapp.feature_list_streams.detail.domain.DetailStreamUseCase
import com.codandotv.streamplayerapp.feature_list_streams.detail.domain.DetailStreamUseCaseImpl
import com.codandotv.streamplayerapp.feature_list_streams.detail.presentation.screens.DetailStreamViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.parameter.parametersOf
import org.koin.dsl.module
import retrofit2.Retrofit

object DetailStreamModule {
    val module = module {
        viewModel { (id: String) ->
            DetailStreamViewModel(
                useCase = get {
                    parametersOf(id)
                }
            )
        }
        factory<DetailStreamUseCase> { (id: String) ->
            DetailStreamUseCaseImpl(
                detailStreamRepository = get {
                    parametersOf(id)
                }
            )
        }
        factory<DetailStreamRepository> { (id: String) ->
            DetailStreamRepositoryImpl(
                favoriteDao = get(),
                service = get(),
                movieId = id,
            )
        }

        factory { get<Retrofit>().create(DetailStreamService::class.java) }
    }
}