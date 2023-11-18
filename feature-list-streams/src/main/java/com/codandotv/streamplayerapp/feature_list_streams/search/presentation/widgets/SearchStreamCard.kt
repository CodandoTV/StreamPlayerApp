package com.codandotv.streamplayerapp.feature_list_streams.search.presentation.widgets

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage import com.codandotv.streamplayerapp.core_shared_ui.theme.ThemePreviews

data class SearchStreamCardModel(
    val title : String,
    val url: String
)
@Composable
fun SearchStreamCard(model: SearchStreamCardModel) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .height(80.dp)
            .background(color = Color.Black)
            .padding(2.dp)
    ) {
        ImageStream(
            url= model.url,
            modifier = Modifier
                .weight(3f)
                .padding(2.dp)
        )
        Text(
            text = model.title,
            color = Color.White,
            modifier = Modifier
                .weight(5f)
                .padding(2.dp),
            fontWeight = FontWeight.SemiBold
        )
        PlayerIcon(
            modifier = Modifier
                .weight(2f)
                .padding(2.dp)
        )
    }
}

@Composable
fun ImageStream(modifier: Modifier, url:String) {
    Card(
        shape = RoundedCornerShape(4.dp),
        modifier = modifier
    ) {
        AsyncImage(
            model = url,
            contentScale = ContentScale.FillBounds,
            contentDescription = "",
            modifier = Modifier.fillMaxSize()
        )
    }
}

@Composable
fun PlayerIcon(modifier: Modifier) {
    Box(
        modifier = Modifier.border(
            width = 0.2.dp,
            shape = RoundedCornerShape(100),
            color = Color.White
        )
    ) {
        Icon(
            imageVector = Icons.Filled.PlayArrow,
            tint = Color.White,
            contentDescription = "",
            modifier = modifier
        )
    }
}

@ThemePreviews
@Composable
fun SearchStreamCardPreview() {
    SearchStreamCard(model = SearchStreamCardModel(
        title = "The Witcher",
        url = "https://image.tmdb.org/t/p/w200/iwsMu0ehRPbtaSxqiaUDQB9qMWT.jpg"
    ))
}

@ThemePreviews
@Composable
fun PlayerPreview() {
    PlayerIcon(modifier = Modifier)
}
