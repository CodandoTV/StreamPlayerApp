package com.codandotv.streamplayerapp.feature_list_streams.list.domain.model

import android.os.Parcelable
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import kotlinx.parcelize.Parcelize

@Parcelize
data class HighlightBanner(
    val name: String,
    val imageUrl: String,
    val contentType: Int,
    val contentTypeAsPlural: Int,
    val extraInfo: IconAndTextInfo,
    val leftButton: IconAndTextInfo,
    val centralButton: IconAndTextInfo,
    val rightButton: IconAndTextInfo
) : Parcelable

@Parcelize
data class IconAndTextInfo(
    @DrawableRes val icon: Int,
    @StringRes val text: Int
) : Parcelable