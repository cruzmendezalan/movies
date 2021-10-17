package com.android.umba.presentation.moviedetails

import com.android.umba.domain.MovieDetails

sealed class MovieState {
    object Loading : MovieState()
    class MovieDetailsReady(val movieDetails: MovieDetails) : MovieState()
    class LatestMovieReady(val movie: MovieDetails) : MovieState()
    class Errors(error: Throwable) : MovieState()
}
