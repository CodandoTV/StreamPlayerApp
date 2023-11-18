package com.codandotv.streamplayerapp.feature_list_streams.search.presentation.widgets

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.codandotv.streamplayerapp.core_shared_ui.theme.ThemePreviews

data class SearchStreamCardModel(
    val id: String,
    val title: String,
    val url: String
)

@Composable
fun SearchStreamCard(
    content: SearchStreamCardModel,
    onSearchStreamPressed: (id: String) -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .height(90.dp)
            .background(color = Color.Black)
            .padding(vertical = 4.dp, horizontal = 8.dp)
            .clickable {
                onSearchStreamPressed(content.id)
            }
    ) {
        ImageStream(
            url = content.url,
            modifier = Modifier
                .weight(2.5f)
                .padding(vertical = 2.dp)
        )
        Text(
            text = content.title,
            color = Color.White,
            modifier = Modifier
                .weight(4.5f)
                .padding(8.dp),
            fontWeight = FontWeight.SemiBold,
            fontSize = 18.sp,
            overflow = TextOverflow.Ellipsis
        )
        PlayerIcon(
            modifier = Modifier
                .weight(2f)
                .padding(2.dp)
        )
    }
}

@Composable
fun ImageStream(modifier: Modifier, url: String) {
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
            width = 0.5.dp,
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
    SearchStreamCard(
        content = SearchStreamCardModel(
            id = "1",
            title = "The Witcher",
            url = "https://image.tmdb.org/t/p/w200/iwsMu0ehRPbtaSxqiaUDQB9qMWT.jpg"
        ),
        onSearchStreamPressed = {}
    )
}

@ThemePreviews
@Composable
fun PlayerPreview() {
    PlayerIcon(modifier = Modifier)
}
