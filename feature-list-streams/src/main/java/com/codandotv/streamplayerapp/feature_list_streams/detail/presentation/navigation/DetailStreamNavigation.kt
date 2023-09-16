package com.codandotv.streamplayerapp.feature_list_streams.detail.presentation.navigation

import androidx.lifecycle.Lifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.codandotv.streamplayerapp.core_navigation.routes.Routes
import com.codandotv.streamplayerapp.core_navigation.routes.Routes.DETAIL_COMPLETE
import com.codandotv.streamplayerapp.core_navigation.routes.Routes.PARAM.ID
import com.codandotv.streamplayerapp.feature_list_streams.detail.di.DetailStreamModule
import com.codandotv.streamplayerapp.feature_list_streams.detail.presentation.screens.DetailStreamScreen
import org.koin.androidx.compose.koinViewModel
import org.koin.core.context.loadKoinModules
import org.koin.core.context.unloadKoinModules
import org.koin.core.parameter.parametersOf

internal const val DEFAULT_ID = "0"

fun NavGraphBuilder.detailStreamNavGraph(navController: NavHostController) {
    composable(DETAIL_COMPLETE) { nav ->
        if (nav.getLifecycle().currentState == Lifecycle.State.STARTED) {
            loadKoinModules(DetailStreamModule.module)
        }
        DetailStreamScreen(
            viewModel = koinViewModel {
                parametersOf(nav.arguments?.getString(ID) ?: DEFAULT_ID)
            },
            navController = navController,
            onNavigateSearchScreen = {
                navController.navigate(Routes.SEARCH)
            },
            disposable = {
                unloadKoinModules(DetailStreamModule.module)
            }
        )
    }
}
