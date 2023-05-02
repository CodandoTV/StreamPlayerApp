package com.codandotv.streamplayerapp.feature_list_streams.presentation.widgets

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage

data class StreamsCardContent(
    val url: String,
    val contentDescription: String,
)

@Composable
fun StreamsCard(content: StreamsCardContent) {
    Card(
        shape = RoundedCornerShape(6.dp),
        modifier = Modifier.size(
            width = 100.dp,
            height = 140.dp
        ).padding(
            horizontal = 4.dp
        )
    ) {
        AsyncImage(
            model = content.url,
            contentScale = ContentScale.FillBounds,
            contentDescription = content.contentDescription
        )
    }
}

@Preview
@Composable
fun StreamsCardPreview() {
    StreamsCard(
        StreamsCardContent(
            url = "https://image.tmdb.org/t/p/w300/evgwd37VHBJhXvSr88Mrx5riFil.jpg",
            contentDescription = "Test 1"
        )
    )
}