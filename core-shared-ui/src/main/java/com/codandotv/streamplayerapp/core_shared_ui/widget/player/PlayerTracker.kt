package com.codandotv.streamplayerapp.core_shared_ui.widget.player

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.SliderPositions
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import com.codandotv.streamplayerapp.core_shared_ui.theme.ThemePreviews

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PlayerTracker(expanded: Boolean, percentage: Float, modifier: Modifier = Modifier) {
    val sliderColors = SliderDefaults.colors(
        activeTrackColor = Color.Red,
        inactiveTrackColor = Color.Red.copy(
            alpha = 0.5f
        ),
        thumbColor = Color.Red,
    )

    val thumb: @Composable (SliderPositions) -> Unit = {
        if (expanded.not()) {
            SliderDefaults.Thumb(
                thumbSize = DpSize(0.dp, 0.dp),
                colors = sliderColors,
                interactionSource = MutableInteractionSource()
            )
        } else {
            SliderDefaults.Thumb(
                thumbSize = DpSize(40.dp, 40.dp),
                colors = sliderColors,
                interactionSource = MutableInteractionSource()
            )
        }
    }

    Slider(
        modifier = modifier,
        value = percentage,
        colors = sliderColors,
        onValueChange = {},
        thumb = thumb
    )
}

@ThemePreviews
@Composable
fun PlayerTrackerPreview() {
    Column(modifier = Modifier.fillMaxWidth()) {
        PlayerTracker(
            expanded = false,
            0.5f
        )

        PlayerTracker(expanded = true, percentage = 0.5f)
    }
}