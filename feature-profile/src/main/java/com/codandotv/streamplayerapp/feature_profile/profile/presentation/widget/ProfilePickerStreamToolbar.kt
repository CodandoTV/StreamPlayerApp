package com.codandotv.streamplayerapp.feature_profile.profile.presentation.widget

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.codandotv.streamplayerapp.core_shared_ui.theme.ThemePreviews
import com.codandotv.streamplayerapp.feature.profile.R

@Composable
fun ProfilePickerStreamToolbar(modifier: Modifier = Modifier) {

    Box(
        modifier
            .fillMaxWidth()
            .height(56.dp),
        contentAlignment = Alignment.CenterEnd
    ) {
        Row(
            Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.netflix_horizontal_logo),
                contentDescription = null,
                modifier = Modifier
                    .height(28.dp)
            )
        }

        IconButton(onClick = { }) {
            Icon(
                Icons.Default.Edit,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onBackground
            )
        }
    }

    Box(
        modifier
            .fillMaxWidth()
            .height(56.dp)
    )
}


@ThemePreviews
@Composable
fun ProfilePickerStreamToolbarPreview() {
    MaterialTheme {
        ProfilePickerStreamToolbar()
    }
}