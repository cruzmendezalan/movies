package com.android.movies.di

import com.android.movies.data.configuration.AndroidStringResourcesProviderImpl
import com.android.movies.domain.AndroidStringResourcesProvider
import com.android.movies.presentation.popular.GridMovieHolderFactory
import com.android.movies.presentation.popular.MovieHolderFactory
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
abstract class PresentationModule {
    @Binds
    abstract fun bindMovieHolderFactory(impl: GridMovieHolderFactory): MovieHolderFactory

    @Binds
    abstract fun bindAndroidStringResourcesProvider(impl: AndroidStringResourcesProviderImpl): AndroidStringResourcesProvider
}

