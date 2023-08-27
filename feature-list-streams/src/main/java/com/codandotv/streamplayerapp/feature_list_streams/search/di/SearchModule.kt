package com.codandotv.streamplayerapp.feature_list_streams.search.di

import com.codandotv.streamplayerapp.feature_list_streams.search.data.datasource.SearchStreamDataSourceImpl
import com.codandotv.streamplayerapp.feature_list_streams.search.data.repository.SearchStreamRepositoryImpl
import com.codandotv.streamplayerapp.feature_list_streams.search.domain.datasource.SearchStreamDataSource
import com.codandotv.streamplayerapp.feature_list_streams.search.domain.repository.SearchStreamRepository
import com.codandotv.streamplayerapp.feature_list_streams.search.presentation.screens.SearchViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object SearchModule {
    val searchInstance = module {

        viewModel { (id: String) ->
            SearchViewModel()
        }
        //factory { SearchUseCase(repository = get()) }
        factory<SearchStreamDataSource> { SearchStreamDataSourceImpl(required = get(), service = get()) }
        factory<SearchStreamRepository> { SearchStreamRepositoryImpl(dataSource = get()) }
    }
}