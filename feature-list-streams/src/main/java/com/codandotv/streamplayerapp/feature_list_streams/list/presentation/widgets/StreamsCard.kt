package com.codandotv.streamplayerapp.feature_list_streams.list.presentation.widgets

import android.os.Parcelable
import androidx.compose.foundation.clickable
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
import com.codandotv.streamplayerapp.core_networking.Url.IMAGE_URL_SIZE_300
import kotlinx.parcelize.Parcelize

@Parcelize
data class StreamsCardContent(
    val id: String,
    val url: String,
    val contentDescription: String,
) : Parcelable

@Composable
fun StreamsCard(
    content: StreamsCardContent,
    onNavigateDetailList: (String) -> Unit = {},
) {
    Card(
        shape = RoundedCornerShape(6.dp),
        modifier = Modifier
            .size(
                width = 100.dp,
                height = 140.dp
            )
            .padding(
                horizontal = 4.dp
            )
            .clickable {
                onNavigateDetailList.invoke(content.id)
            }
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
            url = "${IMAGE_URL_SIZE_300}evgwd37VHBJhXvSr88Mrx5riFil.jpg",
            contentDescription = "Test 1",
            id = "",
        )
    )
}