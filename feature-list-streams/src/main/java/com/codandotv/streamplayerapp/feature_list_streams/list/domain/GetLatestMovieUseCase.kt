package com.codandotv.streamplayerapp.feature_list_streams.list.domain

import com.codandotv.streamplayerapp.feature_list_streams.list.data.ListStreamRepository
import com.codandotv.streamplayerapp.feature_list_streams.list.domain.model.Stream
import kotlinx.coroutines.flow.Flow
import org.koin.core.annotation.Factory

interface GetTopRatedStream {
    suspend operator fun invoke(): Flow<Stream>
}

@Factory
class GetTopRatedStreamImpl(
    private val repository: ListStreamRepository
) : GetTopRatedStream {
    override suspend operator fun invoke(): Flow<Stream> {
        return repository.topRatedStream()
    }
}