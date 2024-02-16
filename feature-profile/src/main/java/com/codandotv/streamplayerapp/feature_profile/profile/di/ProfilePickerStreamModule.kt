package com.codandotv.streamplayerapp.feature_profile.profile.di

import com.codandotv.streamplayerapp.core_networking.di.QualifierProfileRetrofit
import com.codandotv.streamplayerapp.feature_profile.profile.data.ProfilePickerStreamService
import org.koin.core.annotation.ComponentScan
import org.koin.core.annotation.Factory
import org.koin.core.annotation.Module
import org.koin.core.context.GlobalContext
import retrofit2.Retrofit

@Module
@ComponentScan("com.codandotv.streamplayerapp.feature_profile")
class ProfilePickerStreamModule {

    @Factory
    fun service(): ProfilePickerStreamService {
        val koin = GlobalContext.get()
        val retrofit = koin.get<Retrofit>(QualifierProfileRetrofit)
        return retrofit.create(ProfilePickerStreamService::class.java)
    }

}
