package com.android.movies.domain

import com.android.movies.data.api.ApiConfiguration
import com.android.movies.data.api.MovieDbServices
import com.android.movies.entities.MovieDetails
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class GetMovieDetailsUseCaseTest {

    private val MOVIE_DETAILS = "success_movie_details.json"
    private val API_CONFIGURATION = "success_movie_api_configuration.json"

    @get:Rule
    val coroutinesTestRule = CoroutinesTestRule()

    @Mock
    lateinit var services: MovieDbServices

    @Mock
    lateinit var configurationApi: ApiConfigurationProvider

    @Test
    fun `sanity check`() = coroutinesTestRule.runBlockingTest {
        val movieDetailsResponse = FileMapper.toJson(MOVIE_DETAILS, MovieDetails::class.java)
        val apiConfigurationResponse =
            FileMapper.toJson(API_CONFIGURATION, ApiConfiguration::class.java)

        whenever(services.getMovieDetails(any(), any())).thenReturn(movieDetailsResponse)
        whenever(configurationApi.getConfiguration()).thenReturn(apiConfigurationResponse)

        val usecase = GetMovieDetailsUseCase(services, configurationApi)

        val result = usecase.invoke("")
        Assert.assertTrue(result.id == 413323)
    }
}


