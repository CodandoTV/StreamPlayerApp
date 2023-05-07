package com.codandotv.streamplayerapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.codandotv.streamplayerapp.core_navigation.bottomnavigation.StreamPlayerBottomNavigation
import com.codandotv.streamplayerapp.core_navigation.helper.currentRoute
import com.codandotv.streamplayerapp.core_navigation.routes.SplashRoutes
import com.codandotv.streamplayerapp.core_shared_ui.theme.StreamPlayerTheme
import com.codandotv.streamplayerapp.feature_list_streams.di.ListStreamModule
import com.codandotv.streamplayerapp.navigation.NavigationGraph
import org.koin.core.context.loadKoinModules
import org.koin.core.context.unloadKoinModules

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        loadKoinModules(ListStreamModule.module)
        setContent {
            StreamPlayerApp()
        }
    }

    override fun onDestroy() {
        unloadKoinModules(ListStreamModule.module)
        super.onDestroy()
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StreamPlayerApp() {
    StreamPlayerTheme {
        val navController = rememberNavController()

        Scaffold(
            bottomBar = {
                if (currentRoute(navController = navController) != SplashRoutes.Splash) {
                    StreamPlayerBottomNavigation(navController = navController)
                }
            }
        ) { innerPadding ->
            Box(Modifier.padding(innerPadding)) {
                NavigationGraph(navController = navController)
            }
        }
    }
}
