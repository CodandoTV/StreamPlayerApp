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
import org.koin.core.annotation.Factory

interface ListStreamRepository {
    suspend fun getGenres(): Flow<List<Genre>>

    suspend fun topRatedStream(): Flow<Stream>

    fun loadMovies(genre: Genre): Flow<PagingData<Stream>>
}

@Factory
class ListStreamRepositoryImpl(
    private val service: ListStreamService,
) : ListStreamRepository {

    override suspend fun getGenres(): Flow<List<Genre>> {
        return service.getGenres().toFlow().map { it.toGenres() }
    }

    override suspend fun topRatedStream() = service.getTopRatedMovies().toFlow().map {
        it.results.first { it.poster_path != null }.toStream()
    }

    override fun loadMovies(genre: Genre): Flow<PagingData<Stream>> {
        return Pager(
            config = PagingConfig(
                pageSize = PAGE_SIZE,
                maxSize = MAX_SIZE,
            ),
            pagingSourceFactory = {
                StreamDataSource(service, genreName = genre.name, genreId = genre.id)
            },
            initialKey = 1
        ).flow
    }

    companion object {
        private const val PAGE_SIZE = 20
        private const val MAX_SIZE = 500
    }
}