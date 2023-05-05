package com.codandotv.streamplayerapp.core_ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import com.codandotv.streamplayerapp.core_ui.resources.Colors

@Composable
fun StreamPlayerTheme(
    isDarkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {

    MaterialTheme(
        colorScheme = getColors(isDarkTheme),
        content = content
    )
}

private fun getColors(isDarkTheme: Boolean) = when (isDarkTheme) {
    false -> Colors.LightColors
    else -> Colors.DarkColors
}