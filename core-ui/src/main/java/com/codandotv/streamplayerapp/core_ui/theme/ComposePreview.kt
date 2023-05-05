package com.codandotv.streamplayerapp.core_ui.theme

import android.content.res.Configuration
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@Preview(
    name = "dark mode",
    group = "themes",
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Preview(
    name = "light mode",
    group = "themes",
    uiMode = Configuration.UI_MODE_NIGHT_NO
)
annotation class ThemePreviews

@Composable
fun ThemePreview(
    content: @Composable () -> Unit
) {
    StreamPlayerTheme {
        Surface { content() }
    }

}