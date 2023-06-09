package com.codandotv.streamplayerapp.feature_list_streams.list.presentation.widgets

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.paging.PagingData
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemContentType
import androidx.paging.compose.itemKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

@Composable
fun StreamsCarousel(
    title: String,
    contentList: Flow<PagingData<StreamsCardContent>>,
    modifier: Modifier = Modifier,
    onNavigateDetailList: (String) -> Unit = {},
) {
    val flow = remember { contentList }

    val lazyPagingItems = flow.collectAsLazyPagingItems()
    val lazyListState = rememberLazyListState()

    Column(modifier = modifier) {
        Text(
            title,
            style = MaterialTheme.typography.headlineMedium.copy(
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp
            )
        )

        Spacer(modifier = Modifier.size(8.dp))

        LazyRow(
            state = lazyListState,
            modifier = Modifier
                .fillMaxWidth()
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
    StreamsCarousel(
        title = "Ação",
        contentList = emptyFlow()
    )
}