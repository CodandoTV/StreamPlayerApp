package com.codandotv.streamplayerapp.feature_list_streams.data

import com.codandotv.streamplayerapp.core_networking.handleError.toResult
import com.codandotv.streamplayerapp.feature_list_streams.domain.model.ListStream
import com.codandotv.streamplayerapp.feature_list_streams.domain.toListStream
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

interface ListStreamRepository {
    suspend fun getMovies(): Flow<List<ListStream>>
}

class ListStreamRepositoryImpl(
    private val service: ListStreamService
) : ListStreamRepository {

    override suspend fun getMovies(): Flow<List<ListStream>> {
        val genres = service.getGenres().toResult().getOrNull()
        return flow {
            val response = genres?.genres?.mapNotNull { genre ->
                service.getMovies(genre.id.toString())
                    .toResult().getOrNull()?.toListStream(
                        genre.name
                    )
            }
            emit(response ?: emptyList())
        }
    }
}