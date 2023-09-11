package com.codandotv.streamplayerapp.feature_list_streams.profile.domain

import com.codandotv.streamplayerapp.feature_list_streams.profile.data.ProfilePickerStreamRepository
import kotlinx.coroutines.flow.Flow

interface ProfilePickerStreamUseCase {
    suspend fun getProfile(): Flow<List<ProfileStream>>
}

class ProfilePickerStreamUseCaseImpl(
    private val profilePickerStreamRepository: ProfilePickerStreamRepository
) : ProfilePickerStreamUseCase {

    override suspend fun getProfile(): Flow<List<ProfileStream>> =
        profilePickerStreamRepository.getProfiles()
}
