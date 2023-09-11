package com.codandotv.streamplayerapp.feature_list_streams.profile.presentation.screens

import android.annotation.SuppressLint
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateIntOffsetAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.codandotv.streamplayerapp.core_shared_ui.theme.ThemePreview
import com.codandotv.streamplayerapp.core_shared_ui.theme.ThemePreviews
import com.codandotv.streamplayerapp.feature.list.streams.R
import com.codandotv.streamplayerapp.feature_list_streams.profile.presentation.screens.ProfilePickerStreamsUIState.ProfilePickerStreamsLoadedUIState
import com.codandotv.streamplayerapp.feature_list_streams.profile.presentation.widget.ProfilePickerStreamToolbar
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
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

    when (uiState) {
        is ProfilePickerStreamsLoadedUIState -> {
            SetupProfilePickerScreen(
                uiState as ProfilePickerStreamsLoadedUIState,
                navController
            )
        }

        else -> {
            Box(Modifier.fillMaxSize()) {
                CircularProgressIndicator(
                    modifier = Modifier.align(
                        Alignment.Center
                    )
                )
            }
        }
    }

}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalAnimationApi::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
private fun SetupProfilePickerScreen(
    uiState: ProfilePickerStreamsLoadedUIState,
    navController: NavController
) {
    val profiles = uiState.profileStream
    var showCenterImage by remember { mutableStateOf(false) }
    var centerImageAlpha by remember { mutableStateOf(1f) }


    val animatedBlackBackground by animateColorAsState(
        targetValue = if (showCenterImage) {
            MaterialTheme.colorScheme.background
        } else {
            Color.Transparent
        },
        label = "fundo da tela escurecendo",
        animationSpec = tween(durationMillis = 1000),
        finishedListener = {
            centerImageAlpha = 0f
            navController.popBackStack()
        }
    )

    Scaffold(
        topBar = {
            ProfilePickerStreamToolbar(modifier = Modifier.background(animatedBlackBackground))
        },
        content = { _ ->
            var selectedItem by remember { mutableStateOf(profiles.first()) }
            var listOfOffsets: List<IntOffset> by remember { mutableStateOf(listOf()) }

            var canMove by remember { mutableStateOf(false) }
            var expandImage by remember { mutableStateOf(false) }

            val expandedImageSize = 150.dp
            val defaultImageSize = 110.dp

            var screenWidth: Dp
            var screenHeight: Dp

            var oneQuarterOfHeightScreen = 0
            var oneThirdOfWidthScreen = 0

            val haltSizeImage = with(LocalDensity.current) {
                defaultImageSize.roundToPx() / 2
            }

            val animatedSizeImage by animateDpAsState(
                targetValue = if (expandImage) {
                    expandedImageSize
                } else {
                    defaultImageSize
                },
                label = "imagem de perfil selecionada aumentando de tamanho",
                animationSpec = tween(durationMillis = 1000)
            )

            val scope = rememberCoroutineScope()

            BoxWithConstraints(
                Modifier
                    .fillMaxSize()
                    .padding(top = 100.dp)
            ) {
                // for each item in the list will be calculated the position of the item

                screenWidth = this.maxWidth
                screenHeight = this.maxHeight

                oneThirdOfWidthScreen = with(LocalDensity.current) {
                    screenWidth.roundToPx() / 3
                }

                oneQuarterOfHeightScreen = with(LocalDensity.current) {
                    screenHeight.roundToPx() / 4
                }

                listOfOffsets = listOf(
                    IntOffset(oneThirdOfWidthScreen - haltSizeImage, 0),
                    IntOffset(oneThirdOfWidthScreen * 2 - haltSizeImage, 0),
                    IntOffset(oneThirdOfWidthScreen - haltSizeImage, oneQuarterOfHeightScreen),
                    IntOffset(oneThirdOfWidthScreen * 2 - haltSizeImage, oneQuarterOfHeightScreen),
                    IntOffset(oneThirdOfWidthScreen - haltSizeImage, oneQuarterOfHeightScreen * 2),
                )

                var imageAlpha by remember { mutableStateOf(1f) }

                profiles.forEach { profile ->
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier
                            .offset { listOfOffsets[profiles.indexOf(profile)] }
                    ) {
                        AsyncImage(
                            model = profile.imageUrl,
                            placeholder = painterResource(id = R.drawable.image_placeholder),
                            contentDescription = "image de perfil de ${profile.name}",
                            modifier = Modifier
                                .clip(RoundedCornerShape(5))
                                .alpha(if (selectedItem == profile) imageAlpha else 1f)
                                .size(defaultImageSize)
                                .clickable {
                                    scope.launch {
                                        // move hide image to the position of the clicked item
                                        selectedItem = profile

                                        // displacement to clicked item
                                        delay(200)

                                        // show hide image
                                        showCenterImage = !showCenterImage

                                        // hiden selected image
                                        imageAlpha = 0f

                                        // move the new image to the center screen
                                        canMove = !canMove

                                        // as increase in size
                                        expandImage = !expandImage
                                    }
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
                }
            }

            BlackLayer(animatedBlackBackground)

            BoxWithConstraints(
                Modifier
                    .fillMaxSize()
                    .padding(top = 100.dp)
            ) {

                val halfExpandendSizeImage = with(LocalDensity.current) {
                    expandedImageSize.roundToPx() / 2
                }

                val centerScreen = IntOffset(
                    oneThirdOfWidthScreen + oneThirdOfWidthScreen / 2 - halfExpandendSizeImage,
                    oneQuarterOfHeightScreen
                )

                val offsetSelectedProfileImage by animateIntOffsetAsState(
                    targetValue = if (canMove) {
                        centerScreen
                    } else {
                        listOfOffsets[profiles.indexOf(selectedItem)]
                    },
                    label = "imagem de perfil selecionada se movendo para o centro",
                    animationSpec = tween(durationMillis = if (!showCenterImage) 100 else 800)
                )

                if (showCenterImage) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier
                            .offset { offsetSelectedProfileImage }
                    ) {
                        AsyncImage(
                            model = selectedItem.imageUrl,
                            placeholder = painterResource(id = R.drawable.image_placeholder),
                            contentDescription = "image de perfil de ${selectedItem.name}",
                            modifier = Modifier
                                .clip(RoundedCornerShape(5))
                                .size(animatedSizeImage)
                                .alpha(centerImageAlpha)
                        )
                    }
                }
            }
        }
    )
}

@Composable
private fun BlackLayer(animatedBlackBackground: Color) {
    Box(
        Modifier
            .fillMaxSize()
            .background(animatedBlackBackground)
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
            uiState = ProfilePickerStreamsLoadedUIState(emptyList()),
            navController = rememberNavController()
        )
    }
}
