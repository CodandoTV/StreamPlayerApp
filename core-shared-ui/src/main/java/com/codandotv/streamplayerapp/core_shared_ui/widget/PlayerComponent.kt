package com.codandotv.streamplayerapp.core_shared_ui.widget

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.PlayerView
import com.codandotv.streamplayerapp.core_shared_ui.theme.ThemePreviews

@Composable
fun PlayerComponent(url: String, modifier: Modifier = Modifier) {
    val context = LocalContext.current

    val exoplayer = remember {
        val mediaItem = MediaItem.Builder()
            .setUri(url)
            .build()

        ExoPlayer.Builder(context).build().apply {
            setMediaItem(mediaItem)
            prepare()

            playWhenReady = true
        }
    }

    Column(
        modifier = modifier
            .fillMaxSize()
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
                        useController = true
                    }
                }
            )
        }
    }

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
