package com.codandotv.streamplayerapp.feature_list_streams.detail.presentation.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalLifecycleOwner
import com.codandotv.streamplayerapp.core_shared_ui.theme.ThemePreview
import com.codandotv.streamplayerapp.core_shared_ui.theme.ThemePreviews
import org.koin.androidx.compose.koinViewModel

@Composable
fun DetailStreamScreen(
    viewModel: DetailStreamViewModel = koinViewModel(), disposable: () -> Unit = {}
) {
    val uiState = remember {
        viewModel.uiState
    }
    val lifecycleOwner = LocalLifecycleOwner.current

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
fun ListStreamsScreenPreview() {
    ThemePreview {
        DetailStreamScreen()
    }
}