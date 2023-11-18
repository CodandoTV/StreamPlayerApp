package com.codandotv.streamplayerapp.feature_profile.profile.presentation.screens

import com.codandotv.streamplayerapp.feature_profile.profile.domain.ProfileStream


data class ProfilePickerStreamsUIState(
    val profilesStream: List<ProfileStream> = emptyList(),
    val selectedItem: ProfileStream? = null,
    val isLoading: Boolean = true,
    val showCenterImage: Boolean = false,
    val centerImageAlpha: Float = 1f,
    val canMoveImageToCenter: Boolean = false,
    val expandImage: Boolean = false,

    val expandedImageSize: Float = 150f,
    val defaultImageSize: Float = 110f,
    val haltSizeImage: Int = 0,
    val halfExpandedSizeImage: Int = 0,
    val screenWidth: Float = 0f,
    val screenHeight: Float = 0f,
    val oneQuarterOfHeightScreen: Int = 0,
    val oneThirdOfWidthScreen: Int = 0,

    val lastItemPositioned: Boolean = false,
    val offsetProfiles: List<Pair<Int, Int>> = emptyList(),
    val selectedImageAlpha: Float = 1f,
    val centerScreen: Pair<Int, Int> = Pair(0, 0),
)

