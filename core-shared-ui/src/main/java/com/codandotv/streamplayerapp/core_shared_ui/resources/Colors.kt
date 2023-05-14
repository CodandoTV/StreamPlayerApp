package com.codandotv.streamplayerapp.core_shared_ui.resources

import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.ui.graphics.Color

object Colors {
    val DarkDeepGray = Color(0xFF262626)

    val LightColors = lightColorScheme(
        primary = Color(0xFFE50914),
        onBackground = Color(0xFFFFFFFF),
        secondary = Color(0xFFF5F5F1),
        onSurface = Color(0xFFF5F5F1),
        background = Color(0xFF000000),
        surface = Color(0xFF121212),
        onSurfaceVariant = Color(0XFF7b7b7b)
    )

    val DarkColors = darkColorScheme(
        primary = Color(0xFFE50914),
        onBackground = Color(0xFFFFFFFF),
        secondary = Color(0xFFF5F5F1),
        onSurface = Color(0xFFF5F5F1),
        background = Color(0xFFFFFFFF),
        surface = Color(0xFF121212),
        onSurfaceVariant = Color(0XFF7b7b7b)
    )

}