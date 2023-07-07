package com.codandotv.streamplayerapp.core_shared_ui.widget

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.PlayerView
import com.codandotv.streamplayerapp.core_shared_ui.theme.ThemePreviews
import com.codandotv.streamplayerapp.core_shared_ui.widget.player.PlayerTracker
import com.codandotv.streamplayerapp.core_shared_ui.widget.player.PreviewBadge
import kotlinx.coroutines.delay

@Composable
fun PlayerComponent(url: String, modifier: Modifier = Modifier) {
    val context = LocalContext.current

    var isPlayerPlaying by remember { mutableStateOf(true) }

    var playerPosition by remember { mutableStateOf(0L) }

    val exoplayer = remember {
        val mediaItem = MediaItem.Builder()
            .setUri(url)
            .build()

        ExoPlayer.Builder(context).build().apply {
            setMediaItem(mediaItem)
            prepare()

            playWhenReady = true

            addListener(
                object : Player.Listener {
                    override fun onIsPlayingChanged(isPlaying: Boolean) {
                        isPlayerPlaying = isPlaying
                    }
                }
            )
        }
    }

    val playerProgress by remember {
        derivedStateOf { (playerPosition / exoplayer.contentDuration.toDouble()).toFloat() }
    }

    Column(
        modifier = modifier
            .height(225.dp)
            .fillMaxWidth()
    ) {
        Box {
            AndroidView(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .align(Alignment.TopCenter),
                factory = {
                    PlayerView(it).apply {
                        player = exoplayer
                        useController = false
                    }
                }
            )

            PreviewBadge(
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(
                        start = 8.dp,
                        bottom = 8.dp
                    )
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(25.dp)
                .background(MaterialTheme.colorScheme.background)
        ) {
            PlayerTracker(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(18.dp)
                    .padding(horizontal = 0.dp),
                percentage = playerProgress,
                expanded = false
            )
        }
    }


    LaunchedEffect(
        key1 = isPlayerPlaying,
        block = {
            while (isPlayerPlaying) {
                delay(500L)
                playerPosition += 500L
            }
        }
    )

    DisposableEffect(key1 = Unit, effect = {
        onDispose {
            exoplayer.release()
        }
    })
}

@Composable
@ThemePreviews
fun PlayerComponentPreview() {
    PlayerComponent(
        url = "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4",
        modifier = Modifier.fillMaxWidth()
    )
}
