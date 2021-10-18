package com.android.movies.presentation.moviedetails

import com.android.movies.entities.MovieDetails

sealed class MovieState {
    object Loading : MovieState()
    class MovieDetailsReady(val movieDetails: MovieDetails) : MovieState()
    class LatestMovieReady(val movie: MovieDetails) : MovieState()
    class Errors(error: Throwable) : MovieState()
}
