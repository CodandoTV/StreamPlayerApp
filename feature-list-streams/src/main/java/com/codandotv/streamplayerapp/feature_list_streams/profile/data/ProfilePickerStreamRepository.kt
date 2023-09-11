package com.codandotv.streamplayerapp.feature_list_streams.profile.data

import com.codandotv.streamplayerapp.core_networking.handleError.toFlow
import com.codandotv.streamplayerapp.feature_list_streams.profile.domain.ProfileStream
import com.codandotv.streamplayerapp.feature_list_streams.profile.domain.toProfiles
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
        val remoteProfiles = service.getProfiles().toFlow().map { it.toProfiles() }
        val localProfiles = flowOf(mockProfiles)

//      ideal solution, but as remote profiles is not working to profiles, I'm using mockProfiles
//      return combine(remoteProfiles, localProfiles) { remote, local -> remote + local }

        return localProfiles
    }
}


private val mockProfiles = listOf(
    ProfileStream(
        name = "Chapei de Palha",
        imageUrl = "https://raw.githubusercontent.com/git-jr/sample-files/main/profile%20pics/netflix_profile_pic_1.png"
    ),
    ProfileStream(
        name = "Jonas",
        imageUrl = "https://raw.githubusercontent.com/git-jr/sample-files/main/profile%20pics/netflix_profile_pic_2.png"
    ),
    ProfileStream(
        name = "Random name 1",
        imageUrl = "https://raw.githubusercontent.com/git-jr/sample-files/main/profile%20pics/netflix_profile_pic_3.png"
    ),
    ProfileStream(
        name = "Random name 3",
        imageUrl = "https://raw.githubusercontent.com/git-jr/sample-files/main/profile%20pics/netflix_profile_pic_4.png"
    ),
    ProfileStream(
        name = "Random name 2",
        imageUrl = "https://raw.githubusercontent.com/git-jr/sample-files/main/profile%20pics/netflix_profile_pic_5.png"
    )
)
