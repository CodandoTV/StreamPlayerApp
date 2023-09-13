package com.codandotv.streamplayerapp.feature_list_streams.profile.presentation.screens

import android.annotation.SuppressLint
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.animateIntOffsetAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.codandotv.streamplayerapp.core_shared_ui.theme.ThemePreview
import com.codandotv.streamplayerapp.core_shared_ui.theme.ThemePreviews
import com.codandotv.streamplayerapp.feature.list.streams.R
import com.codandotv.streamplayerapp.feature_list_streams.profile.domain.ProfileStream
import com.codandotv.streamplayerapp.feature_list_streams.profile.presentation.widget.LoadScreen
import com.codandotv.streamplayerapp.feature_list_streams.profile.presentation.widget.OpacityLayer
import com.codandotv.streamplayerapp.feature_list_streams.profile.presentation.widget.ProfilePickerStreamToolbar
import com.codandotv.streamplayerapp.feature_list_streams.profile.presentation.widget.dpToPx
import org.koin.androidx.compose.koinViewModel

@Composable
fun ProfilePickerStreamScreen(
    viewModel: ProfilePickerStreamViewModel = koinViewModel(),
    navController: NavController,
    disposable: () -> Unit = {}
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val lifecycleOwner = LocalLifecycleOwner.current
    Lifecycle(lifecycleOwner, viewModel, disposable)

    if (uiState.isLoading) {
        LoadScreen()
    } else {
        SetupProfilePickerScreen(
            uiState = uiState,
            navController = navController,
            onSetCenterImageAlpha = { viewModel.setCenterImageAlpha(it) },
            onSetLastItemPositioned = { viewModel.setLastItemPositioned(it) },
            onSetHaltSizeImage = { viewModel.setHaltSizeImage(it) },
            onSetHalfExpandedSizeImage = { viewModel.setHalfExpandedSizeImage(it) },
            onClickSelectedProfile = { viewModel.moveSelectedProfileToCenterImage(it) },
            onSetScreenSize = { width, height, widthPx, heightPx ->
                viewModel.setScreenSize(width, height, widthPx, heightPx)
            },
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
private fun SetupProfilePickerScreen(
    uiState: ProfilePickerStreamsUIState,
    navController: NavController,
    onSetCenterImageAlpha: (Float) -> Unit = {},
    onSetScreenSize: (Float, Float, Int, Int) -> Unit = { _, _, _, _ -> },
    onSetLastItemPositioned: (Boolean) -> Unit = {},
    onSetHaltSizeImage: (Int) -> Unit = { },
    onSetHalfExpandedSizeImage: (Int) -> Unit = { },
    onClickSelectedProfile: (ProfileStream) -> Unit = {},
) {
    val profiles = uiState.profilesStream

    val animatedOpacityBackground by animateColorAsState(
        targetValue = if (uiState.showCenterImage) {
            MaterialTheme.colorScheme.background
        } else {
            Color.Transparent
        },
        label = stringResource(R.string.profile_animation_background_opacity),
        animationSpec = tween(durationMillis = 1000),
        finishedListener = {
            onSetCenterImageAlpha(0f)
            navController.navigateUp()
        }
    )

    Scaffold(
        topBar = {
            ProfilePickerStreamToolbar(modifier = Modifier.background(animatedOpacityBackground))
        },
        content = { _ ->
            with(uiState) {
                // This code, like others, can't be moved to the ViewModel because it needs to calculate based on the size of the screen.
                // Yes, we can pass "LocalDensity.current" to the ViewModel, but this is not a good practice. The ViewModel is a pure Kotlin class
                // and this class shouldn't have any dependency on Android (even more, kmp is coming soon 😎)
                onSetHaltSizeImage(defaultImageSize.dp.dpToPx() / 2)
                onSetHalfExpandedSizeImage(expandedImageSize.dp.dpToPx() / 2)

                val animatedSizeImage by animateDpAsState(
                    targetValue = if (expandImage) expandedImageSize.dp else defaultImageSize.dp,
                    label = stringResource(R.string.profile_animation_selected_image_size),
                    animationSpec = tween(durationMillis = 1000),
                )

                val animatedProfileAlpha: Float by animateFloatAsState(
                    if (lastItemPositioned) 1f else 0f,
                    label = stringResource(R.string.profile_animation_showing_all_profiles),
                )

                val offsetSelectedProfileImage by animateIntOffsetAsState(
                    targetValue = if (canMoveImageToCenter) {
                        IntOffset(centerScreen.first, centerScreen.second)
                    } else {
                        if (profiles.isNotEmpty() && offsetProfiles.isNotEmpty()) {
                            with(offsetProfiles[profiles.indexOf(selectedItem)]) {
                                IntOffset(first, second)
                            }
                        } else {
                            IntOffset(0, 0)
                        }
                    },
                    label = stringResource(R.string.profile_animation_selected_image_position),
                    animationSpec = tween(durationMillis = if (!showCenterImage) 100 else 800)
                )

                BoxWithConstraints(
                    Modifier
                        .fillMaxSize()
                        .padding(top = 100.dp)
                        .graphicsLayer(alpha = animatedProfileAlpha)
                ) {
                    // for each item in the list will be calculated the position of the item
                    // lazyGrid is not used because it is not possible to calculate correctly the position of the item
                    onSetScreenSize(
                        this.maxWidth.value,
                        this.maxHeight.value,
                        this.maxWidth.dpToPx(),
                        this.maxHeight.dpToPx()
                    )

                    profiles.forEach { profile ->
                        val profileItemPosition = if (offsetProfiles.isNotEmpty()) {
                            with(offsetProfiles[profiles.indexOf(profile)]) {
                                IntOffset(first, second)
                            }
                        } else {
                            IntOffset(0, 0)
                        }

                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier
                                .offset {
                                    profileItemPosition
                                }
                        ) {
                            AsyncImage(
                                model = profile.imageUrl,
                                placeholder = painterResource(id = R.drawable.image_placeholder),
                                contentDescription = stringResource(
                                    id = R.string.profile_current_profile_name,
                                    profile.name
                                ),
                                modifier = Modifier
                                    .clip(RoundedCornerShape(5))
                                    .alpha(if (selectedItem == profile) selectedImageAlpha else 1f)
                                    .size(defaultImageSize.dp)
                                    .clickable {
                                        onClickSelectedProfile(profile)
                                    }
                            )

                            Text(
                                text = profile.name,
                                style = MaterialTheme.typography.bodyMedium.copy(
                                    color = MaterialTheme.colorScheme.onSurface
                                ),
                                modifier = Modifier.padding(top = 8.dp)
                            )

                            Spacer(modifier = Modifier.height(28.dp))
                        }
                        if (profiles.indexOf(profile) == profiles.size - 1) {
                            onSetLastItemPositioned(true)
                        }
                    }
                }

                OpacityLayer(animatedOpacityBackground)

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
                                    .data(selectedItem.imageUrl)
                                    .crossfade(true)
                                    .build(),
                                placeholder = painterResource(id = R.drawable.image_placeholder),
                                contentDescription = stringResource(
                                    id = R.string.profile_current_profile_name,
                                    selectedItem.name
                                ),
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
    )
}


@Composable
private fun Lifecycle(
    lifecycleOwner: LifecycleOwner, viewModel: ProfilePickerStreamViewModel, disposable: () -> Unit
) {
    DisposableEffect(lifecycleOwner) {
        val lifecycle = lifecycleOwner.lifecycle

        lifecycle.addObserver(viewModel)

        onDispose {
            lifecycle.removeObserver(viewModel)
            disposable.invoke()
        }
    }
}

@ThemePreviews
@Composable
fun SetupProfilePickerScreenPreview() {
    ThemePreview {
        SetupProfilePickerScreen(
            uiState = ProfilePickerStreamsUIState(),
            navController = rememberNavController()
        )
    }
}