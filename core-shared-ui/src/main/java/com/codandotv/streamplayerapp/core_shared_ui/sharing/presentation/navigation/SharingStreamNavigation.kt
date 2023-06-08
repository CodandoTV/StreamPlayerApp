package com.codandotv.streamplayerapp.core_shared_ui.sharing.presentation.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.codandotv.streamplayerapp.core_navigation.routes.Routes.SHARING
import com.codandotv.streamplayerapp.core_shared_ui.sharing.di.SharingStreamModule
import com.codandotv.streamplayerapp.core_shared_ui.sharing.presentation.screens.SharingStreamScreen
import org.koin.core.context.loadKoinModules
import org.koin.core.context.unloadKoinModules

fun NavGraphBuilder.sharingStreamNavGraph(navController: NavHostController) {
    composable(SHARING) {
        loadKoinModules(SharingStreamModule.module)
        SharingStreamScreen(navController = navController,
            disposable = {
                unloadKoinModules(SharingStreamModule.module)
            })
    }
}