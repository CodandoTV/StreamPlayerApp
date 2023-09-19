package com.codandotv.streamplayerapp.feature_profile.profile.data

import android.util.Log
import com.codandotv.streamplayerapp.core_networking.handleError.toFlow
import com.codandotv.streamplayerapp.core_networking.handleError.toResult
import com.codandotv.streamplayerapp.feature_profile.profile.domain.ProfileStream
import com.codandotv.streamplayerapp.feature_profile.profile.domain.toProfiles
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map

interface ProfilePickerStreamRepository {
    suspend fun getProfiles(): Flow<List<ProfileStream>>
}

class ProfilePickerStreamRepositoryImpl(
    private val service: ProfilePickerStreamService
) : ProfilePickerStreamRepository {

    override suspend fun getProfiles(): Flow<List<ProfileStream>> {

        with(service.getProfiles()) {
            if (this.toResult().isFailure || this.toResult().getOrNull() == null) {
                Log.i("ProfilePickerStreamRepositoryImpl", "versão off carregada")
                return flowOf(mockProfiles)
            } else {
                return this.toFlow().map { it.toProfiles() }
            }
        }
    }
}


private val mockProfiles = listOf(
    ProfileStream(
        id = "1",
        name = "Chapei de Palha",
        imageUrl = "https://raw.githubusercontent.com/git-jr/sample-files/main/profile%20pics/netflix_profile_pic_1.png"
    ),
    ProfileStream(
        id = "2",
        name = "Martha",
        imageUrl = "https://raw.githubusercontent.com/git-jr/sample-files/main/profile%20pics/netflix_profile_pic_2.png"
    ),
    ProfileStream(
        id = "3",
        name = "Morningstar",
        imageUrl = "https://raw.githubusercontent.com/git-jr/sample-files/main/profile%20pics/netflix_profile_pic_3.png"
    ),
    ProfileStream(
        id = "4",
        name = "Shelby",
        imageUrl = "https://raw.githubusercontent.com/git-jr/sample-files/main/profile%20pics/netflix_profile_pic_4.png"
    ),
    ProfileStream(
        id = "5",
        name = "Mooncake",
        imageUrl = "https://raw.githubusercontent.com/git-jr/sample-files/main/profile%20pics/netflix_profile_pic_5.png"
    ),
    ProfileStream(
        id = "6",
        name = "CodandoTV",
        imageUrl = "https://raw.githubusercontent.com/git-jr/sample-files/main/profile%20pics/netflix_profile_pic_6.png"
    )
)
