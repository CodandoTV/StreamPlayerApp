package com.codandotv.streamplayerapp.feature_list_streams.profile.presentation.screens

import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.codandotv.streamplayerapp.core_networking.handleError.catchFailure
import com.codandotv.streamplayerapp.feature_list_streams.profile.domain.ProfilePickerStreamUseCase
import com.codandotv.streamplayerapp.feature_list_streams.profile.presentation.screens.ProfilePickerStreamsUIState.ProfilePickerStreamsLoadedUIState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ProfilePickerStreamViewModel(
    private val useCase: ProfilePickerStreamUseCase,
) : ViewModel(), DefaultLifecycleObserver {

    private val _uiState =
        MutableStateFlow<ProfilePickerStreamsUIState>(ProfilePickerStreamsUIState.LoadingStreamUIState)
    val uiState: StateFlow<ProfilePickerStreamsUIState> = _uiState.stateIn(
        viewModelScope,
        SharingStarted.Eagerly,
        initialValue = _uiState.value
    )

    override fun onCreate(owner: LifecycleOwner) {
        super.onCreate(owner)

        viewModelScope.launch {
            useCase.getProfile()
                .onStart { onLoading() }
                .catchFailure {
                    println(">>>> ${it.errorMessage}")
                }
                .collect { profilePickerStream ->
                    _uiState.update {
                        ProfilePickerStreamsLoadedUIState(
                            profileStream = profilePickerStream
                        )
                    }
                }
        }
    }

    private fun onLoading() {
        _uiState.update { ProfilePickerStreamsUIState.LoadingStreamUIState }
    }
}