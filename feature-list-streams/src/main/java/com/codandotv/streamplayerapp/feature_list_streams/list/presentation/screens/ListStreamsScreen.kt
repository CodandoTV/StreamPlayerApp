package com.codandotv.streamplayerapp.feature_list_streams.list.presentation.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.codandotv.streamplayerapp.core_navigation.bottomnavigation.StreamPlayerBottomNavigation
import com.codandotv.streamplayerapp.core_shared_ui.theme.ThemePreviews
import com.codandotv.streamplayerapp.core_shared_ui.widget.StreamPlayerTopBar
import com.codandotv.streamplayerapp.feature_list_streams.list.presentation.widgets.HighlightBanner
import com.codandotv.streamplayerapp.feature_list_streams.list.presentation.widgets.StreamsCarousel
import org.koin.androidx.compose.koinViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListStreamsScreen(
    viewModel: ListStreamViewModel = koinViewModel(),
    navController: NavController,
    onNavigateDetailList: (String) -> Unit = {},
    onNavigateProfilePicker: () -> Unit = {},
    disposable: () -> Unit = {},
    profilePicture: String
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val lifecycleOwner = LocalLifecycleOwner.current
    val scrollBehavior =
        TopAppBarDefaults.exitUntilCollapsedScrollBehavior(rememberTopAppBarState())
    val baseScrollState = rememberScrollState()

    DisposableEffect(lifecycleOwner) {
        val lifecycle = lifecycleOwner.lifecycle

        lifecycle.addObserver(viewModel)

        onDispose {
            lifecycle.removeObserver(viewModel)
            disposable.invoke()
        }
    }

    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            StreamPlayerTopBar(
                scrollBehavior = scrollBehavior,
                onNavigateProfilePicker = onNavigateProfilePicker,
                onSelectedProfilePicture = profilePicture
            )
        },
        bottomBar = {
            StreamPlayerBottomNavigation(navController = navController)
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
        ) {
            if (uiState.isLoading) {
                CircularProgressIndicator(
                    modifier = Modifier.align(
                        Alignment.Center
                    )
                )
            } else {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .align(Alignment.TopCenter)
                        .verticalScroll(baseScrollState)
                ) {

                    HighlightBanner(data = uiState.highlightBanner)

                    uiState.streamsCarouselContent.forEach { streamCarouselContent ->
                        StreamsCarousel(
                            content = streamCarouselContent,
                            onNavigateDetailList = onNavigateDetailList,
                        )
                        Spacer(modifier = Modifier.height(12.dp))
                    }
                }
            }
        }
    }
}

@ThemePreviews
@Composable
fun ListStreamsScreenPreview() {
    ListStreamsScreen(navController = rememberNavController(), profilePicture = "")
}