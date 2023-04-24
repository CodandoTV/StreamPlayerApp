package com.codandotv.streamplayerapp.feature_list_streams.presentation.widgets

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun StreamsCarousel(title: String, streams: List<String>, modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
        Text(title)

        LazyRow(modifier = Modifier.fillMaxWidth()) {
            items(streams.size) {
                StreamsCard(streams[it])

                Spacer(modifier = Modifier.size(12.dp))
            }
        }
    }
}

@Composable
@Preview
fun StreamsCarouselPreview() {
    StreamsCarousel(
        title = "Ação",
        streams = listOf(
            "Chuck Norris",
            "Super Mario",
            "Mortal Combat",
            "Chuck Norris",
            "Super Mario",
            "Mortal Combat",
            "Chuck Norris",
            "Super Mario",
            "Mortal Combat",
            "Chuck Norris",
            "Super Mario",
            "Mortal Combat"
        )
    )
}