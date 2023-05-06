package com.codandotv.streamplayerapp.core_shared_ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import com.codandotv.streamplayerapp.core_shared_ui.resources.Colors

@Composable
fun StreamPlayerTheme(
    isDarkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {

    MaterialTheme(
        colorScheme = getColorScheme(isDarkTheme),
        content = content,
    )
}

private fun getColorScheme(isDarkTheme: Boolean) =
    if (isDarkTheme) {
        Colors.LightColors
    } else {
        Colors.DarkColors
    }
