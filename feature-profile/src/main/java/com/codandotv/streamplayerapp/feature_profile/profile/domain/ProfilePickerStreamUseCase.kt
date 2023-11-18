package com.codandotv.streamplayerapp.feature_profile.profile.domain

import com.codandotv.streamplayerapp.feature_profile.profile.data.ProfilePickerStreamRepository
import kotlinx.coroutines.flow.Flow

interface ProfilePickerStreamUseCase {
    suspend fun getProfile(): Flow<List<ProfileStream>>
    fun getListOffsetProfiles(
        haltSizeImage: Int,
        oneThirdOfWidthScreen: Int,
        oneQuarterOfHeightScreen: Int
    ): List<Pair<Int, Int>>

    fun calculateOneThirdOfWidthScreen(widthPx: Int): Int
    fun calculateOneQuarterOfHeightScreen(heightPx: Int): Int
    fun calculateCenterScreen(
        oneThirdOfWidthScreen: Int,
        halfExpandedSizeImage: Int,
        oneQuarterOfHeightScreen: Int
    ): Pair<Int, Int>
}

class ProfilePickerStreamUseCaseImpl(
    private val profilePickerStreamRepository: ProfilePickerStreamRepository
) : ProfilePickerStreamUseCase {

    override suspend fun getProfile(): Flow<List<ProfileStream>> =
        profilePickerStreamRepository.getProfiles()

    override fun getListOffsetProfiles(
        haltSizeImage: Int,
        oneThirdOfWidthScreen: Int,
        oneQuarterOfHeightScreen: Int
    ): List<Pair<Int, Int>> {

        return listOf(
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
    }

    override fun calculateOneThirdOfWidthScreen(widthPx: Int) = widthPx / 3

    override fun calculateOneQuarterOfHeightScreen(heightPx: Int) = heightPx / 4

    override fun calculateCenterScreen(
        oneThirdOfWidthScreen: Int,
        halfExpandedSizeImage: Int,
        oneQuarterOfHeightScreen: Int
    ): Pair<Int, Int> {
        return Pair(
            oneThirdOfWidthScreen + oneThirdOfWidthScreen / 2 - halfExpandedSizeImage,
            oneQuarterOfHeightScreen
        )
    }
}
