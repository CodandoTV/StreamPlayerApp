package com.codandotv.streamplayerapp.feature_list_streams.detail

import com.codandotv.streamplayerapp.feature_list_streams.detail.data.DetailStreamRepository
import com.codandotv.streamplayerapp.feature_list_streams.detail.domain.DetailStream
import com.codandotv.streamplayerapp.feature_list_streams.detail.domain.DetailStreamUseCase
import com.codandotv.streamplayerapp.feature_list_streams.detail.domain.DetailStreamUseCaseImpl
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import kotlin.test.assertTrue

class DetailStreamUseCaseTest {
    private lateinit var detailStreamUseCase: DetailStreamUseCase
    private val detailStreamRepository: DetailStreamRepository = mockk()

    @Before
    fun setUp() {
        detailStreamUseCase = DetailStreamUseCaseImpl(
            detailStreamRepository = detailStreamRepository
        )
    }

    @Test
    fun `load movies`() {
        runBlocking {
            coEvery { detailStreamRepository.getMovie() } returns flowOf(detailStream)

            detailStreamUseCase.getMovie()
                .collect{
                    assertTrue {
                        it == detailStream
                    }
                }

            coVerify{
                detailStreamRepository.getMovie()
            }
        }
    }
}