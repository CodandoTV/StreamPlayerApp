package com.codandotv.streamplayerapp.feature_list_streams.presentation.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.codandotv.streamplayerapp.feature_list_streams.presentation.widgets.StreamsCard
import com.codandotv.streamplayerapp.feature_list_streams.presentation.widgets.StreamsCarousel

@Composable
fun ListStreamsScreen() {
    Column {
        StreamsCarousel(
            title = "Comédia", streams = listOf(
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

        Spacer(modifier = Modifier.height(12.dp))

        StreamsCarousel(
            title = "Ação", streams = listOf(
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
}

@Composable
@Preview
fun ListStreamsScreenPreview() {
    ListStreamsScreen()
}