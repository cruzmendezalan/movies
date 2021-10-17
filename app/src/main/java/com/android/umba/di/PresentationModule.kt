package com.android.umba.di

import com.android.umba.presentation.popular.GridMovieHolderFactory
import com.android.umba.presentation.popular.MovieHolderFactory
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
abstract class PresentationModule {
    @Binds
    abstract fun bindMovieHolderFactory(impl: GridMovieHolderFactory): MovieHolderFactory
}

