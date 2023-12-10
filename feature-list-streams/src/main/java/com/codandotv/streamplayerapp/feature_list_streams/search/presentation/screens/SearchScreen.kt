package com.codandotv.streamplayerapp.feature_list_streams.search.presentation.screens

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.codandotv.streamplayerapp.core_navigation.extensions.goBack
import com.codandotv.streamplayerapp.feature.list.streams.R
import com.codandotv.streamplayerapp.feature_list_streams.search.domain.mapper.toSearchStreamCardModel
import com.codandotv.streamplayerapp.feature_list_streams.search.presentation.widgets.SearchStreamCard
import com.codandotv.streamplayerapp.feature_list_streams.search.presentation.widgets.SearchableTopBar
import com.codandotv.streamplayerapp.feature_list_streams.search.presentation.widgets.StreamsEmpty
import com.codandotv.streamplayerapp.feature_list_streams.search.presentation.widgets.StreamsError
import org.koin.androidx.compose.koinViewModel

@Composable
fun SearchScreen(
    viewModel: SearchViewModel = koinViewModel(),
    onNavigateDetailList: (String) -> Unit = {},
    navController: NavController,
    disposable: () -> Unit = {}
) {

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    Lifecycle(
        lifecycleOwner = LocalLifecycleOwner.current,
        viewModel = viewModel,
        disposable = disposable
    )

    when (uiState) {
        is SearchUIState.Success -> {
            SearchUIState.Loading(true)
            SetupSearchScreen(
                navController = navController,
                uiState = uiState as SearchUIState.Success,
                viewModel = viewModel,
                onNavigateDetailList = onNavigateDetailList
            )
        }

        is SearchUIState.Error -> {
            SearchUIState.Loading(true)
            StreamsError { viewModel.fetchMovies() }
        }

        else -> {
            Box(Modifier.fillMaxSize()) {
                CircularProgressIndicator(
                    modifier = Modifier.align(
                        Alignment.Center
                    )
                )
                SearchUIState.Loading(true)
                StreamsEmpty()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun SetupSearchScreen(
    onNavigateDetailList: (String) -> Unit = {},
    navController: NavController,
    uiState: SearchUIState.Success,
    viewModel: SearchViewModel
) {
    Scaffold(
        topBar = {
            val currentText by viewModel.currentSearchText.collectAsState()
            SearchableTopBar(
                currentSearchText = currentText,
                onSearchTextChanged = { value ->
                    viewModel.setCurrentSearchText(
                        newText = value
                    )
                },
                onSearchDispatched = {
                    viewModel.fetchMovies()
                },
                onSearchIconPressed = {
                    viewModel.fetchMovies()
                },
                onBackPressed = {
                    navController.goBack()
                },
                onCleanTextPressed = {
                    viewModel.onCleanText()
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .verticalScroll(rememberScrollState()),
        ) {
            Text(
                text = stringResource(id = R.string.search_list_describle),
                color = Color.White,
                fontWeight = FontWeight.SemiBold,
                fontSize = 20.sp,
                modifier = Modifier.padding(vertical = 8.dp)
            )

            uiState.listCharacters.results.map {
                SearchStreamCard(
                    content = it.toSearchStreamCardModel(),
                    onSearchStreamPressed = { id ->
                        onNavigateDetailList(id)
                    }
                )
            }
        }
        BackHandler {
            navController.goBack()
        }
    }
}

@Composable
private fun Lifecycle(
    lifecycleOwner: LifecycleOwner, viewModel: SearchViewModel, disposable: () -> Unit
) {
    DisposableEffect(lifecycleOwner) {
        val lifecycle = lifecycleOwner.lifecycle

        lifecycle.addObserver(viewModel)

        onDispose {
            lifecycle.removeObserver(viewModel)
            disposable.invoke()
        }
    }
}
