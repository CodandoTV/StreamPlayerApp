package com.codandotv.streamplayerapp.core_shared_ui.widget

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.provider.Telephony
import android.widget.Toast
import androidx.compose.animation.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ContentCopy
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
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
import com.codandotv.streamplayerapp.core_shared_ui.utils.Sharing.OPTIONS_NAME_MESSAGE
import com.codandotv.streamplayerapp.core_shared_ui.utils.Sharing.OPTIONS_TITLE_MESSAGE
import com.codandotv.streamplayerapp.core_shared_ui.utils.Sharing.SHARING_DATA_TYPE_TEXT
import com.codandotv.streamplayerapp.core_shared_ui.utils.Sharing.SMS_CONTENT_BODY
import com.codandotv.streamplayerapp.core_shared_ui.utils.Sharing.SMS_CONTENT_TYPE
import com.codandotv.streamplayerapp.core_shared_ui.utils.Sharing.WHATSAPP_PACKAGE_SHARING
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


@Composable
fun SharingStreamDialog(
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
            delay(100)
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
                                            shareWhatsAppMessage(
                                                context,
                                                contentMessage,
                                                whatsAppNotInstalledMessage
                                            )
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
                                            shareSmsMessage(
                                                context,
                                                contentMessage,
                                                smsErrorMessage
                                            )
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
                                Spacer(modifier = Modifier.height(12.dp))
                                Row(
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    verticalAlignment = Alignment.CenterVertically,
                                    modifier = Modifier
                                        .clickable {
                                            copyContentLink(context, linkCopiedMessage, contentUrl)
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
                                    Spacer(modifier = Modifier.width(12.dp))
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
                                            setShowDialog(false)
                                        }
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

fun isPackageInstalled(packageName: String?, context: Context): Boolean {
    return try {
        context.packageManager.getApplicationInfo(packageName!!, 0).enabled
    } catch (e: PackageManager.NameNotFoundException) {
        false
    }
}

private fun copyContentLink(context: Context, linkCopiedMessage: String, contentUrl: String) {
    val clipboardManager = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
    val clipData = ClipData.newPlainText("text", contentUrl)
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
    if (Telephony.Sms.getDefaultSmsPackage(context) != null) {
        val sendIntent = Intent(Intent.ACTION_VIEW)
        sendIntent.putExtra(SMS_CONTENT_BODY, message)
        sendIntent.type = SMS_CONTENT_TYPE
        context.startActivity(sendIntent)
    } else {
        Toast.makeText(
            context,
            errorMessage,
            Toast.LENGTH_SHORT
        ).show()
    }
}

fun callSharingOptions(context: Context, message: String) {
    val intent = Intent(Intent.ACTION_SEND)
        .putExtra(OPTIONS_NAME_MESSAGE, message)
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
    delay(100)
    onDismissRequest()
}
