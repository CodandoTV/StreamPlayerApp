package com.codandotv.streamplayerapp.feature_list_streams.detail.presentation.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.codandotv.streamplayerapp.feature.list.streams.R
import com.codandotv.streamplayerapp.feature_list_streams.detail.presentation.screens.DetailStreamsUIState.DetailStreamsLoadedUIState
import com.codandotv.streamplayerapp.feature_list_streams.detail.presentation.widget.*
import org.koin.androidx.compose.koinViewModel

@Composable
fun DetailStreamScreen(
    viewModel: DetailStreamViewModel = koinViewModel(),
    navController: NavController,
    disposable: () -> Unit = {}
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    val lifecycleOwner = LocalLifecycleOwner.current
    Lifecycle(lifecycleOwner, viewModel, disposable)

    when (uiState) {
        is DetailStreamsLoadedUIState -> {
            SetupDetailScreen(uiState as DetailStreamsLoadedUIState, navController)
        }
        else -> {
            Box(Modifier.fillMaxSize()) {
                CircularProgressIndicator(
                    modifier = Modifier.align(
                        Alignment.Center
                    )
                )
            }
        }

    }
}

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
private fun SetupDetailScreen(
    uiState: DetailStreamsLoadedUIState, navController: NavController
) {
    var showPlayer by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            DetailStreamToolbar(navController = navController)
        },
        content = { innerPadding ->
            Column(
                Modifier
                    .fillMaxWidth()
                    .verticalScroll(rememberScrollState())
                    .padding(innerPadding)
            ) {
                DetailStreamImagePreview(
                    uiState = uiState,
                    onPlayEvent = {
                        showPlayer = true
                    },
                    showPlayer = showPlayer
                )
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 16.dp, end = 16.dp, top = 8.dp)
                ) {
                    DetailStreamRowHeader()
                    Text(
                        text = uiState.detailStream.title,
                        style = MaterialTheme.typography.headlineMedium.copy(
                            fontWeight = FontWeight.Bold, fontSize = 28.sp
                        )
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = uiState.detailStream.releaseYear,
                        style = MaterialTheme.typography.headlineMedium.copy(
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            fontSize = 14.sp, fontWeight = FontWeight.Bold
                        )
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    DetailStreamButtonAction(
                        buttonsColors = ButtonDefaults.buttonColors(
                            containerColor = MaterialTheme.colorScheme.surface
                        ),
                        imageVector = Icons.Filled.PlayArrow,
                        imageVectorColor = MaterialTheme.colorScheme.onSurface,
                        text = stringResource(R.string.detail_watch_primary_button),
                        textColor = MaterialTheme.colorScheme.onSurface,
                        onClick = {},
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    DetailStreamButtonAction(
                        buttonsColors = ButtonDefaults.buttonColors(
                            containerColor = MaterialTheme.colorScheme.onSurfaceVariant
                        ),
                        imageVector = Icons.Filled.FileDownload,
                        imageVectorColor = MaterialTheme.colorScheme.onSurface,
                        text = stringResource(id = R.string.detail_default_text_secondary_button),
                        textColor = MaterialTheme.colorScheme.onSurface,
                        onClick = {},
                    )
                    Text(
                        text = uiState.detailStream.overview,
                        style = MaterialTheme.typography.headlineMedium.copy(
                            color = MaterialTheme.colorScheme.onSurface,
                            fontSize = 16.sp,
                            lineHeight = 1.25.em
                        ),
                        modifier = Modifier.padding(top = 8.dp, bottom = 16.dp)
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    DetailStreamActionOption()
                    Spacer(modifier = Modifier.height(16.dp))
                }
            }
        })
}

@Composable
private fun Lifecycle(
    lifecycleOwner: LifecycleOwner, viewModel: DetailStreamViewModel, disposable: () -> Unit
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