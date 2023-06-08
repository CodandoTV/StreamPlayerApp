package com.codandotv.streamplayerapp.core_shared_ui.sharing.di

import com.codandotv.streamplayerapp.core_shared_ui.sharing.domain.SharingStreamAnalytics
import com.codandotv.streamplayerapp.core_shared_ui.sharing.domain.SharingStreamAnalyticsImpl
import org.koin.dsl.module

object SharingStreamModule {
    val module = module {
        factory<SharingStreamAnalytics> {
            SharingStreamAnalyticsImpl()
        }
    }
}