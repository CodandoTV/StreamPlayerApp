package com.codandotv.streamplayerapp.feature_list_streams.list.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.codandotv.streamplayerapp.core_networking.handleError.toFlow
import com.codandotv.streamplayerapp.feature_list_streams.list.domain.model.Genre
import com.codandotv.streamplayerapp.feature_list_streams.list.domain.model.Stream
import com.codandotv.streamplayerapp.feature_list_streams.list.domain.toGenres
import com.codandotv.streamplayerapp.feature_list_streams.list.domain.toStream
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.*

interface ListStreamRepository {
    suspend fun getGenres(): Flow<List<Genre>>

    suspend fun topRatedStream(): Flow<Stream>

    fun loadMovies(genre: Genre): Flow<PagingData<Stream>>
}

class ListStreamRepositoryImpl(
    private val service: ListStreamService,
    private val dispatcher: CoroutineDispatcher,
) : ListStreamRepository {

    override suspend fun getGenres(): Flow<List<Genre>> {
        return service.getGenres().toFlow().map { it.toGenres() }
    }

    override suspend fun topRatedStream(): Flow<Stream> {
        return service.getTopRatedMovies().toFlow().map { it.results.first().toStream() }
    }

    override fun loadMovies(genre: Genre): Flow<PagingData<Stream>> {
        return Pager(
            config = PagingConfig(
                pageSize = 20,
                maxSize = 500,
            ),
            pagingSourceFactory = {
                StreamDataSource(service, genreName = genre.name, genreId = genre.id)
            },
            initialKey = 1
        ).flow
    }
}