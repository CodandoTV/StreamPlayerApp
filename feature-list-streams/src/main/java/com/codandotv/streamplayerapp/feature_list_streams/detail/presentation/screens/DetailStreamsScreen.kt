package com.codandotv.streamplayerapp.feature_list_streams.detail.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import androidx.lifecycle.LifecycleOwner
import coil.compose.AsyncImage
import com.codandotv.streamplayerapp.core_shared_ui.resources.Colors.DarkDeepGray
import com.codandotv.streamplayerapp.core_shared_ui.theme.ThemePreview
import com.codandotv.streamplayerapp.core_shared_ui.theme.ThemePreviews
import com.codandotv.streamplayerapp.core_shared_ui.widget.IconWithText
import com.codandotv.streamplayerapp.feature_list_streams.R
import com.codandotv.streamplayerapp.feature_list_streams.detail.presentation.screens.DetailStreamsUIState.DetailStreamsLoadedUIState
import com.codandotv.streamplayerapp.feature_list_streams.detail.presentation.widget.DetailStreamActionOption
import com.codandotv.streamplayerapp.feature_list_streams.detail.presentation.widget.DetailStreamButtonAction
import com.codandotv.streamplayerapp.feature_list_streams.detail.presentation.widget.DetailStreamImagePreview
import com.codandotv.streamplayerapp.feature_list_streams.detail.presentation.widget.DetailStreamRowHeader
import org.koin.androidx.compose.koinViewModel
import kotlin.reflect.full.createType

@Composable
fun DetailStreamScreen(
    viewModel: DetailStreamViewModel = koinViewModel(), disposable: () -> Unit = {}
) {
    val uiStateRemember = remember {
        viewModel.uiState
    }

    val lifecycleOwner = LocalLifecycleOwner.current
    lifecycle(lifecycleOwner, viewModel, disposable)

    Box(modifier = Modifier.fillMaxSize()) {
        when (val uiState = uiStateRemember.value) {
            is DetailStreamsLoadedUIState -> {
                SetupDetailScreen(uiState)
            }
            else -> {
                CircularProgressIndicator(
                    modifier = Modifier.align(
                        Alignment.Center
                    )
                )
            }
        }
    }
}

@Composable
private fun SetupDetailScreen(uiState: DetailStreamsLoadedUIState) {
    Column {
        DetailStreamImagePreview(uiState)
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 16.dp, end = 16.dp, top = 8.dp)
        ) {
            DetailStreamRowHeader()
            Text(
                text = uiState.detailStream.title,
                style = MaterialTheme.typography.headlineMedium.copy(
                    color = Color.White, fontWeight = FontWeight.Bold, fontSize = 28.sp
                )
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = uiState.detailStream.releaseYear,
                style = MaterialTheme.typography.headlineMedium.copy(
                    color = Color.Gray, fontSize = 14.sp, fontWeight = FontWeight.Bold
                )
            )
            Spacer(modifier = Modifier.height(8.dp))
            DetailStreamButtonAction(
                buttonsColors = ButtonDefaults.buttonColors(
                    containerColor = Color.White
                ),
                imageVector = Icons.Filled.PlayArrow,
                imageVectorColor = Color.Black,
                text = stringResource(R.string.detail_watch_primary_button),
                textColor = Color.Black,
                onClick = {},
            )
            Spacer(modifier = Modifier.height(4.dp))
            DetailStreamButtonAction(
                buttonsColors = ButtonDefaults.buttonColors(
                    containerColor = DarkDeepGray
                ),
                imageVector = Icons.Filled.FileDownload,
                imageVectorColor = Color.White,
                text = stringResource(id = R.string.detail_default_text_secondary_button),
                textColor = Color.White,
                onClick = {},
            )
            Text(
                text = uiState.detailStream.overview,
                style = MaterialTheme.typography.headlineMedium.copy(
                    color = Color.White, fontSize = 16.sp, lineHeight = 1.25.em
                ),
                modifier = Modifier.padding(top = 8.dp, bottom = 16.dp)
            )
            Spacer(modifier = Modifier.height(8.dp))
            DetailStreamActionOption()
        }
    }
}

@Composable
private fun lifecycle(
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

@Composable
@ThemePreviews
fun DetailStreamScreenPreview() {
    ThemePreview {
        DetailStreamScreen()
    }
}