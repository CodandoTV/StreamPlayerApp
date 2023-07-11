package com.codandotv.streamplayerapp.feature_list_streams.detail.presentation.widget

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.codandotv.streamplayerapp.core_shared_ui.widget.PlayerComponent
import com.codandotv.streamplayerapp.feature.list.streams.R
import com.codandotv.streamplayerapp.feature_list_streams.detail.presentation.screens.DetailStreamsUIState.DetailStreamsLoadedUIState

@Composable
fun DetailStreamImagePreview(
    uiState: DetailStreamsLoadedUIState,
    modifier: Modifier = Modifier,
    showPlayer: Boolean = false,
    onPlayEvent: (() -> Unit)
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .aspectRatio(16f / 9f),
        contentAlignment = Alignment.Center
    ) {
        if (showPlayer) {
            PlayerComponent(
                videoId = uiState.videoId ?: ""
            )
        } else {
            AsyncImage(
                model = uiState.detailStream.url,
                contentScale = ContentScale.FillBounds,
                contentDescription = uiState.detailStream.tagline,
            )

            Box(
                modifier = Modifier
                    .background(Color.Black.copy(alpha = 0.5f), CircleShape)
                    .size(50.dp)
                    .align(Alignment.Center),
            )
            Icon(
                painter = painterResource(id = R.drawable.play_circle),
                tint = Color.White,
                contentDescription = null,
                modifier = Modifier
                    .size(64.dp)
                    .align(Alignment.Center)
                    .clickable {
                        onPlayEvent()
                    }
            )
        }
    }
}