package com.android.movies.domain

import com.android.movies.data.api.ApiConfiguration

interface ApiConfigurationProvider {
    suspend fun getConfiguration(): ApiConfiguration
}
