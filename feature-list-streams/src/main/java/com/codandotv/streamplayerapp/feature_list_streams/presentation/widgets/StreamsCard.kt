package com.codandotv.streamplayerapp.feature_list_streams.presentation.widgets

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun StreamsCard(streamTitle: String) {
    Column {
        // Coil - Carregar Imagem do movie
        Box(
            modifier = Modifier
                .background(Color.Blue)
                .size(height = 120.dp, width = 80.dp)
        )

        Text(streamTitle)
    }
}

@Preview(device = "id:Nexus 6")
@Composable
fun StreamsCardPreview() {
    StreamsCard("Filme1")
}