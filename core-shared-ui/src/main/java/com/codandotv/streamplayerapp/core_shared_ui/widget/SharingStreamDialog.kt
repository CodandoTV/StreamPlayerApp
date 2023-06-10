package com.codandotv.streamplayerapp.core_shared_ui.widget

import androidx.compose.animation.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Cancel
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.codandotv.streamplayerapp.core_shared_ui.R
import com.codandotv.streamplayerapp.core_shared_ui.resources.Colors
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun SharingStreamDialog(
    value: String, setShowDialog: (Boolean) -> Unit
) {
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
            color = Color.Transparent,
            modifier = Modifier.fillMaxSize()
        ) {
            Box(
                contentAlignment = Alignment.BottomCenter
            ) {
                AnimatedSlideInTransition(visible = animateTrigger.value) {
                    Surface(
                        color = Colors.AlphaBlack,
                        modifier = Modifier.fillMaxSize()
                    ) {
                        Box(
                            contentAlignment = Alignment.BottomCenter
                        ) {
                            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                Text(
                                    text = stringResource(id = R.string.sharing_title_message),
                                    style = TextStyle(
                                        fontSize = 16.sp,
                                        fontFamily = FontFamily.Default,
                                        color = Color.White
                                    )
                                )
                                Spacer(modifier = Modifier.height(16.dp))
                                Row(
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    IconButton(onClick = { }) {
                                        Image(
                                            painter = painterResource(id = R.drawable.ic_whatsapp),
                                            contentDescription = "",
                                            modifier = Modifier
                                                .width(24.dp)
                                                .height(24.dp)
                                        )
                                    }
                                    Text(
                                        text = stringResource(id = R.string.sharing_title_whatsapp),
                                        style = TextStyle(
                                            fontSize = 18.sp,
                                            fontFamily = FontFamily.Default,
                                            color = Color.White
                                        )
                                    )
                                }
                                Row(
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    IconButton(onClick = { }) {
                                        Image(
                                            painter = painterResource(id = R.drawable.ic_whatsapp),
                                            contentDescription = "",
                                            modifier = Modifier
                                                .width(24.dp)
                                                .height(24.dp)
                                        )
                                    }
                                    Text(
                                        text = stringResource(id = R.string.sharing_title_facebook),
                                        style = TextStyle(
                                            fontSize = 18.sp,
                                            fontFamily = FontFamily.Default,
                                            color = Color.White
                                        )
                                    )
                                }
                                Row(
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    IconButton(onClick = { }) {
                                        Image(
                                            painter = painterResource(id = R.drawable.ic_instagram),
                                            contentDescription = "",
                                            modifier = Modifier
                                                .width(24.dp)
                                                .height(24.dp)
                                        )
                                    }
                                    Text(
                                        text = stringResource(id = R.string.sharing_title_instagram),
                                        style = TextStyle(
                                            fontSize = 18.sp,
                                            fontFamily = FontFamily.Default,
                                            color = Color.White
                                        )
                                    )
                                }
                                Row(
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    IconButton(onClick = { }) {
                                        Image(
                                            painter = painterResource(id = R.drawable.ic_whatsapp),
                                            contentDescription = "",
                                            modifier = Modifier
                                                .width(24.dp)
                                                .height(24.dp)
                                        )
                                    }
                                    Text(
                                        text = stringResource(id = R.string.sharing_title_link),
                                        style = TextStyle(
                                            fontSize = 18.sp,
                                            fontFamily = FontFamily.Default,
                                            color = Color.White
                                        )
                                    )
                                }
                                Spacer(modifier = Modifier.height(8.dp))
                                Text(
                                    text = stringResource(id = R.string.sharing_title_more_options),
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
                                        .width(80.dp)
                                        .height(80.dp)
                                        .clickable { setShowDialog(false) }
                                )
                                Spacer(modifier = Modifier.height(8.dp))
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
internal fun AnimatedSlideInTransition(
    visible: Boolean,
    content: @Composable AnimatedVisibilityScope.() -> Unit
) {
    val density = LocalDensity.current
    AnimatedVisibility(
        visible = visible,
        enter = slideInVertically {
            with(density) { -40.dp.roundToPx() }
        } + expandVertically(
            expandFrom = Alignment.Top
        ) + fadeIn(
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
