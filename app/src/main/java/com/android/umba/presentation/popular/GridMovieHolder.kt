package com.android.umba.presentation.popular

import coil.load
import com.android.umba.databinding.MovieItemBinding
import com.android.umba.domain.MovieEntity

class GridMovieHolder(binding: MovieItemBinding) :
    MovieHolder<MovieItemBinding>(binding) {

    override fun bind(movie: MovieEntity) {
        with(binding) {
            movieName.text = movie.title
            imageMovie.load(movie.backdrop_path)
            overview.text = movie.overview
            popularity.text = "Popularity: ${movie.popularity}"
        }
    }
}
