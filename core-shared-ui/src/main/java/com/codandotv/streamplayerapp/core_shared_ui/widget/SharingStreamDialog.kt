package com.codandotv.streamplayerapp.core_shared_ui.widget

import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.compose.animation.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Cancel
import androidx.compose.material.icons.filled.ContentCopy
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.core.content.ContextCompat
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
    val context = LocalContext.current
    val linkCopiedMessage = stringResource(id = R.string.sharing_link_copied_message)
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
                                    verticalAlignment = Alignment.CenterVertically,
                                    modifier = Modifier
                                        .clickable {
                                            shareWhatsAppMessage(context)
                                        }
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
                                    verticalAlignment = Alignment.CenterVertically,
                                    modifier = Modifier
                                        .clickable {
                                            shareSmsMessage(context)
                                        }
                                ) {
                                    IconButton(onClick = { }) {
                                        Image(
                                            painter = painterResource(id = R.drawable.ic_message),
                                            contentDescription = "",
                                            modifier = Modifier
                                                .width(24.dp)
                                                .height(24.dp)
                                        )
                                    }
                                    Text(
                                        text = stringResource(id = R.string.sharing_title_sms),
                                        style = TextStyle(
                                            fontSize = 18.sp,
                                            fontFamily = FontFamily.Default,
                                            color = Color.White
                                        )
                                    )
                                }
                                Row(
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    verticalAlignment = Alignment.CenterVertically,
                                    modifier = Modifier
                                        .clickable {
                                            shareInstagramStory(context)
                                        }
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
                                Spacer(modifier = Modifier.height(16.dp))
                                Row(
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    verticalAlignment = Alignment.CenterVertically,
                                    modifier = Modifier
                                        .clickable {
                                            Toast.makeText(
                                                context,
                                                linkCopiedMessage,
                                                Toast.LENGTH_SHORT
                                            ).show()
                                        }
                                ) {
                                    Icon(
                                        imageVector = Icons.Filled.ContentCopy,
                                        contentDescription = "",
                                        tint = colorResource(android.R.color.darker_gray),
                                        modifier = Modifier
                                            .width(24.dp)
                                            .height(24.dp)
                                    )
                                    Spacer(modifier = Modifier.width(8.dp))
                                    Text(
                                        text = stringResource(id = R.string.sharing_title_link),
                                        style = TextStyle(
                                            fontSize = 18.sp,
                                            fontFamily = FontFamily.Default,
                                            color = Color.White
                                        )
                                    )
                                }
                                Spacer(modifier = Modifier.height(20.dp))
                                Text(
                                    text = stringResource(id = R.string.sharing_title_more_options),
                                    style = TextStyle(
                                        fontSize = 18.sp,
                                        fontFamily = FontFamily.Default,
                                        color = Color.White
                                    ),
                                    modifier = Modifier.clickable {
                                        callSharingOptions(context)
                                    }
                                )
                                Spacer(modifier = Modifier.height(48.dp))
                                Box(
                                    contentAlignment = Alignment.Center,
                                    modifier = Modifier
                                        .width(64.dp)
                                        .height(64.dp)
                                        .clip(CircleShape)
                                        .background(
                                            colorResource(id = android.R.color.white)
                                        )
                                        .clickable { setShowDialog(false) }
                                ) {
                                    Icon(
                                        painter = painterResource(id = R.drawable.ic_close),
                                        contentDescription = "",
                                        modifier = Modifier
                                            .width(32.dp)
                                            .height(32.dp)
                                    )
                                }
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

private fun shareInstagramStory(context: Context) {
//    val backgroundAssetUri: Uri =
//        FileProvider.getUriForFile(context, "com.codandotv.streamplayerapp.provider", File())
//    val storiesIntent = Intent("com.instagram.share.ADD_TO_STORY")
//    storiesIntent.setDataAndType(backgroundAssetUri, "image/*")
//    storiesIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
//    storiesIntent.setPackage("com.instagram.android")
//    context.grantUriPermission(
//        "com.instagram.android", backgroundAssetUri, Intent.FLAG_GRANT_READ_URI_PERMISSION
//    )
//    context.startActivity(storiesIntent)
}

private fun shareWhatsAppMessage(context: Context) {
    val intent = Intent(Intent.ACTION_SEND)
    intent.type = "text/plain"
    intent.setPackage("com.whatsapp")
    intent.putExtra(Intent.EXTRA_TEXT, "mensagem de teste")
    context.startActivity(intent)
}

private fun shareSmsMessage(context: Context) {
    val sendIntent = Intent(Intent.ACTION_VIEW)
    sendIntent.putExtra("sms_body", "default content")
    sendIntent.type = "vnd.android-dir/mms-sms"
    context.startActivity(sendIntent)
}

fun callSharingOptions(context: Context) {
    val intent = Intent(Intent.ACTION_SEND)
        .putExtra("File Download Link", "https://wwww.google.com")
        .setType("text/plain")
    ContextCompat.startActivity(
        context,
        Intent.createChooser(intent, "Share Using"),
        null
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
