package com.codandotv.streamplayerapp.feature_list_streams.list.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.codandotv.streamplayerapp.core_networking.handleError.NetworkResponse
import com.codandotv.streamplayerapp.feature_list_streams.list.domain.model.Stream
import com.codandotv.streamplayerapp.feature_list_streams.list.domain.toListStream

class StreamDataSource(
    private val service: ListStreamService,
    private val genreId: Long,
    private val genreName: String,
) : PagingSource<Int, Stream>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Stream> {
        val nextPageNumber = params.key ?: START_PAGE_INDEX

        return try {
            val response = service.getPaginatedMovies(
                genres = genreId.toString(),
                page = nextPageNumber
            )

            if (response is NetworkResponse.Success) {
                LoadResult.Page(
                    data = response.value.toListStream(genreName).streams,
                    prevKey = if (nextPageNumber > 1) nextPageNumber - 1 else null,
                    nextKey = nextPageNumber.plus(1)
                )
            } else {
                throw IllegalStateException("Something wrong")
            }
        } catch (exception: Exception) {
            LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Stream>): Int? = null

    companion object {
        private const val START_PAGE_INDEX = 1
    }
}