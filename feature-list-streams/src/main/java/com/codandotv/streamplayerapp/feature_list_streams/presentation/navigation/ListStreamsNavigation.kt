package com.codandotv.streamplayerapp.feature_list_streams.presentation.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.codandotv.streamplayerapp.core_navigation.routes.BottomNavRoutes
import com.codandotv.streamplayerapp.feature_list_streams.presentation.screens.ListStreamsScreen

fun NavGraphBuilder.listStreamsNavGraph(navController: NavHostController) {
    composable(BottomNavRoutes.HOME) {
        ListStreamsScreen(navController)
    }
}