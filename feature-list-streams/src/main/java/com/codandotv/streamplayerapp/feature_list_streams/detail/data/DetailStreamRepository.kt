package com.codandotv.streamplayerapp.feature_list_streams.detail.data

import com.codandotv.streamplayerapp.core_local_storage.data.dao.FavoriteDao
import com.codandotv.streamplayerapp.core_networking.handleError.toFlow
import com.codandotv.streamplayerapp.feature_list_streams.detail.domain.DetailStream
import com.codandotv.streamplayerapp.feature_list_streams.detail.domain.VideoStream
import com.codandotv.streamplayerapp.feature_list_streams.detail.domain.toDetailStream
import com.codandotv.streamplayerapp.feature_list_streams.detail.domain.toDetailStreamLocal
import com.codandotv.streamplayerapp.feature_list_streams.detail.domain.toVideoStreams
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map

interface DetailStreamRepository {
    suspend fun getMovie(): Flow<DetailStream>
    suspend fun deleteFromMyList(movie: String)
    suspend fun insertToMyList(movie: DetailStream)
    suspend fun isFavorite(movieId:String) : Boolean
    suspend fun getVideoStreams(): Flow<List<VideoStream>>
}

class DetailStreamRepositoryImpl(
    private val movieId: String,
    private val service: DetailStreamService,
    private val favoriteDao: FavoriteDao,
) : DetailStreamRepository {

    override suspend fun getMovie(): Flow<DetailStream> =
        service.getMovie(movieId)
            .toFlow()
            .map {
                it.toDetailStream(isFavorite(movieId))
            }


    override suspend fun deleteFromMyList(movie: String) = favoriteDao.delete(movie)

    override suspend fun insertToMyList(movie: DetailStream) = favoriteDao.insert(movie.toDetailStreamLocal())

    override suspend fun isFavorite(movieId: String) : Boolean = favoriteDao.fetchAll().any { movie -> movie.id == movieId }
    
    override suspend fun getVideoStreams(): Flow<List<VideoStream>> =
        service.getVideoStreams(movieId)
            .toFlow()
            .map {
                it.toVideoStreams()
            }
}