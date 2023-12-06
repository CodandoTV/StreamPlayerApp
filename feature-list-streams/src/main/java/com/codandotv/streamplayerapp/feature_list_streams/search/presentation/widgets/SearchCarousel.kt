package com.codandotv.streamplayerapp.feature_list_streams.search.presentation.widgets

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.paging.PagingData
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemContentType
import androidx.paging.compose.itemKey
import com.codandotv.streamplayerapp.feature.list.streams.R
import com.codandotv.streamplayerapp.feature_list_streams.list.presentation.widgets.StreamsCard
import com.codandotv.streamplayerapp.feature_list_streams.list.presentation.widgets.StreamsCardContent
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

data class SearchCarousel(
    val genreTitle: String,
    val contentList: Flow<PagingData<StreamsCardContent>>
)

@Composable
fun SearchCarouselStream(
    content: SearchCarousel,
    onNavigateDetailList: (String) -> Unit = {},
    modifier: Modifier = Modifier
) {
    val lazyPagingItems = content.contentList.collectAsLazyPagingItems()

    Text(
        text = stringResource(id = R.string.search_list_describle),
        color = Color.Black,
        fontSize = 14.sp
    )

    Column {
        LazyColumn(
            modifier
                .fillMaxSize()
                .height(140.dp)
        ) {
            items(
                count = lazyPagingItems.itemCount,
                key = lazyPagingItems.itemKey(),
                contentType = lazyPagingItems.itemContentType()
            ) { index ->
                val item = lazyPagingItems[index]
                item?.let {
                    StreamsCard(
                        content = it,
                        onNavigateDetailList
                    )
                }
            }
        }
    }
}

@Composable
@Preview
fun StreamsCarouselPreview() {
    SearchCarouselStream(
        content = SearchCarousel(
            genreTitle = "Comédia",
            contentList = emptyFlow()
        )
    )
}

@Composable
fun StreamsError() {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Column {
            Text(
                text = stringResource(id = R.string.search_list_error),
                color = Color.White,
                fontWeight = FontWeight.SemiBold,
                fontSize = 20.sp,
                modifier = Modifier.padding(vertical = 8.dp)
            )
        }
    }
}