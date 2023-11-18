package com.codandotv.streamplayerapp.feature_profile.profile.di

import com.codandotv.streamplayerapp.core_networking.di.QualifierProfileRetrofit
import com.codandotv.streamplayerapp.feature_profile.profile.data.ProfilePickerStreamRepository
import com.codandotv.streamplayerapp.feature_profile.profile.data.ProfilePickerStreamRepositoryImpl
import com.codandotv.streamplayerapp.feature_profile.profile.data.ProfilePickerStreamService
import com.codandotv.streamplayerapp.feature_profile.profile.domain.ProfilePickerStreamUseCase
import com.codandotv.streamplayerapp.feature_profile.profile.domain.ProfilePickerStreamUseCaseImpl
import com.codandotv.streamplayerapp.feature_profile.profile.presentation.screens.ProfilePickerStreamViewModel
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

        factory { get<Retrofit>(QualifierProfileRetrofit).create(ProfilePickerStreamService::class.java) }
    }
}