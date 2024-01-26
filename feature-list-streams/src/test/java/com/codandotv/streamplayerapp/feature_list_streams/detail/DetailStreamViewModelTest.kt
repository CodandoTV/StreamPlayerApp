package com.codandotv.streamplayerapp.feature_list_streams.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LifecycleOwner
import com.codandotv.streamplayerapp.feature_list_streams.detail.domain.DetailStreamUseCase
import com.codandotv.streamplayerapp.feature_list_streams.detail.domain.VideoStreamsUseCase
import com.codandotv.streamplayerapp.feature_list_streams.detail.presentation.screens.DetailStreamViewModel
import com.codandotv.streamplayerapp.feature_list_streams.detail.presentation.screens.DetailStreamsUIState
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import kotlin.test.assertTrue

class DetailStreamViewModelTest {
    private lateinit var detailStreamViewModel: DetailStreamViewModel
    private lateinit var detailUseCase: DetailStreamUseCase
    private lateinit var videoUseCase: VideoStreamsUseCase

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @get:Rule
    var executorRule = InstantTaskCoroutinesExecutorRule()

    @Before
    fun setUp() {
        detailUseCase = mockk()
        videoUseCase = mockk()

        detailStreamViewModel = DetailStreamViewModel(
            detailStreamUseCase = detailUseCase,
            videoStreamsUseCase = videoUseCase,
            dispatcher = executorRule.dispatcher
        )
    }

    @Test
    fun `should load the movies with videoId`() {
        runTest {
            coEvery { detailUseCase.getMovie() } returns flowOf(detailStream)
            coEvery { videoUseCase.getVideoStreams() } returns flowOf(videosStreamsList)

            detailStreamViewModel.loadDetail()

            coVerify {
                detailStreamViewModel.uiState.value.let {
                    DetailStreamsUIState.LoadingStreamUIState
                }
                detailUseCase.getMovie()
                detailStreamViewModel.uiState.value.let {
                    assertTrue {
                        it == DetailStreamsUIState.DetailStreamsLoadedUIState(
                            detailStream = detailStream, videoId = videosStreamsList.first().videoId
                        )
                    }
                }
            }
        }
    }

}