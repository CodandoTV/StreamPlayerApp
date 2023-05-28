package com.codandotv.streamplayerapp.feature_list_streams.list.data

import com.codandotv.streamplayerapp.core_networking.handleError.toFlow
import com.codandotv.streamplayerapp.feature_list_streams.list.domain.model.ListStream
import com.codandotv.streamplayerapp.feature_list_streams.list.domain.toListStream
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*

interface ListStreamRepository {
    suspend fun getMovies(): Flow<List<ListStream>>
}

class ListStreamRepositoryImpl(
    private val service: ListStreamService,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO,
) : ListStreamRepository {

    override suspend fun getMovies(): Flow<List<ListStream>> =
        service.getGenres()
            .toFlow()
            .map {
                it.genres
            }
            .map { genres ->
                genres.map { genre ->
                    service.getMovies(genre.id.toString())
                        .toFlow()
                        .map {
                            it.toListStream(
                                genre.name
                            )
                        }.first()
                }
            }.flowOn(dispatcher)
}