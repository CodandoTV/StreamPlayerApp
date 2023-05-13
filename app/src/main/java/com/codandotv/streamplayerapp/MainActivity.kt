package com.codandotv.streamplayerapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.codandotv.streamplayerapp.core_navigation.bottomnavigation.StreamPlayerBottomNavigation
import com.codandotv.streamplayerapp.core_navigation.helper.currentRoute
import com.codandotv.streamplayerapp.core_navigation.routes.Routes
import com.codandotv.streamplayerapp.core_shared_ui.theme.StreamPlayerTheme
import com.codandotv.streamplayerapp.navigation.NavigationGraph

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            StreamPlayerApp()
        }
    }
}

@Composable
fun StreamPlayerApp() {
    StreamPlayerTheme {
        val navController = rememberNavController()
        NavigationGraph(navController = navController)
    }
}
