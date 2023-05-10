package com.codandotv.streamplayerapp.feature_list_streams.presentation.screens

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.codandotv.streamplayerapp.core_shared_ui.theme.ThemePreview
import com.codandotv.streamplayerapp.core_shared_ui.theme.ThemePreviews
import com.codandotv.streamplayerapp.feature_list_streams.presentation.widgets.StreamsCarousel
import org.koin.androidx.compose.koinViewModel

@Composable
fun ListStreamsScreen(viewModel: ListStreamViewModel = koinViewModel()) {

    val uiState = remember {
        viewModel.uiState
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        if (uiState.value.isLoading) {
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
                    .verticalScroll(ScrollState(0))
            ) {
                uiState.value.carousels.forEach {
                    StreamsCarousel(
                        title = it.categoryName,
                        contentList = it.cards
                    )
                }

                Spacer(modifier = Modifier.height(12.dp))
            }
        }
    }
}

@Composable
@ThemePreviews
fun ListStreamsScreenPreview() {
    ThemePreview {
        ListStreamsScreen()
    }
}