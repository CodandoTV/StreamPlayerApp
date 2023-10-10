package com.codandotv.streamplayerapp.feature_list_streams.search.di

import com.codandotv.streamplayerapp.feature_list_streams.search.data.api.SearchStreamService
import com.codandotv.streamplayerapp.feature_list_streams.search.data.datasource.SearchStreamDataSourceImpl
import com.codandotv.streamplayerapp.feature_list_streams.search.data.repository.SearchStreamRepositoryImpl
import com.codandotv.streamplayerapp.feature_list_streams.search.domain.datasource.SearchStreamDataSource
import com.codandotv.streamplayerapp.feature_list_streams.search.domain.repository.SearchStreamRepository
import com.codandotv.streamplayerapp.feature_list_streams.search.domain.usecase.SearchUseCase
import com.codandotv.streamplayerapp.feature_list_streams.search.domain.usecase.SearchUseCaseImpl
import com.codandotv.streamplayerapp.feature_list_streams.search.presentation.screens.SearchViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit

object SearchModule {
    val module = module {

        viewModel {
            SearchViewModel(searchUseCase = get(), latestStream = get())
        }
        factory { get<Retrofit>().create( SearchStreamService::class.java) }
        factory<SearchUseCase> { SearchUseCaseImpl(repository = get()) }
        factory<SearchStreamDataSource> { SearchStreamDataSourceImpl(service = get()) }
        factory<SearchStreamRepository> { SearchStreamRepositoryImpl(dataSource = get()) }
    }
}
