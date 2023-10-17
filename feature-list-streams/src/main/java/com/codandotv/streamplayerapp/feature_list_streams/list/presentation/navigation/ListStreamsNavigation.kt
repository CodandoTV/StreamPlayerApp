package com.codandotv.streamplayerapp.feature_list_streams.list.presentation.navigation

import androidx.activity.compose.BackHandler
import androidx.lifecycle.Lifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.codandotv.streamplayerapp.core_navigation.routes.BottomNavRoutes
import com.codandotv.streamplayerapp.core_navigation.routes.BottomNavRoutes.PARAM.PROFILE_ID
import com.codandotv.streamplayerapp.core_navigation.routes.Routes.DETAIL
import com.codandotv.streamplayerapp.core_navigation.routes.Routes.PROFILE_PICKER
import com.codandotv.streamplayerapp.feature_list_streams.list.di.ListStreamModule
import com.codandotv.streamplayerapp.feature_list_streams.list.presentation.screens.ListStreamsScreen
import org.koin.core.context.loadKoinModules
import org.koin.core.context.unloadKoinModules

internal const val DEFAULT_ID = ""

fun NavGraphBuilder.listStreamsNavGraph(navController: NavHostController) {
    composable(BottomNavRoutes.HOME_COMPLETE) { nav ->
        BackHandler(true) {}
        if (nav.getLifecycle().currentState == Lifecycle.State.STARTED) {
            loadKoinModules(ListStreamModule.module)
        }
        ListStreamsScreen(navController = navController,
            onNavigateDetailList = { id ->
                navController.navigate("${DETAIL}${id}")
            },
            onNavigateProfilePicker = {
                navController.navigate(PROFILE_PICKER)
            },
            disposable = {
                unloadKoinModules(ListStreamModule.module)
            },
            profilePicture = nav.arguments?.getString(PROFILE_ID) ?: DEFAULT_ID
        )
    }
}