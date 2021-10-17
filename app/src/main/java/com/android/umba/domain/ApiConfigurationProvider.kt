package com.android.umba.domain

import com.android.umba.data.api.ApiConfiguration

interface ApiConfigurationProvider {
    suspend fun getConfiguration(): ApiConfiguration
}
