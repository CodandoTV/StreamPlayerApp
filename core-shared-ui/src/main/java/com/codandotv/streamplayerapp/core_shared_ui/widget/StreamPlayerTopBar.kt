package com.codandotv.streamplayerapp.core_shared_ui.widget

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.codandotv.streamplayerapp.core.shared.ui.R
import com.codandotv.streamplayerapp.core_shared.extension.empty
import com.codandotv.streamplayerapp.core_shared_ui.resources.Colors
import com.codandotv.streamplayerapp.core_shared_ui.theme.ThemePreview
import com.codandotv.streamplayerapp.core_shared_ui.theme.ThemePreviews

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StreamPlayerTopBar(
    scrollBehavior: TopAppBarScrollBehavior,
    onNavigateProfilePicker: () -> Unit = {},
    onSelectedProfilePicture: String
) {
    Box(modifier = Modifier.background(color = Colors.Dark10)) {
        StreamPlayerTopBar(
            onNavigateProfilePicker = { onNavigateProfilePicker() },
            profilePicture = onSelectedProfilePicture
        )
        StreamPlayerOptionsTopBar(modifier = Modifier.padding(top = 50.dp), scrollBehavior)
    }
}

@Composable
private fun StreamPlayerTopBar(
    onNavigateProfilePicker: () -> Unit = {},
    profilePicture: String
) {
    Row(
        modifier = Modifier
            .height(50.dp)
    ) {
        IconButton(
            modifier = Modifier
                .fillMaxHeight()
                .padding(vertical = 4.dp),
            onClick = { /* todo */ }
        ) {
            Icon(
                painter = painterResource(R.drawable.ic_netflix),
                contentDescription = stringResource(id = R.string.icon_netflix),
                tint = Color.Unspecified,
            )
        }
        Spacer(modifier = Modifier.weight(1f))

        IconButton(
            modifier = Modifier.fillMaxHeight(),
            onClick = { /* todo */ }
        ) {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = stringResource(id = R.string.icon_search),
                tint = Color.White,
            )
        }


        IconButton(
            modifier = Modifier.fillMaxHeight(),
            onClick = { onNavigateProfilePicker() }
        ) {
            AsyncImage(
                modifier = Modifier
                    .height(24.dp)
                    .clip(RoundedCornerShape(4.dp)),
                model = profilePicture,
                error = painterResource(id = R.drawable.perfil_fake),
                placeholder = painterResource(id = R.drawable.perfil_fake),
                contentDescription = stringResource(id = R.string.icon_profile)
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun StreamPlayerOptionsTopBar(modifier: Modifier, scrollBehavior: TopAppBarScrollBehavior) {
    TopAppBar(
        modifier = modifier,
        windowInsets = WindowInsets(top = 0.dp),
        title = {},
        scrollBehavior = scrollBehavior,
        actions = {
            Row(
                horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier.padding(horizontal = 40.dp)
            ) {
                Text(
                    text = stringResource(id = R.string.topbar_shows),
                    modifier = Modifier.weight(1f),
                    color = Color.White
                )
                Text(
                    text = stringResource(id = R.string.topbar_movies),
                    modifier = Modifier.weight(1f),
                    color = Color.White
                )
                Text(
                    text = stringResource(id = R.string.topbar_categories),
                    modifier = Modifier.weight(1f),
                    color = Color.White
                )
            }
        }, colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = Color.Transparent)
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@ThemePreviews
@Composable
fun StreamPlayerTopBarPreview() {
    ThemePreview {
        StreamPlayerTopBar(
            scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior(
                rememberTopAppBarState()
            ),
            onNavigateProfilePicker = {},
            onSelectedProfilePicture = String.empty()
        )
    }
}