package com.codandotv.streamplayerapp.core_shared_ui.widget

import android.content.*
import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.animation.*
import androidx.compose.animation.core.keyframes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import com.codandotv.streamplayerapp.core_shared_ui.R
import com.codandotv.streamplayerapp.core_shared_ui.resources.Colors
import com.codandotv.streamplayerapp.core_shared_ui.utils.Sharing.ANIMATION_DURATION
import com.codandotv.streamplayerapp.core_shared_ui.utils.Sharing.ANIMATION_EXECUTION_DELAY
import com.codandotv.streamplayerapp.core_shared_ui.utils.Sharing.COPY_CONTENT_TYPE_TEXT
import com.codandotv.streamplayerapp.core_shared_ui.utils.Sharing.OPTIONS_TITLE_MESSAGE
import com.codandotv.streamplayerapp.core_shared_ui.utils.Sharing.SHARING_DATA_TYPE_TEXT
import com.codandotv.streamplayerapp.core_shared_ui.utils.Sharing.SMS_CONTENT_BODY
import com.codandotv.streamplayerapp.core_shared_ui.utils.Sharing.SMS_CONTENT_TYPE
import com.codandotv.streamplayerapp.core_shared_ui.utils.Sharing.WHATSAPP_PACKAGE_SHARING
import com.codandotv.streamplayerapp.core_shared_ui.utils.isPackageInstalled
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


@Composable
fun SharingStreamCustomView(
    contentTitle: String,
    contentUrl: String,
    setShowDialog: (Boolean) -> Unit
) {

    val coroutineScope: CoroutineScope = rememberCoroutineScope()
    val animateTrigger = remember { mutableStateOf(false) }
    val context = LocalContext.current

    val linkCopiedMessage = stringResource(id = R.string.sharing_link_copied_message)
    val contentMessage =
        stringResource(id = R.string.sharing_whatsapp_message, contentTitle, contentUrl)
    val whatsAppNotInstalledMessage = stringResource(id = R.string.whatsapp_not_installed_message)
    val smsErrorMessage = stringResource(id = R.string.sms_app_error_message)

    LaunchedEffect(key1 = Unit) {
        launch {
            delay(ANIMATION_EXECUTION_DELAY)
            animateTrigger.value = true
        }
    }
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
                        Spacer(modifier = Modifier.height(20.dp))
                        Row(
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .clickable {
                                    shareWhatsAppMessage(
                                        context,
                                        contentMessage,
                                        whatsAppNotInstalledMessage
                                    )
                                }
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.ic_whatsapp),
                                contentDescription = null,
                                modifier = Modifier
                                    .width(24.dp)
                                    .height(24.dp)
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = stringResource(id = R.string.sharing_title_whatsapp),
                                style = TextStyle(
                                    fontSize = 18.sp,
                                    fontFamily = FontFamily.Default,
                                    color = Color.White
                                )
                            )
                        }
                        Spacer(modifier = Modifier.height(20.dp))
                        Row(
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .clickable {
                                    shareSmsMessage(
                                        context,
                                        contentMessage,
                                        smsErrorMessage
                                    )
                                }
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.ic_message),
                                contentDescription = null,
                                modifier = Modifier
                                    .width(24.dp)
                                    .height(24.dp)
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = stringResource(id = R.string.sharing_title_sms),
                                style = TextStyle(
                                    fontSize = 18.sp,
                                    fontFamily = FontFamily.Default,
                                    color = Color.White
                                )
                            )
                        }
                        Spacer(modifier = Modifier.height(20.dp))
                        Row(
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .clickable {
                                    shareInstagramStory()
                                }
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.ic_instagram),
                                contentDescription = null,
                                modifier = Modifier
                                    .width(24.dp)
                                    .height(24.dp)
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = stringResource(id = R.string.sharing_title_instagram),
                                style = TextStyle(
                                    fontSize = 18.sp,
                                    fontFamily = FontFamily.Default,
                                    color = Color.White
                                )
                            )
                        }
                        Spacer(modifier = Modifier.height(20.dp))
                        Row(
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .clickable {
                                    copyContentLink(context, linkCopiedMessage, contentUrl)
                                }
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.ic_copy_content),
                                contentDescription = null,
                                modifier = Modifier
                                    .width(28.dp)
                                    .height(28.dp)
                                    .background(
                                        color = Color.DarkGray,
                                        shape = RoundedCornerShape(6.dp)
                                    )
                                    .padding(4.dp)
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
                                callSharingOptions(
                                    context,
                                    contentMessage
                                )
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
                                .clickable {
                                    coroutineScope.launch {
                                        startDismissWithExitAnimation(animateTrigger) {
                                            setShowDialog(
                                                false
                                            )
                                        }
                                    }
                                }
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.ic_close),
                                contentDescription = null,
                                modifier = Modifier
                                    .width(32.dp)
                                    .height(32.dp)
                            )
                        }
                        Spacer(modifier = Modifier.height(12.dp))
                    }
                }
            }
        }
        BackHandler {
            coroutineScope.launch {
                startDismissWithExitAnimation(animateTrigger) { setShowDialog(false) }
            }
        }
    }
}

