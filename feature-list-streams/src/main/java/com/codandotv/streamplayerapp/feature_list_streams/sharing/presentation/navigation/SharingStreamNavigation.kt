package com.codandotv.streamplayerapp.feature_list_streams.sharing.presentation.navigation

import androidx.activity.compose.BackHandler
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.codandotv.streamplayerapp.core_navigation.routes.BottomNavRoutes
import com.codandotv.streamplayerapp.core_navigation.routes.Routes.DETAIL
import com.codandotv.streamplayerapp.feature_list_streams.list.di.ListStreamModule
import com.codandotv.streamplayerapp.feature_list_streams.list.presentation.screens.ListStreamsScreen
import com.codandotv.streamplayerapp.feature_list_streams.sharing.di.SharingStreamModule
import com.codandotv.streamplayerapp.feature_list_streams.sharing.presentation.screens.SharingStreamScreen
import org.koin.core.context.loadKoinModules
import org.koin.core.context.unloadKoinModules

fun NavGraphBuilder.shareStreamNavGraph(navController: NavHostController) {
    composable("SHARING_STREAM") {
        loadKoinModules(SharingStreamModule.module)
        SharingStreamScreen(navController = navController,
            disposable = {
                unloadKoinModules(SharingStreamModule.module)
            })
    }
}