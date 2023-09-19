package com.codandotv.streamplayerapp.navigation

import android.annotation.SuppressLint
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.codandotv.streamplayerapp.core_navigation.bottomnavigation.StreamPlayerBottomNavigation
import com.codandotv.streamplayerapp.core_navigation.routes.BottomNavRoutes
import com.codandotv.streamplayerapp.core_navigation.routes.Routes
import com.codandotv.streamplayerapp.feature_list_streams.detail.presentation.navigation.detailStreamNavGraph
import com.codandotv.streamplayerapp.feature_list_streams.list.presentation.navigation.listStreamsNavGraph
import com.codandotv.streamplayerapp.feature_profile.profile.presentation.navigation.profilePickerStreamNavGraph
import com.codandotv.streamplayerapp.splah.presentation.navigation.splashNavGraph

@Composable
fun NavigationGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Routes.Splash) {
        splashNavGraph(navController = navController)
        listStreamsNavGraph(navController = navController)
        detailStreamNavGraph(navController = navController)
        temporaryFun(BottomNavRoutes.GAMES, navController)
        temporaryFun(BottomNavRoutes.NEWS, navController)
        temporaryFun(BottomNavRoutes.SCENES, navController)
        temporaryFun(BottomNavRoutes.DOWNLOADS, navController)
        profilePickerStreamNavGraph(navController = navController)
    }
}

fun NavGraphBuilder.temporaryFun(route: String, navController: NavController) {
    composable(route = route) {
        example(navController = navController, route)
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun example(navController: NavController, route: String) {
    Scaffold(
        bottomBar = {
            StreamPlayerBottomNavigation(navController = navController)
        }
    ) { _ ->
        Text(text = route, color = Color.White)
    }
}
