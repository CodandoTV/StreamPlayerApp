package com.codandotv.streamplayerapp.core_shared_ui.utils

import android.content.Context
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.os.Build

object Sharing {

    const val SHARING_DATA_TYPE_TEXT = "text/plain"
    const val WHATSAPP_PACKAGE_SHARING = "com.whatsapp"
    const val SMS_CONTENT_TYPE = "vnd.android-dir/mms-sms"
    const val SMS_CONTENT_BODY = "sms_body"
    const val OPTIONS_TITLE_MESSAGE = "Compartilhar usando"
    const val OPTIONS_NAME_MESSAGE = "Link Download"
}

fun isPackageInstalled(packageName: String, context: Context): Boolean {
    val pm = context.packageManager
    return try {
        pm.getPackageInfoCompat(packageName)
        true
    } catch (e: PackageManager.NameNotFoundException) {
        false
    }
}

fun PackageManager.getPackageInfoCompat(packageName: String, flags: Int = 0): PackageInfo =
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        getPackageInfo(packageName, PackageManager.PackageInfoFlags.of(flags.toLong()))
    } else {
        @Suppress("DEPRECATION") getPackageInfo(packageName, flags)
    }