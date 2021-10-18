package com.android.movies.presentation.popular

import androidx.recyclerview.widget.DiffUtil
import com.android.movies.entities.MovieEntity

object MoviesDiffCallback : DiffUtil.ItemCallback<MovieEntity>() {
    override fun areItemsTheSame(oldItem: MovieEntity, newItem: MovieEntity): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: MovieEntity, newItem: MovieEntity): Boolean {
        return oldItem.popularity == newItem.popularity
    }
}
