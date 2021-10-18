package com.android.movies.presentation.popular

import android.os.Bundle
import androidx.navigation.findNavController
import coil.load
import com.android.movies.R
import com.android.movies.databinding.MovieItemBinding
import com.android.movies.entities.MovieEntity

class GridMovieHolder(binding: MovieItemBinding) :
    MovieHolder<MovieItemBinding>(binding) {

    override fun bind(movie: MovieEntity) {
        with(binding) {
            movieName.text = movie.title
            imageMovie.load(movie.backdrop_path)
            overview.text = movie.overview
            popularity.text = this.root.context.getString(
                R.string.popularity,
                movie.popularity
            )
        }

        binding.container.setOnClickListener {
            it.findNavController().navigate(
                R.id.to_movie_details,
                Bundle().apply {
                    putString(it.context.getString(R.string.toMovieDetailsIdKey), movie.id)
                }
            )
        }
    }
}
