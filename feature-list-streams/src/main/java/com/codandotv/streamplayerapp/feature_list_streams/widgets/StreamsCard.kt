package com.codandotv.streamplayerapp.feature_list_streams.widgets

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun StreamsCard() {
    Text(
        text = "Olá Pessoal do CodandoTV!"
    )
}

@Preview(device = "id:Nexus 6")
@Composable
fun StreamsCardPreview() {
    StreamsCard()
}