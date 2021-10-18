package com.android.movies.di

import com.android.movies.domain.ApiConfigurationProvider
import com.android.movies.domain.ApiConfigurationProviderImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
abstract class DomainModuleBinds {
    @Binds
    @Singleton
    abstract fun bindApiConfigurationProvider(impl: ApiConfigurationProviderImpl): ApiConfigurationProvider
}
