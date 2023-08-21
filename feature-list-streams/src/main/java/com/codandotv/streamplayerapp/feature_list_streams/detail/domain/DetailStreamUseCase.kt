package com.codandotv.streamplayerapp.feature_list_streams.detail.domain

import com.codandotv.streamplayerapp.feature_list_streams.detail.data.DetailStreamRepository
import kotlinx.coroutines.flow.Flow

interface DetailStreamUseCase {
    suspend fun getMovie(): Flow<DetailStream>

    suspend fun toggleItemInFavorites(movie: DetailStream)
}

class DetailStreamUseCaseImpl(
    private val detailStreamRepository: DetailStreamRepository
) : DetailStreamUseCase {

    override suspend fun getMovie(): Flow<DetailStream> =
        detailStreamRepository.getMovie()

    override suspend fun toggleItemInFavorites(movie: DetailStream) {
        detailStreamRepository.toggleItemInFavorites(movie)
    }
}