package com.android.umba.domain

import com.android.umba.data.api.ApiConfiguration
import com.android.umba.data.api.MovieDbServices
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

class ApiConfigurationProviderImpl @Inject constructor(private val apiServices: MovieDbServices) :
    ApiConfigurationProvider {

    private val apiConfiguration: ApiConfiguration by lazy {
        runBlocking {
            apiServices.getApiConfiguration()
        }
    }

    override suspend fun getConfiguration(): ApiConfiguration = apiConfiguration
}