@Composable
internal fun AnimatedSlideInTransition(
    visible: Boolean,
    content: @Composable AnimatedVisibilityScope.() -> Unit
) {
    AnimatedVisibility(
        visible = visible,
        enter = slideInVertically(animationSpec = keyframes {
            this.durationMillis = ANIMATION_DURATION
        }, initialOffsetY = { fullHeight -> fullHeight }),
        exit = slideOutVertically(animationSpec = keyframes {
            this.durationMillis = ANIMATION_DURATION
        }, targetOffsetY = { fullHeight -> fullHeight }),
        content = content
    )
}

private fun shareInstagramStory() {
    TODO("Discussão aberta https://github.com/CodandoTV/StreamPlayerApp/discussions/65")
}

private fun shareWhatsAppMessage(
    context: Context,
    message: String,
    errorMessage: String
) {
    if (isPackageInstalled(WHATSAPP_PACKAGE_SHARING, context)) {
        val intent = Intent(Intent.ACTION_SEND)
        intent.type = SHARING_DATA_TYPE_TEXT
        intent.setPackage(WHATSAPP_PACKAGE_SHARING)
        intent.putExtra(Intent.EXTRA_TEXT, message)
        context.startActivity(intent)
    } else {
        Toast.makeText(
            context,
            errorMessage,
            Toast.LENGTH_SHORT
        ).show()
    }
}

private fun copyContentLink(context: Context, linkCopiedMessage: String, contentUrl: String) {
    val clipboardManager = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
    val clipData = ClipData.newPlainText(COPY_CONTENT_TYPE_TEXT, contentUrl)
    clipboardManager.setPrimaryClip(clipData)
    Toast.makeText(
        context,
        linkCopiedMessage,
        Toast.LENGTH_SHORT
    ).show()
}

private fun shareSmsMessage(
    context: Context,
    message: String,
    errorMessage: String
) {
    try {
        val intent = Intent(Intent.ACTION_VIEW)
        intent.putExtra(SMS_CONTENT_BODY, message)
        intent.data = Uri.parse(SMS_CONTENT_TYPE)
        context.startActivity(intent)
    } catch (anfe: ActivityNotFoundException) {
        Toast.makeText(
            context,
            errorMessage,
            Toast.LENGTH_SHORT
        ).show()
    }
}

fun callSharingOptions(context: Context, message: String) {
    val intent = Intent(Intent.ACTION_SEND)
        .putExtra(Intent.EXTRA_TEXT, message)
        .setType(SHARING_DATA_TYPE_TEXT)
    ContextCompat.startActivity(
        context,
        Intent.createChooser(intent, OPTIONS_TITLE_MESSAGE),
        null
    )
}

suspend fun startDismissWithExitAnimation(
    animateTrigger: MutableState<Boolean>,
    onDismissRequest: () -> Unit
) {
    animateTrigger.value = false
    delay(ANIMATION_EXECUTION_DELAY)
    onDismissRequest()
}
