package com.codandotv.streamplayerapp.core_shared_ui.widget

import android.view.View.OnClickListener
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun IconWithText(
    imageVector: ImageVector,
    imageColor: Color,
    text: String,
    textColor: Color,
    onClick: OnClickListener,
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        Icon(
            imageVector = imageVector,
            contentDescription = null,
            tint = imageColor
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = text,
            style = MaterialTheme.typography.headlineMedium.copy(
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
            ),
            color = textColor
        )
    }
}