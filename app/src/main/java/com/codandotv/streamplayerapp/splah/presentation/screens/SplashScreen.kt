package com.codandotv.streamplayerapp.splah.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.codandotv.streamplayerapp.core_shared_ui.R

@Composable
fun SplashScreen(
    onAnimationFinished: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black)
        ) {
            val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.logo))
            val logoAnimationState = animateLottieCompositionAsState(composition = composition)
            LottieAnimation(composition = composition, progress = { logoAnimationState.progress })
            if (logoAnimationState.isAtEnd && logoAnimationState.isPlaying) {
                onAnimationFinished()
            }
        }
    }
}

@Composable
@Preview
fun SplashScreenPreview() {
    SplashScreen(onAnimationFinished = {})
}