package com.codandotv.streamplayerapp.feature_list_streams.detail.presentation.widget

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.codandotv.streamplayerapp.core_shared_ui.R.*
import com.codandotv.streamplayerapp.feature_list_streams.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailStreamToolbar(navController : NavController) {
    TopAppBar(
        title = { Text(text = "") },
        modifier = Modifier.height(56.dp),
        navigationIcon = {
            IconButton(onClick = { navController.navigateUp() }) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = stringResource(id = R.string.detail_back)
                )
            }
        }, actions = {
            IconButton(onClick = { }) {
                Icon(
                    imageVector = Icons.Default.Search,
                    tint = Color.White,
                    contentDescription = stringResource(id = R.string.detail_search)
                )
            }
            IconButton(onClick = { }) {
                Image(
                    painter = painterResource(id = drawable.perfil_fake),
                    contentDescription = null
                )
            }
        })
}