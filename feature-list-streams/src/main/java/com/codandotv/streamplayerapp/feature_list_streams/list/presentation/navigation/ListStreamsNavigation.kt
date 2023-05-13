package com.codandotv.streamplayerapp.feature_list_streams.list.presentation.navigation

import androidx.activity.compose.BackHandler
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.codandotv.streamplayerapp.core_navigation.routes.BottomNavRoutes
import com.codandotv.streamplayerapp.core_navigation.routes.Routes.DETAIL
import com.codandotv.streamplayerapp.feature_list_streams.list.di.ListStreamModule
import com.codandotv.streamplayerapp.feature_list_streams.list.presentation.screens.ListStreamsScreen
import org.koin.core.context.loadKoinModules
import org.koin.core.context.unloadKoinModules

fun NavGraphBuilder.listStreamsNavGraph(navController: NavHostController) {
    composable(BottomNavRoutes.HOME) {
        BackHandler(true) {}
        loadKoinModules(ListStreamModule.module)
        ListStreamsScreen(navController = navController,
            onNavigateDetailList = { id ->
                navController.navigate("${DETAIL}${id}")
            }, disposable = {
                unloadKoinModules(ListStreamModule.module)
            })
    }
}