package com.codandotv.streamplayerapp.navigation

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.codandotv.streamplayerapp.core_navigation.routes.BottomNavRoutes
import com.codandotv.streamplayerapp.core_navigation.routes.SplashRoutes
import com.codandotv.streamplayerapp.feature_list_streams.presentation.navigation.listStreamsNavGraph
import com.codandotv.streamplayerapp.splah.presentation.navigation.splashNavGraph

@Composable
fun NavigationGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = SplashRoutes.Splash) {
        splashNavGraph(navController = navController)
        listStreamsNavGraph(navController = navController)
        temporaryFun(BottomNavRoutes.GAMES)
        temporaryFun(BottomNavRoutes.NEWS)
        temporaryFun(BottomNavRoutes.SCENES)
        temporaryFun(BottomNavRoutes.DOWNLOADS)
    }
}

fun NavGraphBuilder.temporaryFun(route: String) {
    composable(route = route) {
        example(route)
    }
}

@Composable
fun example(route: String) {
    Text(text = route, color = Color.White)
}
