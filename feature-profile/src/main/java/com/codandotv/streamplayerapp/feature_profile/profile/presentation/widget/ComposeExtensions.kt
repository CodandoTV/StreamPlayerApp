package com.codandotv.streamplayerapp.feature_profile.profile.presentation.widget

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp

@Composable
fun Dp.dpToPx(): Int = with(LocalDensity.current) { this@dpToPx.roundToPx() }