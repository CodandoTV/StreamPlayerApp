package com.codandotv.streamplayerapp.feature_onboarding.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
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
import com.codandotv.streamplayerapp.feature_onboarding.R

@Composable
fun SplashScreen(){
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black),
            contentAlignment = Alignment.Center
        ) {
            val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.logo))
            val logoAnimationState =
                animateLottieCompositionAsState(composition = composition)
            LottieAnimation(
                composition = composition,
                progress = { logoAnimationState.progress }
            )
            if (logoAnimationState.isAtEnd && logoAnimationState.isPlaying) {
                println("faz alguma coisa")
            }
        }
    }
}

@Composable
@Preview
fun SplashScreenPreview(){
    SplashScreen()
}