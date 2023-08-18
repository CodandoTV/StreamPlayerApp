package com.codandotv.streamplayerapp.feature_list_streams.detail.data

import com.codandotv.streamplayerapp.core_local_storage.dao.FavoriteDao
import com.codandotv.streamplayerapp.core_local_storage.entities.MovieEntity
import com.codandotv.streamplayerapp.core_networking.handleError.toFlow
import com.codandotv.streamplayerapp.feature_list_streams.detail.domain.DetailStream
import com.codandotv.streamplayerapp.feature_list_streams.detail.domain.toDetailStream
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

interface DetailStreamRepository {
    suspend fun getMovie(): Flow<DetailStream>

    suspend fun insertToFavorites(detailStreamId: String)
}

class DetailStreamRepositoryImpl(
    private val movieId: String,
    private val service: DetailStreamService,
    private val favoriteDao: FavoriteDao
) : DetailStreamRepository {

    override suspend fun getMovie(): Flow<DetailStream> =
        service.getMovie(movieId)
        .toFlow()
        .map {
            it.toDetailStream(isFavorite())
        }

    private suspend fun isFavorite() = favoriteDao.fetchAll().any { movie -> movie.id == movieId }

    override suspend fun insertToFavorites(detailStreamId: String) {
        service.getMovie(movieId)
            .toFlow()
            .map {
                it.toDetailStream()
            }.collect { movie ->
                val favoriteMovie = MovieEntity(
                    id = movie.id,
                    title = movie.title,
                    overview = movie.overview,
                    tagline = movie.tagline,
                    url = movie.url,
                    releaseYear = movie.releaseYear
                )
                favoriteDao.insert(favoriteMovie)
            }
    }
}