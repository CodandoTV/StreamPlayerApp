package com.codandotv.streamplayerapp.feature_profile.profile.presentation.screens

import android.annotation.SuppressLint
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.animateIntOffsetAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.codandotv.streamplayerapp.core_shared_ui.theme.ThemePreview
import com.codandotv.streamplayerapp.core_shared_ui.theme.ThemePreviews
import com.codandotv.streamplayerapp.feature.profile.R
import com.codandotv.streamplayerapp.feature_profile.profile.domain.ProfileStream
import com.codandotv.streamplayerapp.feature_profile.profile.presentation.widget.LoadScreen
import com.codandotv.streamplayerapp.feature_profile.profile.presentation.widget.ProfilePickerOpacityLayer
import com.codandotv.streamplayerapp.feature_profile.profile.presentation.widget.ProfilePickerProfilesGrid
import com.codandotv.streamplayerapp.feature_profile.profile.presentation.widget.ProfilePickerSelectedProfileContainer
import com.codandotv.streamplayerapp.feature_profile.profile.presentation.widget.ProfilePickerStreamToolbar
import com.codandotv.streamplayerapp.feature_profile.profile.presentation.widget.dpToPx
import org.koin.androidx.compose.koinViewModel

@Composable
fun ProfilePickerStreamScreen(
    viewModel: ProfilePickerStreamViewModel = koinViewModel(),
    onNavigateListStreams: (String) -> Unit = {},
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
            onSetCenterImageAlpha = { viewModel.setCenterImageAlpha(it) },
            onSetLastItemPositioned = { viewModel.setLastItemPositioned(it) },
            onSetHaltSizeImage = { viewModel.setHaltSizeImage(it) },
            onSetHalfExpandedSizeImage = { viewModel.setHalfExpandedSizeImage(it) },
            onClickSelectedProfile = { viewModel.moveSelectedProfileToCenterImage(it) },
            onSetScreenSize = { width, height, widthPx, heightPx ->
                viewModel.setScreenSize(width, height, widthPx, heightPx)
            },
            onNavigateListStreams = onNavigateListStreams,
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
private fun SetupProfilePickerScreen(
    uiState: ProfilePickerStreamsUIState,
    onSetCenterImageAlpha: (Float) -> Unit = {},
    onSetScreenSize: (Float, Float, Int, Int) -> Unit = { _, _, _, _ -> },
    onSetLastItemPositioned: (Boolean) -> Unit = {},
    onSetHaltSizeImage: (Int) -> Unit = { },
    onSetHalfExpandedSizeImage: (Int) -> Unit = { },
    onClickSelectedProfile: (ProfileStream) -> Unit = {},
    onNavigateListStreams: (String) -> Unit = {}
) {
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
            uiState.selectedItem?.let { it1 -> onNavigateListStreams(it1.imageUrl) }
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

                // Preparing animations
                val animatedSizeImage by animateDpAsState(
                    targetValue = if (expandImage) expandedImageSize.dp else defaultImageSize.dp,
                    label = stringResource(R.string.profile_animation_selected_image_size),
                    animationSpec = tween(durationMillis = 1000),
                )

                val animatedProfileAlpha: Float by animateFloatAsState(
                    if (lastItemPositioned) 1f else 0f,
                    label = stringResource(R.string.profile_animation_showing_all_profiles),
                )

                val animatedOffsetSelectedProfileImage by animateIntOffsetAsState(
                    targetValue = if (canMoveImageToCenter) {
                        IntOffset(centerScreen.first, centerScreen.second)
                    } else {
                        if (uiState.profilesStream.isNotEmpty() && offsetProfiles.isNotEmpty()) {
                            with(offsetProfiles[uiState.profilesStream.indexOf(selectedItem)]) {
                                IntOffset(first, second)
                            }
                        } else {
                            IntOffset(0, 0)
                        }
                    },
                    label = stringResource(R.string.profile_animation_selected_image_position),
                    animationSpec = tween(durationMillis = if (!showCenterImage) 100 else 800)
                )

                ProfilePickerProfilesGrid(
                    uiState = uiState,
                    animatedProfileAlpha = animatedProfileAlpha,
                    onSetScreenSize = onSetScreenSize,
                    onClickSelectedProfile = onClickSelectedProfile,
                    onSetLastItemPositioned = onSetLastItemPositioned
                )

                ProfilePickerOpacityLayer(animatedOpacityBackground)

                ProfilePickerSelectedProfileContainer(
                    state = uiState,
                    offsetSelectedProfileImage = animatedOffsetSelectedProfileImage,
                    animatedSizeImage = animatedSizeImage
                )
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
            uiState = ProfilePickerStreamsUIState(
                selectedItem = ProfileStream("1", "Name", ""),
            )
        )
    }
}