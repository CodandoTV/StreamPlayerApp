package com.codandotv.streamplayerapp.navigation

import android.annotation.SuppressLint
import android.app.Activity
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.codandotv.streamplayerapp.core_navigation.bottomnavigation.StreamPlayerBottomNavigation
import com.codandotv.streamplayerapp.core_navigation.helper.currentRoute
import com.codandotv.streamplayerapp.core_navigation.routes.Routes
import com.codandotv.streamplayerapp.feature_list_streams.detail.presentation.navigation.detailStreamNavGraph
import com.codandotv.streamplayerapp.feature_list_streams.list.presentation.navigation.listStreamsNavGraph
import com.codandotv.streamplayerapp.splah.presentation.navigation.splashNavGraph

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NavigationGraph(navController: NavHostController) {
    Scaffold(bottomBar = {
        if(Routes.hasBottomBar(currentRoute(navController = navController))) {
            StreamPlayerBottomNavigation(navController = navController)
        }
    }) { paddingValues ->
        Box(modifier = Modifier.padding(paddingValues)) {
            NavHost(navController = navController, startDestination = Routes.Splash.route) {
                splashNavGraph(navController = navController)
                listStreamsNavGraph(navController = navController)
                detailStreamNavGraph(navController = navController)
                temporaryFun(Routes.Games.route, navController)
                temporaryFun(Routes.News.route, navController)
                temporaryFun(Routes.Scenes.route, navController)
                temporaryFun(Routes.Downloads.route, navController)
            }
        }
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
    Scaffold { _ ->
        Text(text = route, color = Color.White)
    }
}
