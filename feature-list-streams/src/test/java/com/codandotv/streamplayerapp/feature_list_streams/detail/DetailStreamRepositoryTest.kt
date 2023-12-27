package com.codandotv.streamplayerapp.feature_list_streams.detail

import com.codandotv.streamplayerapp.core_local_storage.data.dao.FavoriteDao
import com.codandotv.streamplayerapp.core_networking.handleError.NetworkResponse
import com.codandotv.streamplayerapp.feature_list_streams.detail.data.DetailStreamRepository
import com.codandotv.streamplayerapp.feature_list_streams.detail.data.DetailStreamRepositoryImpl
import com.codandotv.streamplayerapp.feature_list_streams.detail.data.DetailStreamService
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.coVerifyOrder
import io.mockk.mockk
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class DetailStreamRepositoryTest {
    private lateinit var repository: DetailStreamRepository
    private val movieId = movieIdString
    private val service: DetailStreamService = mockk()
    private val favoriteDao: FavoriteDao = mockk()

    @Before
    fun setUp() {
        repository = DetailStreamRepositoryImpl(
            movieId = movieId,
            service = service,
            favoriteDao = favoriteDao
        )
    }

    @Test
    fun `getmovie should load the movies when passed a movieId`() {
        runBlocking {
            coEvery { service.getMovie(movieId) } returns NetworkResponse.Success(detailStreamResponse)
            coEvery { favoriteDao.fetchAll() } returns emptyList()

            repository.getMovie()
                .collectLatest {
                    it == detailStream
                }

            coVerifyOrder {
                service.getMovie(movieId)
                favoriteDao.fetchAll()
            }
        }
    }
}