package com.codandotv.streamplayerapp.core_shared_ui.widget.player

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.codandotv.streamplayerapp.core.shared.ui.R
import com.codandotv.streamplayerapp.core_shared_ui.theme.ThemePreviews

@Composable
fun PreviewBadge(modifier: Modifier = Modifier) {
    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(
            containerColor = Color.Black.copy(
                alpha = 0.5f
            )
        ),
        shape = RectangleShape
    ) {
        Text(
            text = stringResource(id = R.string.player_preview),
            style = MaterialTheme.typography.labelMedium.copy(
                color = Color.White,
                fontWeight = FontWeight.SemiBold
            ),
            modifier = Modifier.padding(
                horizontal = 8.dp,
                vertical = 4.dp
            )
        )
    }
}

@ThemePreviews
@Composable
fun PreviewBadgePreview() {
    PreviewBadge()
}