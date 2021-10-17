package com.android.umba.presentation.popular

import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.android.umba.domain.MovieEntity

abstract class MovieHolder<T : ViewBinding>(val binding: T) :
    RecyclerView.ViewHolder(binding.root) {

    abstract fun bind(movie: MovieEntity)
}
