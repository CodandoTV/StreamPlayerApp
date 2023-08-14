package com.codandotv.streamplayerapp.feature_list_streams.detail.presentation.widget

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Download
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import com.codandotv.streamplayerapp.core_shared_ui.widget.IconWithText
import com.codandotv.streamplayerapp.feature.list.streams.R

@Composable
fun DetailStreamActionOption(
    onShowSharingOptions: () -> Unit,
    modifier: Modifier = Modifier.fillMaxWidth()
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        IconWithText(
            imageVector = Icons.Filled.Check,
            imageColor = Color.White,
            text = stringResource(id = R.string.detail_my_list),
            textColor = Color.Gray,
        )
        IconWithText(
            imageVector = Icons.Filled.ThumbUp,
            imageColor = Color.White,
            text = stringResource(id = R.string.detail_classification),
            textColor = Color.Gray,
        )
        IconWithText(
            imageVector = Icons.Filled.Share,
            imageColor = Color.White,
            text = stringResource(id = R.string.detail_share),
            textColor = Color.Gray,
            modifier = Modifier.clickable { onShowSharingOptions.invoke() }
        )
        IconWithText(
            imageVector = Icons.Filled.Download,
            imageColor = Color.White,
            text = stringResource(id = R.string.detail_download),
            textColor = Color.Gray,
        )
    }
}
