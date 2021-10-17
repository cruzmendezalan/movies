package com.android.umba.di

import com.android.umba.domain.ApiConfigurationProvider
import com.android.umba.domain.ApiConfigurationProviderImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
abstract class DomainModuleBinds {
    @Binds
    abstract fun bindApiConfigurationProvider(impl: ApiConfigurationProviderImpl): ApiConfigurationProvider
}
