package com.android.movies.presentation.popular

import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import com.android.movies.entities.MovieEntity

class MoviesListAdapter(
    private val factory: MovieHolderFactory
) : PagingDataAdapter<MovieEntity, MovieHolder<*>>(MoviesDiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieHolder<*> =
        factory.buildMovieHolder(parent)

    override fun onBindViewHolder(holderLinear: MovieHolder<*>, position: Int) {
        val item = getItem(position)
        if (item != null) {
            holderLinear.bind(item)
        }

    }
}
