package com.codandotv.streamplayerapp.splah.presentation.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.codandotv.streamplayerapp.core_navigation.routes.BottomNavRoutes
import com.codandotv.streamplayerapp.core_navigation.routes.SplashRoutes
import com.codandotv.streamplayerapp.splah.presentation.screens.SplashScreen

fun NavGraphBuilder.splashNavGraph(navController: NavHostController) {
    composable(SplashRoutes.Splash) {
        SplashScreen(
            onAnimationFinished = { navController.navigate(BottomNavRoutes.HOME) }
        )
    }
}