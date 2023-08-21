package com.codandotv.streamplayerapp.feature_list_streams.detail.presentation.widget

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import com.codandotv.streamplayerapp.core_shared_ui.widget.IconWithText
import com.codandotv.streamplayerapp.feature.list.streams.R
import com.codandotv.streamplayerapp.feature_list_streams.detail.domain.DetailStream

@Composable
fun DetailStreamActionOption(
    detailStream: DetailStream,
    onToggleToMyList: (DetailStream) -> Unit,
    onShowSharingOptions: () -> Unit,
    modifier: Modifier = Modifier.fillMaxWidth()
) {
    var checked by rememberSaveable { mutableStateOf(detailStream.isFavorite) }
    var iconCheckList by remember { mutableStateOf(Icons.Filled.Add) }

    LaunchedEffect(checked) {
        iconCheckList =
            if (checked) Icons.Filled.Check else Icons.Filled.Add
    }

    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        IconWithText(
            onClick = {
                checked = !checked
                onToggleToMyList(detailStream)
            },
            imageVector = iconCheckList,
            imageColor = Color.White,
            text = stringResource(id = R.string.detail_my_list),
            textColor = Color.Gray,
        )
        IconWithText(
            onClick = { onShowSharingOptions.invoke() },
            imageVector = Icons.Filled.ThumbUp,
            imageColor = Color.White,
            text = stringResource(id = R.string.detail_classification),
            textColor = Color.Gray,
        )
        IconWithText(
            onClick = { },
            imageVector = Icons.Filled.Share,
            imageColor = Color.White,
            text = stringResource(id = R.string.detail_share),
            textColor = Color.Gray,
        )
        IconWithText(
            onClick = { },
            imageVector = Icons.Filled.Download,
            imageColor = Color.White,
            text = stringResource(id = R.string.detail_download),
            textColor = Color.Gray,
        )
    }
}