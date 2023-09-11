package com.codandotv.streamplayerapp.feature_list_streams.profile.di

import com.codandotv.streamplayerapp.feature_list_streams.profile.data.ProfilePickerStreamRepository
import com.codandotv.streamplayerapp.feature_list_streams.profile.data.ProfilePickerStreamRepositoryImpl
import com.codandotv.streamplayerapp.feature_list_streams.profile.data.ProfilePickerStreamService
import com.codandotv.streamplayerapp.feature_list_streams.profile.domain.ProfilePickerStreamUseCase
import com.codandotv.streamplayerapp.feature_list_streams.profile.domain.ProfilePickerStreamUseCaseImpl
import com.codandotv.streamplayerapp.feature_list_streams.profile.presentation.screens.ProfilePickerStreamViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit

object ProfilePickerStreamModule {
    val module = module {
        viewModel {
            ProfilePickerStreamViewModel(
                useCase = get()
            )
        }

        factory<ProfilePickerStreamUseCase> {
            ProfilePickerStreamUseCaseImpl(
                profilePickerStreamRepository = get()
            )
        }

        factory<ProfilePickerStreamRepository> {
            ProfilePickerStreamRepositoryImpl(
                service = get()
            )
        }

        factory { get<Retrofit>().create(ProfilePickerStreamService::class.java) }
    }
}