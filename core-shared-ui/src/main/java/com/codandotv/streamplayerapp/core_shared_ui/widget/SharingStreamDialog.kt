package com.codandotv.streamplayerapp.core_shared_ui.widget

import android.R
import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Cancel
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Money
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.codandotv.streamplayerapp.core_shared_ui.resources.Colors
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SharingStreamDialog(
    value: String, setShowDialog: (Boolean) -> Unit, setValue: (String) -> Unit
) {
    val txtFieldError = remember { mutableStateOf("") }
    val txtField = remember { mutableStateOf(value) }

    val coroutineScope: CoroutineScope = rememberCoroutineScope()
    val animateTrigger = remember { mutableStateOf(false) }
    LaunchedEffect(key1 = Unit) {
        launch {
            delay(300)
            animateTrigger.value = true
        }
    }
    Dialog(onDismissRequest = {
        coroutineScope.launch {
            startDismissWithExitAnimation(animateTrigger) { setShowDialog(false) }
        }
    }) {
        Surface(
            color = Colors.AlphaBlack,
            modifier = Modifier.fillMaxSize()
        ) {
            Box(
                contentAlignment = Alignment.BottomCenter
            ) {
                AnimatedScaleInTransition(visible = animateTrigger.value) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(
                            text = "Compartilhar em...",
                            style = TextStyle(
                                fontSize = 16.sp,
                                fontFamily = FontFamily.Default,
                                color = Color.White
                            )
                        )

                        Spacer(modifier = Modifier.height(20.dp))

                        Row(
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                imageVector = Icons.Filled.Cancel,
                                contentDescription = "",
                                tint = colorResource(android.R.color.darker_gray),
                                modifier = Modifier
                                    .width(30.dp)
                                    .height(30.dp)
                            )
                            Text(
                                text = "WhatsApp",
                                style = TextStyle(
                                    fontSize = 18.sp,
                                    fontFamily = FontFamily.Default,
                                    color = Color.White
                                )
                            )
                        }

                        Spacer(modifier = Modifier.height(16.dp))

                        Row(
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                imageVector = Icons.Filled.Cancel,
                                contentDescription = "",
                                tint = colorResource(android.R.color.darker_gray),
                                modifier = Modifier
                                    .width(30.dp)
                                    .height(30.dp)
                            )
                            Text(
                                text = "Mensagens",
                                style = TextStyle(
                                    fontSize = 18.sp,
                                    fontFamily = FontFamily.Default,
                                    color = Color.White
                                )
                            )
                        }

                        Spacer(modifier = Modifier.height(16.dp))

                        Row(
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                imageVector = Icons.Filled.Cancel,
                                contentDescription = "",
                                tint = colorResource(android.R.color.darker_gray),
                                modifier = Modifier
                                    .width(30.dp)
                                    .height(30.dp)
                            )
                            Text(
                                text = "Stories do Instagram",
                                style = TextStyle(
                                    fontSize = 18.sp,
                                    fontFamily = FontFamily.Default,
                                    color = Color.White
                                )
                            )
                        }

                        Spacer(modifier = Modifier.height(16.dp))

                        Row(
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                imageVector = Icons.Filled.Cancel,
                                contentDescription = "",
                                tint = colorResource(android.R.color.darker_gray),
                                modifier = Modifier
                                    .width(30.dp)
                                    .height(30.dp)
                            )
                            Text(
                                text = "Copiar Link",
                                style = TextStyle(
                                    fontSize = 18.sp,
                                    fontFamily = FontFamily.Default,
                                    color = Color.White
                                )
                            )
                        }

                        Spacer(modifier = Modifier.height(16.dp))

                        Text(
                            text = "Mais opções",
                            style = TextStyle(
                                fontSize = 18.sp,
                                fontFamily = FontFamily.Default,
                                color = Color.White
                            )
                        )

                        Spacer(modifier = Modifier.height(48.dp))

                        Icon(
                            imageVector = Icons.Filled.Cancel,
                            contentDescription = "",
                            tint = Color.White,
                            modifier = Modifier
                                .fillMaxSize(0.2f)
                                .clickable { setShowDialog(false) }
                        )
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
internal fun AnimatedScaleInTransition(
    visible: Boolean,
    content: @Composable AnimatedVisibilityScope.() -> Unit
) {
    val density = LocalDensity.current
    AnimatedVisibility(
        visible = visible,
        enter = slideInVertically {
            // Slide in from 40 dp from the top.
            with(density) { -40.dp.roundToPx() }
        } + expandVertically(
            // Expand from the top.
            expandFrom = Alignment.Top
        ) + fadeIn(
            // Fade in with the initial alpha of 0.3f.
            initialAlpha = 0.3f
        ),
        exit = slideOutVertically() + shrinkVertically() + fadeOut(),
        content = content
    )
}

suspend fun startDismissWithExitAnimation(
    animateTrigger: MutableState<Boolean>,
    onDismissRequest: () -> Unit
) {
    animateTrigger.value = false
    delay(1000)
    onDismissRequest()
}
