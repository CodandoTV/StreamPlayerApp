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
            Pair(
                oneThirdOfWidthScreen * 2 - haltSizeImage,
                oneQuarterOfHeightScreen * 2
            ),
        )

        _uiState.value = _uiState.value.copy(
            offsetProfiles = listOffsetProfiles
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

    fun setCenterImageAlpha(alpha: Float) {
        _uiState.value = _uiState.value.copy(
            centerImageAlpha = alpha
        )
    }

    fun setScreenSize(width: Float, height: Float, widthPx: Int, heightPx: Int) {
        _uiState.value = _uiState.value.copy(
            screenWidth = width,
            screenHeight = height
        )

        calculateGridScreenSize(widthPx, heightPx)
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

    fun moveSelectedProfileToCenterImage(profile: ProfileStream) {
        viewModelScope.launch {
            with(_uiState.value) {
                // move hide image to the position of the clicked item
                _uiState.value = _uiState.value.copy(
                    selectedItem = profile
                )

                // delay displacement to clicked item
                delay(200)

                // show hidden image
                _uiState.value = _uiState.value.copy(
                    showCenterImage = !showCenterImage
                )

                // maybe can remove this delay, when to join codes of this block
                delay(100)

                // hidden selected image
                // move the new image to the center screen
                // as increase in size
                _uiState.value = _uiState.value.copy(
                    selectedImageAlpha = 0f,
                    canMoveImageToCenter = !canMoveImageToCenter,
                    expandImage = !expandImage
                )
            }
        }
    }

}