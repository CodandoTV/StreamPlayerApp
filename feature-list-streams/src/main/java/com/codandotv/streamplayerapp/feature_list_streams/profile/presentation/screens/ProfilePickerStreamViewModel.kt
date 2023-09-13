package com.codandotv.streamplayerapp.feature_list_streams.profile.presentation.screens

import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.codandotv.streamplayerapp.core_networking.handleError.catchFailure
import com.codandotv.streamplayerapp.feature_list_streams.profile.domain.ProfilePickerStreamUseCase
import com.codandotv.streamplayerapp.feature_list_streams.profile.domain.ProfileStream
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ProfilePickerStreamViewModel(
    private val useCase: ProfilePickerStreamUseCase,
) : ViewModel(), DefaultLifecycleObserver {

    private val _uiState = MutableStateFlow(ProfilePickerStreamsUIState())
    val uiState = _uiState.stateIn(
        viewModelScope,
        SharingStarted.Eagerly,
        initialValue = _uiState.value
    )


    override fun onCreate(owner: LifecycleOwner) {
        super.onCreate(owner)

        viewModelScope.launch {
            useCase.getProfile()
                .catchFailure {
                    println(">>>> ${it.errorMessage}")
                }
                .collect { profilePickerStream ->
                    _uiState.update {
                        ProfilePickerStreamsUIState(
                            profilesStream = profilePickerStream,
                            selectedItem = profilePickerStream.first(),
                            isLoading = false
                        )
                    }
                }
        }
    }

    private fun changeShowCenterImage(value: Boolean) {
        _uiState.value = _uiState.value.copy(
            showCenterImage = value
        )
    }

    fun changeCenterImageAlpha(alpha: Float) {
        _uiState.value = _uiState.value.copy(
            centerImageAlpha = alpha
        )
    }

    private fun changeSelectedProfile(profile: ProfileStream) {
        _uiState.value = _uiState.value.copy(
            selectedItem = profile
        )
    }

    private fun changeCanMoveImageToCenter(value: Boolean) {
        _uiState.value = _uiState.value.copy(
            canMoveImageToCenter = value
        )
    }

    private fun changeExpandImage(value: Boolean) {
        _uiState.value = _uiState.value.copy(
            expandImage = value
        )
    }

    fun changeScreenSize(width: Float, height: Float, widthPx: Int, heightPx: Int) {
        _uiState.value = _uiState.value.copy(
            screenWidth = width,
            screenHeight = height
        )

        calculateGridScreenSize(widthPx, heightPx)
    }

    private fun calculateGridScreenSize(widthPx: Int, heightPx: Int) {
        val oneThirdOfWidthScreen = widthPx / 3
        val oneQuarterOfHeightScreen = heightPx / 4
        _uiState.value = _uiState.value.copy(
            oneThirdOfWidthScreen = oneThirdOfWidthScreen,
            oneQuarterOfHeightScreen = oneQuarterOfHeightScreen
        )

        val haltSizeImage = _uiState.value.haltSizeImage

        val listOffsetProfiles = listOf(
            Pair(oneThirdOfWidthScreen - haltSizeImage, 0),
            Pair(oneThirdOfWidthScreen * 2 - haltSizeImage, 0),
            Pair(oneThirdOfWidthScreen - haltSizeImage, oneQuarterOfHeightScreen),
            Pair(
                oneThirdOfWidthScreen * 2 - haltSizeImage,
                oneQuarterOfHeightScreen
            ),
            Pair(
                oneThirdOfWidthScreen - haltSizeImage,
                oneQuarterOfHeightScreen * 2
            ),
        )

        _uiState.value = _uiState.value.copy(
            offsetProfiles = listOffsetProfiles
        )
    }

    fun setLastItemPositioned(value: Boolean) {
        _uiState.value = _uiState.value.copy(
            lastItemPositioned = value
        )
    }


    fun setHaltSizeImage(size: Int) {
        _uiState.value = _uiState.value.copy(
            haltSizeImage = size
        )
    }

    fun setHalfExpandedSizeImage(size: Int) {
        _uiState.value = _uiState.value.copy(
            halfExpandedSizeImage = size
        )
        calculateCenterScreen()
    }


    private fun setSelectedImageAlpha(alpha: Float) {
        _uiState.value = _uiState.value.copy(
            selectedImageAlpha = alpha
        )
    }

    private fun calculateCenterScreen() {
        with(_uiState.value) {
            val newCenterScreen = Pair(
                oneThirdOfWidthScreen + oneThirdOfWidthScreen / 2 - halfExpandedSizeImage,
                oneQuarterOfHeightScreen
            )

            _uiState.value = _uiState.value.copy(
                centerScreen = newCenterScreen
            )
        }

    }

    fun clickSelectedProfile(profile: ProfileStream) {
        viewModelScope.launch {
            with(_uiState.value) {
                // move hide image to the position of the clicked item
                changeSelectedProfile(profile)

                // displacement to clicked item
                delay(200)

                // show hidden image
                changeShowCenterImage(!showCenterImage)

                // maybe can remove this delay, when to join codes of this block
                delay(200)

                // hidden selected image
//                                            selectedImageAlpha = 0f
                setSelectedImageAlpha(0f)

                // move the new image to the center screen
//                                            canMoveImageToCenter = !canMoveImageToCenter
                changeCanMoveImageToCenter(!canMoveImageToCenter)

                // as increase in size
                changeExpandImage(!expandImage)
            }
        }
    }

}