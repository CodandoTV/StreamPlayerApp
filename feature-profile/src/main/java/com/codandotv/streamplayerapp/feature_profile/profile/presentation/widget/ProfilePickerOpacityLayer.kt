package com.codandotv.streamplayerapp.feature_profile.profile.presentation.widget

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun ProfilePickerOpacityLayer(animatedBackground: Color) {
    Box(
        Modifier
            .fillMaxSize()
            .background(animatedBackground)
    )
}
