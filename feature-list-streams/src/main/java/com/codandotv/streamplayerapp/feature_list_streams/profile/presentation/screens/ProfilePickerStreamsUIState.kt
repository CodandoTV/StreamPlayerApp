package com.codandotv.streamplayerapp.feature_list_streams.profile.presentation.screens

import com.codandotv.streamplayerapp.feature_list_streams.profile.domain.ProfileStream

sealed class ProfilePickerStreamsUIState {
    data class ProfilePickerStreamsLoadedUIState(
        val profileStream: List<ProfileStream>,
    ) : ProfilePickerStreamsUIState()

    object LoadingStreamUIState : ProfilePickerStreamsUIState()
}

