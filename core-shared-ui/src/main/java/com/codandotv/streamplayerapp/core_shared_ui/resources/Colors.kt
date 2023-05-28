package com.codandotv.streamplayerapp.core_shared_ui.resources

import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.ui.graphics.Color

object Colors {
    val DarkDeepGray = Color(0xFF262626)
    val Dark10 = Color(0x1A000000)

    val LightColors = lightColorScheme(
        primary = Color(0xFFE50914),
        secondary = Color(0xFFF5F5F1),
        background = Color(0xFF000000),
        onBackground = Color(0xFFFFFFFF),
        surface = Color(0xFF121212),
        onSurface = Color(0xFF070707),
        onSurfaceVariant = Color(0xFF9B9B9B),
    )

    val DarkColors = darkColorScheme(
        primary = Color(0xFFE50914),
        secondary = Color(0xFFF5F5F1),
        background = Color(0xFF000000),
        onBackground = Color(0xFFFFFFFF),
        surface = Color(0xFF121212),
        onSurface = Color(0xFFF5F5F1),
        onSurfaceVariant = Color(0XFF7b7b7b)
    )
}