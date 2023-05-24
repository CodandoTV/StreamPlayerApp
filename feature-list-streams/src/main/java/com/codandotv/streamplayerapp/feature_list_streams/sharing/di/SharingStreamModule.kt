package com.codandotv.streamplayerapp.feature_list_streams.sharing.di

import com.codandotv.streamplayerapp.feature_list_streams.sharing.domain.SharingStreamAnalytics
import com.codandotv.streamplayerapp.feature_list_streams.sharing.domain.SharingStreamAnalyticsImpl
import org.koin.dsl.module

object SharingStreamModule {
    val module = module {
        factory<SharingStreamAnalytics> {
            SharingStreamAnalyticsImpl()
        }
    }
}