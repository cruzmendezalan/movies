package com.android.movies.presentation.popular

import android.view.ViewGroup
import javax.inject.Inject

class GridMovieHolderFactory @Inject constructor() : MovieHolderFactory {
    override fun buildMovieHolder(parent: ViewGroup): MovieHolder<*> {
        return GridMovieHolder(parent.createViewBinding())
    }
}
