package com.android.movies.domain

import com.android.movies.data.api.ApiConfiguration
import com.android.movies.data.api.MovieDbServices
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
class ApiConfigurationProviderImplTest {

    @get:Rule
    val coroutinesTestRule = CoroutinesTestRule()

    @Mock
    lateinit var services: MovieDbServices

    private val API_CONFIGURATION = "success_movie_api_configuration.json"

    @Test
    fun `sanity check`() = coroutinesTestRule.runBlockingTest {
        val apiConfigurationResponse =
            FileMapper.toJson(API_CONFIGURATION, ApiConfiguration::class.java)

        whenever(services.getApiConfiguration(any())).thenReturn(apiConfigurationResponse)

        val provider = ApiConfigurationProviderImpl(services)
        val result = provider.getConfiguration()

        Assert.assertTrue(result.change_keys.isNotEmpty())
    }
}
