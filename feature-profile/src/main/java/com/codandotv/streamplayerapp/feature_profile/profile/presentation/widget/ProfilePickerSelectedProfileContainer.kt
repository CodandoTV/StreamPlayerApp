package com.codandotv.streamplayerapp.feature_profile.profile.presentation.widget

import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.codandotv.streamplayerapp.feature.profile.R
import com.codandotv.streamplayerapp.feature_profile.profile.presentation.screens.ProfilePickerStreamsUIState

@Composable
fun ProfilePickerSelectedProfileContainer(
    state: ProfilePickerStreamsUIState,
    offsetSelectedProfileImage: IntOffset,
    animatedSizeImage: Dp
) {
    with(state) {
        BoxWithConstraints(
            Modifier
                .fillMaxSize()
                .padding(top = 100.dp)
        ) {
            if (showCenterImage) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .offset { offsetSelectedProfileImage }
                ) {
                    AsyncImage(
                        model = ImageRequest.Builder(LocalContext.current)
                            .data(selectedItem?.imageUrl)
                            .crossfade(true)
                            .build(),
                        placeholder = painterResource(id = R.drawable.image_placeholder),
                        contentDescription = selectedItem?.let {
                            stringResource(
                                id = R.string.profile_current_profile_name,
                                it.name
                            )
                        },
                        modifier = Modifier
                            .clip(RoundedCornerShape(5))
                            .size(animatedSizeImage)
                            .alpha(centerImageAlpha)
                    )
                }
            }
        }
    }
}