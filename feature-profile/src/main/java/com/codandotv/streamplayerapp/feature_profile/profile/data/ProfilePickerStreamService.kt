package com.codandotv.streamplayerapp.feature_profile.profile.data

import com.codandotv.streamplayerapp.core_networking.handleError.NetworkResponse
import com.codandotv.streamplayerapp.feature_profile.profile.data.model.ProfilesResponse
import retrofit2.http.GET

interface ProfilePickerStreamService {
    @GET("profiles")
    suspend fun getProfiles(): NetworkResponse<ProfilesResponse>
}