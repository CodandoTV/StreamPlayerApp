package com.codandotv.streamplayerapp.feature_list_streams.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.codandotv.streamplayerapp.core_shared_ui.theme.ThemePreview
import com.codandotv.streamplayerapp.core_shared_ui.theme.ThemePreviews
import com.codandotv.streamplayerapp.feature_list_streams.presentation.widgets.StreamsCardContent
import com.codandotv.streamplayerapp.feature_list_streams.presentation.widgets.StreamsCarousel

val streamsCategoryX = listOf(
    StreamsCardContent(
        contentDescription = "test",
        url = "https://image.tmdb.org/t/p/w300/gU84Leiw6dYyxcx3S7kfneLnVJH.jpg"
    ),
    StreamsCardContent(
        contentDescription = "test",
        url = "https://image.tmdb.org/t/p/w300/evgwd37VHBJhXvSr88Mrx5riFil.jpg"
    ),
    StreamsCardContent(
        contentDescription = "test",
        url = "https://image.tmdb.org/t/p/w300/wAKExQIBBiApsucimUIv6aiJsWF.jpg"
    ),
    StreamsCardContent(
        contentDescription = "test",
        url = "https://image.tmdb.org/t/p/w300/mKEDE9r2ggqtGv5krCFLuELh7ED.jpg"
    ),
    StreamsCardContent(
        contentDescription = "test",
        url = "https://image.tmdb.org/t/p/w300/ojCfQ8syImYWJ38sta46BOz00PC.jpg"
    )
)

val streamsCategoryY = listOf(
    StreamsCardContent(
        contentDescription = "test",
        url = "https://image.tmdb.org/t/p/w300/lOrPcXLAfdtWFSeMPdMiBLwlvtv.jpg"
    ),
    StreamsCardContent(
        contentDescription = "test",
        url = "https://image.tmdb.org/t/p/w300/yjs9NikrKZUmIX3ou3J9QiUcQUP.jpg"
    ), StreamsCardContent(
        contentDescription = "test",
        url = "https://image.tmdb.org/t/p/w300/vfdO4lUxZaUrRhI60Gvf5WEqdhY.jpg"
    ),
    StreamsCardContent(
        contentDescription = "test",
        url = "https://image.tmdb.org/t/p/w300/qdzIpsDH1D0uiFTvFQL9WW4MEZ4.jpg"
    ),
    StreamsCardContent(
        contentDescription = "test",
        url = "https://image.tmdb.org/t/p/w300/i17CbxJ8GrGvPneiknjGyJrbqvp.jpg"
    )
)

@Composable
fun ListStreamsScreen() {
    Column(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.background)
            .fillMaxSize()
    ) {
        StreamsCarousel(
            title = "Category X",
            contentList = streamsCategoryX,
            modifier = Modifier.padding(vertical = 8.dp)
        )

        Spacer(modifier = Modifier.height(12.dp))

        StreamsCarousel(
            title = "Category Y",
            contentList = streamsCategoryY,
            modifier = Modifier.padding(vertical = 8.dp)
        )
    }
}

@Composable
@ThemePreviews
fun ListStreamsScreenPreview() {
    ThemePreview {
        ListStreamsScreen()
    }
}